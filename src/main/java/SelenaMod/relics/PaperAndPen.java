package SelenaMod.relics;

import SelenaMod.utils.ModHelper;
import SelenaMod.utils.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class PaperAndPen extends CustomRelic {
    public static final String ID = ModHelper.makeID(PaperAndPen.class.getSimpleName());
    private static final Texture TEXTURE = TextureLoader.getTexture(ModHelper.makeRelicImagePath(ID));
    private static final Texture OUTLINE = TextureLoader.getTexture(ModHelper.makeRelicOutlinePath(ID));
    public PaperAndPen() {
        super(ID, TEXTURE, OUTLINE,RelicTier.STARTER, LandingSound.MAGICAL);
        this.grayscale=false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        super.onCardDraw(drawnCard);
        if(!this.grayscale){
            addToBot(new DrawCardAction(1));
            this.grayscale=true;
        }
    }

    @Override
    public void atTurnStart() {
        this.grayscale=false;
    }

    @Override
    public void atBattleStart() {
        this.grayscale=false;
    }
}
