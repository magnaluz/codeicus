package com.websystique.springboot.util;

public enum TaskOperationEnum {

    CREATE("create"), READ_ALL("read all"), READ_ONE("read one"), DELETE("delete"), UPDATE("update"),;

    private String text;

    TaskOperationEnum(String text) {
	this.text = text;
    }

    public String getText() {
	return this.text;
    }

}
