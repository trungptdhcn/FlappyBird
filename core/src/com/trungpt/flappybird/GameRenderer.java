package com.trungpt.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by trungpt on 10/22/2014.
 */
public class GameRenderer
{
    private GameWorld myWorld;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch spriteBatch;

    private int midPointY;
    private int gameHeight;

    // Game Objects
    private Bird bird;

    // Game Assets
    private TextureRegion bg, grass;
    private Animation birdAnimation;
    private TextureRegion birdMid, birdDown, birdUp;
    private TextureRegion skullUp, skullDown, bar;

    private void initGameObjects()
    {
        bird = myWorld.getBird();
    }

    private void initAssets()
    {
        bg = AssetLoader.bg;
        grass = AssetLoader.grass;
        birdAnimation = AssetLoader.birdAnimation;
        birdMid = AssetLoader.bird;
        birdDown = AssetLoader.birdDown;
        birdUp = AssetLoader.birdUp;
        skullUp = AssetLoader.skullUp;
        skullDown = AssetLoader.skullDown;
        bar = AssetLoader.bar;
    }

    public GameRenderer(GameWorld myWorld, int gameHeight, int midPointY)
    {
        this.myWorld = myWorld;
        this.midPointY = midPointY;
        this.gameHeight = gameHeight;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 136, gameHeight);

        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        initGameObjects();
        initAssets();
    }

    public void render(float runTime)
    {
        Bird bird = myWorld.getBird();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        // Draw Background color
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 136, midPointY + 66);

        // Draw Grass
        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, 136, 11);

        // Draw Dirt
        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 77, 136, 52);
        shapeRenderer.end();

        spriteBatch.begin();
        spriteBatch.disableBlending();
        spriteBatch.draw(bg, 0, midPointY + 23, 136, 43);
        spriteBatch.enableBlending();
        if(bird.shouldntFlap())
        {
            spriteBatch.draw(birdMid,bird.getX(),bird.getY(),
                    bird.getWidth()/2.0f,bird.getHeight()/2.0f,
                    bird.getWidth(),bird.getHeight(),1,1,bird.getRotation());
        }
        else
        {
            spriteBatch.draw(birdAnimation.getKeyFrame(runTime),bird.getX(),
                    bird.getY(),bird.getWidth()/2.0f,bird.getHeight()/2.0f
                    ,bird.getWidth(),bird.getHeight(),1,1,bird.getRotation());
        }

//        spriteBatch.draw(AssetLoader.birdAnimation.getKeyFrame(runTime), bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight());
        spriteBatch.end();
    }
}
