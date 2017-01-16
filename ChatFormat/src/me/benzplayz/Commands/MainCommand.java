package me.benzplayz.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.benzplayz.Main;

public class MainCommand
  implements CommandExecutor
{
  private Main plugin;
  
  public MainCommand(Main plugin)
  {
    this.plugin = plugin;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    Player p = (Player)sender;
    if (args.length == 0) {
      p.sendMessage(this.plugin.getConfig().getString("UnkownCommand").replace("&", "&"));
    } else if (args[0].contains("reload"))
    {
      if (p.hasPermission("ChatSystem.Admin"))
      {
        this.plugin.reloadConfig();
        p.sendMessage(this.plugin.getConfig().getString("PluginReloaded").replace("&", "&"));
      }
      else
      {
        p.sendMessage(this.plugin.getConfig().getString("NoPermissions").replace("&", "&"));
      }
    }
    else {
      p.sendMessage(this.plugin.getConfig().getString("UnkownCommand").replace("&", "&"));
    }
    return true;
  }
}
