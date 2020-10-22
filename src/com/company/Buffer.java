package com.company;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.concurrent.RunnableFuture;

public class Buffer {

    ArrayList<Object>  buffer;
    int write= 0;
    int read= 0;
    int espacio;

    public Buffer(int espacio) {
        buffer = new ArrayList<Object>(espacio+1);
        this.espacio = espacio;
    }

    public Object pop() {
        while ( isFull() ) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Object result = buffer.get(read);
        read = read + 1 % espacio;
        notifyAll ();
        return result;
    }
    public void push(Object pair )  {

        while ( isEmpty() )
            try{
                wait ();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        buffer.set(write, pair);
        write = write + 1 % espacio;
        notifyAll ();
    }

    public boolean isEmpty(){
        return write == read;
    }
    public boolean isFull(){
        return write + 1 % espacio == read;
    }

}
