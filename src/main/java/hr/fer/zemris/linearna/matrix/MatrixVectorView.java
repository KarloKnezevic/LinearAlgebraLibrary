package hr.fer.zemris.linearna.matrix;

import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;

/**
 * Razred modelira pogled na vektor kao matricu.
 * Matrica je jednostupčana ili jednoretčana.
 * Ponuđene sve operacije rada s matricom.
 * Pretpostavljen način rada je live view, što znači da 
 * bilokoja promjena na vektoru, odražava se i na matricu 
 * i suprotno.
 * @author Karlo Knezevic, karlo.knezevic@fer.hr
 *
 */
public class MatrixVectorView extends AbstractMatrix {
	
	//jednostupčana ili jednoretčana matrica?
	private boolean asRowMatrix;
	
	//referenca na originalni vektor
	private IVector vector;
	
	/**
	 * Konstruktor.
	 * Prima se referenca na vektor i zastavica stvara li se
	 * jednostupčana ili jednoretčana matrica.
	 * @param vector
	 * @param asRow
	 */
	public MatrixVectorView(IVector vector, boolean asRow) {
		asRowMatrix = asRow;
		this.vector = vector;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.matrix.AbstractMatrix#getRowsCount()
	 */
	@Override
	public int getRowsCount() {
		return asRowMatrix ? 1 : vector.getDimension();
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.matrix.AbstractMatrix#getColsCount()
	 */
	@Override
	public int getColsCount() {
		return asRowMatrix ? vector.getDimension() : 1;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.matrix.AbstractMatrix#get(int, int)
	 */
	@Override
	public double get(int row, int col) {
		return asRowMatrix ? vector.get(col) : vector.get(row);
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.matrix.AbstractMatrix#set(int, int, double)
	 */
	@Override
	public IMatrix set(int row, int col, double value) {
		if (asRowMatrix) {
			vector.set(col, value);
		} else {
			vector.set(row, value);
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.matrix.AbstractMatrix#copy()
	 */
	@Override
	public IMatrix copy() {
		double[][] vec2mat = null;
		if (asRowMatrix) {
			vec2mat = new double[1][];
			vec2mat[0] = vector.toArray();
			return new Matrix(1, vector.getDimension(), vec2mat, false);
		}
		vec2mat = new double[vector.getDimension()][1];
		for (int i = 0; i < vector.getDimension(); i++) {
			vec2mat[i][0] = vector.get(i);
		}
		return new Matrix(1, vector.getDimension(), vec2mat, false);
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
