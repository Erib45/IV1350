package se.kth.iv1350.controller;

public class OperationFailedException extends Exception{
	public OperationFailedException(String message, Exception e) {
		super(message, e);
	}
}
