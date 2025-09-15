package SelenaMod.cardEffects;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class ScryEffect extends AbstractCardEffect {
    public ScryEffect(int amount) {
        super();
        this.data.setAmount(amount);
        PowerStrings toneStrings = CardCrawlGame.languagePack.getPowerStrings(ModHelper.makeID(ScryEffect.class.getSimpleName()));
        this.data.setName(toneStrings.NAME);
        this.data.setDescription(toneStrings.DESCRIPTIONS[0]);
        this.data.setTarget(toneStrings.DESCRIPTIONS.length > 1 ? toneStrings.DESCRIPTIONS[1] : "");
    }

    @Override
    public AbstractGameAction trigger(AbstractCreature target) {
        return new ScryAction(data.amount);
    }

    @Override
    public String getDescription() {
        return String.format(this.data.getDescription(), String.valueOf(this.data.amount));
    }
}
