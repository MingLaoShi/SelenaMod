package SelenaMod.cards;

import SelenaMod.modifiers.RepeatModifier;
import SelenaMod.powers.SirenPower;
import SelenaMod.utils.ModHelper;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OutOfControl extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(OutOfControl.class.getSimpleName());

    public OutOfControl() {
        super(ID, -1, CardType.CURSE, CardColor.CURSE, CardRarity.SPECIAL, CardTarget.NONE);
        CardModifierManager.addModifier(this, new RepeatModifier());
    }

    @Override
    protected void upgradeMethod() {

    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        if (SirenPower.IsInSiren()) {
            addToBot(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
        } else {
            this.addPowerToSelf(new SirenPower(AbstractDungeon.player));
        }
    }
}
