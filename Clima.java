public enum Clima 
{
    NORMAL(0), LLUVIA(1), NIEVE(2), TORMENTA(3);

    public final int index;

    Clima(int index) 
    {
        this.index = index;
    }

    public static Clima fromString(String s) 
    {
        return switch (s.toLowerCase()) 
        {
            case "normal" -> NORMAL;
            case "lluvia" -> LLUVIA;
            case "nieve" -> NIEVE;
            case "tormenta" -> TORMENTA;
            default -> throw new IllegalArgumentException("Clima inválido");
        };
    }
}
