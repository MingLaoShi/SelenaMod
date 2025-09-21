package SelenaMod.cardEffects;

import SelenaMod.utils.ModHelper;
import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class ExhaustEffect extends AbstractCardEffect {
    public ExhaustEffect(String id) {
        super(id);
        this.data.setAmount(-1);
        PowerStrings toneStrings = CardCrawlGame.languagePack.getPowerStrings(ModHelper.makeID(ExhaustEffect.class.getSimpleName()));
        this.data.setName(toneStrings.NAME);
        this.data.setDescription("");
        this.data.setTarget(toneStrings.DESCRIPTIONS.length > 1 ? toneStrings.DESCRIPTIONS[1] : "");
    }

    @Override
    public AbstractGameAction trigger(AbstractCreature target) {
        return new WaitAction(0.1F);
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
    }
}
