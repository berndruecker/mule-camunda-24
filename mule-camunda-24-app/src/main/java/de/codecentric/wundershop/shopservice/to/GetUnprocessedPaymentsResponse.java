package de.codecentric.wundershop.shopservice.to;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://shop.de/services/to")
public class GetUnprocessedPaymentsResponse {
    // @XmlElement only works, too, but the @XmlElementWrapper makes the XML nicer. 
    @XmlElementWrapper(name="payments")
    @XmlElement(name="id")
    private List<String> payments = new ArrayList<>();

    public List<String> getPayments() {
        return payments;
    }

    public void setPayments(List<String> payments) {
        this.payments = payments;
    }
}
