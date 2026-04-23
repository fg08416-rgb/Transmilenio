# TransMilenio
## Autores: Francisco Gomez - Oscar Daniel Lopez - Daniel Mosquera
## Colecciones utilizadas

| Colección | Usado en | Razón |
|---|---|---|
| **TreeMap<String, Ruta>** | **Sistema.rutas** | Mantiene las rutas ordenadas alfabéticamente por nombre |
| **TreeMap<String, Troncal>** | **Sistema.troncales** | Orden alfabético por nombre de troncal |
| **HashMap<String, Estacion>** | **Sistema.estaciones** | Acceso en O(1) por nombre de estación |
| **LinkedList<Estacion>** | **Troncal, Ruta** | Secuencia ordenada, recorrido lineal sin acceso aleatorio |
| **LinkedList<Tramo>** | **Troncal** | Los tramos siguen el orden de la troncal |

## Servicios

### Servicio 1 — Tiempo de espera de una estación

**sistema.tiempoEspera("Portal Norte");**

Dado el nombre de la estación, retorna su tiempo de espera en minutos según el nivel de ocupación.

### Servicio 2 — Rutas ordenadas alfabéticamente

**sistema.nombresRutas();**

Retorna la lista de nombres de todas las rutas del sistema en orden alfabético.

### Servicio 3 — Número de paradas entre dos estaciones en una ruta

**sistema.numParadas("B18", "Portal Norte", "Calle 72");**

Dado el nombre de la ruta y los nombres de las dos estaciones, retorna el número de paradas entre ellas.

### Servicio 4 — Rutas sin transbordo entre dos estaciones

**sistema.rutasSinTransbordo("Portal Norte", "Calle 72");**

Retorna las rutas que conectan directamente las dos estaciones, ordenadas por número de paradas y luego alfabéticamente.

### Servicio 5 — Rutas con transbordo entre dos estaciones

**sistema.rutasConTransbordo("Portal Norte", "Biblioteca El Tintal");**

Retorna los pares de rutas que conectan las dos estaciones con un transbordo, ordenados por total de paradas y luego por nombre.

### Servicio 6 — Tiempo de recorrido de un plan de ruta
El plan es una lista de pares **{nombreEstacion, nombreRuta}**. El último par tiene **null** como ruta. El tiempo incluye el desplazamiento en cada tramo más el tiempo de espera en cada estación de transbordo.
