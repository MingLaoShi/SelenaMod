package SelenaMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.*;
import java.util.stream.Collectors;

public class RainbowAction extends AbstractGameAction {
    private boolean upgraded = false;
    private List<AbstractCard.CardRarity> rarityList = new ArrayList<>();

    public RainbowAction(boolean upgraded) {
        this.upgraded = upgraded;
        this.duration = Settings.ACTION_DUR_FAST;

        this.initRarityList();
    }

    private void initRarityList() {
        rarityList.add(AbstractCard.CardRarity.BASIC);
        rarityList.add(AbstractCard.CardRarity.COMMON);
        rarityList.add(AbstractCard.CardRarity.UNCOMMON);
        rarityList.add(AbstractCard.CardRarity.RARE);
        rarityList.add(AbstractCard.CardRarity.CURSE);
        rarityList.add(AbstractCard.CardRarity.SPECIAL);
        Collections.reverse(rarityList);
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            for (AbstractCard.CardRarity rarity : rarityList) {
                List<AbstractCard> cardList = AbstractDungeon.player.drawPile.group.stream().filter(
                        c -> c.rarity == rarity
                ).collect(Collectors.toList());
                AbstractCard card = null;
                if (cardList.isEmpty() && this.upgraded) {
                    card = AbstractDungeon.getCard(rarity, AbstractDungeon.cardRandomRng);
                    if (Objects.nonNull(card))
                        addToTop(new MakeTempCardInHandAction(card));
                } else if (!cardList.isEmpty()) {
                    Collections.shuffle(cardList, new Random(AbstractDungeon.cardRandomRng.randomLong()));
                    card = cardList.get(0);
                    AbstractDungeon.player.drawPile.moveToHand(card);
                }
            }
        }
        this.isDone = true;
    }
}
