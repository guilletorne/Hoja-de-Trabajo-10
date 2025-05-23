import java.io.*;
import java.util.*;

public class Main 
{
    public static void main(String[] args) throws IOException 
    {
        Grafo grafo = new Grafo();
        cargarArchivo("guategrafo.txt", grafo);
        FloydWarshall floyd = new FloydWarshall();
        floyd.calcular(grafo);
        Scanner sc = new Scanner(System.in);
        List<Ciudad> ciudades = grafo.getCiudades();

        while (true) {
            System.out.println("1. Ruta más corta");
            System.out.println("2. Ciudad centro del grafo");
            System.out.println("3. Modificar grafo");
            System.out.println("4. Salir");
            switch (sc.nextLine()) 
            {
                case "1" -> 
                {
                    System.out.print("Ciudad origen: ");
                    String origen = sc.nextLine();
                    System.out.print("Ciudad destino: ");
                    String destino = sc.nextLine();
                    int i = ciudades.indexOf(new Ciudad(origen));
                    int j = ciudades.indexOf(new Ciudad(destino));
                    if (i == -1 || j == -1) System.out.println("Ciudad inválida");
                    else 
                    {
                        List<String> ruta = floyd.reconstruirRuta(i, j, ciudades);
                        System.out.println("Ruta: " + ruta);
                        System.out.println("Distancia: " + floyd.dist[i][j]);
                    }
                }
                case "2" -> 
                {
                    CentroGrafo cg = new CentroGrafo();
                    System.out.println("Centro: " + cg.calcularCentro(floyd, ciudades));
                }
                case "3" -> 
                {
                    System.out.println("a) Interrupción entre ciudades");
                    System.out.println("b) Nueva conexión");
                    System.out.println("c) Cambiar clima");
                    String opt = sc.nextLine();
                    System.out.print("Ciudad1: ");
                    String c1 = sc.nextLine();
                    System.out.print("Ciudad2: ");
                    String c2 = sc.nextLine();
                    switch (opt) 
                    {
                        case "a" -> grafo.eliminarArco(c1, c2);
                        case "b" -> 
                        {
                            int[] tiempos = new int[4];
                            for (Clima clima : Clima.values()) 
                            {
                                System.out.print("Tiempo con " + clima.name().toLowerCase() + ": ");
                                tiempos[clima.index] = Integer.parseInt(sc.nextLine());
                            }
                            grafo.agregarArco(c1, c2, tiempos);
                        }
                        case "c" -> 
                        {
                            System.out.print("Nuevo clima (normal, lluvia, nieve, tormenta): ");
                            grafo.setClima(Clima.fromString(sc.nextLine()));
                        }
                    }
                    floyd.calcular(grafo); // Recalcular
                }
                case "4" -> System.exit(0);
            }
        }
    }

    public static void cargarArchivo(String filename, Grafo grafo) throws IOException 
    {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String linea;
        while ((linea = reader.readLine()) != null) 
        {
            String[] partes = linea.split(" ");
            String c1 = partes[0], c2 = partes[1];
            int[] tiempos = Arrays.stream(Arrays.copyOfRange(partes, 2, 6)).mapToInt(Integer::parseInt).toArray();
            grafo.agregarCiudad(c1);
            grafo.agregarCiudad(c2);
            grafo.agregarArco(c1, c2, tiempos);
        }
        reader.close();
    }
}
