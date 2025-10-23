package SelenaMod.actions;

import SelenaMod.cards.Vortex;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;

public class VortexAction extends AbstractGameAction {
    public UIStrings GridSelectTitle = CardCrawlGame.languagePack.getUIString(ModHelper.makeID("GridSelectTitle"));
    private boolean upgraded;

    public VortexAction(boolean upgraded) {
        this.upgraded = upgraded;
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
            addToTop(new MakeTempCardInHandAction(card));
            this.isDone = true;
            AbstractDungeon.cardRewardScreen.discoveryCard = null;
        }
    }

    private ArrayList<AbstractCard> generateCards() {
        ArrayList<AbstractCard> cards = new ArrayList<>();
        for (AbstractCard card : Vortex.PREVIEW_CARDS) {
            AbstractCard cpy = card.makeCopy();
            if (this.upgraded) {
                cpy.upgrade();
            }
            cards.add(cpy);
        }
        return cards;
    }
}
