import java.util.Collections;
import java.util.List;

public class Rescate implements Runnable {

    private final List<Pasajero> prioridades;

    public Rescate(List<Pasajero> pasajerosOrdenadosPorPrioridad) {

        this.prioridades = Collections.synchronizedList(pasajerosOrdenadosPorPrioridad);
    }

    @Override
    public void run() {
        Balsa acasta = new Balsa("Acasta", 1, 500, prioridades);        // 0.5 s -> 500 ms
        Balsa banff  = new Balsa("Banff", 2, 1000, prioridades);        // 1 s -> 1000 ms
        Balsa cadiz  = new Balsa("Cadiz", 3, 2000, prioridades);        // 2 s -> 2000 ms
        Balsa deimos = new Balsa("Deimos", 4, 4000, prioridades);       // 4 s -> 4000 ms
        Balsa exped  = new Balsa("Expedición", 5, 8000, prioridades);   // 8 s -> 8000 ms

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
