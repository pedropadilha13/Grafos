package com.pedropadilha.grafos.la;

import com.pedropadilha.grafos.ma.TGrafo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author pedropadilha13
 */
public class ALGraph {
    private final int n;
    private int m;
    private final ArrayList<ArrayList<Integer>> adj;


    public ALGraph(int n) {
        this.adj = new ArrayList<>(n);
        this.n = n;

        for (int i = 0; i < n; i++) {
            this.adj.add(new ArrayList<>());
        }
    }

    // Adiciona uma aresta (se não existir)
    public void insertEdge(int u, int v) {
        if (!this.adj.get(u).contains(v)) {
            this.adj.get(u).add(v);
            this.m++;
        }
    }

    // Remove uma aresta (se existir)
    public void removeEdge(int u, int v) {
        if (this.adj.get(u).contains(v)) {
            this.adj.get(u).remove((Integer) v);
            this.m--;
        }
    }

    // Exibe o grafo armazenado
    public void show() {
        System.out.println("Total de arestas: " + this.m);
        for (int i = 0; i < this.n; i++) {
            System.out.print(i + ": ");
            for (int j : this.adj.get(i)) {
                System.out.print(j + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    // Retorna o grau de entrada do vértice
    public int inDegree(int v) {
        int degree = 0;

        for (ArrayList<Integer> list : this.adj) {
            if (list.contains(v)) {
                degree++;
            }
        }

        return degree;
    }

    // Retorna o grau de saída do vértice
    public int outDegree(int v) {
        return this.adj.get(v).size();
    }

    // Retorna o grau do vértice
    public int getDegree(int v) {
        return this.inDegree(v);
    }

    // Inverte as listas dos vértices adjacentes
    public void reverseEdges() {
        for (ArrayList<Integer> l : this.adj) {
            Collections.reverse(l);
        }
    }

    // Retorna se o vértice v é uma fonte
    public boolean isSource(int v) {
        return this.inDegree(v) == 0 && this.outDegree(v) > 0;
    }

    // Retorna se o vértice v é um sorvedouro
    public boolean isSink(int v) {
        return this.inDegree(v) > 0 && this.outDegree(v) == 0;
    }

    // Remove o vértice v
    public void removeVertex(int v) {
        for (int i = 0; i < this.n; i++) {
            this.removeEdge(i, v);
            this.removeEdge(v, i);
        }
    }

    // Retorna se o grafo é completo
    public boolean isComplete() {
        int totalPossibilities = (this.n * this.n) - this.n;
        return this.m == totalPossibilities;
    }

    // Verifica se os grafos são iguais e retorna true em caso positivo
    public static boolean compareGraphs(ALGraph graph1, ALGraph graph2) {
        if (graph1.n != graph2.n) {
            return false;
        }

        for (int i = 0; i < graph1.n; i++) {
            ArrayList<Integer> l1 = graph1.adj.get(i);
            ArrayList<Integer> l2 = graph2.adj.get(i);

            if (l1.size() != l2.size() || !l1.containsAll(l2)) {
                return false;
            }
        }

        return true;
    }

    // Retorna se um grafo é simétrico
    public static boolean isSymmetric(ALGraph graph) {
        for (int i = 0; i < graph.n; i++) {
            ArrayList<Integer> l = graph.adj.get(i);

            for (int v : l) {
                if (!graph.adj.get(v).contains((Integer) i)) {
                    return false;
                }
            }
        }

        return true;
    }

    // Retorna o grafo criado com as especificações de um arquivo
    public static ALGraph createFromFile(String path) {
        try {
            File graphFile = new File(path);
            Scanner fileReader = new Scanner(graphFile);

            int verticesCount = fileReader.nextInt();

            ALGraph graph = new ALGraph(verticesCount);

            int edgeCount = fileReader.nextInt();

            int read = 0;
            while (read++ < edgeCount) {
                int v = fileReader.nextInt();
                int w = fileReader.nextInt();
                graph.insertEdge(v, w);
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
