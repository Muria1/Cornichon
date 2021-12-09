package com.cornichon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PauseRenderer {

  private SpriteBatch spriteBatch;
  private Stage stage;

  public PauseRenderer(SpriteBatch batch) {
    this.spriteBatch = batch;
    stage = new Stage(new ScreenViewport());


  }

  public void render() {
    spriteBatch.begin();

    

    this.drawText();
    this.drawButtons();
    spriteBatch.end();
  }

  private void drawText() {
    BitmapFont pauseMap = new BitmapFont();
    pauseMap.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    pauseMap.getData().setScale(4, 4);
    pauseMap.draw(spriteBatch, "PAUSED", 290, 290);
  }

  private void drawButtons() {
  //   // Add buttons here
    BitmapFont resumeMap = new BitmapFont();

    resumeMap.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    resumeMap.getData().setScale(1, 1);
    resumeMap.draw(spriteBatch, "press R to resume", 330, 200);

    BitmapFont mainMenu = new BitmapFont();
    mainMenu.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    mainMenu.getData().setScale(1, 1);
    mainMenu.draw(spriteBatch, "press M to go to main menu", 330, 180);
  }


  //   Table buttons = new Table();
  //   Skin skin = new Skin(Gdx.files.internal("images/uiskin.json"));
    
  //   TextButton resumeButton = new TextButton( "RESUME", skin);
  //     resumeButton.addListener(new ClickListener(){
  //         @Override
  //         public void clicked(InputEvent event, float x , float y){
  //             //game.setPaused(false);
  //             System.out.println("clicked");
  //         }    
  //     });


  //   TextButton newGameButton = new TextButton("NEW GAME", skin);
        
  //   TextButton menuButton = new TextButton("MENU", skin);

  //       buttons.setBounds(550, 50, 200, 100);
  //       buttons.padTop(64);
  //       buttons.add(resumeButton).row();
  //       buttons.add(newGameButton).row();
  //       buttons.add(menuButton).row();
  //       buttons.setSize(stage.getWidth() / 1.5f , stage.getHeight() / 1.5f);
    
    
  //       stage.addActor(buttons);
  //       stage.draw();
  
  // 
}
