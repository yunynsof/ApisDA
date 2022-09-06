
package hn.com.tigo.resources.emailservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebService(name = "EmailService", targetNamespace = "http://tigo.hn/resources/emailService")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface EmailService {


    /**
     * 
     * @param cc
     * @param sendTo
     * @param attachments
     * @param subject
     * @param from
     * @param body
     * @return
     *     returns hn.tigo.resources.emailservice.GeneralResponse
     */
    @WebMethod(action = "sendMail")
    @WebResult(name = "generalResponseMsg", targetNamespace = "")
    @RequestWrapper(localName = "sendMessage", targetNamespace = "http://tigo.hn/resources/emailService", className = "hn.com.tigo.resources.emailservice.SendMessage")
    @ResponseWrapper(localName = "sendMessageResponse", targetNamespace = "http://tigo.hn/resources/emailService", className = "hn.com.tigo.resources.emailservice.SendMessageResponse")
    @Action(input = "sendMail", output = "http://tigo.hn/resources/emailService/EmailService/sendMessageResponse")
    public GeneralResponse sendMessage(
        @WebParam(name = "from", targetNamespace = "")
        String from,
        @WebParam(name = "SendTo", targetNamespace = "")
        SentDTO sendTo,
        @WebParam(name = "CC", targetNamespace = "")
        String cc,
        @WebParam(name = "subject", targetNamespace = "")
        String subject,
        @WebParam(name = "body", targetNamespace = "")
        String body,
        @WebParam(name = "attachments", targetNamespace = "")
        AttachmentsDTO attachments);

}