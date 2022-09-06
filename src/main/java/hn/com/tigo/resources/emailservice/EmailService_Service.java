
package hn.com.tigo.resources.emailservice;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "EmailService", targetNamespace = "http://tigo.hn/resources/emailService", wsdlLocation = "file:/C:/Projects/Tigo/Varios/NotifyEcoFacturaListener/src/main/resources/EmailService.wsdl")
public class EmailService_Service
    extends Service
{

    private final static URL EMAILSERVICE_WSDL_LOCATION;
    private final static WebServiceException EMAILSERVICE_EXCEPTION;
    private final static QName EMAILSERVICE_QNAME = new QName("http://tigo.hn/resources/emailService", "EmailService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Projects/Tigo/Varios/NotifyEcoFacturaListener/src/main/resources/EmailService.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        EMAILSERVICE_WSDL_LOCATION = url;
        EMAILSERVICE_EXCEPTION = e;
    }

    public EmailService_Service() {
        super(__getWsdlLocation(), EMAILSERVICE_QNAME);
    }

    public EmailService_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), EMAILSERVICE_QNAME, features);
    }

    public EmailService_Service(URL wsdlLocation) {
        super(wsdlLocation, EMAILSERVICE_QNAME);
    }

    public EmailService_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, EMAILSERVICE_QNAME, features);
    }

    public EmailService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EmailService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns EmailService
     */
    @WebEndpoint(name = "EmailService_Port")
    public EmailService getEmailServicePort() {
        return super.getPort(new QName("http://tigo.hn/resources/emailService", "EmailService_Port"), EmailService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EmailService
     */
    @WebEndpoint(name = "EmailService_Port")
    public EmailService getEmailServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://tigo.hn/resources/emailService", "EmailService_Port"), EmailService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (EMAILSERVICE_EXCEPTION!= null) {
            throw EMAILSERVICE_EXCEPTION;
        }
        return EMAILSERVICE_WSDL_LOCATION;
    }

}
