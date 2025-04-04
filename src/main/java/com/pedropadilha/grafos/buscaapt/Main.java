package com.pedropadilha.grafos.buscaapt;

import com.pedropadilha.grafos.ma.TGrafoRotuladoND;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author pedropadilha13
 */
public class Main {
    final static String FILE_NAME = "grafo.txt";
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        String selected;
        TGrafoRotuladoND graph = null;

        menu:
        while (true) {
            showMenu();
            selected = readString("Escolha: ");

            switch (selected) {
                case "1": // Carregar arquivo
                    graph = TGrafoRotuladoND.createFromFile(FILE_NAME);

                    if (graph == null) {
                        System.err.println("Erro ao carregar grafo.");
                    } else {
                        System.out.println("Arquivo carregado com sucesso!");
                    }

                    break;
                case "2": // Salvar arquivo
                    if (graph == null) {
                        nge();
                        break;
                    }

                    boolean success = graph.exportToFile(FILE_NAME);

                    if (success) {
                        System.out.println("Grafo exportado com sucesso!");
                    } else {
                        System.err.println("Erro ao exportar grafo.");
                    }

                    break;
                case "3": // Inserir novo vértice
                    if (graph == null) {
                        nge();
                        break;
                    }

                    String name = readString("Nome do novo vértice: ");
                    int id = graph.insertV(name);
                    if (id != -1) {
                        System.out.printf("ID do vértice adicionado: %d%n", id);
                    } else {
                        System.err.println("Não é possível adicionar um novo vértice.");
                    }

                    break;
                case "4": // Inserir nova aresta
                    if (graph == null) {
                        nge();
                        break;
                    }

                    Edge newEdge = readEdge(graph.vCount(), true);
                    graph.insert(newEdge);

                    break;
                case "5": // Remover vértice
                    if (graph == null) {
                        nge();
                        break;
                    }

                    int vToRemove = readInt("Vértice a ser removido: ");
                    graph.removeV(vToRemove);
                    System.out.printf("Vértice %d removido!%n", vToRemove);

                    break;
                case "6": // Remover aresta
                    if (graph == null) {
                        nge();
                        break;
                    }

                    Edge remove = readEdge(graph.vCount(), false);
                    graph.remove(remove.v, remove.u);
                    System.out.printf("Aresta %d-%d removida!%n", remove.v, remove.u);

                    break;
                case "7": // Exibir arquivo
                    printFile(FILE_NAME);
                    break;
                case "8": // Exibir grafo
                    if (graph == null) {
                        nge();
                        break;
                    }

                    graph.print();
                    break;
                case "9": // Info
                    if (graph == null) {
                        nge();
                        break;
                    }

                    System.out.println();

                    break;
                case "0":
                    break menu;
                default:
                    System.out.println("Escolha inválida.");
            }
        }

    }

    public static void showMenu() {
        System.out.println();
        System.out.println("1) Carregar arquivo");
        System.out.println("2) Salvar arquivo");
        System.out.println("3) Inserir novo vértice");
        System.out.println("4) Inserir nova aresta");
        System.out.println("5) Remover vértice");
        System.out.println("6) Remover aresta");
        System.out.println("7) Exibir arquivo");
        System.out.println("8) Exibir grafo");
        System.out.println("9) Informações");
        System.out.println("0) Sair");
    }

    public static void nge() {
        System.err.println("Nenhum grafo em memória!");
    }

    public static void printFile(String path) {
        try {
            File graphFile = new File(path);
            Scanner fileReader = new Scanner(graphFile);

            while (fileReader.hasNext()) {
                System.out.println(fileReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontado!");
        }
    }

    public static Edge readEdge(int n, boolean hasValue) {
        int v, w, value = -1;

        do {
            v = readInt("Origem: ");
        } while (v < 0 || v > n);

        do {
            w = readInt("Destino: ");
        } while (w < 0 || w > n);

        if (hasValue) {
            do {
                value = readInt("Valor: ");

            } while (value < 0);
        }

        return new Edge(v, w, value);
    }

    public static int readInt(String message) {
        System.out.print(message);
        return s.nextInt();
    }

    public static String readString(String message) {
        System.out.print(message);
        return s.nextLine();
    }
}

