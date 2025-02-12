package com.feove.iboren.client.render;

import com.feove.iboren.entity.custom.RenZombie;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraft.util.ResourceLocation;

public class RenZombieRenderer extends MobRenderer<RenZombie, ZombieModel<RenZombie>> {

    private static final ResourceLocation ZOMBIE_TEXTURE = new ResourceLocation("iboren:textures/entity/ren_zombie/ren_zombie.png");

    public RenZombieRenderer(EntityRendererManager entityRendererManager) {
        super(entityRendererManager, new ZombieModel<>(0.0F, false), 0.5F);
        this.addLayer(new BipedArmorLayer<>(this, new ZombieModel<>(0.5F, true), new ZombieModel<>(1.0F, true))); // Armor Rendering
        this.addLayer(new HeldItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(RenZombie renZombie) {
        return ZOMBIE_TEXTURE;
    }
}
