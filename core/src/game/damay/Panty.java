package game.damay;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Panty extends Actor {
	public Texture texture;
	public Vector2 positon;

	public Panty(Texture texture, Vector2 position) {
		this.texture = texture;
		this.positon = position;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		batch.draw(texture, positon.x, positon.y);
	}

}
