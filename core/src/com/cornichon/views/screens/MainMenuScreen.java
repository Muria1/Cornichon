package com.cornichon.views.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.cornichon.Cornichon;
import com.cornichon.views.textures.Textures;

public class MainMenuScreen implements Screen {

  Cornichon game;
  OrthographicCamera camera;
  boolean pressedOnce = false;
  boolean musicPressedOnce = false;
  public Stage stage;
  public Texture image;
  public Texture music;

  public MainMenuScreen(Cornichon game) {
    this.game = game;
    this.game.setPaused(false);

    this.camera = new OrthographicCamera();
    this.camera.setToOrtho(false, 800, 400);

    stage = new Stage(new ScreenViewport());
    Gdx.input.setInputProcessor(stage);
  }

  @Override
  public void render(float delta) {
    ScreenUtils.clear(0, 0, 0.2f, 1);
    Gdx.gl.glClearColor(0f, 0f, 0f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();
    game.batch.setProjectionMatrix(camera.combined);
    if(Cornichon.isSoundOn()){
      setSoundImage(Textures.SOUND_ON);
    }
    else{
      setSoundImage(Textures.SOUND_OFF);
    }

    if(Cornichon.isMusicOn()){
      setMusicImage(Textures.MUSIC_ON);
    }
    else{
      setMusicImage(Textures.MUSIC_OFF);
    }

    game.batch.begin();
    game.batch.draw(game.img, 0, 0);
  
    drawSoundButton();

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
    Table table = new Table();
    this.stage = new Stage(new ScreenViewport());
    Gdx.input.setInputProcessor(stage);

    table.setDebug(false);
    stage.addActor(table);

    Skin skin = new Skin(Gdx.files.internal("images/uiskin.json"));

    TextButton newGame = new TextButton("New Game", skin);
    TextButton leaderBoard = new TextButton("Leaderboard", skin);
    TextButton exit = new TextButton("Exit", skin);
    TextButton tutorial = new TextButton("Tutorial", skin);
    setSoundImage(Textures.SOUND_ON);
    setMusicImage(Textures.MUSIC_ON);

    table.setBounds(590, 80, 100, 150);
    table.add(newGame).fillX().uniformX();
    table.row().pad(10, 0, 10, 0);
    table.add(leaderBoard).fillX().uniformX();
    table.row();
    table.add(exit).fillX().uniformX();
    table.row().pad(10, 0, 10, 0);
    table.add(tutorial).fillX().uniformX();
    

    exit.addListener(
      new ChangeListener() {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
          Gdx.app.exit();
        }
      }
    );

    newGame.addListener(
      new ChangeListener() {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
          game.setScreen(new GameSelectionScreen(game));
        }
      }
    );
    leaderBoard.addListener(new ChangeListener() {
    	@Override
    	public void changed(ChangeEvent event, Actor actor) {
    		game.setScreen(new LeaderboardListScreen(game));
    	}
    });

    tutorial.addListener(new ChangeListener() {
    	@Override
    	public void changed(ChangeEvent event, Actor actor) {
    		game.setScreen(new HowToPlayScreen(game));
    	}
    });
    

  }

  public void drawSoundButton(){


    
    Table soundTable = new Table();
    soundTable.setDebug(false);
    stage.addActor(soundTable);
   
    Drawable drawableSound = new TextureRegionDrawable(new TextureRegion((getSoundImage())));
    Drawable drawableMusic = new TextureRegionDrawable(new TextureRegion((getMusicImage())));
    ImageButton soundButton = new ImageButton(drawableSound);
    ImageButton musicButton = new ImageButton(drawableMusic);

    soundTable.setBounds(50, 50, 40, 40);
    soundTable.add(soundButton);
    soundTable.add(musicButton);

    soundButton.addListener(new ChangeListener() {
    	@Override
    	public void changed(ChangeEvent event, Actor actor) {
        
        
        if(!pressedOnce){
          Cornichon.backgroundMusic.pause();
          Cornichon.setSound(false);
          Cornichon.setMusic(false);
          pressedOnce = true;

        }
        else{
          Cornichon.backgroundMusic.play();
          Cornichon.setSound(true);
          Cornichon.setMusic(true);
          pressedOnce = false;
        }
        
    	}
    });

    musicButton.addListener(new ChangeListener() {
    	@Override
    	public void changed(ChangeEvent event, Actor actor) {
        
        
        if(!musicPressedOnce){
          Cornichon.backgroundMusic.pause();
          Cornichon.setMusic(false);
          musicPressedOnce = true;

        }
        else{
          Cornichon.backgroundMusic.play();
          Cornichon.setMusic(true);
          musicPressedOnce = false;
        }
        
    	}
    });

    game.batch.setProjectionMatrix(this.camera.projection);

  }

  public void setSoundImage(Texture image){
    this.image = image;
  }

  public Texture getSoundImage(){
    return image;
  }

  public void setMusicImage(Texture image){
    this.music = image;
  }

  public Texture getMusicImage(){
    return music;
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
