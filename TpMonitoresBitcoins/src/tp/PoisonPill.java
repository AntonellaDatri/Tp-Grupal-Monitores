package tp;

import java.util.stream.IntStream;

class PoisonPill implements Tareas{
    public void run() {
         throw new PoisonException("Me llego una poisonException");
   }

	@Override
	public IntStream getStream() {
		// no devuelve nada, nunca deberia llegar a est instancia, siempre se tiene que correr primero el run()
		return null;
	}
}
