//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.02 at 09:04:22 PM EET 
//


package Client.demo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MyMsg complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MyMsg">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *         &lt;element name="Msg" type="{}Msgtype" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MyMsg", propOrder = {
    "header",
    "msg"
})
public class MyMsg {

    @XmlElement(name = "Header", required = true)
    protected Object header;
    @XmlElement(name = "Msg", required = true)
    protected List<Msgtype> msg;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setHeader(Object value) {
        this.header = value;
    }

    /**
     * Gets the value of the msg property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the msg property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMsg().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Msgtype }
     * 
     * 
     */
    public List<Msgtype> getMsg() {
        if (msg == null) {
            msg = new ArrayList<Msgtype>();
        }
        return this.msg;
    }

}
