package hn.com.tigo.josm.orchestrator.adapter.cbs2.task;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.3.7
 * 2022-03-07T11:48:51.431-06:00
 * Generated source version: 3.3.7
 *
 */
@WebService(targetNamespace = "http://task.cbs2.adapter.orchestrator.josm.tigo.com.hn/", name = "CBSQueryCustomerInfoTask")
@XmlSeeAlso({com.tigo.enterprise.resources.parameters.simple.v1.schema.ObjectFactory.class, hn.com.tigo.josm.adapter.requesttype.v1.ObjectFactory.class, ObjectFactory.class})
public interface CBSQueryCustomerInfoTask {

    @WebMethod
    @Action(input = "http://task.cbs2.adapter.orchestrator.josm.tigo.com.hn/CBSQueryCustomerInfoTask/executeTaskRequest", output = "http://task.cbs2.adapter.orchestrator.josm.tigo.com.hn/CBSQueryCustomerInfoTask/executeTaskResponse", fault = {@FaultAction(className = AdapterException_Exception.class, value = "http://task.cbs2.adapter.orchestrator.josm.tigo.com.hn/CBSQueryCustomerInfoTask/executeTask/Fault/AdapterException")})
    @RequestWrapper(localName = "executeTask", targetNamespace = "http://task.cbs2.adapter.orchestrator.josm.tigo.com.hn/", className = "hn.com.tigo.josm.orchestrator.adapter.cbs2.task.ExecuteTask")
    @ResponseWrapper(localName = "executeTaskResponse", targetNamespace = "http://task.cbs2.adapter.orchestrator.josm.tigo.com.hn/", className = "hn.com.tigo.josm.orchestrator.adapter.cbs2.task.ExecuteTaskResponse")
    @WebResult(name = "return", targetNamespace = "")
    public hn.com.tigo.josm.adapter.requesttype.v1.TaskResponseType executeTask(

        @WebParam(name = "arg0", targetNamespace = "")
        hn.com.tigo.josm.adapter.requesttype.v1.TaskRequestType arg0
    ) throws AdapterException_Exception;
}
