package SelenaMod.cardEffects;

import SelenaMod.actions.PeonyPavilionAction;
import SelenaMod.cards.Letter;
import SelenaMod.utils.ModHelper;
import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class PeonyPavilionEffect extends AbstractCardEffect {
    public PeonyPavilionEffect(String id) {
        super(id);
        this.data.setAmount(-1);
        PowerStrings toneStrings = CardCrawlGame.languagePack.getPowerStrings(ModHelper.makeID(PeonyPavilionEffect.class.getSimpleName()));
        this.data.setName(toneStrings.NAME);
        this.data.setDescription(toneStrings.DESCRIPTIONS[0]);
        this.data.setTarget(toneStrings.DESCRIPTIONS.length > 1 ? toneStrings.DESCRIPTIONS[1] : "");
    }

    @Override
    public AbstractGameAction trigger(AbstractCreature target) {
        return new PeonyPavilionAction();
    }

    @Override
    public AbstractCardEffect clone() {
        return new PeonyPavilionEffect(this.data.id);
    }

    @Override
    public int val(AbstractCard card) {
        return 0;
    }

    @Override
    public int baseVal(AbstractCard card) {
        return 0;
    }

    @Override
    public boolean modified(AbstractCard card) {
        return false;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return false;
    }

    @Override
    public void initializeCardEffect(AbstractCard card) {
        CardModifierManager.addModifier(card, new ExhaustMod());
        AbstractDungeon.actionManager.addToTop(new AbstractGameAction() {
            @Override
            public void update() {
                if (card instanceof Letter) {
                    Letter letter = (Letter) card;
                    letter.textureReplaceDuration -= Gdx.graphics.getDeltaTime();
                    if (letter.textureReplaceDuration <= 0.0F) {
                        letter.textureReplaceDuration = 0.0F;
                        this.isDone = true;
                    }
                }

            }
        });
    }
}
