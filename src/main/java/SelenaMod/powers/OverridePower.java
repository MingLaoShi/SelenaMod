package SelenaMod.powers;

import SelenaMod.actions.LetterWaitAction;
import SelenaMod.cardEffects.AbstractCardEffect;
import SelenaMod.cards.Letter;
import SelenaMod.interfaces.IPreUseCard;
import SelenaMod.utils.ModHelper;
import SelenaMod.utils.ToneAndSpaceData;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Optional;

public class OverridePower extends AbstractPower implements IPreUseCard {
    public static final String POWER_ID = ModHelper.makeID(OverridePower.class.getSimpleName());
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public AbstractCardEffect effect;
    public ToneAndSpaceData toneAndSpaceData;

    private static int counter = 0;


    public OverridePower(AbstractCreature owner, int amount, AbstractCardEffect effect) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.effect = effect;
        this.toneAndSpaceData = this.effect.data;
        ModHelper.initPower(this);
        this.ID = POWER_ID + ":" + effect.getClass().getName()+counter;
        counter++;
        this.name = this.name + ":" + toneAndSpaceData.getName();
    }

    public static AbstractPower AdjustApplyInstance() {
        Optional<AbstractPower> powerInstance = AbstractDungeon.player.powers.stream().filter(power -> power.ID.contains(POWER_ID)).findFirst();
        return powerInstance.orElse(null);
    }

    public void updateDescription() {
        String desc = strings.DESCRIPTIONS[0];
        String typeDesc = String.format(toneAndSpaceData.getDescription(), String.valueOf(toneAndSpaceData.amount), String.valueOf(toneAndSpaceData.amount2));
        this.description = desc + ": NL " + typeDesc;
    }

    @Override
    public void onPreUseCard(AbstractCard card, AbstractMonster target) {
        if (card instanceof Letter && this == AdjustApplyInstance()) {
            Letter letter = (Letter) card;
            letter.setOverrideEffect(effect);
            this.flash();
            card.flash();
            card.calculateCardDamage(target);
            card.initializeDescription();
            addToBot(new LetterWaitAction(5, card));
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }

}
