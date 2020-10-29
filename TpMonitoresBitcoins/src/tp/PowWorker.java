package tp;

import java.util.stream.IntStream;

public class PowWorker extends Thread {
	private Buffer buffer;
	public int id = 0;
	private FindNonceState state;
	

	public void setState(FindNonceState state) {
		this.state = state;
	}

	public PowWorker(Buffer buff, String caracteres, int dificultad, ThreadPool threadPool) {
        buffer = buff;
        this.state = new Buscando(caracteres, dificultad, threadPool); 
	}
	
    public void run(){
    	try {
	        while (true) {  		
	        	System.out.println("Entramos de nuevo");
	    		Tareas tarea =  buffer.pop();
	    		tarea.run();
	    		verificarRangos(tarea);
	        }
    	} catch (PoisonException e) {
        	System.out.println("Terminamos y no encotramos un nonce");
		}		
	}
	
	private void verificarRangos(Tareas tarea) {
		System.out.println("Verificando rango");
		IntStream rango = tarea.getStream();
		rango.forEach( num -> state.verificarNum(num));
		System.out.println("Finalizo verificacion");
	}
	
			
}		
	
