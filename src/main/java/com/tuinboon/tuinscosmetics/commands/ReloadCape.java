package com.tuinboon.tuinscosmetics.commands;

import com.tuinboon.tuinscosmetics.Addon;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import meteordevelopment.meteorclient.commands.Command;
import net.minecraft.command.CommandSource;

import static com.mojang.brigadier.Command.SINGLE_SUCCESS;

public class ReloadCape extends Command {
    public ReloadCape() {
        super("reloadcape", "Reloads everyone's cape");
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSource> builder) {
        builder.executes(context -> {
            Addon.initializeCape();
            info("Capes reloaded");
            return SINGLE_SUCCESS;
        });
    }

}
