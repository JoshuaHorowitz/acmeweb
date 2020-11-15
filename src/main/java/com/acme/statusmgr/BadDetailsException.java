package com.acme.statusmgr;

public class BadDetailsException extends Throwable {
    public BadDetailsException() {
        super("You have put invalid details in your detail list.");
    }
}
