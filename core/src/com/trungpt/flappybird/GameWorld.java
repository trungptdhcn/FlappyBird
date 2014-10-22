package com.trungpt.flappybird;

import java.awt.*;

/**
 * Created by trungpt on 10/22/2014.
 */
public class GameWorld
{
    private Bird bird;

    public GameWorld(int midPointY)
    {
        this.bird = new Bird(33, midPointY - 5, 17, 12);
    }

    public void update(float delta)
    {
        bird.update(delta);
    }

    public Bird getBird()
    {
        return bird;
    }

    public void setBird(Bird bird)
    {
        this.bird = bird;
    }
}
