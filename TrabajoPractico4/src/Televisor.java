public class Televisor {
    // Atributo de clase (único para todas las instancias)
    public static int contadorId = 0;

    // Atributos de instancia (propios de cada televisor)
    public int id;
    public String marca;

    public Televisor(String marca) {
        contadorId++; // Incrementa cada vez que se crea una instancia
        this.id = contadorId; // Asigna el valor actual al ID de este televisor
        this.marca = marca;
    }

    // Método main para ejecutar y probar la clase
    public static void main(String[] args) {
        // Creamos la primera instancia
        Televisor tv1 = new Televisor("Samsung");
        System.out.println("Televisor 1 -> ID: " + tv1.id + ", Marca: " + tv1.marca);

        // Creamos la segunda instancia
        Televisor tv2 = new Televisor("LG");
        System.out.println("Televisor 2 -> ID: " + tv2.id + ", Marca: " + tv2.marca);

        // Creamos la tercera instancia
        Televisor tv3 = new Televisor("Sony");
        System.out.println("Televisor 3 -> ID: " + tv3.id + ", Marca: " + tv3.marca);

        // Verificamos el contador total de la clase
        System.out.println("Total de televisores creados: " + Televisor.contadorId);
    }
}
