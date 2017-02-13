package cellsociety_team12;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Used to Parse XML file to GameData
 *
 */
public class XMLParser {
	
	private static final String ROOT_ATTRIBUTE = "type";
	private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	
	public GameData getData(File data) {
		Element root = getRoot(data);
		if (!isValidInput(root, GameData.DATA_TYPE)) {
			throw new XMLException("XML file does not represent %s", GameData.DATA_TYPE);
		}
		HashMap<String, String[]> dataFields = new HashMap<String, String[]>();
		for (String field : GameData.DATA_FIELDS) {
			dataFields.put(field, getTextValue(root, field));
		}
		return new GameData(dataFields);
		
	}
	
	
	
	private Element getRoot(File data) {
		DOCUMENT_BUILDER.reset();
		try {
			Document xmlFile = (Document) DOCUMENT_BUILDER.parse(data);
			return xmlFile.getDocumentElement();
		} catch (SAXException e) {
			throw new XMLException(e);
		} catch (IOException e) {
			throw new XMLException(e);
		}
	}
	
	private boolean isValidInput(Element root, String dataType) {
		return root.getAttribute(ROOT_ATTRIBUTE).equals(dataType);
	}
	
	private String[] getTextValue(Element root, String dataField) {
		NodeList values = root.getElementsByTagName(dataField);
		if (values == null) {
			return new String[0];
		} else {
			String[] allValues = new String[values.getLength()];
			for (int i = 0; i < values.getLength(); i++) {
				allValues[i] = values.item(i).getTextContent();
			}
			return allValues;
		}
	}
	
	
	
	
	private static DocumentBuilder getDocumentBuilder() {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new XMLException(e);
		}
	}

}
