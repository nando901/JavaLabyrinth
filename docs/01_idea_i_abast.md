# 1. Idea i Abast del Projecte
**Projecte:** Java Labyrinth: The Vibe Quest
**Alumne:** Fernando Cascón
**Mòdul:** Entorns de Desenvolupament — DAM1
**Data:** Maig 2026

---

## 1.1 Títol provisional del joc

**"Java Labyrinth: The Vibe Quest"**

---

## 1.2 Tipus de microvideojoc escollit

Joc textual d'aventura amb combat per torns i gestió de recursos *(tipus 1 + 2 del document de l'activitat)*.

---

## 1.3 Objectiu del joc

L'objectiu principal és explorar una "dungeon" generada per sales, sobreviure a les trobades amb enemics i derrotar el **Gran Debugger** (cap final) per escapar amb el tresor.

---

## 1.4 Rol del jugador

El jugador controla un heroi (desenvolupador) que ha de gestionar dos recursos principals:

- **Energia** → equival a la vida del personatge.
- **Puntuació** → equival als punts/experiència acumulats.

En cada sala, el jugador pot decidir entre explorar, descansar o lluitar.

---

## 1.5 Regles bàsiques

- El jugador comença amb **100 punts d'energia** i l'inventari buit.
- Cada moviment a una sala nova consumeix una petita quantitat d'energia.
- Durant el combat, les opcions disponibles són: **Atacar**, **Defensar** o **Usar Objecte**.
- Si l'energia arriba a **0**, la partida acaba immediatament.

---

## 1.6 Condicions de victòria i derrota

| Condició | Descripció |
|---|---|
| Victòria | Arribar a la sala final i derrotar el cap final (Gran Debugger) |
| Derrota | L'energia del jugador arriba a 0 en qualsevol moment |

---

## 1.7 Bucle principal del joc

El joc segueix el cicle següent de manera repetida:

1. **Entrada a sala** → Es mostra la descripció de l'entorn per consola.
2. **Esdeveniment** → Apareix un enemic, un objecte o una trampa de manera aleatòria.
3. **Decisió** → El jugador introdueix la seva acció amb el teclat (via `Scanner`).
4. **Actualització d'estat** → Es calculen els canvis en energia i punts.
5. **Repetició** → Si el jugador segueix viu, torna al pas 1.

> Decisió tècnica: s'utilitzarà un `do-while` en lloc d'un `while`, ja que garanteix que el primer torn sempre s'executa abans de comprovar la condició de fi de partida.

---

## 1.8 Estats del joc

| Estat | Descripció |
|---|---|
| `JUGANT` | El jugador és viu i la partida continua |
| `VICTÒRIA` | El jugador ha derrotat el cap final |
| `DERROTA` | L'energia del jugador ha arribat a 0 |

Variables que canvien durant la partida: `energia`, `Puntuació`, `inventari`, `salaActual`.

---

## 1.9 Repte principal i dificultat

El repte és l'**equilibri de recursos**: gestionar correctament l'energia per arribar al cap final sense quedar-se sense vida abans.

La dificultat serà **mitjana-baixa**, augmentant progressivament el dany dels enemics a mesura que s'avança cap a la sala final.

---

## 1.10 Limitacions explícites

Les següents funcionalitats queden **fora de l'abast** del projecte per mantenir-lo viable en el temps disponible:

- No hi haurà interfície gràfica (GUI); tot funcionarà per **consola de text**.
- No hi haurà so ni música.
- El combat no tindrà animacions, només registres de text (logs).
- No s'implementarà sistema de guardat de partida (*persistence*).

---

## 1.11 Riscos tècnics

| Risc | Per què pot ser un problema | Possible solució |
|---|---|---|
| Gestió de l'entrada d'usuari | L'usuari pot introduir caràcters no vàlids en lloc de números, provocant `InputMismatchException` | Validar l'entrada amb un bloc `try-catch` i demanar-la de nou si no és vàlida |
| Lògica del combat per torns | Un error en el flux entre el torn del jugador i el de l'enemic podria provocar un bucle infinit | Separar clarament el torn del jugador i el de l'enemic en mètodes independents amb condicions de sortida explícites |
| Estructura de classes i herència | Una jerarquia mal dissenyada per als tipus d'enemics pot complicar el codi innecessàriament | Dissenyar el diagrama de classes a la Fase 2 **abans** de programar, i revisar-lo amb la IA si cal |

