/*    */ package thunder.hack.features.modules.misc;
/*    */ 
/*    */ import java.io.BufferedWriter;
/*    */ import java.io.File;
/*    */ import java.io.FileWriter;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.class_1657;
/*    */ import net.minecraft.class_2586;
/*    */ import net.minecraft.class_2818;
/*    */ import net.minecraft.class_3417;
/*    */ import net.minecraft.class_3419;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.core.manager.client.ConfigManager;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.features.modules.render.StorageEsp;
/*    */ import thunder.hack.gui.notification.Notification;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public class StashLogger
/*    */   extends Module
/*    */ {
/* 23 */   private final Setting<Boolean> sound = new Setting("Sound", Boolean.valueOf(true));
/* 24 */   private final Setting<Boolean> saveToFile = new Setting("SaveToFile", Boolean.valueOf(true));
/* 25 */   private final Setting<Integer> minChests = new Setting("MinChests", Integer.valueOf(5), Integer.valueOf(0), Integer.valueOf(100));
/* 26 */   private final Setting<Integer> minShulkers = new Setting("MinShulkers", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(100));
/*    */   
/* 28 */   private List<class_2818> savedChunks = new ArrayList<>();
/*    */   
/*    */   public StashLogger() {
/* 31 */     super("StashLogger", Module.Category.MISC);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 36 */     this.savedChunks.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 41 */     for (class_2818 chunk : StorageEsp.getLoadedChunks()) {
/* 42 */       if (this.savedChunks.contains(chunk)) {
/*    */         continue;
/*    */       }
/* 45 */       List<class_2586> storages = chunk.method_12214().values().stream().toList();
/*    */       
/* 47 */       int chests = 0;
/* 48 */       int shulkers = 0;
/*    */       
/* 50 */       for (class_2586 storage : storages) {
/* 51 */         if (storage instanceof net.minecraft.class_2595)
/* 52 */           chests++; 
/* 53 */         if (storage instanceof net.minecraft.class_2627) {
/* 54 */           shulkers++;
/*    */         }
/*    */       } 
/* 57 */       if (chests >= ((Integer)this.minChests.getValue()).intValue() && shulkers >= ((Integer)this.minShulkers.getValue()).intValue()) {
/* 58 */         this.savedChunks.add(chunk);
/*    */         
/* 60 */         String str = "Stash pos: X:" + chunk.method_12004().method_33940() + " Z:" + chunk.method_12004().method_33942() + " Chests: " + chests + " Shulkers: " + shulkers;
/* 61 */         Managers.NOTIFICATION.publicity("StashLogger", str, 5, Notification.Type.SUCCESS);
/* 62 */         sendMessage(str);
/*    */         
/* 64 */         if (((Boolean)this.sound.getValue()).booleanValue()) {
/* 65 */           mc.field_1687.method_8396((class_1657)mc.field_1724, mc.field_1724.method_24515(), class_3417.field_14709, class_3419.field_15245, 1.0F, 1.0F);
/*    */         }
/* 67 */         String serverIP = "unknown_server";
/* 68 */         if (mc.method_1562().method_45734() != null && (mc.method_1562().method_45734()).field_3761 != null) {
/* 69 */           serverIP = (mc.method_1562().method_45734()).field_3761.replace(':', '_');
/*    */         }
/* 71 */         if (((Boolean)this.saveToFile.getValue()).booleanValue())
/*    */           try {
/* 73 */             BufferedWriter writer = new BufferedWriter(new FileWriter(new File(ConfigManager.STASHLOGGER_FOLDER, serverIP + ".txt"), true));
/* 74 */             writer.append("\nStash pos: X:" + chunk.method_12004().method_33940() + " Z:" + chunk.method_12004().method_33942() + " Chests: " + chests + " Shulkers: " + shulkers);
/* 75 */             writer.close();
/* 76 */           } catch (Exception exception) {} 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\misc\StashLogger.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */