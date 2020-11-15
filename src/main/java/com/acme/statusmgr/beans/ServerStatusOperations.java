package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerManager;

public class ServerStatusOperations extends StatusDecorator {


    public ServerStatusOperations(StatusInterface s) {
        super(s);
    }

    @Override
    public String getStatusDesc() {
        return super.getStatusDesc() + ServerManager.getCurrentServerOperations();
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