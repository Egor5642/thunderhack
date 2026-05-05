/*    */ package thunder.hack.features.cmd.impl;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*    */ import com.mojang.brigadier.arguments.StringArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.class_2172;
/*    */ import net.minecraft.class_2338;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.core.manager.world.WayPointManager;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ import thunder.hack.features.modules.client.ClientSettings;
/*    */ 
/*    */ public class WayPointCommand extends Command {
/*    */   public WayPointCommand() {
/* 19 */     super(new String[] { "waypoint", "waypoints" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/* 24 */     builder.then(literal("list").executes(context -> {
/*    */             sendMessage(ClientSettings.isRu() ? "Метки:" : "Waypoints:");
/*    */             
/*    */             sendMessage(" ");
/*    */             
/*    */             Managers.WAYPOINT.getWayPoints().forEach(());
/*    */             
/*    */             return 1;
/*    */           }));
/* 33 */     builder.then(literal("remove").then(arg("name", (ArgumentType)WayPointArgumentType.create()).executes(context -> {
/*    */               WayPointManager.WayPoint wp = (WayPointManager.WayPoint)context.getArgument("name", WayPointManager.WayPoint.class);
/*    */               
/*    */               Managers.WAYPOINT.removeWayPoint(wp);
/*    */               
/*    */               sendMessage(ClientSettings.isRu() ? "Удалена метка " : ("Deleted waypoint " + wp.getName()));
/*    */               return 1;
/*    */             })));
/* 41 */     builder.then(literal("add").then(((RequiredArgumentBuilder)arg("name", (ArgumentType)StringArgumentType.word()).executes(context -> {
/*    */               String name = (String)context.getArgument("name", String.class);
/*    */               
/*    */               WayPointManager.WayPoint wp = new WayPointManager.WayPoint((int)mc.field_1724.method_23317(), (int)mc.field_1724.method_23318(), (int)mc.field_1724.method_23321(), name, mc.method_1542() ? "SinglePlayer" : (mc.method_1562().method_45734()).field_3761, mc.field_1687.method_27983().method_29177().method_12832());
/*    */               
/*    */               Managers.WAYPOINT.addWayPoint(wp);
/*    */               sendMessage((ClientSettings.isRu() ? ("Добавлена метка " + name + " с координатами") : ("Added waypoint " + name + " with coords")) + " X: " + (ClientSettings.isRu() ? ("Добавлена метка " + name + " с координатами") : ("Added waypoint " + name + " with coords")) + " Y: " + (int)mc.field_1724.method_23317() + " Z: " + (int)mc.field_1724.method_23318());
/*    */               return 1;
/* 49 */             })).then(arg("x", (ArgumentType)IntegerArgumentType.integer())
/* 50 */             .then(arg("y", (ArgumentType)IntegerArgumentType.integer())
/* 51 */               .then(arg("z", (ArgumentType)IntegerArgumentType.integer()).executes(context -> {
/*    */                     String name = (String)context.getArgument("name", String.class);
/*    */                     
/*    */                     class_2338 pos = new class_2338(((Integer)context.getArgument("x", Integer.class)).intValue(), ((Integer)context.getArgument("y", Integer.class)).intValue(), ((Integer)context.getArgument("z", Integer.class)).intValue());
/*    */                     
/*    */                     WayPointManager.WayPoint wp = new WayPointManager.WayPoint(pos.method_10263(), pos.method_10264(), pos.method_10260(), name, mc.method_1542() ? "SinglePlayer" : (mc.method_1562().method_45734()).field_3761, mc.field_1687.method_27983().method_29177().method_12832());
/*    */                     
/*    */                     Managers.WAYPOINT.addWayPoint(wp);
/*    */                     
/*    */                     sendMessage((ClientSettings.isRu() ? ("Добавлена метка " + name + " с координатами X: ") : ("Added waypoint " + name + " with coords")) + (ClientSettings.isRu() ? ("Добавлена метка " + name + " с координатами X: ") : ("Added waypoint " + name + " with coords")) + " Y: " + pos.method_10263() + " Z: " + pos.method_10264());
/*    */                     return 1;
/*    */                   }))))));
/* 63 */     builder.executes(context -> {
/*    */           sendMessage(usage());
/*    */           return 1;
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   String usage() {
/* 71 */     return "waypoint add/remove/list (waypoint add x y z name), (waypoint remove name)";
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\WayPointCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */