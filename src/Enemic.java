import java.util.Random;

public class Enemic extends Personatge {

    protected String tipus;
    protected int danyBase;
    protected int recompensa;
    private Random random;

    public Enemic(String nom, String tipus, int vida, int danyBase, int recompensa) {
        super(nom, vida);
        this.tipus = tipus;
        this.danyBase = danyBase;
        this.recompensa = recompensa;
        this.random = new Random();
    }

    public int atacarJugador() {
        // Variacio aleatoria del dany entre el 80% i el 120% del dany base
        int variacio = random.nextInt(5) - 2;
        int dany = Math.max(1, danyBase + variacio);
        return dany;
    }

    public void mostrarEstat() {
        System.out.println("[" + tipus + "] " + nom
                + " - Vida: " + vida + "/" + vidaMax + " | Atac: ~" + danyBase);
    }

    public String getTipus() {
        return tipus;
    }

    public int getDanyBase() {
        return danyBase;
    }

    public int getRecompensa() {
        return recompensa;
    }
}
