package SelenaMod.cards;

import SelenaMod.cardEffects.DamageAllEffect;
import SelenaMod.cardEffects.DamageEffect;
import SelenaMod.powers.OverridePower;
import SelenaMod.utils.ModHelper;
import SelenaMod.utils.SelenaEnums;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Finale extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(Finale.class.getSimpleName());

    public Finale() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        this.setDamage(6);
        this.setMagic(6);
        this.tags.add(SelenaEnums.HAS_TONE_POWER);
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
        if (!this.upgraded)
            addTonePower(new OverridePower(abstractPlayer, this.magicNumber, new DamageEffect(this.cardID, this.magicNumber, 1)),abstractMonster);
        else
            addTonePower(new OverridePower(abstractPlayer, this.magicNumber, new DamageAllEffect(this.cardID, this.magicNumber)),abstractMonster);
    }
}
