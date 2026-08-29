
interface ProductoRepository {
    void guardarProducto(String producto);
    String obtenerProducto(long id);
}


class SQLProductoRepository implements ProductoRepository {
    @Override
    public void guardarProducto(String producto) {
        System.out.println("Insertando '" + producto + "' en la base de datos SQL Server...");
    }

    @Override
    public String obtenerProducto(long id) {
        return "Producto SQL con ID " + id;
    }
}


class FileProductoRepository implements ProductoRepository {
    @Override
    public void guardarProducto(String producto) {
        System.out.println("Escribiendo '" + producto + "' al final del archivo local productos.txt...");
    }

    @Override
    public String obtenerProducto(long id) {
        return "Producto desde Archivo con ID " + id;
    }
}


public class MainRepositorios {
    public static void main(String[] args) {
        ProductoRepository repoSql = new SQLProductoRepository();
        ProductoRepository repoFile = new FileProductoRepository();

        System.out.println("--- Probando Repositorio SQL ---");
        repoSql.guardarProducto("Laptop Gamer");
        System.out.println(repoSql.obtenerProducto(1L));

        System.out.println("\n--- Probando Repositorio de Archivo ---");
        repoFile.guardarProducto("Teclado Mecánico");
        System.out.println(repoFile.obtenerProducto(2L));
    }
}