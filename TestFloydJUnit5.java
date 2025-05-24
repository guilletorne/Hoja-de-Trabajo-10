import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TestFloydJUnit5 {

    private Grafo grafo;
    private FloydWarshall floyd;

    @BeforeEach
    void setUp() {
        grafo = new Grafo();
        grafo.agregarCiudad("A");
        grafo.agregarCiudad("B");
        grafo.agregarCiudad("C");

        grafo.agregarArco("A", "B", new int[]{3, 4, 5, 6});
        grafo.agregarArco("B", "C", new int[]{2, 3, 4, 5});

        floyd = new FloydWarshall();
        floyd.calcular(grafo);
    }

    @Test
    void testCaminoMasCorto() {
        List<Ciudad> ciudades = grafo.getCiudades();
        int i = ciudades.indexOf(new Ciudad("A"));
        int j = ciudades.indexOf(new Ciudad("C"));
        List<String> ruta = floyd.reconstruirRuta(i, j, ciudades);
        assertEquals(List.of("A", "B", "C"), ruta);
        assertEquals(5, floyd.dist[i][j]);
    }
}