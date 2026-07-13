public class Producto {
    protected String numeroProducto;
    protected String clienteId;
    protected double saldo;

    public Producto(String numeroProducto, String clienteId, double saldoInicial) {
        this.numeroProducto = numeroProducto;
        this.clienteId = clienteId;
        this.saldo = saldoInicial;
    }

    public void consultarEstado() {
        System.out.printf("[Estado] Producto Nro: %s | Cliente: %s | Balance/Saldo: $%.2f%n",
                numeroProducto, clienteId, saldo);
    }
}
