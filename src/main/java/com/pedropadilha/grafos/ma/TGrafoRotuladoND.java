package com.pedropadilha.grafos.ma;

import com.pedropadilha.grafos.buscaapt.Edge;
import com.pedropadilha.grafos.buscaapt.UnsupportedGraphTypeException;
import dnl.utils.text.table.TextTable;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class TGrafoRotuladoND {
    private final int n;
    private int edgeCount;
    private final Integer[][] adj;
    private final String[] vNames;

    public TGrafoRotuladoND(int n) {
        this.n = n;
        this.edgeCount = 0;
        this.adj = new Integer[n][n];
        this.vNames = new String[this.n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.adj[i][j] = null;
            }
        }
    }

    public int vCount() {
        return this.n;
    }

    public void print() {

        Object[][] data = new Object[this.n][];

        for (int i = 0; i < this.n; i++) {
            Object[] row = new Object[this.n + 1];
            row[0] = this.vNames[i];

            for (int j = 0; j < this.n; j++) {
                row[j + 1] = this.adj[i][j];
            }

            data[i] = row;
        }

        TextTable tt = new TextTable(vNames, data);

        tt.printTable();
    }

    public void insert(int v, int u, int w) {
        if (this.adj[v][u] == null) {
            this.adj[v][u] = w;
            this.adj[u][v] = w;
            this.edgeCount++;
        }
    }

    public void remove(int v, int u) {
        if (adj[v][u] != null) {
            adj[v][u] = null;
            adj[u][v] = null;
            this.edgeCount--;
        }
    }

    public void insert(Edge e) {
        this.insert(e.v, e.u, e.value);
    }

    public void removeV(int v) {
        for (int i = 0; i < this.n; i++) {
            this.remove(v, i);
        }

        this.vNames[v] = null;
    }

    public int insertV(String name) {
        for (int i = 0; i < this.n; i++) {
            if (this.vNames[i] == null) {
                this.vNames[i] = name;
                return i;
            }
        }

        return -1;
    }

    public void setName(int v, String name) {
        this.vNames[v] = name;
    }

    public void dijkstra(int start) {
        boolean[] visited = new boolean[this.n];
        int[] routes = new int[this.n];
        int[] distance = new int[this.n];
        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[start] = 0;

        for (int i = 0; i < this.n; i++) {

            int min = Integer.MAX_VALUE;
            int minV = -1;

            for (int j = 0; j < distance.length; j++) {
                if (!visited[j] && distance[j] < min) {
                    min = distance[j];
                    minV = j;
                }
            }

            visited[minV] = true;

            for (int v = 0; v < this.n; v++) {
                int currentDistance = distance[minV] + this.adj[minV][v];
                if (!visited[v] && this.adj[minV][v] != 0 && (currentDistance < distance[v])) {
                    distance[v] = currentDistance;
                    routes[v] = minV;
                }
            }
        }

        for (int i = 0; i < distance.length; i++) {
            System.out.printf("Distância de %s até %s: %s | caminho %s\n", start, i, distance[i], getPath(start, i, routes));
        }
    }

    public String getPath(int start, int i, int[] routes) {
        int currentIndex = i;

        ArrayList<String> path = new ArrayList<>();
        path.add(String.valueOf(i));

        while (currentIndex != start) {
            path.add(String.valueOf(routes[currentIndex]));
            currentIndex = routes[currentIndex];
        }

        StringJoiner joiner = new StringJoiner(" -> ");
        for (String v : path) {
            joiner.add(v);
        }

        return joiner.toString();
    }

    public static TGrafoRotuladoND createFromFile(String path) {
        try {
            File graphFile = new File(path);
            Scanner fileReader = new Scanner(graphFile);

            int type = fileReader.nextInt();

            if (type != 2) {
                throw new UnsupportedGraphTypeException("Somente são aceitos grafos do tipo 2 (não orientados com pesos nas arestas");
            }

            int verticesCount = fileReader.nextInt();

            TGrafoRotuladoND graph = new TGrafoRotuladoND(verticesCount);

            int read = 0;

            while (read++ < verticesCount) {
                String[] line = fileReader.nextLine().split(" ");

                int vertex = Integer.parseInt(line[0]);
                String name = line[1];

                graph.setName(vertex, name);
            }

            int edgeCount = fileReader.nextInt();

            read = 0;
            while (read++ < edgeCount) {
                int v = fileReader.nextInt();
                int u = fileReader.nextInt();
                int w = fileReader.nextInt();
                graph.insert(v, u, w);
            }

            fileReader.close();
            return graph;
        } catch (FileNotFoundException e) {
            System.err.println("Erro ao ler arquivo.");
        } catch (UnsupportedGraphTypeException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public boolean exportToFile(String path) {
        try {
            File graphFile = new File(path);
            boolean deleted = graphFile.delete();

            if (!deleted) {
                throw new Exception("Erro ao deletar arquivo.");
            }

            FileWriter fileWriter = new FileWriter(path);
            PrintWriter writer = new PrintWriter(fileWriter);

            writer.println("2");
            writer.println(this.n);

            for (int i = 0; i < this.n; i++) {
                writer.printf("%d %s%n", i, this.vNames[i]);
            }

            writer.println(this.edgeCount);

            for (int i = 0; i < this.n; i++) {
                for (int j = i + 1; j < this.n; j++) {
                    writer.printf("%d %d %d%n", i, j, this.adj[i][j]);
                }
            }

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

}
