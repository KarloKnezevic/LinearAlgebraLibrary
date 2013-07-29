package hr.fer.zemris.linearna.vector;

import java.text.DecimalFormat;

import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.IncompatibleOperandException;
import hr.fer.zemris.linearna.matrix.MatrixVectorView;

/**
 * Razred apstraktnog vektora.
 * Razred implementira sučelje IVector.
 * Ponuđene operacije za rad s vektorima.
 * @author Karlo Knezevic, karlo.knezevic@fer.hr
 *
 */
public abstract class AbstractVector implements IVector {

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IVector#get(int)
	 */
	@Override
	public abstract double get(int index);

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IVector#set(int, double)
	 */
	@Override
	public abstract IVector set(int index, double value);

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IVector#getDimension()
	 */
	@Override
	public abstract int getDimension();

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IVector#copy()
	 */
	@Override
	public abstract IVector copy();

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IVector#copyPart(int)
	 */
	@Override
	public IVector copyPart(int n) {
		IVector vector = this.newInstance(n);
		for (int i = 0; i < n; i++) {
			vector.set(i, i<this.getDimension() ? this.get(i) : 0);
		}
		return vector;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IVector#newInstance(int)
	 */
	@Override
	public abstract IVector newInstance(int dimension);

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IVector#add(hr.fer.zemris.linearna.IVector)
	 */
	@Override
	public IVector add(IVector other) throws IncompatibleOperandException {
		//zbrajati se mogu samo vektori istih dimenzija
		if (this.getDimension() != other.getDimension()) {
			throw new IncompatibleOperandException("Incompatible vector length.");
		}
		for (int i = this.getDimension()-1; i >= 0; i--) {
			this.set(i, this.get(i)+other.get(i));
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IVector#nAdd(hr.fer.zemris.linearna.IVector)
	 */
	@Override
	public IVector nAdd(IVector other) throws IncompatibleOperandException {
		return this.copy().add(other);
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IVector#sub(hr.fer.zemris.linearna.IVector)
	 */
	@Override
	public IVector sub(IVector other) throws IncompatibleOperandException {
		//oduzimati se mogu samo vektori istih dimenzija
		if (this.getDimension() != other.getDimension()) {
			throw new IncompatibleOperandException("Incompatible vector length.");
		}
		for (int i = this.getDimension()-1; i >= 0; i--) {
			this.set(i, this.get(i)-other.get(i));
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IVector#nSub(hr.fer.zemris.linearna.IVector)
	 */
	@Override
	public IVector nSub(IVector other) throws IncompatibleOperandException {
		return this.copy().sub(other);
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IVector#scalarMultiply(double)
	 */
	@Override
	public IVector scalarMultiply(double byValue) {
		for (int i = this.getDimension()-1; i >= 0; i--) {
			this.set(i, this.get(i)*byValue);
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IVector#nScalarMultiply(double)
	 */
	@Override
	public IVector nScalarMultiply(double byValue) {
		return this.copy().scalarMultiply(byValue);
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IVector#norm()
	 */
	@Override
	public double norm() {
		double squareSum = 0;
		for (int i = this.getDimension()-1; i >= 0; i--) {
			squareSum += this.get(i)*this.get(i);
		}
		return Math.sqrt(squareSum);
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IVector#normalize()
	 */
	@Override
	public IVector normalize() {
		/*
		 * OPASKA!
		 * Po definiciji: ukoliko je norma vektora jednaka 0,
		 * tada je vekor normaliziran i ne provodi se normalizacija.
		 */
		double norm = this.norm();
		//dovoljno mala vrijednost
		if (norm < 1E-9) {
			return this;
		}
		for (int i = this.getDimension()-1; i >= 0; i--) {
			this.set(i, this.get(i)/norm);
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IVector#nNormalize()
	 */
	@Override
	public IVector nNormalize() {
		return this.copy().normalize();
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IVector#cosine(hr.fer.zemris.linearna.IVector)
	 */
	@Override
	public double cosine(IVector other) throws IncompatibleOperandException {
		/*
		 * a*b = |a|*|b|*cos Alfa
		 * kosinus kuta moguće izračunati ukoliko su vektori jednake dimenzije
		 * i ukoliko norma oba vektora nije jednaka 0.
		 */
		if (this.getDimension() != other.getDimension()) {
			throw new IncompatibleOperandException("Incompatible vector length.");
		}
		if (this.norm() < 1E-15 || other.norm() < 1E-15) {
			throw new IncompatibleOperandException("Not defined angle beetwen zerro vector.");
		}
		return this.scalarProduct(other)/(this.norm() * other.norm());
		
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IVector#scalarProduct(hr.fer.zemris.linearna.IVector)
	 */
	@Override
	public double scalarProduct(IVector other)
			throws IncompatibleOperandException {
		/*
		 * vektori moraju biti jednakih dimenzija
		 */
		if (this.getDimension() != other.getDimension()) {
			throw new IncompatibleOperandException("Incompatible vector length.");
		}
		double multiplySum = 0;
		for (int i = this.getDimension()-1; i >= 0; i--) {
			multiplySum += this.get(i)*other.get(i);
		}
		return multiplySum;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IVector#nVectorProduct(hr.fer.zemris.linearna.IVector)
	 */
	@Override
	public IVector nVectorProduct(IVector other)
			throws IncompatibleOperandException {
		/*
		 * PODRŽAN VEKTORSI PRODUKT ZA DIMENZIJU 3!
		 * Nije podržan tenzorski produkt (dimenzija > 3), a ukoliko je
		 * dimenzija manja od 3, tada vektorski produkt nije definiran.
		 */
		final int DIMENSION = 3;
		if (this.getDimension() != DIMENSION || 
				other.getDimension() != DIMENSION) {
			throw new IncompatibleOperandException(
					"Vector product defined only for vector of dimension 3.");
		}
		IVector vector = this.newInstance(DIMENSION);
		//po definiciji vektorskog produkta za dimenziju 3
		vector.set(0, this.get(1)*other.get(2) - this.get(2)*other.get(1));
		vector.set(1, this.get(2)*other.get(0) - this.get(0)*other.get(2));
		vector.set(2, this.get(0)*other.get(1) - this.get(1)*other.get(0));
		return vector;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IVector#nFromHomogeneus()
	 */
	@Override
	public IVector nFromHomogeneus() {
		//ukoliko je homogena koordinata 0, ne dijelimo s 0
		IVector vector = newInstance(this.getDimension()-1);
		double homogeneus = this.get(this.getDimension()-1);
		for (int i = 0; i < vector.getDimension(); i++) {
			vector.set(i, homogeneus!=0 ? this.get(i)/homogeneus : 0);
		}
		return vector;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IVector#toRowMatrix(boolean)
	 */
	@Override
	public IMatrix toRowMatrix(boolean liveView) {
		if (liveView) {
			return new MatrixVectorView(this, true);
		}
		return new MatrixVectorView(this.copy(), true);
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IVector#toColumnMatrix(boolean)
	 */
	@Override
	public IMatrix toColumnMatrix(boolean liveView) {
		if (liveView) {
			return new MatrixVectorView(this, false);
		}
		return new MatrixVectorView(this.copy(), false);
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.IVector#toArray()
	 */
	@Override
	public double[] toArray() {
		double[] array = new double[this.getDimension()];
		for (int i = 0; i < array.length; i++) {
			array[i] = this.get(i);
		}
		return array;
	}
	
	/**
	 * Ispis vektora kao string.
	 * Preciznost određuje broj decimalnih mjesta u ispisu.
	 * @param precision
	 * @return
	 */
	public String toString(int precision) {
		//dolijepi nula koliko je potrebno za ispis
		String pattern = "0.";
		for (int i = 0; i < precision; i++) {
			pattern += "0";
		}
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		//koristi StringBuilder da budemo brži
		StringBuilder out = new StringBuilder(40);
		out.append("[");
		for (int i = 0; i < this.getDimension(); i++) {
			if (i < this.getDimension() - 1) {
				out.append(decimalFormat.format(this.get(i)) + ", ");
			} else {
				out.append(decimalFormat.format(this.get(i)) + "]");
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
		//pretpostavljen je ispis s 3 decimalna mjesta
		return toString(3);
	}

}
