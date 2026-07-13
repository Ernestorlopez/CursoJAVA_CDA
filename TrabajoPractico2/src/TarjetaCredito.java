public  class TarjetaCredito extends Producto {
    private double limiteCredito;

    public TarjetaCredito(String numeroProducto, String clienteId, double limiteCredito) {
        // El saldo heredado representa la deuda acumulada, por ende inicia en 0.0
        super(numeroProducto, clienteId, 0.0);
        this.limiteCredito = limiteCredito;
    }

    public void pagarConTarjeta(double monto) {
        if (monto <= 0) {
            System.out.println("[Error] El monto de compra debe ser positivo.");
            return;
        }

        // El saldo es la deuda actual. Comprobamos si la deuda actual + la nueva compra supera el límite.
        if (this.saldo + monto <= this.limiteCredito) {
            this.saldo += monto;
            System.out.printf("Compra aprobada por $%.2f. Deuda acumulada: $%.2f%n", monto, this.saldo);
        } else {
            System.out.printf("[Rechazado] Crédito insuficiente para compra de $%.2f. " +
                            "Deuda actual: $%.2f | Límite de Crédito: $%.2f%n",
                    monto, this.saldo, this.limiteCredito);
        }
    }

    public void pagarTarjeta(double monto) {
        if (monto > 0) {
            this.saldo -= monto;
            System.out.printf("Pago de tarjeta procesado por $%.2f. Deuda restante: $%.2f%n", monto, this.saldo);
        } else {
            System.out.println("[Error] El monto de pago debe ser positivo.");
        }
    }

    // Sobrescrito opcional para dar contexto de que el saldo es una "Deuda acumulada"
    @Override
    public void consultarEstado() {
        System.out.printf("[Estado Tarjeta] Nro: %s | Cliente: %s | Deuda actual: $%.2f | Límite disponible: $%.2f%n",
                numeroProducto, clienteId, saldo, (limiteCredito - saldo));
    }
}
