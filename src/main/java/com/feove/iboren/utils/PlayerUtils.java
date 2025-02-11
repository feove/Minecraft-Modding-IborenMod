package com.feove.iboren.utils;


import com.feove.iboren.IborenMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerUtils extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerUtils.class);

    private static PlayerUtils instance;

    public PlayerEntity player = null;


    public static void sendMessage(PlayerEntity player, String message) {

        player.sendMessage(new StringTextComponent(message), player.getUUID());

        LOGGER.info(message);
    }
}
