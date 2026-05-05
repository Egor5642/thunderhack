/*     */ package thunder.hack.core.manager.client;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_2172;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import thunder.hack.core.manager.IManager;
/*     */ import thunder.hack.features.cmd.Command;
/*     */ import thunder.hack.features.cmd.impl.BenchMarkCommand;
/*     */ import thunder.hack.features.cmd.impl.CfgCommand;
/*     */ import thunder.hack.features.cmd.impl.NukerCommand;
/*     */ import thunder.hack.features.cmd.impl.TabParseCommand;
/*     */ import thunder.hack.features.cmd.impl.VClipCommand;
/*     */ 
/*     */ public class CommandManager implements IManager {
/*  17 */   private String prefix = "@";
/*     */   
/*  19 */   private final CommandDispatcher<class_2172> dispatcher = new CommandDispatcher();
/*  20 */   private final class_2172 source = (class_2172)new class_637(null, class_310.method_1551());
/*  21 */   private final List<Command> commands = new ArrayList<>();
/*     */   
/*     */   public CommandManager() {
/*  24 */     add((Command)new RpcCommand());
/*  25 */     add((Command)new KitCommand());
/*  26 */     add((Command)new GpsCommand());
/*  27 */     add((Command)new CfgCommand());
/*  28 */     add((Command)new RctCommand());
/*  29 */     add((Command)new BindCommand());
/*  30 */     add((Command)new DrawCommand());
/*  31 */     add((Command)new HelpCommand());
/*  32 */     add((Command)new NukerCommand());
/*  33 */     add((Command)new EClipCommand());
/*  34 */     add((Command)new HClipCommand());
/*  35 */     add((Command)new LoginCommand());
/*  36 */     add((Command)new MacroCommand());
/*  37 */     add((Command)new StaffCommand());
/*  38 */     add((Command)new VClipCommand());
/*  39 */     add((Command)new AddonsCommand());
/*  40 */     add((Command)new GetNbtCommand());
/*  41 */     add((Command)new FriendCommand());
/*  42 */     add((Command)new ModuleCommand());
/*  43 */     add((Command)new PrefixCommand());
/*  44 */     add((Command)new TrackerCommand());
/*  45 */     add((Command)new GamemodeCommand());
/*  46 */     add((Command)new DropAllCommand());
/*  47 */     add((Command)new TreasureCommand());
/*  48 */     add((Command)new WayPointCommand());
/*  49 */     add((Command)new TabParseCommand());
/*  50 */     add((Command)new BlockESPCommand());
/*  51 */     add((Command)new BenchMarkCommand());
/*  52 */     add((Command)new HorseSpeedCommand());
/*  53 */     add((Command)new OpenFolderCommand());
/*  54 */     add((Command)new ResetBindsCommand());
/*  55 */     add((Command)new InvCleanerCommand());
/*  56 */     add((Command)new GotoWaypointCommand());
/*  57 */     add((Command)new ChestStealerCommand());
/*  58 */     add((Command)new GarbageCleanerCommand());
/*     */   }
/*     */   
/*     */   private void add(@NotNull Command command) {
/*  62 */     command.register(this.dispatcher);
/*  63 */     this.commands.add(command);
/*     */   }
/*     */   
/*     */   public String getPrefix() {
/*  67 */     return this.prefix;
/*     */   }
/*     */   
/*     */   public void setPrefix(String prefix) {
/*  71 */     this.prefix = prefix;
/*     */   }
/*     */   
/*     */   public Command get(Class<? extends Command> commandClass) {
/*  75 */     for (Command command : this.commands) {
/*  76 */       if (command.getClass().equals(commandClass)) return command; 
/*     */     } 
/*  78 */     return null;
/*     */   }
/*     */   @NotNull
/*     */   public static String getClientMessage() {
/*  82 */     return String.valueOf(class_124.field_1068) + "⌊" + String.valueOf(class_124.field_1068) + "⚡" + String.valueOf(class_124.field_1065) + "⌉" + String.valueOf(class_124.field_1068);
/*     */   }
/*     */   
/*     */   public List<Command> getCommands() {
/*  86 */     return this.commands;
/*     */   }
/*     */   
/*     */   public class_2172 getSource() {
/*  90 */     return this.source;
/*     */   }
/*     */   
/*     */   public CommandDispatcher<class_2172> getDispatcher() {
/*  94 */     return this.dispatcher;
/*     */   }
/*     */   
/*     */   public void registerCommand(Command command) {
/*  98 */     if (command == null)
/*     */       return; 
/* 100 */     command.register(this.dispatcher);
/* 101 */     this.commands.add(command);
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\core\manager\client\CommandManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */