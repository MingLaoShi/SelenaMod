package SelenaMod.cards;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WipeTears extends CustomSelenaCard {
    public static String ID = ModHelper.makeID("WipeTears");

    public WipeTears() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
        this.isEthereal = true;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addCustomBlockAction();
    }

    @Override
    public void applyPowers() {
        int baseBaseBlock = this.baseBlock;
        this.baseBlock = AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth;
        super.applyPowers();
        this.baseBlock = baseBaseBlock;
        this.isBlockModified = this.block != this.baseBlock;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int baseBaseBlock = this.baseBlock;
        this.baseBlock = AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth;
        super.calculateCardDamage(mo);
        this.baseBlock = baseBaseBlock;
        this.isBlockModified = this.block != this.baseBlock;
    }
}
