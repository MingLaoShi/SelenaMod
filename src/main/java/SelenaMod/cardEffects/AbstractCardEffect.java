package SelenaMod.cardEffects;

import SelenaMod.utils.ToneAndSpaceData;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Strike_Red;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class AbstractCardEffect {
    public ToneAndSpaceData data;
    //用来计算powers数值的
    protected AbstractCard card;

    public AbstractCardEffect() {
        card = new Strike_Red();
        this.data = new ToneAndSpaceData();
    }

    public abstract AbstractGameAction trigger(AbstractCreature target);

    public void applyPowers() {
        card.applyPowers();
    }

    ;

    public void calcCardDamage(AbstractMonster mo) {
        card.calculateCardDamage(mo);
    }

    ;

    public abstract String getDescription();
}
