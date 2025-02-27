package com.pedropadilha.grafos;

/**
 * @author pedropadilha
 */
public class TGrafoND {

    private final int n; // Quantidade de vértices
    private int m; // Quantidade de arestas
    private final int[][] adj; //matriz de adjacência

    public TGrafoND(int n) {
        this.n = n;
        this.m = 0;
        this.adj = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.adj[i][j] = 0;
            }
        }
    }

    // Cria a aresta (caso ela não exista) e incrementa o número total de arestas
    public void insereA(int v, int w) {
        if (adj[v][w] == 0) {
            adj[v][w] = 1;
            adj[w][v] = 1;
            m++;
        }
    }

    // Remove a aresta (caso ela exista) e decrementa o número total de arestas
    public void removeA(int v, int w) {
        if (adj[v][w] == 1) {
            adj[v][w] = 0;
            adj[w][v] = 0;
            m--;
        }
    }

    // Exibe o número total de vértices e arestas e imprime a matriz de adjacência
    public void show() {
        System.out.println("n: " + n);
        System.out.println("m: " + m);
        for (int i = 0; i < n; i++) {
            System.out.print("\n");
            for (int j = 0; j < n; j++) {
//                System.out.printf("adj[%d,%d]= %d ", i, j, adj[i][j]);
                System.out.print(adj[i][j] + " ");
            }
        }
    }
}