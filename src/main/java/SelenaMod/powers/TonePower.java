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
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class TonePower extends AbstractPower implements IPreUseCard {
    public static final String POWER_ID = ModHelper.makeID(TonePower.class.getSimpleName());
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private ToneAndSpaceData toneAndSpaceData;

    public TonePower(AbstractCreature owner, int amount, ToneAndSpaceDataManager.ToneAndSpaceType type) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        toneAndSpaceData = new ToneAndSpaceData();
        toneAndSpaceData.setID(type);
        toneAndSpaceData.setAmount(this.amount);
        toneAndSpaceData.setTone(true);
        PowerStrings toneStrings = CardCrawlGame.languagePack.getPowerStrings(ModHelper.makeID(type.name() + "_TONE"));
        toneAndSpaceData.setName(toneStrings.NAME);
        toneAndSpaceData.setDescription(toneStrings.DESCRIPTIONS[0]);
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
    public void onPreUseCard(AbstractCard card) {
        if(card.cardID.equals(Letter.ID)){
            ModHelper.addToneModifier(card,toneAndSpaceData);
            this.flash();
            card.flash();
            addToBot(new LetterWaitAction(10,card));
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }
}
