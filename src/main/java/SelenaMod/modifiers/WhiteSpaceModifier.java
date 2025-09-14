package SelenaMod.modifiers;

import SelenaMod.cardEffects.AbstractCardEffect;
import SelenaMod.powers.WhiteSpacePower;
import SelenaMod.utils.ModHelper;
import SelenaMod.utils.ToneAndSpaceData;
import SelenaMod.utils.ToneAndSpaceDataManager;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

public class WhiteSpaceModifier extends AbstractCardModifier {

    public List<AbstractCardEffect> spaces=new ArrayList<>();
    public static final String ID = ModHelper.makeID(WhiteSpacePower.class.getSimpleName());
    private AbstractCard.CardTarget target= AbstractCard.CardTarget.NONE;


    @Override
    public AbstractCardModifier makeCopy() {
        WhiteSpaceModifier copy=new WhiteSpaceModifier();
        copy.spaces=new ArrayList<>(spaces);
        return copy;
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    public void addWhiteSpace(ToneAndSpaceData data){
        this.spaces.add(ToneAndSpaceDataManager.getEffectInstance(data));
//        ModHelper.adjustTarget(this.target,data.getTarget());
    }

//    @Override
//    public void onApplyPowers(AbstractCard card) {
//        super.onApplyPowers(card);
//        card.target=ModHelper.adjustTarget(card.target,this.target);
//    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        for(AbstractCardEffect tone:spaces){
            addToBot(tone.trigger(target));
        }
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        StringBuilder rawDescriptionBuilder = new StringBuilder(rawDescription);
        for(AbstractCardEffect tone:spaces){
            rawDescriptionBuilder.append(" NL ").append(tone.getDescription());
        }
        rawDescription = rawDescriptionBuilder.toString();
        return rawDescription;
    }

    @Override
    public void onApplyPowers(AbstractCard card) {
        super.onApplyPowers(card);
        this.spaces.forEach(AbstractCardEffect::applyPowers);
    }

    @Override
    public void onCalculateCardDamage(AbstractCard card, AbstractMonster mo) {
        super.onCalculateCardDamage(card, mo);
        this.spaces.forEach(space->space.calcCardDamage(mo));
    }
}
