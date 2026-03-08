package SelenaMod.effects;

import SelenaMod.utils.ModHelper;
import SelenaMod.utils.PlayMusicHelper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import org.apache.commons.lang3.StringUtils;

public class PlayTempMusicEffect extends AbstractGameEffect {
    public static UIStrings TALK_STRINGS = CardCrawlGame.languagePack.getUIString(ModHelper.makeID("Talk"));


    private final PlayMusicHelper.Music music;
    private final String talk;

    public PlayTempMusicEffect(PlayMusicHelper.Music music, String talk) {
        this.music = music;
        this.talk = talk;
        this.duration = 0.5f;
        this.startingDuration = 0.5f;
    }

    @Override
    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        CardCrawlGame.music.fadeOutTempBGM();
        if (this.duration < 0) {
            this.isDone = true;
            PlayMusicHelper.PlayTempMusic(music, false);
            if (StringUtils.isNotEmpty(talk))
                ModHelper.AddTalkAction(talk);
        }

    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }
}
