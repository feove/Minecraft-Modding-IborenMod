package com.feove.iboren.commands;

import com.feove.iboren.utils.PlayerUtils;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.text.StringTextComponent;

public class IborenCommands {

    public IborenCommands(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("iboren")
                .requires(commandSource -> commandSource.hasPermission(2))
                .executes(this::iboren));
    }

    private int iboren(CommandContext<CommandSource> context) throws CommandSyntaxException {
        CommandSource source = context.getSource();
        if (source.getEntity() instanceof ServerPlayerEntity) {
            ServerPlayerEntity playerEntity = (ServerPlayerEntity) source.getEntity();

            // Create a written book item
            ItemStack book = new ItemStack(Items.WRITTEN_BOOK);
            CompoundNBT bookData = new CompoundNBT();
            ListNBT pages = new ListNBT();

            // Set book title and author
            bookData.putString("title", "Iboren Guide");
            bookData.putString("author", "Feovee");


            pages.add(StringNBT.valueOf(
                    "{\"text\":\"§lWelcome to the Iboren Mod!§r\\n\\n" +
                            "This guide provides crucial information to enhance your adventure.\\n\\n" +
                            " \"}"
            ));

            pages.add(StringNBT.valueOf(
                    "{\"text\":\"The §6Iboren Temple§r is found only in the Iboren Biome.\\n\\n" +
                            "- Ren Skeleton\\n" +
                            "- Ren Magician\\n" +
                            "- Ren Zombies\\n\\n" +
                            "Defeat them to unlock the Iboren Boss!\\n" +
                            "- Reward: §bIboren Scepter§r\\n\\n" +
                            "\"}"
            ));

            pages.add(StringNBT.valueOf(
                    "{\"text\":\"-> Support the Creator! \\n\\n" +
                            "Enjoy this mod? Consider:\\n" +
                            "- Leaving feedback\\n" +
                            "- Sharing with friends\\n" +
                            "- Supporting Feovee!\\n\\n" +
                            "- Happy Adventuring! \"}"
            ));


            bookData.put("pages", pages);
            book.setTag(bookData);

            boolean success = playerEntity.addItem(book);

            if (success) {
                PlayerUtils.sendMessage(playerEntity, "You received the Iboren Guide!");
            } else {
                PlayerUtils.sendMessage(playerEntity, "⚠️ Inventory full! Could not receive the book.");
            }
        }
        return 1;
    }

}
