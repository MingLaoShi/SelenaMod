package SelenaMod.cards;

import SelenaMod.powers.NextFormBondPower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FormBond extends CustomSelenaCard{
    public static String ID= ModHelper.makeID(FormBond.class.getSimpleName());
    public FormBond() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addPowerToSelf(new NextFormBondPower(abstractPlayer,this.upgraded ? 1 : 0));
    }
}
