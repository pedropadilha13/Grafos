package com.pedropadilha.grafos.la;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ALGraphND {
    private final int n;
    private int m;
    private final ArrayList<ArrayList<Integer>> adj;


    public ALGraphND(int n) {
        this.adj = new ArrayList<>(n);
        this.n = n;

        for (int i = 0; i < n; i++) {
            this.adj.add(new ArrayList<Integer>(n));
        }
    }

    // Adiciona uma aresta (se não existir)
    public void insertEdge(int u, int v) {
        if (!this.adj.get(u).contains(v)) {
            this.adj.get(u).add(v);
            this.adj.get(v).add(u);
            this.m++;
        }
    }

    // Remove uma aresta (se existir)
    public void removeEdge(int u, int v) {
        if (this.adj.get(u).contains(v)) {
            this.adj.get(u).remove((Integer) v);
            this.adj.get(v).remove((Integer) u);
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

    // Remove o vértice v
    public void removeVertex(int v) {
        for (int i = 0; i < this.n; i++) {
            this.removeEdge(i, v);
        }
    }

    // Retorna se o grafo é completo
    public boolean isComplete() {
        int totalPossibilities = this.n * (this.n - 1) / 2;
        return this.m == totalPossibilities;
    }
}
