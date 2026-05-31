# Manual d'Usuari
**Projecte:** Java Labyrinth: The Vibe Quest
**Alumne:** Fernando Cascón
**Mòdul:** Entorns de Desenvolupament — DAM1

---

## Nom del joc

**Java Labyrinth: The Vibe Quest**

---

## Objectiu del joc

Ets un desenvolupador atrapat dins d'un dungeon ple d'errors i bugs. El teu objectiu és explorar les 5 sales del dungeon, sobreviure als enemics i derrotar el **Gran Debugger**, el cap final, per escapar amb vida.

---

## Com iniciar el joc

```bash
# Des del terminal de VSCode
cd src
javac *.java
java Main
```

O bé obrir `Main.java` a VSCode i clicar el botó **Run** que apareix sobre el mètode `main`.

---

## Controls i accions disponibles

| Acció | Com es fa | Resultat |
|---|---|---|
| Atacar | Introduir `1` al menú de combat | Fas dany a l'enemic |
| Defensar | Introduir `2` al menú de combat | Reps la meitat del dany de l'enemic |
| Usar objecte | Introduir `3` al menú de combat | Apliques l'efecte d'un item de l'inventari |
| Recollir item | Introduir `1` quan apareix un item | L'item s'afegeix a l'inventari |
| Ignorar item | Introduir `2` quan apareix un item | Continues sense recollir-lo |
| Tornar a jugar | Introduir `1` al final de la partida | Es reinicia el joc |
| Sortir | Introduir `2` al final de la partida | Es tanca el programa |

---

## Regles del joc

- El jugador comença amb **100 punts d'energia**.
- Cada sala pot contenir un enemic, un item o estar buida.
- Durant el combat, el jugador i l'enemic s'alternen els torns.
- Si tries **Defensar**, reps la meitat del dany i l'enemic no contraataca aquell torn.
- Si tries **Usar objecte** amb l'inventari buit, l'enemic aprofita per atacar.
- El **Gran Debugger** (Sala 5) canvia de fase cada vegada que perd 60 punts de vida, augmentant el seu dany.

---

## Sales del dungeon

| Sala | Nom | Contingut |
|---|---|---|
| Sala 1 | Entrada del Dungeon | Enemic: Bug Menor (30 vida, 8 dany) |
| Sala 2 | Corredor dels Logs | Item: Poció de Refactoring (+30 energia) |
| — | **BIFURCACIÓ** | Tries entre el Camí A o el Camí B |
| Sala 3A | Cambra Fosca *(Camí A)* | Enemic: StackOverflow (70 vida, 18 dany, +35 puntuació) |
| Sala 3B | Magatzem Abandonat *(Camí B)* | Item: Kit de Reparació (+40 energia) |
| Sala 3B-2 | Arxiu Secret *(Camí B)* | Item: Poció Extra (+25 energia) |
| Sala 4 | Arxiu de les Dependències | Item: Kit de Debugging (+50 energia) |
| Sala 5 | Cambra del Gran Debugger | Cap final: Gran Debugger (180 vida, 25 dany base) |

> El **Camí A** és més arriscat però dona més puntuació. El **Camí B** és més segur i ofereix dos items per recuperar energia.

---

## Condicions de victòria

Arribar a la Sala 5 i derrotar el **Gran Debugger** amb energia superior a 0.

---

## Condicions de derrota

L'energia del jugador arriba a **0** en qualsevol moment de la partida.

---

## Exemple de partida

```
JAVA LABYRINTH: THE VIBE QUEST

Benvingut, desenvolupador.
La dungeon t'espera. Troba el Gran Debugger i escapa.

Introdueix el nom del teu heroi: Nando

Benvingut, Nando! Que comenci l'aventura.

========================================
Sala 1 - Entrada del Dungeon
Murs de pedra humida t'envolten. Una torxa parpelleja al fons.
========================================
[ENEMIC] Hi ha un enemic: Bug Menor [Error Runtime]

[COMBAT] COMBAT INICIAT contra Bug Menor!

--- Nando ---
Energia: 100/100  |  Puntuacio: 0
Inventari: buit
[Error Runtime] Bug Menor - Vida: 30/30

Que vols fer?
  1. Atacar
  2. Defensar
  3. Usar objecte
> 1
Nando ataca i fa 15 de dany a Bug Menor.
Bug Menor contraataca i fa 8 de dany.
```

---

## Consells

- Recull sempre els items quan els trobis, et poden salvar la vida contra el Gran Debugger.
- Usa la defensa quan tinguis poca energia per reduir el dany rebut.
- Guarda els items per al combat final contra el Gran Debugger.

---

## Problemes coneguts

- Els emojis no es mostren correctament a la consola de Windows. S'han substituït per etiquetes de text (`[ENEMIC]`, `[COMBAT]`, etc.) per garantir la compatibilitat.
- El joc no té sistema de guardat: si es tanca el programa, la partida es perd.
