package com.tuinboon.tuinscosmetics.modules;

import com.tuinboon.tuinscosmetics.Addon;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.utils.player.ChatUtils;


public class ModuleExample extends Module {
    public ModuleExample() {
        super(Addon.CATEGORY, "Tuins Cosmetics", "Enables Tuins Cosmetics");
    }

    @Override
    public void onActivate() {
        ChatUtils.info("Activated cosmetics");
    }
}
