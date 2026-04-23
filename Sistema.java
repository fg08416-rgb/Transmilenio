import java.util.*;

public class Sistema {

    private TreeMap<String, Troncal> troncales;
    private HashMap<String, Estacion> estaciones;
    private TreeMap<String, Ruta> rutas;

    public Sistema() {
        troncales = new TreeMap<>();
        estaciones = new HashMap<>();
        rutas = new TreeMap<>();
    }

    public void agregarTroncal(Troncal t) {
        troncales.put(t.getNombre(), t);
        for (Estacion e : t.getEstaciones()) {
            estaciones.put(e.getNombre(), e);
        }
    }

    public void agregarRuta(Ruta r) {
        rutas.put(r.getNombre(), r);
    }

    // ---- Servicio 1 ----

    public int tiempoEspera(String nombreEstacion) throws TransMilenioException {
        Estacion e = estaciones.get(nombreEstacion);
        if (e == null) throw new EstacionNoExisteException(nombreEstacion);
        return e.tiempoEspera();
    }

    // ---- Servicio 2 ----

    public List<String> nombresRutas() {
        return new ArrayList<>(rutas.keySet());
    }

    // ---- Servicio 3 ----

    public int numParadas(String nombreRuta, String est1, String est2)
            throws TransMilenioException {
        Ruta r = rutas.get(nombreRuta);
        if (r == null) throw new RutaNoExisteException(nombreRuta);
        if (!estaciones.containsKey(est1)) throw new EstacionNoExisteException(est1);
        if (!estaciones.containsKey(est2)) throw new EstacionNoExisteException(est2);
        return r.numParadas(est1, est2);
    }

    // ---- Servicio 4 ----

    public List<String> rutasSinTransbordo(String est1, String est2)
            throws TransMilenioException {
        if (!estaciones.containsKey(est1)) throw new EstacionNoExisteException(est1);
        if (!estaciones.containsKey(est2)) throw new EstacionNoExisteException(est2);

        List<String> nombres = new ArrayList<>();
        List<Integer> paradas = new ArrayList<>();

        for (Ruta r : rutas.values()) {
            if (r.contiene(est1) && r.contiene(est2)) {
                nombres.add(r.getNombre());
                paradas.add(r.numParadas(est1, est2));
            }
        }

        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < nombres.size(); i++) indices.add(i);
        indices.sort((a, b) -> {
            if (!paradas.get(a).equals(paradas.get(b))) {
                return paradas.get(a) - paradas.get(b);
            }
            return nombres.get(a).compareTo(nombres.get(b));
        });

        List<String> resultado = new ArrayList<>();
        for (int i : indices) resultado.add(nombres.get(i));
        return resultado;
    }

    // ---- Servicio 5 ----

    public List<String> rutasConTransbordo(String est1, String est2)
            throws TransMilenioException {
        if (!estaciones.containsKey(est1)) throw new EstacionNoExisteException(est1);
        if (!estaciones.containsKey(est2)) throw new EstacionNoExisteException(est2);

        List<String> nombres = new ArrayList<>();
        List<Integer> totalParadas = new ArrayList<>();

        for (Ruta r1 : rutas.values()) {
            if (!r1.contiene(est1)) continue;
            for (Ruta r2 : rutas.values()) {
                if (r1 == r2 || !r2.contiene(est2)) continue;
                Estacion transbordo = r1.estacionComun(r2);
                if (transbordo != null) {
                    int p1 = r1.numParadas(est1, transbordo.getNombre());
                    int p2 = r2.numParadas(transbordo.getNombre(), est2);
                    nombres.add(r1.getNombre() + " -> " + r2.getNombre());
                    totalParadas.add(p1 + p2);
                }
            }
        }

        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < nombres.size(); i++) indices.add(i);
        indices.sort((a, b) -> {
            if (!totalParadas.get(a).equals(totalParadas.get(b))) {
                return totalParadas.get(a) - totalParadas.get(b);
            }
            return nombres.get(a).compareTo(nombres.get(b));
        });

        List<String> resultado = new ArrayList<>();
        for (int i : indices) resultado.add(nombres.get(i));
        return resultado;
    }

    // ---- Servicio 6 ----

    public double tiempoRecorrido(List<String[]> plan) throws TransMilenioException {
        double tiempo = 0;
        for (int i = 0; i < plan.size() - 1; i++) {
            String estActual = plan.get(i)[0];
            String estSig    = plan.get(i + 1)[0];
            String nomRuta   = plan.get(i)[1];

            Ruta ruta = rutas.get(nomRuta);
            if (ruta == null) throw new RutaNoExisteException(nomRuta);

            Troncal troncal = troncalDeRuta(ruta);
            double distancia = troncal.distancia(estActual, estSig);
            tiempo += distancia / troncal.getVelocidad();

            if (i < plan.size() - 2) {
                String rutaSig = plan.get(i + 1)[1];
                if (rutaSig != null && !rutaSig.equals(nomRuta)) {
                    Estacion transbordo = estaciones.get(estSig);
                    if (transbordo == null) throw new EstacionNoExisteException(estSig);
                    tiempo += transbordo.tiempoEspera();
                }
            }
        }
        return tiempo;
    }

    private Troncal troncalDeRuta(Ruta ruta) throws TransMilenioException {
        for (Troncal t : troncales.values()) {
            for (Estacion e : t.getEstaciones()) {
                if (ruta.contiene(e.getNombre())) {
                    return t;
                }
            }
        }
        throw new TransMilenioException("No se encontro troncal para ruta: " + ruta.getNombre());
    }
}