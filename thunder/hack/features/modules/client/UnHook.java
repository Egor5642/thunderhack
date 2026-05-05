/*    */ package thunder.hack.features.modules.client;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.BufferedWriter;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.class_124;
/*    */ import net.minecraft.class_155;
/*    */ import net.minecraft.class_3262;
/*    */ import net.minecraft.class_8518;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.utility.math.MathUtility;
/*    */ 
/*    */ public class UnHook extends Module {
/*    */   List<Module> list;
/*    */   
/*    */   public UnHook() {
/* 20 */     super("UnHook", Module.Category.CLIENT);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 25 */     this.code = 0;
/*    */   }
/*    */   public int code;
/*    */   public void onEnable() {
/* 29 */     this.code = (int)MathUtility.random(10.0F, 99.0F);
/* 30 */     for (int i = 0; i < 20; i++) {
/* 31 */       sendMessage(ClientSettings.isRu() ? (String.valueOf(class_124.field_1061) + "Ща все свернется, напиши в чат " + String.valueOf(class_124.field_1061) + String.valueOf(class_124.field_1068) + this.code + " чтобы все вернуть!") : (
/* 32 */           String.valueOf(class_124.field_1061) + "It's all close now, write to the chat " + String.valueOf(class_124.field_1061) + String.valueOf(class_124.field_1068) + this.code + " to return everything!"));
/*    */     }
/* 34 */     this.list = Managers.MODULE.getEnabledModules();
/*    */     
/* 36 */     mc.method_1507(null);
/*    */     
/* 38 */     Managers.ASYNC.run(() -> mc.method_40000(()), 5000L);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 87 */     if (this.list == null) {
/*    */       return;
/*    */     }
/* 90 */     for (Module module : this.list) {
/* 91 */       if (module.equals(this))
/*    */         continue; 
/* 93 */       module.enable();
/*    */     } 
/* 95 */     ClientSettings.customMainMenu.setValue(Boolean.valueOf(true));
/*    */ 
/*    */     
/*    */     try {
/* 99 */       (new File("XaeroWaypoints_BACKUP092738")).renameTo(new File("ThunderHackRecode"));
/* :0 */     } catch (Exception exception) {}
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\client\UnHook.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */