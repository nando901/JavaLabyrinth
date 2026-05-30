# Manual Tècnic
**Projecte:** Java Labyrinth: The Vibe Quest
**Alumne:** Fernando Cascón
**Mòdul:** Entorns de Desenvolupament — DAM1

---

## Arquitectura general

El projecte segueix una arquitectura orientada a objectes amb separació clara de responsabilitats. La lògica del joc està centralitzada a `Joc.java`, mentre que cada entitat del sistema té la seva pròpia classe. El punt d'entrada és `Main.java`, que simplement instancia i inicia el joc.

---

## Estructura de carpetes

```plaintext
java-labyrinth-vibe-quest/
│
├── src/
│   ├── Main.java
│   ├── Joc.java
│   ├── Personatge.java
│   ├── Jugador.java
│   ├── Enemic.java
│   ├── GranDebugger.java
│   ├── Sala.java
│   ├── Item.java
│   ├── EstatJoc.java
│   └── TestJoc.java
│
├── docs/
├── diagrames/
├── evidencies/
├── tests/
├── lib/
│   └── junit-platform-console-standalone-6.1.0.jar
└── README.md
```

---

## Components principals del codi

| Component | Fitxer | Responsabilitat |
|---|---|---|
| `Main` | `Main.java` | Punt d'entrada. Instancia i inicia `Joc`. |
| `Joc` | `Joc.java` | Orquestra el bucle principal, el combat, els items i el final de partida. |
| `Personatge` | `Personatge.java` | Classe abstracta base per a totes les entitats vives. |
| `Jugador` | `Jugador.java` | Gestiona l'energia, la puntuació, l'inventari i les accions del jugador. |
| `Enemic` | `Enemic.java` | Representa els enemics de les sales amb dany aleatori. |
| `GranDebugger` | `GranDebugger.java` | Cap final amb sistema de fases que augmenta el dany progressivament. |
| `Sala` | `Sala.java` | Conté la descripció de l'entorn i els esdeveniments de cada sala. |
| `Item` | `Item.java` | Objectes recollibles que restauren energia al jugador. |
| `EstatJoc` | `EstatJoc.java` | Enum amb els estats possibles de la partida: JUGANT, VICTORIA, DERROTA. |
| `TestJoc` | `TestJoc.java` | Tests automatitzats JUnit per verificar la lògica del joc. |

---

## Jerarquia de classes

```plaintext
Personatge (abstracta)
├── Jugador
└── Enemic
    └── GranDebugger
```

---

## Flux principal del programa

1. `Main.main()` instancia `Joc` i crida `iniciar()`.
2. `iniciar()` crida `mostrarBenvinguda()`, `crearJugador()` i `crearSales()`.
3. `buclePrincipal()` s'executa amb un `do-while` fins que `estatActual != JUGANT`.
4. En cada iteració es processa una sala: combat, item o sala buida.
5. `verificarCondicions()` comprova si el jugador ha mort o ha guanyat.
6. `mostrarFinal()` mostra el resultat i ofereix tornar a jugar.

---

## Diagrames

![Diagrama de classes](../diagrames/diagrama_classes.png)
![Diagrama de comportament](../diagrames/diagrama_comportament.png)

---

## Decisions tècniques

| Decisió | Justificació | Alternativa descartada |
|---|---|---|
| Classe abstracta `Personatge` | Centralitza atributs i mètodes comuns a `Jugador` i `Enemic`, evitant duplicació de codi (DRY) i facilitant l'escalabilitat | Dues classes independents sense jerarquia |
| `do-while` com a bucle principal | Garanteix que la primera sala s'executa sempre abans de comprovar la condició de finalització | `while` estàndard |
| `switch-case` per a les accions de combat | Més llegible i mantenible que una cadena d'`if-else` amb múltiples opcions | `if-else` encadenats |
| `try-catch` per a les entrades | Evita que el programa es tanqui amb `InputMismatchException` si l'usuari introdueix un caràcter no vàlid | Sense validació |
| Etiquetes de text en lloc d'emojis | Els emojis no es mostren correctament a la consola de Windows per problemes d'encoding | Emojis Unicode |
| `Main.java` separat de `Joc.java` | El punt d'entrada no conté lògica pròpia, cosa que fa el codi més llegible i el joc instanciable de manera independent | Tot al `main` |

---

## Com executar els tests

```bash
# Des de VSCode
# Obrir la pestanya Testing a la barra lateral i clicar Run All Tests

# Des del terminal
cd src
javac -cp .;../lib/junit-platform-console-standalone-6.1.0.jar *.java
java -jar ../lib/junit-platform-console-standalone-6.1.0.jar --class-path . --scan-class-path
```

---

## Possibles millores futures

- Afegir un sistema de guardat de partida amb fitxers (`FileWriter` / `BufferedReader`).
- Implementar més tipus d'enemics amb comportaments diferenciats mitjançant polimorfisme.
- Afegir un sistema de nivells per al jugador que augmenti l'atac progressivament.
- Implementar un rànquing de puntuacions per comparar partides.
- Migrar el projecte a Maven per gestionar les dependències de manera professional.