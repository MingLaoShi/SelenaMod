package SelenaMod.effects;

import SelenaMod.utils.PlayMusicHelper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class FiftyTwoHzMusicEffect extends AbstractGameEffect {

    public FiftyTwoHzMusicEffect() {
        this.duration = 0.5F;
        this.startingDuration = 0.5F;
    }

    @Override
    public void update() {
        CardCrawlGame.music.fadeOutTempBGM();
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
            PlayMusicHelper.PlayTempMusic(PlayMusicHelper.Music.MUSIC_52HZ, false);
        }

    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }
}
