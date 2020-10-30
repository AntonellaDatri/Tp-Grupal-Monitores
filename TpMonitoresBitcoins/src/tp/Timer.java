package tp;

public class Timer {
	private int thread;
	private int cantThreadTerminados = 0;
	private long TInicio, TFin;
	
	public Timer(int thread, long inicio) {
		this.thread = thread;
		this.TInicio = inicio;
	}
	

	public synchronized void terminarThread() {
		cantThreadTerminados ++;
		notify();
	} 

	public synchronized void calcularSegundos() {
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
