package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerManager;

/**
 * Decorator for ServerStatus that outputs the state of the server - whether operating normally or otherwise
 */
public class ServerStatusOperations extends StatusDecorator {


    public ServerStatusOperations(StatusInterface s) {
        super(s);
    }

    @Override
    public String getStatusDesc() {
        return super.getStatusDesc() + ", and is operating " + ServerManager.getCurrentServerOperations();
    }

    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public String getContentHeader() {
        return super.getContentHeader();
    }
}