package br.com.sussurronoite;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.Random;

public class SinoDaRuinaItem extends Item {
    private static final Random RANDOM = new Random();

    public SinoDaRuinaItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide) {
            level.playSound(null, player.blockPosition(), SoundEvents.SCULK_SHRIEKER_SHRIEK,
                    SoundSource.HOSTILE, 1.4F, 0.65F);
            level.playSound(null, player.blockPosition(), SoundEvents.WARDEN_HEARTBEAT,
                    SoundSource.HOSTILE, 0.8F, 0.7F);

            // The bell calls danger only in darkness; daylight still produces an ominous warning.
            boolean dark = level.getMaxLocalRawBrightness(player.blockPosition()) <= 7;
            int count = dark ? 3 : 1;
            for (int i = 0; i < count; i++) {
                BlockPos spawn = findSpawn(level, player.blockPosition());
                if (spawn != null) {
                    EntityType.ZOMBIE.spawn((net.minecraft.server.level.ServerLevel) level,
                            null, null, spawn, MobSpawnType.MOB_SUMMONED, true, false);
                }
            }
            player.getCooldowns().addCooldown(this, 20 * 45);
            stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    private BlockPos findSpawn(Level level, BlockPos origin) {
        for (int attempt = 0; attempt < 12; attempt++) {
            int dx = RANDOM.nextInt(13) - 6;
            int dz = RANDOM.nextInt(13) - 6;
            BlockPos candidate = origin.offset(dx, 0, dz);
            candidate = level.getHeightmapPos(net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, candidate);
            if (level.getMaxLocalRawBrightness(candidate) <= 7
                    && level.getBlockState(candidate).isAir()
                    && level.getBlockState(candidate.above()).isAir()
                    && level.getWorldBorder().isWithinBounds(candidate)) {
                return candidate;
            }
        }
        return null;
    }
}
