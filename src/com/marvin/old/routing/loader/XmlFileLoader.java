package com.marvin.old.routing.loader;

import com.marvin.old.builder.Builder;
import com.marvin.old.filter.XmlNodeFilter;
import com.marvin.old.loader.XmlLoader;
import com.marvin.old.locator.ILocator;
import com.marvin.old.routing.Route;
import com.marvin.old.routing.Router;

import java.io.File;

import java.util.Arrays;

import org.w3c.dom.Element;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

public class XmlFileLoader extends XmlLoader<Router, Route> {

    public XmlFileLoader(ILocator<File> locator, Builder<Router, Route> builder) {
        super(locator, builder);
    }
    
    @Override
    public boolean supports(String type) {
        return type != null && type.contains("routing.xml");
    }

    @Override
    public void load(File file) {
        super.load(file);
        parseRoutes(this.root);
    }

    private void parseRoutes(Element root) {
        NodeFilter filter = new XmlNodeFilter("route", Arrays.asList("routing"));
        TreeWalker walker = this.traversal.createTreeWalker(root, NodeFilter.SHOW_ELEMENT, filter, true);

        // describe current node:
        Element parent = (Element) walker.getCurrentNode();
        Element child = (Element) walker.firstChild();

        // traverse children:
        while (child != null) {
            
            String name = child.getAttribute("name");
            String path = child.getAttribute("path");
            String controller = child.getAttribute("controller");
            
            Route route = new Route(name, path, controller);

            this.builder.stock(name, route);

            child = (Element) walker.nextSibling();
        }
        walker.setCurrentNode(parent);
    }
}
