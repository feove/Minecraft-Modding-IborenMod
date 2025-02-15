package com.feove.iboren.client.render;

import com.feove.iboren.client.model.RenArcherModel;
import com.feove.iboren.entity.custom.RenArcher;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.client.renderer.entity.model.SkeletonModel;
import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenArcherRenderer extends GeoEntityRenderer<RenArcher> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("iboren:textures/entity/ren_archer/ren_archer.png");

    public RenArcherRenderer(EntityRendererManager renderManager) {
        super(renderManager, new RenArcherModel()); // Ensure RenArcherModel exists
        this.shadowRadius = 0.5F;
    }


    @Override
    public ResourceLocation getTextureLocation(RenArcher renArcher) {
        return TEXTURE;

    }




}
