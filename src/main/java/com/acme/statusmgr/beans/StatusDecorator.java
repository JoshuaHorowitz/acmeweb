package com.acme.statusmgr.beans;

/**
Abstract decorator class to add more functionality to the ServerStatus.

 @getStatusDesc() will be the added functionality, returning a String object from ServerManager
 */
public abstract class StatusDecorator implements StatusInterface {
    StatusInterface status;

    //Takes StatusInterface as parameter to enable decoration of ServerStatus to occur
    public StatusDecorator(StatusInterface s) {
        this.status = s;
    }

    public StatusDecorator() {}

    @Override
    public long getId() { return status.getId(); }

    @Override
    public String getContentHeader() {
        return status.getContentHeader();
    }

    @Override
    public String getStatusDesc() {
        return status.getStatusDesc();
    }
}
