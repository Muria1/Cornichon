package com.cornichon.views.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType.Bitmap;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.cornichon.Cornichon;

public class PauseScreen implements Screen {

    Cornichon game;
    OrthographicCamera camera;
    public Stage stage;
    boolean isPaused;

    public PauseScreen(Cornichon game){
        this.game = game;

        isPaused = true;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 400);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        
        
    }

    @Override
    public void show() {
        Table table = new Table();
        table.setDebug(true);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("images/uiskin.json"));

        
        TextButton newGame = new TextButton("New Game", skin);
        TextButton resume = new TextButton("Continue", skin);
        TextButton menu = new TextButton("Menu", skin);
        
    


        newGame.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new GameScreen());
			}
		});

        resume.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				isPaused = false;
			}
		});

        menu.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new MainMenuScreen(game));
			}
		});
        
        

        table.setBounds(550,100, 200,100);
        table.add(newGame).fillX().uniformX();
		table.row().pad(10, 0, 10, 0);
		table.row();
		table.add(resume).fillX().uniformX();
        table.row();
        table.add(menu).fillX().uniformX();
        
    }

    @Override
    public void render(float delta) {

       
        ScreenUtils.clear(0,0,0.2f,1);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        BitmapFont pauseMap = new BitmapFont();
        pauseMap.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        pauseMap.getData().setScale(4, 4);
        pauseMap.draw(game.batch, "PAUSED", 290, 290);

        
        game.batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        
        
        
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() {
        
    }

    @Override
    public void dispose() {
        stage.dispose();
        
    }

    public boolean getPaused(){
        return isPaused;
    }
    
}
