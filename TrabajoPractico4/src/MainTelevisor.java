public class MainTelevisor {
    public static void main(String[] args) {
        TelevisorBase tv1 = new TelevisorTubo("Sony Trinitron 29\"");
        TelevisorBase tv2 = new TelevisorLed("Samsung Crystal UHD 55\"");

        System.out.println("Procesando televisor: " + tv1.getModelo());
        tv1.destruirTelevisor();

        System.out.println("---------------------------------------------------");

        System.out.println("Procesando televisor: " + tv2.getModelo());
        tv2.destruirTelevisor();
    }
}