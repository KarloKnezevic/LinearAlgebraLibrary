package hr.fer.zemris.linearna.demo;

import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.matrix.Matrix;
import hr.fer.zemris.linearna.vector.Vector;

/**
 * DEMO 1.
 * Primjer iz udžbenika IRG.
 * Primjer rada frameworka s matricama.
 * @author Karlo Knezevic, karlo.knezevic@fer.hr
 *
 */
public class Demo1 {

	public static void main(String[] args) {
		
		IVector v1 = Vector.parseSimple("2 3 -4").add(Vector.parseSimple("-1 4 -3"));
		System.out.println("v1 = " + v1.toString());
		System.out.println();
		
		double s = v1.scalarProduct(Vector.parseSimple("-1 4 -3"));
		System.out.println("s = " + s);
		System.out.println();
		
		IVector v2 = v1.nVectorProduct(Vector.parseSimple("2 2 4"));
		System.out.println("v2 = " + v2.toString());
		System.out.println();
		
		IVector v3 = v2.nNormalize();
		System.out.println("v3 = " + v3.toString());
		System.out.println();
		
		IVector v4 = v2.scalarMultiply(-1);
		System.out.println("v4 = " + v4.toString());
		System.out.println();
		
		IMatrix m1 = Matrix.parseSimple("1 2 3 | 2 1 3 | 4 5 1").add(Matrix.parseSimple("-1 2 -3 | 5 -2 7 | -4 -1 3"));
		System.out.println("M1 = \n" + m1.toString());
		System.out.println();
		
		IMatrix m2 = Matrix.parseSimple("1 2 3 | 2 1 3 | 4 5 1").nMultiply(Matrix.parseSimple("-1 2 -3 | 5 -2 7 | -4 -1 3").nTranspose(false));
		System.out.println("M2 = \n" + m2.toString());
		System.out.println();
		
		IMatrix m3 = Matrix.parseSimple("-24 18 5 | 20 -15 -4 | -5 4 1").nInvert().nMultiply(Matrix.parseSimple("1 2 3 | 0 1 4 | 5 6 0").nInvert());
		System.out.println("M3 = \n" + m3.toString());
		System.out.println();

	}

}
