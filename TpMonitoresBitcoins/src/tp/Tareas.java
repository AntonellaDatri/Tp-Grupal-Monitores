package tp;

import java.util.stream.IntStream;

public interface Tareas extends Runnable{
	 public void run();

	public IntStream getStream();
	 
}
