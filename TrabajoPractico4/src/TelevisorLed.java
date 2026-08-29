public class TelevisorLed extends TelevisorBase {
    public TelevisorLed(String modelo) {
        super(modelo);
    }

    @Override
    public void destruirTelevisor() {
        System.out.println("Desmontando panel LED y separando componentes electrónicos finos.");
    }
}