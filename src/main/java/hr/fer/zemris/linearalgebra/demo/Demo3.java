package hr.fer.zemris.linearalgebra.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import hr.fer.zemris.linearalgebra.matrix.IMatrix;
import hr.fer.zemris.linearalgebra.matrix.Matrix;

/**
 * DEMO 3.
 * Primjer rada frameworka s matricama.
 * @author Karlo Knezevic, karlo.knezevic@fer.hr
 *
 */
public class Demo3 {


	public static void main(String[] args) throws IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
		
		String input = null;
		IMatrix a = new Matrix(3, 3);
		IMatrix r = new Matrix(3, 1);
		
		System.out.print("Vrh1 = ");
		input = reader.readLine();
		a.set(0, 0, Double.parseDouble(input.split(" ")[0]));
		a.set(1, 0, Double.parseDouble(input.split(" ")[1]));
		a.set(2, 0, Double.parseDouble(input.split(" ")[2]));
		System.out.print("Vrh2 = ");
		input = reader.readLine();
		a.set(0, 1, Double.parseDouble(input.split(" ")[0]));
		a.set(1, 1, Double.parseDouble(input.split(" ")[1]));
		a.set(2, 1, Double.parseDouble(input.split(" ")[2]));
		System.out.print("Vrh3 = ");
		input = reader.readLine();
		a.set(0, 2, Double.parseDouble(input.split(" ")[0]));
		a.set(1, 2, Double.parseDouble(input.split(" ")[1]));
		a.set(2, 2, Double.parseDouble(input.split(" ")[2]));

		System.out.print("Točka = ");
		input = reader.readLine();
		r.set(0, 0, Double.parseDouble(input.split(" ")[0]));
		r.set(1, 0, Double.parseDouble(input.split(" ")[1]));
		r.set(2, 0, Double.parseDouble(input.split(" ")[2]));

		IMatrix v = a.nInvert();
		if(v != null){
			v = v.nMultiply(r);	
			System.out.println("Baricentrične koordinate su:");
			System.out.println(v);
		}
		else{
			System.out.println("Sustav nema jednoznačno rješenje.");
		}
	}
}
