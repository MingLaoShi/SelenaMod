package SelenaMod.utils;

import SelenaMod.cardEffects.AbstractCardEffect;
import SelenaMod.cardEffects.BlockEffect;
import SelenaMod.cardEffects.DamageEffect;
import SelenaMod.cardEffects.ScryEffect;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.HashMap;
import java.util.Map;

public class ToneAndSpaceDataManager {

    private static Map<ToneAndSpaceType,Class< ? extends AbstractCardEffect>> dataMap = new HashMap<>();

    public enum ToneAndSpaceType {
        SCRY,
        BLOCK,
        DAMAGE
    }

    public static void registerData(ToneAndSpaceType id, Class<? extends AbstractCardEffect> effectClass) {
        dataMap.put(id, effectClass);
    }

    public static void registerAll() {
        registerData(ToneAndSpaceType.SCRY, ScryEffect.class);
        registerData(ToneAndSpaceType.BLOCK, BlockEffect.class);
        registerData(ToneAndSpaceType.DAMAGE, DamageEffect.class);
    }

    public static AbstractCardEffect getEffectInstance(ToneAndSpaceData data){
        if (dataMap.containsKey(data.ID)) {
            try {
                return dataMap.get(data.ID).getDeclaredConstructor(ToneAndSpaceData.class).newInstance(data);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

}
