import java.util.Locale;

public class TrabajoPractico2 {

    public static void main(String[] args) {
        // Configuramos el formato para que use puntos/comas decimales estándar en consola
        Locale.setDefault(Locale.US);

        System.out.println("==================================================");
        System.out.println("      INICIO DE PRUEBAS DE ACEPTACIÓN             ");
        System.out.println("==================================================\n");

        //  Paso 1: Validación de Caja de Ahorro
        System.out.println("---  Paso 1: Validación de Caja de Ahorro ---");
        CajaAhorro cajaAhorro = new CajaAhorro("CA-001", "CLI-101", 5000.0);
        cajaAhorro.consultarEstado();

        System.out.println("Retirando $1,000...");
        cajaAhorro.retirar(1000.0);
        cajaAhorro.consultarEstado(); // Saldo esperado: $4,000

        System.out.println("Intentando retirar $6,000...");
        cajaAhorro.retirar(6000.0); // Debe mostrar alerta de fondos insuficientes
        cajaAhorro.consultarEstado();
        System.out.println();


        //  Paso 2: Validación de Cuenta Corriente (Sobregiro)
        System.out.println("---  Paso 2: Validación de Cuenta Corriente (Sobregiro) ---");
        CuentaCorriente cuentaCorriente = new CuentaCorriente("CC-001", "CLI-102", 1000.0, 2000.0);
        cuentaCorriente.consultarEstado();

        System.out.println("Retirando $2,500...");
        cuentaCorriente.retirar(2500.0);
        cuentaCorriente.consultarEstado(); // Saldo esperado: -$1,500

        System.out.println("Intentando retirar $1,000 adicionales...");
        cuentaCorriente.retirar(1000.0); // Debe rechazar por superar límite de sobregiro
        cuentaCorriente.consultarEstado();
        System.out.println();


        //  Paso 3: Validación de Plazo Fijo
        System.out.println("---  Paso 3: Validación de Plazo Fijo ---");
        PlazoFijo plazoFijo = new PlazoFijo("PF-001", "CLI-103", 10000.0, 30, 45.0);
        plazoFijo.consultarEstado();
        plazoFijo.simularVencimiento(); // Simular vencimiento y verificar intereses
        System.out.println();


        //  Paso 4: Validación de Tarjeta de Crédito
        System.out.println("---  Paso 4: Validación de Tarjeta de Crédito ---");
        TarjetaCredito tarjeta = new TarjetaCredito("TC-001", "CLI-104", 50000.0);
        tarjeta.consultarEstado(); // Deuda inicial en $0.0

        System.out.println("Realizando compra de $15,000...");
        tarjeta.pagarConTarjeta(15000.0);
        tarjeta.consultarEstado(); // Deuda esperada: $15,000

        System.out.println("Intentando compra de $40,000...");
        tarjeta.pagarConTarjeta(40000.0); // Debe ser rechazada

        System.out.println("Realizando pago a la tarjeta de $5,000...");
        tarjeta.pagarTarjeta(5000.0);
        tarjeta.consultarEstado(); // Deuda esperada: $10,000
        System.out.println();

        System.out.println("==================================================");
        System.out.println("      PRUEBAS COMPLETADAS CON ÉXITO               ");
        System.out.println("==================================================");
    }
}