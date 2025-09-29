package SelenaMod.cards;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.SlowPower;

public class Dusk extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(Dusk.class.getSimpleName());

    public Dusk() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF_AND_ENEMY);
        this.setBlock(15);
        this.setMagic(1);
        this.exhaust = true;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeBlock(3);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addCustomBlockAction();
        addPowerToEnemy(abstractMonster,new SlowPower(abstractPlayer, this.magicNumber));
    }
}
