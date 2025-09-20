package SelenaMod.powers;

import SelenaMod.actions.LetterWaitAction;
import SelenaMod.cardEffects.AbstractCardEffect;
import SelenaMod.cards.Letter;
import SelenaMod.interfaces.IPreUseCard;
import SelenaMod.modifiers.ToneModifier;
import SelenaMod.utils.ModHelper;
import SelenaMod.utils.ToneAndSpaceData;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.List;
import java.util.Optional;

public class TonePower extends AbstractPower implements IPreUseCard {
    public static final String POWER_ID = ModHelper.makeID(TonePower.class.getSimpleName());
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public ToneAndSpaceData toneAndSpaceData;
    public AbstractCardEffect effect;

    private static int counter=0;

    public TonePower(AbstractCreature owner, int amount, AbstractCardEffect effect) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.effect = effect;
        this.effect.data.setType(ToneAndSpaceData.Type.TONE);
        this.toneAndSpaceData = effect.data;
        ModHelper.initPower(this);
        this.ID = POWER_ID + ":" + effect.getClass().getName()+counter;
        counter++;
        this.name = this.name + ":" + toneAndSpaceData.getName();

    }

    public static AbstractPower AdjustApplyInstance(AbstractCard card) {
        Optional<AbstractPower> powerInstance = AbstractDungeon.player.powers.stream()
                .filter(power -> {
                    if(power instanceof TonePower){
                        TonePower p= (TonePower) power;
                        if(!p.effect.canApply(card)){
                            return false;
                        }
                        List<AbstractCardModifier> modifierList= CardModifierManager.getModifiers(card, ToneModifier.ID);
                        if(modifierList.isEmpty()){
                            return true;
                        }else{
                            ToneModifier modifier= (ToneModifier) modifierList.get(0);
                            return modifier.tones.stream().noneMatch(t->t.data.id.equals(p.toneAndSpaceData.id));
                        }

                    }
                    return false;
                }).findFirst();
        return powerInstance.orElse(null);
    }

    public void updateDescription() {
        String desc = strings.DESCRIPTIONS[0];
        String typeDesc = String.format(toneAndSpaceData.getDescription(), String.valueOf(toneAndSpaceData.amount), String.valueOf(toneAndSpaceData.amount2));
        this.description = desc + ": NL " + typeDesc;
    }

    @Override
    public void onPreUseCard(AbstractCard card, AbstractMonster target) {
        if (card.cardID.equals(Letter.ID) && this == AdjustApplyInstance(card)) {
            ModHelper.addToneModifier(card, effect);
            this.flash();
            card.flash();
            card.calculateCardDamage(target);
            card.initializeDescription();
            addToBot(new LetterWaitAction(5, card));
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }
}
