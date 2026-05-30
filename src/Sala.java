public class Sala {

    private String descripcio;
    private Enemic enemic;
    private Item item;
    private boolean visitada;

    public Sala(String descripcio, Enemic enemic, Item item) {
        this.descripcio = descripcio;
        this.enemic = enemic;
        this.item = item;
        this.visitada = false;
    }

    public void descriureEntorn() {
        System.out.println("\n========================================");
        System.out.println(descripcio);
        System.out.println("========================================");

        if (teEnemic()) {
            System.out.println("[ENEMIC] Hi ha un enemic: " + enemic.getNom() + " [" + enemic.getTipus() + "]");
        } else if (teItem()) {
            System.out.println("[ITEM]   Hi ha un objecte al terra: " + item.getNom());
        } else {
            System.out.println("[BUIT]   La sala es silenciosa. No hi ha res.");
        }
    }

    public void marcarVisitada() {
        this.visitada = true;
    }

    public boolean teEnemic() {
        return enemic != null && enemic.estaViu();
    }

    public boolean teItem() {
        return item != null;
    }

    public void recollirItem() {
        item = null;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public Enemic getEnemic() {
        return enemic;
    }

    public Item getItem() {
        return item;
    }

    public boolean isVisitada() {
        return visitada;
    }
}
