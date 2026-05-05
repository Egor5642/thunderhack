/*    */ package thunder.hack.utility.render;
/*    */ 
/*    */ public class TextUtil {
/*    */   private final String[] words;
/*  5 */   private String currentWord = "_"; private String currentResult = "_";
/*    */   private int arrayIndex;
/*    */   private int currentIndex;
/*    */   
/*    */   public TextUtil(String... words) {
/* 10 */     this.words = words;
/*    */   }
/*    */   private int ticks; private boolean filip = false;
/*    */   
/*    */   public String toString() {
/* 15 */     return this.currentResult;
/*    */   }
/*    */   
/*    */   public void tick() {
/* 19 */     this.ticks++;
/* 20 */     if (this.ticks % (this.filip ? 2 : 1) != 0) {
/*    */       return;
/*    */     }
/* 23 */     if (!this.currentWord.isEmpty())
/* 24 */       this.currentResult = this.currentWord.substring(0, this.currentWord.length() - Math.max(this.currentIndex, 0)); 
/* 25 */     if (this.currentIndex >= this.currentWord.length()) {
/* 26 */       this.filip = true;
/* 27 */       this.arrayIndex++;
/* 28 */       if (this.arrayIndex >= this.words.length) this.arrayIndex = 0; 
/* 29 */       this.currentWord = this.words[this.arrayIndex];
/* 30 */       this.currentIndex = this.currentWord.length();
/*    */     } 
/* 32 */     if (!this.filip) { this.currentIndex++; }
/* 33 */     else { this.currentIndex--; }
/* 34 */      if (this.currentIndex <= -20) {
/* 35 */       this.filip = false;
/* 36 */       this.currentIndex = 0;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\TextUtil.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */