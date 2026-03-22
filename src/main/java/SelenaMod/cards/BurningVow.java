package SelenaMod.cards;

import SelenaMod.effects.PlayTempMusicEffect;
import SelenaMod.powers.BurningVowPower;
import SelenaMod.utils.ModHelper;
import SelenaMod.utils.PlayMusicHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BurningVow extends CustomSelenaCard {
    public static String ID = ModHelper.makeID("BurningVow");

    public BurningVow() {
        super(ID, 0, CardType.POWER, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.SELF);
        this.setMagic(5);
        this.setSecondMagic(4);
    }

    @Override
    protected void upgradeMethod() {
        this.setSecondMagic(5);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (isFirstInThisBattle()) {
            AbstractDungeon.effectList.add(new PlayTempMusicEffect(PlayMusicHelper.Music.BURNING_VOW, null));
        }
        addPowerToSelf(new BurningVowPower(abstractPlayer, this.secondMagicVar));
    }

    private boolean isFirstInThisBattle() {
        return AbstractDungeon.actionManager.cardsPlayedThisCombat.stream().noneMatch(c -> c.cardID.equals(ID) && c != this);
    }
}
