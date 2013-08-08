package hr.fer.zemris.linearalgebra.matrix;

import java.text.DecimalFormat;

import hr.fer.zemris.linearalgebra.exception.IncompatibleOperandException;
import hr.fer.zemris.linearalgebra.vector.IVector;
import hr.fer.zemris.linearalgebra.vector.VectorMatrixView;

/**
 * Apstraktni razred matrice.
 * Razred implementira sučlje IMatrix.
 * Na raspolaganju metode za matrične operacije.
 * @author Karlo Knezevic, karlo.knezevic@fer.hr
 *
 */
public abstract class AbstractMatrix implements IMatrix {

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IMatrix#getRowsCount()
	 */
	@Override
	public abstract int getRowsCount();

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IMatrix#getColsCount()
	 */
	@Override
	public abstract int getColsCount();

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IMatrix#get(int, int)
	 */
	@Override
	public abstract double get(int row, int col);

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IMatrix#set(int, int, double)
	 */
	@Override
	public abstract IMatrix set(int row, int col, double value);

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IMatrix#copy()
	 */
	@Override
	public abstract IMatrix copy();

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IMatrix#newInstance(int, int)
	 */
	@Override
	public abstract IMatrix newInstance(int rows, int cols);

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IMatrix#nTranspose(boolean)
	 */
	@Override
	public IMatrix nTranspose(boolean liveView) {
		return liveView ? 
				new MatrixTransposeView(this) : 
					new MatrixTransposeView(this.copy());
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IMatrix#add(hr.fer.zemris.linearna.IMatrix)
	 */
	@Override
	public IMatrix add(IMatrix other) {
		//ako nisu jednake dimenzije, vrati iznimku
		if (this.getColsCount() != other.getColsCount() 
				|| this.getRowsCount() != other.getRowsCount()) {
			throw new IncompatibleOperandException(
					"Matrices have not same dimensions.");
		}
		for (int row = 0; row < this.getRowsCount(); row++) {
			for (int col = 0; col < this.getColsCount(); col++) {
				this.set(row, col, this.get(row, col)+other.get(row, col));
			}
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IMatrix#nAdd(hr.fer.zemris.linearna.IMatrix)
	 */
	@Override
	public IMatrix nAdd(IMatrix other) {
		return this.copy().add(other);
		
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IMatrix#sub(hr.fer.zemris.linearna.IMatrix)
	 */
	@Override
	public IMatrix sub(IMatrix other) {
		//ako nisu jednake dimenzije, vrati iznimku
		if (this.getColsCount() != other.getColsCount() 
				|| this.getRowsCount() != other.getRowsCount()) {
			throw new IncompatibleOperandException(
					"Matrices have not same dimensions.");
		}
		for (int row = 0; row < this.getRowsCount(); row++) {
			for (int col = 0; col < this.getColsCount(); col++) {
				this.set(row, col, this.get(row, col)-other.get(row, col));
			}
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IMatrix#nSub(hr.fer.zemris.linearna.IMatrix)
	 */
	@Override
	public IMatrix nSub(IMatrix other) {
		return this.copy().sub(other);
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IMatrix#nMultiply(hr.fer.zemris.linearna.IMatrix)
	 */
	@Override
	public IMatrix nMultiply(IMatrix other) {
		/*
		 * da bi se dvije matrice mogle pomnožiti, moraju biti ulančane;
		 * ulančanost podrazumijeva da je broj stupaca prve matrice jednak broju 
		 * redaka druge matrice;
		 * rezultantna matrica ima broj redaka jednak broju redaka prvoj matrici,
		 * a broj stupaca jednak broju stupaca drugoj matrici
		 */
		if (this.getColsCount() != other.getRowsCount()) {
			throw new IncompatibleOperandException(
					"Matrices are not chained.");
		}
		IMatrix matrix = this.newInstance(
				this.getRowsCount(), other.getColsCount());
		
		//složenost O(n^3)!
		for (int row = 0; row < this.getRowsCount(); row++) {
			for (int col = 0; col < other.getColsCount(); col++) {
				for (int rowCol = 0; rowCol < this.getColsCount(); rowCol++) {
					matrix.set(
							row, 
							col, 
							matrix.get(row, col) + 
							this.get(row, rowCol)*other.get(rowCol, col));
				}
			}
		}
		return matrix;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IMatrix#determinant()
	 */
	@Override
	public double determinant() throws IncompatibleOperandException {
		//determinantu je moguće izračunati kod kvadratnih matrica
		//ukoliko treba podržati i za ne kvadratne, poogledati Penrose-Mooreov
		//pseudoinverz
		if (this.getColsCount() != this.getRowsCount()) {
			throw new IncompatibleOperandException(
					"Determinant of nonquadratic matrix in incomputable.");
		}
		
		//iskoristi memoizaciju
		if (this.getColsCount() == 1) {
			return this.get(0, 0);
		}
		if (this.getColsCount() == 2) {
			return this.get(0,0)*this.get(1,1) - this.get(1,0)*this.get(0,1);
		}
		double determinant = 0;
		for (int i = 0; i < this.getColsCount(); i++) {
			double factor = 
					this.get(0, i)*new MatrixSubMatrixView(this, 0, i).determinant();
			factor = i%2==0 ? factor : -1*factor;
			determinant += factor;
		}
		return determinant;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IMatrix#subMatrix(int, int, boolean)
	 */
	@Override
	public IMatrix subMatrix(int row, int col, boolean liveView) {
		return liveView ? 
				new MatrixSubMatrixView(this, row, col) : 
					new MatrixSubMatrixView(this.copy(), row, col);
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IMatrix#nInvert()
	 */
	@Override
	public IMatrix nInvert() {
		/*
		 * inverz se računa kao umnožak inverza determinante matrice i 
		 * transponirane adjungirane matrice početne matrice;
		 * ukoliko je determinanta 0, tada inverz ne postoji i takva matrica
		 * naziva se singularna matrica
		 */
		double determinant = this.determinant();
		if (determinant == 0) {
			throw new IncompatibleOperandException("Matrix is singular.");
		}
		IMatrix cofactorMatrix = new Matrix(this.getRowsCount(), this.getColsCount());
		for (int row = 0; row < this.getRowsCount(); row++) {
			for (int col = 0; col < this.getColsCount(); col++) {
				cofactorMatrix.set(
						row, 
						col, 
						(row+col)%2==0 ? 
								//ako je (i+j)%2 neparno, rezultat množimo s -1
								subMatrix(row, col, true).determinant() : 
									-1*subMatrix(row, col, true).determinant());
			}
		}
		return cofactorMatrix.nTranspose(true).scalarMultiply(1.0/determinant);
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IMatrix#toArray()
	 */
	@Override
	public double[][] toArray() {
		double[][] matrix = new double[this.getRowsCount()][this.getColsCount()];
		for (int row = 0; row < this.getRowsCount(); row++) {
			for (int col = 0; col < this.getColsCount(); col++) {
				matrix[row][col] = this.get(row, col);
			}
		}
		return matrix;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IMatrix#toVector(boolean)
	 */
	@Override
	public IVector toVector(boolean liveView) {
		return liveView ? 
				new VectorMatrixView(this) : 
					new VectorMatrixView(this.copy());
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IMatrix#nScalarMultiply(double)
	 */
	@Override
	public IMatrix nScalarMultiply(double value) {
		return this.copy().scalarMultiply(value);
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IMatrix#scalarMultiply(double)
	 */
	@Override
	public IMatrix scalarMultiply(double value) {
		for (int row = 0; row < this.getRowsCount(); row++) {
			for (int col = 0; col < this.getColsCount(); col++) {
				this.set(row, col, this.get(row, col)*value);
			}
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IMatrix#makeIdentity()
	 */
	@Override
	public IMatrix makeIdentity() {
		for (int row = 0; row < this.getRowsCount(); row++) {
			for (int col = 0; col < this.getColsCount(); col++) {
				this.set(row, col, row==col ? 1 : 0);
			}
		}
		return this;
	}
	
	/**
	 * Ispis matrice. Elementi se ispisuju s preciznošću od
	 * 10^-precision.
	 * @param precision broj decimalnih mjesta u prikazu brojeva
	 * @return zapis matrice kao string
	 */
	public String toString(int precision) {
		//dolijepi broj nula koji je potreban
		String pattern = "0.";
		for (int i = 0; i < precision; i++) {
			pattern += "0";
		}
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		//da sve bude brže, koristi StringBuilder
		StringBuilder out = new StringBuilder(80);
		for (int row = 0; row < this.getRowsCount(); row++) {
			out.append("[");
			for (int col = 0; col < this.getColsCount(); col++) {
				if (col < this.getColsCount() - 1) {
					out.append(decimalFormat.format(this.get(row, col)) + ", ");
				} else {
					out.append(decimalFormat.format(this.get(row, col)) + "]");
				}
			}
			if (row < this.getRowsCount()-1) {
				out.append("\n");
			}
		}
		return out.toString();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		//po defaultu se koristi ispis s 3 decimalna mjesta
		return toString(3);
	}

}
