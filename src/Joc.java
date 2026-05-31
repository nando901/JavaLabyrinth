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

    // INICIALITZACIO
    public void iniciar() {
        mostrarBenvinguda();
        crearJugador();
        crearSales();
        buclePrincipal();
    }

    private void mostrarBenvinguda() {
        System.out.println("+======================================+");
        System.out.println("|   JAVA LABYRINTH: THE VIBE QUEST     |");
        System.out.println("+======================================+");
        System.out.println("\nBenvingut, desenvolupador.");
        System.out.println("La dungeon t'espera. Troba el Gran Debugger i escapa.\n");
    }

    private void crearJugador() {
        String nom = "";
        do {
            System.out.print("Introdueix el nom del teu heroi: ");
            nom = sc.nextLine().trim();
            if (nom.isEmpty()) {
                System.out.println("[!] El nom no pot estar buit. Torna-ho a intentar.");
            }
        } while (nom.isEmpty());
        jugador = new Jugador(nom);
        System.out.println("\nBenvingut, " + jugador.getNom() + "! Que comenci l'aventura.\n");
    }

    private void crearSales() {
        // Sales comunes inicials
        sales.add(new Sala(
                "Sala 1 - Entrada del Dungeon\nMurs de pedra humida t'envolten. Una torxa parpelleja al fons.",
                new Enemic("Bug Menor", "Error Runtime", 30, 8, 10),
                null
        ));
        sales.add(new Sala(
                "Sala 2 - Armeria Abandonada\nUna espasa vella brilla entre la foscor. Alguna cosa util t'espera.",
                null,
                new Item("Espasa de Codi", 5, "Augmenta l'atac del heroi en 5 punts.")
        ));
        sales.add(new Sala(
                "Sala 3 - Corredor dels Logs\nFileres de codi erroni cobreixen les parets. Fa olor de stack overflow.",
                null,
                new Item("Pocio de Refactoring", 30, "Restaura 30 punts d'energia.")
        ));

        // Bifurcacio: el jugador tria el cami
        int cami = demanarBifurcacio();

        if (cami == 1) {
            // Cami A: mes risc, mes recompensa
            sales.add(new Sala(
                    "Sala 4A - Cambra Fosca\nFoscor absoluta. Sents una respiracio pesada al teu costat.",
                    new Enemic("StackOverflow", "Error Fatal", 70, 18, 35),
                    null
            ));
            sales.add(new Sala(
                    "Sala 5A - Sala de Descans\nUna habitacio tranquil.la. Trobes provisions abans del combat final.",
                    null,
                    new Item("Kit de Debugging", 50, "Restaura 50 punts d'energia.")
            ));
        } else {
            // Cami B: mes segur, menys recompensa
            sales.add(new Sala(
                    "Sala 4B - Corredor de les Excepcions\nEl terra vibra. Un error critic apareix al teu pas.",
                    new Enemic("NullPointerException", "Error Critic", 40, 10, 15),
                    null
            ));
            sales.add(new Sala(
                    "Sala 4B-2 - Sala de Descans\nUna habitacio tranquil.la. Trobes alguna cosa util abans del combat final.",
                    null,
                    new Item("Pocio Menor", 20, "Restaura 20 punts d'energia.")
            ));
        }

        // Sala final comuna
        sales.add(new Sala(
                "Sala Final - Cambra del Gran Debugger\nUna llum vermella illumina la sala. El cap final t'espera.",
                new GranDebugger(),
                null
        ));
    }

    private int demanarBifurcacio() {
        System.out.println("\n========================================");
        System.out.println("Davant teu hi ha dues portes.");
        System.out.println("  1. [CAMI A] Porta fosca - Sembla perillosa. Potser hi ha mes recompensa.");
        System.out.println("  2. [CAMI B] Porta illuminada - Sembla segura. Probablement hi ha recursos.");
        System.out.println("========================================");
        System.out.println("Quin cami tries?");

        int opcio = -1;
        do {
            try {
                System.out.print("> ");
                opcio = sc.nextInt();
                sc.nextLine();
                if (opcio < 1 || opcio > 2) {
                    System.out.println("Opcio no valida. Tria 1 o 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Introdueix un numero valid.");
                sc.nextLine();
            }
        } while (opcio < 1 || opcio > 2);

        return opcio;
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
                // Si es una sala de descans, oferim usar l'inventari
                if (esSalaDescans(sala)) {
                    ofertarUsarItems();
                }
            } else {
                System.out.println("\nNo hi ha res aqui. Continues endavant.");
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
        System.out.println("\n[COMBAT] COMBAT INICIAT contra " + enemic.getNom() + "!\n");

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
                    System.out.println(jugador.getNom() + " adopta posicio defensiva.");
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
            System.out.println("[OK] Has derrotat " + enemic.getNom() + "!");
            jugador.guanyarPuntuacio(enemic.getRecompensa());
        }
    }

    private int demanarAccioCombat() {
        System.out.println("\nQue vols fer?");
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
                    System.out.println("Opcio no valida. Tria entre 1 i 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Introdueix un numero valid.");
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
                    System.out.println("Seleccio no valida.");
                    index = -1;
                }
            } catch (InputMismatchException e) {
                System.out.println("Introdueix un numero valid.");
                sc.nextLine();
            }
        } while (index < 0);

        jugador.usarObjecte(index);
    }

    // ITEMS
    private void recollirItem(Sala sala) {
        Item item = sala.getItem();
        System.out.println("\nTrobes: " + item.getNom());

        // L'Espasa de Codi s'equipa automaticament
        if (item.getNom().equals("Espasa de Codi")) {
            System.out.println("Vols equipar-la? (1. Si / 2. No)");
        } else {
            System.out.println("Vols recollir-lo? (1. Si / 2. No)");
        }

        int opcio = -1;
        do {
            try {
                System.out.print("> ");
                opcio = sc.nextInt();
                sc.nextLine();
                if (opcio < 1 || opcio > 2) {
                    System.out.println("Opcio no valida. Tria 1 o 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Introdueix un numero valid.");
                sc.nextLine();
            }
        } while (opcio < 1 || opcio > 2);

        if (opcio == 1) {
            if (item.getNom().equals("Espasa de Codi")) {
                // L'espasa augmenta l'atac directament, no va a l'inventari
                jugador.boostAtac(item.getEfecte());
            } else {
                jugador.afegirItem(item);
            }
            sala.recollirItem();
        } else {
            System.out.println("Deixes l'objecte al terra i continues.");
        }
    }

    // SALA DE DESCANS
    private boolean esSalaDescans(Sala sala) {
        return sala.getDescripcio().contains("Sala de Descans");
    }

    private void ofertarUsarItems() {
        if (jugador.getInventari().isEmpty()) {
            System.out.println("\nNo portes cap item a l'inventari. Continues cap al combat final.");
            return;
        }

        System.out.println("\n--- SALA DE DESCANS ---");
        System.out.println("Pots usar els teus items per recuperar-te abans del combat final.");

        boolean continuar = true;
        while (continuar && !jugador.getInventari().isEmpty()) {
            jugador.mostrarEstat();
            System.out.println("\nVols usar algun item? (1. Si / 2. No, continuar)");

            int opcio = -1;
            do {
                try {
                    System.out.print("> ");
                    opcio = sc.nextInt();
                    sc.nextLine();
                    if (opcio < 1 || opcio > 2) {
                        System.out.println("Opcio no valida. Tria 1 o 2.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Introdueix un numero valid.");
                    sc.nextLine();
                }
            } while (opcio < 1 || opcio > 2);

            if (opcio == 1) {
                ArrayList<Item> inventari = jugador.getInventari();
                System.out.println("Quin item vols usar?");
                for (int i = 0; i < inventari.size(); i++) {
                    System.out.println("  " + (i + 1) + ". " + inventari.get(i).getNom()
                            + " (+" + inventari.get(i).getEfecte() + " energia)");
                }

                int index = -1;
                do {
                    try {
                        System.out.print("> ");
                        index = sc.nextInt() - 1;
                        sc.nextLine();
                        if (index < 0 || index >= inventari.size()) {
                            System.out.println("Seleccio no valida.");
                            index = -1;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Introdueix un numero valid.");
                        sc.nextLine();
                    }
                } while (index < 0);

                jugador.usarObjecte(index);
            } else {
                continuar = false;
            }
        }

        if (jugador.getInventari().isEmpty()) {
            System.out.println("\nNo tens mes items. Preparat per al combat final!");
        } else {
            System.out.println("\nD'acord. Guardes els items restants i continues endavant.");
        }
    }

    // VERIFICACIO DE CONDICIONS
    public void verificarCondicions() {
        if (!jugador.estaViu()) {
            estatActual = EstatJoc.DERROTA;
        } else if (salaActual == sales.size() - 1 && !sales.get(salaActual).teEnemic()) {
            estatActual = EstatJoc.VICTORIA;
        }
    }

    // FINAL
    private void mostrarFinal() {
        System.out.println("\n+======================================+");
        if (estatActual == EstatJoc.VICTORIA) {
            System.out.println("|              VICTORIA!               |");
            System.out.println("+======================================+");
            System.out.println("\nHas derrotat el Gran Debugger i escapat del dungeon.");
            System.out.println("Puntuacio acumulada: " + jugador.getPuntuacio() + " punts.");
        } else {
            System.out.println("|              DERROTA...              |");
            System.out.println("+======================================+");
            System.out.println("\nL'energia de " + jugador.getNom() + " ha arribat a 0.");
            System.out.println("El dungeon t'ha venut. Intenta-ho de nou.");
        }

        // Opcio de tornar a jugar
        System.out.println("\nVols tornar a jugar? (1. Si / 2. No)");
        int opcio = -1;
        do {
            try {
                System.out.print("> ");
                opcio = sc.nextInt();
                sc.nextLine();
                if (opcio < 1 || opcio > 2) {
                    System.out.println("Opcio no valida. Tria 1 o 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Introdueix un numero valid.");
                sc.nextLine();
            }
        } while (opcio < 1 || opcio > 2);

        if (opcio == 1) {
            salaActual = 0;
            estatActual = EstatJoc.JUGANT;
            sales.clear();
            crearJugador();
            crearSales();
            buclePrincipal();
        } else {
            System.out.println("\nGracies per jugar. Fins aviat!");
            sc.close();
        }
    }
}
