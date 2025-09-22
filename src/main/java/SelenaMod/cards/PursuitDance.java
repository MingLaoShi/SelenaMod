package SelenaMod.cards;

import SelenaMod.core.SelenaMod;
import SelenaMod.interfaces.IFirstSight;
import SelenaMod.powers.AsFirstSightPower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PursuitDance extends CustomSelenaCard implements IFirstSight {
    public static String ID = ModHelper.makeID(PursuitDance.class.getSimpleName());

    public PursuitDance() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.setMagic(7);
        this.exhaust = true;
        this.isMultiDamage = true;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(3);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAllEnemiesAction(abstractPlayer, this.multiDamage, DamageInfo.DamageType.HP_LOSS, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
//        addToBot(new DamageAction(abstractPlayer, new DamageInfo(abstractPlayer, this.magicNumber, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
//        addToBot(new LoseHPAction(abstractMonster,abstractPlayer,this.magicNumber));
        if (SelenaMod.LOSE_HP_THIS_TURN) {
            addToBot(new HealAction(abstractPlayer, abstractPlayer, this.magicNumber));
        }
    }

    @Override
    public void triggerWhenDrawn() {
        if (AsFirstSightPower.isFirstSight(this)) {
            this.firstSight = false;
            this.onFirstSight();
        }
    }

    @Override
    public void onFirstSight() {
        addToBot(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, 1, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

//        addToBot(new LoseHPAction(AbstractDungeon.player,AbstractDungeon.player,1));
    }
}
