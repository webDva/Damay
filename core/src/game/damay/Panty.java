package game.damay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Panty extends Actor {
	public Texture texture;
	public Vector2 positon;
	public int pantyNumber;

	public Panty(int pantyNumber, Vector2 position, final PlayScreen screen) {
		this.pantyNumber = pantyNumber;
		this.texture = new Texture(Gdx.files.internal("panty" + pantyNumber + ".png"));
		this.positon = position;
		setBounds(position.x, position.y, texture.getWidth(), texture.getHeight());

		addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				screen.changeSelection(Panty.this.pantyNumber, Panty.this);

				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
			}
		});
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		batch.draw(texture, positon.x, positon.y);
	}

}
