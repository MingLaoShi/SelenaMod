package SelenaMod.cards;

import SelenaMod.modifiers.RepeatModifier;
import SelenaMod.utils.ModHelper;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class TheyLoveEachOther extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(TheyLoveEachOther.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private boolean changeEffect = false;

    public TheyLoveEachOther() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.setMagic(3);
        CardModifierManager.addModifier(this, new RepeatModifier());
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (this.changeEffect) {
            addPowerToSelf(new StrengthPower(abstractPlayer, -1));
            addPowerToSelf(new DexterityPower(abstractPlayer, this.magicNumber));
        } else {
            addPowerToSelf(new StrengthPower(abstractPlayer, this.magicNumber));
            addPowerToSelf(new DexterityPower(abstractPlayer, -1));
        }
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                changeEffect = !changeEffect;
                TheyLoveEachOther.this.initializeDescription();
                this.isDone = true;
            }
        });
    }

    @Override
    public void initializeDescription() {
        if (this.changeEffect) {
            this.rawDescription = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        } else {
            this.rawDescription = CARD_STRINGS.DESCRIPTION;
        }
        super.initializeDescription();
    }
}
