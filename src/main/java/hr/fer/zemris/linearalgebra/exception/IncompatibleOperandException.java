package hr.fer.zemris.linearalgebra.exception;

public class IncompatibleOperandException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public IncompatibleOperandException() {
	}

	public IncompatibleOperandException(final String message) {
		super(message);
	}

	public IncompatibleOperandException(final Throwable cause) {
		super(cause);
	}

	public IncompatibleOperandException(final String message,
			final Throwable cause) {
		super(message, cause);
	}
}
