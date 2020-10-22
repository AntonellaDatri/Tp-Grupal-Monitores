package tp;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PowWorker extends Thread{
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
            	String hashable = caracteres + rango;
            	byte[] hash = digest.digest(hashable.getBytes(StandardCharsets.UTF_8));
            	System.out.println(hash);
            	//[B@4fe4669b
            	//[B@4fe4669b
            }
        }
        catch (PoisonException e) {
            System.out.println("Me llego una poisonException");
        } catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        
    }
}
