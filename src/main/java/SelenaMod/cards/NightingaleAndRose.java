package SelenaMod.cards;

import SelenaMod.actions.DrawOrDisCardToHandAction;
import SelenaMod.utils.ModHelper;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class NightingaleAndRose extends CustomSelenaCard{
    public static String ID= ModHelper.makeID(NightingaleAndRose.class.getSimpleName());
    public NightingaleAndRose() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.setMagic(1);
        this.setSecondMagic(3);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                ArrayList<AbstractCard> cardList=new ArrayList<>();
                cardList.addAll(AbstractDungeon.player.drawPile.group);
                cardList.addAll(AbstractDungeon.player.discardPile.group);
                addToTop(new SelectCardsAction(cardList,magicNumber, CardCrawlGame.languagePack.getUIString(ModHelper.makeID("GridSelectTitle")).TEXT[0],
                        true,c->true,selectedCards->{
                    for(int i=selectedCards.size()-1;i>=0;i--){
                        addToTop(new DrawOrDisCardToHandAction(selectedCards.get(i)));
                    }
                }));
                this.isDone=true;
            }
        });
        addToBot(new DamageAction(abstractPlayer, new DamageInfo(abstractPlayer, this.secondMagicVar, DamageInfo.DamageType.HP_LOSS)));
        this.secondMagicVar++;
    }
}
