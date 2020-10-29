package tp;

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
	    		Tareas rango =  buffer.pop();
	    		rango.run();
	    		verificarRangos(rango);
	        }
    	} catch (PoisonException e) {
        	System.out.println("Terminamos y no encotramos un nonce");
		}		
	}
	
	private void verificarRangos(Tareas rango) {
		System.out.println("Verificando rango");
		rango.getStream().forEach( num -> state.verificarNum(num));
		System.out.println("Finalizo verificacion");
	}
	
			
}		
	
