package hr.fer.zemris.linearalgebra.demo;

import hr.fer.zemris.linearalgebra.vector.IVector;
import hr.fer.zemris.linearalgebra.vector.Vector;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Primjer 1.2.4
 * Primjer izračuna reflektiranog vektora
 * @author Karlo Knezevic, karlo.knezevic@fer.hr
 *
 */
public class Example124 {
	
	public static void main(String[] args) throws Exception {
		final int VECTORNUM = 2;
		
		BufferedReader reader = 
				new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
		String line = null;
		
		do {
			System.out.print("Unesite dimenzionalnost vektora (2|3): ");
			line = reader.readLine();
		} while (!line.equals("2") && !line.equals("3"));
		
		int dimension = Integer.parseInt(line);
		double[] elements = new double[dimension];
		IVector[] vectorArray = new IVector[VECTORNUM];
		System.out.println("First vector is M and second N.");
		for (int i = 0; i < VECTORNUM; i++) {
			for (int d = 0; d < dimension; d++) {
				try {
					System.out.print("Enter " + (d+1) + ". component of " + (i+1) + " vector:");
					line = reader.readLine();
					elements[d] = Double.parseDouble(line);
				} catch (Exception e) {
					System.out.println("Integer or double are permitted.");
					e.printStackTrace();
				}
			}
			vectorArray[i] = new Vector(false, false, elements);
		}
		
		IVector reflected = vectorArray[1].nNormalize().
				nScalarMultiply(vectorArray[0].scalarProduct(
						vectorArray[1]) / vectorArray[1].norm()).
				nScalarMultiply(2).
				nSub(vectorArray[0]);
		
		System.out.println(
				"Reflektirani vektor na vektore " + 
		vectorArray[0] + " i " + vectorArray[1] + " je " + reflected);
		
	}

}
