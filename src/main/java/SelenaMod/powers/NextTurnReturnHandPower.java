package SelenaMod.powers;

import SelenaMod.actions.DrawOrDisCardToHandAction;
import SelenaMod.cards.BunnyDeerAndYou;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class NextTurnReturnHandPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.makeID(NextTurnReturnHandPower.class.getSimpleName());
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    private final AbstractCard card;
    public NextTurnReturnHandPower(AbstractPlayer owner,int amount, AbstractCard card) {
        this.card=card;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        ModHelper.initPower(this);
        this.ID+=card.uuid;
    }

    @Override
    public void stackPower(int stackAmount) {

    }

    @Override
    public void updateDescription() {
        this.description=String.format(strings.DESCRIPTIONS[0],this.amount,this.card.name);
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new ReducePowerAction(this.owner,this.owner,this,1));
    }

    @Override
    public void onRemove() {
        this.flash();
        addToBot(new DrawOrDisCardToHandAction(card));
    }
}
