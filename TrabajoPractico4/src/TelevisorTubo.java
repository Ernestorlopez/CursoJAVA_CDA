public class TelevisorTubo extends TelevisorBase {
    public TelevisorTubo(String modelo) {
        super(modelo);
    }

    @Override
    public void destruirTelevisor() {
        System.out.println("Extrayendo con cuidado el tubo de rayos catódicos para reciclaje seguro.");
    }
}