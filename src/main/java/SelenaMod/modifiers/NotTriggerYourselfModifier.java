package SelenaMod.modifiers;

import SelenaMod.utils.ModHelper;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class NotTriggerYourselfModifier extends AbstractCardModifier {
    public static String ID= ModHelper.makeID(NotTriggerYourselfModifier.class.getSimpleName());

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }


    @Override
    public AbstractCardModifier makeCopy() {
        return new NotTriggerYourselfModifier();
    }
}
