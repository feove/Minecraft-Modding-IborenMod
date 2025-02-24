package com.feove.iboren.item.custom;

import com.feove.iboren.utils.PlayerUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;
import org.lwjgl.system.CallbackI;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class IborenEscape extends Item implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);

    public IborenEscape(Properties properties) {
        super(properties);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 20, this::predicate));
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {

        AnimationController<P> controller = event.getController();

        // Play Idle Animation
        controller.setAnimation(new AnimationBuilder().addAnimation("animation.iboren_escape.idle", true));


        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

        if (!world.isClientSide) {

            PlayerUtils.sendMessage(player, "Iboren Escape used");
        }

        return super.use(world, player, hand);
    }
}
