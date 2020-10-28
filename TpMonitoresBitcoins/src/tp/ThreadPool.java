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
        for (int i = 0; i < worker; ++i){
            PowWorker pow = new PowWorker(buffer, caracters, dificultad, this);
            pow.id = i;
            powWorkers.add(pow);
            pow.start();
        }
    }

    public  void terminarThreads() {
    	for (int i = 0; i<worker +1; i++) {
			PoisonPill poison = new PoisonPill();
			buffer.push(poison);
		}
	}
	 
    public void stop(){
    	for (PowWorker pow : powWorkers) {
    		pow.setEncontroNonce(true);
    	}
    }
}
