package com.pedropadilha.grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author pedropadilha
 */
//definição de uma estrutura Matriz de Adjacência para armezanar um grafo
public class TGrafo {
    // Atributos Privados

    private final int n; // quantidade de vértices
    private int m; // quantidade de arestas
    private final int[][] adj; //matriz de adjacência
    // Métodos Públicos

    public TGrafo(int n) {  // construtor
        this.n = n;
        // No início dos tempos não há arestas
        this.m = 0;
        // alocação da matriz do TGrafo
        this.adj = new int[n][n];

        // Inicia a matriz com zeros
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.adj[i][j] = 0;
            }
        }
    }

    // Insere uma aresta no Grafo tal que
    // v é adjacente a w
    public void insereA(int v, int w) {
        // testa se nao temos a aresta
        if (adj[v][w] == 0) {
            adj[v][w] = 1;
            m++; // atualiza qtd arestas
        }
    }

    // remove uma aresta v->w do Grafo	
    public void removeA(int v, int w) {
        // testa se temos a aresta
        if (adj[v][w] == 1) {
            adj[v][w] = 0;
            m--; // atualiza qtd arestas
        }
    }
    // Apresenta o Grafo contendo
    // número de vértices, arestas
    // e a matriz de adjacência obtida	

    public void show() {
        System.out.println("n: " + n);
        System.out.println("m: " + m);
        for (int i = 0; i < n; i++) {
            System.out.print("\n");
            for (int w = 0; w < n; w++) {
                if (adj[i][w] == 1) {
                    System.out.print("Adj[" + i + "," + w + "]= 1" + " ");
                } else {
                    System.out.print("Adj[" + i + "," + w + "]= 0" + " ");
                }
            }
        }
        System.out.println("\n\nfim da impressao do grafo.");
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

    // Remove um vértice (todas as arestas relacionadas a ele)
    public void removeV(int v) {
        for (int i = 0; i < this.n; i++) {
            this.removeA(i, v);
            this.removeA(v, i);
        }
    }

    public boolean isComplete() {
        int totalPossibilities = (this.n * this.n) - this.n;
        return this.m == totalPossibilities;
    }

    public boolean getComplement() {
        TGrafo complement = new TGrafo(this.n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.adj[i][j] == 1) {

            }
        }
    }
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

public static TGrafo createFromFile(String path) {
    try {
        File graphFile = new File(path);
        Scanner fileReader = new Scanner(graphFile);

        int verticesCount = fileReader.nextInt();

        TGrafo graph = new TGrafo(verticesCount);

        int edgeCount = fileReader.nextInt();

        int read = 0;
        while (read++ < edgeCount) {
            int v = fileReader.nextInt();
            int w = fileReader.nextInt();
            graph.insereA(v, w);
        }

        fileReader.close();
        return graph;
    } catch (FileNotFoundException e) {
        System.err.println("Erro ao ler arquivo.");
        // e.printStackTrace();
    }

    return null;
}

}
