package edu.eci.arsw.samples;

public class Main {

	public static void main(String[] args) {
		int numHilos=20;
		
		HiloProc[] hilos=new HiloProc[numHilos];
		
		// Crear los hilos
		for (int i=0;i<numHilos;i++){
			hilos[i]=new HiloProc(i);
		}

		// Iniciar los hilos
		for (int i=0;i<numHilos;i++){
			hilos[i].start();
		}

		// BARRERA: Esperar activamente a que TODOS los hilos terminen
		// El programa principal se duerme (bloquea) hasta que el último hilo termine
		for (int i = 0; i < numHilos; i++) {
			try {
				hilos[i].join();  // El hilo principal espera a que el hilo i termine
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Solo llegamos aquí cuando el ÚLTIMO hilo ha terminado
		long tiempoPromedio=0;
		for (int i=0;i<numHilos;i++){
			tiempoPromedio+=hilos[i].getResultado();
		}

		System.out.println("El tiempo promedio de la ejecuci�n fue de:"+tiempoPromedio/numHilos);
	}
}
