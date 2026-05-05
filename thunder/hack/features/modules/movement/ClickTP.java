/*    */ package thunder.hack.features.modules.movement;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_238;
/*    */ import net.minecraft.class_239;
/*    */ import net.minecraft.class_243;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_3965;
/*    */ import net.minecraft.class_4587;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.utility.render.Render3DEngine;
/*    */ 
/*    */ public class ClickTP extends Module {
/*    */   private final Setting<Float> blockOffset;
/*    */   private final Setting<Integer> spoofs;
/*    */   
/*    */   public ClickTP() {
/* 19 */     super("ClickTP", Module.Category.MOVEMENT);
/*    */ 
/*    */     
/* 22 */     this.blockOffset = new Setting("BlockOffset", Float.valueOf(1.0F), Float.valueOf(-1.0F), Float.valueOf(1.0F));
/* 23 */     this.spoofs = new Setting("Spoofs", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(40));
/* 24 */     this.ground = new Setting("Ground", Boolean.valueOf(false));
/*    */   }
/*    */   private final Setting<Boolean> ground; private int delay;
/*    */   
/*    */   @EventHandler
/*    */   public void onSync(EventSync e) {
/* 30 */     if (this.delay >= 0) {
/* 31 */       this.delay--;
/*    */     }
/* 33 */     if (mc.field_1690.field_1871.method_1434() && this.delay < 0) {
/* 34 */       class_239 ray = mc.field_1724.method_5745(256.0D, Render3DEngine.getTickDelta(), false);
/* 35 */       if (ray instanceof class_3965) { class_3965 bhr = (class_3965)ray; if (!mc.field_1687.method_22347(bhr.method_17777())) {
/* 36 */           class_243 pos = bhr.method_17777().method_46558();
/* 37 */           for (int i = 0; i < ((Integer)this.spoofs.getValue()).intValue(); i++)
/* 38 */             sendPacket((class_2596)new class_2828.class_2829(pos.method_10216(), pos.method_10214() + ((Float)this.blockOffset.getValue()).floatValue(), pos.method_10215(), ((Boolean)this.ground.getValue()).booleanValue())); 
/* 39 */           mc.field_1724.method_5814(pos.method_10216(), pos.method_10214() + ((Float)this.blockOffset.getValue()).floatValue(), pos.method_10215());
/* 40 */           this.delay = 5;
/*    */         }  }
/*    */     
/*    */     } 
/*    */   }
/*    */   
/*    */   public void onRender3D(class_4587 stack) {
/* 47 */     class_239 ray = mc.field_1724.method_5745(256.0D, Render3DEngine.getTickDelta(), false);
/* 48 */     if (ray instanceof class_3965) { class_3965 bhr = (class_3965)ray; if (!mc.field_1687.method_22347(bhr.method_17777())) {
/* 49 */         class_2338 pos = bhr.method_17777();
/* 50 */         Render3DEngine.OUTLINE_QUEUE.add(new Render3DEngine.OutlineAction(new class_238(pos), HudEditor.getColor(1), 1.0F));
/*    */       }  }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\movement\ClickTP.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */