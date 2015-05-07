package com.db.crud;

public class NullDocumentException extends RuntimeException {

	private static final long serialVersionUID = 2942378367179849171L;

	public NullDocumentException(String collection) {
		super("The document you want to insert into collection " + collection
				+ " is empty!");
	}

}
