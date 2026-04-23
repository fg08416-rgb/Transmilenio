import java.util.ArrayList;
import java.util.List;

public class Ruta {
    private String nombre;
    private List<Estacion> paradas;

    public Ruta(String nombre) {
        this.nombre = nombre;
        this.paradas = new ArrayList<>();
    }

    public void agregarParada(Estacion estacion) {
        paradas.add(estacion);
    }

    public String getNombre() {
        return nombre;
    }

    public boolean contiene(String nombreEstacion) {
        for (Estacion e : paradas) {
            if (e.getNombre().equals(nombreEstacion)) {
                return true;
            }
        }
        return false;
    }

    public int numParadas(String est1, String est2) throws EstacionNoExisteException {
        int indice1 = -1;
        int indice2 = -1;

        for (int i = 0; i < paradas.size(); i++) {
            if (paradas.get(i).getNombre().equals(est1)) indice1 = i;
            if (paradas.get(i).getNombre().equals(est2)) indice2 = i;
        }

        if (indice1 == -1) throw new EstacionNoExisteException(est1);
        if (indice2 == -1) throw new EstacionNoExisteException(est2);

        return Math.abs(indice2 - indice1);
    }

    public Estacion estacionComun(Ruta otraRuta) {
        for (Estacion miEstacion : paradas) {
            if (otraRuta.contiene(miEstacion.getNombre())) {
                return miEstacion;
            }
        }
        return null;
    }
}