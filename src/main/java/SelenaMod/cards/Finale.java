package SelenaMod.cards;

import SelenaMod.powers.OverridePower;
import SelenaMod.utils.ModHelper;
import SelenaMod.utils.ToneAndSpaceDataManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Finale extends CustomSelenaCard{
    public static String ID= ModHelper.makeID(Finale.class.getSimpleName());
    public Finale() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        this.setDamage(6);
        this.setMagic(3);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDamage(3);
        this.upgradeMagicNumber(2);
        this.upgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addCustomDamageAction(abstractMonster, AbstractGameAction.AttackEffect.LIGHTNING);
        addPowerToSelf(new OverridePower(abstractPlayer,this.magicNumber, ToneAndSpaceDataManager.ToneAndSpaceType.DAMAGE));
    }
}
