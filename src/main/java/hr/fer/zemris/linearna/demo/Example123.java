package hr.fer.zemris.linearna.demo;

import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.matrix.Matrix;

/**
 * Primjer 1.2.3
 * Primjer izračuna baricentirčnih koordinata.
 * @author Karlo Knezevic, karlo.knezevic@fer.hr
 *
 */
public class Example123 {
	
	public static void main(String[] args) {
		
		IMatrix a = Matrix.parseSimple("1 5 3 | 0 0 8 | 1 1 1");
		IMatrix b = Matrix.parseSimple("3 | 4 | 1");
		
		System.out.println("Rješenje je: ");
		System.out.println(a.nInvert().nMultiply(b));
		
	}

}
