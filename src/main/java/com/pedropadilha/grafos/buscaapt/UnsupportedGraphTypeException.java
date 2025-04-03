package com.pedropadilha.grafos.buscaapt;

public class UnsupportedGraphTypeException extends Exception {
    public UnsupportedGraphTypeException() {
    }

    public UnsupportedGraphTypeException(String message) {
        super(message);
    }
}
