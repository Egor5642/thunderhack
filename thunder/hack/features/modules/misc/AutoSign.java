/*    */ package thunder.hack.features.modules.misc;
/*    */ import java.util.Date;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_124;
/*    */ import net.minecraft.class_1268;
/*    */ import net.minecraft.class_1792;
/*    */ import net.minecraft.class_1802;
/*    */ import net.minecraft.class_2350;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_3965;
/*    */ import net.minecraft.class_437;
/*    */ import net.minecraft.class_498;
/*    */ import thunder.hack.events.impl.EventScreen;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.injection.accesors.ISignEditScreen;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.utility.player.InventoryUtility;
/*    */ import thunder.hack.utility.player.SearchInvResult;
/*    */ 
/*    */ public class AutoSign extends Module {
/*    */   private final Setting<String> line1;
/*    */   private final Setting<String> line2;
/*    */   private final Setting<String> line3;
/*    */   
/*    */   public AutoSign() {
/* 26 */     super("AutoSign", Module.Category.MISC);
/*    */ 
/*    */     
/* 29 */     this.line1 = new Setting("Line1", "<player>");
/* 30 */     this.line2 = new Setting("Line2", "was here");
/* 31 */     this.line3 = new Setting("Line3", "<------------->");
/* 32 */     this.line4 = new Setting("Line4", "<date>");
/* 33 */     this.dateFormat = new Setting("DateFormat", "dd/MM/yyyy", v -> 
/* 34 */         (((String)this.line1.getValue()).contains("<date>") || ((String)this.line2.getValue()).contains("<date>") || ((String)this.line3.getValue()).contains("<date>") || ((String)this.line4.getValue()).contains("<date>")));
/* 35 */     this.glow = new Setting("Glowing", Boolean.valueOf(false));
/*    */   } private final Setting<String> line4; private final Setting<String> dateFormat; private final Setting<Boolean> glow;
/*    */   @EventHandler
/*    */   public void onScreen(EventScreen e) {
/* 39 */     class_437 class_437 = e.getScreen(); if (class_437 instanceof class_498) { class_498 ses = (class_498)class_437;
/* 40 */       e.cancel();
/* 41 */       sendPacketSilent((class_2596)new class_2877(((ISignEditScreen)ses).getBlockEntity().method_11016(), ((ISignEditScreen)ses).isFront(), format((String)this.line1.getValue()), format((String)this.line2.getValue()), format((String)this.line3.getValue()), format((String)this.line4.getValue())));
/*    */       
/* 43 */       if (((Boolean)this.glow.getValue()).booleanValue()) {
/* 44 */         SearchInvResult result = InventoryUtility.findItemInHotBar(new class_1792[] { class_1802.field_28410 });
/* 45 */         boolean offhand = (mc.field_1724.method_6079().method_7909() == class_1802.field_28410);
/* 46 */         if (result.found() || offhand) {
/* 47 */           InventoryUtility.saveSlot();
/* 48 */           result.switchTo();
/* 49 */           mc.field_1761.method_2896(mc.field_1724, offhand ? class_1268.field_5810 : class_1268.field_5808, new class_3965(((ISignEditScreen)ses)
/* 50 */                 .getBlockEntity().method_11016().method_46558().method_1031(0.0D, 0.5D, 0.0D), class_2350.field_11036, ((ISignEditScreen)ses).getBlockEntity().method_11016(), false));
/* 51 */           sendPacket((class_2596)new class_2879(offhand ? class_1268.field_5810 : class_1268.field_5808));
/* 52 */           InventoryUtility.returnSlot();
/*    */         } 
/*    */       }  }
/*    */   
/*    */   }
/*    */   
/*    */   public String format(String s) {
/* 59 */     String format = "dd/MM/yyyy";
/*    */     
/*    */     try {
/* 62 */       format = (new SimpleDateFormat((String)this.dateFormat.getValue())).format(new Date());
/* 63 */     } catch (Exception e) {
/* 64 */       sendMessage(String.valueOf(class_124.field_1061) + String.valueOf(class_124.field_1061));
/*    */     } 
/*    */     
/* 67 */     return s.replace("<player>", mc.method_1548().method_1676()).replace("<date>", format);
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\misc\AutoSign.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */