package unice.dam.koubi.lebourblanc;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.util.Log;

public class StationParser {

	private DocumentBuilder builder;

	/**
	 * Constructeur
	 */
	public StationParser() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			Log.e("Error", e.getMessage());
		}
	}

	/**
	 * Parse un flux XML pour en extraire des informations du elder
	 * 
	 * @param xml
	 *            Le flux à parser
	 * @return Les informations de configuration dans une Map
	 * @throws BadXMLException
	 *             Exception levée si le flux XML n'a pas le format attendu
	 */
	public ArrayList<Station> parse(String xml) {
		ArrayList<Station> stations = new ArrayList<Station>();

		Element racine;

		try {
			Document document = builder.parse(new InputSource(new StringReader(xml)));
			racine = document.getDocumentElement();
		} catch (SAXException e) {
			Log.e("Error", e.getMessage());
			return stations;
		} catch (IOException e) {
			Log.e("Error", e.getMessage());
			return stations;
		}

		NodeList nodeList = racine.getElementsByTagName("stand");

		String idStation = null, nomStation = null, adresse = null, dispo = null, longitude = null, latitude = null, capaTot = null, capaDisp = null, plaDisp = null, veloDisp = null;

		for (int i = 0; i < nodeList.getLength(); i++) {
			Element childNode = (Element) nodeList.item(i);
			idStation = childNode.getAttribute("id");
			
			try {
				nomStation = URLDecoder.decode(childNode.getAttribute("name"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			NodeList children = childNode.getChildNodes();
			
			for (int j = 0; j < children.getLength(); j++) {
				Element child = (Element) children.item(j);
				if (child.getNodeName().equals("wcom")) {
					adresse = (child.getTextContent() != null)?child.getTextContent():"";
				} else if (child.getNodeName().equals("disp")) {
					dispo = (child.getTextContent() != null)?child.getTextContent():"0";
				} else if (child.getNodeName().equals("lng")) {
					longitude = (child.getTextContent() != null)?child.getTextContent():"0";
				} else if (child.getNodeName().equals("lat")) {
					latitude = (child.getTextContent() != null)?child.getTextContent():"0";
				} else if (child.getNodeName().equals("tc")) {
					capaTot = (child.getTextContent() != null)?child.getTextContent():"0";
				} else if (child.getNodeName().equals("ac")) {
					capaDisp = (child.getTextContent() != null)?child.getTextContent():"0";
				} else if (child.getNodeName().equals("ap")) {
					plaDisp = (child.getTextContent() != null)?child.getTextContent():"0";
				} else if (child.getNodeName().equals("ab")) {
					veloDisp = (child.getTextContent() != null)?child.getTextContent():"0";
				}
			}		
			
			
			if(Integer.parseInt(dispo) != 0)
			{
				Station sta = new Station(Integer.parseInt(idStation), nomStation,
					adresse, Integer.parseInt(dispo),
					Double.parseDouble(longitude),
					Double.parseDouble(latitude), Integer.parseInt(capaTot),
					Integer.parseInt(capaDisp), Integer.parseInt(plaDisp),
					Integer.parseInt(veloDisp));
				stations.add(sta);
			}			
		}

		/*
		 * for (RemoteConfig.ElderKey key : RemoteConfig.ElderKey.values()) {
		 * 
		 * NodeList list = racine.getElementsByTagName(key.NAME);
		 * 
		 * 
		 * if(list.getLength()==0) { continue; }
		 * 
		 * if(list.getLength()>1) { throw new BadXMLException(); }
		 * 
		 * Element e = (Element)list.item(0); String value = e.getTextContent();
		 * remoteConfig.put(key, value); }
		 */

		return stations;
	}

}
