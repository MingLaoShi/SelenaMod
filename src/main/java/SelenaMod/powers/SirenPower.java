package SelenaMod.powers;

import SelenaMod.cards.Letter;
import SelenaMod.utils.ModHelper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.unique.ExpertiseAction;
import com.megacrit.cardcrawl.actions.unique.MadnessAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import com.megacrit.cardcrawl.vfx.stance.WrathParticleEffect;

import java.util.List;
import java.util.stream.Collectors;

public class SirenPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.makeID(SirenPower.class.getSimpleName());
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public int hpOnEnter = -1;
    private float particleTimer = 0.0F;
    private float particleTimer2 = 0.0F;

    public SirenPower(AbstractCreature owner) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.BUFF;
        ModHelper.initPower(this);
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
        this.hpOnEnter = this.owner.currentHealth;
    }

    public static boolean IsInSiren() {
        return AbstractDungeon.player.powers.stream().anyMatch(p -> p instanceof SirenPower);
    }

    @Override
    public void updateDescription() {
        this.description = strings.DESCRIPTIONS[0];
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new ExpertiseAction(AbstractDungeon.player, 10));
        addToBot(new MadnessAction());

    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            List<AbstractCard> letters = AbstractDungeon.player.hand.group.stream().filter(c -> c instanceof Letter).collect(Collectors.toList());
            if (!letters.isEmpty()) {
                AbstractCard card = letters.get(AbstractDungeon.cardRandomRng.random(letters.size() - 1));
                addToBot(new ExhaustSpecificCardAction(card, AbstractDungeon.player.hand));
            } else {
                addToBot(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, GameActionManager.turn, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            }
        }
    }

    @Override
    public void update(int slot) {
        super.update(slot);
        if (!Settings.DISABLE_EFFECTS) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.05F;
                AbstractDungeon.effectsQueue.add(new WrathParticleEffect());
            }
        }

        this.particleTimer2 -= Gdx.graphics.getDeltaTime();
        if (this.particleTimer2 < 0.0F) {
            this.particleTimer2 = MathUtils.random(0.3F, 0.4F);
            AbstractDungeon.effectsQueue.add(new StanceAuraEffect("Wrath"));
        }
    }
}
