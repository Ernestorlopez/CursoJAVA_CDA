public class Constantes {
    // Campos estáticos e inmutables (final)
    public static final String ERROR_CONEXION = "Error al conectar con la base de datos";
    public static final String ERROR_DATO_NUMERICO = "El dato de entrada debe ser numérico";

    // Método estático de utilidad
    public static String formatearError(String codigo, String descripcion) {
        return "CDA-ERR-" + codigo + ": " + descripcion;
    }

    // Método main para ejecutar y probar la clase
    public static void main(String[] args) {
        // Uso directo sin instanciar con 'new'
        System.out.println(Constantes.ERROR_CONEXION);

        String errorCompleto = Constantes.formatearError("500", Constantes.ERROR_CONEXION);
        System.out.println(errorCompleto);
    }
}