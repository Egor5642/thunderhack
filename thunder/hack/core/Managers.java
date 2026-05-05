/*    */ package thunder.hack.core;
/*    */ 
/*    */ import thunder.hack.ThunderHack;
/*    */ import thunder.hack.core.manager.client.AddonManager;
/*    */ import thunder.hack.core.manager.client.AsyncManager;
/*    */ import thunder.hack.core.manager.client.CommandManager;
/*    */ import thunder.hack.core.manager.client.ConfigManager;
/*    */ import thunder.hack.core.manager.client.MacroManager;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.core.manager.client.NotificationManager;
/*    */ import thunder.hack.core.manager.client.ProxyManager;
/*    */ import thunder.hack.core.manager.client.ServerManager;
/*    */ import thunder.hack.core.manager.client.ShaderManager;
/*    */ import thunder.hack.core.manager.client.SoundManager;
/*    */ import thunder.hack.core.manager.client.TelemetryManager;
/*    */ import thunder.hack.core.manager.player.CombatManager;
/*    */ import thunder.hack.core.manager.player.FriendManager;
/*    */ import thunder.hack.core.manager.player.PlayerManager;
/*    */ import thunder.hack.core.manager.world.HoleManager;
/*    */ import thunder.hack.core.manager.world.WayPointManager;
/*    */ import thunder.hack.utility.ThunderUtility;
/*    */ 
/*    */ public class Managers
/*    */ {
/* 25 */   public static final CombatManager COMBAT = new CombatManager();
/* 26 */   public static final FriendManager FRIEND = new FriendManager();
/* 27 */   public static final PlayerManager PLAYER = new PlayerManager();
/*    */ 
/*    */   
/* 30 */   public static final HoleManager HOLE = new HoleManager();
/* 31 */   public static final WayPointManager WAYPOINT = new WayPointManager();
/*    */ 
/*    */   
/* 34 */   public static final AddonManager ADDON = new AddonManager();
/* 35 */   public static final AsyncManager ASYNC = new AsyncManager();
/* 36 */   public static final ModuleManager MODULE = new ModuleManager();
/* 37 */   public static final ConfigManager CONFIG = new ConfigManager();
/* 38 */   public static final MacroManager MACRO = new MacroManager();
/* 39 */   public static final NotificationManager NOTIFICATION = new NotificationManager();
/* 40 */   public static final ProxyManager PROXY = new ProxyManager();
/* 41 */   public static final ServerManager SERVER = new ServerManager();
/* 42 */   public static final ShaderManager SHADER = new ShaderManager();
/* 43 */   public static final SoundManager SOUND = new SoundManager();
/* 44 */   public static final TelemetryManager TELEMETRY = new TelemetryManager();
/* 45 */   public static final CommandManager COMMAND = new CommandManager();
/*    */   
/*    */   public static void init() {
/* 48 */     ADDON.initAddons();
/* 49 */     CONFIG.load(CONFIG.getCurrentConfig());
/* 50 */     MODULE.onLoad("none");
/* 51 */     FRIEND.loadFriends();
/* 52 */     MACRO.onLoad();
/* 53 */     WAYPOINT.onLoad();
/* 54 */     PROXY.onLoad();
/* 55 */     SOUND.registerSounds();
/*    */     
/* 57 */     ASYNC.run(() -> {
/*    */           ThunderUtility.syncContributors();
/*    */           ThunderUtility.parseStarGazer();
/*    */           ThunderUtility.parseCommits();
/*    */           TELEMETRY.fetchData();
/*    */         });
/*    */   }
/*    */   
/*    */   public static void subscribe() {
/* 66 */     ThunderHack.EVENT_BUS.subscribe(NOTIFICATION);
/* 67 */     ThunderHack.EVENT_BUS.subscribe(SERVER);
/* 68 */     ThunderHack.EVENT_BUS.subscribe(PLAYER);
/* 69 */     ThunderHack.EVENT_BUS.subscribe(COMBAT);
/* 70 */     ThunderHack.EVENT_BUS.subscribe(ASYNC);
/* 71 */     ThunderHack.EVENT_BUS.subscribe(TELEMETRY);
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\core\Managers.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */