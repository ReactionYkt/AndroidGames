package com.reaction.zombiesushi.util;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.reaction.zombiesushi.model.Level;
import com.reaction.zombiesushi.res.Textures;

public class LevelLoader {

	private Document document;
	private DocumentBuilderFactory dbf;
	private final Level level;

	public LevelLoader(VertexBufferObjectManager vertexBufferObjectManager,
			Level level) {
		dbf = DocumentBuilderFactory.newInstance();
		this.level = level;
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.parse(level.getInputStream());
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void loadLevel(){
		parseDocument();
		level.submit();
	}

	private void parseDocument() {
		Element rootElement = document.getDocumentElement();
		NodeList layerNodes = rootElement.getElementsByTagName("entity");
		if (layerNodes != null && layerNodes.getLength() > 0) {
			for (int i = 0; i < layerNodes.getLength(); i++) {
				Element element = (Element) layerNodes.item(i);
				putObject(element);
			}
		}
	}

	private void putObject(Element element) {
		float x = NumberUtils.stringToFloat(element.getAttribute("x"));
		float y = NumberUtils.stringToFloat(element.getAttribute("y"));
		float width = NumberUtils.stringToFloat(element.getAttribute("width"));
		float height = NumberUtils.stringToFloat(element.getAttribute("height"));
		int type = NumberUtils.stringToInt(element.getAttribute("type"));
		int depth = NumberUtils.stringToInt(element.getAttribute("layer"));
		switch (depth) {
			case 1:
				level.addSpriteToFirstLayer(Textures.getTextureRegionByType(1, type), x, y, width, height);
				break;
			case 2:
				level.addSpriteToSecondLayer(Textures.getTextureRegionByType(2, type), x, y, width, height);
				break;
			default:
				break;
		}

	}

}
