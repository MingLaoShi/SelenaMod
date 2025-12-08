package SelenaMod.potions;

import SelenaMod.cards.OutOfControl;
import SelenaMod.utils.ModHelper;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class RedTideSample extends AbstractPotion {
    public static String ID = ModHelper.makeID("RedTideSample");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);
    private static final PotionRarity rarity = PotionRarity.UNCOMMON;
    private static final PotionSize size = PotionSize.CARD;
    private static final PotionColor color = PotionColor.FAIRY;
    public static Color liquidColor = new Color(159.0F / 255.0F, 165.0F / 255.0F, 290.0F / 255.0F, 1.0F);
    public static Color hybridColor = new Color(233.0F / 255.0F, 233.0F / 255.0F, 233.0F / 255.0F, 1.0F);
    public static Color spotsColor = new Color(254.0F / 255.0F, 168.0F / 255.0F, 198.0F / 255.0F, 1.0F);

    public RedTideSample() {
        super(potionStrings.NAME, ID, rarity, size, color);

    }

    @Override
    public void initializeData() {
        super.initializeData();
        this.potency = getPotency();
        this.tips.clear();

        if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic("SacredBark")) {
            this.description = potionStrings.DESCRIPTIONS[1];
            this.tips.add(new PowerTip(this.name, this.description));

        } else {
            this.description = potionStrings.DESCRIPTIONS[0];
            this.tips.add(new PowerTip(this.name, this.description));

        }
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        addToBot(new GainBlockAction(AbstractDungeon.player, this.potency));
        addToBot(new MakeTempCardInHandAction(new OutOfControl(), this.potency / 30));
    }

    @Override
    public int getPotency(int i) {
        return 30;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new RedTideSample();
    }
}
