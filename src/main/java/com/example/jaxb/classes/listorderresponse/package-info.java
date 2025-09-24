@XmlSchema(
    namespace = "http://www.example.com/response",
    xmlns = {
      @XmlNs(prefix = "ord", namespaceURI = "http://www.example.com/order"),
      @XmlNs(prefix = "cli", namespaceURI = "https://www.example.com/client"),
      @XmlNs(prefix = "prod", namespaceURI = "http://www.example.com/product")
    },
    elementFormDefault = XmlNsForm.QUALIFIED,
    attributeFormDefault = XmlNsForm.UNSET)
package com.example.jaxb.classes.listorderresponse;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;
