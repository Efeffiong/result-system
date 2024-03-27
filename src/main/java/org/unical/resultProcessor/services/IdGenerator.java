package org.unical.resultProcessor.services;

public class IdGenerator {

	public Long generateId() {
		Long id = System.nanoTime();
		return id;
	}
}
