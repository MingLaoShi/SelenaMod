//package SelenaMod.cardEffects;
//
//import SelenaMod.utils.ToneAndSpaceData;
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//
//public class DamageMultiEffect extends AbstractCardEffect{
//    public DamageMultiEffect(ToneAndSpaceData data) {
//        super();
//        card.baseDamage=3;
//    }
//
//    @Override
//    public AbstractGameAction trigger(AbstractCreature target) {
//        return new AbstractGameAction() {
//            {
//                this.amount=DamageMultiEffect.this.data.amount;
//            }
//
//            @Override
//            public void update() {
//                for(int i=0;i<this.amount;i++){
//                    addToTop(new DamageAction(target,new DamageInfo(AbstractDungeon.player,DamageMultiEffect.this.card.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
//                }
//                this.isDone = true;
//            }
//        };
//    }
//
//    @Override
//    public String getDescription() {
//        return String.format(this.data.getDescription(),String.valueOf(card.damage),String.valueOf(this.data.amount));
//    }
//}
