package com.pedropadilha.grafos;

/**
 * @author pedropadilha
 */
public class Grafos {

    public static void main(String[] args) {
        //  chama o construtor para criar um grafo 4x4
        TGrafo g = new TGrafo(4);
        //insere as arestas do grafo
        //A={(0,1),(0,2),(2,1),(2,3),(1,3)}
        g.insereA(0, 1);
        g.insereA(0, 2);
        g.insereA(2, 1);
        g.insereA(2, 3);
        g.insereA(1, 3);
        // mostra o grafo preenchido
        g.show();


        /*
         * Exercício 1
         * Escreva um método “int inDegree(int v)” que calcula e retorna o grau de entrada de um vértice v de um grafo
         * dirigido. O método deve ser implementado na classe TGrafo da matriz  de adjacência. Obs.: Grau de entrada de
         * v é o total de arestas que chegam no vértice v.
         */
        System.out.println("\nExercício 1");

        System.out.println("Grau de entrada do vértice 0: " + g.inDegree(0));
        System.out.println("Grau de entrada do vértice 1: " + g.inDegree(1));
        System.out.println("Grau de entrada do vértice 2: " + g.inDegree(2));
        System.out.println("Grau de entrada do vértice 3: " + g.inDegree(3));


        /*
         * Exercício 2
         * Escreva o método outDegree(int v) que calcula o grau de saída de **v** em grafo dirigido. O método deve ser
         * implementado na classe TGrafo que usa matriz de adjacência. Obs.: Grau de saída de v é o total de arestas que
         * saem do vértice v.
         */
        System.out.println("\nExercício 2");

        System.out.println("Grau de saída do vértice 0: " + g.outDegree(0));
        System.out.println("Grau de saída do vértice 1: " + g.outDegree(1));
        System.out.println("Grau de saída do vértice 2: " + g.outDegree(2));



        /*
         * Exercício 3
         * Fazer o método degree(int v) que calcula o grau do vértice de um grafo dirigido. O método deve ser
         * implementado na classe TGrafo que usa matriz de adjacência.
         */
        System.out.println("\nExercício 3");

        System.out.println("Grau do vértice 0: " + g.degree(0));
        System.out.println("Grau do vértice 1: " + g.degree(1));
        System.out.println("Grau do vértice 2: " + g.degree(2));



        /*
         * Exercício 4
         * Escreva um método para um grafo direcionado que recebe um vértice como parâmetro e retorne 1 se vértice for
         * uma fonte (grau de saída maior que zero e grau de entrada igual a 0), ou 0 caso contrário. O método deve
         * ser implementado para a classe TGrafo como matriz de adjacência.
         */
        System.out.println("\nExercício 4");

        System.out.printf("O vértice %d%s é uma fonte.\n", 0, g.isSource(0) ? "" : " não");
        System.out.printf("O vértice %d%s é uma fonte.\n", 1, g.isSource(1) ? "" : " não");
        System.out.printf("O vértice %d%s é uma fonte.\n", 2, g.isSource(2) ? "" : " não");
        System.out.printf("O vértice %d%s é uma fonte.\n", 3, g.isSource(3) ? "" : " não");



        /*
         * Exercício 5
         * Escreva um método para um grafo direcionado que recebe um vértice como parâmetro, retorne 1 se vértice for um
         * sorvedouro (grau de entrada maior que zero e grau de saída igual a 0), ou 0 caso contrário. O método deve
         * ser implementado para a classe TGrafo que utiliza matriz de adjacência.
         */
        System.out.println("\nExercício 5");

        System.out.printf("O vértice %d%s é um sorvedouro.\n", 0, g.isSink(0) ? "" : " não");
        System.out.printf("O vértice %d%s é um sorvedouro.\n", 1, g.isSink(1) ? "" : " não");
        System.out.printf("O vértice %d%s é um sorvedouro.\n", 2, g.isSink(2) ? "" : " não");
        System.out.printf("O vértice %d%s é um sorvedouro.\n", 3, g.isSink(3) ? "" : " não");


        /*
         * Exercício 6
         * Escreva um método que receba um grafo dirigido como parâmetro e retorna 1 se o grafo for simétrico e 0 caso
         * contrário. O método deve ser implementado  para a classe TGrafo que utiliza matriz de adjacência.
         */
        System.out.println("\nExercício 6");

        final int[][] grafoSimetrico = {
                {0, 1, 0, 1},
                {1, 1, 1, 1},
                {0, 1, 0, 1},
                {1, 1, 1, 1}
        };

        final int[][] grafoAssimetrico = {
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1},
                {1, 0, 0, 0}
        };

        System.out.println("O grafo é " + (TGrafo.isSymmetric(grafoSimetrico) ? "simétrico" : "assimétrico"));
        System.out.println("O grafo é " + (TGrafo.isSymmetric(grafoAssimetrico) ? "simétrico" : "assimétrico"));


