package hr.fer.zemris.linearalgebra.matrix;


/**
 * Razred transponirane matrice.
 * Razred je po pretpostavljenom načinu rada u live view modu,
 * što znači da svaka promjena u originalnoj matrici, odmah je vidljiva
 * i u transponiranoj matrici.
 * @author Karlo Knezevic, karlo.knezevic@fer.hr
 *
 */
public class MatrixTransposeView extends AbstractMatrix {
	
	//referenca na originalnu matricu
	private IMatrix originalMatrix;

	/**
	 * Konstruktor.
	 * Prima se referenca na originalnu matricu.
	 * @param originalMatrix referenca na originalnu matricu
	 */
	public MatrixTransposeView(IMatrix originalMatrix) {
		super();
		this.originalMatrix = originalMatrix;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.matrix.AbstractMatrix#getRowsCount()
	 */
	@Override
	public int getRowsCount() {
		return originalMatrix.getColsCount();
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.matrix.AbstractMatrix#getColsCount()
	 */
	@Override
	public int getColsCount() {
		return originalMatrix.getRowsCount();
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.matrix.AbstractMatrix#get(int, int)
	 */
	@Override
	public double get(int row, int col) {
		return originalMatrix.get(col, row);
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.matrix.AbstractMatrix#set(int, int, double)
	 */
	@Override
	public IMatrix set(int row, int col, double value) {
		originalMatrix.set(col, row, value);
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.matrix.AbstractMatrix#copy()
	 */
	@Override
	public IMatrix copy() {
		return new MatrixTransposeView(originalMatrix.copy());
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
	 * Metoda vraća matricu kao 2D polje vrijednosti s pomočnom
	 * točkom.
	 */
	public double[][] toArray() {
		double[][] matrix = new double
				[originalMatrix.getColsCount()][originalMatrix.getRowsCount()];
		for (int row = 0; row < originalMatrix.getColsCount(); row++) {
			for (int col = 0; col < originalMatrix.getRowsCount(); col++) {
				matrix[row][col] = originalMatrix.get(col, row);
			}
		}
		return matrix;
	}

}
