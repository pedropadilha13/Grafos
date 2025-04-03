package com.pedropadilha.grafos.ma;

import java.util.ArrayList;

/**
 * @author pedropadilha
 */
public class ListaBusca {
    public static void main(String[] args) {
        TGrafo grafo = new TGrafo(8);
        grafo.insereA(0, 1);
        grafo.insereA(0, 2);
        grafo.insereA(0, 4);
        grafo.insereA(1, 3);
        grafo.insereA(1, 4);
        grafo.insereA(2, 5);
        grafo.insereA(2, 6);
        grafo.insereA(3, 7);
        grafo.insereA(4, 7);
        grafo.insereA(5, 4);
        grafo.insereA(5, 6);
        grafo.insereA(6, 7);


        System.out.println("DFS:");
        boolean[] visitedDFS = new boolean[8];
        grafo.dfs(0, visitedDFS);
        print(visitedDFS);

        System.out.println("\nBFS:");
        boolean[] visitedBFS = new boolean[8];
        ArrayList<Integer> order = grafo.bfs(0, visitedBFS);
        print(visitedBFS);
        System.out.println(order);

    }

    private static void print(boolean[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d: %svisitado\n", i, arr[i] ? "" : "não ");
        }
    }
}
