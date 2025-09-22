package SelenaMod.actions;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;

public class SelectRareCardAction extends AbstractGameAction {

    public UIStrings GridSelectTitle = CardCrawlGame.languagePack.getUIString(ModHelper.makeID("GridSelectTitle"));

    public SelectRareCardAction(int amount) {
        this.amount = amount;

        this.duration = Settings.ACTION_DUR_FAST;

    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            ArrayList<AbstractCard> cards = generateCards();
            if (cards.isEmpty()) {
                this.isDone = true;
                return;
            }
            AbstractDungeon.cardRewardScreen.customCombatOpen(cards, GridSelectTitle.TEXT[0], false);
            tickDuration();
            return;
        }
        if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
            AbstractCard card = AbstractDungeon.cardRewardScreen.discoveryCard;
            card.setCostForTurn(0);
            addToTop(new MakeTempCardInHandAction(card));
            this.isDone = true;
            AbstractDungeon.cardRewardScreen.discoveryCard = null;
        }
    }

    private ArrayList<AbstractCard> generateCards() {
        ArrayList<AbstractCard> cards = new ArrayList();
        while (cards.size() < this.amount) {
            AbstractCard card = AbstractDungeon.getCard(AbstractCard.CardRarity.RARE, AbstractDungeon.cardRandomRng).makeCopy();
            if (cards.stream().noneMatch(c -> c.cardID.equals(card.cardID))) {
                cards.add(card);
            }
        }
        return cards;
    }
}
