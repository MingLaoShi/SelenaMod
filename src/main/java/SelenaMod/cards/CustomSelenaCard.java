package SelenaMod.cards;

import SelenaMod.cardEffects.AbstractCardEffect;
import SelenaMod.powers.OverridePower;
import SelenaMod.powers.SpringBreezePower;
import SelenaMod.powers.TonePower;
import SelenaMod.powers.WhiteSpacePower;
import SelenaMod.utils.ModHelper;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Objects;

public abstract class CustomSelenaCard extends CustomCard {
    public boolean firstSight = true;
    public int baseSecondMagicVar=0;
    public int secondMagicVar=0;
    public boolean isSecondMagicModified = false;

    private String flavor2 = null;
    private ArrayList<PowerTip> flavorTip = new ArrayList<>();

    public CustomSelenaCard(String id, String name, String img, int cost, String rawDescription, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.flavor2 = ModHelper.FLAVOR2.get(id);
        if (StringUtils.isNotEmpty(flavor2)) {
            flavorTip.add(new PowerTip("@STSLIB:FLAVOR@", this.flavor2));
        }
    }

    public CustomSelenaCard(String id, int cost, AbstractCard.CardType type, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target) {
        super(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, ModHelper.makeCardImagePath(id), cost,
                CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION, type,
                ModHelper.getSelenaColor(), rarity, target);
        this.flavor2 = ModHelper.FLAVOR2.get(id);
        if (StringUtils.isNotEmpty(flavor2)) {
            flavorTip.add(new PowerTip("@STSLIB:FLAVOR@", this.flavor2));
        }
//        ReflectionHacks.setPrivate(this,AbstractCard.class,"textColor", Color.BLACK.cpy());
//        ReflectionHacks.setPrivate(this,AbstractCard.class,"goldColor", Color.GREEN.cpy());

    }

    public CustomSelenaCard(String id, int cost, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target) {
        super(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, ModHelper.makeCardImagePath(id), cost,
                CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION, type,
                color, rarity, target);
        this.flavor2 = ModHelper.FLAVOR2.get(id);
        if (StringUtils.isNotEmpty(flavor2)) {
            flavorTip.add(new PowerTip("@STSLIB:FLAVOR@", this.flavor2));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMethod();
        }
    }

    protected abstract void upgradeMethod();


    public void setDamage(int damage) {
        this.baseDamage = this.damage = damage;
    }

    public void setBlock(int block) {
        this.baseBlock = this.block = block;
    }

    public void setMagic(int magic) {
        this.baseMagicNumber = this.magicNumber = magic;
    }

    public void setSecondMagic(int secondMagic){
        this.baseSecondMagicVar=this.secondMagicVar=secondMagic;
    }

    public void setNums(int... nums) {
        if (nums.length > 0) this.setDamage(nums[0]);
        if (nums.length > 1) this.setBlock(nums[1]);
        if (nums.length > 2) this.setMagic(nums[2]);
        if (nums.length > 3) this.setSecondMagic(nums[3]);
    }

    protected void addCustomDamageAction(AbstractMonster target, AbstractGameAction.AttackEffect effect) {
        addToBot(new DamageAction(target,
                new DamageInfo(AbstractDungeon.player, this.damage, DamageInfo.DamageType.NORMAL), effect));
    }

    protected void addCustomBlockAction() {
        addToBot(new GainBlockAction(AbstractDungeon.player, this.block));
    }

