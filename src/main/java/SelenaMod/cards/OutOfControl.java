package SelenaMod.cards;

import SelenaMod.powers.SirenPower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OutOfControl extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(OutOfControl.class.getSimpleName());

    public OutOfControl() {
        super(ID, -2, CardType.STATUS, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        this.isEthereal = true;
    }

    @Override
    protected void upgradeMethod() {

    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        super.triggerOnEndOfPlayerTurn();
        if (!SirenPower.IsInSiren()) {
            this.addPowerToSelf(new SirenPower(AbstractDungeon.player));
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }


}
