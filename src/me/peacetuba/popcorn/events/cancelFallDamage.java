package me.peacetuba.popcorn.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import me.peacetuba.popcorn.Popcorn;

public class cancelFallDamage implements Listener{

	private Popcorn p;
	
	public cancelFallDamage(Popcorn p) {
		this.p = p;
	}
	
	@EventHandler
	public void cancel(EntityDamageEvent e) {
		
		if(!(e.getEntity() instanceof Player)) {
			return;
		}
		
		if(p.takeFallDamage) {
			return;
		}
		
		if(!(e.getCause().equals(DamageCause.FALL))) {
			return;
		}
		
		e.setCancelled(true);
	}
	
}
