/*    */ package thunder.hack.features.modules.client;
/*    */ 
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2856;
/*    */ import thunder.hack.events.impl.PacketEvent;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.utility.math.MathUtility;
/*    */ 
/*    */ public final class AntiServerRP
/*    */   extends Module {
/*    */   public AntiServerRP() {
/* 13 */     super("AntiServerRP", Module.Category.CLIENT);
/*    */   }
/*    */   private boolean confirm;
/*    */   private boolean accepted;
/*    */   private int delay;
/*    */   
/*    */   @EventHandler
/*    */   public void onPacketReceive(PacketEvent.Receive e) {
/* 21 */     if (e.getPacket() instanceof net.minecraft.class_2720) {
/* 22 */       this.confirm = true;
/* 23 */       this.accepted = false;
/* 24 */       this.delay = 0;
/* 25 */       e.cancel();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 31 */     if (this.confirm) {
/* 32 */       this.delay++;
/*    */       
/* 34 */       if (this.delay > MathUtility.random(15.0F, 30.0F) && !this.accepted) {
/* 35 */         sendPacket((class_2596)new class_2856(mc.field_1724.method_5667(), class_2856.class_2857.field_13016));
/* 36 */         this.accepted = true;
/*    */       } 
/*    */       
/* 39 */       if (this.delay > MathUtility.random(40.0F, 60.0F) && this.accepted) {
/* 40 */         sendPacket((class_2596)new class_2856(mc.field_1724.method_5667(), class_2856.class_2857.field_13017));
/* 41 */         this.confirm = false;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\client\AntiServerRP.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */