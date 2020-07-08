package me.peacetuba.popcorn.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.peacetuba.popcorn.Popcorn;
import me.peacetuba.popcorn.cc;

public class Setup implements CommandExecutor{

	private Popcorn main;
	
	public Setup(Popcorn main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(cc.Chat("&cOnly players are allowed to perform this command!"));
			return false;
		}
		
		Player p = (Player) sender;
		
		intervalTimeGUI(p);
		
		return false;
	}

	@SuppressWarnings("deprecation")
	public void summary(Player p) {
		Inventory Summary = Bukkit.createInventory(p, 27, cc.Chat("&aSummary"));
		
		ItemStack intervalTimeClock = new ItemStack(Material.WATCH, 1);
		ItemStack featherLaunchDistance = new ItemStack(Material.FEATHER, 1);
				
		
		ItemStack takeFallDamageGW = new ItemStack(Material.WOOL, 1, DyeColor.GREEN.getData());
		ItemStack dontTakeFallDamageRW = new ItemStack(Material.WOOL, 1, DyeColor.RED.getData());
		
		ItemStack Done = new ItemStack(Material.EMERALD_BLOCK, 1);
		ItemStack Cancel = new ItemStack(Material.COAL_BLOCK, 1);
		
		ItemMeta itc = intervalTimeClock.getItemMeta();
		ItemMeta fld = featherLaunchDistance.getItemMeta();
		ItemMeta tfdgw = takeFallDamageGW.getItemMeta();
		ItemMeta dtfdrw = dontTakeFallDamageRW.getItemMeta();
		ItemMeta d = Done.getItemMeta();
		ItemMeta c = Cancel.getItemMeta();
		
		if(main.isCloseLaunch) {
			fld.setDisplayName(cc.Chat("&a&lLaunch a little distance"));
		} else if(main.isMediumLaunch) {
			fld.setDisplayName(cc.Chat("&e&lLaunch a medium distance"));
		} else if(main.isFarLaunch) {
			fld.setDisplayName(cc.Chat("&c&lLaunch a really far distance"));
		}
		
		long interval = (main.Interval) / 20;
		
		String i = Long.toString(interval);
		
		itc.setDisplayName(cc.Chat("&aInterval: &f" + i + " &aseconds"));
		
		tfdgw.setDisplayName(cc.Chat("&aTake Fall Damage"));
		dtfdrw.setDisplayName(cc.Chat("&cDo not Take Fall Damage"));
		d.setDisplayName(cc.Chat("&aDone"));
		c.setDisplayName(cc.Chat("&cCancel"));
		
		intervalTimeClock.setItemMeta(itc);
		featherLaunchDistance.setItemMeta(fld);
		takeFallDamageGW.setItemMeta(tfdgw);
		dontTakeFallDamageRW.setItemMeta(dtfdrw);
		Done.setItemMeta(d);
		Cancel.setItemMeta(c);
		
		Summary.setItem(0, intervalTimeClock);
		Summary.setItem(9, featherLaunchDistance);
		if(main.takeFallDamage) {
			Summary.setItem(18, takeFallDamageGW);
		} else {
			Summary.setItem(18, dontTakeFallDamageRW);
		}
		Summary.setItem(7, Done);
		Summary.setItem(25, Cancel);
		p.openInventory(Summary);
	}
	@SuppressWarnings("deprecation")
	public void takeFallDamageGUI(Player p) {
		Inventory IntervalTime = Bukkit.createInventory(p, 9, cc.Chat("&bTake fall damage"));
		
		ItemStack greenwool = new ItemStack(Material.WOOL, 1, DyeColor.GREEN.getData());
		ItemStack redwool = new ItemStack(Material.WOOL, 1, DyeColor.RED.getData());
		
		ItemMeta gwm = greenwool.getItemMeta();
		ItemMeta rwm = redwool.getItemMeta();
		
		gwm.setDisplayName(cc.Chat("&a&lYes"));
		rwm.setDisplayName(cc.Chat("&c&lNo"));
		
		ArrayList<String> glore = new ArrayList<String>();
		ArrayList<String> rlore = new ArrayList<String>();
		glore.add(cc.Chat("&aDo take fall damage"));
		rlore.add(cc.Chat("&cDo not take fall damage"));
		gwm.setLore(glore);
		rwm.setLore(rlore);
		
		
		greenwool.setItemMeta(gwm);
		redwool.setItemMeta(rwm);
		
		IntervalTime.setItem(0, greenwool);
		IntervalTime.setItem(8, redwool);
		p.openInventory(IntervalTime);
	}
	
	@SuppressWarnings("deprecation")
	public void setLaunchDistanceGUI(Player p) {
		Inventory IntervalTime = Bukkit.createInventory(p, 9, cc.Chat("&bSet Launch Distance"));
		
		ItemStack greenwool = new ItemStack(Material.WOOL, 1, DyeColor.GREEN.getData());
		ItemStack yellowwool = new ItemStack(Material.WOOL, 1, DyeColor.YELLOW.getData());
		ItemStack redwool = new ItemStack(Material.WOOL, 1, DyeColor.RED.getData());
		
		ItemMeta gwm = greenwool.getItemMeta();
		ItemMeta ywm = yellowwool.getItemMeta();
		ItemMeta rwm = redwool.getItemMeta();
		
		gwm.setDisplayName(cc.Chat("&a&lLaunch a little distance"));
		ywm.setDisplayName(cc.Chat("&e&lLaunch a medium distance"));
		rwm.setDisplayName(cc.Chat("&c&lLaunch a really far distance"));
		
		ArrayList<String> glore = new ArrayList<String>();
		ArrayList<String> ylore = new ArrayList<String>();
		ArrayList<String> rlore = new ArrayList<String>();
		
		glore.add(cc.Chat("&aMin x/z velocity: -1"));
		glore.add(cc.Chat("&aMax x/z velocity: 1"));
		glore.add(cc.Chat(""));
		glore.add(cc.Chat("&aMin y velocity: -0.25"));
		glore.add(cc.Chat("&aMax y velocity: 0.25"));
		
		ylore.add(cc.Chat("&eMin x/z velocity: -2"));
		ylore.add(cc.Chat("&eMax x/z velocity: 2"));
		ylore.add("");
		ylore.add(cc.Chat("&eMin y velocity: -0.5"));
		ylore.add(cc.Chat("&eMax y velocity: 0.5"));
		
		rlore.add(cc.Chat("&cMin x/z velocity: -4"));
		rlore.add(cc.Chat("&cMax x/z velocity: 4"));
		rlore.add("");
		rlore.add(cc.Chat("&cMin y velocity: -1"));
		rlore.add(cc.Chat("&cMax y velocity: 1"));
		
		gwm.setLore(glore);
		ywm.setLore(ylore);
		rwm.setLore(rlore);
		
		
		greenwool.setItemMeta(gwm);
		yellowwool.setItemMeta(ywm);
		redwool.setItemMeta(rwm);
		
		IntervalTime.setItem(0, greenwool);
		IntervalTime.setItem(4, yellowwool);
		IntervalTime.setItem(8, redwool);
		p.openInventory(IntervalTime);
		
	}

	@SuppressWarnings("deprecation")
	private void intervalTimeGUI(Player p) {
		Inventory IntervalTime = Bukkit.createInventory(p, 9, cc.Chat("&bSet Interval Time"));
		
		ItemStack greenwool = new ItemStack(Material.WOOL, 1, DyeColor.GREEN.getData());
		ItemStack yellowwool = new ItemStack(Material.WOOL, 1, DyeColor.YELLOW.getData());
		ItemStack redwool = new ItemStack(Material.WOOL, 1, DyeColor.RED.getData());
		
		ItemMeta gwm = greenwool.getItemMeta();
		ItemMeta ywm = yellowwool.getItemMeta();
		ItemMeta rwm = redwool.getItemMeta();
		
		gwm.setDisplayName(cc.Chat("&a&l60 ticks"));
		ywm.setDisplayName(cc.Chat("&e&l40 ticks"));
		rwm.setDisplayName(cc.Chat("&c&l20 ticks"));
		
		ArrayList<String> glore = new ArrayList<String>();
		ArrayList<String> ylore = new ArrayList<String>();
		ArrayList<String> rlore = new ArrayList<String>();
		glore.add(cc.Chat("&aLaunch every 3 seconds!"));
		ylore.add(cc.Chat("&eLaunch every 2 seconds!"));
		rlore.add(cc.Chat("&cLaunch every second!"));
		gwm.setLore(glore);
		ywm.setLore(ylore);
		rwm.setLore(rlore);
		
		
		greenwool.setItemMeta(gwm);
		yellowwool.setItemMeta(ywm);
		redwool.setItemMeta(rwm);
		
		IntervalTime.setItem(0, greenwool);
		IntervalTime.setItem(4, yellowwool);
		IntervalTime.setItem(8, redwool);
		p.openInventory(IntervalTime);
	}
	
}
