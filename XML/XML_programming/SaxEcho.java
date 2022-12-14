import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxEcho extends DefaultHandler {
    protected static final String XML_FILE_NAME = "book_catalog.xml";

    public static void main(String[] argv) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            // Set up output stream
            out = new OutputStreamWriter(System.out, StandardCharsets.UTF_8);

            // Parse the input
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new File(XML_FILE_NAME), new SaxEcho());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        System.exit(0);
    }

    static private Writer out;

    // ===========================================================
    // Methods in SAX DocumentHandler
    // ===========================================================


    /*
     * Called when the Parser starts parsing the Current XML File.
     */
    @Override
    public void startDocument()
            throws SAXException {
        showData("<?xml version='1.0' encoding='UTF-8'?>");
        newLine();
    }

    /*
     *Called when the Parser Completes parsing the Current XML File.
     */
    @Override
    public void endDocument()
            throws SAXException {
        try {
            newLine();
            out.flush();
        } catch (IOException e) {
            throw new SAXException("I/O error", e);
        }
    }

    /*
     * Called when the starting of the Element is reached. For Example if we have Tag
     * called <Title> ... </Title>, then this method is called when <Title> tag is
     * Encountered while parsing the Current XML File. The AttributeList Parameter has
     * the list of all Attributes declared for the Current Element in the XML File.
     */
    @Override
    public void startElement(String namespaceURI,
                             String sName, // simple name
                             String qName, // qualified name
                             Attributes attrs)
            throws SAXException {
        showData("<" + qName);
        if (attrs != null) {
            for (int i = 0; i < attrs.getLength(); i++) {
                showData(" ");
                showData(attrs.getQName(i) + "=\"" + attrs.getValue(i) + "\"");
            }
        }
        showData(">");
    }


    /*
     * Called when the Ending of the current Element is reached. For example in the
     * above explanation, this method is called when </Title> tag is reached
     */
    @Override
    public void endElement(String namespaceURI,
                           String sName, // simple name
                           String qName  // qualified name
    )
            throws SAXException {
        showData("</" + qName + ">");
    }

    /*
     * While Parsing the XML file, if extra characters like space or enter Character
     * are encountered then this method is called. If you don't want to do anything
     * special with these characters, then you can normally leave this method blank.
     */
    @Override
    public void characters(char buf[], int offset, int len)
            throws SAXException {
        String s = new String(buf, offset, len);
        showData(s);
    }

    // ===========================================================
    // Helpers Methods
    // ===========================================================

    // Wrap I/O exceptions in SAX exceptions, to
    // suit handler signature requirements
    private void showData(String s)
            throws SAXException {
        try {
            out.write(s);
            out.flush();
        } catch (IOException e) {
            throw new SAXException("I/O error", e);
        }
    }

    // Start a new line
    private void newLine()
            throws SAXException {
        String lineEnd = System.getProperty("line.separator");
        try {
            out.write(lineEnd);
        } catch (IOException e) {
            throw new SAXException("I/O error", e);
        }
    }
}
