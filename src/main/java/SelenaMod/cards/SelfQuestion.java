package SelenaMod.cards;

import SelenaMod.cards.options.SelfQuestionOptions;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class SelfQuestion extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(SelfQuestion.class.getSimpleName());

    public SelfQuestion() {
        super(ID, 3, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.setMagic(6);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(3);
        this.upgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        ArrayList<AbstractCard> options = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            options.add(new SelfQuestionOptions(i, this.upgraded, this));
        }
        addToBot(new ChooseOneAction(options));

    }
}
