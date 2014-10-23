package com.trungpt.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
    private ScrollHandler scroller;
    private Grass frontGrass, backGrass;
    private Pipe pipe1, pipe2, pipe3;

    // Game Assets
    private TextureRegion bg, grass;
    private Animation birdAnimation;
    private TextureRegion birdMid, birdDown, birdUp;
    private TextureRegion skullUp, skullDown, bar;

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

    private void initGameObjects()
    {
        bird = myWorld.getBird();
        scroller = myWorld.getScrollHandler();
        frontGrass = scroller.getFrontGrass();
        backGrass = scroller.getBackGrass();
        pipe1 = scroller.getPipe1();
        pipe2 = scroller.getPipe2();
        pipe3 = scroller.getPipe3();
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
    public void drawGrass()
    {
        spriteBatch.draw(grass,frontGrass.getX(),frontGrass.getY(),
                frontGrass.getWidth(),frontGrass.getHeight());
        spriteBatch.draw(grass,backGrass.getX(),backGrass.getY(),
                backGrass.getWidth(),backGrass.getHeight());
    }

    private void drawSkulls() {
        // Temporary code! Sorry about the mess :)
        // We will fix this when we finish the Pipe class.

        spriteBatch.draw(skullUp, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
        spriteBatch.draw(skullDown, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() + 45, 24, 14);

        spriteBatch.draw(skullUp, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
        spriteBatch.draw(skullDown, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() + 45, 24, 14);

        spriteBatch.draw(skullUp, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
        spriteBatch.draw(skullDown, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() + 45, 24, 14);
    }

    private void drawPipes() {
        // Temporary code! Sorry about the mess :)
        // We will fix this when we finish the Pipe class.
        spriteBatch.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(),
                pipe1.getHeight());
        spriteBatch.draw(bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 45,
                pipe1.getWidth(), midPointY + 66 - (pipe1.getHeight() + 45));

        spriteBatch.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(),
                pipe2.getHeight());
        spriteBatch.draw(bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 45,
                pipe2.getWidth(), midPointY + 66 - (pipe2.getHeight() + 45));

        spriteBatch.draw(bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(),
                pipe3.getHeight());
        spriteBatch.draw(bar, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 45,
                pipe3.getWidth(), midPointY + 66 - (pipe3.getHeight() + 45));
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
        drawGrass();
        drawPipes();
        spriteBatch.enableBlending();
        drawSkulls();
        if (bird.shouldntFlap())
        {
            spriteBatch.draw(birdMid, bird.getX(), bird.getY(),
                    bird.getWidth() / 2.0f, bird.getHeight() / 2.0f,
                    bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
        }
        else
        {
            spriteBatch.draw(birdAnimation.getKeyFrame(runTime), bird.getX(),
                    bird.getY(), bird.getWidth() / 2.0f, bird.getHeight() / 2.0f
                    , bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
        }

//        spriteBatch.draw(AssetLoader.birdAnimation.getKeyFrame(runTime), bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight());
        spriteBatch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(bird.getBoundingCircle().x,bird.getBoundingCircle().y,bird.getBoundingCircle().radius);


        /*
        render pipe and barup
         */
        shapeRenderer.rect(pipe1.getBarUp().x,pipe1.getBarUp().y,pipe1.getBarUp().width,pipe1.getBarUp().height);
        shapeRenderer.rect(pipe2.getBarUp().x,pipe2.getBarUp().y,pipe2.getBarUp().width,pipe2.getBarUp().height);
        shapeRenderer.rect(pipe3.getBarUp().x,pipe3.getBarUp().y,pipe3.getBarUp().width,pipe3.getBarUp().height);

        shapeRenderer.rect(pipe1.getBarDown().x,pipe1.getBarDown().y,pipe1.getBarDown().width,pipe1.getBarDown().height);
        shapeRenderer.rect(pipe2.getBarDown().x,pipe2.getBarDown().y,pipe2.getBarDown().width,pipe2.getBarDown().height);
        shapeRenderer.rect(pipe3.getBarDown().x,pipe3.getBarDown().y,pipe3.getBarDown().width,pipe3.getBarDown().height);

        shapeRenderer.rect(pipe1.getSkullDown().x,pipe1.getSkullDown().y,pipe1.getSkullDown().width,pipe1.getSkullDown().height);
        shapeRenderer.rect(pipe2.getSkullDown().x,pipe2.getSkullDown().y,pipe2.getSkullDown().width,pipe2.getSkullDown().height);
        shapeRenderer.rect(pipe3.getSkullDown().x,pipe3.getSkullDown().y,pipe3.getSkullDown().width,pipe3.getSkullDown().height);

        shapeRenderer.rect(pipe1.getSkullUp().x,pipe1.getSkullUp().y,pipe1.getSkullUp().width,pipe1.getSkullUp().height);
        shapeRenderer.rect(pipe2.getSkullUp().x,pipe2.getSkullUp().y,pipe2.getSkullUp().width,pipe2.getSkullUp().height);
        shapeRenderer.rect(pipe3.getSkullUp().x,pipe3.getSkullUp().y,pipe3.getSkullUp().width,pipe3.getSkullUp().height);

        shapeRenderer.end();
    }
}
