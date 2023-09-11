package com.tuinboon.tuinscosmetics;

import com.tuinboon.tuinscosmetics.commands.ReloadCape;
import com.tuinboon.tuinscosmetics.modules.ModuleExample;
import com.mojang.logging.LogUtils;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.commands.Commands;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import meteordevelopment.meteorclient.utils.network.Http;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static meteordevelopment.meteorclient.MeteorClient.mc;

public class Addon extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();
    public static final Category CATEGORY = new Category("Test");

    public static List<String> capes = new ArrayList<>();

    public static void initializeCape() {
        Stream<String> lines = Http.get("https://raw.githubusercontent.com/tuinboonDev/tuins-appstore/main/capes.txt").sendLines();
        if (lines == null) {
            lines = "https://raw.githubusercontent.com/tuinboondev/tuins-appstore/main/boon.png Tuinboon".lines();
            LOG.debug(lines.toString());
        }
        lines.forEach(l -> {
            if (!capes.contains(l.split(" ")[1])) {
                capes.add(l.split(" ")[1].toLowerCase());
                InputStream in = Http.get(l.split(" ")[0]).sendInputStream();
                NativeImage img = null;
                try {
                    img = NativeImage.read(in);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Identifier capeid = new Identifier(l.split(" ")[1].toLowerCase());
                mc.getTextureManager().registerTexture(capeid, new NativeImageBackedTexture(img));
            }
        });
    }
    @Override
    public void onInitialize() {
        LOG.info("Initializing Meteor Addon Template");

        initializeCape();

        // Modules
        Modules.get().add(new ModuleExample());

        // Commands
        Commands.add(new ReloadCape());

    }

        @Override
        public void onRegisterCategories() {
            Modules.registerCategory(CATEGORY);
        }

    @Override
    public String getPackage() {
        return "com.tuinboon.tuinscosmetics";
    }
}
