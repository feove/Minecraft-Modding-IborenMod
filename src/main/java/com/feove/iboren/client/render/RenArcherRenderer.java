package com.feove.iboren.client.render;

import com.feove.iboren.client.model.RenArcherModel;
import com.feove.iboren.entity.custom.RenArcher;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.SkeletonModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.layer.AbstractLayerGeo;

public class RenArcherRenderer extends GeoEntityRenderer<RenArcher> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("iboren", "textures/entity/ren_archer/ren_archer.png");

    public RenArcherRenderer(EntityRendererManager renderManager) {
        super(renderManager, new RenArcherModel());

        this.addLayer(new GeoLayerRenderer<RenArcher>(this) {
            @Override
            public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, RenArcher renArcher,
                               float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks,
                               float netHeadYaw, float headPitch) {

                ItemStack bow = renArcher.getItemBySlot(EquipmentSlotType.MAINHAND);
                if (!bow.isEmpty()) {

                    matrixStack.pushPose();

                    matrixStack.translate(0.0D, 0.6D, -0.8D);


                    Quaternion rotation = new Quaternion(0.0F, 0F, 0.8F, 0.45F); // 90-degree rotation around Y-axis
                    matrixStack.mulPose(rotation);


                    Minecraft.getInstance().getItemRenderer().renderStatic(
                            bow,
                            ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND,
                            packedLight,
                            OverlayTexture.NO_OVERLAY,
                            matrixStack,
                            buffer
                    );
                    matrixStack.popPose();
                }
            }
        });

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
