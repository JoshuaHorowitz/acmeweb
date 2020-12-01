package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerManager;

public class ServerStatusExtensions extends StatusDecorator {


    public ServerStatusExtensions(StatusInterface s) {
        super(s);
    }

    @Override
    public String getStatusDesc() {
        return super.getStatusDesc() + ", and its extensions are [" + ServerManager.getCurrentServerExtensions() + "]";
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