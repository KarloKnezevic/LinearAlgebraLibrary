package hr.fer.zemris.linearalgebra.matrix;

import hr.fer.zemris.linearalgebra.exception.IncompatibleOperandException;
import hr.fer.zemris.linearalgebra.vector.IVector;

public interface IMatrix {

	public abstract int getRowsCount();

	public abstract int getColsCount();

	public abstract double get(int paramInt1, int paramInt2);

	public abstract IMatrix set(int paramInt1, int paramInt2, double paramDouble);

	public abstract IMatrix copy();

	public abstract IMatrix newInstance(int paramInt1, int paramInt2);

	public abstract IMatrix nTranspose(boolean paramBoolean);

	public abstract IMatrix add(IMatrix paramIMatrix);

	public abstract IMatrix nAdd(IMatrix paramIMatrix);

	public abstract IMatrix sub(IMatrix paramIMatrix);

	public abstract IMatrix nSub(IMatrix paramIMatrix);

	public abstract IMatrix nMultiply(IMatrix paramIMatrix);

	public abstract double determinant() throws IncompatibleOperandException;

	public abstract IMatrix subMatrix(int paramInt1, int paramInt2,
			boolean paramBoolean);

	public abstract IMatrix nInvert();

	public abstract double[][] toArray();

	public abstract IVector toVector(boolean paramBoolean);

	public abstract IMatrix nScalarMultiply(double paramDouble);

	public abstract IMatrix scalarMultiply(double paramDouble);

	public abstract IMatrix makeIdentity();

}
