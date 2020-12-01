package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerManager;

public class ServerStatusMemory extends StatusDecorator {


    public ServerStatusMemory(StatusInterface s) {
        super(s);
    }

    @Override
    public String getStatusDesc() {
        return super.getStatusDesc() + ", and the memory is " + ServerManager.getCurrentServerMemory();
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
