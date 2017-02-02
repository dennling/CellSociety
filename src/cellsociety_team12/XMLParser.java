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


public class XMLParser {
	
	
	
	private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	
	public GameData getData(File data) {
		Element root = getRoot(data);
		if (!isValidInput(root, GameData.DATA_TYPE)) {
			throw new XMLException();
		}
		HashMap<String, String> dataFields = new HashMap<String, String>();
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
			throw new XMLException();
		} catch (IOException e) {
			throw new XMLException();
		}
	}
	
	private boolean isValidInput(Element root, String dataType) {
		return root.getAttribute(dataType).equals(dataType);
	}
	
	private String getTextValue(Element root, String dataField) {
		String allValues = "";
		NodeList values = root.getElementsByTagName(dataField);
		if (values == null) {
			return "";
		} else {
			for (int i = 0; i < values.getLength(); i++) {
				allValues += values.item(i).getTextContent();
				if (i != values.getLength()) {
					allValues += " ";
				}
			}
			return allValues;
		}
	}
	
	
	private static DocumentBuilder getDocumentBuilder() {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new XMLException();
		}
	}

}
