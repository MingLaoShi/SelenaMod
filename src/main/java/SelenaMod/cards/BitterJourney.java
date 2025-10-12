package SelenaMod.cards;

import SelenaMod.powers.BitterJourneyPower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BitterJourney extends CustomSelenaCard{
    public static String ID= ModHelper.makeID("BitterJourney");
    public BitterJourney() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.setMagic(10);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeBaseCost(0);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addPowerToSelf(new BitterJourneyPower(abstractPlayer, this.magicNumber));
    }
}
