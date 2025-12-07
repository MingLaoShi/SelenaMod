package SelenaMod.cards;

import SelenaMod.cardEffects.VigorEffect;
import SelenaMod.powers.DamageNextTurnPower;
import SelenaMod.powers.TonePower;
import SelenaMod.utils.ModHelper;
import SelenaMod.utils.SelenaEnums;
import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Minuet extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(Minuet.class.getSimpleName());

    public Minuet() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.setDamage(5);
        this.setMagic(3);
        this.tags.add(SelenaEnums.HAS_TONE_POWER);

    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDamage(2);
        this.upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageCallbackAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage), AbstractGameAction.AttackEffect.SLASH_DIAGONAL, i -> {
            this.callback(i, abstractMonster);
        }));
        addTonePower(new TonePower(abstractPlayer, this.magicNumber, new VigorEffect(this.cardID, this.magicNumber)),abstractMonster);
    }

    private void callback(Integer integer, AbstractMonster m) {
        addToTop(new ApplyPowerAction(m, AbstractDungeon.player, new DamageNextTurnPower(m, integer)));
    }
}
