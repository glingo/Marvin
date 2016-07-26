/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.old.loader;

import com.marvin.old.dependency.loader.XmlFileLoader;
import com.marvin.old.builder.Builder;
import com.marvin.old.filter.XmlNodeFilter;
import com.marvin.old.locator.ILocator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;
import org.xml.sax.SAXException;

/**
 *
 * @author Dr.Who
 * @param <P>
 * @param <M>
 */
public class XmlLoader<P, M> extends FileLoader<P, M> {
    
    protected final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    protected DocumentTraversal traversal;
    protected Element root;

    public XmlLoader(ILocator<File> locator, Builder<P, M> builder) {
        super(locator, builder);
    }

    @Override
    public boolean supports(String resource) {
        return resource != null && resource.endsWith("xml");
    }

    @Override
    public void load(File file) {
        try {
            Document document = factory.newDocumentBuilder().parse(file);
            this.traversal = (DocumentTraversal) document;
            this.root = document.getDocumentElement();
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XmlFileLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void filter(Element root, XmlNodeFilter filter, Function parser) {
        TreeWalker walker = traversal.createTreeWalker(root, NodeFilter.SHOW_ELEMENT, filter, true);
        
        Element argument = (Element) walker.firstChild();
        
        while (argument != null) {
            Collector<Element, ?, List<M>> c = Collectors.mapping(parser, Collectors.toList());
            argument = (Element) walker.nextSibling();
        }
    }
    
    public Object resolveValue(String value) {
        
        if(value == null) {
            return value;
        }
        
        switch (value) {
            case "":
                return null;
            case "null":
                return null;
            case "true":
                return true;
            case "false":
                return false;
        }
        
        if(value.matches("\\d+")) {
            return Integer.parseInt(value);
        }
        
        return value;
    }

}
