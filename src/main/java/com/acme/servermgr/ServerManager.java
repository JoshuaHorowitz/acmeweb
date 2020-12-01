package com.acme.servermgr;

/**
 * Manage all servers (service providers) being tracked by the Acme server tracking system
 * For now just some simple static methods for use in school project.
 * Treat this as a 'black box' that gives back indicators like up, running etc for
 * various 'services' that are being managed.
 */
public class ServerManager {

    /**
     * Get the status of this server
     * @return a descriptive string about the servers status
     */
    static public String getCurrentServerStatus() {
        return "up";  // The server is up
    }

    static public String getCurrentServerMemory() {
        return "running low"; //The server's memory is full
    }

    static public String getCurrentServerExtensions() {
        return ", and the extensions are Hypervisor, RAID-6"; //The server's extensions are
    }

    static public String getCurrentServerOperations() {
        return "normally"; //The server's operations are searching
    }


    /**
     * Find out if this server is operating normally
     * @return Boolean indicating if server is operating normally
     */
    static public Boolean isOperatingNormally()
    {
        return true;
    }
}
