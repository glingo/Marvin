/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.old.parser;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Dr.Who
 * @param <T>
 */
public abstract class XmlFileParser<T> extends FileParser {

    protected final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    
    @Override
    public T parse(File parsable) throws Exception {
        Document document = factory.newDocumentBuilder().parse(parsable);
        return this.parseDocument(document);
    }
    
    protected abstract T parseDocument(Document document);
    
    protected void parseNode(Node node){
        this.parseNode(node, false);
    }
    
    protected void parseNode(Node node, boolean recursive){
        String name = node.getNodeName();
        NamedNodeMap attributes = node.getAttributes();
        
        if(node.hasChildNodes() && recursive){
            this.parseChildren(node);
        }
    }
    
    protected void parseChildren(Node node){
        this.parseChildren(node, false);
    }
    
    protected void parseChildren(Node node, boolean recursive){
        NodeList childs = node.getChildNodes();
         
        int length = childs.getLength();
        for (int i = 0; i<length; i++) {
            this.parseNode(childs.item(i), recursive);
        }
    }
}
