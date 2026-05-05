/*    */ package thunder.hack.events.impl;
/*    */ 
/*    */ import net.minecraft.class_2596;
/*    */ import thunder.hack.events.Event;
/*    */ 
/*    */ public class PacketEvent extends Event {
/*    */   private final class_2596<?> packet;
/*    */   
/*    */   public PacketEvent(class_2596<?> packet) {
/* 10 */     this.packet = packet;
/*    */   }
/*    */   
/*    */   public <T extends class_2596<?>> T getPacket() {
/* 14 */     return (T)this.packet;
/*    */   }
/*    */   
/*    */   public static class Send extends PacketEvent {
/*    */     public Send(class_2596<?> packet) {
/* 19 */       super(packet);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Receive extends PacketEvent {
/*    */     public Receive(class_2596<?> packet) {
/* 25 */       super(packet);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class SendPost extends PacketEvent {
/*    */     public SendPost(class_2596<?> packet) {
/* 31 */       super(packet);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class ReceivePost extends PacketEvent {
/*    */     public ReceivePost(class_2596<?> packet) {
/* 37 */       super(packet);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\events\impl\PacketEvent.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */