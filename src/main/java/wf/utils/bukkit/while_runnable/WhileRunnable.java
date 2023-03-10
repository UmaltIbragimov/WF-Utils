package wf.utils.bukkit.while_runnable;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;


public class WhileRunnable {

    private BukkitTask task;

    public WhileRunnable(WRunnable runnable, Plugin plugin, int each, long period, long delay){
        task = new BukkitRunnable() {
            int eachI = 0;
            @Override
            public void run() {
                eachI++;
                if(eachI - 1 > each) cancel();
                runnable.run(eachI - 1);
            }
        }.runTaskTimerAsynchronously(plugin, delay, period);

    }

    public void stop(){
        if(task != null && !task.isCancelled()) task.cancel();
    }

}
