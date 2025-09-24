@XmlSchema(
    xmlns = {@XmlNs(prefix = "cli", namespaceURI = "https://www.example.com/client")},
    namespace = "http://www.example.com/request",
    elementFormDefault = XmlNsForm.QUALIFIED,
    attributeFormDefault = XmlNsForm.UNSET)
package com.example.jaxb.classes.listorderrequest;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;
