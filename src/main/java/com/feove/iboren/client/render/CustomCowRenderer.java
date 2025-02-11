package com.feove.iboren.client.render;

import com.feove.iboren.entity.custom.CustomCow;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.util.ResourceLocation;
import org.jline.builtins.Source;

@MethodsReturnNonnullByDefault
public class CustomCowRenderer extends MobRenderer<CustomCow, CowModel<CustomCow>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("iboren:textures/entity/custom_cow/custom_cow.png");

    public CustomCowRenderer(EntityRendererManager renderManager) {
        super(renderManager, new CowModel<>(), 0.7f);
        System.out.println("CustomCowRenderer initialized");
    }


    @Override
    public ResourceLocation getTextureLocation(CustomCow entity) {

        return TEXTURE;
    }
}


