package SelenaMod.cards;

import SelenaMod.powers.SkipNextDrawPower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EquilibriumPower;

public class WeiGu extends CustomSelenaCard{
    public static String ID= ModHelper.makeID(WeiGu.class.getSimpleName());
    public WeiGu() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.setDamage(18);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDamage(4);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addCustomDamageAction(abstractMonster, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        addPowerToSelf(new EquilibriumPower(abstractPlayer,1));
        addPowerToSelf(new SkipNextDrawPower(abstractPlayer,1));
    }
}
