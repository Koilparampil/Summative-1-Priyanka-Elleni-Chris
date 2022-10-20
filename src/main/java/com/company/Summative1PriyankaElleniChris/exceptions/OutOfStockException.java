package com.company.Summative1PriyankaElleniChris.exceptions;

public class OutOfStockException extends RuntimeException{

    public OutOfStockException() {
        super();
    }

    public OutOfStockException(String msg) {
        super(msg);
    }
}
