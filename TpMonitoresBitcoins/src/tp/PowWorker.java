package tp;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.PrimitiveIterator;
import java.util.stream.IntStream;

public class PowWorker extends Thread {
	 private Buffer buffer;
		private String caracteres;
	    public PowWorker(Buffer buff, String c) {
	        buffer = buff;
	        caracteres = c; 
	    }

	    public void run(){
	        try {
	            while (true) {
	            	System.out.println("Inicialice PowWorker");
	            	MessageDigest digest = MessageDigest.getInstance("SHA-256");
	            	Object rango = buffer.pop();
	            	String hashable = caracteres + 3; 
	            	byte[] hash = digest.digest(hashable.getBytes(StandardCharsets.UTF_8));
	            	//String hexa = Integer.toHexString(hash[0]); Una sola posicion
	                String hex = String.format("%064x", new BigInteger(1, hash));  //El array completo 
	                for (int i = 0; i< hash.length; i++) {
	                	//System.out.println(hash[i]); // esto nos lo da en bytes  
	                }
	                //System.out.println(hex);
	            }
	        }
	        catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
	    }
}
