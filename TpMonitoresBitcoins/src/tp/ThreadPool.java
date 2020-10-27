package tp;

import java.util.ArrayList;

public class ThreadPool {

    Buffer buffer;
    int worker, dificultad;
    String caracters;
    ArrayList<PowWorker> powWorkers = new ArrayList<PowWorker>(); 
  
    
    public ThreadPool (Buffer b, int t, int d, String c) {
        buffer = b;
        worker = t;
        caracters = c;
        this.dificultad = d;
    }

    public void init(){
    	System.out.println("holaaaa");
        for (int i = 0; i < worker; ++i){
        	System.out.println("Soy el worker numero: " + i);
            PowWorker pow = new PowWorker(buffer, caracters, dificultad, this);
            powWorkers.add(pow);
            pow.start();
        }
    }

    public void launch (Object tarea){ //la tarea es el rango de valores a analizar
        buffer.push(tarea);

    }

    public void stop(){
    	for (PowWorker pow : powWorkers) {
    		pow.setEncontroNonce(true);
    	}
        for (int i = 0; i < worker; ++i){
            buffer.push(new PoisonPill());
        }
        
    }
}
