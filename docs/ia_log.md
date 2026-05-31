# Registre d'ús de la IA
**Projecte:** Java Labyrinth: The Vibe Quest
**Alumne:** Fernando Cascón
**Mòdul:** Entorns de Desenvolupament — DAM1

---

## Normes personals d'ús

La IA s'ha utilitzat com a eina de suport al desenvolupament, mai com a substitut del pensament propi. Els usos principals han estat:

- Explorar alternatives de disseny i mecanismes de joc.
- Generar codi de diagrames UML (PlantUML) per revisar i validar.
- Contrastar decisions tècniques d'estructura i arquitectura del codi.
- Millorar la redacció tècnica de la documentació.

En cap cas s'ha acceptat cap proposta sense analitzar-la, justificar-la i adaptar-la al projecte.

---

## Taula de prompts

| Núm. | Fase | Eina | Prompt utilitzat | Resposta resumida | Què he aprofitat | Què he descartat | Justificació |
|---|---|---|---|---|---|---|---|
| 1 | Fase 1 | ChatGPT | Vegeu entrada detallada #1 | Va proposar tres tipus de microvideojocs textuals en Java | La idea del dungeon crawler textual | Simulador de botiga i combat de gladiadors | Vegeu entrada detallada #1 |
| 2 | Fase 1 | ChatGPT | Vegeu entrada detallada #2 | Va suggerir un `while` amb `switch-case` i mètodes separats | Estructura de mètodes separats i `switch-case` | El `while` com a estructura del bucle principal | Vegeu entrada detallada #2 |
| 3 | Fase 2 | Claude | Vegeu entrada detallada #3 | Va generar el codi PlantUML per al diagrama de classes | El codi PlantUML com a base del diagrama | Cap part descartada, però sí revisada i validada | Vegeu entrada detallada #3 |
| 4 | Fase 2 | Claude | Vegeu entrada detallada #4 | Va generar el codi PlantUML per al diagrama d'activitat | El codi PlantUML com a base del diagrama | Cap part descartada, però sí revisada i validada | Vegeu entrada detallada #4 |
| 5 | Fase 3 | Claude | Vegeu entrada detallada #5 | Va generar el codi Java complet de les 9 classes del projecte | L'estructura general i la separació de responsabilitats | Algunes decisions de nomenclatura adaptades al meu estil | Vegeu entrada detallada #5 |
| 6 | Fase 4 | Claude | Vegeu entrada detallada #6 | Va proposar tres solucions per al problema dels emojis a Windows | La solució definitiva d'eliminar els emojis i usar etiquetes de text | Les solucions de `chcp` i `PrintStream` per ser incomplertes | Vegeu entrada detallada #6 |
| 7 | Fase 6 | Claude | Vegeu entrada detallada #7 | Va implementar la bifurcació de camins al mètode `crearSales()` | L'estructura del `if/else` per als dos camins i el mètode `demanarBifurcacio()` | Cap part descartada | Vegeu entrada detallada #7 |
| 8 | Fase 6 | Claude | Vegeu entrada detallada #8 | Va rebalancejar el joc: arma, sales de descans, nou enemic al Camí B i noves estadístiques del Gran Debugger. Va corregir tots els caràcters Unicode problemàtics | Tots els canvis aplicats | Cap part descartada | Vegeu entrada detallada #8 |

---

## Entrades detallades

### Entrada #1 — Exploració d'idees de joc

**Fase:** 1 — Idea i abast
**Eina:** ChatGPT

**Prompt:**
```
Proposa'm tres tipus de microvideojocs textuals implementables en Java
en un màxim de 10 hores de desenvolupament, adequats per a un estudiant
de primer curs de DAM. Els jocs han de permetre gestionar estats com
vida, punts o experiència, i han de ser prou estructurats per aplicar
principis d'orientació a objectes.
```

