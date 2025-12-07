package SelenaMod.cards;

import SelenaMod.actions.ReDrawAction;
import SelenaMod.powers.AsFirstSightPower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TokyoLoveStory extends CustomSelenaCard {
    public static String ID = ModHelper.makeID("TokyoLoveStory");

    public TokyoLoveStory() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.cardsToPreview = new Miracle();
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDescription();
        this.cardsToPreview.upgrade();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy(), 1));
    }

    @Override
    public void triggerWhenDrawn() {
        if (AsFirstSightPower.isFirstSight(this)) {
            this.firstSight = false;
            addToBot(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
            addToBot(new ReDrawAction());
        }
    }
}
