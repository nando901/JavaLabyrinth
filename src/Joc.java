import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Joc {

    private Scanner sc;
    private Jugador jugador;
    private ArrayList<Sala> sales;
    private int salaActual;
    private EstatJoc estatActual;

    public Joc() {
        this.sc = new Scanner(System.in);
        this.salaActual = 0;
        this.estatActual = EstatJoc.JUGANT;
        this.sales = new ArrayList<>();
    }

    // INICIALITZACIÓ

    public void iniciar() {
        mostrarBenvinguda();
        crearJugador();
        crearSales();
        buclePrincipal();
    }

    private void mostrarBenvinguda() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   JAVA LABYRINTH: THE VIBE QUEST     ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("\nBenvingut, desenvolupador.");
        System.out.println("La dungeon t'espera. Troba el Gran Debugger i escapa.\n");
    }

    private void crearJugador() {
        System.out.print("Introdueix el nom del teu heroi: ");
        String nom = sc.nextLine().trim();
        if (nom.isEmpty()) nom = "Heroi";
        jugador = new Jugador(nom);
        System.out.println("\nBenvingut, " + jugador.getNom() + "! Que comenci l'aventura.\n");
    }

    private void crearSales() {
        sales.add(new Sala(
                "Sala 1 — Entrada de la dungeon\nMurs de pedra humida t'envolten. Una torxa parpelleja al fons.",
                new Enemic("Bug Menor", "Error Runtime", 30, 8, 10),
                null
        ));
        sales.add(new Sala(
                "Sala 2 — Corredor dels Logs\nFileres de codi erroni cobreixen les parets. Fa olor de stack overflow.",
                null,
                new Item("Poció de Refactoring", 30, "Restaura 30 punts d'energia.")
        ));
        sales.add(new Sala(
                "Sala 3 — Cambra del NullPointer\nUna presència invisible es fa sentir. El codi crida a l'error.",
                new Enemic("NullPointerException", "Error Crític", 50, 14, 20),
                null
        ));
        sales.add(new Sala(
                "Sala 4 — Arxiu de les Dependències\nPaquets trencats per tot arreu. El terra tremola.",
                null,
                new Item("Kit de Debugging", 50, "Restaura 50 punts d'energia.")
        ));
        sales.add(new Sala(
                "Sala 5 — Cambra del Gran Debugger\nUna llum vermella il·lumina la sala. El cap final t'espera.",
                new GranDebugger(),
                null
        ));
    }

    // BUCLE PRINCIPAL
    public void buclePrincipal() {
        do {
            Sala sala = sales.get(salaActual);
            sala.descriureEntorn();
            sala.marcarVisitada();

            if (sala.teEnemic()) {
                combatLoop(sala.getEnemic());
            } else if (sala.teItem()) {
                recollirItem(sala);
            } else {
                System.out.println("\nNo hi ha res aquí. Continues endavant.");
            }

            verificarCondicions();

            if (estatActual == EstatJoc.JUGANT) {
                salaActual++;
            }

        } while (estatActual == EstatJoc.JUGANT && salaActual < sales.size());

        mostrarFinal();
    }

    // COMBAT
    private void combatLoop(Enemic enemic) {
        System.out.println("\n⚔  COMBAT INICIAT contra " + enemic.getNom() + "!\n");

        do {
            jugador.mostrarEstat();
            enemic.mostrarEstat();

            int accio = demanarAccioCombat();

            switch (accio) {
                case 1:
                    // Atacar
                    int danyJugador = jugador.atacar();
                    enemic.rebreDany(danyJugador);
                    System.out.println(jugador.getNom() + " ataca i fa " + danyJugador + " de dany a " + enemic.getNom() + ".");
                    break;
                case 2:
                    // Defensar
                    if (enemic.estaViu()) {
                        int danyEnemic = enemic.atacarJugador();
                        jugador.defensar(danyEnemic);
                    }
                    // En defensar l'enemic no ataca un segon cop
                    System.out.println(jugador.getNom() + " adopta posició defensiva.");
                    break;
                case 3:
                    // Usar objecte
                    usarObjecteEnCombat();
                    break;
            }

            // Torn de l'enemic (si segueix viu i no s'ha defensat)
            if (enemic.estaViu() && accio != 2) {
                int danyEnemic = enemic.atacarJugador();
                jugador.rebreDany(danyEnemic);
                System.out.println(enemic.getNom() + " contraataca i fa " + danyEnemic + " de dany.");
            }

            System.out.println();

        } while (enemic.estaViu() && jugador.estaViu());

        if (!enemic.estaViu()) {
            System.out.println("✔ Has derrotat " + enemic.getNom() + "!");
            jugador.guanyarCodiNet(enemic.getRecompensa());
        }
    }

    private int demanarAccioCombat() {
        System.out.println("\nQuè vols fer?");
        System.out.println("  1. Atacar");
        System.out.println("  2. Defensar");
        System.out.println("  3. Usar objecte");

        int opcio = -1;
        do {
            try {
                System.out.print("> ");
                opcio = sc.nextInt();
                sc.nextLine();
                if (opcio < 1 || opcio > 3) {
                    System.out.println("Opció no vàlida. Tria entre 1 i 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Introdueix un número vàlid.");
                sc.nextLine();
            }
        } while (opcio < 1 || opcio > 3);

        return opcio;
    }

    private void usarObjecteEnCombat() {
        ArrayList<Item> inventari = jugador.getInventari();
        if (inventari.isEmpty()) {
            System.out.println("No tens cap objecte. L'enemic aprofita per atacar!");
            int dany = sales.get(salaActual).getEnemic().atacarJugador();
            jugador.rebreDany(dany);
            System.out.println(sales.get(salaActual).getEnemic().getNom() + " ataca i fa " + dany + " de dany.");
            return;
        }

        System.out.println("Tria un objecte:");
        for (int i = 0; i < inventari.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + inventari.get(i).getNom());
        }

        int index = -1;
        do {
            try {
                System.out.print("> ");
                index = sc.nextInt() - 1;
                sc.nextLine();
                if (index < 0 || index >= inventari.size()) {
                    System.out.println("Selecció no vàlida.");
                    index = -1;
                }
            } catch (InputMismatchException e) {
                System.out.println("Introdueix un número vàlid.");
                sc.nextLine();
            }
        } while (index < 0);

        jugador.usarObjecte(index);
    }

    // ITEMS

    private void recollirItem(Sala sala) {
        Item item = sala.getItem();
        System.out.println("\nTrobes un objecte: " + item.getNom());
        System.out.println("Vols recollir-lo? (1. Sí / 2. No)");

        int opcio = -1;
        do {
            try {
                System.out.print("> ");
                opcio = sc.nextInt();
                sc.nextLine();
                if (opcio < 1 || opcio > 2) {
                    System.out.println("Opció no vàlida. Tria 1 o 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Introdueix un número vàlid.");
                sc.nextLine();
            }
        } while (opcio < 1 || opcio > 2);

        if (opcio == 1) {
            jugador.afegirItem(item);
            sala.recollirItem();
        } else {
            System.out.println("Deixes l'objecte al terra i continues.");
        }
    }

    // VERIFICACIÓ DE CONDICIONS
    public void verificarCondicions() {
        if (!jugador.estaViu()) {
            estatActual = EstatJoc.DERROTA;
        } else if (salaActual == sales.size() - 1 && !sales.get(salaActual).teEnemic()) {
            estatActual = EstatJoc.VICTORIA;
        }
    }

    // FINAL
    private void mostrarFinal() {
        System.out.println("\n╔══════════════════════════════════════╗");
        if (estatActual == EstatJoc.VICTORIA) {
            System.out.println("║           ✔  VICTÒRIA!               ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.println("\nHas derrotat el Gran Debugger i escapat de la dungeon.");
            System.out.println("Codi Net acumulat: " + jugador.getCodiNet() + " punts.");
        } else {
            System.out.println("║           ✘  DERROTA...              ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.println("\nL'energia de " + jugador.getNom() + " ha arribat a 0.");
            System.out.println("La dungeon t'ha vençut. Intenta-ho de nou.");
        }
        sc.close();
    }
}
