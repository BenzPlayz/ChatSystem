package me.benzplayz;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import me.benzplayz.Commands.MainCommand;
import me.benzplayz.Listeners.PlayerListener;

public class Main
  extends JavaPlugin
{
  public Chat chat = null;
  public Permission perms = null;
  public boolean placeholders = false;
  
  public void onEnable()
  {
    setup();
  }
  
  public void onDisable()
  {
    this.chat = null;
    this.perms = null;
    this.placeholders = false;
  }
  
  public void setup()
  {
    if (getServer().getPluginManager().getPlugin("Vault") == null)
    {
      getLogger().info("--- Chat System ---");
      getLogger().info(" ");
      getLogger().info("   - Vault not found! Disabling plugin...");
      getLogger().info(" ");
      getLogger().info("   - Plugin sucesfully disabled!");
      getLogger().info("   - Plugin made by BenTheNoble");
      getLogger().info("   - Used for MC-Central Network!");
      getLogger().info(" ");
      getLogger().info("---------------------");
      getServer().getPluginManager().disablePlugin(this);
    }
    else
    {
      setupPermissions();
      setupChat();
      getLogger().info("--- Chat System ---");
      getLogger().info(" ");
      getLogger().info("   - Vault hooked!");
      getLogger().info(" ");
      }
      if (setupPlaceHolderAPI())
      {
        getLogger().info("   - PlaceHolderAPI hooked!");
        getLogger().info(" ");
      }
      getLogger().info("   - Plugin sucesfully enabled!");
      getLogger().info("   - Plugin made by BenTheNoble");
      getLogger().info("   - Used for MC-Central Network!");
      getLogger().info(" ");
      getLogger().info("---------------------");
      saveDefaultConfig();
      getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
      getCommand("chatsystem").setExecutor(new MainCommand(this));
    }
  
  
  public boolean setupChat()
  {
    RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
    this.chat = ((Chat)rsp.getProvider());
    return this.chat != null;
  }
  
  public boolean setupPermissions()
  {
    RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
    this.perms = ((Permission)rsp.getProvider());
    return this.perms != null;
  }
  
  
  public boolean setupPlaceHolderAPI()
  {
    if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
      this.placeholders = true;
    }
    return this.placeholders;
  }
}