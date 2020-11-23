package pl.oldcotage.simplegame.screens.textures;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import pl.oldcotage.simplegame.GameRunner;

/**
 * Class for setting background textures and rendering
 */
public class Background {

    private float backgroundOffset;
    private float[] backgroundOffsets = {0, 0, 0, 0};
    private float backgroundMaxScrollingSpeed;
    private TextureAtlas textureAtlas;
    private TextureRegion background;
    private TextureRegion[] backgrounds;

    public Background() {
    }

    //initialize texture atlas
    private void setTextureAtlas() {
        textureAtlas = new TextureAtlas("images.atlas");
    }

    //setting texture array for parallax background scrolling in four layers
    //TODO change images for parallax effects
    private void setBackgroundTexture() {
        setTextureAtlas();
        backgrounds = new TextureRegion[4];
        backgrounds[0] = textureAtlas.findRegion("bg");
        backgrounds[1] = textureAtlas.findRegion("bg");
        backgrounds[2] = textureAtlas.findRegion("bg");
        backgrounds[3] = textureAtlas.findRegion("bg");

        backgroundMaxScrollingSpeed = (float) GameRunner.HEIGHT / 8;
    }

    //Method for rendering background in parallax
    public void renderParallaxBackground(float delta, SpriteBatch batch) {
        setBackgroundTexture();

        backgroundOffsets[0] += delta * backgroundMaxScrollingSpeed / 8;
        backgroundOffsets[1] += delta * backgroundMaxScrollingSpeed / 4;
        backgroundOffsets[2] += delta * backgroundMaxScrollingSpeed / 2;
        backgroundOffsets[3] += delta * backgroundMaxScrollingSpeed;

        for (int layer = 0; layer < backgroundOffsets.length; layer++) {
            if (backgroundOffsets[layer] > GameRunner.HEIGHT) {
                backgroundOffsets[layer] = 0;
            }
            batch.draw(backgrounds[layer], 0,
                    -backgroundOffsets[layer],
                    GameRunner.WIDTH,
                    GameRunner.HEIGHT);
            batch.draw(backgrounds[layer], 0,
                    -backgroundOffsets[layer] + GameRunner.HEIGHT,
                    GameRunner.WIDTH,
                    GameRunner.HEIGHT);
        }
    }

    //rendering background with single texture
    public void renderSingleBackground(SpriteBatch batch) {
        setTextureAtlas();
        background = textureAtlas.findRegion("bg");
        backgroundOffset++;
        if (backgroundOffset % GameRunner.HEIGHT == 0) {
            backgroundOffset = 0;
        }
        batch.draw(background, 0,
                -backgroundOffset,
                GameRunner.WIDTH,
                GameRunner.HEIGHT);
        batch.draw(background, 0,
                -backgroundOffset + GameRunner.HEIGHT,
                GameRunner.WIDTH,
                GameRunner.HEIGHT);
    }
}
