//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Barco barco = new Barco();

        Rescate rescate = new Rescate(barco.getPasajeros());
        Thread rescateHilo = new Thread(rescate);
        rescateHilo.start();

        try {
            rescateHilo.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Main: Programa terminado.");
    }
}