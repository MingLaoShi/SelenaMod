//package SelenaMod.patches;
//
//import SelenaMod.modifiers.RepeatModifier;
//import basemod.helpers.CardModifierManager;
//import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
//import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
//import com.megacrit.cardcrawl.actions.utility.UseCardAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.cards.CardGroup;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import javassist.CannotCompileException;
//import javassist.expr.ExprEditor;
//import javassist.expr.MethodCall;
//
//public class UseCardActionPatch {
//    @SpirePatch(clz = UseCardAction.class,method = SpirePatch.CONSTRUCTOR,paramtypez = {AbstractCard.class, AbstractCreature.class})
//    public static class constructorPatch{
//        @SpirePostfixPatch
//        public static void postfix(UseCardAction __instance,AbstractCard card, AbstractCreature target){
//            if(CardModifierManager.hasModifier(card, RepeatModifier.ID)){
//                __instance.reboundCard=true;
//            }
//        }
//    }
//}
