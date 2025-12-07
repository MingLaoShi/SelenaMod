package SelenaMod.cards;

import SelenaMod.powers.SpringBreezePower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SpringBreeze extends CustomSelenaCard{
    public static String ID= ModHelper.makeID("SpringBreeze");
    public SpringBreeze() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);

    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDescription();
        this.isInnate=true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addPowerToSelf(new SpringBreezePower(abstractPlayer));
    }
}
