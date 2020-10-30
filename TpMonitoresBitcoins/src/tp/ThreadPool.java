package tp;

import java.util.ArrayList;

public class ThreadPool {
    private Buffer buffer;
    private int workers;
    private ArrayList<PowWorker> powWorkers = new ArrayList<PowWorker>();
    
	
    public ThreadPool (Buffer buffer, int workers, int dificultad, String caracters, Timer timer) {
        this.buffer = buffer;
        this.workers = workers;
        start(buffer, caracters, dificultad, timer);
    }

    
    private void start(Buffer buffer, String caracters, int dificultad, Timer timer){
        for (int i = 0; i < workers; ++i){
            PowWorker pow = new PowWorker(buffer, caracters, dificultad, this, timer);
            powWorkers.add(pow);
            pow.start();
        }
    }

    public  void stop() {
    	for (int i = 0; i<workers +1; i++) {
			PoisonPill poison = new PoisonPill();
			buffer.push(poison);
		}
	}
	 
    public void pararWorkers(){
    	FindNonceState state = new Encontrado();	
    	for (PowWorker pow : powWorkers) {
    		pow.setState(state);
    	}
    }
}
