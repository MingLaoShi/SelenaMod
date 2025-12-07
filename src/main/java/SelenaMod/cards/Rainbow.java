package SelenaMod.cards;

import SelenaMod.actions.RainbowAction;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Rainbow extends CustomSelenaCard {
    public static String ID = ModHelper.makeID("Rainbow");

    public Rainbow() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new RainbowAction(this.upgraded));
    }
}
