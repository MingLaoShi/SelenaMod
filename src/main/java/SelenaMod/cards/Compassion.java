package SelenaMod.cards;

import SelenaMod.interfaces.IFirstSight;
import SelenaMod.powers.AsFirstSightPower;
import SelenaMod.powers.CompassionPower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;

public class Compassion extends CustomSelenaCard implements IFirstSight {
    public static String ID = ModHelper.makeID("Compassion");

    public Compassion() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.cardsToPreview = new Miracle();
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDescription();
        this.cardsToPreview.upgrade();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addPowerToSelf(new CompassionPower(abstractPlayer));
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
        addToBot(new LoseEnergyAction(1));
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new EnergizedBluePower(AbstractDungeon.player, 1)));
    }
}
