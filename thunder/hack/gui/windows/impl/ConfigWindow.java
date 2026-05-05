/*     */ package thunder.hack.gui.windows.impl;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_3544;
/*     */ import net.minecraft.class_3675;
/*     */ import net.minecraft.class_437;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.ClientSettings;
/*     */ import thunder.hack.features.modules.client.HudEditor;
/*     */ import thunder.hack.gui.clickui.ClickGUI;
/*     */ import thunder.hack.gui.clickui.impl.SliderElement;
/*     */ import thunder.hack.gui.font.FontRenderers;
/*     */ import thunder.hack.gui.windows.WindowBase;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.setting.impl.ColorSetting;
/*     */ import thunder.hack.setting.impl.PositionSetting;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ import thunder.hack.utility.render.TextureStorage;
/*     */ 
/*     */ public class ConfigWindow
/*     */   extends WindowBase
/*     */ {
/*     */   private static ConfigWindow instance;
/*  29 */   private ArrayList<ConfigPlate> configPlates = new ArrayList<>();
/*  30 */   private int listeningId = -1;
/*  31 */   private String search = "Search", addName = "Name";
/*     */   
/*     */   public ConfigWindow(float x, float y, float width, float height, Setting<PositionSetting> position) {
/*  34 */     super(x, y, width, height, "Config", position, TextureStorage.configIcon);
/*  35 */     refresh();
/*     */   }
/*     */   
/*     */   public static ConfigWindow get(float x, float y, Setting<PositionSetting> position) {
/*  39 */     if (instance == null)
/*  40 */       instance = new ConfigWindow(x, y, 200.0F, 180.0F, position); 
/*  41 */     instance.refresh();
/*  42 */     return instance;
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(class_332 context, int mouseX, int mouseY) {
/*  47 */     super.render(context, mouseX, mouseY);
/*     */     
/*  49 */     Color color = new Color(-986500301, true);
/*  50 */     Color color2 = new Color(-983868581, true);
/*  51 */     Color hoveredColor = new Color(-985052855, true);
/*  52 */     int textColor = (new Color(12434877)).getRGB();
/*     */     
/*  54 */     boolean hover1 = Render2DEngine.isHovered(mouseX, mouseY, (getX() + getWidth() - 90.0F), (getY() + 3.0F), 70.0D, 10.0D);
/*     */     
/*  56 */     Render2DEngine.drawRectWithOutline(context.method_51448(), getX() + getWidth() - 90.0F, getY() + 3.0F, 70.0F, 10.0F, hover1 ? hoveredColor : color, color2);
/*  57 */     FontRenderers.sf_medium_mini.drawString(context.method_51448(), this.search, (getX() + getWidth() - 86.0F), (getY() + 7.0F), (new Color(14013909)).getRGB());
/*     */     
/*  59 */     if (this.configPlates.isEmpty()) {
/*  60 */       FontRenderers.sf_medium.drawCenteredString(context.method_51448(), ClientSettings.isRu() ? "Тут пока пусто" : "It's empty here yet", (
/*  61 */           getX() + getWidth() / 2.0F), (getY() + getHeight() / 2.0F), (new Color(12434877)).getRGB());
/*     */     }
/*     */     
/*  64 */     String blink = (System.currentTimeMillis() / 240L % 2L == 0L) ? "" : "   <<<<";
/*  65 */     String blink2 = (System.currentTimeMillis() / 240L % 2L == 0L) ? "" : "l";
/*     */ 
/*     */ 
/*     */     
/*  69 */     boolean hover2 = Render2DEngine.isHovered(mouseX, mouseY, (getX() + 11.0F), (getY() + 19.0F), (getWidth() - 28.0F), 11.0D);
/*  70 */     Render2DEngine.drawRectWithOutline(context.method_51448(), getX() + 11.0F, getY() + 19.0F, getWidth() - 28.0F, 11.0F, hover2 ? hoveredColor : color, color2);
/*  71 */     FontRenderers.sf_medium.drawString(context.method_51448(), this.addName + this.addName, (
/*  72 */         getX() + 13.0F), (getY() + 23.0F), (new Color(12434877)).getRGB());
/*     */ 
/*     */     
/*  75 */     boolean hover5 = Render2DEngine.isHovered(mouseX, mouseY, (getX() + getWidth() - 15.0F), (getY() + 19.0F), 11.0D, 11.0D);
/*  76 */     Render2DEngine.drawRectWithOutline(context.method_51448(), getX() + getWidth() - 15.0F, getY() + 19.0F, 11.0F, 11.0F, hover5 ? hoveredColor : color, color2);
/*  77 */     FontRenderers.categories.drawString(context.method_51448(), "+", (getX() + getWidth() - 12.0F), (getY() + 23.0F), -1);
/*     */ 
/*     */     
/*  80 */     Render2DEngine.horizontalGradient(context.method_51448(), getX() + 2.0F, getY() + 33.0F, getX() + 2.0F + getWidth() / 2.0F - 2.0F, getY() + 33.5F, Render2DEngine.injectAlpha(((ColorSetting)HudEditor.textColor.getValue()).getColorObject(), 0), ((ColorSetting)HudEditor.textColor.getValue()).getColorObject());
/*  81 */     Render2DEngine.horizontalGradient(context.method_51448(), getX() + 2.0F + getWidth() / 2.0F - 2.0F, getY() + 33.0F, getX() + 2.0F + getWidth() - 4.0F, getY() + 33.5F, ((ColorSetting)HudEditor.textColor.getValue()).getColorObject(), Render2DEngine.injectAlpha(((ColorSetting)HudEditor.textColor.getValue()).getColorObject(), 0));
/*     */     
/*  83 */     Render2DEngine.addWindow(context.method_51448(), getX(), getY() + 38.0F, getX() + getWidth(), getY() + getHeight() - 1.0F, 1.0D);
/*  84 */     int id = 0;
/*  85 */     for (ConfigPlate configPlate : this.configPlates) {
/*  86 */       id++;
/*  87 */       if ((int)(configPlate.offset + getY() + 50.0F) + getScrollOffset() > getY() + getHeight() || configPlate.offset + getScrollOffset() + getY() < getY()) {
/*     */         continue;
/*     */       }
/*     */       
/*  91 */       Render2DEngine.drawRectWithOutline(context.method_51448(), getX() + 11.0F, configPlate.offset + getY() + 36.0F + getScrollOffset(), getWidth() - 52.0F, 11.0F, color, color2);
/*  92 */       FontRenderers.sf_medium.drawString(context.method_51448(), configPlate.name() + configPlate.name(), (
/*  93 */           getX() + 13.0F), (configPlate.offset + getY() + 40.0F + getScrollOffset()), textColor);
/*     */ 
/*     */       
/*  96 */       boolean hover3 = Render2DEngine.isHovered(mouseX, mouseY, (getX() + getWidth() - 39.0F), (configPlate.offset + getY() + 36.0F + getScrollOffset()), 22.0D, 11.0D);
/*  97 */       Render2DEngine.drawRectWithOutline(context.method_51448(), getX() + getWidth() - 39.0F, configPlate.offset + getY() + 36.0F + getScrollOffset(), 22.0F, 11.0F, hover3 ? hoveredColor : color, color2);
/*  98 */       FontRenderers.sf_medium.drawString(context.method_51448(), "Load", (getX() + getWidth() - 37.0F), (configPlate.offset + getY() + 40.0F + getScrollOffset()), (new Color(12434877)).getRGB());
/*     */ 
/*     */       
/* 101 */       boolean bool1 = Render2DEngine.isHovered(mouseX, mouseY, (getX() + getWidth() - 15.0F), (configPlate.offset + getY() + 36.0F + getScrollOffset()), 11.0D, 11.0D);
/* 102 */       Render2DEngine.drawRectWithOutline(context.method_51448(), getX() + getWidth() - 15.0F, configPlate.offset + getY() + 36.0F + getScrollOffset(), 11.0F, 11.0F, bool1 ? hoveredColor : color, color2);
/* 103 */       FontRenderers.icons.drawString(context.method_51448(), "w", (getX() + getWidth() - 15.0F), (configPlate.offset + getY() + 40.0F + getScrollOffset()), -1);
/* 104 */       FontRenderers.sf_medium_mini.drawString(context.method_51448(), "" + id + ".", (getX() + 3.0F), (configPlate.offset + getY() + 41.0F + getScrollOffset()), textColor);
/*     */     } 
/* 106 */     setMaxElementsHeight((this.configPlates.size() * 20));
/* 107 */     Render2DEngine.popWindow();
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseClicked(double mouseX, double mouseY, int button) {
/* 112 */     super.mouseClicked(mouseX, mouseY, button);
/*     */     
/* 114 */     if (Render2DEngine.isHovered(mouseX, mouseY, (getX() + getWidth() - 90.0F), (getY() + 3.0F), 70.0D, 10.0D)) {
/* 115 */       this.listeningId = -2;
/* 116 */       this.search = "";
/*     */     } 
/*     */     
/* 119 */     if (Render2DEngine.isHovered(mouseX, mouseY, (getX() + getWidth() - 15.0F), (getY() + 3.0F), 10.0D, 10.0D)) {
/* 120 */       Module.mc.method_1507((class_437)ClickGUI.getClickGui());
/*     */     }
/* 122 */     boolean hoveringName = Render2DEngine.isHovered(mouseX, mouseY, (getX() + 11.0F), (getY() + 19.0F), (getWidth() - 28.0F), 11.0D);
/* 123 */     boolean hoveringAdd = Render2DEngine.isHovered(mouseX, mouseY, (getX() + getWidth() - 15.0F), (getY() + 19.0F), 11.0D, 11.0D);
/*     */     
/* 125 */     if (hoveringName) {
/* 126 */       this.addName = "";
/* 127 */       this.listeningId = -3;
/*     */     } 
/*     */     
/* 130 */     if (hoveringAdd && !this.addName.isEmpty()) {
/* 131 */       Managers.CONFIG.save(this.addName);
/* 132 */       this.addName = "";
/* 133 */       refresh();
/*     */     } 
/*     */     
/* 136 */     ArrayList<ConfigPlate> copy = Lists.newArrayList(this.configPlates);
/* 137 */     for (ConfigPlate configPlate : copy) {
/* 138 */       if ((int)(configPlate.offset + getY() + 50.0F) + getScrollOffset() > getY() + getHeight()) {
/*     */         continue;
/*     */       }
/* 141 */       boolean hoveringRemove = Render2DEngine.isHovered(mouseX, mouseY, (getX() + getWidth() - 15.0F), (configPlate.offset + getY() + 36.0F + getScrollOffset()), 11.0D, 11.0D);
/* 142 */       boolean hoverLoad = Render2DEngine.isHovered(mouseX, mouseY, (getX() + getWidth() - 39.0F), (configPlate.offset + getY() + 36.0F + getScrollOffset()), 22.0D, 11.0D);
/*     */       
/* 144 */       if (hoverLoad) {
/* 145 */         Managers.CONFIG.load(configPlate.name());
/*     */       }
/* 147 */       if (hoveringRemove) {
/* 148 */         Managers.CONFIG.delete(configPlate.name());
/* 149 */         refresh();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void keyPressed(int keyCode, int scanCode, int modifiers) {
/* 156 */     if (keyCode == 70 && (class_3675.method_15987(Module.mc.method_22683().method_4490(), 341) || class_3675.method_15987(Module.mc.method_22683().method_4490(), 345))) {
/* 157 */       this.listeningId = -2;
/*     */       
/*     */       return;
/*     */     } 
/* 161 */     if (this.listeningId != -1) {
/* 162 */       switch (keyCode) {
/*     */         case 257:
/* 164 */           if (this.listeningId != -2) {
/* 165 */             this.listeningId = -1;
/*     */           }
/*     */           break;
/*     */         case 256:
/* 169 */           if (this.listeningId == -2)
/* 170 */             this.search = "Search"; 
/* 171 */           this.listeningId = -1;
/* 172 */           refresh();
/*     */           break;
/*     */         
/*     */         case 259:
/* 176 */           if (this.listeningId == -2) {
/* 177 */             this.search = SliderElement.removeLastChar(this.search);
/* 178 */             refresh();
/* 179 */             if (Objects.equals(this.search, "")) {
/* 180 */               this.listeningId = -1;
/* 181 */               this.search = "Search";
/*     */             } 
/*     */             
/*     */             return;
/*     */           } 
/* 186 */           if (this.listeningId == -3) {
/* 187 */             this.addName = SliderElement.removeLastChar(this.addName);
/*     */           }
/*     */           break;
/*     */         case 32:
/* 191 */           if (this.listeningId == -2) {
/* 192 */             this.search += " ";
/*     */           }
/*     */           break;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void charTyped(char key, int keyCode) {
/* 200 */     if (class_3544.method_57175(key) && this.listeningId != -1) {
/* 201 */       if (this.listeningId == -2) {
/* 202 */         this.search += this.search;
/*     */       }
/* 204 */       if (this.listeningId == -3) {
/* 205 */         this.addName += this.addName;
/*     */       }
/*     */       
/* 208 */       refresh();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void refresh() {
/* 213 */     resetScroll();
/* 214 */     this.configPlates.clear();
/* 215 */     int id1 = 0;
/* 216 */     for (String s : Managers.CONFIG.getConfigList()) {
/* 217 */       if (this.search.equals("Search") || this.search.isEmpty() || s.contains(this.search)) {
/* 218 */         this.configPlates.add(new ConfigPlate(id1, (id1 * 20 + 8), s));
/* 219 */         id1++;
/*     */       } 
/*     */     } 
/*     */   } private static final class ConfigPlate extends Record { private final int id; private final float offset; private final String name;
/* 223 */     private ConfigPlate(int id, float offset, String name) { this.id = id; this.offset = offset; this.name = name; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lthunder/hack/gui/windows/impl/ConfigWindow$ConfigPlate;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #223	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 223 */       //   0	7	0	this	Lthunder/hack/gui/windows/impl/ConfigWindow$ConfigPlate; } public int id() { return this.id; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lthunder/hack/gui/windows/impl/ConfigWindow$ConfigPlate;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #223	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lthunder/hack/gui/windows/impl/ConfigWindow$ConfigPlate; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lthunder/hack/gui/windows/impl/ConfigWindow$ConfigPlate;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #223	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lthunder/hack/gui/windows/impl/ConfigWindow$ConfigPlate;
/* 223 */       //   0	8	1	o	Ljava/lang/Object; } public float offset() { return this.offset; } public String name() { return this.name; }
/*     */      }
/*     */ 
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\windows\impl\ConfigWindow.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */