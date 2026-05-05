/*    */ package thunder.hack.setting.impl;
/*    */ 
/*    */ public class SettingGroup {
/*    */   private boolean extended;
/*    */   private final int hierarchy;
/*    */   
/*    */   public SettingGroup(boolean extended, int hierarchy) {
/*  8 */     this.extended = extended;
/*  9 */     this.hierarchy = hierarchy;
/*    */   }
/*    */   
/*    */   public boolean isExtended() {
/* 13 */     return this.extended;
/*    */   }
/*    */   
/*    */   public int getHierarchy() {
/* 17 */     return this.hierarchy;
/*    */   }
/*    */   
/*    */   public void setExtended(boolean extended) {
/* 21 */     this.extended = extended;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\setting\impl\SettingGroup.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */