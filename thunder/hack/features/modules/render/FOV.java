/*    */ package thunder.hack.features.modules.render;
/*    */ 
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public class FOV extends Module {
/*    */   public FOV() {
/*  8 */     super("FOV", Module.Category.RENDER);
/*    */ 
/*    */     
/* 11 */     this.fovModifier = new Setting("FOV modifier", Integer.valueOf(120), Integer.valueOf(0), Integer.valueOf(358));
/* 12 */     this.itemFov = new Setting("Item Fov", Boolean.valueOf(false));
/* 13 */     this.itemFovModifier = new Setting("Item FOV modifier", Integer.valueOf(120), Integer.valueOf(0), Integer.valueOf(358));
/*    */   }
/*    */   
/*    */   public final Setting<Integer> fovModifier;
/*    */   public final Setting<Boolean> itemFov;
/*    */   public final Setting<Integer> itemFovModifier;
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\render\FOV.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */