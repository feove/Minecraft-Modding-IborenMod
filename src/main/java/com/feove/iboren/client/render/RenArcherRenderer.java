package com.feove.iboren.client.render;

import com.feove.iboren.client.model.RenArcherModel;
import com.feove.iboren.entity.custom.RenArcher;
import com.feove.iboren.item.ModItems;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;

public class RenArcherRenderer extends GeoEntityRenderer<RenArcher> {


    public RenArcherRenderer(EntityRendererManager renderManager) {
        super(renderManager, new RenArcherModel());

        this.addLayer(new GeoLayerRenderer<RenArcher>(this) {
            @Override
            public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, RenArcher renArcher,
                               float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks,
                               float netHeadYaw, float headPitch) {

                ItemStack bow = new ItemStack(ModItems.REN_BOW.get());
                if (!bow.isEmpty()) {
                    matrixStack.pushPose();

                    if (renArcher.isAggressive()) {
                        setBowOnLegs(matrixStack);

                    } else {

                            setBowOnBack(matrixStack);

                    }

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

        this.shadowRadius = 0.5F;
    }


    private static int BowDisplayDelay = 180;

    public static void setBowOnLegs(MatrixStack matrixStack) {

        if (BowDisplayDelay <= 0) {

            matrixStack.translate(-0.3D, 0.36, -1.4D);
            Quaternion rotation = new Quaternion(0.0F, 0.2F, 90.0F, true);
            matrixStack.mulPose(rotation);

        }else{
            BowDisplayDelay--;
        }
    }

    public static void setBowOnBack(MatrixStack matrixStack) {

        matrixStack.translate(-0.1D, 1.1D, 0.2D);
        matrixStack.scale(0.75F, 0.75F, 0.75F);
        Quaternion rotation = new Quaternion(90F, 50F, 80F, true);
        matrixStack.mulPose(rotation);

        BowDisplayDelay = 180;
    }


}
