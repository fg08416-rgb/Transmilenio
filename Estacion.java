public class Estacion {
    private String nombre;
    private int tiempoEspera; 

    public Estacion(String nombre, int tiempoEspera) {
        this.nombre = nombre;
        this.tiempoEspera = tiempoEspera;
    }

    public String getNombre() {
        return nombre;
    }

    public int tiempoEspera() {
        return tiempoEspera;
    }
}