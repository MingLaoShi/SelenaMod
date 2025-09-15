package SelenaMod.utils;


import com.megacrit.cardcrawl.cards.AbstractCard;

public class ToneAndSpaceData {
    public String name;
    public String description;
    public int amount = 0;
    public AbstractCard.CardTarget target;
    public int amount2 = 0;
    public boolean isTone;

    public int getAmount2() {
        return amount2;
    }

    public void setAmount2(int amount2) {
        this.amount2 = amount2;
    }

    public AbstractCard.CardTarget getTarget() {
        return target;
    }

    public void setTarget(AbstractCard.CardTarget target) {
        this.target = target;
    }

    public void setTarget(String target) {
        switch (target) {
            case "SELF":
                this.target = AbstractCard.CardTarget.SELF;
                break;
            case "ENEMY":
                this.target = AbstractCard.CardTarget.ENEMY;
                break;
            case "ALL_ENEMY":
                this.target = AbstractCard.CardTarget.ALL_ENEMY;
                break;
            case "ALL":
                this.target = AbstractCard.CardTarget.ALL;
                break;
            case "SELF_AND_ENEMY":
                this.target = AbstractCard.CardTarget.SELF_AND_ENEMY;
                break;
            default:
                this.target = AbstractCard.CardTarget.NONE;
                break;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTone() {
        return isTone;
    }

    public void setTone(boolean tone) {
        isTone = tone;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
