package SelenaMod.cards;

import SelenaMod.actions.FlowerTreeAction;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FlowerTree extends CustomSelenaCard {
    public static String ID = ModHelper.makeID("FlowerTree");

    public FlowerTree() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.setDamage(24);
        this.setBlock(10);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDamage(8);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addCustomDamageAction(abstractMonster, AbstractGameAction.AttackEffect.FIRE);
        addToBot(new FlowerTreeAction(this.block));
    }
}
