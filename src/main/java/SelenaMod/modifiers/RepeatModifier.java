package SelenaMod.modifiers;

import SelenaMod.cards.Confrontation;
import SelenaMod.cards.Correspondence;
import SelenaMod.cards.OutOfControl;
import SelenaMod.utils.ModHelper;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class RepeatModifier extends AbstractCardModifier {
    public static String ID = ModHelper.makeID(RepeatModifier.class.getSimpleName());

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
}
