import java.util.*;

public class Grafo 
{
    private final Map<String, Ciudad> ciudades = new HashMap<>();
    private final List<Arco> arcos = new ArrayList<>();
    private Clima climaActual = Clima.NORMAL;

    public void agregarCiudad(String nombre) 
    {
        ciudades.putIfAbsent(nombre, new Ciudad(nombre));
    }

    public void agregarArco(String origen, String destino, int[] tiempos) 
    {
        Ciudad o = ciudades.get(origen);
        Ciudad d = ciudades.get(destino);
        if (o != null && d != null) 
        {
            arcos.add(new Arco(o, d, tiempos));
        }
    }

    public void eliminarArco(String origen, String destino) 
    {
        arcos.removeIf(arco ->
            arco.getOrigen().getNombre().equals(origen) &&
            arco.getDestino().getNombre().equals(destino));
    }

    public void setClima(Clima clima) 
    {
        this.climaActual = clima;
    }

    public Clima getClimaActual() 
    {
        return climaActual;
    }

    public List<Ciudad> getCiudades() 
    {
        return new ArrayList<>(ciudades.values());
    }

    public List<Arco> getArcos() 
    {
        return arcos;
    }

    public int[][] construirMatrizAdyacencia() 
    {
        int n = ciudades.size();
        int[][] matriz = new int[n][n];
        for (int[] fila : matriz) Arrays.fill(fila, Integer.MAX_VALUE / 2);

        List<Ciudad> lista = getCiudades();
        for (int i = 0; i < n; i++) matriz[i][i] = 0;

        for (Arco arco : arcos) 
        {
            int i = lista.indexOf(arco.getOrigen());
            int j = lista.indexOf(arco.getDestino());
            matriz[i][j] = arco.getTiempo(climaActual);
        }

        return matriz;
    }
}

