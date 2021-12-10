package com.cornichon.views.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.cornichon.Cornichon;
import com.cornichon.utils.Database;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.cornichon.Cornichon;

public class LeaderboardListScreen implements Screen {

    Cornichon game;
    OrthographicCamera camera;
    public Stage stage;
    public Database database;
    int score;
    Skin skin = new Skin(Gdx.files.internal("images/uiskin.json"));

    TextField scoreTextField = new TextField("", skin);
    
    public LeaderboardListScreen(Cornichon game, final int score) {
      this.game = game;  
      this.camera = new OrthographicCamera();
      this.camera.setToOrtho(false, 800, 400);
      this.score= score;
    }
  
    @Override
    public void render(float delta) {
      ScreenUtils.clear(0, 0, 0.2f, 1);
      Gdx.gl.glClearColor(0f, 0f, 0f, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      camera.update();
      game.batch.setProjectionMatrix(camera.combined);
  
      game.batch.begin();
      
      for(int i = 0; i < 20; i += 2){
        game.font.draw(game.batch, database.read().get(i), 270, 300 - 10 *i);
        game.font.draw(game.batch, database.read().get(i+1), 430, 300 - 10*i);
      }
      
      game.batch.end();
  
      stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
      stage.draw();


    }
  
    @Override
    public void resize(int width, int height) {
      stage.getViewport().update(width, height, true);
    }
  
    @Override
    public void show() {

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setDebug(true);
        stage.addActor(table);

        
        Skin skin = new Skin(Gdx.files.internal("images/uiskin.json"));

        TextButton mainMenu = new TextButton("Return to Main Menu", skin);

        /** 
        table.setBounds(250, 100, 200, 10);
        table.add(mainMenu).fillX().uniformX(); */

        
        mainMenu.setPosition(550, 30);
        stage.addActor(mainMenu); 
    
        mainMenu.addListener(
          new ChangeListener() {
          @Override
          public void changed(ChangeEvent event, Actor actor) {
            game.setScreen(new MainMenuScreen(game));
          }
      }
    );
    }
  
    @Override
    public void hide() {}
  
    @Override
    public void pause() {}
  
    @Override
    public void resume() {}
  
    @Override
    public void dispose() {
      stage.dispose();
    }
  }
  