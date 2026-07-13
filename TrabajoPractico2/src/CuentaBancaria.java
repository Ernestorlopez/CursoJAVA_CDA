public  class CuentaBancaria extends Producto {

    public CuentaBancaria(String numeroProducto, String clienteId, double saldoInicial) {
        super(numeroProducto, clienteId, saldoInicial);
    }

    public void depositar(double monto) {
        if (monto > 0) {
            this.saldo += monto;
            System.out.printf("Depósito exitoso de $%.2f. Nuevo saldo: $%.2f%n", monto, this.saldo);
        } else {
            System.out.println("[Error] El monto a depositar debe ser positivo.");
        }
    }

    public void retirar(double monto) {
        if (monto <= 0) {
            System.out.println("[Error] El monto a retirar debe ser positivo.");
            return;
        }

        if (this.saldo >= monto) {
            this.saldo -= monto;
            System.out.printf("Retiro exitoso de $%.2f. Nuevo saldo: $%.2f%n", monto, this.saldo);
        } else {
            System.out.printf("[Alerta] Fondos insuficientes. Intento de retiro: $%.2f | Saldo disponible: $%.2f%n",
                    monto, this.saldo);
        }
    }
}
