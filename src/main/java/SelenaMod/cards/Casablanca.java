package SelenaMod.cards;

import SelenaMod.core.SelenaMod;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Casablanca extends CustomSelenaCard{
    public static String ID= ModHelper.makeID("Casablanca");
    public Casablanca() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.setMagic(99);
        this.setBlock(0);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDescription();
        this.selfRetain = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addCustomBlockAction();
    }

    @Override
    public void applyPowers() {
        this.baseBlock = SelenaMod.DAMAGED_THIS_TURN / 2;
        super.applyPowers();
        this.isBlockModified=true;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        this.baseBlock=SelenaMod.DAMAGED_THIS_TURN;
        if(this.upgraded){
            this.baseBlock*=2;
        }
        super.calculateCardDamage(mo);
        this.isBlockModified=true;
    }
}
