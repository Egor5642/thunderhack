/*    */ package thunder.hack.features.cmd.impl;
/*    */ import baritone.api.BaritoneAPI;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.class_2172;
/*    */ import thunder.hack.ThunderHack;
/*    */ import thunder.hack.core.manager.world.WayPointManager;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ import thunder.hack.features.cmd.args.WayPointArgumentType;
/*    */ import thunder.hack.features.modules.client.ClientSettings;
/*    */ 
/*    */ public class GotoWaypointCommand extends Command {
/*    */   public GotoWaypointCommand() {
/* 16 */     super(new String[] { "goto" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(LiteralArgumentBuilder<class_2172> builder) {
/* 21 */     builder.then(arg("name", (ArgumentType)WayPointArgumentType.create()).executes(context -> {
/*    */             if (!ThunderHack.baritone) {
/*    */               sendMessage(ClientSettings.isRu() ? "Баритон не найден (можешь скачать на https://meteorclient.com)" : "Baritone not found (you can download it at https://meteorclient.com)");
/*    */               return 1;
/*    */             } 
/*    */             WayPointManager.WayPoint wp = (WayPointManager.WayPoint)context.getArgument("name", WayPointManager.WayPoint.class);
/*    */             if (!mc.field_1687.method_27983().method_29177().method_12832().equals(wp.getDimension())) {
/*    */               sendMessage(ClientSettings.isRu() ? "Метка в другом измерении" : "Waypoint is in another dimension");
/*    */               return 1;
/*    */             } 
/*    */             BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute("goto " + wp.getX() + " " + wp.getY() + " " + wp.getZ());
/*    */             return 1;
/*    */           }));
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\GotoWaypointCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */