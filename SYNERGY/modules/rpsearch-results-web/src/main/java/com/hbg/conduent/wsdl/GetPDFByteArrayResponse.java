//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.09.05 at 04:49:13 PM IST 
//


package com.hbg.conduent.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GetPDFByteArrayResult" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getPDFByteArrayResult"
})
@XmlRootElement(name = "GetPDFByteArrayResponse")
public class GetPDFByteArrayResponse {

    @XmlElement(name = "GetPDFByteArrayResult")
    protected byte[] getPDFByteArrayResult;

    /**
     * Gets the value of the getPDFByteArrayResult property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getGetPDFByteArrayResult() {
        return getPDFByteArrayResult;
    }

    /**
     * Sets the value of the getPDFByteArrayResult property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setGetPDFByteArrayResult(byte[] value) {
        this.getPDFByteArrayResult = value;
    }

}
