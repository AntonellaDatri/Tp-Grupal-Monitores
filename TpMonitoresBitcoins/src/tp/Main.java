package tp;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    private static int cantThreads;
    private static int dificultad;
    private static String caracteres;
    private static  Buffer buffer;
    private static ThreadPool threadPool;
    private static long TInicio, TFin;
   
    
	 public static void main(String[] args) { //Threads: 1, 2, 4, 6, 8 y 10
		 pedirDatos();
		 TInicio = System.currentTimeMillis();
		 Timer contador = new Timer(cantThreads ,TInicio);
		 buffer = new Buffer(2);
		 threadPool = new ThreadPool(buffer, cantThreads, dificultad, caracteres, contador);
		 repartirTareas();
		 contador.calcularSegundos();
		 /*------------------------------------------------*/
		 /*TFin = System.currentTimeMillis();
		 System.out.println("Cantidad de segundos tardados: " + (TFin -TInicio));*/
	  }
	 
	 private static void pedirDatos() {
		 Scanner entrada = new Scanner(System.in);
	     System.out.print("Ingrese la cadena de caracteres: ");
		 caracteres = entrada.nextLine();
		 System.out.print("Ingrese la cantidad de threads: ");
		 cantThreads = entrada.nextInt();
		 System.out.print("Ingrese la dificultad deseada: ");
		 dificultad = entrada.nextInt();
		
	 }
	 
	 private static void repartirTareas() {
			int unidad = (int) Math.pow(2, 32)/cantThreads;
			int mod = (int) Math.pow(2, 32)%cantThreads;
			if ( esPar(mod)) {
				formatoPar(unidad);
			} 
			else {
				formatoImpar(unidad, mod);
			}
			threadPool.stop();
	 }
	 
	private static boolean esPar(int mod) {
		 return mod == 0;
	 }
	 
	 private static void formatoPar(int unidad) {
		 for (int i = 0; i<cantThreads; i++) {
			int inicio = (unidad)*i;
			int fin = ((unidad)*(i+1))-1;
			IntStream stream = IntStream.range(inicio, fin);
			Rango tarea = new Rango();
			tarea.setStream(stream);
			buffer.push(tarea);
		 }
	 }
	 
	 private static void formatoImpar(int unidad, int mod) {
		 ArrayList<IntStream> rangos = new ArrayList<IntStream>();
		 int ultimaUnidad = 0;
		 for (int i = 0; i<(cantThreads); i++) {
			IntStream stream = IntStream.range((unidad)*i, (unidad)*(i+1));
			rangos.add(stream);
			ultimaUnidad = (unidad)*(i+1);
		 }
		 IntStream stream1 = IntStream.range(ultimaUnidad, (ultimaUnidad+mod));
		 ArrayList<IntStream> probando = new ArrayList<IntStream>();
		 stream1.forEach(e -> probando.add(IntStream.range(e, e+1)));
		 for (int i=0; i< mod; i++) {
			IntStream stream2= rangos.remove(i);
			IntStream stream3 = IntStream.concat(stream2, probando.get(i));
			rangos.add(stream3);
		 }
		 pushearAlBuffer(rangos);
	 }
	 
	 private static void pushearAlBuffer(ArrayList<IntStream> rangos) {
		 rangos.forEach(rango -> pushearRango(rango));
	 }

	private static void pushearRango(IntStream rango) {
		Rango tarea = new Rango();
		tarea.setStream(rango);
		
		buffer.push(tarea);
	}
}