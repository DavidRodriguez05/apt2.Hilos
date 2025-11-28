import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Balsa extends Thread {

    private final String nombre;
    private final int capacidad;
    private final long tiempoRescate;
    private final List<Pasajero> arrayCompartido;
    private final Semaphore semaforo;

    public Balsa(String nombre, int capacidad, long tiempoRescateMs, List<Pasajero> arrayCompartido, Semaphore semaforo) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tiempoRescate = tiempoRescateMs;
        this.arrayCompartido = arrayCompartido;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {

        while (true) {
            List<Pasajero> rescatadosArray = new ArrayList<>();

            try {
                // Adquirir el semáforo para acceder al recurso compartido
                semaforo.acquire();

                for (int i = 0; i < capacidad && !arrayCompartido.isEmpty(); i++) {
                    rescatadosArray.add(arrayCompartido.remove(0));
                }

                // Liberar el semáforo
                semaforo.release();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }


            if (rescatadosArray.isEmpty()) {

                break;
            }


            if (rescatadosArray.size() == capacidad) {
                System.out.println("La balsa "+nombre+" esta llena, los pasajeros rescatados son:" + idsToString(rescatadosArray));
            } else {

                System.out.println("Es el último viaje de la barca "+nombre+", los pasajeros recatados son:" + idsToString(rescatadosArray));
            }


            try {
                Thread.sleep(tiempoRescate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println("La balsa "+nombre+" ha terminado");
    }

    private String idsToString(List<Pasajero> lista) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < lista.size(); i++) {
            sb.append(lista.get(i).getId());
            if (i < lista.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public int getCapacidad() {
        return capacidad;
    }
}