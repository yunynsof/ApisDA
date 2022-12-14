
package hn.com.tigo.josm.orchestrator.adapter.cbs2.task;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.3.7
 * 2022-03-07T11:48:51.216-06:00
 * Generated source version: 3.3.7
 */

@WebFault(name = "AdapterException", targetNamespace = "http://task.cbs2.adapter.orchestrator.josm.tigo.com.hn/")
public class AdapterException_Exception extends Exception {

    private hn.com.tigo.josm.orchestrator.adapter.cbs2.task.AdapterException adapterException;

    public AdapterException_Exception() {
        super();
    }

    public AdapterException_Exception(String message) {
        super(message);
    }

    public AdapterException_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public AdapterException_Exception(String message, hn.com.tigo.josm.orchestrator.adapter.cbs2.task.AdapterException adapterException) {
        super(message);
        this.adapterException = adapterException;
    }

    public AdapterException_Exception(String message, hn.com.tigo.josm.orchestrator.adapter.cbs2.task.AdapterException adapterException, java.lang.Throwable cause) {
        super(message, cause);
        this.adapterException = adapterException;
    }

    public hn.com.tigo.josm.orchestrator.adapter.cbs2.task.AdapterException getFaultInfo() {
        return this.adapterException;
    }
}
