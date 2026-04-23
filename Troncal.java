// Archivo: Troncal.java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Troncal {
    private String nombre;
    private double velocidad;
    private List<Estacion> estaciones;
    private Map<String, Double> posicionesKm; 

    public Troncal(String nombre, double velocidad) {
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.estaciones = new ArrayList<>();
        this.posicionesKm = new HashMap<>();
    }

    
    public void agregarEstacion(Estacion estacion, double posicionEnKm) {
        estaciones.add(estacion);
        posicionesKm.put(estacion.getNombre(), posicionEnKm);
    }

    public String getNombre() {
        return nombre;
    }

    public List<Estacion> getEstaciones() {
        return estaciones;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public double distancia(String est1, String est2) throws EstacionNoExisteException {
        if (!posicionesKm.containsKey(est1)) throw new EstacionNoExisteException(est1);
        if (!posicionesKm.containsKey(est2)) throw new EstacionNoExisteException(est2);

        return Math.abs(posicionesKm.get(est1) - posicionesKm.get(est2));
    }
}