package com.marvin.old.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Classe utilitaire pour parsing Xpath avec DOM.
 */
public final class XPathUtils {

    /**
     * Suppression du contructeur par defaut.
     */
    private XPathUtils() {
    }

    /**
     * Construction d'un objet DOM depuis un flux entrant.
     *
     * @param is flux en entr�e
     * @throws ParserConfigurationException erreur configuration parser
     * @throws IOException erreur lecture flux
     * @throws SAXException erreur parsing flux
     * @return un objet document dom
     */
    public static Document getDomDocumentFromStream(final InputStream is)
            throws SAXException, IOException, ParserConfigurationException {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
    }

    /**
     * Evaluation d'une expression XPATH.
     *
     * @param document document DOM.
     * @param expression expression xpath.
     * @param retour type �l�ment retourn�.
     * @return element du XML.
     * @throws XPathExpressionException erreur lors de la validation du XPATH.
     */
    private static Object getValueFromXPathDOM(final Document document, final String expression, final QName retour)
            throws XPathExpressionException {
        XPathFactory myXPathFactoy = XPathFactory.newInstance();
        XPathExpression exp = myXPathFactoy.newXPath().compile(expression);
        return exp.evaluate(document, retour);
    }

    /**
     * R�cup�ration d'un attribut de type 'chaine de caract�re' depuis son
     * expression XPATH.
     *
     * @param document document DOM.
     * @param expression expression xpath.
     * @return attribut recherch�
     * @throws XPathExpressionException erreur lors de la validation du XPATH.
     */
    public static String getStringAttributeValueFromXPathDOM(final Document document, final String expression)
            throws XPathExpressionException {
        return (String) getValueFromXPathDOM(document, expression, XPathConstants.STRING);
    }

    /**
     * R�cup�ration d'un attribut de type 'nombre' depuis son expression XPATH.
     *
     * @param document document DOM.
     * @param expression expression xpath.
     * @return attribut recherch�
     * @throws XPathExpressionException erreur lors de la validation du XPATH.
     */
    public static Double getDoubleAttributeValueFromXPathDOM(final Document document, final String expression)
            throws XPathExpressionException {
        return (Double) getValueFromXPathDOM(document, expression, XPathConstants.NUMBER);
    }

    /**
     * R�cup�ration d'un attribut de type 'boolean' depuis son expression XPATH.
     *
     * @param document document DOM.
     * @param expression expression xpath.
     * @return attribut recherch�
     * @throws XPathExpressionException erreur lors de la validation du XPATH.
     */
    public static Boolean getBooleanAttributeValueFromXPathDOM(final Document document, final String expression)
            throws XPathExpressionException {
        return (Boolean) getValueFromXPathDOM(document, expression, XPathConstants.BOOLEAN);
    }

    /**
     * R�cup�ration d'un attribut de type 'date' depuis son expression XPATH.
     *
     * @param document document DOM.
     * @param expression expression xpath.
     * @param sdf simple date format to parse attribute
     * @return attribut recherch�
     * @throws XPathExpressionException erreur lors de la validation du XPATH.
     * @throws ParseException erreur lors du parsing de la date, pattern non
     * respect�.
     */
    public static Date getDateAttributeValueFromXPathDOM(final Document document, final String expression,
            final SimpleDateFormat sdf)
            throws XPathExpressionException, ParseException {
        String value = getStringAttributeValueFromXPathDOM(document, expression);
        return sdf.parse(value);
    }

    /**
     * R�cup�ration d'un attribut de type 'date' depuis son expression XPATH.
     *
     * @param document document DOM.
     * @param expression expression xpath.
     * @param datePattern pattern pour la date.
     * @return attribut recherch�.
     * @throws XPathExpressionException erreur lors de la validation du XPATH.
     * @throws ParseException erreur lors du parsing de la date, pattern non
     * respect�.
     */
    public static Date getDateAttributeValueFromXPathDOM(final Document document, final String expression,
            final String datePattern)
            throws XPathExpressionException, ParseException {
        SimpleDateFormat localSdf = new SimpleDateFormat(datePattern);
        return getDateAttributeValueFromXPathDOM(document, expression, localSdf);
    }

    /**
     * R�cup�ration d'un attribut de type 'date' depuis son expression XPATH.
     *
     * @param document document DOM.
     * @param expression expression xpath.
     * @param sdf simple date format.
     * @return attribut recherch�.
     * @throws XPathExpressionException erreur lors de la validation du XPATH.
     * @throws ParseException erreur lors du parsing de la date, pattern non
     * respect�.
     */
    public static Calendar getCalendarAttributeValueFromXPathDOM(final Document document, final String expression,
            final SimpleDateFormat sdf)
            throws XPathExpressionException, ParseException {
        Date adate = getDateAttributeValueFromXPathDOM(document, expression, sdf);
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(adate);
        return aCalendar;
    }

    /**
     * R�cup�ration d'un attribut de type 'date' depuis son expression XPATH.
     *
     * @param document document DOM.
     * @param expression expression xpath.
     * @param datePattern pattern pour la date.
     * @return attribut recherch�.
     * @throws XPathExpressionException erreur lors de la validation du XPATH.
     * @throws ParseException erreur lors du parsing de la date, pattern non
     * respect�.
     */
    public static Calendar getCalendarAttributeValueFromXPathDOM(final Document document, final String expression,
            final String datePattern)
            throws XPathExpressionException, ParseException {
        Date adate = getDateAttributeValueFromXPathDOM(document, expression, datePattern);
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(adate);
        return aCalendar;
    }

    /**
     * R�cup�ration d'un attribut de type chaine de caract�re depuis son
     * expression XPATH.
     *
     * @param document document DOM.
     * @param expression expression xpath.
     * @return attribut recherch�
     * @throws XPathExpressionException erreur lors de la validation du XPATH.
     */
    public static Node getNodeFromXPathDOM(final Document document, final String expression)
            throws XPathExpressionException {
        return (Node) getValueFromXPathDOM(document, expression, XPathConstants.NODE);
    }

    /**
     * R�cup�ration d'un attribut de type chaine de caract�re depuis son
     * expression XPATH.
     *
     * @param document document DOM.
     * @param expression expression xpath.
     * @return attribut recherch�
     * @throws XPathExpressionException erreur lors de la validation du XPATH.
     */
    public static NodeList getNodeListFromXPathDOM(final Document document, final String expression)
            throws XPathExpressionException {
        return (NodeList) getValueFromXPathDOM(document, expression, XPathConstants.NODESET);
    }

}
