package hr.fer.zemris.linearna.matrix;

import hr.fer.zemris.linearna.IMatrix;

/**
 * Podmatrica matrice.
 * Razred je prepostavljen za rad u live view načinu rada,
 * što znači da promjena elementa u originalnoj matrici
 * uzrokuje i promjenu u podmatrici.
 * @author Karlo Knezevic, karlo.knezevic@fer.hr
 *
 */
public class MatrixSubMatrixView extends AbstractMatrix {
	
	//indeksi redaka originalne matrice
	private int[] rowIndexes;
	
	//indeksi stupaca originalne matrice
	private int[] colIndexes;
	
	//referenca na originalnu matricu
	private IMatrix matrix;
	
	/**
	 * Konastruktor.
	 * Šalje se referenca na originalnu matricu te indeks retka i stupca
	 * koji podmatrica NEĆE sadržavati, odnosno vidjeti.
	 * @param matrix referenca na originalnu matricu
	 * @param row redak koji podmatrica ne sadržava
	 * @param col stupac kojeg podmatrica ne sadržava
	 */
	public MatrixSubMatrixView(IMatrix matrix, int row, int col) {	
		rowIndexes = new int[matrix.getRowsCount()-1];
		colIndexes =  new int[matrix.getColsCount()-1];
		this.matrix = matrix;
		for (int rows = 0; rows < rowIndexes.length; rows++) {
			rowIndexes[rows] = rows<row ? rows : rows+1;
		}
		
		for (int cols = 0; cols < colIndexes.length; cols++) {
			colIndexes[cols] = cols<col ? cols : cols+1;
		}
	}
	
	/*
	 * Privatni konstruktor.
	 * Svrha ovog konstruktora jest da se na jednostavan način stvara
	 * kopija this objekta.
	 * Kao argument predaje se kopija originalne matrice te indeksi 
	 * redaka i stupaca koje će novi objekt podmatrice vidjeti u originalnoj
	 * matrici.
	 */
	private MatrixSubMatrixView(IMatrix matrix, int[] rows, int[] cols) {
		rowIndexes = new int[rows.length];
		colIndexes =  new int[cols.length];
		System.arraycopy(rows, 0, this.rowIndexes, 0, rows.length);
		System.arraycopy(cols, 0, this.colIndexes, 0, cols.length);
		this.matrix = matrix;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.matrix.AbstractMatrix#getRowsCount()
	 */
	@Override
	public int getRowsCount() {
		return rowIndexes.length;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.matrix.AbstractMatrix#getColsCount()
	 */
	@Override
	public int getColsCount() {
		return colIndexes.length;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.matrix.AbstractMatrix#get(int, int)
	 */
	@Override
	public double get(int row, int col) {
		return matrix.get(rowIndexes[row], colIndexes[col]);
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.matrix.AbstractMatrix#set(int, int, double)
	 */
	@Override
	public IMatrix set(int row, int col, double value) {
		matrix.set(rowIndexes[row], colIndexes[col], value);
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.matrix.AbstractMatrix#copy()
	 */
	@Override
	public IMatrix copy() {
		return new MatrixSubMatrixView(
				this.matrix.copy(), rowIndexes, colIndexes);
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.matrix.AbstractMatrix#newInstance(int, int)
	 */
	@Override
	public IMatrix newInstance(int rows, int cols) {
		return new Matrix(rows, cols);
	}

}
