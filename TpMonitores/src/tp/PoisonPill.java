package tp;

class PoisonPill extends Thread{
    public void run() {
        throw new PoisonException();
    }

}


