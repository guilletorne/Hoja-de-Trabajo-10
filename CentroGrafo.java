import java.util.List;

public class CentroGrafo 
{
    public String calcularCentro(FloydWarshall fw, List<Ciudad> ciudades) 
    {
        int n = ciudades.size();
        int minExcentricidad = Integer.MAX_VALUE;
        String centro = null;

        for (int i = 0; i < n; i++) 
        {
            int max = 0;
            for (int j = 0; j < n; j++) 
            {
                max = Math.max(max, fw.dist[i][j]);
            }
            if (max < minExcentricidad) 
            {
                minExcentricidad = max;
                centro = ciudades.get(i).getNombre();
            }
        }

        return centro;
    }
}

