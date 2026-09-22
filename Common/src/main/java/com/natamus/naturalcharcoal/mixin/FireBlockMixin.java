package com.natamus.naturalcharcoal.mixin;

import com.natamus.collective.functions.CompareBlockFunctions;
import com.natamus.naturalcharcoal.config.ConfigHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = FireBlock.class, priority = 1001)
public class FireBlockMixin {
	@Redirect(method = "checkBurnOut", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;removeBlock(Lnet/minecraft/core/BlockPos;Z)Z"))
	private boolean checkBurnOut_dropCharcoal(Level level, BlockPos pos, boolean isMoving) {
		BlockState blockState = level.getBlockState(pos);
		Block block = blockState.getBlock();

		if (CompareBlockFunctions.isTreeLog(block) && Math.random() <= ConfigHandler.burnedLogBecomesCharcoalChance) {
			level.addFreshEntity(new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(Items.CHARCOAL, ConfigHandler.charcoalDropAmount)));
		}

		return level.removeBlock(pos, isMoving);
	}
}