**Resposta resumida de la IA:**
Va proposar tres opcions: un simulador de botiga amb gestió econòmica, un combat per torns de gladiadors i un dungeon crawler textual amb sistema d'exploració per sales.

**Decisió presa:**
Es va escollir el dungeon crawler textual.

**Què he aprofitat:**
La idea del dungeon crawler, ja que permet estructurar una jerarquia de classes clara (jugador, enemics, sales) i gestionar múltiples estats simultàniament (vida, punts, inventari).

**Què he descartat:**
- *Simulador de botiga:* descartat per ser massa estàtic, amb poca varietat d'estats i sense repte real per al jugador.
- *Combat de gladiadors:* descartat perquè el dungeon crawler oferia més possibilitats d'estructuració en classes i una referència de disseny més clara.

**Valoració crítica:**
La resposta era correcta i útil com a punt de partida. La decisió final, però, va estar motivada tant per criteris tècnics (millor jerarquia de classes) com personals (similitud amb *The Binding of Isaac* com a referència de disseny).

---

### Entrada #2 — Estructura del bucle principal de joc

**Fase:** 1 — Idea i abast
**Eina:** ChatGPT

**Prompt:**
```
Per a un dungeon crawler textual en Java, quina estructura de control
seria més adequada per implementar el bucle principal de joc, separant
correctament la lògica de negoci de la interacció per consola i
respectant el principi DRY?
```

**Resposta resumida de la IA:**
Va suggerir un `while(jugadorViu)` com a bucle principal, un `switch-case` per gestionar les accions del jugador i mètodes separats per encapsular els esdeveniments aleatoris de cada sala.

**Decisió presa:**
S'ha adoptat parcialment la proposta amb una modificació important.

**Què he aprofitat:**
- L'estructura de mètodes separats per encapsular responsabilitats, aplicant el principi DRY.
- El `switch-case` per gestionar les opcions d'acció del jugador, més llegible que una cadena de `if-else`.

**Què he descartat:**
- El `while` com a estructura del bucle principal: s'ha substituït per un `do-while`, ja que garanteix que el primer torn s'executa sempre abans de comprovar la condició de finalització de la partida.

**Valoració crítica:**
La proposta era tècnicament correcta però no òptima per a aquest cas concret. La substitució del `while` per un `do-while` és una decisió pròpia basada en el comportament esperat del joc.

---

### Entrada #3 — Generació del diagrama de classes en PlantUML

**Fase:** 2 — Model del joc
**Eina:** Claude

**Prompt:**
```
Genera el codi PlantUML per a un diagrama de classes d'un dungeon crawler
textual en Java amb les següents classes: Personatge (abstracta), Jugador,
Enemic, GranDebugger, Sala, Item i Joc. Inclou una enum EstatJoc amb els
estats JUGANT, VICTORIA i DERROTA. Representa les relacions d'herència,
composició i associació corresponents.
```

**Resposta resumida de la IA:**
Va generar el codi PlantUML complet amb totes les classes, atributs, mètodes i relacions sol·licitades.

**Decisió presa:**
S'ha acceptat el codi generat després de revisar-lo i validar que les relacions representades eren correctes i coherents amb el disseny previ.

**Què he aprofitat:**
El codi PlantUML complet com a base per generar el diagrama visual.

**Què he descartat:**
No s'ha descartat cap element, però s'ha revisat manualment cada relació per assegurar la coherència amb l'arquitectura decidida prèviament.

**Valoració crítica:**
La resposta era correcta i completa. La decisió clau d'usar una classe abstracta `Personatge` havia estat presa prèviament de manera independent: la IA simplement va implementar el disseny ja decidit.

---

### Entrada #4 — Generació del diagrama de comportament en PlantUML

**Fase:** 2 — Model del joc
**Eina:** Claude

**Prompt:**
```
Genera el codi PlantUML per a un diagrama d'activitat que representi
el bucle principal d'un dungeon crawler textual en Java. El diagrama
ha d'incloure: configuració inicial, bucle de sales, bifurcació
enemic/item/sala buida, bucle de combat intern amb les opcions
Atacar/Defensar/Usar Objecte, i les condicions finals de VICTÒRIA
i DERROTA.
```

