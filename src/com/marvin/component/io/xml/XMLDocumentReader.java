/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.io.xml;

import com.marvin.component.parser.DelegatingParser;
import com.marvin.component.parser.ParserResolver;
import com.marvin.component.util.StringUtils;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author cdi305
 */
public class XMLDocumentReader {

    public static final String IMPORT_ELEMENT = "import";
    public static final String RESOURCE_ATTRIBUTE = "resource";

    protected final ParserResolver parserResolver = new ParserResolver();
    protected DelegatingParser parser = new DelegatingParser(parserResolver);
    protected XMLReaderContext context;

    public XMLDocumentReader(XMLReaderContext context) {
        this.context = context;
    }

    protected void parseElement(Element ele) {
        if (nodeNameEquals(ele, IMPORT_ELEMENT)) {
            importResource(ele);
        }
    }

    protected void importResource(Element ele) {
        String location = ele.getAttribute(RESOURCE_ATTRIBUTE);

        if (!StringUtils.hasText(location)) {
            System.err.println("Resource location must not be empty");
//            getReaderContext().error("Resource location must not be empty", ele);
            return;
        }

        this.context.getReader().read(location);
    }

    public String getNamespaceURI(Node node) {
        return node.getNamespaceURI();
    }

    public String getLocalName(Node node) {
        return node.getLocalName();
    }

    public boolean nodeNameEquals(Node node, String desiredName) {
        return desiredName.equals(node.getNodeName()) || desiredName.equals(getLocalName(node));
    }
}
