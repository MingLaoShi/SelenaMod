package SelenaMod.powers;

import SelenaMod.actions.DrawOrDisCardToHandAction;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.List;

public class EchoingPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.makeID(EchoingPower.class.getSimpleName());
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    private List<AbstractCard> lastTurnCards=new ArrayList<>();
    public EchoingPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        ModHelper.initPower(this);
    }

    @Override
    public void updateDescription() {
        this.description = String.format(strings.DESCRIPTIONS[0], this.amount);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        List<AbstractCard> cardList;
        cardList=this.lastTurnCards.subList(Math.max(0,this.lastTurnCards.size()-this.amount),this.lastTurnCards.size());
        for (AbstractCard card : cardList) {
            addToBot(new DrawOrDisCardToHandAction(card));
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.lastTurnCards=new ArrayList<>(AbstractDungeon.actionManager.cardsPlayedThisTurn);
    }
}