    protected void addPowerToSelf(AbstractPower power) {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, power));
    }

    protected void addTonePower(AbstractPower power, AbstractMonster targetMonster) {
        if (AbstractDungeon.player.hasPower(SpringBreezePower.POWER_ID)) {
            AbstractCardEffect effect = null;
            if (power instanceof TonePower) {
                TonePower tonePower = (TonePower) power;
                effect = tonePower.effect;
            } else if (power instanceof WhiteSpacePower) {
                WhiteSpacePower whiteSpacePower = (WhiteSpacePower) power;
                effect = whiteSpacePower.effect;
            } else if (power instanceof OverridePower) {
                OverridePower overridePower = (OverridePower) power;
                effect = overridePower.effect;
            }
            if (Objects.nonNull(effect)) {

                effect.calcCardDamage(targetMonster);
                addToBot(effect.trigger(targetMonster));
            }
        } else {
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, power));
        }
    }

    protected void addPowerToEnemy(AbstractMonster target, AbstractPower power) {
        addToBot(new ApplyPowerAction(target, AbstractDungeon.player, power));
    }

    protected void addDrawCardAction() {
        addToBot(new DrawCardAction(this.magicNumber));
    }

    protected void upgradeDescription() {
        this.rawDescription = CardCrawlGame.languagePack.getCardStrings(this.cardID).UPGRADE_DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        AbstractCard card = super.makeCopy();
        if (card instanceof CustomSelenaCard) {
            ((CustomSelenaCard) card).firstSight = this.firstSight;
        }
        return card;
    }

    @Override
    public void initializeDescription() {
        super.initializeDescription();
        this.keywords = this.keywords;
    }

    @Override
    public void renderCardTip(SpriteBatch sb) {
        super.renderCardTip(sb);
        this.renderFlavor2(sb);
    }

    private void renderFlavor2(SpriteBatch sb) {
        if (!this.flavorTip.isEmpty() && !Settings.hideCards && this.getRenderTip()) {
//        if(!this.flavorTip.isEmpty()){
            float height = ModHelper.GetPowerTipHeight(this.flavorTip.get(0));
            // 根据关键词数量调整FLAVOR2的Y坐标，避免被关键词遮挡
            int keywordCount = this.keywords != null ? this.keywords.size() : 0;
            float keywordOffset = 0;
            if (keywordCount >= 3) {
                // 计算关键词占用的总高度
                for (String s : this.keywords) {
                    if (com.megacrit.cardcrawl.helpers.GameDictionary.keywords.containsKey(s)) {
                        float textHeight = -com.megacrit.cardcrawl.helpers.FontHelper.getSmartHeight(
                                com.megacrit.cardcrawl.helpers.FontHelper.tipBodyFont,
                                com.megacrit.cardcrawl.helpers.GameDictionary.keywords.get(s),
                                320.0F,
                                18.0F
                        ) - 7.0F * Settings.scale;
                        keywordOffset += textHeight + 16.0F * 3.15F; // BOX_EDGE_H = 16.0F
                    }
                }
            }
            String flavor = FlavorText.AbstractCardFlavorFields.flavor.get(this);
            if (StringUtils.isNotEmpty(flavor)) {
                float textHeight = -com.megacrit.cardcrawl.helpers.FontHelper.getSmartHeight(
                        com.megacrit.cardcrawl.helpers.FontHelper.tipBodyFont,
                        flavor,
                        320.0F,
                        18.0F
                ) - 7.0F * Settings.scale;
                keywordOffset += textHeight + 16.0F * 3.15F; // BOX_EDGE_H = 16.0F
            }
            keywordOffset -= AbstractCard.IMG_HEIGHT * this.drawScale / 2.0F;
            if (keywordOffset < 0) {
                keywordOffset = 0;
            }
            float y = this.current_y + AbstractCard.IMG_HEIGHT * this.drawScale / 2.0F + height + 80.0F * Settings.scale + keywordOffset;
//            if(y<this.current_y + AbstractCard.IMG_HEIGHT * this.drawScale / 2.0F + height + 80.0F * Settings.scale){
//                y=this.current_y + AbstractCard.IMG_HEIGHT * this.drawScale / 2.0F + height + 80.0F * Settings.scale;
//            }
            if (this.current_x > Settings.WIDTH * 0.75F) {
                ModHelper.RenderPowerTips(this.current_x - AbstractCard.IMG_WIDTH * this.drawScale / 2.0F - 12.0F * Settings.scale - 320.0F * Settings.scale,
                        y, sb, flavorTip);
            } else {
                ModHelper.RenderPowerTips(this.current_x + AbstractCard.IMG_WIDTH * this.drawScale / 2.0F + 12.0F * Settings.scale,
                        y, sb, flavorTip);
            }

        }

    }

    private boolean getRenderTip() {
        return ReflectionHacks.getPrivate(this, AbstractCard.class, "renderTip");
    }
}
