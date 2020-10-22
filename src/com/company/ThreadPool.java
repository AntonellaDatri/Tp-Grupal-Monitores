package com.company;


import javafx.util.Pair;

public class ThreadPool {

        Buffer buffer;
        int worker;

        public ThreadPool (Buffer b, int t, int d, String c) {
            buffer = b;
            worker = t;
        }

        public  void init(){
            for (int i = 0; i < worker; ++i){
                new PowWorker(buffer).start();
            }
        }

        public void launch (Pair<Integer,Integer> tarea){ //la tarea es el rango de valores a analizar
            buffer.push(tarea);

        }

        public void stop(){
            for (int i = 0; i < worker; ++i){
                buffer.push(new PoisonPill());
            }
        }

}
