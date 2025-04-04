package com.pedropadilha.grafos.ma;

/**
 * @author pedropadilha13
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
            System.out.println();
            for (int j = 0; j < n; j++) {
                System.out.print(adj[i][j] + " ");
            }
        }
        System.out.println();
    }

    public void removeV(int v) {
        for (int i = 0; i < this.n; i++) {
            this.removeA(v, i);
        }
    }

    // Retorna o grau do vértice v
    public int degree(int v) {
        int degree = 0;

        for (int i = 0; i < this.n; i++) {
            degree += this.adj[v][i];
        }

        return degree;
    }

    // Retorna se o grafo é completo
    public boolean isComplete() {
        int totalPossibilities = this.n * (this.n - 1) / 2;
        return this.m == totalPossibilities;
    }

    // Retorna o complemento do grafo
    public TGrafoND getComplement() {
        TGrafoND complement = new TGrafoND(this.n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (this.adj[i][j] == 0) {
                    complement.insereA(i, j);
                }
            }
        }

        return complement;
    }

    public void dfs(int start, boolean[] visited) {
        visited[start] = true;

        for (int i = 0; i < this.n; i++) {
            if (adj[start][i] == 1 && !visited[i]) {
                this.dfs(i, visited);
            }
        }
    }


    public boolean isDisconnected() {
        int i = 1;
        while (i < this.n) {
            boolean[] visits = new boolean[this.n];
            this.dfs(i, visits);

            for (boolean b : visits) {
                if (!b) {
                    return true;
                }
            }

            i++;
        }

        return false;
    }
}