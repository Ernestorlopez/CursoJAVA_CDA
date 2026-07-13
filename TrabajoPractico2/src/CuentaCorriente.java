public  class CuentaCorriente extends CuentaBancaria {
    private double limiteSobregiro;

    public CuentaCorriente(String numeroProducto, String clienteId, double saldoInicial, double limiteSobregiro) {
        super(numeroProducto, clienteId, saldoInicial);
        this.limiteSobregiro = limiteSobregiro;
    }

    @Override
    public void retirar(double monto) {
        if (monto <= 0) {
            System.out.println("[Error] El monto a retirar debe ser positivo.");
            return;
        }

        // Validamos que el nuevo saldo resultante no supere negativamente el límite de sobregiro
        // saldo - monto >= -limiteSobregiro
        if (this.saldo - monto >= -this.limiteSobregiro) {
            this.saldo -= monto;
            System.out.printf("Retiro exitoso (con sobregiro) de $%.2f. Nuevo saldo: $%.2f%n", monto, this.saldo);
        } else {
            System.out.printf("[Rechazado] Operación supera el límite de sobregiro autorizado. " +
                            "Intento de retiro: $%.2f | Saldo: $%.2f | Límite Sobregiro: $%.2f%n",
                    monto, this.saldo, this.limiteSobregiro);
        }
    }
}
