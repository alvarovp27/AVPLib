package avp.random;

import java.util.Random;

public class Randoms {
	
	
	/**
	 * Próximos métodos:
	 * - Generar un double aleatorio entre 0 y un límite superior con una presición dada
	 * - Generar un int entre un límite inferior dado y otro superior
	 * - Generar también números negativos
	 * 
	 * */
	
	/**
	 * Generate a double between 0 and 1
	 * */
	private static double randomDouble(){
		int num = randomInt(101);
		double doub = (double) num;
		return doub/100.0;
	}
	
	/**
	 * Returns a int between 0 and the number given as param (inclusive)
	 * */
	public static int randomInt(int limSup){
		Random r = new Random();
		return r.nextInt(limSup+1);
	}

}
