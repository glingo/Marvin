/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.old.routing.loader;

import com.marvin.old.dependency.ContainerBuilder;
import com.marvin.old.dependency.loader.XmlFileLoader;
import com.marvin.old.locator.FileLocator;
//import com.marvin.component.routing.Route;
//import com.marvin.component.util.classloader.ClassLoaderUtil;
//import com.marvin.component.util.introspection.AnnotationUtils;
//import java.lang.reflect.Method;
//import java.util.Arrays;
//import java.util.List;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.traversal.DocumentTraversal;
//import org.w3c.dom.traversal.NodeFilter;
//import org.w3c.dom.traversal.TreeWalker;

/**
 *
 * @author Dr.Who
 */
public class RoutingXmlFileLoader extends XmlFileLoader {
//
//    protected DocumentTraversal traversal;
//    protected Element root;

    public RoutingXmlFileLoader(FileLocator locator, ContainerBuilder builder) {
        super(locator, builder);
    }

//    protected void parseDocument(Document document) throws Exception {
//        this.traversal = (DocumentTraversal) document;
//        this.root = document.getDocumentElement();
//        parseRoutes(root);
////        this.builder.build();
//    }
    
//    private Object[] parseArguments(Element route) throws Exception {
//        NodeFilter filter = new XmlNodeFilter("argument");
//        TreeWalker walker = this.traversal.createTreeWalker(route, NodeFilter.SHOW_ELEMENT, filter, true);
//
//        // describe current node:
//        Element parent = (Element) walker.getCurrentNode();
//        Element child = (Element) walker.firstChild();
//
//        // traverse children:
//        while (child != null) {
//            
////            route.
////            routes.add(this.parseRoute(child));
//            child = (Element) walker.nextSibling();
//        }
//
//        // return position to the current (level up):
//        walker.setCurrentNode(parent);
//        
//    }

//    private Route parseRoute(Element route) throws Exception {
//        
//        String id = route.getAttribute("id");
//        String name = route.getAttribute("controller");
//        String action = route.getAttribute("action");
//        String path = route.getAttribute("path");
//        
//        if (name == null || "".equals(name)) {
//            throw new Exception("name can not be null");
//        }
//        
//        if (id == null || "".equals(id)) {
//            throw new Exception("id can not be null");
//        }
//        
//        Class<?> controller = ClassLoaderUtil.loadClass(name);
//        Object[] arguments = this.parseArguments(route);
//        Class[] types = this.getArgumentsTypes(route);
//        
//        controller.getDeclaredMethod(action, this.parseArguments(route));
////        List<Method> actions = AnnotationUtils.getMethodsAnnotated(Route.class, controller);
//        
//        return new Route(id, path, controller);
//    }

//    private void parseRoutes(Element root) throws Exception {
//        RouteCollection collection = new RouteCollection();
//        NodeFilter filter = new XmlNodeFilter("service", Arrays.asList("container", "services"));
//        TreeWalker walker = this.traversal.createTreeWalker(root, NodeFilter.SHOW_ELEMENT, filter, true);
//
//        // describe current node:
//        Element parent = (Element) walker.getCurrentNode();
//        Element child = (Element) walker.firstChild();
//            
//        System.out.println("RouteAnnotationLoader");
//        
//        // traverse children:
//        while (child != null) {
//            routes.add(this.parseRoute(child));
//            child = (Element) walker.nextSibling();
//        }
//
//        // return position to the current (level up):
//        walker.setCurrentNode(parent);
//
//    }

}
