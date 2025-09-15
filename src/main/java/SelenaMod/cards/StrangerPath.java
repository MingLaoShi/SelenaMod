package SelenaMod.cards;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class StrangerPath extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(StrangerPath.class.getSimpleName());

    public StrangerPath() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.setBlock(20);
        this.setMagic(2);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(-1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addCustomBlockAction();
        addPowerToSelf(new VulnerablePower(abstractPlayer, this.magicNumber, false));
    }
}
