# Casos de Prova
**Projecte:** Java Labyrinth: The Vibe Quest
**Alumne:** Fernando Cascón
**Mòdul:** Entorns de Desenvolupament — DAM1
**Data:** Maig 2026

---

## Proves manuals

### CP-01 — Inici correcte del joc

**Objectiu:** Comprovar que el joc s'inicia correctament i mostra la pantalla de benvinguda.

**Passos:**
1. Executar `Main.java`.
2. Observar el títol del joc i el missatge de benvinguda.
3. Comprovar que es demana el nom del jugador.

**Resultat esperat:** Es mostra el títol i es demana el nom.
**Resultat obtingut:** Es mostra correctament.
**Estat:** [OK] Superada.

---

### CP-02 — Validació d'entrada no vàlida al combat

**Objectiu:** Comprovar que el menú de combat rebutja entrades no vàlides.

**Passos:**
1. Iniciar el joc i entrar a la Sala 1.
2. Quan apareix el menú de combat, introduir `a`.
3. Observar la resposta del programa.
4. Introduir `4`.
5. Observar la resposta del programa.

**Resultat esperat:** El programa mostra "Introdueix un número vàlid." per a la lletra i "Opció no vàlida. Tria entre 1 i 3." per al número fora de rang.
**Resultat obtingut:** Tots dos missatges es mostren correctament i el menú torna a demanar l'opció.
**Estat:** [OK] Superada.

---

### CP-03 — Usar objecte amb inventari buit

**Objectiu:** Comprovar el comportament quan es tria "Usar objecte" sense tenir cap item.

**Passos:**
1. Iniciar el joc i entrar a la Sala 1 sense recollir cap item.
2. Durant el combat, seleccionar l'opció `3` (Usar objecte).
3. Observar la resposta del programa.

**Resultat esperat:** El programa avisa que no hi ha objectes i l'enemic aprofita per atacar.
**Resultat obtingut:** Es mostra el missatge i l'enemic ataca correctament.
**Estat:** [OK] Superada.

---

### CP-04 — Condició de derrota

**Objectiu:** Comprovar que el joc finalitza correctament quan l'energia arriba a 0.

**Passos:**
1. Iniciar el joc.
2. Deixar que l'enemic ataqui repetidament sense defensar-se.
3. Observar el comportament quan l'energia arriba a 0.

**Resultat esperat:** El joc mostra el missatge de DERROTA i ofereix tornar a jugar.
**Resultat obtingut:** El joc finalitza correctament amb el missatge esperat.
**Estat:** [OK] Superada.

---

### CP-05 — Accés a la sala final i Gran Debugger

**Objectiu:** Comprovar que s'arriba correctament a la sala final i el Gran Debugger apareix en Fase 1.

**Passos:**
1. Completar les 4 primeres sales.
2. Entrar a la Sala 5.
3. Observar l'aparició del Gran Debugger.

**Resultat esperat:** Es mostra la Sala 5 amb el Gran Debugger amb 180/180 de vida i Fase 1.
**Resultat obtingut:** Es mostra correctament.
**Estat:** [OK] Superada.

### CP-06 — Bifurcació de camins

**Objectiu:** Comprovar que la bifurcació funciona correctament i carrega les sales corresponents a cada camí.

**Passos:**
1. Completar la Sala 1 i la Sala 2.
2. Quan apareix la bifurcació, triar l'opció `1` (Camí A).
3. Comprovar que la Sala 3A apareix amb l'enemic StackOverflow.
4. Repetir el procés triant l'opció `2` (Camí B).
5. Comprovar que apareix la Sala 3B amb un item i després la Sala 3B-2 amb un segon item.

**Resultat esperat:** Cada camí carrega les sales corresponents correctament.
**Resultat obtingut:** Ambdós camins funcionen correctament.
**Estat:** [OK] Superada.

---

Les proves automatitzades s'han implementat amb **JUnit 6.1.0** al fitxer `src/TestJoc.java`.

### Com executar les proves

**Des de VSCode:**
Obrir la pestanya **Testing** a la barra lateral esquerra i clicar **Run All Tests**.

**Des del terminal:**
```bash
cd src
javac -cp .;../lib/junit-platform-console-standalone-6.1.0.jar *.java
java -jar ../lib/junit-platform-console-standalone-6.1.0.jar --class-path . --scan-class-path
```

---

### CT-01 — Jugador comença amb 100 de vida

**Objectiu:** Verificar que un jugador nou té exactament 100 punts d'energia.

