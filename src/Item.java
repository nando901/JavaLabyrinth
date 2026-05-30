public class Item {

    private String nom;
    private int efecte;
    private String descripcio;

    public Item(String nom, int efecte, String descripcio) {
        this.nom = nom;
        this.efecte = efecte;
        this.descripcio = descripcio;
    }

    public void aplicarEfecte(Jugador jugador) {
        jugador.curar(efecte);
        System.out.println("Has usat \"" + nom + "\". " + descripcio);
    }

    public String getNom() {
        return nom;
    }

    public int getEfecte() {
        return efecte;
    }

    public String getDescripcio() {
        return descripcio;
    }
}
