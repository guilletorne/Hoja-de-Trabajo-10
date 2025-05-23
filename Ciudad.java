public class Ciudad 
{
    private final String nombre;

    public Ciudad(String nombre) 
    {
        this.nombre = nombre;
    }

    public String getNombre() 
    {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (!(obj instanceof Ciudad)) return false;
        return nombre.equalsIgnoreCase(((Ciudad) obj).nombre);
    }

    @Override
    public int hashCode() 
    {
        return nombre.toLowerCase().hashCode();
    }
}
