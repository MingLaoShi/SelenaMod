package SelenaMod.cards;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import java.util.Arrays;

public class OffenseDefense extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(OffenseDefense.class.getSimpleName());

    public OffenseDefense() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.setDamage(4);
        this.setMagic(3);
        this.setSecondMagic(3);
        this.selfRetain = true;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(2);
        this.setSecondMagic(5);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addCustomDamageAction(abstractMonster, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addPowerToEnemy(m, new VulnerablePower(m, 1, false));
        }
    }


    @Override
    public void triggerWhenDrawn() {
        int[] damageAmount = new int[AbstractDungeon.getCurrRoom().monsters.monsters.size()];
        Arrays.fill(damageAmount, this.secondMagicVar);
        addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, damageAmount, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot(new GainBlockAction(AbstractDungeon.player, this.magicNumber));
    }
}
