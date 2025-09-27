package SelenaMod.cards;

import SelenaMod.utils.ModHelper;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Fireworks extends CustomSelenaCard{
    public static String ID= ModHelper.makeID(Fireworks.class.getSimpleName());
    private static final String[] KEYWORDS=new String[]{

    };
    public Fireworks() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.exhaust=true;
        this.isEthereal=true;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SelectCardsInHandAction(1, CardCrawlGame.languagePack.getUIString(ModHelper.makeID("GridSelectTitle")).TEXT[0],
                card -> true, Fireworks::Callback
        ));
    }

    private static void Callback(List<AbstractCard> cards) {
        for(AbstractCard card:cards){
            ArrayList<String> keywords=card.keywords;
            if(keywords.isEmpty()) continue;
            List<AbstractCard> cardList=getCardByKeywords(keywords);
            AbstractCard c=cardList.get(AbstractDungeon.cardRandomRng.random(cardList.size()-1)).makeCopy();
            AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(c));
        }
    }

    private static List<AbstractCard> getCardByKeywords(List<String> keywords){
        Set<String> keywordSet = new HashSet<>(keywords);
        List<AbstractCard> cardList=new ArrayList<>();
        CardGroup[] cardGroups=new CardGroup[]{AbstractDungeon.srcCommonCardPool,AbstractDungeon.srcUncommonCardPool,
        AbstractDungeon.srcRareCardPool};
        for(CardGroup group:cardGroups){
            outer:
            for(AbstractCard card: group.group){
                for(String keyword:card.keywords){
                    if(keywordSet.contains(keyword)){
                        cardList.add(card);
                        continue outer;
                    }
                }
            }
        }
        return cardList;

    }

}
