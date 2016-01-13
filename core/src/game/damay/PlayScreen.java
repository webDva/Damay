package game.damay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class PlayScreen implements Screen {

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private FitViewport viewport;

	private Stage stage;
	private Skin skin;

	public PlayScreen(SpriteBatch batch) {
		this.batch = batch;
	}

	@Override
	public void show() {
		camera = new OrthographicCamera();
		viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
		viewport.apply();

		/* User Interface */
		stage = new Stage(new FitViewport(800 / 2, 600 / 2, new OrthographicCamera()), batch);
		Gdx.input.setInputProcessor(stage);

		Table table = new Table();
		table.setPosition(50, Gdx.graphics.getHeight() - 70);
		stage.addActor(table);

		table.setDebug(true);

		skin = new Skin();

		skin.add("my_font", new BitmapFont(), BitmapFont.class);

		LabelStyle labelStyle = new LabelStyle(skin.getFont("my_font"), skin.getFont("my_font").getColor());
		Label label1 = new Label("Health", labelStyle);

		table.add(label1);

		/* Create 2D array of actors */
		int rows, columns;
		rows = 5;
		columns = 5;
		Panty[][] panties = new Panty[rows][columns];

		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < columns; ++j) {
				panties[i][j] = new Panty(new Texture(Gdx.files.internal("panty1.png")), new Vector2(i * 50, j * 50));
				stage.addActor(panties[i][j]);
			}
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.end();

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
		skin.dispose();
	}

}
