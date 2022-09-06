
package hn.com.tigo.josm.orchestrator.adapter.cbs2.task;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import hn.com.tigo.josm.adapter.requesttype.v1.TaskRequestType;


/**
 * <p>Java class for executeTask complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="executeTask"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="arg0" type="{http://adapter.josm.tigo.com.hn/RequestType/V1}TaskRequestType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "executeTask", propOrder = {
    "arg0"
})
public class ExecuteTask {

    protected TaskRequestType arg0;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link TaskRequestType }
     *     
     */
    public TaskRequestType getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaskRequestType }
     *     
     */
    public void setArg0(TaskRequestType value) {
        this.arg0 = value;
    }

}
