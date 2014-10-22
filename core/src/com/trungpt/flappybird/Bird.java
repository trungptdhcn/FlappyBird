package com.trungpt.flappybird;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by trungpt on 10/22/2014.
 */
public class Bird
{
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private float rotation;
    private int with;
    private int height;

    public Bird(float x, float y, int with, int height)
    {
        this.with = with;
        this.height = height;
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0, 0);
        this.acceleration = new Vector2(0, 460);
    }

    public void update(float delta)
    {
        velocity.add(acceleration.cpy().scl(delta));
        if (velocity.y > 200)
        {
            velocity.y = 200;
        }
        position.add(velocity.cpy().scl(delta));
        // Rotate counterclockwise
        if (velocity.y < 0)
        {
            rotation -= 600 * delta;
            if (rotation < -20)
            {
                rotation = -20;
            }
        }

        if (isFalling())
        {
            rotation += 480 * delta;
            if (rotation > 90)
            {
                rotation = 90;
            }
        }

    }

    public boolean isFalling()
    {
        return velocity.y > 110;
    }

    public boolean shouldntFlap()
    {
        return velocity.y > 70;
    }

    public void onClick()
    {
        velocity.y = 0;
    }

    public float getX()
    {
        return position.x;
    }

    public float getY()
    {
        return position.y;
    }

    public float getWidth()
    {
        return with;
    }

    public float getHeight()
    {
        return height;
    }

    public float getRotation()
    {
        return rotation;
    }
}
