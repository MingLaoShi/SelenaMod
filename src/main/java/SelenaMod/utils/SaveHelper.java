package SelenaMod.utils;

import basemod.BaseMod;
import basemod.abstracts.CustomSavable;
import com.google.gson.JsonElement;

public class SaveHelper implements CustomSavable<SaveHelper.SaveValue> {

    public static class SaveValue {
    }

    public SaveValue values = new SaveValue();

    public SaveHelper() {
        BaseMod.addSaveField(ModHelper.MOD_ID,this);
    }


    @Override
    public SaveValue onSave() {
        return null;
    }

    @Override
    public void onLoad(SaveValue saveValue) {

    }

    @Override
    public JsonElement onSaveRaw() {
        return ModHelper.gson.toJsonTree(this.values);
    }

    @Override
    public void onLoadRaw(JsonElement value) {
        if (value != null) {
            this.values = ModHelper.gson.fromJson(value, SaveValue.class);
        }
    }
}
