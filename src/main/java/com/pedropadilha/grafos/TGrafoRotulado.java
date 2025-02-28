package com.pedropadilha.grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TGrafoRotulado {
    // Atributos Privados

    private final int n; // quantidade de vértices
    private int m; // quantidade de arestas
    private final float[][] adj; //matriz de adjacência

    public TGrafoRotulado(int n) {  // construtor
        this.n = n;

        this.m = 0;

        this.adj = new float[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.adj[i][j] = Float.MAX_VALUE;
            }
        }
    }

    // Insere a aresta v->w com peso l no grafo (se ela não existir) e atualiza a quantidade total de arestas
    public void insereA(int v, int w, float l) {
        if (adj[v][w] == Float.MAX_VALUE) {
            adj[v][w] = l;
            m++;
        }
    }

    // Remove a aresta v->w do grafo (se ela existir) e atualiza a quantidade total de arestas
    public void removeA(int v, int w) {
        if (adj[v][w] != Float.MAX_VALUE) {
            adj[v][w] = Float.MAX_VALUE;
            m--;
        }
    }

    // Exibe o número total de vértices e arestas e imprime a matriz de adjacência com os respectivos pesos das arestas
    public void show() {
        System.out.println("n: " + n);
        System.out.println("m: " + m);

        for (int i = 0; i < n; i++) {
            System.out.print("\n");
            for (int j = 0; j < n; j++) {
                String text = adj[i][j] == Float.MAX_VALUE ? " ∞ " : String.valueOf(adj[i][j]);
                System.out.print(text);
//                if (adj[i][w] == 1) {
//                    System.out.print("Adj[" +   i + "," + w + "]= 1" + " ");
//                } else {
//                    System.out.print("Adj[" + i + "," + w + "]= 0" + " ");
//                }
            }
        }
    }

    // Calcula e retorna o grau de entrada do vértice v
    public int inDegree(int v) {
        // Inicializa a contagem de incidências em v (0)
        int deg = 0;

        // Itera a matriz de adjacência para encontrar todas as incidências em v
        for (int i = 0; i < this.n; i++) {
            if (this.adj[i][v] == 1) {
                // Caso haja incidência em v, o contador do grau é incrementado em 1
                deg++;
            }
        }

        // Retorna o grau calculado
        return deg;
    }

    // Calcula e retorna o grau de saída do vértice v
    public int outDegree(int v) {
        // Inicializa a contagem de saídas de v (0)
        int deg = 0;

        // Itera a matriz de adjacência para encontrar todas as saídas de v
        for (int i = 0; i < this.n; i++) {
            if (this.adj[v][i] == 1) {
                // Caso haja saída de v, o contador do grau é incrementado em 1
                deg++;
            }
        }

        // Retorna o grau calculado
        return deg;
    }

    // Retorna o grau de incidência do vértice v
    public int degree(int v) {
        return this.outDegree(v);
    }

    // Retorna se o vértice v é uma fonte
    public boolean isSource(int v) {
        return this.inDegree(v) == 0 && this.outDegree(v) > 0;
    }

    // Retorna se o vértice v é um sorvedouro
    public boolean isSink(int v) {
        return this.inDegree(v) > 0 && this.outDegree(v) == 0;
    }

    // Retorna se o grafo é simétrico
    public static boolean isSymmetric(int[][] adj) {
        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < i; j++) {
                if (adj[i][j] != adj[j][i]) {
                    return false;
                }
            }
        }

        return true;
    }

//    public static TGrafoRotulado createFromFile(String path) {
//        try {
//            File graphFile = new File(path);
//            Scanner fileReader = new Scanner(graphFile);
//
//            int verticesCount = fileReader.nextInt();
//
//            TGrafoRotulado graph = new TGrafoRotulado(verticesCount);
//
//            int edgeCount = fileReader.nextInt();
//
//            int read = 0;
//            while (read++ < edgeCount) {
//                int v = fileReader.nextInt();
//                int w = fileReader.nextInt();
//                graph.insereA(v, w);
//            }
//
//            fileReader.close();
//            return graph;
//        } catch (FileNotFoundException e) {
//            System.err.println("Erro ao ler arquivo.");
//            // e.printStackTrace();
//        }
//
//        return null;
//    }
}
