package SelenaMod.cardEffects;

import SelenaMod.utils.ToneAndSpaceData;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class ScryEffect extends AbstractCardEffect {
    public ScryEffect(ToneAndSpaceData data) {
        super(data);
    }
    @Override
    public AbstractGameAction trigger(AbstractCreature target) {
        return new ScryAction(data.amount);
    }
    @Override
    public String getDescription() {
        return String.format(this.data.getDescription(),String.valueOf(this.data.amount));
    }
}
