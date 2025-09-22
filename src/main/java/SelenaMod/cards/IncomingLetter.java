package SelenaMod.cards;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class IncomingLetter extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(IncomingLetter.class.getSimpleName());

    public IncomingLetter() {
        super(ID, -2, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        setMagic(1);
        this.cardsToPreview = new Letter();
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    @Override
    public void triggerWhenDrawn() {
        if (!this.upgraded) {
            addToBot(new MakeTempCardInDrawPileAction(new Letter(), this.magicNumber, false, true));
        } else {
            addToBot(new MakeTempCardInHandAction(new Letter(), this.magicNumber));
        }
        addToBot(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }
}
