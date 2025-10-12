package SelenaMod.cards;

import SelenaMod.powers.WanderPower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Wander extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(Wander.class.getSimpleName());

    public Wander() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.setMagic(1);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(1);
        this.upgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addPowerToSelf(new WanderPower(abstractPlayer, this.magicNumber, this.upgraded ? 1 : 0));
    }
}
