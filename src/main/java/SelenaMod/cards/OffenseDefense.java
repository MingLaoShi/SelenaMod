package SelenaMod.cards;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class OffenseDefense extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(OffenseDefense.class.getSimpleName());

    public OffenseDefense() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.setDamage(4);
        this.setMagic(1);
        this.setBlock(3);
        this.setSecondMagic(3);
        this.selfRetain = true;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(1);
        this.upgradeBlock(2);
        this.setSecondMagic(5);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addCustomDamageAction(abstractMonster, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addPowerToEnemy(m, new VulnerablePower(m, this.magicNumber, false));
        }
    }

    @Override
    public void applyPowers() {
        int baseBaseDamage = this.baseDamage;
        this.baseDamage = this.baseSecondMagicVar;
        super.applyPowers();
        this.secondMagicVar = this.damage;
        this.baseDamage = baseBaseDamage;
        super.applyPowers();
    }

    @Override
    public void triggerWhenDrawn() {
        addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, this.secondMagicVar, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addCustomBlockAction();
    }
}
