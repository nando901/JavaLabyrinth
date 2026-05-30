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
