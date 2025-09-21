package SelenaMod.cards;

import SelenaMod.cardEffects.NextEnergyEffect;
import SelenaMod.powers.TonePower;
import SelenaMod.utils.ModHelper;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class FaustHoliday extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(FaustHoliday.class.getSimpleName());

    public FaustHoliday() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.setMagic(1);
        this.exhaust = true;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new AbstractGameAction() {
            {
                boolean upgraded = FaustHoliday.this.upgraded;
            }

            @Override
            public void update() {
                ArrayList<AbstractCard> cardList = new ArrayList<>();
                cardList.addAll(AbstractDungeon.player.drawPile.group);
                if (upgraded) {
                    cardList.addAll(AbstractDungeon.player.discardPile.group);
                }

                addToTop(new SelectCardsAction(cardList, 999, "", true, card -> card.type == CardType.CURSE || card.type == CardType.STATUS, cards -> {
                    for (AbstractCard card : cards) {
                        addToTop(new ExhaustSpecificCardAction(card, AbstractDungeon.player.drawPile.contains(card) ? AbstractDungeon.player.drawPile : AbstractDungeon.player.discardPile));
                    }
                }));
                this.isDone = true;
            }
        });

        addPowerToSelf(new TonePower(abstractPlayer, 1, new NextEnergyEffect(this.cardID, 1)));
    }
}