**Codi del test:**
```java
@Test
void jugadorComencaAmbVida100() {
    Jugador j = new Jugador("Test");
    assertEquals(100, j.getVida());
}
```

**Resultat esperat:** `getVida()` retorna 100.
**Resultat obtingut:** Test passat.
**Estat:** [OK] Superada.

---

### CT-02 — `rebreDany` redueix la vida correctament

**Objectiu:** Verificar que rebre 30 de dany redueix la vida de 100 a 70.

**Codi del test:**
```java
@Test
void rebreDanyReduceixVida() {
    Jugador j = new Jugador("Test");
    j.rebreDany(30);
    assertEquals(70, j.getVida());
}
```

**Resultat esperat:** `getVida()` retorna 70.
**Resultat obtingut:** Test passat.
**Estat:** [OK] Superada.

---

### CT-03 — La vida no pot ser negativa

**Objectiu:** Verificar que la vida no baixa per sota de 0 encara que el dany sigui superior a la vida actual.

**Codi del test:**
```java
@Test
void vidaNoPotSerNegativa() {
    Jugador j = new Jugador("Test");
    j.rebreDany(200);
    assertEquals(0, j.getVida());
}
```

**Resultat esperat:** `getVida()` retorna 0.
**Resultat obtingut:** Test passat.
**Estat:** [OK] Superada.

---

### CT-04 — `estaViu` retorna false amb vida zero

**Objectiu:** Verificar que `estaViu()` retorna `false` quan la vida és 0.

**Codi del test:**
```java
@Test
void estaViuRetornaFalseAmbVidaZero() {
    Jugador j = new Jugador("Test");
    j.rebreDany(100);
    assertFalse(j.estaViu());
}
```

**Resultat esperat:** `estaViu()` retorna `false`.
**Resultat obtingut:** Test passat.
**Estat:** [OK] Superada.

---

### CT-05 — `curar` no supera la vida màxima

**Objectiu:** Verificar que curar-se no permet superar els 100 punts d'energia màxims.

**Codi del test:**
```java
@Test
void curarNoSuperaVidaMax() {
    Jugador j = new Jugador("Test");
    j.rebreDany(20);
    j.curar(50);
    assertEquals(100, j.getVida());
}
```

**Resultat esperat:** `getVida()` retorna 100, no 130.
**Resultat obtingut:** Test passat.
**Estat:** [OK] Superada.

---

### CT-06 — Gran Debugger comença en Fase 1

**Objectiu:** Verificar que el Gran Debugger s'inicialitza correctament en la Fase 1.

**Codi del test:**
```java
@Test
void granDebuggerComencaEnFase1() {
    GranDebugger g = new GranDebugger();
    assertEquals(1, g.getFaseActual());
}
```

**Resultat esperat:** `getFaseActual()` retorna 1.
**Resultat obtingut:** Test passat.
**Estat:** [OK] Superada.

---

### CT-07 — Enemic està viu amb vida positiva

**Objectiu:** Verificar que `estaViu()` retorna `true` quan la vida de l'enemic és positiva.

**Codi del test:**
```java
@Test
void enemicEstaViuAmbVidaPositiva() {
    Enemic e = new Enemic("Test", "Tipus", 50, 10, 5);
    assertTrue(e.estaViu());
}
```

**Resultat esperat:** `estaViu()` retorna `true`.
**Resultat obtingut:** Test passat.
**Estat:** [OK] Superada.

---

## Resum de resultats

| Codi | Tipus | Descripció | Estat |
|---|---|---|---|
| CP-01 | Manual | Inici correcte del joc | [OK] Superada |
| CP-02 | Manual | Validació d'entrada no vàlida | [OK] Superada |
| CP-03 | Manual | Usar objecte amb inventari buit | [OK] Superada |
| CP-04 | Manual | Condició de derrota | [OK] Superada |
| CP-05 | Manual | Accés al Gran Debugger | [OK] Superada |
| CP-06 | Manual | Bifurcació de camins | [OK] Superada |
| CT-01 | Automatitzada | Jugador comença amb 100 de vida | [OK] Superada |
| CT-02 | Automatitzada | `rebreDany` redueix la vida | [OK] Superada |
| CT-03 | Automatitzada | Vida no pot ser negativa | [OK] Superada |
| CT-04 | Automatitzada | `estaViu` retorna false amb vida 0 | [OK] Superada |
| CT-05 | Automatitzada | `curar` no supera vidaMax | [OK] Superada |
| CT-06 | Automatitzada | Gran Debugger comença en Fase 1 | [OK] Superada |
| CT-07 | Automatitzada | Enemic està viu amb vida positiva | [OK] Superada |
