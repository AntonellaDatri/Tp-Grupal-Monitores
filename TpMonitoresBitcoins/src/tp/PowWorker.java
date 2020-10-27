package tp;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.IntStream;

public class PowWorker extends Thread {
	private Buffer buffer;
	private int dificultad;
	private ThreadPool threadPool; 
	private String caracteres;
	private boolean encontroNonce = false;
	
	
	
	public boolean isEncontroNonce() {
		return encontroNonce;
	}

	public void setEncontroNonce(boolean encontroNonce) {
		this.encontroNonce = encontroNonce;
	}

	public PowWorker(Buffer buff, String c, int dificultad, ThreadPool threadPool) {
        buffer = buff;
        caracteres = c;
        this.dificultad = dificultad;
        this.threadPool = threadPool;
	}
	
	    public void run(){
	            while (true) {
	            	System.out.println("Inicialice PowWorker");
	    	
	    	Object rango = buffer.pop();
	    	verificarRangos(rango);
	    	
	    	
	        }
	}
	
	public void verificarRangos(Object rango) {
		((IntStream) rango).forEach(num -> verificarNum(num));
		
	}
	
	private void verificarNum(int num) {
		System.out.println(num);
		if (!encontroNonce) {
			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance("SHA-256");
						String hashable = caracteres + num; 
			        	byte[] hash = digest.digest(hashable.getBytes(StandardCharsets.UTF_8));
			        	
			        	verificarDificultad(hash);
					}
			catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
			}
		}	
	}
	
	private void verificarDificultad(byte[] hash) {
		boolean verificacion = true;
		for (int i = 0; i < dificultad; i++) {
			verificacion = verificacion && hash[i] == 0;
		}
		if (verificacion) {
			this.encontroNonce = true;
			threadPool.stop();
			printNonce(hash);
		}
		
	}

	private void printNonce(byte[] hash) {
		String hex = String.format("%064x", new BigInteger(1, hash));  //El array completo 
        System.out.println(hex);
        
      //String hexa = Integer.toHexString(hash[0]); Una sola posicion
	    //for (int i = 0; i< hash.length; i++) {
	    	//System.out.println(hash[i]); // esto nos lo da en bytes  
	    //}
		
	}
			
}		
	
