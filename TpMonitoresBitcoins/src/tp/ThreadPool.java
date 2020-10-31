package tp;

import java.util.ArrayList;

public class ThreadPool {
    private Buffer buffer;
    private ArrayList<PowWorker> powWorkers = new ArrayList<PowWorker>();
    private Timer timer;
	
    public ThreadPool (Buffer buffer, int workers, int dificultad, String caracters, Timer timer) {
        this.buffer = buffer;
        this.timer = timer;
        start(buffer, caracters, dificultad, timer, workers);
    }

    
    private void start(Buffer buffer, String caracters, int dificultad, Timer timer, int workers){
        for (int i = 0; i < workers; ++i){
            PowWorker pow = new PowWorker(buffer, caracters, dificultad, this, timer);
            powWorkers.add(pow);
        }
    }

    public void stop() {
    	powWorkers.forEach(pow -> enviarPoision());
	}
	 
    
    private void enviarPoision() {
    	PoisonPill poison = new PoisonPill();
		buffer.push(poison);;
	}


	public void pararWorkers(){
    	FindNonceState state = new Encontrado();	
    	powWorkers.forEach(pow -> pow.setState(state));
    	timer.calcularSegundos();
    }
}
