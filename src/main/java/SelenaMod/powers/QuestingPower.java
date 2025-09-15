package SelenaMod.powers;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class QuestingPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.makeID(QuestingPower.class.getSimpleName());
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public QuestingPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        ModHelper.initPower(this);
    }

    @Override
    public void updateDescription() {
        this.description = String.format(strings.DESCRIPTIONS[0], this.amount);
    }

    @Override
    public boolean canPlayCard(AbstractCard card) {
        return card.type != AbstractCard.CardType.ATTACK;
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
    }
}
