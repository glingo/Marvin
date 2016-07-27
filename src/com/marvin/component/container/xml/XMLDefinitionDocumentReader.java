/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.container.xml;

import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.container.config.Definition;
import com.marvin.component.container.config.Reference;
import com.marvin.component.util.StringUtils;
import java.util.concurrent.ExecutionException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author cdi305
 */
public class XMLDefinitionDocumentReader {

    public static final String IMPORT_ELEMENT = "import";
    public static final String SERVICE_ELEMENT = "service";
    public static final String SERVICES_ELEMENT = "services";
    public static final String ARGUMENT_ELEMENT = "argument";
    public static final String TYPE_ATTRIBUTE = "type";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String INDEX_ATTRIBUTE = "index";

    public void registerBeanDefinitions(Document doc, ContainerBuilder builder) {
//            this.readerContext = readerContext;
//            logger.debug("Loading bean definitions");
        Element root = doc.getDocumentElement();
        doRegisterDefinitions(root, builder);
    }

    protected void doRegisterDefinitions(Element root, ContainerBuilder builder) {

//        preProcessXml(root);
        NodeList nl = root.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                parseDefinitionElement(ele, builder);
            }
        }
//        postProcessXml(root); 

    }

    private void parseDefinitionElement(Element ele, ContainerBuilder builder) {
        if (nodeNameEquals(ele, IMPORT_ELEMENT)) {
//            importDefinitionResource(ele);
        } else if (nodeNameEquals(ele, SERVICE_ELEMENT)) {
            processDefinition(ele, builder);
        } else if (nodeNameEquals(ele, SERVICES_ELEMENT)) {
            // recurse
            doRegisterDefinitions(ele, builder);
        }
    }

    private void parseArgumentElements(Element serviceEle, Definition definition) {
        NodeList nl = serviceEle.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element && nodeNameEquals(node, ARGUMENT_ELEMENT)) {
                parseArgumentElement((Element) node, definition);
            }
        }
    }

    private void parseArgumentElement(Element argEle, Definition definition) {
        String typeAttr = argEle.getAttribute(TYPE_ATTRIBUTE);
        String nameAttr = argEle.getAttribute(NAME_ATTRIBUTE);
        String indexAttr = argEle.getAttribute(INDEX_ATTRIBUTE);

        if (StringUtils.hasLength(indexAttr)) {
            try {
                int index = Integer.parseInt(indexAttr);

                if (index > 0) {

                }
            } catch (NumberFormatException ex) {
//                error("Attribute 'index' of tag 'constructor-arg' must be an integer", ele);
            }

        }

        definition.addArgument(argEle);
    }

    public Object parsePropertyValue(Element ele, Definition def, String propertyName) {
        String elementName = (propertyName != null)
                ? "<property> element for property '" + propertyName + "'"
                : "<constructor-arg> element";

        // Should only have one child element: ref, value, list, etc.
        NodeList nl = ele.getChildNodes();
        Element subElement = null;
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element
                    && !nodeNameEquals(node, DESCRIPTION_ELEMENT)
                    && !nodeNameEquals(node, META_ELEMENT)) {

                // Child element is what we're looking for.
                if (subElement != null) {
//                    error(elementName + " must not contain more than one sub-element", ele);
                } else {
                    subElement = (Element) node;
                }
            }
        }

        boolean hasRefAttribute = ele.hasAttribute(REF_ATTRIBUTE);
        boolean hasValueAttribute = ele.hasAttribute(VALUE_ATTRIBUTE);
        
        if ((hasRefAttribute && hasValueAttribute)
                || ((hasRefAttribute || hasValueAttribute) && subElement != null)) {
//            error(elementName
//                    + " is only allowed to contain either 'ref' attribute OR 'value' attribute OR sub-element", ele);
        }

        if (hasRefAttribute) {
            String refName = ele.getAttribute(REF_ATTRIBUTE);
            if (!StringUtils.hasText(refName)) {
                error(elementName + " contains empty 'ref' attribute", ele);
            }
            Reference ref = new Reference(refName);
//            ref.setSource(extractSource(ele));
            return ref;
        } else if (hasValueAttribute) {
            TypedStringValue valueHolder = new TypedStringValue(ele.getAttribute(VALUE_ATTRIBUTE));
            valueHolder.setSource(extractSource(ele));
            return valueHolder;
        } else if (subElement != null) {
            return parsePropertySubElement(subElement, bd);
        } else {
            // Neither child element nor "ref" or "value" attribute found.
            error(elementName + " must specify a ref or value", ele);
            return null;
        }
    }

    protected void processDefinition(Element ele, ContainerBuilder builder) {
        String id = ele.getAttribute("id");
        String name = ele.getAttribute("class");

        Definition definition = new Definition();
        definition.setClassName(name);

        builder.addDefinition(id, definition);
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
