package tp;

class PoisonPill extends Tareas{
	@Override
    public void run() {
        throw new PoisonException("Me llego una poisonException");
   }
}
