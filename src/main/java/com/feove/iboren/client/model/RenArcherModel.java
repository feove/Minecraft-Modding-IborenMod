package com.feove.iboren.client.model;

import com.feove.iboren.entity.custom.RenArcher;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RenArcherModel extends AnimatedGeoModel<RenArcher> {
    @Override
    public ResourceLocation getModelLocation(RenArcher renArcher) {
        return new ResourceLocation("iboren", "geo/ren_archer.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(RenArcher renArcher) {
        return new ResourceLocation("iboren", "textures/entity/ren_archer/ren_archer.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(RenArcher renArcher) {
        return new ResourceLocation("iboren", "animations/ren_archer.animation.json");
    }


}
