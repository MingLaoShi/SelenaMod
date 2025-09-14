package SelenaMod.utils;


import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class ToneAndSpaceData {
    public ToneAndSpaceDataManager.ToneAndSpaceType ID;
    public String name;
    public String description;
    public int amount;

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
        return String.format(this.description,this.amount);
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
