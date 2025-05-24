import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestGrafoJUnit4 {

    private Grafo grafo;

    @Before
    public void setUp() {
        grafo = new Grafo();
        grafo.agregarCiudad("A");
        grafo.agregarCiudad("B");
        grafo.agregarArco("A", "B", new int[]{5, 6, 7, 10});
    }

    @Test
    public void testAgregarCiudad() {
        grafo.agregarCiudad("C");
        assertEquals(3, grafo.getCiudades().size());
    }

    @Test
    public void testEliminarArco() {
        grafo.eliminarArco("A", "B");
        assertTrue(grafo.getArcos().isEmpty());
    }

    @Test
    public void testAgregarArco() {
        assertEquals(1, grafo.getArcos().size());
    }
}