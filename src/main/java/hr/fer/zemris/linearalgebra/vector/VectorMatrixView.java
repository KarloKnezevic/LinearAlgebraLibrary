package hr.fer.zemris.linearalgebra.vector;

import hr.fer.zemris.linearalgebra.exception.IncompatibleOperandException;
import hr.fer.zemris.linearalgebra.matrix.IMatrix;
import hr.fer.zemris.linearalgebra.matrix.Matrix;

/**
 * Razred modelira pogled na jednoretčanu ili jednostupčanu
 * matricu kao vektor.
 * Pretpostavljen način rada je live view, što znači da promjena 
 * na originalnoj matrici ili vektoru, uzrokuje trenutnu promjenu na oba 
 * objekta.
 * Omogućeno korištenje operacija razreda ukoliko je matrica
 * jednoretčana ili jednostupčana.
 * @author Karlo Knezevic, karlo.knezevic@fer.hr
 *
 */
public class VectorMatrixView extends AbstractVector {
	
	private int dimension;
	
	private boolean rowMatrix;
	
	//referenca na originalnu matricu
	private IMatrix matrix;
	
	/**
	 * Konstruktor.
	 * Predaje se referenca na jednoretčanu ili jednostupčanu matricu.
	 * Ukoliko matrica nije jednoretčana ili jednostupčana,
	 * vraća se iznimka IncompatibleOperandException.
	 * @param matrix
	 */
	public VectorMatrixView(IMatrix matrix) {
		if (matrix.getColsCount() != 1 || matrix.getRowsCount() !=1) {
			throw new IncompatibleOperandException(
					"Can not convert matrix to vector.");
		}
		this.matrix = matrix;
		dimension = matrix.getColsCount() == 1 ? 
				matrix.getRowsCount() : matrix.getColsCount();
		rowMatrix = matrix.getRowsCount() == 1 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.vector.AbstractVector#get(int)
	 */
	@Override
	public double get(int index) {
		return rowMatrix ? matrix.get(0, index) : matrix.get(index, 0);
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.vector.AbstractVector#set(int, double)
	 */
	@Override
	public IVector set(int index, double value) {
		if (rowMatrix) {
			matrix.set(0, index, value);
		} else {
			matrix.set(index, 0, value);
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.vector.AbstractVector#getDimension()
	 */
	@Override
	public int getDimension() {
		return dimension;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.vector.AbstractVector#copy()
	 */
	@Override
	public IVector copy() {
		if (rowMatrix) {
			return new Vector(false, false, matrix.toArray()[0]);
		}
		double[] array = new double[dimension];
		double[][] arrayMatrix = matrix.toArray();
		for (int i = 0; i < dimension; i++) {
			array[i] = arrayMatrix[0][i];
		}
		return new Vector(false, false, array);
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.vector.AbstractVector#newInstance(int)
	 */
	@Override
	public IVector newInstance(int dimension) {
		//pretpostavljeno se stvara jednoretčana matrica dimenzija
		//1 x dimenzija vektora
		return new VectorMatrixView(new Matrix(1, dimension));
	}

}
