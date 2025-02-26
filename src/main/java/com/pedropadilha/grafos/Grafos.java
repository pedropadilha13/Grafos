package com.pedropadilha.grafos;

/**
 *
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
        // g.show();
        
        
        /**
         * 
         * Exercício 1
         * 
         * Escreva um método “int inDegree(int v)” que calcula e retorna o grau de entrada de um vértice v de um grafo dirigido. O método deve ser implementado na classe TGrafo da matriz  de adjacência. Obs.: Grau de entrada de v é o total de arestas que chegam no vértice v. 
         * 
         */
        
//        System.out.println("Grau de entrada do vértice 0: " + g.inDegree(0));
//        System.out.println("Grau de entrada do vértice 1: " + g.inDegree(1));
//        System.out.println("Grau de entrada do vértice 2: " + g.inDegree(2));
//        System.out.println("Grau de entrada do vértice 3: " + g.inDegree(3));

        /**
         * 
         * Exercício 2
         * 
         * Escreva o método outDegree(int v) que calcula o grau de saída de **v** em grafo dirigido. O método deve ser implementado na classe TGrafo que usa matriz de adjacência. Obs.: Grau de saída de v é o total de arestas que saem do vértice v.
         * 
         * 
         */
        
//        System.out.println("Grau de saída do vértice 0: " + g.outDegree(0));
//        System.out.println("Grau de saída do vértice 1: " + g.outDegree(1));
//        System.out.println("Grau de saída do vértice 2: " + g.outDegree(2));
//        System.out.println("Grau de saída do vértice 3: " + g.outDegree(3));
        
        /**
         * 
         * Exercício 3
         * 
         * Fazer o método degree(int v) que calcula o grau do vértice de um grafo dirigido. O método deve ser implementado na classe TGrafo que usa matriz de adjacência.

         * 
         */
        
        System.out.println("Grau do vértice 0: " + g.degree(0));   
    }
}
