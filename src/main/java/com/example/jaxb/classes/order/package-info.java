@XmlSchema(
    xmlns = {
      @XmlNs(prefix = "cli", namespaceURI = "https://www.example.com/client"),
      @XmlNs(prefix = "prod", namespaceURI = "https://www.example.com/client")
    },
    namespace = "http://www.example.com/order",
    elementFormDefault = XmlNsForm.QUALIFIED,
    attributeFormDefault = XmlNsForm.UNSET)
package com.example.jaxb.classes.order;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;
