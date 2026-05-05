/*     */ package thunder.hack.core.manager.client;
/*     */ 
/*     */ import thunder.hack.core.manager.IManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Macro
/*     */ {
/*     */   private String name;
/*     */   private String text;
/*     */   private int bind;
/*     */   
/*     */   public Macro(String name, String text, int bind) {
/*  76 */     this.name = name;
/*  77 */     this.text = text;
/*  78 */     this.bind = bind;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  82 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  86 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getText() {
/*  90 */     return this.text;
/*     */   }
/*     */   
/*     */   public void setText(String text) {
/*  94 */     this.text = text;
/*     */   }
/*     */   
/*     */   public int getBind() {
/*  98 */     return this.bind;
/*     */   }
/*     */   
/*     */   public void setBind(int bind) {
/* 102 */     this.bind = bind;
/*     */   }
/*     */   
/*     */   public void runMacro() {
/* 106 */     if (IManager.mc.field_1724 == null)
/* 107 */       return;  if (this.text.contains("/")) { IManager.mc.field_1724.field_3944.method_45730(this.text.replace("/", "")); }
/* 108 */     else { IManager.mc.field_1724.field_3944.method_45729(this.text); }
/*     */   
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\core\manager\client\MacroManager$Macro.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */