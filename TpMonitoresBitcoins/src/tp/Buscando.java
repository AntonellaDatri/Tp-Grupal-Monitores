package tp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Buscando implements FindNonceState{
	private int dificultad;
	private ThreadPool threadPool; 
	private String caracteres;
	
	public Buscando(String caracteres, int dificultad, ThreadPool threadPool) {
		this.caracteres = caracteres;
        this.dificultad = dificultad;
        this.threadPool = threadPool;
	}

	@Override
	public void verificarNum(int num) {
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
	
	private void verificarDificultad(byte[] hash, int num) {
		boolean verificacion = true;		
		for (int i = 0; i < dificultad; i++) {
			verificacion = verificacion && hash[i] == 0;
		}
		if (verificacion) {
			System.out.println("Se encontro el nonce: " + num);
/*			byte [] bytes =  BigInteger.valueOf(num).toByteArray();
			for (int i = 0; i < bytes.length; i++ ) {
				System.out.println("Se encontro el nonce: " + bytes[i]);
			}*/
			threadPool.stop();
		}
	}

}
