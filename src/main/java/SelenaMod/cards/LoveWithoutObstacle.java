package SelenaMod.cards;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AfterImagePower;
import com.megacrit.cardcrawl.powers.SharpHidePower;
import com.megacrit.cardcrawl.powers.ThornsPower;

public class LoveWithoutObstacle extends CustomSelenaCard{
    public static String ID= ModHelper.makeID("LoveWithoutObstacle");
    public LoveWithoutObstacle() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.ALL);
        this.setMagic(1);
        this.setSecondMagic(2);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (!this.upgraded) {
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                addPowerToEnemy(m, new ThornsPower(m, this.magicNumber));
            }
        } else {
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                addPowerToEnemy(m, new SharpHidePower(m, this.secondMagicVar));
            }
        }
        addPowerToSelf(new AfterImagePower(abstractPlayer,this.secondMagicVar));
    }
}
