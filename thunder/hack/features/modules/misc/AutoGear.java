/*     */ package thunder.hack.features.modules.misc;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_1703;
/*     */ import net.minecraft.class_1735;
/*     */ import net.minecraft.class_1799;
/*     */ import thunder.hack.features.cmd.impl.KitCommand;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.ClientSettings;
/*     */ import thunder.hack.setting.Setting;
/*     */ 
/*     */ public class AutoGear extends Module {
/*     */   public Setting<Integer> actionDelay;
/*     */   public Setting<Integer> clicksPerAction;
/*     */   
/*     */   public AutoGear() {
/*  19 */     super("AutoGear", Module.Category.MISC);
/*     */ 
/*     */     
/*  22 */     this.actionDelay = new Setting("ActionDelay", Integer.valueOf(50), Integer.valueOf(0), Integer.valueOf(20));
/*  23 */     this.clicksPerAction = new Setting("Click/Action", Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(108));
/*     */     
/*  25 */     this.expectedInv = new HashMap<>();
/*  26 */     this.delay = 0;
/*     */   }
/*     */   private HashMap<Integer, String> expectedInv; private int delay;
/*     */   public void onEnable() {
/*  30 */     setup();
/*     */   }
/*     */   
/*     */   public void setup() {
/*  34 */     String selectedKit = KitCommand.getSelectedKit();
/*     */     
/*  36 */     if (selectedKit.isEmpty()) {
/*  37 */       disable(ClientSettings.isRu() ? "Не выбран кит! Воспользуйся командой kit" : "No kit is selected! Use the kit command");
/*     */       
/*     */       return;
/*     */     } 
/*  41 */     sendMessage(ClientSettings.isRu() ? ("Выбран кит => " + String.valueOf(class_124.field_1075) + selectedKit) : ("Selected kit -> " + String.valueOf(class_124.field_1075) + selectedKit));
/*     */     
/*  43 */     String kitItems = KitCommand.getKitItems(selectedKit);
/*     */     
/*  45 */     if (kitItems.isEmpty() || (kitItems.split(" ")).length != 36) {
/*  46 */       disable(ClientSettings.isRu() ? "Произошла ошибка в конфигурации кита! Создай кит снова" : "There was an error in the kit configuration! Create the kit again");
/*     */       
/*     */       return;
/*     */     } 
/*  50 */     String[] items = kitItems.split(" ");
/*  51 */     this.expectedInv = new HashMap<>();
/*     */     
/*  53 */     for (int i = 0; i < 36; i++) {
/*  54 */       if (!items[i].equals("block.minecraft.air"))
/*  55 */         this.expectedInv.put(Integer.valueOf(i), items[i]); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onUpdate() {
/*  60 */     if (this.delay > 0) {
/*  61 */       this.delay--;
/*     */       
/*     */       return;
/*     */     } 
/*  65 */     if (this.expectedInv.isEmpty()) {
/*  66 */       setup();
/*     */       
/*     */       return;
/*     */     } 
/*  70 */     int actions = 0;
/*     */     
/*  72 */     class_1703 handler = mc.field_1724.field_7512;
/*     */     
/*  74 */     if (handler.field_7761.size() != 63 && handler.field_7761.size() != 90) {
/*     */       return;
/*     */     }
/*  77 */     ArrayList<Integer> clickSequence = buildClickSequence(handler);
/*  78 */     for (Iterator<Integer> iterator = clickSequence.iterator(); iterator.hasNext(); ) { int s = ((Integer)iterator.next()).intValue();
/*  79 */       clickSlot(s);
/*  80 */       actions++;
/*  81 */       if (actions >= ((Integer)this.clicksPerAction.getValue()).intValue())
/*     */         break;  }
/*     */     
/*  84 */     this.delay = ((Integer)this.actionDelay.getValue()).intValue();
/*     */   }
/*     */   
/*     */   private int searchInContainer(String name, boolean lower, class_1703 handler) {
/*  88 */     class_1799 cursorStack = handler.method_34255();
/*     */     
/*  90 */     if (((cursorStack.method_7909() instanceof net.minecraft.class_1812) ? (
/*  91 */       cursorStack.method_7909().method_7876() + cursorStack.method_7909().method_7876()) : 
/*  92 */       cursorStack.method_7909().method_7876()).equals(name)) {
/*  93 */       return -2;
/*     */     }
/*  95 */     for (int i = 0; i < (lower ? 26 : 53); i++) {
/*  96 */       class_1799 stack = handler.method_7611(i).method_7677();
/*  97 */       if (((stack.method_7909() instanceof net.minecraft.class_1812) ? (
/*  98 */         stack.method_7909().method_7876() + stack.method_7909().method_7876()) : 
/*  99 */         stack.method_7909().method_7876()).equals(name))
/* 100 */         return i; 
/*     */     } 
/* 102 */     return -1;
/*     */   }
/*     */   
/*     */   private ArrayList<Integer> buildClickSequence(class_1703 handler) {
/* 106 */     ArrayList<Integer> clicks = new ArrayList<>();
/* 107 */     for (Iterator<Integer> iterator = this.expectedInv.keySet().iterator(); iterator.hasNext(); ) { int s = ((Integer)iterator.next()).intValue();
/* 108 */       int lower = (s < 9) ? (s + 54) : (s + 18);
/* 109 */       int upper = (s < 9) ? (s + 81) : (s + 45);
/*     */       
/* 111 */       class_1799 itemInslot = ((class_1735)handler.field_7761.get((handler.field_7761.size() == 63) ? lower : upper)).method_7677();
/*     */       
/* 113 */       if (((itemInslot.method_7909() instanceof net.minecraft.class_1812) ? (
/* 114 */         itemInslot.method_7909().method_7876() + itemInslot.method_7909().method_7876()) : 
/* 115 */         itemInslot.method_7909().method_7876()).equals(this.expectedInv.get(Integer.valueOf(s)))) {
/*     */         continue;
/*     */       }
/* 118 */       int slot = searchInContainer(this.expectedInv.get(Integer.valueOf(s)), (handler.field_7761.size() == 63), handler);
/*     */       
/* 120 */       if (slot == -2) {
/* 121 */         clicks.add(Integer.valueOf((handler.field_7761.size() == 63) ? lower : upper)); continue;
/* 122 */       }  if (slot != -1) {
/* 123 */         clicks.add(Integer.valueOf(slot));
/* 124 */         clicks.add(Integer.valueOf((handler.field_7761.size() == 63) ? lower : upper));
/* 125 */         clicks.add(Integer.valueOf(slot));
/*     */       }  }
/*     */     
/* 128 */     return clicks;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\misc\AutoGear.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */