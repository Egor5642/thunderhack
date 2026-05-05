/*    */ package thunder.hack.setting.impl;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.class_1792;
/*    */ import net.minecraft.class_2248;
/*    */ 
/*    */ public class ItemSelectSetting
/*    */ {
/*    */   private List<String> itemsById;
/*    */   
/*    */   public ItemSelectSetting(List<String> itemsById) {
/* 12 */     this.itemsById = itemsById;
/*    */   }
/*    */   
/*    */   public List<String> getItemsById() {
/* 16 */     return this.itemsById;
/*    */   }
/*    */   
/*    */   public void add(String s) {
/* 20 */     this.itemsById.add(s);
/*    */   }
/*    */   
/*    */   public void remove(String s) {
/* 24 */     this.itemsById.remove(s);
/*    */   }
/*    */   
/*    */   public boolean contains(String s) {
/* 28 */     return this.itemsById.contains(s);
/*    */   }
/*    */   
/*    */   public void add(class_2248 b) {
/* 32 */     add(b.method_9539().replace("block.minecraft.", ""));
/*    */   }
/*    */   
/*    */   public void add(class_1792 i) {
/* 36 */     add(i.method_7876().replace("item.minecraft.", ""));
/*    */   }
/*    */   
/*    */   public void remove(class_2248 b) {
/* 40 */     remove(b.method_9539().replace("block.minecraft.", ""));
/*    */   }
/*    */   
/*    */   public void remove(class_1792 i) {
/* 44 */     remove(i.method_7876().replace("item.minecraft.", ""));
/*    */   }
/*    */   
/*    */   public boolean contains(class_2248 b) {
/* 48 */     return contains(b.method_9539().replace("block.minecraft.", ""));
/*    */   }
/*    */   
/*    */   public boolean contains(class_1792 i) {
/* 52 */     return contains(i.method_7876().replace("item.minecraft.", ""));
/*    */   }
/*    */   
/*    */   public void clear() {
/* 56 */     this.itemsById.clear();
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\setting\impl\ItemSelectSetting.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */