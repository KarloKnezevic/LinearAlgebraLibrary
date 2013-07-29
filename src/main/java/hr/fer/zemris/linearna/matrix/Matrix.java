package hr.fer.zemris.linearna.matrix;

import hr.fer.zemris.linearna.IMatrix;

/**
 * Razred Matrica.
 * Nasljeđuje apstraktni razred AbstractMatrix.
 * Implementira metode potrebne operacije s matricama.
 * @author Karlo Knezevic, karlo.knezevic@fer.hr
 *
 */
public class Matrix extends AbstractMatrix {
	
	//elementi matrice
	private double[][] elements;
	
	private int rows;
	
	private int cols;
	
	/**
	 * Konstruktor.
	 * @param rows broj redaka matrice
	 * @param cols broj stupaca matrice
	 */
	public Matrix(int rows, int cols) {
		super();
		this.rows = rows;
		this.cols = cols;
		elements = new double[rows][cols];
	}
	
	/**
	 * Konstruktor.
	 * U konstruktoru se predaju i elementi matrice. Zastavicom
	 * useReference određeno je pamte li se elementi po referenci(OPREZ!)
	 * ili se elementi kopiraju.
	 * @param rows
	 * @param cols
	 * @param elements
	 * @param useReference
	 */
	public Matrix(int rows, int cols, double[][] elements, 
			boolean useReference) {
		super();
		this.rows = rows;
		this.cols = cols;
		if (useReference) {
			this.elements = elements;
		} else {
			this.elements = new double[rows][cols];
			for (int row = 0; row < rows; row++) {
				System.arraycopy(elements[row], 0, this.elements[row], 0, cols);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.matrix.AbstractMatrix#getRowsCount()
	 */
	@Override
	public int getRowsCount() {
		return rows;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.matrix.AbstractMatrix#getColsCount()
	 */
	@Override
	public int getColsCount() {
		return cols;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.matrix.AbstractMatrix#get(int, int)
	 */
	@Override
	public double get(int row, int col) {
		return elements[row][col];
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.matrix.AbstractMatrix#set(int, int, double)
	 */
	@Override
	public IMatrix set(int row, int col, double value) {
		elements[row][col] = value;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.matrix.AbstractMatrix#copy()
	 */
	@Override
	public IMatrix copy() {
		IMatrix matrix = 
				new Matrix(this.rows, this.cols, this.elements, false);
		return matrix;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.matrix.AbstractMatrix#newInstance(int, int)
	 */
	@Override
	public IMatrix newInstance(int rows, int cols) {
		return new Matrix(rows, cols);
	}
	
	/**
	 * Metoda na temelju predanog matričnog zapisa stvara i vraća objekt
	 * Matrix.
	 * Format zapisa: elementi | elementi | ...
	 * Razdjelnih "|" označava novi redak u matrici. Elementi u retku odvojeni 
	 * su razmakom. Broj elemenata u svakom retku mora biti jednak.
	 * U slučaju različitog broja elemenata, nije garantiran ispravan rad
	 * operacija s matricom.
	 * @param stringMatrix matrica zapisana kao string
	 * @return Matrix
	 */
	public static IMatrix parseSimple(String stringMatrix) {
		double[][] elements = null;
		String[] stringRows = stringMatrix.split("\\|");
		String[] stringElements = null;
		for (int stringRow = 0; stringRow < stringRows.length; stringRow++) {
			stringElements = stringRows[stringRow].trim().split("\\s+");
			if (stringRow == 0) {
				elements = new double[stringRows.length][stringElements.length];
			}
			for (int elem = 0; elem < stringElements.length; elem++) {
				elements[stringRow][elem] = Double.parseDouble(stringElements[elem]);
			}
		}
		return new Matrix(stringRows.length, stringElements.length, elements, false);
	}
	
}
