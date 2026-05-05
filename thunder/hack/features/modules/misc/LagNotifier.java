/*    */ package thunder.hack.features.modules.misc;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import java.awt.Color;
/*    */ import java.text.DecimalFormat;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_332;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.events.impl.PacketEvent;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.features.modules.client.ClientSettings;
/*    */ import thunder.hack.gui.font.FontRenderers;
/*    */ import thunder.hack.gui.notification.Notification;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.utility.Timer;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ import thunder.hack.utility.render.TextureStorage;
/*    */ 
/*    */ public class LagNotifier extends Module {
/*    */   private final Setting<Boolean> rubberbandNotify;
/*    */   private final Setting<Boolean> serverResponseNotify;
/*    */   private final Setting<Integer> responseTreshold;
/*    */   private final Setting<Boolean> tpsNotify;
/*    */   
/*    */   public LagNotifier() {
/* 26 */     super("LagNotifier", Module.Category.MISC);
/*    */ 
/*    */     
/* 29 */     this.rubberbandNotify = new Setting("Rubberband", Boolean.valueOf(true));
/* 30 */     this.serverResponseNotify = new Setting("ServerResponse", Boolean.valueOf(true));
/* 31 */     this.responseTreshold = new Setting("ResponseTreshold", Integer.valueOf(5), Integer.valueOf(0), Integer.valueOf(15), v -> ((Boolean)this.serverResponseNotify.getValue()).booleanValue());
/* 32 */     this.tpsNotify = new Setting("TPS", Boolean.valueOf(true));
/*    */     
/* 34 */     this.notifyTimer = new Timer();
/* 35 */     this.rubberbandTimer = new Timer();
/* 36 */     this.packetTimer = new Timer();
/*    */     
/* 38 */     this.isLagging = false;
/*    */   }
/*    */   private Timer notifyTimer; private Timer rubberbandTimer; private Timer packetTimer; private boolean isLagging;
/*    */   public void onEnable() {
/* 42 */     this.notifyTimer = new Timer();
/* 43 */     this.rubberbandTimer = new Timer();
/* 44 */     this.packetTimer = new Timer();
/* 45 */     this.isLagging = false;
/*    */     
/* 47 */     super.onEnable();
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPacketReceive(PacketEvent.Receive e) {
/* 52 */     if (fullNullCheck())
/*    */       return; 
/* 54 */     if (e.getPacket() instanceof net.minecraft.class_2708) this.rubberbandTimer.reset(); 
/* 55 */     if (e.getPacket() instanceof net.minecraft.class_2761) this.packetTimer.reset(); 
/*    */   }
/*    */   
/*    */   public void onRender2D(class_332 context) {
/* 59 */     Render2DEngine.setupRender();
/* 60 */     RenderSystem.defaultBlendFunc();
/*    */     
/* 62 */     if (!this.rubberbandTimer.passedMs(5000L) && ((Boolean)this.rubberbandNotify.getValue()).booleanValue()) {
/* 63 */       DecimalFormat decimalFormat = new DecimalFormat("#.#");
/* 64 */       FontRenderers.modules.drawCenteredString(context.method_51448(), (ClientSettings.isRu() ? "Обнаружен руббербенд! " : "Rubberband detected! ") + (ClientSettings.isRu() ? "Обнаружен руббербенд! " : "Rubberband detected! "), (mc.method_22683().method_4486() / 2.0F), (mc.method_22683().method_4502() / 3.0F), (new Color(16768768)).getRGB());
/*    */     } 
/*    */     
/* 67 */     if (this.packetTimer.passedMs(((Integer)this.responseTreshold.getValue()).intValue() * 1000L) && ((Boolean)this.serverResponseNotify.getValue()).booleanValue()) {
/* 68 */       DecimalFormat decimalFormat = new DecimalFormat("#.#");
/* 69 */       FontRenderers.modules.drawCenteredString(context.method_51448(), (ClientSettings.isRu() ? "Сервер перестал отвечать! " : "The server stopped responding! ") + (ClientSettings.isRu() ? "Сервер перестал отвечать! " : "The server stopped responding! "), (mc.method_22683().method_4486() / 2.0F), (mc.method_22683().method_4502() / 3.0F), (new Color(16768768)).getRGB());
/*    */       
/* 71 */       RenderSystem.setShaderColor(1.0F, 0.87F, 0.0F, 1.0F);
/* 72 */       context.method_25290(TextureStorage.lagIcon, (int)(mc.method_22683().method_4486() / 2.0F - 40.0F), (int)(mc.method_22683().method_4502() / 3.0F - 120.0F), 0.0F, 0.0F, 80, 80, 80, 80);
/* 73 */       RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     } 
/*    */     
/* 76 */     if (Managers.SERVER.getTPS() < 10.0F && this.notifyTimer.passedMs(60000L) && ((Boolean)this.tpsNotify.getValue()).booleanValue()) {
/* 77 */       String msg = ClientSettings.isRu() ? "ТПС сервера ниже 10!" : "Server TPS is below 10!";
/* 78 */       if (ModuleManager.tpsSync.isDisabled()) msg = msg + msg; 
/* 79 */       Managers.NOTIFICATION.publicity("LagNotifier", msg, 8, Notification.Type.ERROR);
/*    */       
/* 81 */       this.isLagging = true;
/* 82 */       this.notifyTimer.reset();
/*    */     } 
/*    */     
/* 85 */     if (Managers.SERVER.getTPS() > 15.0F && this.isLagging) {
/* 86 */       Managers.NOTIFICATION.publicity("LagNotifier", ClientSettings.isRu() ? "ТПС сервера стабилизировался!" : "Server TPS has stabilized!", 8, Notification.Type.SUCCESS);
/* 87 */       this.isLagging = false;
/*    */     } 
/* 89 */     Render2DEngine.endRender();
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\misc\LagNotifier.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */