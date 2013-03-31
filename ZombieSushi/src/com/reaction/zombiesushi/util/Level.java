package com.reaction.zombiesushi.util;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.reaction.zombiesushi.model.Layer;

public class Level {

	private static final String RESOURCE_TAG = "resources";

	private BitmapTextureAtlas atlas;
	private SpriteBackground background;
	private Layer[] layers;

	private Level() {
	}

	public static Level loadLevel(String levelName) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document document;
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db
					.parse(ResourceManager.getResource(levelName + ".xml"));
			Level level = new Level();
			Element root = document.getDocumentElement();
			Element resources = (Element) getResourceNode(root);
			NodeList layerNodes = root.getElementsByTagName("layer");
			level.loadTextureAtlas(getAtlasElement(resources));
			level.setTextureRegions(layerNodes);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return new Level();
	}

	private static Element getAtlasElement(Element resourceNode) {
		Element atlasElement = (Element) resourceNode.getElementsByTagName(
				"texture-atlas").item(0);
		return atlasElement;
	}

	private static Node getResourceNode(Element root) {
		return root.getElementsByTagName(RESOURCE_TAG).item(0);
	}

	private void setTextureRegions(NodeList layerNodes) {
		int length = layerNodes.getLength();
		layers = new Layer[length];
		for (int i = 0; i < length; i++) {
			Element layer = (Element) layerNodes.item(i);
			NodeList objects = layer.getElementsByTagName("entity");
			int layerSize = objects.getLength();
			float velocity = NumberUtil.stringToFloat(layer.getAttribute("velocity"));
			TextureRegion[] layerRegions = new TextureRegion[layerSize];
			for (int j = 0; j < layerSize; j++) {
				Element object = (Element) objects.item(j);
				int xOffset = NumberUtil.stringToInt(object
						.getAttribute("x"));
				int yOffset = NumberUtil.stringToInt(object
						.getAttribute("y"));
				int width = NumberUtil.stringToInt(object
						.getAttribute("w"));
				int height = NumberUtil.stringToInt(object
						.getAttribute("h"));
				layerRegions[j] = TextureRegionFactory.extractFromTexture(
						this.atlas, xOffset, yOffset, width, height, false);
			}
			layers[i] = new Layer(layerRegions, velocity, null);//TODO need to figure out better class structure
		}
	}

	private void loadTextureAtlas(Element atlasElement) {
		int width = NumberUtil.stringToInt(atlasElement.getAttribute("width"));
		int height = NumberUtil.stringToInt(atlasElement
				.getAttribute("height"));
		this.atlas = new BitmapTextureAtlas(
				ResourceManager.getTextureManager(), width, height);
		BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.atlas,
				ResourceManager.getMainActivity(),
				atlasElement.getAttribute("filename"), 0, 0);
	}

	public SpriteBackground getBackground() {
		return background;
	}
	
	public Layer[] getLayers(){
		return layers;
	}

}
