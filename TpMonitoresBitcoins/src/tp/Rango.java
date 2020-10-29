package tp;

import java.util.stream.IntStream;

public class Rango implements Tareas{
	private IntStream rango;
	
	@Override
	public void run() {
		//no hace nada
		
	}
	@Override
	public IntStream getStream() {
		return rango;
	}
	public void setStream(IntStream rango) {
		this.rango = rango;
	}

}
