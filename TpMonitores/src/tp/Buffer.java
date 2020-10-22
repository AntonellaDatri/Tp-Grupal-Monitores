package tp;

class Buffer {
	
	private int espacio = 0;
	private Object[] data;
	private int begin = 0, end = 0;
	public Buffer(int size) {
		this.espacio = size;
		this.data = new Object[this.espacio +1];
		
	}
	public synchronized void push( Object o ) {
		while ( isFull()) {
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
	
	public synchronized Object pop() {
		while (isEmpty()) {
			try {
				wait ();
			}
			catch (InterruptedException  e) {
				e.printStackTrace();
			}
		}
		Object result = data [ end ];
		end = next ( end );
		notifyAll();
		return result ;
	}
	
	private boolean isEmpty () { return begin == end ; }
	private boolean isFull () { return next ( begin ) == end ; }
	private int next ( int i) { return (i +1) %( espacio +1); }
}