package com.marvin.component.routing.xml;

import com.marvin.component.io.xml.XMLDocumentReader;
import com.marvin.component.io.xml.XMLReaderContext;
import com.marvin.component.routing.Router;
import com.marvin.component.routing.config.Route;
import com.marvin.component.util.StringUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author cdi305
 */
public class XMLRouteDocumentReader extends XMLDocumentReader {

    public static final String ROUTE_ELEMENT = "route";
    public static final String ROUTES_ELEMENT = "routes";

    public XMLRouteDocumentReader(XMLReaderContext context) {
        super(context);
    }
    
    public void registerRoutes(Document doc, Router router) {
//            this.readerContext = readerContext;
//            logger.debug("Loading bean definitions");
        Element root = doc.getDocumentElement();
        doRegisterRoutes(root, router);
    }

    protected void doRegisterRoutes(Element root, Router router) {

//        preProcessXml(root);
        NodeList nl = root.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                parseRouteElement(ele, router);
            }
        }
//        postProcessXml(root); 

    }

    private void parseRouteElement(Element ele, Router router) {
        
        parseElement(ele);
        
        if (nodeNameEquals(ele, ROUTE_ELEMENT)) {
            processRoute(ele, router);
        } else if (nodeNameEquals(ele, ROUTES_ELEMENT)) {
            // recurse
            doRegisterRoutes(ele, router);
        }
    }

    protected void processRoute(Element ele, Router router) {
        String name = ele.getAttribute("name");
        String controller = ele.getAttribute("controller");
        String path = ele.getAttribute("path");

        if (StringUtils.hasLength(name)) {
            Route route = new Route();
            route.setPath(path);
            route.setController(controller);
            router.addRoute(name, route);
        }
    }


//    public boolean isDefaultNamespace(String namespaceUri) {
//        return (!StringUtils.hasLength(namespaceUri) || BEANS_NAMESPACE_URI.equals(namespaceUri));
//    }
//    public boolean isDefaultNamespace(Node node) {
//        return isDefaultNamespace(getNamespaceURI(node));
//    }
//    private boolean isCandidateElement(Node node) {
//        return (node instanceof Element && (isDefaultNamespace(node) || !isDefaultNamespace(node.getParentNode())));
//    }
}
