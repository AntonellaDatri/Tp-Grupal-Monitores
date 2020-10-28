package tp;

import java.util.stream.IntStream;

public class Tareas extends Thread{
	 IntStream stream;
	 public void run() {}
	 public void guardarStream(IntStream s) {
		 stream = s;
	 }
	 
	 public IntStream getStream() {
		 return stream;
	 }
}
