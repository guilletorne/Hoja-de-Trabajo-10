public class Arco 
{
    private final Ciudad origen;
    private final Ciudad destino;
    private final int[] tiempos;

    public Arco(Ciudad origen, Ciudad destino, int[] tiempos) 
    {
        this.origen = origen;
        this.destino = destino;
        this.tiempos = tiempos;
    }

    public Ciudad getOrigen() 
    {
        return origen;
    }

    public Ciudad getDestino() 
    {
        return destino;
    }

    public int getTiempo(Clima clima) 
    {
        return tiempos[clima.index];
    }

    public void setTiempo(Clima clima, int tiempo) 
    {
        tiempos[clima.index] = tiempo;
    }
}