---

## 1.12 Exploració amb IA

### Prompt 1
**Eina:** ChatGPT
**Prompt utilitzat:**
```
Dóna'm 3 idees de microvideojocs textuals en Java que es puguin programar
en menys de 10 hores per a un estudiant de DAM, enfocant-se en la gestió
d'estats com vida i punts.
```

**Resposta resumida:** La IA va proposar tres opcions: un simulador de botiga, un combat per torns de gladiadors i un dungeon crawler textual.

**Reflexió i decisió presa:**
El simulador de botiga es va descartar perquè resultava poc atractiu com a idea de joc: massa estàtic, sense repte real i amb poca varietat d'estats a gestionar. Entre els gladiadors i el dungeon crawler, es va escollir el segon perquè permet estructurar millor una jerarquia de classes (jugador, enemics, sales) i, a més, té un aire similar a *The Binding of Isaac*, un joc conegut que servia de referència clara per al disseny. La decisió final va ser pròpia i motivada tant per criteris tècnics com personals.

---

### Prompt 2
**Eina:** Claude
**Prompt utilitzat:**
```
Per a un dungeon crawler textual en Java, quin seria el bucle de joc
més eficient per separar la lògica de la interacció per consola?
```

**Resposta resumida:** La IA va suggerir un `while(jugadorViu)` amb un `switch-case` per a les accions del jugador i mètodes separats per als esdeveniments aleatoris.

**Reflexió i decisió presa:**
- **Acceptat:** L'estructura de mètodes separats es va acceptar completament, ja que aplica el principi **DRY** (*Don't Repeat Yourself*) i millora la llegibilitat i el manteniment del codi.
- **Modificat:** El `while` es va substituir per un `do-while`, ja que garanteix que el primer torn s'executa sempre abans de comprovar la condició de fi de partida.
- **Acceptat:** El `switch-case` es va mantenir perquè, amb múltiples opcions d'acció, els `if-else` encadenats farien el codi innecessàriament llarg i difícil de llegir.

---

## 1.13 Proposta final i justificació de viabilitat

**Proposta escollida:** Dungeon Crawler textual amb combat per torns.

**Justificació de viabilitat:**
- Utilitza estructures bàsiques de Java: condicionals, bucles, llistes i classes.
- No requereix llibreries externes complexes.
- La complexitat és baixa-mitjana i s'adapta al temps disponible de 10 hores.
- Permet complir tots els Resultats d'Aprenentatge (RA1 a RA6) del mòdul.

---

## 1.14 Pla de treball (mini pla)

| Fase | Temps | Contingut principal |
|---|---|---|
| Fase 1 | 1,5h | Definició d'idea i abast |
| Fase 2 | 2h | Disseny de diagrames de classes (`Jugador`, `Enemic`, `Sala`, `Joc`) i diagrama d'activitat |
| Fase 3 | 2h | Configuració IDE + implementació del bucle principal, classe `Jugador` i classe `Sala` |
| Fase 4 | 2h | Implementació del combat (`Enemic`, `GranDebugger`) + proves i depuració |
| Fase 5 | 2h | Refactorització, millores i documentació final |
| Extra | 0,5h | Enregistrament del vídeo de gameplay comentat |

---

## 1.15 Eines i tecnologies previstes

| Eina | Ús previst | Justificació |
|---|---|---|
| **Java 17+** | Llenguatge de programació | Es va considerar Python com a alternativa però es va descartar ja que el currículum de DAM     estableix Java com a llenguatge principal i es al que estic més acostumat. |
| **Visual Studio Code** | IDE de desenvolupament i depuració | Lleuger, gratuït i amb excel·lent suport per a Java mitjançant extensions. Té previsualització integrada de Markdown, útil per a la documentació del projecte. |
| **GitHub** | Control de versions i lliurament | Permet demostrar l'evolució progressiva del projecte mitjançant commits |
| **ChatGPT / Claude** | Suport al desenvolupament (Vibe Coding) | Per resoldre dubtes de lògica, revisar errors i explorar alternatives de disseny |
| **Draw.io / PlantUML** | Creació de diagrames UML | Eines gratuïtes i senzilles per generar diagrames de classes i d'activitat |
