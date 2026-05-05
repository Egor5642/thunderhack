/*    */ package thunder.hack.features.modules.misc;
/*    */ import net.minecraft.class_1657;
/*    */ import net.minecraft.class_332;
/*    */ import net.minecraft.class_7439;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.events.impl.PacketEvent;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.features.modules.client.ClientSettings;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.utility.ThunderUtility;
/*    */ 
/*    */ public class AutoTpAccept extends Module {
/*    */   public Setting<Boolean> grief;
/*    */   public Setting<Boolean> onlyFriends;
/*    */   public Setting<Boolean> duo;
/*    */   private final Setting<Integer> timeOut;
/*    */   private TpTask tpTask;
/*    */   
/*    */   public AutoTpAccept() {
/* 20 */     super("AutoTPaccept", Module.Category.MISC);
/*    */ 
/*    */     
/* 23 */     this.grief = new Setting("Grief", Boolean.valueOf(false));
/* 24 */     this.onlyFriends = new Setting("onlyFriends", Boolean.valueOf(true));
/* 25 */     this.duo = new Setting("Duo", Boolean.valueOf(false));
/* 26 */     this.timeOut = new Setting("TimeOut", Integer.valueOf(60), Integer.valueOf(1), Integer.valueOf(180), v -> ((Boolean)this.duo.getValue()).booleanValue());
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void onPacketReceive(PacketEvent.Receive event) {
/* 32 */     if (fullNullCheck())
/* 33 */       return;  if (event.getPacket() instanceof class_7439) {
/* 34 */       class_7439 packet = (class_7439)event.getPacket();
/* 35 */       if (packet.comp_763().getString().contains("телепортироваться") || packet.comp_763().getString().contains("tpaccept"))
/* 36 */         if (((Boolean)this.onlyFriends.getValue()).booleanValue())
/* 37 */         { if (Managers.FRIEND.isFriend(ThunderUtility.solveName(packet.comp_763().getString())))
/* 38 */             if (!((Boolean)this.duo.getValue()).booleanValue()) { acceptRequest(packet.comp_763().getString()); }
/*    */             else
/* 40 */             { this.tpTask = new TpTask(() -> acceptRequest(packet.comp_763.getString()), System.currentTimeMillis()); }
/*    */               }
/* 42 */         else { acceptRequest(packet.comp_763().getString()); }
/*    */          
/*    */     } 
/*    */   }
/*    */   
/*    */   public void onRender2D(class_332 context) {
/* 48 */     if (((Boolean)this.duo.getValue()).booleanValue() && this.tpTask != null) {
/* 49 */       String text = (ClientSettings.isRu() ? "Ждем таргета " : "Awaiting target ") + (ClientSettings.isRu() ? "Ждем таргета " : "Awaiting target ");
/* 50 */       FontRenderers.sf_bold.drawCenteredString(context.method_51448(), text, (mc.method_22683().method_4486() / 2.0F), (mc.method_22683().method_4502() / 2.0F + 30.0F), HudEditor.getColor(1).getRGB());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 56 */     if (((Boolean)this.duo.getValue()).booleanValue() && this.tpTask != null) {
/* 57 */       if (System.currentTimeMillis() - this.tpTask.time > (((Integer)this.timeOut.getValue()).intValue() * 1000)) {
/* 58 */         this.tpTask = null;
/*    */         return;
/*    */       } 
/* 61 */       for (class_1657 pl : mc.field_1687.method_18456()) {
/* 62 */         if (pl == mc.field_1724 || 
/* 63 */           Managers.FRIEND.isFriend(pl))
/* 64 */           continue;  this.tpTask.task.run();
/* 65 */         this.tpTask = null;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void acceptRequest(String name) {
/* 72 */     if (((Boolean)this.grief.getValue()).booleanValue()) { mc.method_1562().method_45730("tpaccept " + ThunderUtility.solveName(name)); }
/* 73 */     else { mc.method_1562().method_45730("tpaccept"); }
/*    */   
/*    */   } private static final class TpTask extends Record { private final Runnable task; private final long time;
/* 76 */     private TpTask(Runnable task, long time) { this.task = task; this.time = time; } public final String toString() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> toString : (Lthunder/hack/features/modules/misc/AutoTpAccept$TpTask;)Ljava/lang/String;
/*    */       //   6: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #76	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/* 76 */       //   0	7	0	this	Lthunder/hack/features/modules/misc/AutoTpAccept$TpTask; } public Runnable task() { return this.task; } public final int hashCode() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> hashCode : (Lthunder/hack/features/modules/misc/AutoTpAccept$TpTask;)I
/*    */       //   6: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #76	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lthunder/hack/features/modules/misc/AutoTpAccept$TpTask; } public final boolean equals(Object o) { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: <illegal opcode> equals : (Lthunder/hack/features/modules/misc/AutoTpAccept$TpTask;Ljava/lang/Object;)Z
/*    */       //   7: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #76	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	8	0	this	Lthunder/hack/features/modules/misc/AutoTpAccept$TpTask;
/* 76 */       //   0	8	1	o	Ljava/lang/Object; } public long time() { return this.time; }
/*    */      }
/*    */ 
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\misc\AutoTpAccept.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */