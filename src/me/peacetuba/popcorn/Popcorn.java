package me.peacetuba.popcorn;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import me.peacetuba.popcorn.commands.Setup;
import me.peacetuba.popcorn.events.cancelFallDamage;
import me.peacetuba.popcorn.events.gui.GUIProcess;

public class Popcorn extends JavaPlugin{ /*

    ------------------------------------------------------
    CREDITS TO TAPPLE.WORLD FOR GIVING ME THIS IDEA
	------------------------------------------------------
	*/
	
	
	public long Interval;	
	public boolean isFarLaunch = false;
	public boolean isCloseLaunch = false;
	public boolean isMediumLaunch = false;
	public boolean takeFallDamage = true;
	public boolean firstTime = true;
	
	
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(new cancelFallDamage(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new GUIProcess(this), this);
		getCommand("runsetup").setExecutor(new Setup(this));
	}
	
	
	@SuppressWarnings("deprecation")
	public void alwaysRun() {
		Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
			public void run() {
				for(Player se : Bukkit.getOnlinePlayers()) {
					if(isCloseLaunch) {
						double min = -1;
						double max = 1;
						Random r = new Random();
						
						double miny = -0.25;
						double maxy = 0.25;
						
						double randomx = (r.nextDouble() * (max - min)) + min;
						double randomy = (r.nextDouble() * (maxy - miny)) + miny;
						double randomz = (r.nextDouble() * (max - min)) + min;
						Vector v = new Vector(randomx, randomy, randomz);
						se.setVelocity(v);
						
					} else if(isMediumLaunch) {
						double min = -2;
						double max = 2;
						Random r = new Random();
						
						double miny = -0.5;
						double maxy = 0.5;
						
						double randomx = (r.nextDouble() * (max - min)) + min;
						double randomy = (r.nextDouble() * (maxy - miny)) + miny;
						double randomz = (r.nextDouble() * (max - min)) + min;
						Vector v = new Vector(randomx, randomy, randomz);
						se.setVelocity(v);
						
					} else if(isFarLaunch) {
						double min = -4;
						double max = 4;
						Random r = new Random();
						
						double miny = -1;
						double maxy = 1;
						
						double randomx = (r.nextDouble() * (max - min)) + min;
						double randomy = (r.nextDouble() * (maxy - miny)) + miny;
						double randomz = (r.nextDouble() * (max - min)) + min;
						Vector v = new Vector(randomx, randomy, randomz);
						se.setVelocity(v);
					}
					
				}
			}
		}, 0, Interval);
	}
}

