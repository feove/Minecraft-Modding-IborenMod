package com.feove.iboren.client.render;

import com.feove.iboren.IborenMod;
import com.feove.iboren.entity.custom.RenArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.ArrowLayer;
import net.minecraft.util.ResourceLocation;

public class RenArrowRenderer extends ArrowRenderer<RenArrow> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("iboren", "textures/entity/projectiles/ren_arrow.png");

    public RenArrowRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public ResourceLocation getTextureLocation(RenArrow entity) {
        return TEXTURE;
    }
}

