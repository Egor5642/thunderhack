/*     */ package thunder.hack.gui.windows.impl;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.class_1074;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_1792;
/*     */ import net.minecraft.class_2248;
/*     */ import net.minecraft.class_286;
/*     */ import net.minecraft.class_287;
/*     */ import net.minecraft.class_289;
/*     */ import net.minecraft.class_290;
/*     */ import net.minecraft.class_293;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_3544;
/*     */ import net.minecraft.class_3675;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_757;
/*     */ import net.minecraft.class_7923;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.ClientSettings;
/*     */ import thunder.hack.gui.clickui.ClickGUI;
/*     */ import thunder.hack.gui.clickui.impl.SliderElement;
/*     */ import thunder.hack.gui.font.FontRenderers;
/*     */ import thunder.hack.gui.windows.WindowBase;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.setting.impl.ItemSelectSetting;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ 
/*     */ public class ItemSelectWindow extends WindowBase {
/*     */   private Setting<ItemSelectSetting> itemSetting;
/*  33 */   private ArrayList<ItemPlate> itemPlates = new ArrayList<>();
/*  34 */   private ArrayList<ItemPlate> allItems = new ArrayList<>();
/*     */   private boolean allTab = true;
/*     */   private boolean listening = false;
/*  37 */   private String search = "Search";
/*     */   
/*     */   public ItemSelectWindow(Setting<ItemSelectSetting> itemSetting) {
/*  40 */     this(Module.mc.method_22683().method_4486() / 2.0F - 100.0F, Module.mc.method_22683().method_4502() / 2.0F - 150.0F, 200.0F, 300.0F, itemSetting);
/*     */   }
/*     */   
/*     */   public ItemSelectWindow(float x, float y, float width, float height, Setting<ItemSelectSetting> itemSetting) {
/*  44 */     super(x, y, width, height, "Items / " + String.valueOf(class_124.field_1080) + itemSetting.getModule().getName(), null, null);
/*  45 */     this.itemSetting = itemSetting;
/*  46 */     refreshItemPlates();
/*     */     
/*  48 */     int id1 = 0;
/*  49 */     for (class_2248 block : class_7923.field_41175) {
/*  50 */       this.allItems.add(new ItemPlate(id1, (id1 * 20), block.method_8389(), block.method_9539()));
/*  51 */       id1++;
/*     */     } 
/*     */     
/*  54 */     for (class_1792 item : class_7923.field_41178) {
/*  55 */       this.allItems.add(new ItemPlate(id1, (id1 * 20), item, item.method_7876()));
/*  56 */       id1++;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(class_332 context, int mouseX, int mouseY) {
/*  62 */     super.render(context, mouseX, mouseY);
/*  63 */     boolean hover1 = Render2DEngine.isHovered(mouseX, mouseY, (getX() + getWidth() - 90.0F), (getY() + 3.0F), 70.0D, 10.0D);
/*     */     
/*  65 */     Render2DEngine.drawRect(context.method_51448(), getX() + getWidth() - 90.0F, getY() + 3.0F, 70.0F, 10.0F, hover1 ? new Color(-981236861, true) : new Color(-984131753, true));
/*  66 */     FontRenderers.sf_medium_mini.drawString(context.method_51448(), this.search, (getX() + getWidth() - 86.0F), (getY() + 7.0F), (new Color(14013909)).getRGB());
/*     */     
/*  68 */     RenderSystem.setShader(class_757::method_34540);
/*     */     
/*  70 */     int tabColor1 = this.allTab ? (new Color(14013909)).getRGB() : Color.GRAY.getRGB();
/*  71 */     int tabColor2 = this.allTab ? Color.GRAY.getRGB() : (new Color(12434877)).getRGB();
/*  72 */     class_287 bufferBuilder = class_289.method_1348().method_60827(class_293.class_5596.field_29345, class_290.field_1576);
/*  73 */     bufferBuilder.method_22912(getX() + 1.5F, getY() + 29.0F, 0.0F).method_39415(Color.DARK_GRAY.getRGB());
/*  74 */     bufferBuilder.method_22912(getX() + 8.0F, getY() + 29.0F, 0.0F).method_39415(tabColor1);
/*  75 */     bufferBuilder.method_22912(getX() + 8.0F, getY() + 19.0F, 0.0F).method_39415(tabColor1);
/*  76 */     bufferBuilder.method_22912(getX() + 48.0F, getY() + 19.0F, 0.0F).method_39415(tabColor1);
/*  77 */     bufferBuilder.method_22912(getX() + 54.0F, getY() + 29.0F, 0.0F).method_39415(tabColor1);
/*  78 */     bufferBuilder.method_22912(getX() + 52.0F, getY() + 25.0F, 0.0F).method_39415(tabColor2);
/*  79 */     bufferBuilder.method_22912(getX() + 52.0F, getY() + 19.0F, 0.0F).method_39415(tabColor2);
/*  80 */     bufferBuilder.method_22912(getX() + 92.0F, getY() + 19.0F, 0.0F).method_39415(tabColor2);
/*  81 */     bufferBuilder.method_22912(getX() + 100.0F, getY() + 29.0F, 0.0F).method_39415(Color.GRAY.getRGB());
/*  82 */     bufferBuilder.method_22912(getX() + getWidth() - 1.0F, getY() + 29.0F, 0.0F).method_39415(Color.DARK_GRAY.getRGB());
/*  83 */     class_286.method_43433(bufferBuilder.method_60800());
/*     */     
/*  85 */     FontRenderers.sf_medium_mini.drawString(context.method_51448(), "All", (getX() + 25.0F), (getY() + 25.0F), tabColor1);
/*  86 */     FontRenderers.sf_medium_mini.drawString(context.method_51448(), "Selected", (getX() + 60.0F), (getY() + 25.0F), tabColor2);
/*     */     
/*  88 */     if (!this.allTab && this.itemPlates.isEmpty()) {
/*  89 */       FontRenderers.sf_medium.drawCenteredString(context.method_51448(), ClientSettings.isRu() ? "Тут пока пусто" : "It's empty here yet", (
/*  90 */           getX() + getWidth() / 2.0F), (getY() + getHeight() / 2.0F), (new Color(12434877)).getRGB());
/*     */     }
/*     */     
/*  93 */     Render2DEngine.addWindow(context.method_51448(), getX(), getY() + 30.0F, getX() + getWidth(), getY() + getHeight() - 1.0F, 1.0D);
/*     */     
/*  95 */     for (ItemPlate itemPlate : this.allTab ? this.allItems : this.itemPlates) {
/*  96 */       if (itemPlate.offset + getY() + 25.0F + getScrollOffset() > getY() + getHeight() || itemPlate.offset + getScrollOffset() + getY() + 10.0F < getY()) {
/*     */         continue;
/*     */       }
/*  99 */       context.method_51448().method_22903();
/* 100 */       context.method_51448().method_46416(getX() + 6.0F, itemPlate.offset + getY() + 32.0F + getScrollOffset(), 0.0F);
/* 101 */       context.method_51427(itemPlate.item().method_7854(), 0, 0);
/* 102 */       context.method_51448().method_22909();
/*     */       
/* 104 */       FontRenderers.sf_medium.drawString(context.method_51448(), class_1074.method_4662(itemPlate.key(), new Object[0]), (getX() + 26.0F), (itemPlate.offset + getY() + 38.0F + getScrollOffset()), (new Color(12434877)).getRGB());
/*     */       
/* 106 */       boolean hover2 = Render2DEngine.isHovered(mouseX, mouseY, (getX() + getWidth() - 20.0F), (itemPlate.offset + getY() + 35.0F + getScrollOffset()), 11.0D, 11.0D);
/*     */       
/* 108 */       Render2DEngine.drawRect(context.method_51448(), getX() + getWidth() - 20.0F, itemPlate.offset + getY() + 35.0F + getScrollOffset(), 11.0F, 11.0F, 
/* 109 */           hover2 ? new Color(-981828998, true) : new Color(-984131753, true));
/*     */       
/* 111 */       boolean selected = this.itemPlates.stream().anyMatch(sI -> Objects.equals(sI.key, itemPlate.key));
/*     */       
/* 113 */       if (this.allTab && !selected) {
/* 114 */         FontRenderers.categories.drawString(context.method_51448(), "+", (getX() + getWidth() - 17.0F), (itemPlate.offset + getY() + 39.0F + getScrollOffset()), -1); continue;
/*     */       } 
/* 116 */       FontRenderers.icons.drawString(context.method_51448(), "w", (getX() + getWidth() - 19.5F), (itemPlate.offset + getY() + 39.0F + getScrollOffset()), -1);
/*     */     } 
/*     */     
/* 119 */     setMaxElementsHeight(((this.allTab ? this.allItems : this.itemPlates).size() * 20));
/* 120 */     Render2DEngine.popWindow();
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseClicked(double mouseX, double mouseY, int button) {
/* 125 */     super.mouseClicked(mouseX, mouseY, button);
/*     */     
/* 127 */     if (Render2DEngine.isHovered(mouseX, mouseY, (getX() + 8.0F), (getY() + 19.0F), 52.0D, 19.0D)) {
/* 128 */       this.allTab = true;
/* 129 */       resetScroll();
/* 130 */       Managers.SOUND.playBoolean();
/*     */     } 
/*     */     
/* 133 */     if (Render2DEngine.isHovered(mouseX, mouseY, (getX() + 54.0F), (getY() + 19.0F), 70.0D, 19.0D)) {
/* 134 */       this.allTab = false;
/* 135 */       resetScroll();
/* 136 */       Managers.SOUND.playBoolean();
/*     */     } 
/*     */     
/* 139 */     if (Render2DEngine.isHovered(mouseX, mouseY, (getX() + getWidth() - 90.0F), (getY() + 3.0F), 70.0D, 10.0D)) {
/* 140 */       this.listening = true;
/* 141 */       this.search = "";
/*     */     } 
/*     */     
/* 144 */     if (Render2DEngine.isHovered(mouseX, mouseY, (getX() + getWidth() - 15.0F), (getY() + 3.0F), 10.0D, 10.0D)) {
/* 145 */       Module.mc.method_1507((class_437)ClickGUI.getClickGui());
/*     */     }
/* 147 */     ArrayList<ItemPlate> copy = Lists.newArrayList(this.allTab ? this.allItems : this.itemPlates);
/* 148 */     for (ItemPlate itemPlate : copy) {
/* 149 */       if ((int)(itemPlate.offset + getY() + 50.0F) + getScrollOffset() > getY() + getHeight()) {
/*     */         continue;
/*     */       }
/* 152 */       String name = itemPlate.key().replace("item.minecraft.", "").replace("block.minecraft.", "");
/*     */       
/* 154 */       if (Render2DEngine.isHovered(mouseX, mouseY, (getX() + getWidth() - 20.0F), (itemPlate.offset + getY() + 35.0F + getScrollOffset()), 10.0D, 10.0D)) {
/* 155 */         boolean selected = this.itemPlates.stream().anyMatch(sI -> Objects.equals(sI.key(), itemPlate.key));
/*     */         
/* 157 */         if (this.allTab && !selected) {
/* 158 */           if (((ItemSelectSetting)this.itemSetting.getValue()).getItemsById().contains(name))
/*     */             continue; 
/* 160 */           ((ItemSelectSetting)this.itemSetting.getValue()).getItemsById().add(name);
/* 161 */           refreshItemPlates();
/*     */         } else {
/* 163 */           ((ItemSelectSetting)this.itemSetting.getValue()).getItemsById().remove(name);
/* 164 */           refreshItemPlates();
/*     */         } 
/* 166 */         Managers.SOUND.playScroll();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void keyPressed(int keyCode, int scanCode, int modifiers) {
/* 173 */     if (keyCode == 70 && (class_3675.method_15987(Module.mc.method_22683().method_4490(), 341) || class_3675.method_15987(Module.mc.method_22683().method_4490(), 345))) {
/* 174 */       this.listening = !this.listening;
/*     */       
/*     */       return;
/*     */     } 
/* 178 */     if (this.listening) {
/* 179 */       switch (keyCode) {
/*     */         case 256:
/* 181 */           this.listening = false;
/* 182 */           this.search = "Search";
/* 183 */           refreshAllItems();
/*     */           break;
/*     */         
/*     */         case 259:
/* 187 */           this.search = SliderElement.removeLastChar(this.search);
/* 188 */           refreshAllItems();
/*     */           
/* 190 */           if (Objects.equals(this.search, "")) {
/* 191 */             this.listening = false;
/* 192 */             this.search = "Search";
/*     */           } 
/*     */           break;
/*     */         case 32:
/* 196 */           this.search += " ";
/*     */           break;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void charTyped(char key, int keyCode) {
/* 203 */     if (class_3544.method_57175(key) && this.listening) {
/* 204 */       this.search += this.search;
/* 205 */       refreshAllItems();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void refreshItemPlates() {
/* 211 */     this.itemPlates.clear();
/*     */     
/* 213 */     int id = 0;
/* 214 */     for (class_2248 block : class_7923.field_41175) {
/* 215 */       if (((ItemSelectSetting)this.itemSetting.getValue()).getItemsById().contains(block.method_9539().replace("block.minecraft.", ""))) {
/* 216 */         this.itemPlates.add(new ItemPlate(id, (id * 20), block.method_8389(), block.method_9539()));
/* 217 */         id++;
/*     */       } 
/*     */     } 
/*     */     
/* 221 */     for (class_1792 item : class_7923.field_41178) {
/* 222 */       if (((ItemSelectSetting)this.itemSetting.getValue()).getItemsById().contains(item.method_7876().replace("item.minecraft.", ""))) {
/* 223 */         this.itemPlates.add(new ItemPlate(id, (id * 20), item, item.method_7876()));
/* 224 */         id++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private void refreshAllItems() {
/* 229 */     this.allItems.clear();
/* 230 */     resetScroll();
/* 231 */     int id1 = 0;
/* 232 */     for (class_2248 block : class_7923.field_41175) {
/* 233 */       if (this.search.equals("Search") || this.search.isEmpty() || block.method_9539().contains(this.search) || class_1074.method_4662(block.method_9539(), new Object[0]).toLowerCase().contains(this.search.toLowerCase())) {
/* 234 */         this.allItems.add(new ItemPlate(id1, (id1 * 20), block.method_8389(), block.method_9539()));
/* 235 */         id1++;
/*     */       } 
/*     */     } 
/*     */     
/* 239 */     for (class_1792 item : class_7923.field_41178) {
/* 240 */       if (this.search.equals("Search") || this.search.isEmpty() || item.method_7876().contains(this.search) || item.method_7848().getString().toLowerCase().contains(this.search.toLowerCase())) {
/* 241 */         this.allItems.add(new ItemPlate(id1, (id1 * 20), item, item.method_7876()));
/* 242 */         id1++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private static final class ItemPlate extends Record { private final float id; private final float offset; private final class_1792 item; private final String key;
/* 247 */     private ItemPlate(float id, float offset, class_1792 item, String key) { this.id = id; this.offset = offset; this.item = item; this.key = key; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lthunder/hack/gui/windows/impl/ItemSelectWindow$ItemPlate;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #247	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 247 */       //   0	7	0	this	Lthunder/hack/gui/windows/impl/ItemSelectWindow$ItemPlate; } public float id() { return this.id; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lthunder/hack/gui/windows/impl/ItemSelectWindow$ItemPlate;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #247	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lthunder/hack/gui/windows/impl/ItemSelectWindow$ItemPlate; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lthunder/hack/gui/windows/impl/ItemSelectWindow$ItemPlate;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #247	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lthunder/hack/gui/windows/impl/ItemSelectWindow$ItemPlate;
/* 247 */       //   0	8	1	o	Ljava/lang/Object; } public float offset() { return this.offset; } public class_1792 item() { return this.item; } public String key() { return this.key; }
/*     */      }
/*     */ 
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\windows\impl\ItemSelectWindow.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */