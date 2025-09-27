package SelenaMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DrawOrDisCardToHandAction extends AbstractGameAction {

    private AbstractCard card;
    private boolean randomCard;
    private AbstractPlayer p;
    private Pile pile;

    public DrawOrDisCardToHandAction(boolean randomCard, AbstractCard card, Pile pile) {
        this.card = card;
        this.p = AbstractDungeon.player;
        this.randomCard = randomCard;
        this.pile = pile;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    public DrawOrDisCardToHandAction(AbstractCard card) {
        this(false, card, Pile.ALL);
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            if (this.pile == Pile.DrawPile) {
                if (this.p.drawPile.isEmpty()) {
                    this.isDone = true;
                    return;
                }
                if (randomCard) {
                    this.card = this.p.drawPile.getRandomCard(AbstractDungeon.cardRandomRng);
                }

                if (this.p.hand.size() < 10) {
                    this.card.current_x = CardGroup.DRAW_PILE_X;
                    this.card.current_y = CardGroup.DRAW_PILE_Y;
                    this.p.hand.addToHand(card);
                    this.p.drawPile.removeCard(card);
                    card.unhover();
                    card.lighten(true);
                    this.p.hand.refreshHandLayout();
                    this.p.hand.applyPowers();
                } else {
                    this.p.drawPile.moveToDiscardPile(card);
                    AbstractDungeon.player.createHandIsFullDialog();
                }

            } else if (this.pile == Pile.DiscardPile) {
                if (this.p.discardPile.isEmpty()) {
                    this.isDone = true;
                    return;
                }
                if (randomCard) {
                    this.card = this.p.discardPile.getRandomCard(AbstractDungeon.cardRandomRng);
                }
                if (this.p.hand.size() < 10) {
                    this.card.current_x = CardGroup.DISCARD_PILE_X;
                    this.card.current_y = CardGroup.DISCARD_PILE_Y;
                    this.p.hand.addToHand(card);
                    this.p.discardPile.removeCard(card);
                    card.unhover();
                    card.lighten(true);
                    this.p.hand.refreshHandLayout();
                    this.p.hand.applyPowers();

                } else {
                    this.p.discardPile.moveToDiscardPile(card);
                    AbstractDungeon.player.createHandIsFullDialog();

                }

            } else if (this.pile == Pile.ALL) {
                if (this.card == null) {
                    this.isDone = true;
                    return;
                }
                if (this.p.drawPile.contains(this.card)) {
                    addToTop(new DrawOrDisCardToHandAction(false, this.card, Pile.DrawPile));
                } else if (this.p.discardPile.contains(this.card)) {
                    addToTop(new DrawOrDisCardToHandAction(false, this.card, Pile.DiscardPile));
                }
                this.isDone = true;
                return;
            }

        }
        this.isDone = true;
        tickDuration();
    }

    public enum Pile {
        DrawPile, DiscardPile, ALL
    }
}
