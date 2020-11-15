package com.acme.statusmgr;

public class EmptyDetailsException extends Throwable {

    public EmptyDetailsException() {
        super("You have given no details in your detail list.");
    }
}
