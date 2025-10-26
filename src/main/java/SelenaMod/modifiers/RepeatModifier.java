package SelenaMod.modifiers;

import SelenaMod.cards.Confrontation;
import SelenaMod.cards.Correspondence;
import SelenaMod.cards.OutOfControl;
import SelenaMod.utils.ModHelper;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class RepeatModifier extends AbstractCardModifier {
    public static String ID = ModHelper.makeID(RepeatModifier.class.getSimpleName());
    private static String EXTRA_DESC = CardCrawlGame.languagePack.getUIString(ID).TEXT[0];
    @Override
    public AbstractCardModifier makeCopy() {
        return new RepeatModifier();
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return card instanceof Confrontation ||
                card instanceof OutOfControl
                ||card instanceof Correspondence;
    }

    @Override
    public boolean shouldApply(AbstractCard card) {
        return !CardModifierManager.hasModifier(card, RepeatModifier.ID);
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + EXTRA_DESC;
    }
}
