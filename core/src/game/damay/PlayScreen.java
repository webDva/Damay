package game.damay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class PlayScreen implements Screen {

	private static final int PANTY_AREA = 48;

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private FitViewport viewport;

	private Stage stage;
	private Skin skin;

	public int selections[];
	private int tapAttempts = 0;
	private Panty selectedPanties[];

	private Sound positive, negative;

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
		table.setPosition(50, 70);
		stage.addActor(table);

		table.setDebug(true);

		skin = new Skin();

		skin.add("my_font", new BitmapFont(), BitmapFont.class);

		// LabelStyle labelStyle = new LabelStyle(skin.getFont("my_font"), skin.getFont("my_font").getColor());
		// Label label1 = new Label("AAAAAAAAAAAAAA", labelStyle);

		// table.add(label1);

		/* Load sound */
		positive = Gdx.audio.newSound(Gdx.files.internal("audio/positive.wav"));
		negative = Gdx.audio.newSound(Gdx.files.internal("audio/negative.wav"));

		/* Create 2D array of actors */
		int rows, columns;
		rows = 5;
		columns = 5;
		Panty[][] panties = new Panty[rows][columns];
		selections = new int[2];
		selectedPanties = new Panty[2];

		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < columns; ++j) {
				panties[i][j] = new Panty(MathUtils.random(1, 2), new Vector2(i * PANTY_AREA, j * PANTY_AREA), this);
				stage.addActor(panties[i][j]);
			}
		}

	}

	public void changeSelection(int i, Panty p) {
		if (!(tapAttempts >= 2)) {
			tapAttempts++;
		} else {
			tapAttempts = 0;
		}

		if (tapAttempts == 1)
			this.selections[0] = i;
		else if (tapAttempts == 2)
			this.selections[1] = i;

		if (tapAttempts != 0) {
			selectedPanties[tapAttempts - 1] = p;
		}
	}

	@Override
	public void render(float delta) {
		if (tapAttempts == 2) {
			if (selectedPanties[0].pantyNumber == selectedPanties[1].pantyNumber && selectedPanties[0] != selectedPanties[1]) {
				positive.play();
			} else {
				negative.play();
			}
			tapAttempts = 0;
		}

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
