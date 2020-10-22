package tp;

public class ThreadPool {

        Buffer buffer;
        int worker;
        String caracters;
        
        public ThreadPool (Buffer b, int t, int d, String c) {
            buffer = b;
            worker = t;
            caracters = c;
        }

        public void init(){
        	System.out.println("Inicialice");
            for (int i = 0; i < worker; ++i){
                new PowWorker(buffer, caracters).start();
            }
        }

        public void launch (Object tarea){ //la tarea es el rango de valores a analizar
            buffer.push(tarea);

        }

        public void stop(){
            for (int i = 0; i < worker; ++i){
                buffer.push(new PoisonPill());
            }
        }

}
