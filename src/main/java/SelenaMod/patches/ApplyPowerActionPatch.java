package SelenaMod.patches;

import SelenaMod.powers.RebirthPower;
import SelenaMod.powers.SirenPower;
import SelenaMod.powers.WanderPower;
import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

public class ApplyPowerActionPatch {
    public static boolean replaceCondition(AbstractPower power) {
        return power instanceof WanderPower;
    }

    public static void replaceResult(AbstractPower power, AbstractPower targetPower) {
        if (power instanceof WanderPower && targetPower instanceof WanderPower) {
            ((WanderPower) targetPower).stackPower(power.amount, ((WanderPower) power).amount2);
        }
    }

    @SpirePatch(clz = ApplyPowerAction.class, method = "update")
    public static class ApplyPowerActionUpdatePatch {
        @SpireInstrumentPatch
        public static ExprEditor instrumentPatch() {
            return new ExprEditor() {
                @Override
                public void edit(MethodCall methodCall) throws CannotCompileException {
                    if (methodCall.getMethodName().equals("stackPower")) {
                        methodCall.replace(String.format("if(%s.replaceCondition($0)){" +
                                "%s.replaceResult(this.powerToApply,$0);" +
                                "}else{" +
                                "$_ = $proceed($$);" +
                                "}", ApplyPowerActionPatch.class.getName(), ApplyPowerActionPatch.class.getName()));
                    }
                }
            };
        }
    }

    @SpirePatch(clz = ApplyPowerAction.class,method = "update")
    public static class updatePatch{
        @SpirePrefixPatch
        public static SpireReturn<Void> prefixPatch(ApplyPowerAction _instance,AbstractPower ___powerToApply){
            if(_instance.target!=null&&___powerToApply instanceof SirenPower &&_instance.target.hasPower(RebirthPower.POWER_ID)){
                _instance.isDone=true;
                _instance.target.getPower(RebirthPower.POWER_ID).flash();
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
}
