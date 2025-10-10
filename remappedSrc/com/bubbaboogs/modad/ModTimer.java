package com.bubbaboogs.modad;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModTimer implements ServerTickEvents.EndTick {
    public static final ModTimer INSTANCE = new ModTimer();

    private final Map<Long, List<Runnable>> scheduledTasks = new HashMap<>();
    private long currentTick = 0;

    public void schedule(long delayTicks, Runnable task) {
        long runAt = currentTick + delayTicks;
        scheduledTasks.computeIfAbsent(runAt, k -> new ArrayList<>()).add(task);
    }

    @Override
    public void onEndTick(MinecraftServer server) {
        List<Runnable> toRun = scheduledTasks.remove(currentTick);
        if (toRun != null) {
            for (Runnable task : toRun) {
                task.run();
            }
        }
        currentTick++;
    }

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(INSTANCE);
    }
}
