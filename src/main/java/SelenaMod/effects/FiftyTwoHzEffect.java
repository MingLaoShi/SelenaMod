package SelenaMod.effects;

import SelenaMod.utils.ModHelper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;

public class FiftyTwoHzEffect extends AbstractGameEffect {
    private static final Color FIFTY_TWO_HZ = Color.GREEN.cpy();
    private AbstractGameEffect effect;
    private boolean secondEffect;
    public static UIStrings TALK_STRINGS = CardCrawlGame.languagePack.getUIString(ModHelper.makeID("Talk"));

    public FiftyTwoHzEffect() {
        this.duration = 2.0F;
        this.secondEffect = false;
        this.effect = new ShockWaveEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, FIFTY_TWO_HZ, ShockWaveEffect.ShockWaveType.CHAOTIC);
    }

    @Override
    public void update() {
        if (!this.effect.isDone)
            this.effect.update();
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F && !secondEffect) {
            this.duration = 2.0F;
            this.secondEffect = true;
            this.effect = new ShockWaveEffect(Settings.WIDTH, Settings.HEIGHT, FIFTY_TWO_HZ, ShockWaveEffect.ShockWaveType.CHAOTIC);
            if (ModHelper.GetSaveValue().FistPlayFiftyTwoHz == 0) {
                ModHelper.AddTalkAction(TALK_STRINGS.TEXT[0]);
                ModHelper.GetSaveValue().FistPlayFiftyTwoHz++;
            }
        }
        if (this.effect.isDone && secondEffect) {
            this.isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        this.effect.render(spriteBatch);
    }

    @Override
    public void dispose() {

    }
}
