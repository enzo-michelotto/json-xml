@XmlSchema(
    xmlns = {
      @XmlNs(prefix = "cli", namespaceURI = "https://www.example.com/client"),
      @XmlNs(prefix = "prod", namespaceURI = "http://www.example.com/product")
    },
    namespace = "http://www.example.com/product",
    elementFormDefault = XmlNsForm.QUALIFIED,
    attributeFormDefault = XmlNsForm.UNSET)
package com.example.jaxb.classes.product;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;