        /*
         * Exercício 7
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
         * aresta, podemos dizer que o arquivo define um grafo com vértices 0..V-1. Escreva um método que receba um nome
         * de arquivo com o formato acima e construa a representação do grafo como matriz de adjacência.
         */
        System.out.println("\nExercício 7");

        TGrafo fileGraph = TGrafo.createFromFile("example.graph");

        if (fileGraph != null) {
            fileGraph.show();
        }


        /*
         * Exercício 8
         * Criar uma outra classe TGrafoND e modifique as funções insereA, removeA e show para representar um grafo
         * não-dirigido utilizando matriz de adjacência
         */
        System.out.println("\nExercício 8");

        TGrafoND ndGraph = new TGrafoND(4);
        ndGraph.insereA(0, 1);
        ndGraph.insereA(0, 2);
        ndGraph.insereA(2, 1);
        ndGraph.insereA(2, 3);
        ndGraph.insereA(1, 3);
        ndGraph.show();


        /*
         * Exercício 9
         * Fazer o método degree(int v) que calcula o grau do vértice de um grafo não-dirigido. O método deve ser
         * implementado na classe TGrafoND que usa matriz de adjacência.
         */
        System.out.println("\nExercício 9");

        System.out.println("Grau do vértice 0: " + ndGraph.degree(0));
        System.out.println("Grau do vértice 1: " + ndGraph.degree(1));
        System.out.println("Grau do vértice 2: " + ndGraph.degree(2));
        System.out.println("Grau do vértice 3: " + ndGraph.degree(3));


        /*
         * Exercício 10
         * Modifique a classe TGrafo e os métodos correspondentes para permitir a criação de um grafo direcionado
         * rotulado (valor float) nas arestas
         */
        System.out.println("\nExercício 10");

        TGrafoRotulado wg = new TGrafoRotulado(4);

        wg.insereA(0, 1, 0.0f);
        wg.insereA(1, 2, 1.0f);
        wg.insereA(2, 3, 2.0f);

        wg.show();


        /*
         * Exercício 11
         * Fazer um método que permita remover um vértice do Grafo (não dirigido e dirigido).
         * Não se esqueça de remover as arestas associadas.
         */
        System.out.println("\nExercício 11");

        System.out.println("--- Grafo dirigido ---");

        TGrafo grafo1 = new TGrafo(4);
        grafo1.insereA(0, 1);
        grafo1.insereA(1, 0);
        grafo1.insereA(1, 2);
        grafo1.insereA(1, 3);
        grafo1.insereA(2, 1);
        grafo1.insereA(2, 3);
        grafo1.show();

        System.out.println("Remover vértice 2 e exibir grafo novamente");
        grafo1.removeV(2);
        grafo1.show();

        System.out.println("--- Grafo não-dirigido ---");

        TGrafoND grafo2 = new TGrafoND(4);
        grafo2.insereA(0, 1);
        grafo2.insereA(0, 2);
        grafo2.insereA(1, 2);
        grafo2.insereA(2, 3);
        grafo2.show();

        System.out.println("Remover vértice 2 e exibir grafo novamente");
        grafo2.removeV(2);
        grafo2.show();


        /*
         * Exercício 12
         * Fazer um método que verifique e retorne se o grafo (não dirigido) é completo
         */
        System.out.println("\nExercício 12");

        TGrafoND completoND = new TGrafoND(3);
        completoND.insereA(0, 1);
        completoND.insereA(1, 2);
        completoND.insereA(2, 0);
        System.out.println("O grafo não direcionado é: " + (completoND.isComplete() ? "completo" : "incompleto"));

        TGrafoND incompletoND = new TGrafoND(3);
        incompletoND.insereA(0, 1);
        incompletoND.insereA(1, 2);
        System.out.println("O grafo não direcionado é: " + (incompletoND.isComplete() ? "completo" : "incompleto"));


        /*
         * Exercício 13
         * Fazer um método que verifique e retorne se o grafo (dirigido) é completo
         */
        System.out.println("\nExercício 13");

        TGrafo completoD = new TGrafo(3);
        completoD.insereA(0, 1);
        completoD.insereA(1, 0);
        completoD.insereA(1, 2);
        completoD.insereA(2, 1);
        completoD.insereA(2, 0);
        completoD.insereA(0, 2);
        System.out.println("O grafo direcionado é: " + (completoD.isComplete() ? "completo" : "incompleto"));

        TGrafo incompletoD = new TGrafo(3);
        incompletoD.insereA(0, 1);
        incompletoD.insereA(1, 2);
        System.out.println("O grafo direcionado é: " + (incompletoD.isComplete() ? "completo" : "incompleto"));


        /*
         * Exercício 14
         * Fazer um método que retorne o complemento (grafo complementar) de um grafo (dirigido ou não) na forma de
         * uma matriz de adjacência
         */
        System.out.println("\nExercício 14");




    }
}
