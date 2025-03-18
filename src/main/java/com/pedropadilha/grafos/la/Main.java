package com.pedropadilha.grafos.la;

import com.pedropadilha.grafos.ma.TGrafo;

/**
 * @author pedropadilha
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Lista de Adjacência – Grafos Direcionados e Não direcionados ---");

        ALGraph graph = new ALGraph(4);
        graph.insertEdge(0, 1);
        graph.insertEdge(0, 2);
        graph.insertEdge(1, 2);
        graph.insertEdge(1, 3);
        graph.insertEdge(2, 3);
        graph.insertEdge(3, 0);

        graph.show();


        /*
         * Exercício 19
         * Escreva um método “int inDegree(int v)” que calcula e retorna o grau de entrada de um vértice v de um grafo dirigido fazendo uso da lista de adjacência.
         */
        System.out.println("\nExercício 19");

        System.out.println("Grau de entrada do vértice 0: " + graph.inDegree(0));
        System.out.println("Grau de entrada do vértice 1: " + graph.inDegree(1));
        System.out.println("Grau de entrada do vértice 2: " + graph.inDegree(2));
        System.out.println("Grau de entrada do vértice 3: " + graph.inDegree(3));


        /*
         * Exercício 20
         * Escreva o método outDegree(int v) que calcula o grau de saída de v em grafo dirigido. fazendo uso da lista de adjacência.
         */
        System.out.println("\nExercício 20");

        System.out.println("Grau de saída do vértice 0: " + graph.outDegree(0));
        System.out.println("Grau de saída do vértice 1: " + graph.outDegree(1));
        System.out.println("Grau de saída do vértice 2: " + graph.outDegree(2));
        System.out.println("Grau de saída do vértice 3: " + graph.outDegree(3));

        /*
         * Exercício 21
         * Fazer o método degree(int v) que calcula o grau do vértice de um grafo dirigido fazendo uso da lista de adjacência.
         */
        System.out.println("\nExercício 21");

        System.out.println("Grau do vértice 0: " + graph.getDegree(0));
        System.out.println("Grau do vértice 1: " + graph.getDegree(1));
        System.out.println("Grau do vértice 2: " + graph.getDegree(2));
        System.out.println("Grau do vértice 3: " + graph.getDegree(3));

        /*
         * Exercício 22
         * Escreva um método que decida se dois grafos direcionados são iguais. O método deve ser implementado para a classe TGrafo faz uso da lista de adjacência.
         */
        System.out.println("\nExercício 22");

        ALGraph graph1 = new ALGraph(3);
        graph1.insertEdge(0, 1);
        graph1.insertEdge(1, 2);
        graph1.insertEdge(2, 0);

        ALGraph graph2 = new ALGraph(3);
        graph2.insertEdge(0, 1);
        graph2.insertEdge(1, 2);
        graph2.insertEdge(2, 0);

        ALGraph graph3 = new ALGraph(3);
        graph3.insertEdge(0, 1);
        graph3.insertEdge(1, 2);

        System.out.println("Os Grafos 1 e 2 são: " + (ALGraph.compareGraphs(graph1, graph2) ? "iguais" : "diferentes"));
        System.out.println("Os Grafos 1 e 3 são: " + (ALGraph.compareGraphs(graph1, graph3) ? "iguais" : "diferentes"));
        System.out.println("Os Grafos 2 e 3 são: " + (ALGraph.compareGraphs(graph2, graph3) ? "iguais" : "diferentes"));

        /*
         * Exercício 23
         * Escreva um método que converta uma representação de um grafo em outra. Por exemplo, converta um grafo armazenado em matriz de adjacência em uma lista de adjacência.
         */
        System.out.println("\nExercício 23");

        TGrafo mg = new TGrafo(3);
        mg.insereA(0, 1);
        mg.insereA(0, 2);
        mg.insereA(1, 2);

        ALGraph converted = mg.convert();

        converted.show();

        /*
         * Exercício 24
         * Escreva um método que receba um grafo armazenado em lista de adjacência e inverta a lista de adjacência de todos os vértices do grafo.  Por exemplo, se os 4 vizinhos de um certo vértice u aparecem na lista adj[u] na ordem v, w, x, y, então depois da aplicação do método a lista deve conter os mesmos vértices na ordem y, x, w, v. Obs.: Vizinhos são todos os vértices ligados ao vértice u.
         */
        System.out.println("\nExercício 24");

        ALGraph gti = new ALGraph(3);
        gti.insertEdge(0, 1);
        gti.insertEdge(0, 2);
        gti.insertEdge(1, 2);
        gti.insertEdge(1, 0);
        gti.insertEdge(2, 0);
        gti.insertEdge(2, 1);

        gti.show();
        gti.reverseEdges();
        System.out.println("Grafo com as adjacências invertidas:");
        gti.show();

        /*
         * Exercício 25
         * Escreva um método que receba um grafo e um vértice como parâmetro e retorne 1 se vértice for uma fonte (grau de saída maior que zero e grau de entrada igual a 0), ou 0 caso contrário. O método deve ser implementado para a classe TGrafo como lista de adjacência.
         */
        System.out.println("\nExercício 25");

        System.out.printf("O vértice %d%s é uma fonte.\n", 0, graph3.isSource(0) ? "" : " não");
        System.out.printf("O vértice %d%s é uma fonte.\n", 1, graph3.isSource(1) ? "" : " não");
        System.out.printf("O vértice %d%s é uma fonte.\n", 2, graph3.isSource(2) ? "" : " não");

        /*
         * Exercício 26
         * Escreva um método que receba um grafo e um vértice como parâmetro, retorne 1 se vértice for um sorvedouro (grau de entrada maior que zero e grau de saída igual a 0), ou 0 caso contrário. O método deve ser implementado para a classe TGrafo que utiliza lista de adjacência.
         */
        System.out.println("\nExercício 26");

        System.out.printf("O vértice %d%s é um sorvedouro.\n", 0, graph3.isSink(0) ? "" : " não");
        System.out.printf("O vértice %d%s é um sorvedouro.\n", 1, graph3.isSink(1) ? "" : " não");
        System.out.printf("O vértice %d%s é um sorvedouro.\n", 2, graph3.isSink(2) ? "" : " não");

        /*
         * Exercício 27
         * Escreva um método que receba um grafo dirigido como parâmetro e retorna 1 se o grafo for simétrico e 0 caso contrário. O método deve ser implementado  para a classe TGrafo que utiliza lista de adjacência.
         */
        System.out.println("\nExercício 27");

        ALGraph sym = new ALGraph(2);
        sym.insertEdge(0, 1);
        sym.insertEdge(1, 0);

        ALGraph notSym = new ALGraph(2);
        notSym.insertEdge(0, 1);

        System.out.println("O grafo é " + (ALGraph.isSymmetric(sym) ? "simétrico" : "assimétrico"));
        System.out.println("O grafo é " + (ALGraph.isSymmetric(notSym) ? "simétrico" : "assimétrico"));

        /*
         * Exercício 28
         * Um grafo pode ser armazenado em um arquivo com o seguinte formato:
         * 6
         * 8
         * 0 1
         * 0 5
         * 1 0
         * 1 5
         * 2 4
         * 3 1
         * 4 3
         * 3 5
         * Onde na primeira linha contém um inteiro V (vértice), na segunda contém um inteiro A (arestas) e nas demais
         * linha contém dois inteiros pertencentes ao intervalo 0..V-1. Se interpretarmos cada linha do arquivo como uma
         * aresta, podemos dizer que o arquivo define um grafo com vértices 0..V-1.
         * Escreva um método que receba um nome de arquivo com o formato acima e construa a representação de  lista de
         * adjacência do grafo.
         */
        System.out.println("\nExercício 28");

        ALGraph fileGraph = ALGraph.createFromFile("example.graph");

        if (fileGraph != null) {
            fileGraph.show();
        }

        /*
         * Exercício 29
         * Fazer um método que permita remover um vértice do Grafo (não dirigido). Não se esqueça de remover as arestas
         * associadas.
         */
        System.out.println("\nExercício 29");

        ALGraphND ndGraph = new ALGraphND(4);

        ndGraph.insertEdge(0, 1);
        ndGraph.insertEdge(1, 2);
        ndGraph.insertEdge(2, 3);
        ndGraph.insertEdge(3, 0);

        ndGraph.show();
        ndGraph.removeVertex(3);
        ndGraph.show();

        /*
         * Exercício 30
         * Fazer um método que permita remover um vértice do Grafo (dirigido). Não se esqueça de remover as arestas
         * associadas.
         */
        System.out.println("\nExercício 30");

        ALGraph dGraph = new ALGraph(4);

        dGraph.insertEdge(0, 1);
        dGraph.insertEdge(1, 2);
        dGraph.insertEdge(2, 3);
        dGraph.insertEdge(3, 0);

        dGraph.show();
        dGraph.removeVertex(3);
        dGraph.show();

        /*
         * Exercício 31
         * Fazer um método que verifique se o grafo (dirigido ou não) é completo.
         */
        System.out.println("\nExercício 31");

        ALGraph dComplete = new ALGraph(2);
        dComplete.insertEdge(0, 1);
        dComplete.insertEdge(1, 0);

        ALGraph dIncomplete = new ALGraph(2);

        System.out.println("O grafo direcionado é: " + (dComplete.isComplete() ? "completo" : "incompleto"));
        System.out.println("O grafo direcionado é: " + (dIncomplete.isComplete() ? "completo" : "incompleto"));

        ALGraphND ndComplete = new ALGraphND(2);
        ndComplete.insertEdge(0, 1);

        ALGraphND ndIncomplete = new ALGraphND(2);

        System.out.println("O grafo não direcionado é: " + (ndComplete.isComplete() ? "completo" : "incompleto"));
        System.out.println("O grafo não direcionado é: " + (ndIncomplete.isComplete() ? "completo" : "incompleto"));
    }
}
