/*    */ package thunder.hack.setting.impl;
/*    */ 
/*    */ public class BooleanSettingGroup {
/*    */   private boolean enabled;
/*    */   
/*    */   public BooleanSettingGroup(boolean enabled) {
/*  7 */     this.enabled = enabled;
/*  8 */     this.extended = false;
/*    */   }
/*    */   private boolean extended;
/*    */   public boolean isExtended() {
/* 12 */     return this.extended;
/*    */   }
/*    */   
/*    */   public void setExtended(boolean extended) {
/* 16 */     this.extended = extended;
/*    */   }
/*    */   
/*    */   public boolean isEnabled() {
/* 20 */     return this.enabled;
/*    */   }
/*    */   
/*    */   public void setEnabled(boolean enabled) {
/* 24 */     this.enabled = enabled;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\setting\impl\BooleanSettingGroup.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */