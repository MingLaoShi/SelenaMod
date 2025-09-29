package SelenaMod.cards;

import SelenaMod.utils.ModHelper;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.List;

public class Sonnet18 extends CustomSelenaCard {
    public static String ID = ModHelper.makeID("Sonnet18");

    public Sonnet18() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        this.setMagic(1);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SelectCardsInHandAction(this.magicNumber, CardCrawlGame.languagePack.getUIString(ModHelper.makeID("GridSelectTitle")).TEXT[0],
                false, false, card -> true, this::callback));
    }

    private void callback(List<AbstractCard> cards) {
        for (int i = 0; i < cards.size(); i++) {
            AbstractCard.CardRarity rarity1 = cards.get(i).rarity;
            CardRarity nextRarity;
            switch (rarity1) {
                case BASIC:
                case COMMON:
                    nextRarity = CardRarity.UNCOMMON;
                    break;
                case UNCOMMON:
                case RARE:
                    nextRarity = CardRarity.RARE;
                    break;
                default:
                    nextRarity = CardRarity.RARE;
            }

            AbstractCard card = AbstractDungeon.getCard(nextRarity, AbstractDungeon.cardRandomRng).makeCopy();
            if (this.upgraded) {
                card.upgrade();
            }
            card.current_x = cards.get(i).current_x;
            card.current_y = cards.get(i).current_y;
            card.drawScale = cards.get(i).drawScale;
            card.angle = cards.get(i).angle;
            cards.set(i, card);
        }
    }
}
