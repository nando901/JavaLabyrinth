import java.util.ArrayList;

public class Jugador extends Personatge {

    private int puntuacio;
    private int atac;
    private ArrayList<Item> inventari;

    public Jugador(String nom) {
        super(nom, 100);
        this.puntuacio = 0;
        this.atac = 15;
        this.inventari = new ArrayList<>();
    }

    public int atacar() {
        return atac;
    }

    public void defensar(int dany) {
        // Defensar redueix el dany rebut a la meitat
        int danyReduit = dany / 2;
        rebreDany(danyReduit);
        System.out.println(nom + " es defensa i rep " + danyReduit + " de dany.");
    }

    public void usarObjecte(int index) {
        if (inventari.isEmpty()) {
            System.out.println("No tens cap objecte a l'inventari.");
            return;
        }
        if (index < 0 || index >= inventari.size()) {
            System.out.println("Selecció no vàlida.");
            return;
        }
        Item item = inventari.get(index);
        item.aplicarEfecte(this);
        inventari.remove(index);
    }

    public void curar(int quantitat) {
        vida = Math.min(vida + quantitat, vidaMax);
        System.out.println(nom + " recupera " + quantitat + " d'energia. Energia actual: " + vida + "/" + vidaMax);
    }

    public void afegirItem(Item item) {
        inventari.add(item);
        System.out.println("Has recollit: " + item.getNom() + " — " + item.getDescripcio());
    }

    public void guanyarPuntuacio(int punts) {
        puntuacio += punts;
        System.out.println("+" + punts + " Puntuacio! Total: " + puntuacio);
    }

    public void mostrarEstat() {
        System.out.println("--- " + nom + " ---");
        System.out.println("Energia: " + vida + "/" + vidaMax + "  |  Puntuacio: " + puntuacio);
        if (!inventari.isEmpty()) {
            System.out.println("Inventari:");
            for (int i = 0; i < inventari.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + inventari.get(i).getNom()
                        + " (+" + inventari.get(i).getEfecte() + " energia)");
            }
        } else {
            System.out.println("Inventari: buit");
        }
    }

    public int getPuntuacio() {
        return puntuacio;
    }

    public int getAtac() {
        return atac;
    }

    public ArrayList<Item> getInventari() {
        return inventari;
    }
}
