import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrabajoPractico1 {

    public static void main(String[] args) {
        // Datos de prueba
        List<String> nombres = new ArrayList<>();
        nombres.add("Juan Melo");
        nombres.add("María Lopez");
        nombres.add("Carlos Gómez");
        nombres.add("Ana Melo");
        nombres.add("Pedro Picapiedra");

        System.out.println("--- 1. Imprimir listado de nombres ---");
        imprimirNombres(nombres);
        System.out.println();

        System.out.println("--- 2. Buscar coincidencia exacta ---");
        String buscarExacto = "María Lopez";
        boolean contieneExacto = contieneNombre(nombres, buscarExacto);
        System.out.println("¿La lista contiene exactamente '" + buscarExacto + "'?: " + contieneExacto);
        System.out.println("¿La lista contiene exactamente 'Juan'?: " + contieneNombre(nombres, "Juan"));
        System.out.println();

        System.out.println("--- 3. Filtrar por subcadena ---");
        String subcadena = "Melo";
        List<String> filtrados = filtrarPorSubcadena(nombres, subcadena);
        System.out.println("Elementos que contienen '" + subcadena + "': " + filtrados);
        System.out.println();

        System.out.println("--- 4. Trabajar con Maps ---");
        // Generar el Map
        Map<Long, String> miMap = generarMap();
        System.out.println("Map generado: " + miMap);

        // Verificar si existe una clave
        Long claveABuscar = 102L;
        boolean existeClave = existeClaveEnMap(miMap, claveABuscar);
        System.out.println("¿Existe la clave " + claveABuscar + "?: " + existeClave);
        System.out.println("¿Existe la clave 999?: " + existeClaveEnMap(miMap, 999L));

        // Obtener el valor de una clave
        String valorClave = obtenerValorPorClave(miMap, claveABuscar);
        System.out.println("El valor para la clave " + claveABuscar + " es: \"" + valorClave + "\"");
    }

    /**
     * Punto 1: Imprime un listado de nombres (Lista de String)
     */
    public static void imprimirNombres(List<String> lista) {
        for (String nombre : lista) {
            System.out.println("- " + nombre);
        }
    }

    /**
     * Punto 2: Retorna true si la lista contiene exactamente el String buscado
     */
    public static boolean contieneNombre(List<String> lista, String buscar) {
        return lista.contains(buscar);
    }

    /**
     * Punto 3: Retorna una nueva lista con los elementos que contienen el String (subcadena)
     */
    public static List<String> filtrarPorSubcadena(List<String> lista, String subcadena) {
        List<String> resultado = new ArrayList<>();
        for (String nombre : lista) {
            if (nombre.contains(subcadena)) {
                resultado.add(nombre);
            }
        }
        return resultado;
    }

    /**
     * Punto 4.a: Genera y retorna un Map (clave Long y valor String)
     */
    public static Map<Long, String> generarMap() {
        Map<Long, String> mapa = new HashMap<>();
        mapa.put(101L, "Desarrollo Frontend");
        mapa.put(102L, "Desarrollo Backend");
        mapa.put(103L, "Data Science");
        return mapa;
    }

    /**
     * Punto 4.b: Retorna si una clave existe o no en el Map
     */
    public static boolean existeClaveEnMap(Map<Long, String> mapa, Long clave) {
        return mapa.containsKey(clave);
    }

    /**
     * Punto 4.c: Retorna el valor de una clave en el Map
     */
    public static String obtenerValorPorClave(Map<Long, String> mapa, Long clave) {
        return mapa.get(clave);
    }
}