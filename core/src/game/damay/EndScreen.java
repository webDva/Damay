package game.damay;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class EndScreen implements Screen {

	private SpriteBatch batch;
	private Game g;

	private OrthographicCamera camera;
	private FitViewport viewport;

	private Stage stage;
	private int matches;
	private Skin skin;
	private Label scoreLabel;

	public EndScreen(SpriteBatch batch, Game g, Skin skin, int matches) {
		this.batch = batch;
		this.g = g;
		this.skin = skin;
		this.matches = matches;
	}

	@Override
	public void show() {
		camera = new OrthographicCamera();
		viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
		viewport.apply();

		stage = new Stage(new FitViewport(800 / 2, 600 / 2, new OrthographicCamera()), batch);
		Gdx.input.setInputProcessor(stage);

		Table table = new Table();
		stage.addActor(table);
		table.setPosition(100, 50);

		LabelStyle labelStyle = new LabelStyle(skin.getFont("my_font"), skin.getFont("my_font").getColor());
		scoreLabel = new Label("Score", labelStyle);

		skin.add("panty_image", new Texture(Gdx.files.internal("panty" + MathUtils.random(1, PlayScreen.ASSETS_CREATED) + ".png")));

		TextButtonStyle buttonStyle = new TextButtonStyle(skin.getDrawable("panty_image"), skin.getDrawable("panty_image"), skin.getDrawable("panty_image"),
				skin.getFont("my_font"));
		final TextButton button = new TextButton("Retry", buttonStyle);

		button.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (button.isPressed()) {
					g.setScreen(new PlayScreen(batch, g));
				}
			}
		});

		table.add(button);
		table.row();
		table.add(scoreLabel);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);

		scoreLabel.setText(matches + " matches in 60 seconds");

		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		stage.dispose();
		batch.dispose();
		skin.dispose();
	}

}
