package SelenaMod.cards;

import SelenaMod.interfaces.IFirstSight;
import SelenaMod.powers.AsFirstSightPower;
import SelenaMod.powers.SirenPower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConfusionPower;

public class SoundOfStorm extends CustomSelenaCard implements IFirstSight {
    public static String ID = ModHelper.makeID(SoundOfStorm.class.getSimpleName());

    public SoundOfStorm() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.setDamage(28);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDamage(4);
        this.upgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addCustomDamageAction(abstractMonster, AbstractGameAction.AttackEffect.SMASH);

    }

    @Override
    public void triggerWhenDrawn() {
        if (AsFirstSightPower.isFirstSight(this)) {
            this.firstSight = false;
            this.onFirstSight();
        }
    }

    @Override
    public void onFirstSight() {
        if (!this.upgraded) {
            addPowerToSelf(new ConfusionPower(AbstractDungeon.player));
        }
        addPowerToSelf(new SirenPower(AbstractDungeon.player));
    }
}
