package SelenaMod.cards;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Premiere extends Letter{
    public static String ID= ModHelper.makeID(Premiere.class.getSimpleName());
    public Premiere() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.setDamage(7);

    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDamage(2);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (this.overrideEffect == null) {
            addCustomDamageAction(abstractMonster, AbstractGameAction.AttackEffect.FIRE);
        } else {
            addToBot(this.overrideEffect.trigger(abstractMonster));
        }
    }
}
