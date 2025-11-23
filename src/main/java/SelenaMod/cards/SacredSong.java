//package SelenaMod.cards;
//
//import SelenaMod.cardEffects.AbstractCardEffect;
//import SelenaMod.powers.OverridePower;
//import SelenaMod.powers.TonePower;
//import SelenaMod.powers.WhiteSpacePower;
//import SelenaMod.utils.ModHelper;
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import com.megacrit.cardcrawl.powers.AbstractPower;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class SacredSong extends CustomSelenaCard{
//    public static String ID= ModHelper.makeID("SacredSong");
//    public SacredSong() {
//        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
//        this.exhaust=true;
//    }
//
//    @Override
//    protected void upgradeMethod() {
//        this.upgradeDescription();
//        this.exhaust=false;
//    }
//
//    @Override
//    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
//        addToBot(new AbstractGameAction() {
//            @Override
//            public void update() {
//                List<WhiteSpacePower> whiteSpacePowerList=new ArrayList<>();
//                List<TonePower> tonePowerList=new ArrayList<>();
//                List<OverridePower> overridePowerList=new ArrayList<>();
//                for(AbstractPower power: AbstractDungeon.player.powers){
//                    if(power instanceof WhiteSpacePower){
//                        whiteSpacePowerList.add((WhiteSpacePower) power);
//                    }else if(power instanceof TonePower){
//                        tonePowerList.add((TonePower) power);
//                    }else if(power instanceof OverridePower){
//                        overridePowerList.add((OverridePower) power);
//                    }
//                }
//                if(!overridePowerList.isEmpty()){
//                    OverridePower temp=overridePowerList.get(AbstractDungeon.cardRandomRng.random(0,overridePowerList.size()));
//                    AbstractCardEffect effect=temp.effect.makeCopy(SacredSong.ID);
//                    addToTop(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new OverridePower(AbstractDungeon.player,temp.amount,effect)));
//                }
//                if(!tonePowerList.isEmpty()){
//                    TonePower temp=tonePowerList.get(AbstractDungeon.cardRandomRng.random(0,overridePowerList.size()));
//                    AbstractCardEffect effect=temp.effect.makeCopy(SacredSong.ID);
//                    addToTop(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new TonePower(AbstractDungeon.player,temp.amount,effect)));
//                }
//                if(!whiteSpacePowerList.isEmpty()){
//                    WhiteSpacePower temp=whiteSpacePowerList.get(AbstractDungeon.cardRandomRng.random(0,overridePowerList.size()));
//                    AbstractCardEffect effect=temp.effect.makeCopy(SacredSong.ID);
//                    addToTop(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new WhiteSpacePower(AbstractDungeon.player,temp.amount,effect)));
//                }
//                this.isDone=true;
//            }
//        });
//    }
//
//}
