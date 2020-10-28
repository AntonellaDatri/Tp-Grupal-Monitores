package tp;

public class Buffer {

	private int size = 0;
	private Tareas[] data;
	private int begin = 0, end = 0;
	public Buffer(int size) {
		this.size = size;
		this.data = new Tareas[this.size +1];
		
	}
	
	public synchronized void push( Tareas o ) {
		while (isFull()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		data[begin] = o;
		begin = next(begin);
		notifyAll();
	}
	
	public synchronized Tareas pop() {
		while (isEmpty()) {
			try {
				wait ();
			}
			catch (InterruptedException  e) {
				e.printStackTrace();
			}
		}
		Tareas result = data [end];
		end = next (end);
		notifyAll();
		return result;
	}
	
	private boolean isEmpty () { return begin == end ; }
	private boolean isFull () { return next ( begin ) == end ; }
	private int next ( int i) { return (i +1) %( size +1); }
}
