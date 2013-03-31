package com.reaction.zombiesushi.model;

import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.util.adt.pool.GenericPool;

import com.reaction.zombiesushi.MainActivity;
import com.reaction.zombiesushi.util.NumberUtil;

public class TemplateObjectPool extends GenericPool<TemplateSprite> {

	private MainActivity activity;
	private TextureRegion[] templates;

	public TemplateObjectPool(TextureRegion[] templates,
			MainActivity activity) {
		this.templates = templates;
		this.activity = activity;
	}

	@Override
	protected TemplateSprite onAllocatePoolItem() {
		return new TemplateSprite(0, 0,
				templates[NumberUtil.getRandomInt(templates.length)],
				activity.getVertexBufferObjectManager(), this);
	}

	@Override
	public synchronized void recyclePoolItem(TemplateSprite item) {
		item.setVisible(false);
		item.setIgnoreUpdate(true);
		item.setX(500);
		super.recyclePoolItem(item);
	}

}
