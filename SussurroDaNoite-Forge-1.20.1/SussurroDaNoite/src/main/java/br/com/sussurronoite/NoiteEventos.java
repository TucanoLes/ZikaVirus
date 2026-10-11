package br.com.sussurronoite;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

public class NoiteEventos {
    private static final Random RANDOM = new Random();

    public static void register() {
        MinecraftForge.EVENT_BUS.register(NoiteEventos.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.side != net.minecraftforge.eventbus.api.Event.Result.DEFAULT) {
            // Side is checked below through the level; don't run twice per tick.
        }
        Player player = event.player;
        Level level = player.level();
        if (level.isClientSide || player.isSpectator() || player.isCreative()) return;
        if (player.tickCount % 240 != 0 || !level.isNight()) return;

        BlockPos pos = player.blockPosition();
        if (level.getMaxLocalRawBrightness(pos) > 5) return;

        // Rare distant whispers, deliberately harmless: atmosphere rather than unfair damage.
        if (RANDOM.nextInt(4) == 0) {
            level.playSound(null, pos, SoundEvents.ALLAY_AMBIENT_WITHOUT_ITEM,
                    SoundSource.AMBIENT, 0.25F, 0.55F + RANDOM.nextFloat() * 0.35F);
        }
    }
}
