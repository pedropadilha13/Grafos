package com.pedropadilha.grafos.ma;

public class Dijkstra {
    public static void main(String[] args) {
        TGrafoRotuladoND grafo = new TGrafoRotuladoND(4);

        grafo.insert(0, 0, 0);
        grafo.insert(0, 1, 20);
        grafo.insert(0, 2, 30);
        grafo.insert(0, 3, 50);
        grafo.insert(1, 1, 0);
        grafo.insert(1, 2, 40);
        grafo.insert(1, 3, 15);
        grafo.insert(2, 2, 0);
        grafo.insert(2, 3, 15);
        grafo.insert(3, 3, 0);

        grafo.dijkstra(3);
    }
}
