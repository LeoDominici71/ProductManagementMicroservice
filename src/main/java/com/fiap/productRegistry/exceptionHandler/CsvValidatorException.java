package com.fiap.productRegistry.exceptionHandler;

public class CsvValidatorException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CsvValidatorException(String msg) {
		super(msg);
	}
}
