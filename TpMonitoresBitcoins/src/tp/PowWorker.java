package tp;

import java.util.stream.IntStream;

public class PowWorker extends Thread {
	private Buffer buffer;
	public int id = 0;
	private FindNonceState state;
	private Contador contador;

	public void setState(FindNonceState state) {
		this.state = state;
	}

	public PowWorker(Buffer buff, String caracteres, int dificultad, ThreadPool threadPool, Contador contador) {
        buffer = buff;
        this.contador = contador;
        this.state = new Buscando(caracteres, dificultad, threadPool); 
	}
	
    public void run(){
    	try {
	        while (true) {  	
	    		Tareas tarea = buffer.pop();
	    		tarea.run();
	    		verificarRangos(tarea);
	        }
    	} catch (PoisonException e) {
    		contador.escribir();
        	System.out.println("Termina thread");
		}		
	}
	
	private void verificarRangos(Tareas tarea) {
		IntStream rango = tarea.getStream();
		rango.forEach( num -> state.verificarNum(num));
	}
	
			
}		
	
