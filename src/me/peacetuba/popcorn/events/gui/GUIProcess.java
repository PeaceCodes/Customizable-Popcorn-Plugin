package me.peacetuba.popcorn.events.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.peacetuba.popcorn.Popcorn;
import me.peacetuba.popcorn.cc;
import me.peacetuba.popcorn.commands.Setup;

public class GUIProcess implements Listener{

	private final Popcorn main;
	
	public GUIProcess(Popcorn main) {
		this.main = main;
	}
	
	@EventHandler
	public void on(InventoryClickEvent e) {
		Inventory inv = e.getClickedInventory();
		ItemStack currentItem = e.getCurrentItem();
		Player p = (Player) e.getWhoClicked();
		
		
		if(currentItem == null) {
			return;
		}
		if(inv.getName().equals(cc.Chat("&bTake fall damage"))) {
			if(currentItem.getItemMeta().getDisplayName().equals(cc.Chat("&a&lYes"))) {
				main.takeFallDamage = true;
			} else if(currentItem.getItemMeta().getDisplayName().equals(cc.Chat("&c&lNo"))) {
				main.takeFallDamage = false;
			} 
			p.closeInventory();
			e.setCancelled(true);
			Setup s = new Setup(main);
			s.summary(p);
		} else if(inv.getTitle().equals(cc.Chat("&bSet Interval Time"))) {
			
			if(currentItem.getItemMeta().getDisplayName().equals(cc.Chat("&c&l20 ticks"))) {
				main.Interval = 20L;
			} else if(currentItem.getItemMeta().getDisplayName().equals(cc.Chat("&e&l40 ticks"))) {
				main.Interval = 40L;
			} else if(currentItem.getItemMeta().getDisplayName().equals(cc.Chat("&a&l60 ticks"))) {
				main.Interval = 60L;
			}
			
			p.closeInventory();
			e.setCancelled(true);
			Setup s = new Setup(main);
			
			s.setLaunchDistanceGUI(p);
		} else if(inv.getName().equals(cc.Chat("&bSet Launch Distance"))) {
			if(currentItem.getItemMeta().getDisplayName().equals(cc.Chat("&a&lLaunch a little distance"))) {
				main.isCloseLaunch = true;
				main.isMediumLaunch = false;
				main.isFarLaunch = false;
			} else if(currentItem.getItemMeta().getDisplayName().equals(cc.Chat("&e&lLaunch a medium distance"))) {
				main.isMediumLaunch = true;
				main.isCloseLaunch = false;
				main.isFarLaunch = false;
			} else if(currentItem.getItemMeta().getDisplayName().equals(cc.Chat("&c&lLaunch a really far distance"))) {
				main.isFarLaunch = true;
				main.isMediumLaunch = false;
				main.isCloseLaunch = false;
			}
			p.closeInventory();
			e.setCancelled(true);
			Setup s = new Setup(main);
			s.takeFallDamageGUI(p);
		} else if(inv.getName().equals(cc.Chat("&aSummary"))) {
			if(currentItem.getItemMeta() == null) return;
			if(currentItem.getItemMeta().getDisplayName().equals(cc.Chat("&aDone"))) {
				p.closeInventory();
				e.setCancelled(true);
				if(main.firstTime) {
					main.alwaysRun();
					main.firstTime = false;
				}
				System.out.println("Interval: " + main.Interval);
				System.out.println("Green: " + main.isCloseLaunch);
				System.out.println("Yellow: " + main.isMediumLaunch);
				System.out.println("Red: " + main.isFarLaunch);
				System.out.println("Fall Damage: " + main.takeFallDamage);
			} else if(currentItem.getItemMeta().getDisplayName().equals(cc.Chat("&cCancel"))){
				e.setCancelled(true);
				p.closeInventory();
			} else {
				e.setCancelled(true);
			}
		} 
	}
}
