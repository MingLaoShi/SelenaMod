package SelenaMod.cards;

import SelenaMod.powers.OverridePower;
import SelenaMod.powers.TonePower;
import SelenaMod.powers.WhiteSpacePower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Cadenza extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(Cadenza.class.getSimpleName());

    public Cadenza() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.setDamage(7);
        this.isMultiDamage = true;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDamage(3);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAllEnemiesAction(abstractPlayer, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        for (AbstractPower p : abstractPlayer.powers) {
            if (p instanceof TonePower || p instanceof WhiteSpacePower || p instanceof OverridePower) {
                addToBot(new RemoveSpecificPowerAction(abstractPlayer, abstractPlayer, p));
                addToBot(new DamageAllEnemiesAction(abstractPlayer, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            }
        }
    }
}
