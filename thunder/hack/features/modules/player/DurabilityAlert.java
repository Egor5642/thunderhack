/*    */ package thunder.hack.features.modules.player;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.class_1657;
/*    */ import net.minecraft.class_1799;
/*    */ import net.minecraft.class_332;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.features.modules.client.ClientSettings;
/*    */ import thunder.hack.gui.font.FontRenderers;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.utility.Timer;
/*    */ import thunder.hack.utility.render.TextureStorage;
/*    */ 
/*    */ public class DurabilityAlert extends Module {
/*    */   private final Setting<Boolean> friends;
/*    */   private final Setting<Integer> percent;
/*    */   
/*    */   public DurabilityAlert() {
/* 21 */     super("DurabilityAlert", Module.Category.PLAYER);
/*    */ 
/*    */     
/* 24 */     this.friends = new Setting("Friend message", Boolean.valueOf(true));
/* 25 */     this.percent = new Setting("Percent", Integer.valueOf(20), Integer.valueOf(1), Integer.valueOf(100));
/* 26 */     this.need_alert = false;
/* 27 */     this.timer = new Timer();
/*    */   }
/*    */   private boolean need_alert; private final Timer timer;
/*    */   public void onUpdate() {
/* 31 */     if (((Boolean)this.friends.getValue()).booleanValue()) {
/* 32 */       for (class_1657 player : mc.field_1687.method_18456()) {
/* 33 */         if (!Managers.FRIEND.isFriend(player) || 
/* 34 */           player == mc.field_1724)
/* 35 */           continue;  for (class_1799 stack : (player.method_31548()).field_7548) {
/* 36 */           if (!stack.method_7960() && stack.method_7909() instanceof net.minecraft.class_1738 && 
/* 37 */             getDurability(stack) < ((Integer)this.percent.getValue()).intValue() && this.timer.passedMs(30000L)) {
/* 38 */             mc.field_1724.field_3944.method_45730("msg " + player.method_5477().getString() + (ClientSettings.isRu() ? " Срочно чини броню!" : " Fix your armor right now!"));
/*    */             
/* 40 */             this.timer.reset();
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     }
/*    */     
/* 46 */     boolean flag = false;
/* 47 */     for (class_1799 stack : (mc.field_1724.method_31548()).field_7548) {
/* 48 */       if (!stack.method_7960() && stack.method_7909() instanceof net.minecraft.class_1738 && 
/* 49 */         getDurability(stack) < ((Integer)this.percent.getValue()).intValue()) {
/* 50 */         this.need_alert = true;
/* 51 */         flag = true;
/*    */       } 
/*    */     } 
/* 54 */     if (!flag && this.need_alert) this.need_alert = false; 
/*    */   }
/*    */   
/*    */   public void onRender2D(class_332 context) {
/* 58 */     if (this.need_alert) {
/* 59 */       FontRenderers.sf_bold.drawCenteredString(context.method_51448(), ClientSettings.isRu() ? "Срочно чини броню!" : "Fix your armor right now!", (mc.method_22683().method_4486() / 2.0F), (mc.method_22683().method_4502() / 3.0F), (new Color(16768768)).getRGB());
/*    */       
/* 61 */       Color c1 = new Color(16768768);
/* 62 */       RenderSystem.setShaderColor(c1.getRed() / 255.0F, c1.getGreen() / 255.0F, c1.getBlue() / 255.0F, 1.0F);
/* 63 */       context.method_25293(TextureStorage.brokenShield, (int)(mc.method_22683().method_4486() / 2.0F - 40.0F), (int)(mc.method_22683().method_4502() / 3.0F - 120.0F), 80, 80, 0.0F, 0.0F, 80, 80, 80, 80);
/* 64 */       RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static int getDurability(class_1799 stack) {
/* 69 */     return (int)((stack.method_7936() - stack.method_7919()) / Math.max(0.1D, stack.method_7936()) * 100.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\player\DurabilityAlert.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */