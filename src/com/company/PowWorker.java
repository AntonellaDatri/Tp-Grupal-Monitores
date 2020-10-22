package com.company;

import com.sun.org.apache.xpath.internal.objects.XObject;

public class PowWorker extends Thread{
    Buffer buffer;
    public PowWorker(Buffer buff) {
        buffer = buff;
    }


    public void run(){
        try {
            while (true) {
                Object rango = buffer.pop();
            }
        }
        catch (PoisonException e) {
            System.out.println("Me llego una poisonException");
        }



    }


}
