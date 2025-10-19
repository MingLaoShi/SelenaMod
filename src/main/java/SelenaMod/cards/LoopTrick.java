package SelenaMod.cards;

import SelenaMod.modifiers.RepeatModifier;
import SelenaMod.utils.ModHelper;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LoopTrick extends CustomSelenaCard{
    public static String ID= ModHelper.makeID("LoopTrick");
    public LoopTrick() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        this.setMagic(10);
        CardModifierManager.addModifier(this,new RepeatModifier());
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(-2);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }
}
