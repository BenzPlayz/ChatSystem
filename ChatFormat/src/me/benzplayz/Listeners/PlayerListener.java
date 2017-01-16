package me.benzplayz.Listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerListener
  implements Listener
{
  public me.benzplayz.Main plugin;
  
  public PlayerListener(me.benzplayz.Main plugin)
  {
    this.plugin = plugin;
  }
  
  @EventHandler
  public void onChat(AsyncPlayerChatEvent e)
  {
    Player p = e.getPlayer();
    String g = this.plugin.perms.getPrimaryGroup(p);
    if (p.hasPermission("ChatSystem.Color")) {
      e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
    }
    String format = "";
    if (this.plugin.getConfig().getConfigurationSection("groups." + g) != null) {
      format = this.plugin.getConfig().getString("groups." + g + ".format");
    } else {
      format = this.plugin.getConfig().getString("Format");
    }
    format = format.replace("{PLAYER_NAME}", p.getName());
    format = format.replace("{DISPLAY_NAME}", "%s");
    format = replacePlaceholderAPI(p, format);
    format = replaceVault(p, format);
    format = ChatColor.translateAlternateColorCodes('&', format);
    format = format.replace("%", "%%");
    format = format.replace("{MESSAGE}", "%2$s");
    e.setFormat(format);
  }
  
  
  public String replaceVault(Player p, String message)
  {
    String holders = message;
    
    String rank = null;
    String prefix = null;
    String suffix = null;
    
    prefix = this.plugin.chat.getPlayerPrefix(p);
    suffix = this.plugin.chat.getPlayerSuffix(p);
    rank = this.plugin.perms.getPrimaryGroup(p);
    
    holders = holders.replace("{PREFIX}", prefix);
    holders = holders.replace("{SUFFIX}", suffix);
    return holders = holders.replace("{RANK}", rank);
    }
  
  public String replacePlaceholderAPI(Player p, String message)
  {
    String holders = message;
    if ((this.plugin.placeholders) && (PlaceholderAPI.containsPlaceholders(holders))) {
      holders = PlaceholderAPI.setPlaceholders(p, holders);
    }
    return holders;
  }
}
