package com.fiap.productRegistry.exceptionHandler;

public class TransportDataException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TransportDataException(String msg) {
		super(msg);
	}
}
