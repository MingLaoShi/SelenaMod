package SelenaMod.modifiers;

import SelenaMod.utils.ModHelper;
import SelenaMod.utils.ToneAndSpaceData;
import SelenaMod.utils.ToneAndSpaceDataManager;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import java.util.ArrayList;
import java.util.List;

public class ToneModifier extends AbstractCardModifier {

    public List<ToneAndSpaceData> tones;
    public static final String ID = ModHelper.makeID(ToneModifier.class.getSimpleName());
    public static final int TONE_MAX=3;
    public ToneModifier(){
        this.tones=new ArrayList<>();
    }
    @Override
    public AbstractCardModifier makeCopy() {
        ToneModifier copy=new ToneModifier();
        copy.tones=new ArrayList<>(this.tones);
        return copy;
    }

    public void addTone(ToneAndSpaceData data){
        while(tones.size()>=TONE_MAX){
            tones.remove(0);
        }
        tones.add(data);
    }
    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        for(ToneAndSpaceData tone:tones){
            addToBot(ToneAndSpaceDataManager.getAction(tone));
        }
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        StringBuilder rawDescriptionBuilder = new StringBuilder(rawDescription);
        for(ToneAndSpaceData tone:tones){
            rawDescriptionBuilder.append(" NL ").append(tone.getDescription());
        }
        rawDescription = rawDescriptionBuilder.toString();
        return rawDescription;
    }
}
