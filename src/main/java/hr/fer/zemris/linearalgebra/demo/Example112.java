package hr.fer.zemris.linearalgebra.demo;

import hr.fer.zemris.linearalgebra.matrix.IMatrix;
import hr.fer.zemris.linearalgebra.matrix.Matrix;

/**
 * Primjer 1.1.2
 * Primjer rada frameworka s matricama i live view.
 * @author Karlo Knezevic, karlo.knezevic@fer.hr
 *
 */
public class Example112 {
	
	public static void main(String[] args) {
		
		IMatrix m1 = Matrix.parseSimple("1 2 3 | 4 5 6");
		IMatrix m2 = m1.nTranspose(true);
		
		System.out.println("m1:");
		System.out.println(m1.toString());
		System.out.println("m2:");
		System.out.println(m2.toString());
		System.out.println();
		
		m2.set(2, 1, 9);
		
		System.out.println("m1:");
		System.out.println(m1.toString());
		System.out.println("m2:");
		System.out.println(m2.toString());
	
	}
	
}
