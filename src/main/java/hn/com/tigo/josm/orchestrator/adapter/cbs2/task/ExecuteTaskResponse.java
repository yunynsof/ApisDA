
package hn.com.tigo.josm.orchestrator.adapter.cbs2.task;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import hn.com.tigo.josm.adapter.requesttype.v1.TaskResponseType;


/**
 * <p>Java class for executeTaskResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="executeTaskResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://adapter.josm.tigo.com.hn/RequestType/V1}TaskResponseType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "executeTaskResponse", propOrder = {
    "_return"
})
public class ExecuteTaskResponse {

    @XmlElement(name = "return")
    protected TaskResponseType _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link TaskResponseType }
     *     
     */
    public TaskResponseType getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaskResponseType }
     *     
     */
    public void setReturn(TaskResponseType value) {
        this._return = value;
    }

}