**Resposta resumida de la IA:**
Va generar el codi PlantUML complet del diagrama d'activitat amb tots els elements sol·licitats.

**Decisió presa:**
S'ha acceptat el codi generat després de revisar que el flux representat era coherent amb el bucle de joc definit a la Fase 1.

**Què he aprofitat:**
El codi PlantUML complet com a base per generar el diagrama visual.

**Què he descartat:**
No s'ha descartat cap element. Es va optar per un diagrama d'activitat en lloc d'un de seqüència o d'estats perquè representa millor el flux seqüencial i les decisions del bucle de joc.

**Valoració crítica:**
La resposta era correcta i completa. La decisió de usar un diagrama d'activitat va ser pròpia i prèvia a la generació: la IA va implementar el tipus de diagrama ja escollit.

---

### Entrada #5 — Generació del codi Java del projecte

**Fase:** 3 — Entorn i prototip funcional
**Eina:** Claude

**Prompt:**
```
Genera el codi Java complet per a un dungeon crawler textual seguint
l'arquitectura definida als diagrames: classe abstracta Personatge,
Jugador, Enemic, GranDebugger, Sala, Item, Joc i EstatJoc. El joc ha
de tenir 5 sales, combat per torns amb les opcions Atacar/Defensar/Usar
Objecte, validació d'entrades amb try-catch i un sistema de fases per
al cap final. El codi ha de seguir el meu estil: atributs protected a
les classes pare, private amb getters/setters a les filles, Scanner amb
nextLine() després de nextInt(), comentaris en català i switch-case per
als menús.
```

**Resposta resumida de la IA:**
Va generar els 9 fitxers Java complets seguint l'arquitectura dels diagrames i respectant l'estil de codificació indicat.

**Decisió presa:**
S'ha acceptat el codi com a base del prototip, però s'han revisat totes les classes per verificar que la lògica era correcta i coherent amb el disseny previ.

**Què he aprofitat:**
- L'estructura general de totes les classes i la separació de responsabilitats.
- La implementació del sistema de fases del `GranDebugger`.
- La validació d'entrades amb `try-catch` i `InputMismatchException`.
- El `do-while` com a bucle principal, tal com havia decidit a la Fase 1.

**Què he descartat:**
- El nom `codiNet` per a la puntuació, que es valorà canviar per ser poc intuïtiu (pendent per a la Fase 6).
- L'ús d'emojis als missatges de consola, que va resultar incompatible amb Windows (resolt a la Fase 4).

**Valoració crítica:**
La IA va generar un codi correcte i ben estructurat, però calia revisar-lo íntegrament per entendre cada decisió abans d'acceptar-la. El fet que el codi seguís exactament l'arquitectura dissenyada prèviament als diagrames demostra que el disseny previ va ser útil i ben definit.

---

### Entrada #6 — Resolució del problema d'emojis a Windows

**Fase:** 4 — Proves i depuració
**Eina:** Claude

**Prompt:**
```
Els emojis del codi Java (com ara ⚔, ✔, ⚠) es mostren com a caràcters
corruptes a la consola de PowerShell de Windows. Com puc solucionar-ho
sense canviar la configuració del sistema?
```

**Resposta resumida de la IA:**
Va proposar dues solucions: activar UTF-8 al terminal amb `chcp 65001` i forçar l'encoding de Java amb `System.setOut(new PrintStream(System.out, true, "UTF-8"))`.

**Decisió presa:**
Cap de les dues solucions proposades va funcionar correctament. Es va aplicar una solució pròpia diferent.

**Què he aprofitat:**
El diagnòstic del problema: la incompatibilitat entre l'encoding de PowerShell i els caràcters Unicode de Java.

