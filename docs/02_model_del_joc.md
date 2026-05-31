# 2. Model del Joc
**Projecte:** Java Labyrinth: The Vibe Quest
**Alumne:** Fernando Cascón
**Mòdul:** Entorns de Desenvolupament — DAM1
**Data:** Maig 2026

---

## 2.1 Components principals

El sistema es divideix en tres blocs lògics:

- **Motor de joc:** Gestiona el bucle principal, l'entrada de l'usuari i l'estat global (guanyar/perdre).
- **Entitats:** Representen els elements actius i passius de la masmorra (Jugador, Enemics, Objectes).
- **Escenari:** Gestiona l'estructura de les sales i la progressió del jugador.

---

## 2.2 Taula de components principals

| Component | Tipus | Responsabilitat |
|---|---|---|
| `Personatge` | Classe abstracta | Defineix els atributs i mètodes comuns a totes les entitats vives del joc |
| `Jugador` | Classe (hereda de `Personatge`) | Gestiona l'energia, la puntuació, l'inventari i les accions del jugador |
| `Enemic` | Classe (hereda de `Personatge`) | Representa les amenaces que apareixen a les sales |
| `GranDebugger` | Classe (hereda de `Enemic`) | Cap final del joc amb comportament especial per fases |
| `Sala` | Classe | Conté la descripció de l'entorn i els esdeveniments de cada sala |
| `Item` | Classe | Representa els objectes que el jugador pot recollir i usar |
| `Joc` | Classe principal | Orquestra el bucle de joc i gestiona l'estat global |
| `EstatJoc` | Enum | Defineix els tres estats possibles de la partida |

---

## 2.3 Atributs i mètodes clau

### Personatge *(abstracta)*
- **Atributs:** `nom: String`, `vida: int`
- **Mètodes:** `rebreDany(dany: int): void`, `estaViu(): boolean`

### Jugador
- **Atributs:** `puntuacio: int`, `inventari: List<Item>`
- **Mètodes:** `atacar(enemic: Enemic): void`, `descansar(): void`, `usarObjecte(item: Item): void`

### Enemic
- **Atributs:** `tipus: String`, `danyBase: int`
- **Mètodes:** `atacarJugador(jugador: Jugador): void`

### GranDebugger
- **Atributs:** `faseActual: int`
- **Mètodes:** `canviarFase(): void`

### Sala
- **Atributs:** `descripcio: String`, `teEnemic: boolean`, `itemDisponible: Item`
- **Mètodes:** `generarEsdeveniment(): void`, `descriureEntorn(): void`

### Item
- **Atributs:** `nom: String`, `efecte: int`
- **Mètodes:** `aplicarEfecte(jugador: Jugador): void`

### Joc
- **Atributs:** `estatActual: EstatJoc`, `salaActual: Sala`
- **Mètodes:** `iniciar(): void`, `buclePrincipal(): void`, `verificarCondicions(): void`

### EstatJoc *(enum)*
- `JUGANT`, `VICTORIA`, `DERROTA`

---

## 2.4 Diagrama de classes

![Diagrama de classes](../diagrames/diagrama_classes.png)

### Explicació del diagrama de classes

S'ha optat per crear una **classe abstracta `Personatge`** de la qual hereten tant `Jugador` com `Enemic`. Aquesta decisió es va prendre perquè ambdues entitats comparteixen atributs comuns (`nom`, `vida`) i comportaments comuns (`rebreDany`, `estaViu`), i centralitzar-los en una classe pare evita duplicació de codi aplicant el principi **DRY**.

A més, aquesta estructura aporta **escalabilitat**: en el futur es podrien afegir nous tipus d'entitats (aliats, personatges secundaris) simplement fent-los heretar de `Personatge`, sense modificar el codi existent.

Les relacions del diagrama són:

| Relació | Tipus | Descripció |
|---|---|---|
| `Personatge` → `Jugador` | Herència | `Jugador` hereta els atributs i mètodes base de `Personatge` |
| `Personatge` → `Enemic` | Herència | `Enemic` hereta els atributs i mètodes base de `Personatge` |
| `Enemic` → `GranDebugger` | Herència | `GranDebugger` és un tipus especial d'`Enemic` amb comportament per fases |
| `Joc` → `Sala` | Composició (1 a many) | El joc conté múltiples sales; sense el joc no existeixen |
| `Sala` → `Enemic` | Associació | Una sala pot contenir un enemic |
| `Sala` → `Item` | Associació | Una sala pot contenir un item |
| `Joc` → `EstatJoc` | Associació | El joc usa l'enum per controlar l'estat actual de la partida |

---

## 2.5 Diagrama de comportament

![Diagrama de comportament](../diagrames/diagrama_comportament.png)

### Explicació del diagrama de comportament

S'ha escollit un **diagrama d'activitat** perquè és el tipus de diagrama que millor representa el flux seqüencial i les decisions del bucle de joc. Un diagrama de seqüència seria més adequat per a interaccions entre objectes, i un d'estats per a sistemes amb molts estats transicionals; en canvi, el diagrama d'activitat reflecteix de manera clara i directa el recorregut del jugador per la masmorra.

El flux principal és el següent:

1. **Inici** → Configuració inicial del jugador i missatge de benvinguda.
2. **Bucle de sales** → El jugador entra a una sala nova i es descriu l'entorn.
3. **Bifurcació d'esdeveniment:**
   - Si hi ha **enemic** → s'inicia el bucle de combat.
   - Si hi ha **item** → el jugador el recull a l'inventari.
   - Si la sala és **buida** → es continua a la sala següent.
4. **Bucle de combat** → El jugador tria entre Atacar, Defensar o Usar Objecte. L'enemic contraataca. Es comprova si algun dels dos ha mort.
5. **Final de partida** → Quan el jugador mor (`DERROTA`) o supera la sala final (`VICTÒRIA`).

### Relació entre el diagrama i el codi

| Element del diagrama | Correspon a |
|---|---|
| Bucle de sales | Mètode `buclePrincipal()` de la classe `Joc` |
| Bifurcació d'esdeveniment | Mètode `generarEsdeveniment()` de la classe `Sala` |
| Bucle de combat | Lògica interna del mètode `atacar()` de `Jugador` i `atacarJugador()` de `Enemic` |
| Comprovació de fi de partida | Mètode `verificarCondicions()` de la classe `Joc` |
| Estats VICTÒRIA / DERROTA | Enum `EstatJoc` |

---

## 2.6 Estructura del repositori

```plaintext
java-labyrinth-vibe-quest/
│
├── README.md
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
├── docs/
├── diagrames/
├── evidencies/
├── tests/
└── lib/
    └── junit-platform-console-standalone-6.1.0.jar
```