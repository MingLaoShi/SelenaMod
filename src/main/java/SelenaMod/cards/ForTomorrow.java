package SelenaMod.cards;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

public class ForTomorrow extends CustomSelenaCard {
    public static String ID = ModHelper.makeID("ForTomorrow");
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public ForTomorrow() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.setDamage(10);
        this.setMagic(1);
        this.cardsToPreview = new Miracle();
        this.isMultiDamage = true;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDescription();
        this.cardsToPreview.upgrade();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
//        addToBot(new DamageAllEnemiesAction(abstractPlayer, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot(new MakeTempCardInDrawPileAction(this.cardsToPreview, this.magicNumber, false, false, true));
        for (int i = 0; i < this.magicNumber; i++) {
            addToBot(new DamageAllEnemiesAction(abstractPlayer, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        this.magicNumber = baseMagicNumber + findMiracles();
        this.isMagicNumberModified = magicNumber != baseMagicNumber;
        if (!this.upgraded) {
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        } else {
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.magicNumber = baseMagicNumber + findMiracles();
        this.isMagicNumberModified = magicNumber != baseMagicNumber;
        if (!this.upgraded) {
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        } else {
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        }
    }

    private int findMiracles() {
        int count = 0;
        List<CardGroup> groups = new ArrayList<>();
        groups.add(AbstractDungeon.player.drawPile);
        groups.add(AbstractDungeon.player.discardPile);
        groups.add(AbstractDungeon.player.hand);
        groups.add(AbstractDungeon.player.exhaustPile);
        for (CardGroup g : groups) {
            for (AbstractCard c : g.group) {
                if (c instanceof Miracle) {
                    count++;
                }
            }
        }
        return count;
    }
}
