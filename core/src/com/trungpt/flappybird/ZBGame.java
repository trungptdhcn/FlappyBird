package com.trungpt.flappybird;

import com.badlogic.gdx.Game;

/**
 * Created by trungpt on 10/22/2014.
 */
public class ZBGame extends Game
{
    @Override
    public void create()
    {
        AssetLoader.load();
        setScreen(new GameScreen());
    }

    @Override
    public void dispose()
    {
        super.dispose();
        AssetLoader.dispose();
    }
}
