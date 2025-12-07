package SelenaMod.relics;

import SelenaMod.cards.Letter;
import SelenaMod.utils.ModHelper;
import SelenaMod.utils.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class PaperPenHeart extends CustomRelic {
    public static final String ID = ModHelper.makeID(PaperPenHeart.class.getSimpleName());
    private static final Texture TEXTURE = TextureLoader.getTexture(ModHelper.makeRelicImagePath(ID));
    private static final Texture OUTLINE = TextureLoader.getTexture(ModHelper.makeRelicOutlinePath(ID));

    public PaperPenHeart() {
        super(ID, TEXTURE, OUTLINE, RelicTier.BOSS, LandingSound.MAGICAL);

    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(PaperAndPen.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); i++) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(PaperAndPen.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    return;
                }
            }
        } else {
            super.obtain();
        }
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.masterHandSize++;
    }

    @Override
    public void onUnequip() {
        AbstractDungeon.player.masterHandSize--;
    }

    @Override
    public void atTurnStart() {
        this.grayscale = false;
    }

    @Override
    public void atBattleStart() {
        this.grayscale = false;
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c instanceof Letter) {
            this.flash();
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VigorPower(AbstractDungeon.player, 3)));
            if (!this.grayscale) {
                addToBot(new DrawCardAction(1));
                this.grayscale = true;
            }
        }
    }
}
