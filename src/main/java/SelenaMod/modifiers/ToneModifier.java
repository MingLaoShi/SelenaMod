package SelenaMod.modifiers;

import SelenaMod.cardEffects.AbstractCardEffect;
import SelenaMod.utils.ModHelper;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

public class ToneModifier extends AbstractCardModifier {

    public static final String ID = ModHelper.makeID(ToneModifier.class.getSimpleName());
    public static final int TONE_MAX = 3;
    public List<AbstractCardEffect> tones;
    private AbstractCard.CardTarget target = AbstractCard.CardTarget.NONE;

    public ToneModifier() {
        this.tones = new ArrayList<>();
    }

    @Override
    public AbstractCardModifier makeCopy() {
        ToneModifier copy = new ToneModifier();
        copy.tones = new ArrayList<>(this.tones);
        return copy;
    }

    public void addTone(AbstractCardEffect effect) {
        while (tones.size() >= TONE_MAX) {
            tones.remove(0);
        }
        tones.add(effect);
//        ModHelper.adjustTarget(this.target,data.getTarget());

    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        for (AbstractCardEffect tone : tones) {
            addToBot(tone.trigger(target));
        }
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        StringBuilder rawDescriptionBuilder = new StringBuilder(rawDescription);
        for (AbstractCardEffect tone : tones) {
            rawDescriptionBuilder.append(" NL ").append(tone.getDescription());
        }
        rawDescription = rawDescriptionBuilder.toString();
        return rawDescription;
    }


    @Override
    public void onApplyPowers(AbstractCard card) {
        super.onApplyPowers(card);
        this.tones.forEach(AbstractCardEffect::applyPowers);
    }

    @Override
    public void onCalculateCardDamage(AbstractCard card, AbstractMonster mo) {
        super.onCalculateCardDamage(card, mo);
        this.tones.forEach(tone -> tone.calcCardDamage(mo));
    }
}
