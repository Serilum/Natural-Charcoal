package com.natamus.naturalcharcoal.mixin;

import com.natamus.collective.functions.CompareBlockFunctions;
import com.natamus.naturalcharcoal.config.ConfigHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = FireBlock.class, priority = 1001)
public class FireBlockMixin {
	@Inject(method = "checkBurnOut(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;ILnet/minecraft/util/RandomSource;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;removeBlock(Lnet/minecraft/core/BlockPos;Z)Z"))
	private void checkBurnOut(Level level, BlockPos pos, int chance, RandomSource random, int age, CallbackInfo ci) {
		BlockState blockState = level.getBlockState(pos);
		Block block = blockState.getBlock();

		if (!CompareBlockFunctions.isTreeLog(block)) {
			return;
		}

		if (Math.random() > ConfigHandler.burnedLogBecomesCharcoalChance) {
			return;
		}

		level.addFreshEntity(new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(Items.CHARCOAL, ConfigHandler.charcoalDropAmount)));
	}
}
