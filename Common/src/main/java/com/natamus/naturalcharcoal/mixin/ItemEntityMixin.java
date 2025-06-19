package com.natamus.naturalcharcoal.mixin;

import com.natamus.naturalcharcoal.config.ConfigHandler;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemEntity.class, priority = 1001)
public abstract class ItemEntityMixin {
	@Shadow public abstract ItemStack getItem();

	@Inject(method = "fireImmune()Z", at = @At(value = "HEAD"), cancellable = true)
	public void fireImmune(CallbackInfoReturnable<Boolean> cir) {
		if (ConfigHandler.charcoalIsImmuneToFire) {
			if (this.getItem().getItem().equals(Items.CHARCOAL)) {
				cir.setReturnValue(true);
			}
		}
	}
}
