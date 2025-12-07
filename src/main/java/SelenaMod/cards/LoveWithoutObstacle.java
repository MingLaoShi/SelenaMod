package SelenaMod.cards;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AfterImagePower;
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
        for(AbstractMonster m: AbstractDungeon.getCurrRoom().monsters.monsters){
            addPowerToEnemy(m,new ThornsPower(m,this.magicNumber));
        }
        if(this.upgraded){
            addPowerToSelf(new ThornsPower(abstractPlayer,this.magicNumber));
        }
        addPowerToSelf(new AfterImagePower(abstractPlayer,this.secondMagicVar));
    }
}
