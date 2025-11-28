import java.util.List;
import java.util.concurrent.Semaphore;

public class Rescate implements Runnable {

    private final List<Pasajero> prioridades;

    public Rescate(List<Pasajero> pasajerosOrdenadosPorPrioridad) {
        // Ya no necesitamos Collections.synchronizedList porque usamos semáforos
        this.prioridades = pasajerosOrdenadosPorPrioridad;
    }

    @Override
    public void run() {
        // Crear un semáforo binario (1 permiso) para controlar el acceso al recurso compartido
        Semaphore semaforo = new Semaphore(1);

        Balsa acasta = new Balsa("Acasta", 1, 500, prioridades, semaforo);
        Balsa banff  = new Balsa("Banff", 2, 1000, prioridades, semaforo);
        Balsa cadiz  = new Balsa("Cadiz", 3, 2000, prioridades, semaforo);
        Balsa deimos = new Balsa("Deimos", 4, 4000, prioridades, semaforo);
        Balsa exped  = new Balsa("Expedición", 5, 8000, prioridades, semaforo);

        // Establecer prioridades de hilos: Mayor capacidad = Mayor prioridad
        // Prioridades van de Thread.MIN_PRIORITY (1) a Thread.MAX_PRIORITY (10)
        acasta.setPriority(Thread.MIN_PRIORITY);
        banff.setPriority(3);
        cadiz.setPriority(Thread.NORM_PRIORITY);
        deimos.setPriority(7);
        exped.setPriority(Thread.MAX_PRIORITY);

        acasta.start();
        banff.start();
        cadiz.start();
        deimos.start();
        exped.start();

        try {
            acasta.join();
            banff.join();
            cadiz.join();
            deimos.join();
            exped.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("No se puede rescatar");
        }

        System.out.println("=== OPERACIÓN DE RESCATE FINALIZADA ===");
    }
}
