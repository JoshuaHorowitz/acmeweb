package com.acme.statusmgr;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.acme.servermgr.ServerManager;
import com.acme.statusmgr.beans.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for all web/REST requests about the status of servers
 *
 * For initial school project - just handles info about this server
 * Syntax for URLS:
 *    All start with /server
 *    /status  will give back status of server
 *    a param of 'name' specifies a requestor name to appear in response
 *
 * Examples:
 *    http://localhost:8080/server/status
 *
 *    http://localhost:8080/server/status?name=Noach
 *
 *
 *
 */
@RestController
@RequestMapping("/server")
public class StatusController {

    protected static final String template = "Server Status requested by %s";
    protected final AtomicLong counter = new AtomicLong();


    @RequestMapping("/status")
    public StatusInterface getStatus(@RequestParam(value="name", defaultValue="Anonymous") String name) {
        return new ServerStatus(counter.incrementAndGet(), String.format(template, name));
    }


    /**
     *
     * @param name
     * @param details
     * @return
     * @throws BadDetailsException
     * @throws EmptyDetailsException
     * Receives the request for a server status, and then decorates that request in order to return
     * a specialized string of the servers status, operations, memory, etc.
     * Example: /server/status/detailed?details=operations,extensions&name=Noah
     *  should return 'Server is up, and is operating normally, and its extensions are [Hypervisor, RAID-6]'
     */
    @RequestMapping("/status/detailed")
    public StatusInterface getDetails(
            @RequestParam(value="name", defaultValue="Anonymous") String name,
            @RequestParam(value="details", defaultValue="Null") List<String> details) throws BadDetailsException, EmptyDetailsException {
        System.out.println("***DEBUG INFO ***" + details);
        StatusInterface status = getStatus(name);
        if(details != null) { //ie: if the details are not empty
            //Where decoration happens
            for (String s : details) {
                if (s.equals("operations")) {
                    status = new ServerStatusOperations(status);
                } else if (s.equals("extensions")) {
                    status = new ServerStatusExtensions(status);
                } else if (s.equals("memory")) {
                    status = new ServerStatusMemory(status);
                } else {
                    //whatever details were given were not part of the allowed set of details
                    throw new BadDetailsException();
                }
            }
        } else {
            throw new EmptyDetailsException();
        }
        return status;
    }
}

