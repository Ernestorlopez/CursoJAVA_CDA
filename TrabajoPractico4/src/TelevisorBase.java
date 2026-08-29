public abstract class TelevisorBase {
    private String modelo;

    public TelevisorBase(String modelo) {
        this.modelo = modelo;
    }

    public String getModelo() {
        return modelo;
    }

    public abstract void destruirTelevisor();
}