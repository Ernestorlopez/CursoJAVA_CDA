public class PlazoFijo extends Producto {
    private int plazoDias;
    private double tasaInteres; // Guardada como porcentaje (ej: 45.0 para 45%)

    public PlazoFijo(String numeroProducto, String clienteId, double saldoInicial, int plazoDias, double tasaInteres) {
        super(numeroProducto, clienteId, saldoInicial);
        this.plazoDias = plazoDias;
        this.tasaInteres = tasaInteres;
    }

    public void simularVencimiento() {
        double ganancia = this.saldo * (this.tasaInteres / 100.0) * (this.plazoDias / 365.0);
        double totalAlVencer = this.saldo + ganancia;
        System.out.println("--- Simulación de Vencimiento de Plazo Fijo ---");
        System.out.printf("  Capital Inicial: $%.2f%n", this.saldo);
        System.out.printf("  Plazo: %d días | Tasa de Interés: %.2f%%%n", plazoDias, tasaInteres);
        System.out.printf("  Intereses ganados: $%.2f%n", ganancia);
        System.out.printf("  Monto total a recibir al vencimiento: $%.2f%n", totalAlVencer);
    }
}
