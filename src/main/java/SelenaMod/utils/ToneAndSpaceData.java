package SelenaMod.utils;


import com.megacrit.cardcrawl.cards.AbstractCard;

public class ToneAndSpaceData {
    public enum Type {
        TONE,
        SPACE,
        OVERRIDE
    }

    public String name;
    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String description;
    public int amount = 0;
    public AbstractCard.CardTarget target;
    public int amount2 = 0;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type type=Type.SPACE;

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
