/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.old.filter;

import java.util.Arrays;
import java.util.List;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;

/**
 *
 * @author Dr.Who
 */
public class XmlNodeFilter extends Filter<Node> implements NodeFilter {

    public XmlNodeFilter(String name, String[] skips) {
        super(name, Arrays.asList(skips));
    }
    
    public XmlNodeFilter(String name, List<String> skips) {
        super(name, skips);
    }
    
    public XmlNodeFilter(String name) {
        super(name);
    }

    @Override
    public short acceptNode(Node n) {
        String competitor = n.getNodeName();
        
        if(this.skip(competitor)) {
            return NodeFilter.FILTER_SKIP;
        }
        
        if(this.accept(competitor)) {
            return NodeFilter.FILTER_ACCEPT;
        }
        
        return NodeFilter.FILTER_REJECT;
    }

}
