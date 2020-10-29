package tp;

import java.util.stream.IntStream;

public interface Tareas extends Runnable{
	 //IntStream stream;
	 public void run();

	public IntStream getStream();
	 
	 
	 /*public void guardarStream(IntStream s) {
		 stream = s;
	 }
	 
	 public IntStream getStream() {
		 return stream;
	 }*/
}
