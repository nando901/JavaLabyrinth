public abstract class Personatge {

    protected String nom;
    protected int vida;
    protected int vidaMax;

    public Personatge(String nom, int vida) {
        this.nom = nom;
        this.vida = vida;
        this.vidaMax = vida;
    }

    public void rebreDany(int dany) {
        this.vida -= dany;
        if (this.vida < 0) this.vida = 0;
    }

    public boolean estaViu() {
        return this.vida > 0;
    }

    public String getNom() {
        return nom;
    }

    public int getVida() {
        return vida;
    }

    public int getVidaMax() {
        return vidaMax;
    }
}
