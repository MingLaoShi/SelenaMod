package SelenaMod.cards;

import SelenaMod.cardEffects.ExhaustEffect;
import SelenaMod.interfaces.IFirstSight;
import SelenaMod.powers.AsFirstSightPower;
import SelenaMod.powers.WhiteSpacePower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AsFirstSight extends CustomSelenaCard implements IFirstSight {
    public static String ID = ModHelper.makeID(AsFirstSight.class.getSimpleName());

    public AsFirstSight() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.setMagic(1);

    }

    @Override
    protected void upgradeMethod() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addPowerToSelf(new AsFirstSightPower(abstractPlayer, this.magicNumber));
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
        addTonePower(new WhiteSpacePower(AbstractDungeon.player, -1, new ExhaustEffect(this.cardID)),null);
    }
}
