package com.reaction.zombiesushi.util;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.util.debug.Debug;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.util.Log;

import com.reaction.zombiesushi.MainActivity;
import com.reaction.zombiesushi.model.Layer;
import com.reaction.zombiesushi.model.Obstacle;

public class Level {

	private static final String RESOURCES_TAG = "resources";
	private static final String ATLAS_TAG = "atlas";
	private static final String MUSIC_TAG = "music";
	private static final String LAYER_TAG = "layer";
	private static final String ENTITY_TAG = "entity";
	private static final String OBSTACLES_TAG = "obstacles";
	private static final String BACKGROUND_TAG = "background";

	private MainActivity activity;
	private BitmapTextureAtlas atlas;
	private Music music;
	private SpriteBackground background;
	private int numberOfLayers;
	private Layer[] layers;
	private PhysicsWorld physicsWorld;
	private ObstaclePool obstaclePool;

	private Level(MainActivity activity, PhysicsWorld physicsWorld) {
		this.activity = activity;
		this.physicsWorld = physicsWorld;
	}

	public static Level loadLevel(String levelName, MainActivity activity,
			PhysicsWorld physicsWorld) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document document;
		Level level = null;
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.parse(ResourceManager.getResource("levels/"
					+ levelName + ".xml"));
			level = new Level(activity, physicsWorld);
			Element root = document.getDocumentElement();
			Element resources = (Element) getResourceNode(root);
			Element backgroundElement = (Element) getElementByTag(root, BACKGROUND_TAG);
			NodeList layerNodes = root.getElementsByTagName(LAYER_TAG);
			NodeList obstaclesNodes = root.getElementsByTagName(OBSTACLES_TAG);
			level.loadTextureAtlas(getElementByTag(resources, ATLAS_TAG));
			level.loadMusic(getElementByTag(resources, MUSIC_TAG));
			level.setLayers(layerNodes);
			level.setObstaclesRegions(obstaclesNodes);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			Log.e("xml", "failed to parse xml", se);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return level;
	}

	private static Element getElementByTag(Element resourceNode, String tag) {
		Element atlasElement = (Element) resourceNode.getElementsByTagName(
				tag).item(0);
		return atlasElement;
	}

	private static Node getResourceNode(Element root) {
		return root.getElementsByTagName(RESOURCES_TAG).item(0);
	}

	private void setLayers(NodeList layerNodes) {
		this.numberOfLayers = layerNodes.getLength();
		this.layers = new Layer[numberOfLayers];
		for (int i = 0; i < numberOfLayers; i++) {
			Element layer = (Element) layerNodes.item(i);
			NodeList objects = layer.getElementsByTagName(ENTITY_TAG);
			float velocity = NumberUtil.stringToFloat(layer
					.getAttribute("velocity"));
			int maxGapWidth = NumberUtil.stringToInt(layer
					.getAttribute("maxGapWidth"));
			TextureRegion[] layerRegions = parseTextureRegions(objects);
			layers[i] = new Layer(layerRegions, velocity, this.activity,
					maxGapWidth);
		}
	}
	
	private void setBackground(Element root){
		
	}

	private void setObstaclesRegions(NodeList obstaclesNodes) {
		Obstacle.init(parseTextureRegions(obstaclesNodes));
		this.obstaclePool = new ObstaclePool(physicsWorld,
				activity.getVertexBufferObjectManager());
	}

	private TextureRegion[] parseTextureRegions(NodeList objects) {
		int layerSize = objects.getLength();
		TextureRegion[] regions = new TextureRegion[layerSize];
		for (int j = 0; j < layerSize; j++) {
			Element object = (Element) objects.item(j);
			int xOffset = NumberUtil.stringToInt(object.getAttribute("x"));
			int yOffset = NumberUtil.stringToInt(object.getAttribute("y"));
			int width = NumberUtil.stringToInt(object.getAttribute("w"));
			int height = NumberUtil.stringToInt(object.getAttribute("h"));
			regions[j] = TextureRegionFactory.extractFromTexture(this.atlas,
					xOffset, yOffset, width, height, false);
		}
		return regions;
	}

	private void loadTextureAtlas(Element atlasElement) {
		int width = NumberUtil.stringToInt(atlasElement.getAttribute("w"));
		int height = NumberUtil.stringToInt(atlasElement.getAttribute("h"));
		this.atlas = new BitmapTextureAtlas(this.activity.getTextureManager(),
				width, height);
		BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.atlas,
				this.activity, atlasElement.getAttribute("filename"), 0, 0);
		this.atlas.load();
	}

	private void loadMusic(Element musicElement) {
		String filename = musicElement.getAttribute("filename");
		MusicFactory.setAssetBasePath("mfx/");
		try {
			this.music = MusicFactory.createMusicFromAsset(
					this.activity.getMusicManager(), this.activity, filename);
			this.music.setLooping(true);
		} catch (IOException e) {
			Debug.e("Error", e);
		}
	}

	public SpriteBackground getBackground() {
		return background;
	}

	public Layer[] getLayers() {
		return layers;
	}

	public void update() {
		for (Layer layer : layers) {
			layer.spawnObject();
		}
		if (NumberUtil.getRandomInt(100) < 50) {
			Obstacle obstacle = this.obstaclePool.obtainPoolItem();
			layers[numberOfLayers - 1].getLayerEntity().attachChild(obstacle);
		}
	}

	public Music getMusic() {
		return this.music;
	}

}
