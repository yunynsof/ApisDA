/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.com.tigo.da.apisda;


import hn.com.tigo.josm.adapter.requesttype.v1.TaskRequestType;
import hn.com.tigo.josm.adapter.requesttype.v1.TaskResponseType;
import hn.com.tigo.josm.orchestrator.adapter.cbs2.task.AdapterException_Exception;
import hn.com.tigo.josm.orchestrator.adapter.cbs2.task.CBSQueryCustomerInfoTask;
import hn.com.tigo.josm.orchestrator.adapter.cbs2.task.CBSQueryCustomerInfoTaskService;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import javax.xml.ws.BindingProvider;

/**
 *
 * @author Leovijil
 */
public class CBSQueryCustomerInfoServiceProvider {
    
	private String endPoint;
	private final URL url;
	CBSQueryCustomerInfoTaskService webService = null;
	CBSQueryCustomerInfoTask webServicePort = null;
	
	/**
	 * 
	 * @param endPoint
	 * @throws MalformedURLException
	 */
	public CBSQueryCustomerInfoServiceProvider(String endPoint) throws MalformedURLException
	{
		this.endPoint = endPoint;
		this.url = new URL(endPoint + "?wsdl");
		webService = new CBSQueryCustomerInfoTaskService(this.url);
		webServicePort = webService.getCBSQueryCustomerInfoTaskPort();
		Map <String, Object> ctx= ((BindingProvider)webServicePort).getRequestContext();
		ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint);
	}
	

	public TaskResponseType excuteTask(final TaskRequestType request) throws AdapterException_Exception {
		return webServicePort.executeTask(request);
	}
	
	/**
	 * @return the url
	 */
	public URL getUrl() {
		return url;
	}

	/**
	 * @return the endPoint
	 */
	public String getEndPoint() {
		return endPoint;
	}

	/**
	 * @param endPoint the endPoint to set
	 */
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	

}
