package com.pedropadilha.grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
                    System.out.print("[" + i + "," + w + "]= 1" + " ");
                } else {
                    System.out.print("[" + i + "," + w + "]= 0" + " ");
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

    public TGrafo getComplement() {
        TGrafo complement = new TGrafo(this.n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
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
            if (this.adj[start][i] == 1 && !visited[i]) {
                this.dfs(i, visited);
            }
        }
    }

    public boolean reaches(int start, int destination) {
        if (this.adj[start][destination] == 1) {
            return true;
        }

        boolean[] visited = new boolean[this.n];
        return this.reaches(start, destination, visited);
    }

    public boolean reaches(int start, int destination, boolean[] visited) {
        visited[start] = true;

        if (this.adj[start][destination] == 1) {
            return true;
        }

        for (int i = 0; i < this.n; i++) {
            // System.out.printf("start: %d, destination: %d, current i: %d\n", start, destination, i);
            if (this.adj[start][i] == 1 && !visited[i]) {
                return this.reaches(i, destination, visited);
            }
        }

        return false;
    }

    public boolean reaches(ArrayList<Integer> starts, ArrayList<Integer> destinations) {
        boolean[] visited = new boolean[this.n];
        return this.reaches(starts, destinations, visited);
    }

    public boolean reaches(ArrayList<Integer> starts, ArrayList<Integer> destinations, boolean[] visited) {
        for (int start : starts) {
            for (int destination : destinations) {
                if (this.adj[start][destination] == 1) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean[] getReachableBy(int v) {
        boolean[] reachableBy = new boolean[this.n];
        this.getReachableBy(v, reachableBy);
        return reachableBy;
    }

    public void getReachableBy(int v, boolean[] reachableBy) {
        reachableBy[v] = true;

        for (int i = 0; i < this.n; i++) {
            if (this.adj[i][v] == 1 && !reachableBy[i]) {
                this.getReachableBy(i, reachableBy);
            }
        }
    }

    // Retorna a categoria de conexidade de um grafo direcionado
    public int getCategory() {
        boolean[][] vvisits = new boolean[this.n][];

        for (int i = 0; i < this.n; i++) {
            boolean[] visits = new boolean[this.n];
            dfs(i, visits);
            vvisits[i] = visits;
        }

        for (int i = 0; i < this.n; i++) {
            int j = 0;
            boolean pass = false;

            while (j < this.n) {
                if (j != i) {
                    pass = pass || vvisits[i][j] || vvisits[j][i];
                }

                j++;
            }

            if (!pass) {
                return 0;
            }
        }

        boolean isC2 = true;
        boolean isC3 = true;

        // check pair reachability
        for (int i = 0; i < this.n; i++) {
            for (int j = i; j < this.n; j++) {
                // check if pair (i,j) is reachable:

                isC2 = isC2 && vvisits[i][j];

                if (isC2) {
                    isC3 = isC3 && vvisits[j][i];
                }

            }
        }

        if (isC3) {
            return 3;
        } else if (isC2) {
            return 2;
        } else {
            return 1;
        }
    }

    public TGrafo getReducedGraph() {
        boolean[] reduced = new boolean[this.n];
        ArrayList<ArrayList<Integer>> components = new ArrayList<>();

        for (int i = 0; i < this.n; i++) {
            if (reduced[i]) {
                continue;
            }

            boolean[] reaches = new boolean[this.n];
            dfs(i, reaches);
            boolean[] isReachedBy = this.getReachableBy(i);

            ArrayList<Integer> intersection = new ArrayList<>();
            for (int j = 0; j < this.n; j++) {
                if (reaches[j] && isReachedBy[j]) {
                    intersection.add(j);
                }
            }

            components.add(intersection);
            for (int j : intersection) {
                reduced[j] = true;
            }
        }

        TGrafo reducedGraph = new TGrafo(components.size());

        System.out.println(components);

        for (int i = 0; i < components.size(); i++) {
            for (int j = 0; j < components.size(); j++) {
                if (i == j) {
                    continue;
                }

                if (reaches(components.get(i), components.get(j))) {
                    reducedGraph.insereA(i, j);
                }
            }
        }

        return reducedGraph;
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
