import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Barco {

    private final List<Pasajero> pasajeros = new ArrayList<>();

    public Barco() {
        generacionPasajeros();
    }

    private void generacionPasajeros() {
        int id = 1;

        for (int i = 0; i < 100; i++) pasajeros.add(new Pasajero(id++, 1));
        for (int i = 0; i < 150; i++) pasajeros.add(new Pasajero(id++, 2));
        for (int i = 0; i < 80; i++) pasajeros.add(new Pasajero(id++, 3));
        for (int i = 0; i < 22; i++) pasajeros.add(new Pasajero(id++, 4));

        pasajeros.sort(Comparator.comparingInt(Pasajero::getPrioridad));
    }

    public List<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public boolean hayPasajeros() {
        return !pasajeros.isEmpty();
    }
}
