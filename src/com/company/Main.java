package com.company;

public class Main {

    public static void main(String[] args) { //Threads: 1, 2, 4, 6, 8 y 10
        Buffer buffer = new Buffer(2);
        int thread = Integer.parseInt(args[0]);
        int dificultad = Integer.parseInt(args[1]);
        String caracteres = args[2];
        System.out.println(thread + dificultad + caracteres);
        ThreadPool threadPool = new ThreadPool(buffer, thread, dificultad, caracteres);
     }
}





