package SelenaMod.cards;

import SelenaMod.cardEffects.RetainEffect;
import SelenaMod.powers.TonePower;
import SelenaMod.utils.ModHelper;
import SelenaMod.utils.SelenaEnums;
import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Fantasia extends CustomSelenaCard{
    public static String ID= ModHelper.makeID("Fantasia");
    public Fantasia() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.setDamage(6);
        this.tags.add(SelenaEnums.HAS_TONE_POWER);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDamage(3);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new DamageCallbackAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage), AbstractGameAction.AttackEffect.LIGHTNING,this::callback));
        addTonePower(new TonePower(AbstractDungeon.player,1,new RetainEffect(this.cardID,1)),abstractMonster);
    }

    private void callback(Integer integer) {
        addToTop(new ScryAction(integer));
    }

}
