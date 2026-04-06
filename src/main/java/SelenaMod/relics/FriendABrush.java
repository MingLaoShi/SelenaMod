package SelenaMod.relics;

import SelenaMod.utils.ModHelper;
import SelenaMod.utils.SelenaEnums;
import SelenaMod.utils.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;

public class FriendABrush extends CustomRelic {
    public static final String ID = ModHelper.makeID(FriendABrush.class.getSimpleName());
    private static final Texture TEXTURE = TextureLoader.getTexture(ModHelper.makeRelicImagePath(ID));
    private static final Texture OUTLINE = TextureLoader.getTexture(ModHelper.makeRelicOutlinePath(ID));
    private static final int DAMAGE_BONUS = 3;

    private static final Texture BIG_IMAGE = TextureLoader.getTexture(ModHelper.makeImgPath("relic", "FriendABrush_b"));

    private float fadeAlpha = 0.0F;
    private boolean isHovered = false;

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


    @Override
    public void renderInTopPanel(SpriteBatch sb) {
        super.renderInTopPanel(sb);
        if (this.hb.hovered) {
            if (!this.isHovered) {
                // 重新hover时重置透明度
                this.fadeAlpha = 0.0F;
            }
            this.isHovered = true;
            renderBigImage(sb);
        } else {
            this.isHovered = false;
        }
    }

    private void renderBigImage(SpriteBatch sb) {
        // 更新透明度：hover时渐显，非hover时渐隐
        if (this.isHovered) {
            this.fadeAlpha += Gdx.graphics.getDeltaTime() * 5.0F;
            if (this.fadeAlpha > 1.0F) {
                this.fadeAlpha = 1.0F;
            }
        } else {
            this.fadeAlpha -= Gdx.graphics.getDeltaTime() * 5.0F;
            if (this.fadeAlpha < 0.0F) {
                this.fadeAlpha = 0.0F;
            }
        }

        // 计算目标尺寸：宽度为屏幕的50%，高度等比缩放
        float targetWidth = Settings.WIDTH * 0.5F;
        float aspectRatio = (float) BIG_IMAGE.getHeight() / BIG_IMAGE.getWidth();
        float targetHeight = targetWidth * aspectRatio;

        // 计算左上角坐标，使图案中心在hb.cX, hb.cY
        float drawX = this.hb.cX - targetWidth / 2.0F;
        float drawY = this.hb.cY - targetHeight / 2.0F;

        // 确保图案不超出屏幕左边界
        if (drawX < 0) {
            drawX = 0;
        }

        // 确保图案不超出屏幕右边界
        if (drawX + targetWidth > Settings.WIDTH) {
            drawX = Settings.WIDTH - targetWidth;
        }

        // 确保图案不超出屏幕下边界
        if (drawY < 0) {
            drawY = 0;
        }

        // 确保图案不超出屏幕上边界
        if (drawY + targetHeight > Settings.HEIGHT) {
            drawY = Settings.HEIGHT - targetHeight;
        }

        // 向下偏移200px * scale
        drawY -= 170.0F * Settings.scale;

        // 再次检查下边界，确保偏移后不超出屏幕
        if (drawY < 0) {
            drawY = 0;
        }

        // 设置颜色并应用透明度
        Color renderColor = Color.WHITE.cpy();
        renderColor.a = this.fadeAlpha;
        sb.setColor(renderColor);
        sb.draw(BIG_IMAGE, drawX, drawY, targetWidth, targetHeight);
    }
}
