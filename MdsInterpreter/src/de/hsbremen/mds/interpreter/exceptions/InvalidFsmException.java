package de.hsbremen.mds.interpreter.exceptions;


public class InvalidFsmException extends Exception {
	public InvalidFsmException(){
		super("The Finit State Machine is invalid");
	}
}
