package com.marvin.component.io.xml;

import com.marvin.component.parser.DelegatingParser;
import com.marvin.component.parser.ParserResolver;
import com.marvin.component.util.StringUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XMLDocumentReader {
    protected final Logger logger = Logger.getLogger(getClass().getName());

    public static final String IMPORT_ELEMENT       = "import";
    public static final String RESOURCE_ATTRIBUTE   = "resource";

    protected final ParserResolver parserResolver   = new ParserResolver();
    
    protected DelegatingParser parser               = new DelegatingParser(this.parserResolver);
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
            this.logger.log(Level.SEVERE, "Resource location must not be empty at {0}", ele);
            return;
        }
        
        if(!location.startsWith("/")) {
            location = String.format("%s/%s", this.context.getParent(), location);
        }
        
        this.logger.log(Level.FINEST, "IMPORT from : {0}", location);

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
