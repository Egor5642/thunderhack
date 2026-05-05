/*    */ package thunder.hack.features.modules.combat;
/*    */ 
/*    */ public class Reach extends Module {
/*    */   public final Setting<Float> blocksRange;
/*    */   public final Setting<Float> entityRange;
/*    */   
/*    */   public Reach() {
/*  8 */     super("Reach", Module.Category.COMBAT);
/*    */ 
/*    */     
/* 11 */     this.blocksRange = new Setting("BlocksRange", Float.valueOf(3.0F), Float.valueOf(0.1F), Float.valueOf(10.0F));
/* 12 */     this.entityRange = new Setting("EntityRange", Float.valueOf(3.0F), Float.valueOf(0.1F), Float.valueOf(10.0F));
/* 13 */     this.Creative = new Setting("Creative", Boolean.valueOf(false));
/* 14 */     this.creativeBlocksRange = new Setting("CBlocksRange", Float.valueOf(5.0F), Float.valueOf(0.1F), Float.valueOf(10.0F), v -> ((Boolean)this.Creative.getValue()).booleanValue());
/* 15 */     this.creativeEntityRange = new Setting("CEntityRange", Float.valueOf(5.0F), Float.valueOf(0.1F), Float.valueOf(10.0F), v -> ((Boolean)this.Creative.getValue()).booleanValue());
/*    */   }
/*    */   public final Setting<Boolean> Creative; public final Setting<Float> creativeBlocksRange; public final Setting<Float> creativeEntityRange;
/*    */   public String getDisplayInfo() {
/* 19 */     return "B: " + String.valueOf(this.blocksRange.getValue()) + " E:" + String.valueOf(this.entityRange.getValue());
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\combat\Reach.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */