package SelenaMod.cards;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Strike extends CustomSelenaCard{
    public static final String ID = ModHelper.makeID(Strike.class.getSimpleName());


    public Strike() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        this.setDamage(6);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDamage(3);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addCustomDamageAction(abstractMonster, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }
}