**Què he descartat:**
- `chcp 65001`: el problema persistia perquè Java usava el seu propi encoding internament.
- `PrintStream` amb UTF-8: requeria gestionar una excepció addicional i el problema continuava.

**Valoració crítica:**
Les solucions de la IA eren tècnicament vàlides en teoria però no resolien el problema en aquest entorn concret. La solució definitiva va ser pròpia: eliminar els emojis i substituir-los per etiquetes de text entre claudàtors (`[COMBAT]`, `[OK]`, `[!]`), que funcionen correctament en qualsevol terminal independentment de la configuració d'encoding.

---

### Entrada #7 — Implementació de la bifurcació de camins

**Fase:** 6 — Refactorització i millores
**Eina:** Claude

**Prompt:**
```
Afegeix una bifurcació de camins al joc després de la Sala 2. El jugador
ha de poder triar entre dos camins: el Cami A amb un enemic fort i mes
recompensa, i el Cami B amb dues sales amb items i sense combat. Implementa
el canvi al metode crearSales() de Joc.java afegint un nou metode
demanarBifurcacio() amb validació d'entrada.
```

**Resposta resumida de la IA:**
Va modificar el mètode `crearSales()` per incloure la bifurcació i va afegir el mètode `demanarBifurcacio()` amb un bucle `do-while` i gestió d'excepcions.

**Decisió presa:**
S'ha acceptat la implementació completa després de verificar que el codi era coherent amb l'estil del projecte i que la lògica de bifurcació funcionava correctament.

**Què he aprofitat:**
L'estructura completa de la bifurcació: el `if/else` per als dos camins, el mètode `demanarBifurcacio()` i les sales amb contingut diferenciat per a cada camí.

**Què he descartat:**
Cap element. La idea de la bifurcació va ser pròpia i prèvia a la consulta; la IA simplement va implementar el disseny ja decidit.

**Valoració crítica:**
La implementació era correcta i coherent. La decisió de fer el Camí A més arriscat i el Camí B més segur com a mecànica de risc/recompensa va ser una decisió de disseny pròpia que la IA va implementar fidelment.

---

### Entrada #8 — Rebalancejar el joc, sala de descans interactiva i correcció d'icones

**Fase:** 6 — Refactorització i millores
**Eina:** Claude

**Prompt:**
```
El joc es impossible de superar pel Cami A. Afegeix una arma a la Sala 2
que augmenti l'atac de 15 a 20, una sala de descans interactiva abans del
cap final on es puguin usar items de l'inventari, un enemic al Cami B,
i rebalanceja el Gran Debugger. Corregeix tambe els caracters especials
que mostren interrogants a la consola de Windows.
```

**Resposta resumida de la IA:**
Va reescriure `Joc.java` amb nova estructura de sales, va afegir `boostAtac()` a `Jugador.java`, va afegir `ofertarUsarItems()` per a les sales de descans, va rebalancejar `GranDebugger.java` (100HP, 18 dany, +8/fase) i va eliminar tots els caràcters Unicode problemàtics (╔═║╚, guions llargs, punt volat).

**Decisió presa:**
S'han acceptat tots els canvis. La sala de descans interactiva permet al jugador usar qualsevol item de l'inventari abans d'afrontar el cap final, cosa que millora significativament l'equilibri del joc.

**Què he aprofitat:**
L'estructura completa: arma auto-equipable, `ofertarUsarItems()` en bucle, nou enemic al Camí B (NullPointerException), reducció del Gran Debugger a 100HP i eliminació sistemàtica de tots els caràcters Unicode problemàtics.

**Què he descartat:**
Cap element. Tots els canvis eren necessaris i correctes.

**Valoració crítica:**
El problema d'equilibri era real i verificat durant les proves. La solució és equilibrada: el Camí A segueix sent més difícil però ara és superable, i el Camí B és segur però amb menys recursos. La sala de descans és un element de disseny que aporta agència al jugador en un moment crític de la partida.
