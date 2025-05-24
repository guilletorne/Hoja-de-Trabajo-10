import networkx as nx

def leer_archivo(nombre_archivo, clima='normal'):
    clima_idx = {'normal': 2, 'lluvia': 3, 'nieve': 4, 'tormenta': 5}
    idx = clima_idx[clima]
    G = nx.DiGraph()

    with open(nombre_archivo, 'r') as archivo:
        for linea in archivo:
            partes = linea.strip().split()
            ciudad1 = partes[0]
            ciudad2 = partes[1]
            peso = int(partes[idx])
            G.add_edge(ciudad1, ciudad2, weight=peso)
    return G

def floyd_mas_corto(G):
    dist = dict(nx.floyd_warshall(G, weight='weight'))
    path = dict(nx.floyd_warshall_predecessor_and_distance(G, weight='weight')[0])
    return dist, path

def centro_del_grafo(G, distancias):
    excentricidades = {}
    for nodo in G.nodes:
        excentricidades[nodo] = max(distancias[nodo].values())
    centro = min(excentricidades, key=excentricidades.get)
    return centro

def mostrar_menu(G):
    distancias, _ = floyd_mas_corto(G)
    continuar = True

    while continuar:
        print("\n--- MENÚ ---")
        print("1. Ruta más corta entre ciudades")
        print("2. Centro del grafo")
        print("3. Cambiar clima")
        print("4. Salir")
        opcion = input("Seleccione una opción: ")

        if opcion == "1":
            origen = input("Ciudad origen: ")
            destino = input("Ciudad destino: ")
            try:
                path = nx.shortest_path(G, origen, destino, weight='weight')
                peso = nx.shortest_path_length(G, origen, destino, weight='weight')
                print("Ruta:", " -> ".join(path))
                print("Distancia:", peso)
            except (nx.NetworkXNoPath, nx.NodeNotFound):
                print("Ruta no disponible entre esas ciudades.")

        elif opcion == "2":
            centro = centro_del_grafo(G, distancias)
            print("Centro del grafo:", centro)

        elif opcion == "3":
            nuevo_clima = input("Ingrese nuevo clima (normal, lluvia, nieve, tormenta): ").lower()
            try:
                G = leer_archivo("guategrafo.txt", nuevo_clima)
                distancias, _ = floyd_mas_corto(G)
                print("Clima actualizado a:", nuevo_clima)
            except:
                print("Clima no válido.")

        elif opcion == "4":
            continuar = False

        else:
            print("Opción inválida.")

if _name_ == "_main_":
    grafo = leer_archivo("guategrafo.txt", clima='normal')
    mostrar_menu(grafo)