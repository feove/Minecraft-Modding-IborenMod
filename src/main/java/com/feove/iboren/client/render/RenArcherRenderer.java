package com.feove.iboren.client.render;

import com.feove.iboren.client.model.RenArcherModel;
import com.feove.iboren.entity.custom.RenArcher;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;

public class RenArcherRenderer extends GeoEntityRenderer<RenArcher> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("iboren", "textures/entity/ren_archer/ren_archer.png");

    public RenArcherRenderer(EntityRendererManager renderManager) {
        super(renderManager, new RenArcherModel());

        this.addLayer(new GeoLayerRenderer<RenArcher>(this) {
            @Override
            public void render(MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int i, RenArcher renArcher, float v, float v1, float v2, float v3, float v4, float v5) {

            }
        });
        this.shadowRadius = 0.5F;
    }

    @Override
    public ResourceLocation getTextureLocation(RenArcher renArcher) {
        return TEXTURE;
    }
}
