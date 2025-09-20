package SelenaMod.cards;

import SelenaMod.core.SelenaMod;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PursuitDance extends CustomSelenaCard{
    public static String ID= ModHelper.makeID(PursuitDance.class.getSimpleName());
    public PursuitDance() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.setMagic(7);
        this.exhaust=true;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(3);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new LoseHPAction(abstractMonster,abstractPlayer,this.magicNumber));
        if(SelenaMod.LOSE_HP_THIS_TURN){
            addToBot(new HealAction(abstractPlayer,abstractPlayer,this.magicNumber));
        }
    }

    @Override
    public void triggerWhenDrawn() {
        if(this.firstSight){
            addToBot(new LoseHPAction(AbstractDungeon.player,AbstractDungeon.player,1));
            this.firstSight=false;
        }
    }
}
