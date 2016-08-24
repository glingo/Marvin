package com.marvin.component.io.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class DocumentLoader {

    /**
     * JAXP attribute used to configure the schema language for validation.
     */
    private static final String SCHEMA_LANGUAGE_ATTRIBUTE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

    /**
     * JAXP attribute value indicating the XSD schema language.
     */
    private static final String XSD_SCHEMA_LANGUAGE = "http://www.w3.org/2001/XMLSchema";

    /**
     * Indicates that the validation should be disabled.
     */
    public static final int VALIDATION_NONE = 0;

    /**
     * Indicates that the validation mode should be auto-guessed, since we
     * cannot find a clear indication (probably choked on some special
     * characters, or the like).
     */
    public static final int VALIDATION_AUTO = 1;

    /**
     * Indicates that DTD validation should be used (we found a "DOCTYPE"
     * declaration).
     */
    public static final int VALIDATION_DTD = 2;

    /**
     * Indicates that XSD validation should be used (found no "DOCTYPE"
     * declaration).
     */
    public static final int VALIDATION_XSD = 3;

    public Document load(InputSource inputSource, int validationMode, boolean namespaceAware) throws Exception {
        DocumentBuilderFactory factory = createDocumentBuilderFactory(validationMode, namespaceAware);
        DocumentBuilder builder = createDocumentBuilder(factory); //, entityResolver, errorHandler
        return builder.parse(inputSource);
    }

    protected DocumentBuilder createDocumentBuilder(DocumentBuilderFactory factory) throws ParserConfigurationException {
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        return docBuilder;
    }

    protected DocumentBuilderFactory createDocumentBuilderFactory(int validationMode, boolean namespaceAware)
            throws ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(namespaceAware);

        if (validationMode != VALIDATION_NONE) {
            factory.setValidating(true);
            if (validationMode == VALIDATION_XSD) {
                // Enforce namespace aware for XSD...
                factory.setNamespaceAware(true);
                try {
                    factory.setAttribute(SCHEMA_LANGUAGE_ATTRIBUTE, XSD_SCHEMA_LANGUAGE);
                } catch (IllegalArgumentException ex) {
                    ParserConfigurationException pcex = new ParserConfigurationException(
                            "Unable to validate using XSD: Your JAXP provider [" + factory
                            + "] does not support XML Schema. Are you running on Java 1.4 with Apache Crimson? "
                            + "Upgrade to Apache Xerces (or Java 1.5) for full XSD support.");
                    pcex.initCause(ex);
                    throw pcex;
                }
            }
        }

        return factory;
    }
}
