/*    */ package thunder.hack.utility.player;
/*    */ 
/*    */ public final class SearchInvResult extends Record {
/*    */   private final int slot;
/*    */   private final boolean found;
/*    */   private final class_1799 stack;
/*    */   
/*  8 */   public SearchInvResult(int slot, boolean found, class_1799 stack) { this.slot = slot; this.found = found; this.stack = stack; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lthunder/hack/utility/player/SearchInvResult;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #8	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*  8 */     //   0	7	0	this	Lthunder/hack/utility/player/SearchInvResult; } public int slot() { return this.slot; } public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lthunder/hack/utility/player/SearchInvResult;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #8	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lthunder/hack/utility/player/SearchInvResult; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lthunder/hack/utility/player/SearchInvResult;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #8	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lthunder/hack/utility/player/SearchInvResult;
/*  8 */     //   0	8	1	o	Ljava/lang/Object; } public boolean found() { return this.found; } public class_1799 stack() { return this.stack; }
/*  9 */    private static final SearchInvResult NOT_FOUND_RESULT = new SearchInvResult(-1, false, null);
/*    */   
/*    */   public static SearchInvResult notFound() {
/* 12 */     return NOT_FOUND_RESULT;
/*    */   }
/*    */   @NotNull
/*    */   public static SearchInvResult inOffhand(class_1799 stack) {
/* 16 */     return new SearchInvResult(999, true, stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isHolding() {
/* 21 */     if (Module.mc.field_1724 == null) return false;
/*    */     
/* 23 */     return ((Module.mc.field_1724.method_31548()).field_7545 == this.slot);
/*    */   }
/*    */   
/*    */   public boolean isInHotBar() {
/* 27 */     return (this.slot < 9);
/*    */   }
/*    */   
/*    */   public void switchTo() {
/* 31 */     if (this.found && isInHotBar())
/* 32 */       InventoryUtility.switchTo(this.slot); 
/*    */   }
/*    */   
/*    */   public void switchToSilent() {
/* 36 */     if (this.found && isInHotBar())
/* 37 */       InventoryUtility.switchToSilent(this.slot); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\player\SearchInvResult.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */