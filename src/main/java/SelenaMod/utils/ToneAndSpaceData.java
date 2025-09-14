package SelenaMod.utils;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class ToneAndSpaceData {
    public ToneAndSpaceDataManager.ToneAndSpaceType ID;
    public String name;
    public String description;
    public int amount;
    public AbstractCard.CardTarget target;

    public AbstractCard.CardTarget getTarget() {
        return target;
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

    public boolean isTone;

    public boolean isTone() {
        return isTone;
    }

    public void setTone(boolean tone) {
        isTone = tone;
    }


    public ToneAndSpaceDataManager.ToneAndSpaceType getID() {
        return ID;
    }

    public void setID(ToneAndSpaceDataManager.ToneAndSpaceType ID) {
        this.ID = ID;
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
