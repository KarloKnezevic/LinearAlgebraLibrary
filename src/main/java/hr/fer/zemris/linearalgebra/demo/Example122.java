package hr.fer.zemris.linearalgebra.demo;

import hr.fer.zemris.linearalgebra.matrix.IMatrix;
import hr.fer.zemris.linearalgebra.matrix.Matrix;

/**
 * Primjer 1.2.2
 * Primjer rješavanja linearnog sustava.
 * @author Karlo Knezevic, karlo.knezevic@fer.hr
 *
 */
public class Example122 {
	
	public static void main(String[] args) {
		
		IMatrix a = Matrix.parseSimple("3 5 | 2 10");
		IMatrix r = Matrix.parseSimple("2 | 8");
		IMatrix v = a.nInvert().nMultiply(r);
		
		System.out.println("Rješenje sustava je: ");
		System.out.println(v);
	}

}
