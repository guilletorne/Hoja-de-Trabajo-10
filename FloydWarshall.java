import java.util.*;

public class FloydWarshall 
{
    public int[][] dist;
    public int[][] next;

    public void calcular(Grafo grafo) 
    {
        List<Ciudad> ciudades = grafo.getCiudades();
        int n = ciudades.size();
        dist = grafo.construirMatrizAdyacencia();
        next = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                next[i][j] = (dist[i][j] < Integer.MAX_VALUE / 2) ? j : -1;

        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (dist[i][k] + dist[k][j] < dist[i][j]) 
                    {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
    }

    public List<String> reconstruirRuta(int i, int j, List<Ciudad> ciudades) 
    {
        if (next[i][j] == -1) return null;
        List<String> ruta = new ArrayList<>();
        ruta.add(ciudades.get(i).getNombre());
        while (i != j) 
        {
            i = next[i][j];
            ruta.add(ciudades.get(i).getNombre());
        }
        return ruta;
    }
}
