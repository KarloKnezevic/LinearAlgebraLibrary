package hr.fer.zemris.linearna.demo;


import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.matrix.Matrix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * DEMO 2.
 * Primjer rada frameworka s matricama.
 * @author Karlo Knezevic, karlo.knezevic@fer.hr
 *
 */
public class Demo2 {

	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
		double[][] equations = new double[3][4];
		
		for(int i = 0; i<3; i++){
			for(int j = 0; j<4; j++){
				try {
					equations[i][j] = Double.parseDouble(reader.readLine());
				} catch (NumberFormatException | IOException e) {
					System.out.println("pogrešan unos!");
					System.exit(1);
				}
			}
		}
		
		IMatrix a = new Matrix(3, 3);
		
		for(int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
				a.set(i, j, equations[i][j]);
			}
		}
		
		IMatrix r = new Matrix(3, 1);
		r.set(0, 0, equations[0][3]).set(1, 0, equations[1][3]).set(2, 0, equations[2][3]);
		
		IMatrix v = a.nInvert();
		if(v != null){
			v = v.nMultiply(r);	
			System.out.println("Rješenje je:");
			System.out.println(v);	
		}
		else{
			System.out.println("Sustav nema jednoznačno rješenje.");
		}
	}
}
