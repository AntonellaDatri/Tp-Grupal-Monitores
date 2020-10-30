package tp;

public class Contador {
	private int thread;
	private int cantThreadTerminados = 0;
	private long TInicio, TFin;
	
	public Contador(int thread, long inicio) {
		this.thread = thread;
		this.TInicio = inicio;
	}
	

	public synchronized void escribir() {
		cantThreadTerminados ++;
		notify();
	} 

	public synchronized void imprimir() {
		while (cantThreadTerminados < thread){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
					
		}
		TFin = System.currentTimeMillis();
		long segundosTotales =  (TFin -TInicio) / 1000;
		System.out.println("Cantidad de segundos tardados: " + segundosTotales);
	
	}

}
