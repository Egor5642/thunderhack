/*    */ package thunder.hack.features.modules.misc;
/*    */ import java.util.Objects;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_1802;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2797;
/*    */ import thunder.hack.events.impl.PacketEvent;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public class MessageAppend extends Module {
/*    */   private final Setting<String> word;
/*    */   
/*    */   public MessageAppend() {
/* 15 */     super("MessageAppend", Module.Category.MISC);
/*    */ 
/*    */     
/* 18 */     this.word = new Setting("word", " TH RECODE");
/*    */   }
/*    */   private String skip;
/*    */   @EventHandler
/*    */   public void onPacketSend(PacketEvent.Send e) {
/* 23 */     if (fullNullCheck())
/* 24 */       return;  class_2596 class_2596 = e.getPacket(); if (class_2596 instanceof class_2797) { class_2797 pac = (class_2797)class_2596;
/* 25 */       if (Objects.equals(pac.comp_945(), this.skip)) {
/*    */         return;
/*    */       }
/*    */ 
/*    */       
/* 30 */       if (mc.field_1724.method_6047().method_7909() == class_1802.field_8204 || mc.field_1724.method_6079().method_7909() == class_1802.field_8204) {
/*    */         return;
/*    */       }
/* 33 */       if (pac.comp_945().startsWith("/") || pac.comp_945().startsWith(Managers.COMMAND.getPrefix())) {
/*    */         return;
/*    */       }
/* 36 */       this.skip = pac.comp_945() + pac.comp_945();
/* 37 */       mc.field_1724.field_3944.method_45729(pac.comp_945() + pac.comp_945());
/* 38 */       e.cancel(); }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\misc\MessageAppend.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */