package net.klzed.slappingplayers;

//These are automatically added by Eclipse when they're used. <3 Eclipse.
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		//Let's the console know.
		getLogger().info("SlappingPlayers has been enabled! Version 1.0 by Klz");
		
		//Getting the PluginManager.
		//This is how we're going to add our permissions. 
		PluginManager pm = getServer().getPluginManager();
		//Each permission that we use needs to be added.
		//By default, OPs have permission to these commands.
		pm.addPermission(new Permission("slappingplayers.slap", PermissionDefault.OP));
		pm.addPermission(new Permission("slappingplayers.slap.damage", PermissionDefault.OP));
	}
	
	@Override
	public void onDisable() {
		//Let's the console know.
		getLogger().info("SlappingPlayers has been disabled.");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//Our plugin received word from Spigot that someone's running our command! 
		//We need to check what command is being run, if we have multiple commands defined in our plugin.yml file.
		//In this case, we check to see if the player ran "/slap", which is our only command.
		//If so, then we move inside the if.
		if (cmd.getName().equalsIgnoreCase("slap")) {
			//Is the command a player, or a console command?
			//If the "sender" isn't a "Player", it's the Console.
			//We don't want the Console to run this command.
			//If we find that "sender" isn't a player, it runs everything in the braces and ends.
			//"return true" signals to Spigot that the command has executed successfully.
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command cannot be run as the console.");
				return true;
			}
			//Oh, it's a player! Let's assign them a variable.
			//At this point, it's safe to assume we have a real Player running the command.
			//So we identify the "sender" as a Player, and save their data into a variable called "player".
			//This specifically is called "Type Casting", and can be an advanced topic.
			//For now, just know that "sender", if it's passes the above check, can be changed into a "Player".
			//It's so confusing, sorry. I just followed what the tutorials were doing ;-;
			Player player = (Player) sender;
			
			//Did they specify someone?
			//We check this by comparing the amount of arguments in "args".
			//If there's no arguments, then we just send them a message and return true.
			if (args.length == 0) {
				player.sendMessage(new String[]{ChatColor.AQUA+"SlappingPlayers by Klz - version 1.0",ChatColor.GREEN + "Usage: /slap <player> [damage]"});
				return true;
			}
			
			//Does the player have the permission to slap?
			//If not, they're denied and we return true.
			//This cuts them off from the rest of the plugin, because once it returns true, nothing else is run.
			//It signals the end of execution, effectively stopping the command early.
			if (!player.hasPermission("slappingplayers.slap")) {
				player.sendMessage(ChatColor.RED + "You do not have permission for this command.");
				return true;
			}
			
			//Is the target online?
			//"Bukkit.getPlayer()" will give us "null" if the player that's specified isn't online.
			//args[0] is supposed to be a player, but if not, we'll check for it anyways.
			//So, if the checked-for player isn't online, we let the command-sender know, and we're done.
			if (Bukkit.getPlayer(args[0]) == null) {
				player.sendMessage(ChatColor.RED + "The player isn't online or cannot be found!");
				return true;
			}
			
			//The console isn't requesting /slap, the player they want to slap is online,
			//and they have permission to slap. All the checks passed, so let's keep moving!
			
			//We get the slapped player's data by using "getPlayer()" and save it into the variable "slapped".
			//This is for later use.
			Player slapped = Bukkit.getPlayer(args[0]);
			//We define, by default, how much damage a slap will cause.
			double damage = 0;
			
			//Here, we're trying to see if the argument has a 2nd parameter, which might be damage.
			//If it does, then we check to see if the player has permission to deal damage.
			//If not, then we let them know and cut them off.
			//If so, then we try to convert the argument into a number value.
			
			//If the conversion works, then our "damage" is set to the argument's number value.
			//if it fails, then the "catch()" portion takes over, allowing us to continue even though we came across an error.
			//--------
			//Using "try" and "catch" to handle errors is called Exception Handling, and is 
			//very useful and important for catching things that don't go the way you want them to.
			//Specifically, like taking a String of text and trying to turn it into a number value.
			//You can't take "abc" and turn it into a number, but you /can/ take "123" and turn it into a number.
			//so we "try" to convert it, and if it fails, we can "catch" that exception and handle it, without it crashing us completely.
			try {
				if (args.length == 2) {
					//do they even have permission to hurt someone with a slap?
					if (!player.hasPermission("slappingplayers.slap.damage")) {
						player.sendMessage(ChatColor.RED + "You don't have permission to cause actual damage with slaps!");
						return true;
					}
					//"Double" is a numerical data type.
					damage = Double.parseDouble(args[1]);
				}
			} catch (NumberFormatException ex) {
				//Shit, something happened.. Let them know.
				sender.sendMessage(ChatColor.RED + "Your damage must be a number!");
				return true;
			}
			
			//After every single check, we're down to everything being given the OK.
			//This is after the permission checks, the player checks, the damage checks, and the Console check.
			
			//We take command sender, the "player" and send him a message saying that he just slapped someone!
			player.sendMessage(ChatColor.GREEN + "You just slapped " + slapped.getName() + "!");
			//We take the player being slapped, and send them a message saying that they were slapped by the command sender!
			slapped.sendMessage(ChatColor.RED + sender.getName() + " just slapped you!");
			//We deal the /damage/ to the player being slapped, even if it's 0 damage for effect.
			slapped.damage(damage);
			//We output info to the console for who slapped who and for how much damage.
			getLogger().info("[SlappingPlayers]" + player.getName() + " slapped " +slapped.getName()+" for "+damage+" damage!");
			//and then we're done!
			return true;
			
		}
		//This is if our command wasn't called, but Spigot thought it was.
		//This is here just in case, but otherwise will never actually be run.
		return false;
	}
}
