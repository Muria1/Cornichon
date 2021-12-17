package com.cornichon.views.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.cornichon.Cornichon;

public class HowToPlayScreen implements Screen{

    Cornichon game;
    OrthographicCamera camera;
    public Stage stage;
  
    public HowToPlayScreen(Cornichon game) {
      this.game = game;
      this.game.setPaused(false);
  
      this.camera = new OrthographicCamera();
      this.camera.setToOrtho(false, 800, 400);
  
      stage = new Stage(new ScreenViewport());
      Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        Table table = new Table();
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setDebug(false);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("images/uiskin.json"));

        TextButton back = new TextButton("Back", skin);

        table.setBounds(100, 650, 50, 30);
        table.add(back).fillX().uniformX();

            
        back.addListener(
        new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
            }
        );
  
        
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
    
        game.batch.begin();
        
        game.batch.draw(new Texture("images/KeyBoard.png"), 190, 180, 400,200);

        BitmapFont pauseMap = new BitmapFont();
        pauseMap.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        pauseMap.getData().setScale(1, 1);
        pauseMap.draw(game.batch, "Use the second entity (Cornichon) to help you finish the game, it can defend you", 180, 150);
        pauseMap.draw(game.batch, "Try to find the exit door while escaping from slimes and skeletons", 200, 120);
        pauseMap.draw(game.batch, "PS: Skeletons can shoot, be careful", 280, 90);
    
        game.batch.end();
    
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        
    }

    @Override
    public void resize(int width, int height) {
        
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
    
}
