package SelenaMod.cards;

import SelenaMod.actions.ExhaustAllMiraclesAction;
import SelenaMod.powers.BewitchPower;
import SelenaMod.powers.SirenPower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Bewitch extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(Bewitch.class.getSimpleName());

    public Bewitch() {
        super(ID, 0, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.setMagic(1);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addPowerToSelf(new SirenPower(abstractPlayer));
        addPowerToSelf(new BewitchPower(abstractPlayer, this.magicNumber));
        addToBot(new ExhaustAllMiraclesAction());
    }
}
