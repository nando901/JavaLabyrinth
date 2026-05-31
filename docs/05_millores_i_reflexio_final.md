# 5. Millores i Reflexió Final
**Projecte:** Java Labyrinth: The Vibe Quest
**Alumne:** Nando
**Mòdul:** Entorns de Desenvolupament — DAM1
**Data:** Maig 2026

---

## 5.1 Millores identificades

| Codi | Millora detectada | Motiu | Prioritat |
|---|---|---|---|
| M-01 | Validar que el nom del jugador no quedi buit | El jugador podia prémer Enter sense escriure res i el joc assignava "Heroi" silenciosament sense avisar | Alta |
| M-02 | Renombrar `codiNet` per `puntuacio` | El nom `codiNet` no era intuïtiu per a un jugador que no coneix el context tècnic del projecte | Mitjana |
| M-03 | Afegir opció de tornar a jugar al final de la partida | El joc es tancava directament en acabar, sense donar la possibilitat de jugar una altra partida sense reiniciar el programa | Mitjana |
| M-04 | Afegir bifurcació de camins després de la Sala 2 | El recorregut era completament lineal, sense cap decisió real per part del jugador. Afegir una bifurcació apropa el joc al concepte de dungeon crawler real | Alta |

---

## 5.2 Millores aplicades

| Millora | Fitxer modificat | Evidència |
|---|---|---|
| M-01 — Validació del nom del jugador | `Joc.java` | Vegeu exemple abans/després |
| M-02 — Renombrar `codiNet` per `puntuacio` | `Jugador.java`, `Joc.java` | Vegeu exemple abans/després |
| M-03 — Opció de tornar a jugar | `Joc.java` | Vegeu exemple abans/després |
| M-04 — Bifurcació de camins | `Joc.java` | Vegeu exemple abans/després |

---

## 5.3 Exemples abans/després

### M-01 — Validació del nom del jugador

**Abans:** el nom podia quedar buit sense cap avís al jugador.

```java
// Abans
private void crearJugador() {
    System.out.print("Introdueix el nom del teu heroi: ");
    String nom = sc.nextLine().trim();
    if (nom.isEmpty()) nom = "Heroi";
    jugador = new Jugador(nom);
    System.out.println("\nBenvingut, " + jugador.getNom() + "! Que comenci l'aventura.\n");
}
```

**Després:** s'utilitza un bucle `do-while` que obliga el jugador a introduir un nom no buit.

```java
// Després
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
```

---

### M-02 — Renombrar `codiNet` per `puntuacio`

**Abans:** la variable i els mètodes usaven el nom `codiNet`, poc intuïtiu.

```java
// Abans — Jugador.java
private int codiNet;
this.codiNet = 0;
public void guanyarCodiNet(int punts) { ... }
public int getCodiNet() { ... }
System.out.println("Energia: " + vida + "/" + vidaMax + "  |  Codi Net: " + codiNet);
```

**Després:** renombrat a `puntuacio` per ser més clar i intuïtiu.

```java
// Després — Jugador.java
private int puntuacio;
this.puntuacio = 0;
public void guanyarPuntuacio(int punts) { ... }
public int getPuntuacio() { ... }
System.out.println("Energia: " + vida + "/" + vidaMax + "  |  Puntuacio: " + puntuacio);
```

---

### M-03 — Opció de tornar a jugar

**Abans:** el joc es tancava directament al final.

```java
// Abans
private void mostrarFinal() {
    // ... mostrar resultat ...
    sc.close();
}
```

**Després:** s'afegeix un menú de reinici que permet tornar a jugar sense tancar el programa.

```java
// Després
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
```

---

### M-04 — Bifurcació de camins

**Abans:** el recorregut era completament lineal, sense cap decisió real per part del jugador.

```java
// Abans — crearSales() creava sempre les mateixes 5 sales en ordre fix
sales.add(sala1);
sales.add(sala2);
sales.add(sala3);
sales.add(sala4);
sales.add(sala5);
```

**Després:** després de la Sala 2 el jugador tria entre dos camins amb contingut diferent.

```java
// Després
int cami = demanarBifurcacio();
if (cami == 1) {
    // Cami A: enemic fort, mes recompensa
    sales.add(new Sala("Sala 3A — Cambra Fosca", new Enemic("StackOverflow", ...), null));
} else {
    // Cami B: dues sales amb items, sense combat
    sales.add(new Sala("Sala 3B — Magatzem Abandonat", null, new Item(...)));
    sales.add(new Sala("Sala 3B-2 — Arxiu Secret", null, new Item(...)));
}
```

---

## 5.4 Reflexió final

### Decisions tècniques preses

La decisió més rellevant del projecte va ser crear una classe abstracta `Personatge` de la qual hereten `Jugador` i `Enemic`. Aquesta estructura no estava prevista inicialment, però va sorgir en analitzar que ambdues entitats compartien atributs i comportaments comuns. Separar el punt d'entrada (`Main.java`) de la lògica del joc (`Joc.java`) va ser una altra decisió important, que fa el codi més llegible i mantenible.

### Part més difícil

La part més difícil va ser el problema de compatibilitat dels emojis amb la consola de Windows. No era un error de lògica sinó d'encoding, i va requerir provar diverses solucions abans de trobar la definitiva. Va ser un bon exemple de com un problema que sembla senzill pot tenir múltiples capes.

### Què he après sobre programació

He après a separar millor les responsabilitats entre classes, aplicant el principi DRY de manera conscient. Treballar amb herència en dos nivells (`Personatge` → `Enemic` → `GranDebugger`) m'ha ajudat a entendre millor quan té sentit crear una jerarquia i quan és millor no complicar l'estructura.

### Què he après sobre proves

He après que les proves no consisteixen només a executar el programa i veure si funciona. Cal provar casos límit: entrades no vàlides, inventari buit, condicions de derrota forçades. Documentar el resultat esperat i l'obtingut obliga a pensar amb més precisió en el comportament real del programa.

### Què he après sobre documentació

He après que documentar no és explicar el codi línia per línia, sinó justificar les decisions: per què s'ha fet així i no d'una altra manera. Un repositori ben documentat demostra que s'ha pensat el projecte, no només que s'ha programat.

### Com he utilitzat la IA

La IA s'ha usat principalment com a eina per accelerar tasques concretes: generar diagrames UML, estructurar el codi inicial i resoldre problemes tècnics puntuals. En cap cas s'ha acceptat cap proposta sense revisar-la i entendre-la. Les decisions de disseny (classe abstracta, `do-while`, eliminació d'emojis) van ser pròpies i prèvies a qualsevol consulta a la IA.

### Què he acceptat de la IA

L'estructura general del codi, els diagrames PlantUML i el diagnòstic del problema d'encoding.

### Què he descartat

Les solucions de `chcp 65001` i `PrintStream` per al problema dels emojis, el `while` en favor del `do-while`, i el nom `codiNet` en favor de `puntuacio`.

### Què milloraria amb més temps

- Afegir un sistema de guardat de partida amb fitxers.
- Implementar més sales i més tipus d'enemics amb comportaments diferenciats.
- Afegir un sistema de nivells per al jugador que augmenti l'atac progressivament.
- Implementar un rànquing de puntuacions per comparar partides.
