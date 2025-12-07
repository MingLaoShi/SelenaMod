package SelenaMod.cards;

import SelenaMod.cardEffects.NextEnergyEffect;
import SelenaMod.powers.TonePower;
import SelenaMod.utils.ModHelper;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FaustHoliday extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(FaustHoliday.class.getSimpleName());

    public FaustHoliday() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
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
                List<CardGroup> groupList = new ArrayList<>();
                groupList.add(AbstractDungeon.player.drawPile);
                groupList.add(AbstractDungeon.player.hand);
                groupList.add(AbstractDungeon.player.discardPile);
                for (CardGroup g : groupList) {
                    cardList.addAll(g.group);
                }

                addToTop(new SelectCardsAction(cardList, 999, "", true, card -> card.type == CardType.CURSE || card.type == CardType.STATUS, cards -> {
                    if (FaustHoliday.this.upgraded && !cards.isEmpty()) {
                        addToTop(new DrawCardAction(cards.size()));
                    }
                    for (AbstractCard card : cards) {
                        Optional<CardGroup> group = groupList.stream().filter(g -> g.contains(card)).findFirst();
                        group.ifPresent(cardGroup -> addToTop(new ExhaustSpecificCardAction(card, cardGroup)));
                    }

                }));
                this.isDone = true;
            }
        });

        addTonePower(new TonePower(abstractPlayer, 1, new NextEnergyEffect(this.cardID, 1)),abstractMonster);
    }
}
