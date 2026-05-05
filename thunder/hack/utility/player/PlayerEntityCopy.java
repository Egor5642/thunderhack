/*    */ package thunder.hack.utility.player;
/*    */ import java.util.Objects;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.class_1297;
/*    */ import net.minecraft.class_638;
/*    */ import net.minecraft.class_745;
/*    */ import net.minecraft.class_746;
/*    */ import thunder.hack.features.modules.Module;
/*    */ 
/*    */ public class PlayerEntityCopy extends class_745 {
/*    */   public PlayerEntityCopy() {
/* 12 */     super(Objects.<class_638>requireNonNull(Module.mc.field_1687), ((class_746)Objects.<class_746>requireNonNull(Module.mc.field_1724)).method_7334());
/*    */     
/* 14 */     method_5878((class_1297)Module.mc.field_1724);
/* 15 */     method_3123();
/* 16 */     this.field_6011.method_12778(field_7518, Module.mc.field_1724.method_5841().method_12789(field_7518));
/* 17 */     method_5826(UUID.randomUUID());
/*    */   }
/*    */   
/*    */   public void spawn() {
/* 21 */     if (Module.mc.field_1687 == null)
/*    */       return; 
/* 23 */     method_31482();
/* 24 */     Module.mc.field_1687.method_53875((class_1297)this);
/*    */   }
/*    */   
/*    */   public void deSpawn() {
/* 28 */     if (Module.mc.field_1687 == null)
/*    */       return; 
/* 30 */     Module.mc.field_1687.method_2945(method_5628(), class_1297.class_5529.field_26999);
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\player\PlayerEntityCopy.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */