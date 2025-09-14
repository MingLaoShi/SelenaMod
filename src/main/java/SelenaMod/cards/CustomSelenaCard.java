package SelenaMod.cards;

import SelenaMod.utils.ModHelper;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class CustomSelenaCard extends CustomCard {

    public CustomSelenaCard(String id, String name, String img, int cost, String rawDescription, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    public CustomSelenaCard(String id, int cost, AbstractCard.CardType type, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target) {
        super(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, ModHelper.makeCardImagePath(id), cost,
                CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION, type,
                ModHelper.getSelenaColor(), rarity, target);
    }

    public CustomSelenaCard(String id, int cost, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target) {
        super(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, ModHelper.makeCardImagePath(id), cost,
                CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION, type,
                color, rarity, target);
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

    public void setNums(int... nums) {
        if (nums.length > 0) this.setDamage(nums[0]);
        if (nums.length > 1) this.setBlock(nums[1]);
        if (nums.length > 2) this.setMagic(nums[2]);
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

    protected void addPowerToEnemy(AbstractMonster target, AbstractPower power) {
        addToBot(new ApplyPowerAction(target, AbstractDungeon.player, power));
    }

    protected void addDrawCardAction(){
        addToBot(new DrawCardAction(this.magicNumber));
    }

    protected void upgradeDescription() {
        this.rawDescription = CardCrawlGame.languagePack.getCardStrings(this.cardID).UPGRADE_DESCRIPTION;
        this.initializeDescription();
    }
}
