package hr.fer.zemris.linearalgebra.exception;

public class UnmodifiableObjectException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UnmodifiableObjectException() {
	}

	public UnmodifiableObjectException(final String message) {
		super(message);
	}

	public UnmodifiableObjectException(final Throwable cause) {
		super(cause);
	}

	public UnmodifiableObjectException(final String message,
			final Throwable cause) {
		super(message, cause);
	}
}
