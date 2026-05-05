/*     */ package thunder.hack.core;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import meteordevelopment.orbit.EventHandler;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_241;
/*     */ import net.minecraft.class_2596;
/*     */ import net.minecraft.class_2848;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_3417;
/*     */ import net.minecraft.class_3419;
/*     */ import net.minecraft.class_3532;
/*     */ import net.minecraft.class_3675;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_7439;
/*     */ import net.minecraft.class_7833;
/*     */ import thunder.hack.ThunderHack;
/*     */ import thunder.hack.core.manager.client.MacroManager;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.events.impl.EventDeath;
/*     */ import thunder.hack.events.impl.EventKeyPress;
/*     */ import thunder.hack.events.impl.EventMouse;
/*     */ import thunder.hack.events.impl.EventSync;
/*     */ import thunder.hack.events.impl.PacketEvent;
/*     */ import thunder.hack.events.impl.PlayerUpdateEvent;
/*     */ import thunder.hack.features.cmd.Command;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.ClientSettings;
/*     */ import thunder.hack.features.modules.client.HudEditor;
/*     */ import thunder.hack.gui.font.FontRenderers;
/*     */ import thunder.hack.gui.notification.Notification;
/*     */ import thunder.hack.gui.thundergui.ThunderGui;
/*     */ import thunder.hack.utility.Timer;
/*     */ import thunder.hack.utility.player.InteractionUtility;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ import thunder.hack.utility.render.Render3DEngine;
/*     */ import thunder.hack.utility.render.TextureStorage;
/*     */ import thunder.hack.utility.render.animation.CaptureMark;
/*     */ 
/*     */ public final class Core {
/*     */   public static boolean lockSprint;
/*     */   public static boolean serverSprint;
/*  50 */   public static final Map<String, class_2960> HEADS = new ConcurrentHashMap<>(); public static boolean hold_mouse0; public static boolean showSkull;
/*  51 */   public ArrayList<class_2596<?>> silentPackets = new ArrayList<>();
/*  52 */   private final Timer skullTimer = new Timer();
/*  53 */   private final Timer lastPacket = new Timer();
/*  54 */   private final Timer autoSave = new Timer();
/*  55 */   private final Timer setBackTimer = new Timer();
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onTick(PlayerUpdateEvent event) {
/*  60 */     if (Module.fullNullCheck())
/*     */       return; 
/*  62 */     Managers.NOTIFICATION.onUpdate();
/*  63 */     Managers.MODULE.onUpdate();
/*  64 */     ThunderGui.getInstance().onTick();
/*     */     
/*  66 */     if (ModuleManager.clickGui.getBind().getKey() == -1) {
/*  67 */       Command.sendMessage(String.valueOf(class_124.field_1061) + String.valueOf(class_124.field_1061));
/*  68 */       Command.sendMessage(String.valueOf(class_124.field_1061) + String.valueOf(class_124.field_1061));
/*  69 */       ModuleManager.clickGui.setBind(class_3675.method_15981("key.keyboard.p").method_1444(), false, false);
/*     */     } 
/*     */     
/*  72 */     for (class_1657 p : Module.mc.field_1687.method_18456()) {
/*  73 */       if (p.method_29504() || p.method_6032() == 0.0F) {
/*  74 */         ThunderHack.EVENT_BUS.post(new EventDeath(p));
/*     */       }
/*     */     } 
/*  77 */     if (!Objects.equals(Managers.COMMAND.getPrefix(), ClientSettings.prefix.getValue())) {
/*  78 */       Managers.COMMAND.setPrefix((String)ClientSettings.prefix.getValue());
/*     */     }
/*  80 */     (new HashMap<>(InteractionUtility.awaiting)).forEach((bp, time) -> {
/*     */           if ((float)(System.currentTimeMillis() - time.longValue()) > Managers.SERVER.getPing() * 2.0F) {
/*     */             InteractionUtility.awaiting.remove(bp);
/*     */           }
/*     */         });
/*  85 */     if (this.autoSave.every(600000L)) {
/*  86 */       Managers.FRIEND.saveFriends();
/*  87 */       Managers.CONFIG.save(Managers.CONFIG.getCurrentConfig());
/*  88 */       Managers.WAYPOINT.saveWayPoints();
/*  89 */       Managers.MACRO.saveMacro();
/*  90 */       Managers.NOTIFICATION.publicity("AutoSave", ClientSettings.isRu() ? "Сохраняю конфиг.." : "Saving config..", 3, Notification.Type.INFO);
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPacketSend(PacketEvent.Send e) {
/*  96 */     if (e.getPacket() instanceof net.minecraft.class_2828 && !(e.getPacket() instanceof net.minecraft.class_2828.class_5911)) {
/*  97 */       this.lastPacket.reset();
/*     */     }
/*  99 */     class_2596 class_2596 = e.getPacket(); if (class_2596 instanceof class_2848) { class_2848 c = (class_2848)class_2596;
/* 100 */       if (c.method_12365() == class_2848.class_2849.field_12981 || c.method_12365() == class_2848.class_2849.field_12985) {
/* 101 */         if (lockSprint) {
/* 102 */           e.cancel();
/*     */           
/*     */           return;
/*     */         } 
/* 106 */         switch (c.method_12365()) { case field_12981:
/* 107 */             serverSprint = true; break;
/* 108 */           case field_12985: serverSprint = false;
/*     */             break; }
/*     */       
/*     */       }  }
/*     */   
/*     */   }
/*     */   @EventHandler
/*     */   public void onSync(EventSync event) {
/* 116 */     if (Module.fullNullCheck())
/* 117 */       return;  ModuleManager.timer.onEntitySync();
/* 118 */     CaptureMark.tick();
/* 119 */     Render3DEngine.updateTargetESP();
/*     */   }
/*     */   
/*     */   public void onRender2D(class_332 e) {
/* 123 */     drawGps(e);
/* 124 */     drawSkull(e);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPacketReceive(PacketEvent.Receive e) {
/* 129 */     if (Module.fullNullCheck())
/*     */       return; 
/* 131 */     if (e.getPacket() instanceof class_7439) {
/* 132 */       class_7439 packet = (class_7439)e.getPacket();
/* 133 */       if (packet.comp_763().getString().contains("skull")) {
/* 134 */         showSkull = true;
/* 135 */         this.skullTimer.reset();
/* 136 */         Module.mc.field_1687.method_8396((class_1657)Module.mc.field_1724, Module.mc.field_1724.method_24515(), class_3417.field_14877, class_3419.field_15245, 1.0F, 1.0F);
/*     */       } 
/*     */     } 
/*     */     
/* 140 */     if (e.getPacket() instanceof net.minecraft.class_2678) {
/* 141 */       Managers.MODULE.onLogin();
/*     */     }
/* 143 */     if (e.getPacket() instanceof net.minecraft.class_2708) {
/* 144 */       this.setBackTimer.reset();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawSkull(class_332 e) {
/* 159 */     if (showSkull && !this.skullTimer.passedMs(3000L) && ((Boolean)ClientSettings.skullEmoji.getValue()).booleanValue())
/* 160 */     { int xPos = (int)(Module.mc.method_22683().method_4486() / 2.0F - 150.0F);
/* 161 */       int yPos = (int)(Module.mc.method_22683().method_4502() / 2.0F - 150.0F);
/* 162 */       float alpha = 1.0F - (float)this.skullTimer.getPassedTimeMs() / 3000.0F;
/* 163 */       RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, alpha);
/* 164 */       e.method_25290(TextureStorage.skull, xPos, yPos, 0.0F, 0.0F, 300, 300, 300, 300);
/* 165 */       RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F); }
/* 166 */     else { showSkull = false; }
/*     */   
/*     */   }
/*     */   public void drawGps(class_332 e) {
/* 170 */     if (ThunderHack.gps_position != null) {
/* 171 */       float dst = getDistance(ThunderHack.gps_position);
/* 172 */       float xOffset = Module.mc.method_22683().method_4486() / 2.0F;
/* 173 */       float yOffset = Module.mc.method_22683().method_4502() / 2.0F;
/* 174 */       float yaw = getRotations(new class_241(ThunderHack.gps_position.method_10263(), ThunderHack.gps_position.method_10260())) - Module.mc.field_1724.method_36454();
/* 175 */       e.method_51448().method_46416(xOffset, yOffset, 0.0F);
/* 176 */       e.method_51448().method_22907(class_7833.field_40718.rotationDegrees(yaw));
/* 177 */       e.method_51448().method_46416(-xOffset, -yOffset, 0.0F);
/* 178 */       Render2DEngine.drawTracerPointer(e.method_51448(), xOffset, yOffset - 50.0F, 12.5F, 0.5F, 3.63F, true, true, HudEditor.getColor(1).getRGB());
/* 179 */       e.method_51448().method_46416(xOffset, yOffset, 0.0F);
/* 180 */       e.method_51448().method_22907(class_7833.field_40718.rotationDegrees(-yaw));
/* 181 */       e.method_51448().method_46416(-xOffset, -yOffset, 0.0F);
/* 182 */       RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
/* 183 */       FontRenderers.modules.drawCenteredString(e.method_51448(), "gps (" + dst + "m)", ((float)(Math.sin(Math.toRadians(yaw)) * 50.0D) + xOffset), ((float)(yOffset - Math.cos(Math.toRadians(yaw)) * 50.0D) - 23.0F), -1);
/*     */       
/* 185 */       if (dst < 10.0F)
/* 186 */         ThunderHack.gps_position = null; 
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onKeyPress(EventKeyPress event) {
/* 192 */     if (event.getKey() == -1)
/* 193 */       return;  for (MacroManager.Macro m : Managers.MACRO.getMacros()) {
/* 194 */       if (m.getBind() == event.getKey())
/* 195 */         m.runMacro(); 
/*     */     } 
/*     */   }
/*     */   @EventHandler
/*     */   public void onMouse(EventMouse event) {
/* 200 */     if (event.getAction() == 0) hold_mouse0 = false; 
/* 201 */     if (event.getAction() == 1) hold_mouse0 = true; 
/*     */   }
/*     */   
/*     */   public int getDistance(class_2338 bp) {
/* 205 */     double d0 = Module.mc.field_1724.method_23317() - bp.method_10263();
/* 206 */     double d2 = Module.mc.field_1724.method_23321() - bp.method_10260();
/* 207 */     return (int)class_3532.method_15355((float)(d0 * d0 + d2 * d2));
/*     */   }
/*     */   
/*     */   public long getSetBackTime() {
/* 211 */     return this.setBackTimer.getPassedTimeMs();
/*     */   }
/*     */   
/*     */   public static float getRotations(class_241 vec) {
/* 215 */     if (Module.mc.field_1724 == null) return 0.0F; 
/* 216 */     double x = vec.field_1343 - (Module.mc.field_1724.method_19538()).field_1352;
/* 217 */     double z = vec.field_1342 - (Module.mc.field_1724.method_19538()).field_1350;
/* 218 */     return (float)-(Math.atan2(x, z) * 57.29577951308232D);
/*     */   }
/*     */   public void bobView(class_4587 matrices, float tickDelta) {
/*     */     class_1657 playerEntity;
/* 222 */     class_1297 class_1297 = Module.mc.method_1560(); if (class_1297 instanceof class_1657) { playerEntity = (class_1657)class_1297; }
/*     */     else
/*     */     { return; }
/*     */     
/* 226 */     float g = -(playerEntity.field_5973 + (playerEntity.field_5973 - playerEntity.field_6039) * tickDelta);
/* 227 */     float h = class_3532.method_16439(tickDelta, playerEntity.field_7505, playerEntity.field_7483);
/* 228 */     matrices.method_22904((class_3532.method_15374(g * 3.1415927F) * h * 0.1F), -Math.abs(class_3532.method_15362(g * 3.1415927F) * h) * 0.3D, 0.0D);
/* 229 */     matrices.method_22907(class_7833.field_40718.rotationDegrees(class_3532.method_15374(g * 3.1415927F) * h * 3.0F));
/* 230 */     matrices.method_22907(class_7833.field_40714.rotationDegrees(Math.abs(class_3532.method_15362(g * 3.1415927F - 0.2F) * h) * 0.3F));
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\core\Core.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */