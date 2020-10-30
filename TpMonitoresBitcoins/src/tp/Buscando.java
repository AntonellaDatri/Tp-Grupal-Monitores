package tp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Buscando implements FindNonceState{
    private int dificultad, nonce;
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
            nonce = num;
            try {
                digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = pasarloASha256(digest);
                verificarDificultad(hash);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
    }

    private byte[] pasarloASha256(MessageDigest digest) {
    	byte[] caracteresToBytes = caracteres.getBytes();
        byte[] numToBytes = BigInteger.valueOf(nonce).toByteArray();
        byte[] hashable = convertirEnBytes(caracteresToBytes, numToBytes);
        return  digest.digest(hashable);
    }

    private byte[] convertirEnBytes(byte[] caracteresToBytes, byte[] numToBytes) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            output.write(caracteresToBytes);
            output.write(numToBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toByteArray();
    }

    private void verificarDificultad(byte[] hash) {
        boolean verificacion = true;
        for (int i = 0; i < dificultad; i++) {
            verificacion = verificacion && hash[i] == 0;
        }
        seEncontroGoldenNonce(verificacion); 
    }

    private void seEncontroGoldenNonce(boolean verificacion) {
        if (verificacion) {
            System.out.println("Se encontro el nonce: " + nonce);
            threadPool.pararWorkers();
        }
    }

}
