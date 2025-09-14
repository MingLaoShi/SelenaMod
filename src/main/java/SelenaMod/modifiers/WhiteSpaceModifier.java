package SelenaMod.modifiers;

import SelenaMod.powers.WhiteSpacePower;
import SelenaMod.utils.ModHelper;
import SelenaMod.utils.ToneAndSpaceData;
import SelenaMod.utils.ToneAndSpaceDataManager;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import java.util.ArrayList;
import java.util.List;

public class WhiteSpaceModifier extends AbstractCardModifier {

    public List<ToneAndSpaceData> spaces;
    public static final String ID = ModHelper.makeID(WhiteSpacePower.class.getSimpleName());
    @Override
    public AbstractCardModifier makeCopy() {
        WhiteSpaceModifier copy=new WhiteSpaceModifier();
        copy.spaces=new ArrayList<>(spaces);
        return copy;
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    public void addWhiteSpace(ToneAndSpaceData data){
        this.spaces.add(data);
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        for(ToneAndSpaceData tone:spaces){
            addToBot(ToneAndSpaceDataManager.getAction(tone));
        }
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        StringBuilder rawDescriptionBuilder = new StringBuilder(rawDescription);
        for(ToneAndSpaceData tone:spaces){
            rawDescriptionBuilder.append(" NL ").append(tone.getDescription());
        }
        rawDescription = rawDescriptionBuilder.toString();
        return rawDescription;
    }
}
