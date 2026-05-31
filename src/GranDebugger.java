public class GranDebugger extends Enemic {

    private int faseActual;
    private static final int VIDA_PER_FASE = 50;

    public GranDebugger() {
        super("Gran Debugger", "Cap Final", 100, 18, 100);
        this.faseActual = 1;
    }

    // El Gran Debugger canvia de fase en perdre cada 50 punts de vida
    public void canviarFase() {
        int novaFase = ((vidaMax - vida) / VIDA_PER_FASE) + 1;
        if (novaFase > faseActual) {
            faseActual = novaFase;
            danyBase += 8;
            System.out.println("\n[!] El Gran Debugger entra a la FASE " + faseActual + "!");
            System.out.println("  El seu atac augmenta a " + danyBase + " de dany base.\n");
        }
    }

    @Override
    public int atacarJugador() {
        // Comprova si ha de canviar de fase abans d'atacar
        canviarFase();
        return super.atacarJugador();
    }

    @Override
    public void mostrarEstat() {
        System.out.println("[Cap Final - Fase " + faseActual + "] " + nom
                + " - Vida: " + vida + "/" + vidaMax);
    }

    public int getFaseActual() {
        return faseActual;
    }
}
