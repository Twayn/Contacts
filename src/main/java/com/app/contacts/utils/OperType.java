package com.app.contacts.utils;

public enum  OperType {
	ADD("add"), EDIT("edit"), DELETE("delete");

	private final String type;

	OperType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
