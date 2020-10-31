package tp;

public class Timer {
	private long TInicio, TFin;
	
	public Timer(long inicio) {
		this.TInicio = inicio;
	}

	public synchronized void calcularSegundos() {
		TFin = System.currentTimeMillis();
		long segundosTotales =  (TFin -TInicio);
		System.out.println("Cantidad de segundos tardados: " + segundosTotales);
	
	}

}
