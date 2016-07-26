package com.marvin.old.dependency.loader;

import com.marvin.old.builder.Builder;
import com.marvin.old.dependency.Container;
import com.marvin.old.dependency.Definition;
import com.marvin.old.dependency.Reference;
import com.marvin.old.filter.XmlNodeFilter;
import com.marvin.old.locator.ILocator;
import com.marvin.old.loader.XmlLoader;
import com.marvin.old.util.classloader.ClassLoaderUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

/**
 *
 * @author Dr.Who
 */
public class XmlFileLoader extends XmlLoader<Container, Definition> {
    
    protected NodeFilter serviceFilter = new XmlNodeFilter("service", Arrays.asList("container", "services"));
    protected NodeFilter argumentFilter = new XmlNodeFilter("argument", Arrays.asList(new String[]{}));

    public XmlFileLoader(ILocator<File> locator, Builder<Container, Definition> builder) {
        super(locator, builder);
    }

    @Override
    public boolean supports(String resource) {
        return resource != null && resource.endsWith("xml");
    }

    @Override
    public void load(File file) {
        super.load(file);
        parseServices(this.root);
    }
    
    private Object getArgument(Element argument) {
        String type = argument.getAttribute("type");
        Object value = argument.getTextContent();
        switch (type) {
            case "collection":
                return this.getArgumentsAsCollection(argument);

//            case "array":
//                return this.getArguments(argument).toArray();

            case "service":
                String id = argument.getAttribute("id");
                return new Reference(id);

            default:
                return super.resolveValue(value.toString());
        }
    }
    
    private List<Object> getArguments(Element service) {
        List<Object> args = new ArrayList<>();

        NodeFilter filter = new XmlNodeFilter("argument", Arrays.asList(new String[]{}));
        TreeWalker walker = traversal.createTreeWalker(service, NodeFilter.SHOW_ELEMENT, filter, true);

        // describe current node:
        Element parent = (Element) walker.getCurrentNode();
        Element argument = (Element) walker.firstChild();

        // traverse children:
        while (argument != null) {
            Object arg = this.getArgument(argument);
            args.add(arg);
            argument = (Element) walker.nextSibling();
        }

        // return position to the current (level up):
        walker.setCurrentNode(parent);
        return args;
    }

    private Collection<Object> getArgumentsAsCollection(Element service) {
        return this.getArguments(service);
    }

    private void parseServices(Element root) {
        NodeFilter filter = new XmlNodeFilter("service", Arrays.asList("container", "services"));
        TreeWalker walker = this.traversal.createTreeWalker(root, NodeFilter.SHOW_ELEMENT, filter, true);

        // describe current node:
        Element parent = (Element) walker.getCurrentNode();
        Element child = (Element) walker.firstChild();

        // traverse children:
        while (child != null) {
            this.parseService(child);
            child = (Element) walker.nextSibling();
        }

        // return position to the current (level up):
        walker.setCurrentNode(parent);

    }
    
    private void parseService(Element service) {
        String id = service.getAttribute("id");
        String name = service.getAttribute("class");
        
        if (name == null || "".equals(name)) {
            return;
        }
        
        if (id == null || "".equals(id)) {
            return;
        }
        
        List<Object> args = this.getArguments(service);
        Class type = ClassLoaderUtil.loadClass(name);
        Definition def = new Definition(id, type, args.toArray());
        this.builder.stock(id, def);
    }

}
