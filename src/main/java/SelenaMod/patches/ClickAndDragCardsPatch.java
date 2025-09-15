package SelenaMod.patches;

import SelenaMod.cards.Letter;
import SelenaMod.modifiers.ToneModifier;
import SelenaMod.modifiers.WhiteSpaceModifier;
import SelenaMod.powers.OverridePower;
import SelenaMod.powers.TonePower;
import SelenaMod.powers.WhiteSpacePower;
import SelenaMod.utils.ModHelper;
import SelenaMod.utils.ToneAndSpaceData;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClickAndDragCardsPatch {
    public static AbstractCard.CardTarget adjustLetterTarget(AbstractPlayer player, AbstractCard card) {
        List<ToneAndSpaceData> dataList = new ArrayList<>();
        List<AbstractPower> overridePower = new ArrayList<>();
        List<AbstractPower> tonePower = new ArrayList<>();
        List<AbstractPower> whiteSpacePower = new ArrayList<>();
        //整理powers
        for (AbstractPower p : player.powers) {
            if (p instanceof TonePower) {
                tonePower.add(p);
            } else if (p instanceof WhiteSpacePower) {
                whiteSpacePower.add(p);
            } else if (p instanceof OverridePower) {
                overridePower.add(p);
            }
        }
        //是否有覆写效果
        if (!overridePower.isEmpty()) {
            dataList.add(((OverridePower) overridePower.get(0)).toneAndSpaceData);
        } else if (card instanceof Letter) {
            if (((Letter) card).overrideEffect != null) {
                dataList.add(((Letter) card).overrideEffect.data);
            }
        }
        //整理转调效果
        List<AbstractCardModifier> toneMod = CardModifierManager.getModifiers(card, ToneModifier.ID);
        if (!toneMod.isEmpty()) {
            List<ToneAndSpaceData> tones = ((ToneModifier) toneMod.get(0)).tones.stream().map(x -> x.data).collect(Collectors.toList());
            int start = 0;
            if (tones.size() >= ToneModifier.TONE_MAX) {
                start = 1;
                if (!tonePower.isEmpty()) {
                    dataList.add(((TonePower) tonePower.get(0)).toneAndSpaceData);
                }
            }
            for (int i = start; i < tones.size(); i++) {
                dataList.add(tones.get(i));
            }
        }
        //整理留白效果
        List<AbstractCardModifier> whiteSpaceMod = CardModifierManager.getModifiers(card, WhiteSpaceModifier.ID);
        if (!whiteSpaceMod.isEmpty()) {
            List<ToneAndSpaceData> spaces = ((WhiteSpaceModifier) whiteSpaceMod.get(0)).spaces.stream().map(x -> x.data).collect(Collectors.toList());
            dataList.addAll(spaces);
        }
        for (AbstractPower p : whiteSpacePower) {
            dataList.add(((WhiteSpacePower) p).toneAndSpaceData);
        }

        //总结target
        AbstractCard.CardTarget target = AbstractCard.CardTarget.NONE;
        for (ToneAndSpaceData data : dataList) {
            target = ModHelper.adjustTarget(target, data.getTarget());
        }
        return target;

    }

    @SpirePatch(clz = AbstractPlayer.class, method = "clickAndDragCards")
    public static class ClickAndDragCards {
        @SpirePrefixPatch
        public static void prefix(AbstractPlayer __instance) {
            if (__instance.hoveredCard instanceof Letter) {
                __instance.hoveredCard.target = ClickAndDragCardsPatch.adjustLetterTarget(__instance, __instance.hoveredCard);
            }
        }
    }
}
