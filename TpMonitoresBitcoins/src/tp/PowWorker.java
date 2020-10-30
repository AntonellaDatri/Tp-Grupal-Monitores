package tp;

import java.util.stream.IntStream;

public class PowWorker extends Thread {
	private FindNonceState state;
	

	public PowWorker(Buffer buffer, String caracteres, int dificultad, ThreadPool threadPool, Timer timer) {
        this.state = new Buscando(caracteres, dificultad, threadPool);
        start(timer, buffer);
	}
	
	
    private void start(Timer timer, Buffer buffer){
    	try {
	        while (true) {  	
	    		Tareas tarea = buffer.pop();
	    		tarea.run();
	    		verificarRangos(tarea);
	        }
    	} catch (PoisonException e) {
    		timer.terminarThread();
		}		
	}
    
	
	private void verificarRangos(Tareas tarea) {
		IntStream rango = tarea.getStream();
		rango.forEach( num -> state.verificarNum(num));
	}
	
	
	public void setState(FindNonceState state) {
		this.state = state;
	}		
}		
	
