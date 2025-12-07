package SelenaMod.cardEffects;

import SelenaMod.interfaces.DynamicEffectVar;
import SelenaMod.utils.EffectsDynamicVariableManager;
import SelenaMod.utils.ModHelper;
import SelenaMod.utils.ToneAndSpaceData;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Strike_Red;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public abstract class AbstractCardEffect implements DynamicEffectVar,Cloneable {
    public ToneAndSpaceData data;
    //用来计算powers数值的
    protected AbstractCard card;

    public boolean waiting=false;

    public AbstractCardEffect(String id) {
        card = new Strike_Red();
        this.data = new ToneAndSpaceData();
        this.data.setId(id);
    }

    public abstract AbstractGameAction trigger(AbstractCreature target);

    public void applyPowers() {
        card.applyPowers();
    }


    public void calcCardDamage(AbstractMonster mo) {
        if(Objects.nonNull(mo))
            card.calculateCardDamage(mo);
    }


    public String getDescription() {
        EffectsDynamicVariableManager.register(this);
        return String.format(this.data.getDescription(), ModHelper.makeVarId(this.key()), ModHelper.makeVarId(this.key()));
    }

    public boolean canApply(AbstractCard card) {
        return true;
    }

    @Override
    public String key() {
        return this.data.id + this.data.type.name();
    }

    public void initializeCardEffect(AbstractCard card) {

    }

    public abstract AbstractCardEffect clone();

    public AbstractCardEffect makeCopy(String id){
        AbstractCardEffect effect=this.clone();
        if(!StringUtils.isEmpty(id)){
            effect.data.setId(id);
        }
        return effect;
    }
}
