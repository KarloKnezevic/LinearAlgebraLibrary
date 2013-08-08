package hr.fer.zemris.linearalgebra.vector;

import hr.fer.zemris.linearalgebra.exception.IncompatibleOperandException;
import hr.fer.zemris.linearalgebra.exception.UnmodifiableObjectException;
import hr.fer.zemris.linearalgebra.matrix.IMatrix;

public interface IVector {

	public abstract double get(int paramInt);

	public abstract IVector set(int paramInt, double paramDouble)
			throws UnmodifiableObjectException;

	public abstract int getDimension();

	public abstract IVector copy();

	public abstract IVector copyPart(int paramInt);

	public abstract IVector newInstance(int paramInt);

	public abstract IVector add(IVector paramIVector)
			throws IncompatibleOperandException;

	public abstract IVector nAdd(IVector paramIVector)
			throws IncompatibleOperandException;

	public abstract IVector sub(IVector paramIVector)
			throws IncompatibleOperandException;

	public abstract IVector nSub(IVector paramIVector)
			throws IncompatibleOperandException;

	public abstract IVector scalarMultiply(double paramDouble);

	public abstract IVector nScalarMultiply(double paramDouble);

	public abstract double norm();

	public abstract IVector normalize();

	public abstract IVector nNormalize();

	public abstract double cosine(IVector paramIVector)
			throws IncompatibleOperandException;

	public abstract double scalarProduct(IVector paramIVector)
			throws IncompatibleOperandException;

	public abstract IVector nVectorProduct(IVector paramIVector)
			throws IncompatibleOperandException;

	public abstract IVector nFromHomogeneus();

	public abstract IMatrix toRowMatrix(boolean paramBoolean);

	public abstract IMatrix toColumnMatrix(boolean paramBoolean);

	public abstract double[] toArray();

}
