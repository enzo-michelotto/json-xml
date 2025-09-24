@XmlSchema(
    namespace = "https://www.example.com/client",
    xmlns = {@XmlNs(prefix = "cli", namespaceURI = "https://www.example.com/client")},
    elementFormDefault = XmlNsForm.QUALIFIED,
    attributeFormDefault = XmlNsForm.UNSET)
package com.example.jaxb.classes.client;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;
