package com.tuinboon.tuinscosmetics.mixin;

import com.tuinboon.tuinscosmetics.Addon;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayerEntity.class)
public class ChangeCape {
    @Inject(method = "getCapeTexture", at = @At("HEAD"), cancellable = true)
    private void onGetCapeTexture(CallbackInfoReturnable<Identifier> info) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        String name = player.getEntityName().toLowerCase();

        if (Addon.capes.contains(name)) {
            Identifier capeid = new Identifier(name);
            info.setReturnValue(capeid);
        }
    }
}



