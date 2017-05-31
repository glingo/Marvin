package com.marvin.component.routing.xml;

import com.marvin.component.io.xml.XMLDocumentReader;
import com.marvin.component.io.xml.XMLReaderContext;
import com.marvin.component.routing.Route;
import com.marvin.component.routing.RouteCollection;
import com.marvin.component.util.StringUtils;
import java.util.logging.Level;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlRouteDocumentReader extends XMLDocumentReader {

    public static final String ROUTE_ELEMENT = "route";
    public static final String ROUTES_ELEMENT = "routes";

    public static final String REQUIREMENT_ELEMENT = "requirement";
    public static final String DEFAULT_ELEMENT = "default";

    public static final String KEY_ATTRIBUTE = "key";
    public static final String PREFIX_ATTRIBUTE = "prefix";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String PATH_ATTRIBUTE = "path";

    protected String prefix;

    public XmlRouteDocumentReader(XMLReaderContext context) {
        super(context);
    }

    public void registerRoutes(Document doc, RouteCollection router) {
        Element root = doc.getDocumentElement();
        doRegisterRoutes(root, router);
    }

    @Override
    protected void importResource(Element ele) {
        String prefix = ele.getAttribute(PREFIX_ATTRIBUTE);
        if (StringUtils.hasLength(prefix)) {
            this.prefix = prefix;
            this.logger.log(Level.INFO, "Found a prefix : {}", this.prefix);
        }
        super.importResource(ele);
    }

    protected void doRegisterRoutes(Element root, RouteCollection router) {

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

    private void parseRouteElement(Element ele, RouteCollection router) {
        parseElement(ele);

        if (nodeNameEquals(ele, ROUTE_ELEMENT)) {
            processRoute(ele, router);
        } else if (nodeNameEquals(ele, ROUTES_ELEMENT)) {
            // recurse
            doRegisterRoutes(ele, router);
        }
    }

    protected void processRoute(Element ele, RouteCollection collection) {
        String name = ele.getAttribute(NAME_ATTRIBUTE);
        String path = ele.getAttribute(PATH_ATTRIBUTE);

        if (StringUtils.hasLength(name)) {
            Route route = new Route();

            if (this.prefix != null) {
                path = this.prefix + path;
            }
            route.setPath(path);
            NodeList nl = ele.getChildNodes();
            for (int i = 0; i < nl.getLength(); i++) {
                Node node = nl.item(i);
                if (node instanceof Element) {
                    parseDefaultElement((Element) node, route);
                    parseRequirementElement((Element) node, route);
                }
            }
            collection.addRoute(name, route);
        }
    }

    protected void processDefault(Element ele, Route route) {
        String key = ele.getAttribute(KEY_ATTRIBUTE);

        if (StringUtils.hasLength(key)) {
            route.addDefault(key, ele.getTextContent().trim());
        }
    }

    protected void processRequirement(Element ele, Route route) {
        String key = ele.getAttribute(KEY_ATTRIBUTE);

        if (StringUtils.hasLength(key)) {
            route.addRequirement(key, ele.getTextContent().trim());
        }
    }

    private void parseDefaultElement(Element ele, Route route) {
        if (nodeNameEquals(ele, DEFAULT_ELEMENT)) {
            processDefault(ele, route);
        }
    }

    private void parseRequirementElement(Element ele, Route route) {
        if (nodeNameEquals(ele, REQUIREMENT_ELEMENT)) {
            processRequirement(ele, route);
        }
    }

}
