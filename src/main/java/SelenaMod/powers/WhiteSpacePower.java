package SelenaMod.powers;

import SelenaMod.actions.LetterWaitAction;
import SelenaMod.cardEffects.AbstractCardEffect;
import SelenaMod.cards.Letter;
import SelenaMod.interfaces.IPreUseCard;
import SelenaMod.modifiers.WhiteSpaceModifier;
import SelenaMod.utils.ModHelper;
import SelenaMod.utils.ToneAndSpaceData;
import basemod.ReflectionHacks;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DescriptionLine;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.List;

public class WhiteSpacePower extends AbstractPower implements IPreUseCard {
    public static final String POWER_ID = ModHelper.makeID(WhiteSpacePower.class.getSimpleName());
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static int counter = 0;
    public ToneAndSpaceData toneAndSpaceData;
    public AbstractCardEffect effect;

    public WhiteSpacePower(AbstractCreature owner, int amount, AbstractCardEffect effect) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.effect = effect;
        this.toneAndSpaceData = this.effect.data;
        this.effect.data.setType(ToneAndSpaceData.Type.SPACE);
        ModHelper.initPower(this);
        this.ID = POWER_ID + ":" + effect.getClass().getName() + counter;
        counter++;
        this.name = this.name + ":" + toneAndSpaceData.getName();
    }

    public void updateDescription() {
        String desc = strings.DESCRIPTIONS[0];
        String typeDesc = String.format(toneAndSpaceData.getDescription(), String.valueOf(toneAndSpaceData.amount), String.valueOf(toneAndSpaceData.amount2));
        this.description = desc + ": NL " + typeDesc;
    }

    @Override
    public void onPreUseCard(AbstractCard card, AbstractMonster target) {
        if (card.cardID.equals(Letter.ID) && this.canApply(card)) {
//            ArrayList<DescriptionLine> lines= ReflectionHacks.getPrivate(card,AbstractCard.class,"description");
//            ArrayList<DescriptionLine> originLines=new ArrayList<>(lines);
            ModHelper.addWhiteSpaceModifier(card, effect);
            this.flash();
//            card.flash();
//            card.calculateCardDamage(target);
//            card.initializeDescription();
//            ArrayList<DescriptionLine> newLines=new ArrayList<>(lines);
//            newLines.removeIf(l->{
//                for(DescriptionLine line:originLines){
//                    if(line.getText().equals(l.getText())){
//                        return true;
//                    }
//                }
//                return false;
//            });
//            addToBot(new LetterWaitAction(5, card,newLines));

            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }

    private boolean canApply(AbstractCard card) {
        if (!this.effect.canApply(card)) {
            return false;
        }
        List<AbstractCardModifier> modifierList = CardModifierManager.getModifiers(card, WhiteSpaceModifier.ID);
        if (modifierList.isEmpty()) {
            return true;
        } else {
            WhiteSpaceModifier modifier = (WhiteSpaceModifier) modifierList.get(0);
            return modifier.spaces.stream().noneMatch(s -> s.data.id.equals(this.toneAndSpaceData.id));
        }

    }
}
