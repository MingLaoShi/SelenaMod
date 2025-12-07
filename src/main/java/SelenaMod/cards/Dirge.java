package SelenaMod.cards;

import SelenaMod.cardEffects.StrengthEffect;
import SelenaMod.powers.SirenPower;
import SelenaMod.powers.TonePower;
import SelenaMod.utils.ModHelper;
import SelenaMod.utils.SelenaEnums;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Dirge extends CustomSelenaCard {
    public static String ID = ModHelper.makeID("Dirge");

    public Dirge() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.setDamage(1);
        this.setMagic(1);
        this.tags.add(SelenaEnums.HAS_TONE_POWER);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDamage(1);
        this.upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for (int i = 0; i < 5; i++) {
            addCustomDamageAction(abstractMonster, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        }

        addTonePower(new TonePower(abstractPlayer, this.magicNumber, new StrengthEffect(this.cardID, this.magicNumber)),abstractMonster);
    }

    @Override
    public void applyPowers() {
        int baseBaseDamage = this.baseDamage;
        if (AbstractDungeon.player.hasPower(SirenPower.POWER_ID)) {
            this.baseDamage += 4;
        }
        super.applyPowers();
        this.baseDamage = baseBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int baseBaseDamage = this.baseDamage;
        if (AbstractDungeon.player.hasPower(SirenPower.POWER_ID)) {
            this.baseDamage += 4;
        }
        super.calculateCardDamage(mo);
        super.applyPowers();
        this.baseDamage = baseBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }
}
