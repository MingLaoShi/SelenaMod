package SelenaMod.relics;

import SelenaMod.utils.ModHelper;
import SelenaMod.utils.SelenaEnums;
import SelenaMod.utils.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class FriendABrush extends CustomRelic {
    public static final String ID = ModHelper.makeID(FriendABrush.class.getSimpleName());
    private static final Texture TEXTURE = TextureLoader.getTexture(ModHelper.makeRelicImagePath(ID));
    private static final Texture OUTLINE = TextureLoader.getTexture(ModHelper.makeRelicOutlinePath(ID));
    private static final int DAMAGE_BONUS = 3;

    public FriendABrush() {
        super(ID, TEXTURE, OUTLINE, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public float atDamageModify(float damage, AbstractCard c) {
        if (c.hasTag(SelenaEnums.HAS_TONE_POWER)) {
            return damage + DAMAGE_BONUS;
        }
        return damage;
    }

}
