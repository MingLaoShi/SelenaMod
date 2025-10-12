package SelenaMod.cards.options;

import SelenaMod.cards.CustomSelenaCard;
import SelenaMod.cards.Letter;
import SelenaMod.cards.SelfQuestion;
import SelenaMod.modifiers.ReduceCostModifier;
import SelenaMod.utils.ModHelper;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import java.util.ArrayList;
import java.util.List;

public class SelfQuestionOptions extends CustomSelenaCard {
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(SelfQuestion.ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final int COST = -2;
    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    public static String ID = ModHelper.makeID(SelfQuestionOptions.class.getSimpleName());
    private static final String IMG_PATH = ModHelper.makeCardImagePath(ID);
    private int position;
    private SelfQuestion card;

    public SelfQuestionOptions(int position, boolean upgraded, SelfQuestion card) {
        super(ID, NAME, IMG_PATH, COST, CARD_STRINGS.EXTENDED_DESCRIPTION[position], TYPE, COLOR, RARITY, TARGET);
        this.position = position;
        switch (position) {
            case 0:
                this.setMagic(4);
                break;
            case 1:
            case 2:
                this.setMagic(1);
                break;
        }
        if (upgraded) {
            this.upgrade();
        }
        this.card = card;
    }

    public SelfQuestionOptions() {
        this(0, false, null);
    }

    @Override
    protected void upgradeMethod() {
        switch (this.position) {
            case 0:
                this.upgradeMagicNumber(2);
                break;
            case 2:
                this.upgradeMagicNumber(1);
                break;
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        switch (this.position) {
            case 0:
                addPowerToSelf(new StrengthPower(abstractPlayer, this.magicNumber));
                break;
            case 1:
                addToBot(new ReduceCostAction(this.card.uuid, this.magicNumber));
                break;
            case 2:
                addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        for (AbstractCard card1 : AbstractDungeon.player.masterDeck.group) {
                            if (card1.uuid.equals(card.uuid)) {
                                CardModifierManager.addModifier(card1, new ReduceCostModifier(1, SelfQuestionOptions.this.magicNumber));
                                break;
                            }
                        }
                        this.isDone = true;
                    }
                });
                break;
        }
    }

    @Override
    public void onChoseThisOption() {
        switch (this.position) {
            case 0:
                addPowerToSelf(new StrengthPower(AbstractDungeon.player, this.magicNumber));
                break;
            case 1:
                addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        List<Letter> letters = new ArrayList<>();
                        for (AbstractCard card2 : AbstractDungeon.player.hand.group) {
                            if (card2 instanceof Letter) {
                                letters.add((Letter) card2);
                            }
                        }
                        if (!letters.isEmpty()) {
                            AbstractCard card1 = letters.get(AbstractDungeon.cardRandomRng.random(0, letters.size() - 1));
                            addToTop(new ExhaustSpecificCardAction(card1, AbstractDungeon.player.hand, true));
                        }
                        this.isDone = true;
                    }
                });
                addToBot(new ReduceCostAction(this.card.uuid, this.magicNumber));
                break;
            case 2:
                addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {

                        for (AbstractCard card1 : AbstractDungeon.player.masterDeck.group) {
                            if (card1.uuid.equals(card.uuid)) {
                                CardModifierManager.addModifier(card1, new ReduceCostModifier(1, SelfQuestionOptions.this.magicNumber));
                                break;
                            }
                        }
                        this.isDone = true;
                    }
                });
                break;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new SelfQuestionOptions(this.position, this.upgraded, this.card);
    }
}
