package tp;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.IntStream;

public class PowWorker extends Thread {
	private Buffer buffer;
	private int dificultad;
	private ThreadPool threadPool; 
	private String caracteres;
	public int id = 0;
	private boolean encontroNonce = false;

	public PowWorker(Buffer buff, String c, int dificultad, ThreadPool threadPool) {
        buffer = buff;
        caracteres = c;
        this.dificultad = dificultad;
        this.threadPool = threadPool;
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
		rango.getStream().forEach( num -> verificarNum(num));
		System.out.println("Finalizo verificacion");
	}
	
	private void verificarNum(int num) {
		if (!encontroNonce) {
			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance("SHA-256");
				byte[] caracteresToBytes = caracteres.getBytes();
				byte[] numToBytes = BigInteger.valueOf(num).toByteArray();
				ByteArrayOutputStream output = new ByteArrayOutputStream();
				output.write(caracteresToBytes);
				output.write(numToBytes);
				byte[] hashable = output.toByteArray();
	        	byte[] hash = digest.digest(hashable);
	        	verificarDificultad(hash, num);
			} catch (NoSuchAlgorithmException | IOException e) {
				e.printStackTrace();
			} 
		}
	}
	
	private void verificarDificultad(byte[] hash, int num) {
		boolean verificacion = true;		
		for (int i = 0; i < dificultad; i++) {
			verificacion = verificacion && hash[i] == 0;
		}
		if (verificacion) {
			this.encontroNonce = true;
			System.out.println("Se encontro el nonce: " + num);
/*			byte [] bytes =  BigInteger.valueOf(num).toByteArray();
			for (int i = 0; i < bytes.length; i++ ) {
				System.out.println("Se encontro el nonce: " + bytes[i]);
			}*/
			threadPool.stop();
		}
	}
	
	public boolean isEncontroNonce() {
		return encontroNonce;
	}

	public void setEncontroNonce(boolean encontroNonce) {
		this.encontroNonce = encontroNonce;
	}
			
}		
	
