package SelenaMod.utils;

import SelenaMod.cardEffects.AbstractCardEffect;
import SelenaMod.cards.Letter;
import SelenaMod.interfaces.DynamicEffectVar;
import SelenaMod.modifiers.ToneModifier;
import SelenaMod.modifiers.WhiteSpaceModifier;
import basemod.BaseMod;
import basemod.abstracts.AbstractCardModifier;
import basemod.abstracts.DynamicVariable;
import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.SmithPreview;
import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatches2;
import com.megacrit.cardcrawl.cards.AbstractCard;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import java.util.HashMap;
import java.util.List;

public class EffectsDynamicVariableManager extends DynamicVariable {
    public static EffectsDynamicVariableManager instance = new EffectsDynamicVariableManager();
    public static String workingKey = "";
    public static String KEY = ModHelper.makeID(EffectsDynamicVariableManager.class.getSimpleName()).toLowerCase();
    public static String KEY_WITH_SPACE = String.format(" !%s! ", KEY);

    public static void register(DynamicEffectVar var) {
        BaseMod.cardDynamicVariableMap.put(var.key(), instance);
    }

    private static boolean isLetter(AbstractCard card) {
        return card instanceof Letter;
    }

    @Override
    public String key() {
        return KEY;
    }

    @Override
    public boolean isModified(AbstractCard abstractCard) {
        if (isLetter(abstractCard)) {
            Letter letter = (Letter) abstractCard;
            if (letter.overrideEffect instanceof DynamicEffectVar && ((DynamicEffectVar) letter.overrideEffect).key().equals(workingKey)) {
                return ((DynamicEffectVar) letter.overrideEffect).modified(abstractCard);
            }

            List<AbstractCardModifier> modifierList = CardModifierManager.getModifiers(abstractCard, ToneModifier.ID);
            for (AbstractCardModifier modifier : modifierList) {
                ToneModifier toneModifier = (ToneModifier) modifier;
                for (AbstractCardEffect effect : toneModifier.tones) {
                    if (effect instanceof DynamicEffectVar && ((DynamicEffectVar) effect).key().equals(workingKey)) {
                        return ((DynamicEffectVar) effect).modified(abstractCard);
                    }
                }
            }

            modifierList = CardModifierManager.getModifiers(abstractCard, WhiteSpaceModifier.ID);
            for (AbstractCardModifier modifier : modifierList) {
                WhiteSpaceModifier whiteSpaceModifier = (WhiteSpaceModifier) modifier;
                for (AbstractCardEffect effect : whiteSpaceModifier.spaces) {
                    if (effect instanceof DynamicEffectVar && ((DynamicEffectVar) effect).key().equals(workingKey)) {
                        return ((DynamicEffectVar) effect).modified(abstractCard);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int value(AbstractCard abstractCard) {
        if (isLetter(abstractCard)) {
            Letter letter = (Letter) abstractCard;
            if (letter.overrideEffect instanceof DynamicEffectVar && ((DynamicEffectVar) letter.overrideEffect).key().equals(workingKey)) {
                return ((DynamicEffectVar) letter.overrideEffect).val(abstractCard);
            }

            List<AbstractCardModifier> modifierList = CardModifierManager.getModifiers(abstractCard, ToneModifier.ID);
            for (AbstractCardModifier modifier : modifierList) {
                ToneModifier toneModifier = (ToneModifier) modifier;
                for (AbstractCardEffect effect : toneModifier.tones) {
                    if (effect instanceof DynamicEffectVar && ((DynamicEffectVar) effect).key().equals(workingKey)) {
                        return ((DynamicEffectVar) effect).val(abstractCard);
                    }
                }
            }

            modifierList = CardModifierManager.getModifiers(abstractCard, WhiteSpaceModifier.ID);
            for (AbstractCardModifier modifier : modifierList) {
                WhiteSpaceModifier whiteSpaceModifier = (WhiteSpaceModifier) modifier;
                for (AbstractCardEffect effect : whiteSpaceModifier.spaces) {
                    if (effect instanceof DynamicEffectVar && ((DynamicEffectVar) effect).key().equals(workingKey)) {
                        return ((DynamicEffectVar) effect).val(abstractCard);
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public int baseValue(AbstractCard abstractCard) {
        if (isLetter(abstractCard)) {
            Letter letter = (Letter) abstractCard;
            if (letter.overrideEffect instanceof DynamicEffectVar && ((DynamicEffectVar) letter.overrideEffect).key().equals(workingKey)) {
                return ((DynamicEffectVar) letter.overrideEffect).baseVal(abstractCard);
            }

            List<AbstractCardModifier> modifierList = CardModifierManager.getModifiers(abstractCard, ToneModifier.ID);
            for (AbstractCardModifier modifier : modifierList) {
                ToneModifier toneModifier = (ToneModifier) modifier;
                for (AbstractCardEffect effect : toneModifier.tones) {
                    if (effect instanceof DynamicEffectVar && ((DynamicEffectVar) effect).key().equals(workingKey)) {
                        return ((DynamicEffectVar) effect).baseVal(abstractCard);
                    }
                }
            }

            modifierList = CardModifierManager.getModifiers(abstractCard, WhiteSpaceModifier.ID);
            for (AbstractCardModifier modifier : modifierList) {
                WhiteSpaceModifier whiteSpaceModifier = (WhiteSpaceModifier) modifier;
                for (AbstractCardEffect effect : whiteSpaceModifier.spaces) {
                    if (effect instanceof DynamicEffectVar && ((DynamicEffectVar) effect).key().equals(workingKey)) {
                        return ((DynamicEffectVar) effect).baseVal(abstractCard);
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public boolean upgraded(AbstractCard abstractCard) {
        if (isLetter(abstractCard)) {
            Letter letter = (Letter) abstractCard;
            if (letter.overrideEffect instanceof DynamicEffectVar && ((DynamicEffectVar) letter.overrideEffect).key().equals(workingKey)) {
                return ((DynamicEffectVar) letter.overrideEffect).upgraded(abstractCard);
            }

            List<AbstractCardModifier> modifierList = CardModifierManager.getModifiers(abstractCard, ToneModifier.ID);
            for (AbstractCardModifier modifier : modifierList) {
                ToneModifier toneModifier = (ToneModifier) modifier;
                for (AbstractCardEffect effect : toneModifier.tones) {
                    if (effect instanceof DynamicEffectVar && ((DynamicEffectVar) effect).key().equals(workingKey)) {
                        return ((DynamicEffectVar) effect).upgraded(abstractCard);
                    }
                }
            }

            modifierList = CardModifierManager.getModifiers(abstractCard, WhiteSpaceModifier.ID);
            for (AbstractCardModifier modifier : modifierList) {
                WhiteSpaceModifier whiteSpaceModifier = (WhiteSpaceModifier) modifier;
                for (AbstractCardEffect effect : whiteSpaceModifier.spaces) {
                    if (effect instanceof DynamicEffectVar && ((DynamicEffectVar) effect).key().equals(workingKey)) {
                        return ((DynamicEffectVar) effect).upgraded(abstractCard);
                    }
                }
            }
        }
        return false;
    }

    @SpirePatches2({@SpirePatch2(clz = SmithPreview.class, method = "ForEachDynamicVariable"), @SpirePatch2(clz = basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.RenderCustomDynamicVariable.Inner.class, method = "subRenderDynamicVariable"), @SpirePatch2(clz = basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.RenderCustomDynamicVariableCN.class, method = "Insert"),
            @SpirePatch2(clz = basemod.patches.com.megacrit.cardcrawl.screens.SingleCardViewPopup.RenderCustomDynamicVariable.Inner.class, method = "subRenderDynamicVariable"), @SpirePatch2(clz = basemod.patches.com.megacrit.cardcrawl.screens.SingleCardViewPopup.RenderCustomDynamicVariableCN.class, method = "Insert")})
    public static class GrabWorkingKey {
        @SpireInstrumentPatch
        public static ExprEditor patch() {
            return new ExprEditor() {
                public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getClassName().equals(HashMap.class.getName()) && m.getMethodName().equals("get"))
                        m.replace("{ $1 = " + EffectsDynamicVariableManager.GrabWorkingKey.class
                                .getName() + ".grabWorkingKey($1); $_ = $proceed($$);}");
                }
            };
        }

        public static Object grabWorkingKey(Object key) {
            if (key instanceof String)
                EffectsDynamicVariableManager.workingKey = (String) key;
            return key;
        }
    }
}
