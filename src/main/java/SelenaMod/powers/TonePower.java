package SelenaMod.powers;

import SelenaMod.actions.LetterWaitAction;
import SelenaMod.cards.Letter;
import SelenaMod.interfaces.IPreUseCard;
import SelenaMod.utils.ModHelper;
import SelenaMod.utils.ToneAndSpaceData;
import SelenaMod.utils.ToneAndSpaceDataManager;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Optional;

public class TonePower extends AbstractPower implements IPreUseCard {
    public static final String POWER_ID = ModHelper.makeID(TonePower.class.getSimpleName());
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public ToneAndSpaceData toneAndSpaceData;

    public TonePower(AbstractCreature owner, int amount, ToneAndSpaceDataManager.ToneAndSpaceType type) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        toneAndSpaceData = new ToneAndSpaceData();
        toneAndSpaceData.setID(type);
        toneAndSpaceData.setAmount(this.amount);
        toneAndSpaceData.setTone(true);
        PowerStrings toneStrings = CardCrawlGame.languagePack.getPowerStrings(ModHelper.makeID(type.name() + "_EFFECT"));
        toneAndSpaceData.setName(toneStrings.NAME);
        toneAndSpaceData.setDescription(toneStrings.DESCRIPTIONS[0]);
        toneAndSpaceData.setTarget(toneStrings.DESCRIPTIONS.length>1?toneStrings.DESCRIPTIONS[1]:"");
        ModHelper.initPower(this);
        this.ID = POWER_ID + ":" + type.name();
        this.name = this.name + ":" + toneAndSpaceData.getName();

    }

    public void updateDescription() {
        String desc = strings.DESCRIPTIONS[0];
        String typeDesc = String.format(toneAndSpaceData.getDescription(), String.valueOf(toneAndSpaceData.amount));
        this.description = desc + ": NL " + typeDesc;
    }


    @Override
    public void onPreUseCard(AbstractCard card, AbstractMonster target) {
        if(card.cardID.equals(Letter.ID)&&this==AdjustApplyInstance()){
            ModHelper.addToneModifier(card,toneAndSpaceData);
            this.flash();
            card.flash();
            card.calculateCardDamage(target);
            addToBot(new LetterWaitAction(5,card));
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }

    public static AbstractPower AdjustApplyInstance(){
        Optional<AbstractPower> powerInstance=AbstractDungeon.player.powers.stream().filter(power->power.ID.contains(POWER_ID)).findFirst();
        return powerInstance.orElse(null);
    }
}
