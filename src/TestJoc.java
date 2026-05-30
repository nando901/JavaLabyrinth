import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestJoc {

    @Test
    void jugadorComencaAmbVida100() {
        Jugador j = new Jugador("Test");
        assertEquals(100, j.getVida());
    }

    @Test
    void rebreDanyReduceixVida() {
        Jugador j = new Jugador("Test");
        j.rebreDany(30);
        assertEquals(70, j.getVida());
    }

    @Test
    void vidaNoPotSerNegativa() {
        Jugador j = new Jugador("Test");
        j.rebreDany(200);
        assertEquals(0, j.getVida());
    }

    @Test
    void estaViuRetornaFalseAmbVidaZero() {
        Jugador j = new Jugador("Test");
        j.rebreDany(100);
        assertFalse(j.estaViu());
    }

    @Test
    void curarNoSuperaVidaMax() {
        Jugador j = new Jugador("Test");
        j.rebreDany(20);
        j.curar(50);
        assertEquals(100, j.getVida());
    }

    @Test
    void granDebuggerComencaEnFase1() {
        GranDebugger g = new GranDebugger();
        assertEquals(1, g.getFaseActual());
    }

    @Test
    void enemicEstaViuAmbVidaPositiva() {
        Enemic e = new Enemic("Test", "Tipus", 50, 10, 5);
        assertTrue(e.estaViu());
    }
}