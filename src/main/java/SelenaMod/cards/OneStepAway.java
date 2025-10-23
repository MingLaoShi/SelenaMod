package SelenaMod.cards;

import SelenaMod.interfaces.IFirstSight;
import SelenaMod.powers.AsFirstSightPower;
import SelenaMod.powers.OneStepAwayPower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.UpgradeSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OneStepAway extends CustomSelenaCard implements IFirstSight {
    public static String ID= ModHelper.makeID("OneStepAway");
    public OneStepAway() {
        super(ID, 4, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.setMagic(1);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addPowerToSelf(new OneStepAwayPower(abstractPlayer, this.magicNumber));
    }

    @Override
    public void triggerWhenDrawn() {
        if (AsFirstSightPower.isFirstSight(this)) {
            this.firstSight = false;
            this.onFirstSight();
        }
    }

    @Override
    public void onFirstSight() {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard card : AbstractDungeon.player.hand.group) {
                    if (card instanceof Letter) {
                        addToTop(new UpgradeSpecificCardAction(card));
                    }
                }
                this.isDone = true;
            }
        });
    }
}
