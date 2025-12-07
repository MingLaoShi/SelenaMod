package SelenaMod.cards;

import SelenaMod.cardEffects.DrawEffect;
import SelenaMod.powers.WhiteSpacePower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Questing extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(Questing.class.getSimpleName());
    public static String QUESTING_SEEKING_ID = ID + Seeking.ID;

    public Questing() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.setBlock(13);
        this.setMagic(1);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeBlock(4);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addCustomBlockAction();
        addPowerToSelf(new SelenaMod.powers.QuestingPower(abstractPlayer, 1));
        addTonePower(new WhiteSpacePower(abstractPlayer, 1, new DrawEffect(QUESTING_SEEKING_ID, 1)),abstractMonster);
    }
}
