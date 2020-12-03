package com.acme.statusmgr.beans;


/**
 * Interface that ServerStatus implements to allow for the decorator to pass a ServerStatus as a parameter, and decorate it
 */
public interface StatusInterface {

    long getId();

    String getContentHeader();

    String getStatusDesc();
}
