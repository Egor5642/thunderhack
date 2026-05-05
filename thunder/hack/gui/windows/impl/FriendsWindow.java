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
/*     */ import net.minecraft.class_640;
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
/*     */ public class FriendsWindow
/*     */   extends WindowBase {
/*     */   private static FriendsWindow instance;
/*  29 */   private ArrayList<FriendPlate> friendPlates = new ArrayList<>();
/*  30 */   private int listeningId = -1;
/*  31 */   private String search = "Search", addName = "Name";
/*     */   
/*     */   public FriendsWindow(float x, float y, float width, float height, Setting<PositionSetting> position) {
/*  34 */     super(x, y, width, height, "Friends", position, TextureStorage.playerIcon);
/*  35 */     refresh();
/*     */   }
/*     */   
/*     */   public static FriendsWindow get(float x, float y, Setting<PositionSetting> position) {
/*  39 */     if (instance == null)
/*  40 */       instance = new FriendsWindow(x, y, 200.0F, 180.0F, position); 
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
/*  55 */     Render2DEngine.drawRectWithOutline(context.method_51448(), getX() + getWidth() - 90.0F, getY() + 3.0F, 70.0F, 10.0F, hover1 ? hoveredColor : color, color2);
/*  56 */     FontRenderers.sf_medium_mini.drawString(context.method_51448(), this.search, (getX() + getWidth() - 86.0F), (getY() + 7.0F), (new Color(14013909)).getRGB());
/*     */     
/*  58 */     if (this.friendPlates.isEmpty()) {
/*  59 */       FontRenderers.sf_medium.drawCenteredString(context.method_51448(), ClientSettings.isRu() ? "Тут пока пусто" : "It's empty here yet", (
/*  60 */           getX() + getWidth() / 2.0F), (getY() + getHeight() / 2.0F), (new Color(12434877)).getRGB());
/*     */     }
/*     */     
/*  63 */     String blink2 = (System.currentTimeMillis() / 240L % 2L == 0L) ? "" : "l";
/*     */ 
/*     */ 
/*     */     
/*  67 */     boolean hover2 = Render2DEngine.isHovered(mouseX, mouseY, (getX() + 11.0F), (getY() + 19.0F), (getWidth() - 28.0F), 11.0D);
/*  68 */     Render2DEngine.drawRectWithOutline(context.method_51448(), getX() + 11.0F, getY() + 19.0F, getWidth() - 28.0F, 11.0F, hover2 ? hoveredColor : color, color2);
/*  69 */     FontRenderers.sf_medium.drawString(context.method_51448(), this.addName + this.addName, (
/*  70 */         getX() + 13.0F), (getY() + 23.0F), (new Color(12434877)).getRGB());
/*     */ 
/*     */     
/*  73 */     boolean hover5 = Render2DEngine.isHovered(mouseX, mouseY, (getX() + getWidth() - 15.0F), (getY() + 19.0F), 11.0D, 11.0D);
/*  74 */     Render2DEngine.drawRectWithOutline(context.method_51448(), getX() + getWidth() - 15.0F, getY() + 19.0F, 11.0F, 11.0F, hover5 ? hoveredColor : color, color2);
/*  75 */     FontRenderers.categories.drawString(context.method_51448(), "+", (getX() + getWidth() - 12.0F), (getY() + 23.0F), -1);
/*     */ 
/*     */     
/*  78 */     Render2DEngine.horizontalGradient(context.method_51448(), getX() + 2.0F, getY() + 33.0F, getX() + 2.0F + getWidth() / 2.0F - 2.0F, getY() + 33.5F, Render2DEngine.injectAlpha(((ColorSetting)HudEditor.textColor.getValue()).getColorObject(), 0), ((ColorSetting)HudEditor.textColor.getValue()).getColorObject());
/*  79 */     Render2DEngine.horizontalGradient(context.method_51448(), getX() + 2.0F + getWidth() / 2.0F - 2.0F, getY() + 33.0F, getX() + 2.0F + getWidth() - 4.0F, getY() + 33.5F, ((ColorSetting)HudEditor.textColor.getValue()).getColorObject(), Render2DEngine.injectAlpha(((ColorSetting)HudEditor.textColor.getValue()).getColorObject(), 0));
/*     */ 
/*     */     
/*  82 */     Render2DEngine.addWindow(context.method_51448(), getX(), getY() + 38.0F, getX() + getWidth(), getY() + getHeight() - 1.0F, 1.0D);
/*     */     
/*  84 */     int id = 0;
/*  85 */     for (FriendPlate friendPlate : this.friendPlates) {
/*  86 */       id++;
/*  87 */       if ((int)(friendPlate.offset + getY() + 25.0F) + getScrollOffset() > getY() + getHeight() || friendPlate.offset + getScrollOffset() + getY() + 10.0F < getY()) {
/*     */         continue;
/*     */       }
/*  90 */       boolean online = ((Module.mc.field_1724 != null && Module.mc.field_1724.field_3944.method_2880().stream().map(p -> p.method_2966().getName()).toList().contains(friendPlate.name())) || Managers.TELEMETRY.getOnlinePlayers().contains(friendPlate.name()));
/*     */ 
/*     */       
/*  93 */       Render2DEngine.drawRectWithOutline(context.method_51448(), getX() + 11.0F, friendPlate.offset + getY() + 36.0F + getScrollOffset(), getWidth() - 28.0F, 11.0F, color, color2);
/*  94 */       FontRenderers.sf_medium.drawString(context.method_51448(), friendPlate.name() + friendPlate.name(), (getX() + 13.0F), (friendPlate.offset + getY() + 40.0F + getScrollOffset()), textColor);
/*     */ 
/*     */       
/*  97 */       boolean bool1 = Render2DEngine.isHovered(mouseX, mouseY, (getX() + getWidth() - 15.0F), (friendPlate.offset + getY() + 36.0F + getScrollOffset()), 11.0D, 11.0D);
/*  98 */       Render2DEngine.drawRectWithOutline(context.method_51448(), getX() + getWidth() - 15.0F, friendPlate.offset + getY() + 36.0F + getScrollOffset(), 11.0F, 11.0F, bool1 ? hoveredColor : color, color2);
/*  99 */       FontRenderers.icons.drawString(context.method_51448(), "w", (getX() + getWidth() - 15.0F), (friendPlate.offset + getY() + 40.0F + getScrollOffset()), -1);
/* 100 */       FontRenderers.sf_medium_mini.drawString(context.method_51448(), "" + id + ".", (getX() + 3.0F), (friendPlate.offset + getY() + 41.0F + getScrollOffset()), textColor);
/*     */     } 
/* 102 */     setMaxElementsHeight((this.friendPlates.size() * 20));
/* 103 */     Render2DEngine.popWindow();
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseClicked(double mouseX, double mouseY, int button) {
/* 108 */     super.mouseClicked(mouseX, mouseY, button);
/*     */     
/* 110 */     if (Render2DEngine.isHovered(mouseX, mouseY, (getX() + getWidth() - 90.0F), (getY() + 3.0F), 70.0D, 10.0D)) {
/* 111 */       this.listeningId = -2;
/* 112 */       this.search = "";
/*     */     } 
/*     */     
/* 115 */     if (Render2DEngine.isHovered(mouseX, mouseY, (getX() + getWidth() - 15.0F), (getY() + 3.0F), 10.0D, 10.0D)) {
/* 116 */       Module.mc.method_1507((class_437)ClickGUI.getClickGui());
/*     */     }
/* 118 */     boolean hoveringName = Render2DEngine.isHovered(mouseX, mouseY, (getX() + 11.0F), (getY() + 19.0F), (getWidth() - 28.0F), 11.0D);
/* 119 */     boolean hoveringAdd = Render2DEngine.isHovered(mouseX, mouseY, (getX() + getWidth() - 15.0F), (getY() + 19.0F), 11.0D, 11.0D);
/*     */     
/* 121 */     if (hoveringName) {
/* 122 */       this.addName = "";
/* 123 */       this.listeningId = -3;
/*     */     } 
/*     */     
/* 126 */     if (hoveringAdd && !this.addName.isEmpty()) {
/* 127 */       Managers.FRIEND.addFriend(this.addName);
/* 128 */       this.addName = "";
/* 129 */       refresh();
/*     */     } 
/*     */     
/* 132 */     ArrayList<FriendPlate> copy = Lists.newArrayList(this.friendPlates);
/* 133 */     for (FriendPlate friendPlate : copy) {
/* 134 */       if ((int)(friendPlate.offset + getY() + 50.0F) + getScrollOffset() > getY() + getHeight()) {
/*     */         continue;
/*     */       }
/* 137 */       boolean hoveringRemove = Render2DEngine.isHovered(mouseX, mouseY, (getX() + getWidth() - 15.0F), (friendPlate.offset + getY() + 36.0F + getScrollOffset()), 11.0D, 11.0D);
/*     */       
/* 139 */       if (hoveringRemove) {
/* 140 */         Managers.FRIEND.removeFriend(friendPlate.name());
/* 141 */         refresh();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void keyPressed(int keyCode, int scanCode, int modifiers) {
/* 148 */     if (keyCode == 70 && (class_3675.method_15987(Module.mc.method_22683().method_4490(), 341) || class_3675.method_15987(Module.mc.method_22683().method_4490(), 345))) {
/* 149 */       this.listeningId = -2;
/*     */       
/*     */       return;
/*     */     } 
/* 153 */     if (this.listeningId != -1) {
/* 154 */       switch (keyCode) {
/*     */         
/*     */         case 257:
/* 157 */           if (!this.addName.isEmpty()) {
/* 158 */             Managers.FRIEND.addFriend(this.addName);
/*     */           }
/* 160 */           if (this.listeningId != -2) {
/* 161 */             this.listeningId = -1;
/*     */           }
/* 163 */           this.addName = "Name";
/* 164 */           refresh();
/*     */           break;
/*     */         
/*     */         case 256:
/* 168 */           if (this.listeningId == -2)
/* 169 */             this.search = "Search"; 
/* 170 */           this.listeningId = -1;
/* 171 */           refresh();
/*     */           break;
/*     */         
/*     */         case 259:
/* 175 */           if (this.listeningId == -2) {
/* 176 */             this.search = SliderElement.removeLastChar(this.search);
/* 177 */             refresh();
/* 178 */             if (Objects.equals(this.search, "")) {
/* 179 */               this.listeningId = -1;
/* 180 */               this.search = "Search";
/*     */             } 
/*     */             
/*     */             return;
/*     */           } 
/* 185 */           if (this.listeningId == -3) {
/* 186 */             this.addName = SliderElement.removeLastChar(this.addName);
/*     */           }
/*     */           break;
/*     */         case 32:
/* 190 */           if (this.listeningId == -2) {
/* 191 */             this.search += " ";
/*     */           }
/*     */           break;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void charTyped(char key, int keyCode) {
/* 199 */     if (class_3544.method_57175(key) && this.listeningId != -1) {
/* 200 */       if (this.listeningId == -2) {
/* 201 */         this.search += this.search;
/*     */       }
/* 203 */       if (this.listeningId == -3) {
/* 204 */         this.addName += this.addName;
/*     */       }
/*     */       
/* 207 */       refresh();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void refresh() {
/* 212 */     resetScroll();
/* 213 */     this.friendPlates.clear();
/* 214 */     int id1 = 0;
/* 215 */     for (String f : Managers.FRIEND.getFriends()) {
/* 216 */       if (this.search.equals("Search") || this.search.isEmpty() || f.contains(this.search)) {
/* 217 */         this.friendPlates.add(new FriendPlate(id1, (id1 * 20 + 8), f));
/* 218 */         id1++;
/*     */       } 
/*     */     } 
/*     */   } private static final class FriendPlate extends Record { private final int id; private final float offset; private final String name;
/* 222 */     private FriendPlate(int id, float offset, String name) { this.id = id; this.offset = offset; this.name = name; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lthunder/hack/gui/windows/impl/FriendsWindow$FriendPlate;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #222	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 222 */       //   0	7	0	this	Lthunder/hack/gui/windows/impl/FriendsWindow$FriendPlate; } public int id() { return this.id; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lthunder/hack/gui/windows/impl/FriendsWindow$FriendPlate;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #222	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lthunder/hack/gui/windows/impl/FriendsWindow$FriendPlate; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lthunder/hack/gui/windows/impl/FriendsWindow$FriendPlate;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #222	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lthunder/hack/gui/windows/impl/FriendsWindow$FriendPlate;
/* 222 */       //   0	8	1	o	Ljava/lang/Object; } public float offset() { return this.offset; } public String name() { return this.name; }
/*     */      }
/*     */ 
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\windows\impl\FriendsWindow.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */