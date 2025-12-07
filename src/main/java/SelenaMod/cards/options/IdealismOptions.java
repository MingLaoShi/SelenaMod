package SelenaMod.cards.options;

import SelenaMod.cards.CustomSelenaCard;
import SelenaMod.cards.Idealism;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;

import java.util.List;
import java.util.stream.Collectors;

public class IdealismOptions extends CustomSelenaCard {
    public static String ID = ModHelper.makeID("IdealismOptions");
    private static final CardStrings cardString = CardCrawlGame.languagePack.getCardStrings(ID);
    private int index = 0;

    public IdealismOptions(int index) {
        super(ID, -2, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
        this.index = index;
        this.rawDescription = cardString.EXTENDED_DESCRIPTION[index];
        this.initializeDescription();
    }

    @Override
    protected void upgradeMethod() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    @Override
    public void onChoseThisOption() {
        if (this.index == 0) {
            this.removeAllIdealismFormDeck();
        }
    }

    private void removeAllIdealismFormDeck() {
        List<AbstractCard> cardList = AbstractDungeon.player.masterDeck.group.stream().filter(c -> c.cardID.equals(Idealism.ID))
                .collect(Collectors.toList());
        float displayCount = 0.0F;
        for (AbstractCard card : cardList) {
            card.untip();
            card.unhover();
            AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(
                    card, Settings.WIDTH / 3.0F + displayCount, Settings.HEIGHT / 2.0F
            ));
            displayCount += Settings.WIDTH / 6.0F;
            AbstractDungeon.player.masterDeck.removeCard(card);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new IdealismOptions(this.index);
    }
}
