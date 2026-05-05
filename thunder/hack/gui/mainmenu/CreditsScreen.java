/*     */ package thunder.hack.gui.mainmenu;
/*     */ 
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.awt.Color;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.class_1011;
/*     */ import net.minecraft.class_1043;
/*     */ import net.minecraft.class_156;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_437;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import thunder.hack.ThunderHack;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.gui.font.FontRenderers;
/*     */ import thunder.hack.utility.math.MathUtility;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ import thunder.hack.utility.render.Render3DEngine;
/*     */ import thunder.hack.utility.render.TextureStorage;
/*     */ 
/*     */ public class CreditsScreen
/*     */   extends class_437
/*     */ {
/*  31 */   public ArrayList<Contributor> contributors = new ArrayList<>();
/*     */   
/*     */   private static int scroll;
/*     */   private static final int SCROLL_SPEED = 1;
/*     */   
/*     */   protected CreditsScreen() {
/*  37 */     super(class_2561.method_30163("CreditsScreen"));
/*  38 */     INSTANCE = this;
/*  39 */     for (String line : ThunderHack.contributors) {
/*  40 */       if (line != null) {
/*     */         
/*  42 */         String name = line.split(";")[0];
/*  43 */         String avatar = line.split(";")[1];
/*  44 */         String role = line.split(";")[2];
/*  45 */         String description = line.split(";")[3];
/*  46 */         String clickAction = line.split(";")[4];
/*  47 */         (getInstance()).contributors.add(new Contributor(name, getAvatar(avatar), role, description.replace('т', '\n'), clickAction));
/*     */       } 
/*     */     } 
/*     */   }
/*  51 */   private static CreditsScreen INSTANCE = new CreditsScreen();
/*     */   
/*     */   public static CreditsScreen getInstance() {
/*  54 */     scroll = 150;
/*  55 */     if (INSTANCE == null) {
/*  56 */       INSTANCE = new CreditsScreen();
/*     */     }
/*  58 */     return INSTANCE;
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25394(@NotNull class_332 context, int mouseX, int mouseY, float delta) {
/*  63 */     float halfOfWidth = Module.mc.method_22683().method_4486() / 2.0F;
/*  64 */     float halfOfHeight = Module.mc.method_22683().method_4502() / 2.0F;
/*  65 */     float globalOffset = (this.contributors.size() * 150) / 2.0F;
/*     */ 
/*     */     
/*  68 */     method_25420(context, mouseX, mouseY, delta);
/*     */     
/*  70 */     RenderSystem.enableBlend();
/*  71 */     RenderSystem.defaultBlendFunc();
/*  72 */     int offset = 0;
/*     */     
/*  74 */     for (Contributor contributor : this.contributors) {
/*  75 */       float cX = halfOfWidth + offset - globalOffset + scroll;
/*  76 */       float cY = halfOfHeight - 120.0F;
/*  77 */       Render2DEngine.drawHudBase(context.method_51448(), cX, cY, 140.0F, 240.0F, 20.0F, false);
/*  78 */       FontRenderers.sf_medium.drawGradientString(context.method_51448(), contributor.name, cX + 70.0F - FontRenderers.sf_medium.getStringWidth(contributor.name) / 2.0F, halfOfHeight - 57.0F, 30);
/*  79 */       FontRenderers.sf_medium.drawCenteredString(context.method_51448(), contributor.role, (cX + 70.0F), (halfOfHeight - 48.0F), (new Color(8487297)).getRGB());
/*     */       
/*  81 */       Render2DEngine.horizontalGradient(context.method_51448(), cX + 2.0F, cY + 90.0F, cX + 70.0F, cY + 91.0F, Render2DEngine.injectAlpha(new Color(-1), 0), new Color(-1));
/*  82 */       Render2DEngine.horizontalGradient(context.method_51448(), cX + 70.0F, cY + 90.0F, cX + 138.0F, cY + 91.0F, new Color(-1), Render2DEngine.injectAlpha(new Color(-1), 0));
/*  83 */       Render2DEngine.drawRound(context.method_51448(), cX + 5.0F, cY + 100.0F, 130.0F, 130.0F, 8.0F, new Color(1929379840, true));
/*  84 */       FontRenderers.sf_medium.drawString(context.method_51448(), contributor.description, (cX + 10.0F), (cY + 108.0F), (new Color(8487298)).getRGB());
/*     */       
/*  86 */       if (contributor.avatar != null) {
/*  87 */         context.method_25293(contributor.avatar, (int)(cX + 70.0F - 24.0F), (int)(halfOfHeight - 110.0F), 48, 48, 0.0F, 0.0F, 96, 96, 96, 96);
/*     */       }
/*  89 */       if (Render2DEngine.isHovered(mouseX, mouseY, cX, cY, 140.0D, 240.0D) && !Objects.equals(contributor.clickAction, "")) {
/*  90 */         Render2DEngine.drawRound(context.method_51448(), cX, cY, 140.0F, 240.0F, 8.0F, new Color(100663295, true));
/*     */       }
/*  92 */       offset += 150;
/*     */     } 
/*  94 */     RenderSystem.disableBlend();
/*  95 */     Render2DEngine.drawHudBase(context.method_51448(), (Module.mc.method_22683().method_4486() - 40), (Module.mc.method_22683().method_4502() - 40), 30.0F, 30.0F, 5.0F, Render2DEngine.isHovered(mouseX, mouseY, (Module.mc.method_22683().method_4486() - 60), (Module.mc.method_22683().method_4502() - 60), 40.0D, 40.0D) ? 0.7F : 1.0F);
/*  96 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, Render2DEngine.isHovered(mouseX, mouseY, (Module.mc.method_22683().method_4486() - 40), (Module.mc.method_22683().method_4502() - 40), 30.0D, 30.0D) ? 0.7F : 1.0F);
/*  97 */     context.method_25293(TextureStorage.thTeam, Module.mc.method_22683().method_4486() - 40, Module.mc.method_22683().method_4502() - 40, 30, 30, 0.0F, 0.0F, 30, 30, 30, 30);
/*  98 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 104 */     float halfOfWidth = Module.mc.method_22683().method_4486() / 2.0F;
/* 105 */     float halfOfHeight = Module.mc.method_22683().method_4502() / 2.0F;
/* 106 */     float globalOffset = (this.contributors.size() * 150) / 2.0F;
/* 107 */     int offset = 0;
/* 108 */     for (Contributor contributor : this.contributors) {
/* 109 */       float cX = (float)((halfOfWidth + offset - globalOffset) + Render2DEngine.interpolate(scroll, (scroll + 1), Render3DEngine.getTickDelta()));
/* 110 */       float cY = halfOfHeight - 120.0F;
/* 111 */       if (Render2DEngine.isHovered(mouseX, mouseY, cX, cY, 140.0D, 240.0D) && !Objects.equals(contributor.clickAction, "none"))
/* 112 */         class_156.method_668().method_673(URI.create(contributor.clickAction)); 
/* 113 */       offset += 150;
/*     */     } 
/*     */     
/* 116 */     if (Render2DEngine.isHovered(mouseX, mouseY, (Module.mc.method_22683().method_4486() - 40), (Module.mc.method_22683().method_4502() - 40), 40.0D, 40.0D)) {
/* 117 */       Module.mc.method_1507(MainMenuScreen.getInstance());
/*     */     }
/* 119 */     return super.method_25402(mouseX, mouseY, button);
/*     */   }
/*     */   public static final class Contributor extends Record { private final String name; private final class_2960 avatar; private final String role; private final String description; private final String clickAction;
/* 122 */     public Contributor(String name, class_2960 avatar, String role, String description, String clickAction) { this.name = name; this.avatar = avatar; this.role = role; this.description = description; this.clickAction = clickAction; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lthunder/hack/gui/mainmenu/CreditsScreen$Contributor;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #122	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 122 */       //   0	7	0	this	Lthunder/hack/gui/mainmenu/CreditsScreen$Contributor; } public String name() { return this.name; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lthunder/hack/gui/mainmenu/CreditsScreen$Contributor;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #122	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lthunder/hack/gui/mainmenu/CreditsScreen$Contributor; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lthunder/hack/gui/mainmenu/CreditsScreen$Contributor;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #122	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lthunder/hack/gui/mainmenu/CreditsScreen$Contributor;
/* 122 */       //   0	8	1	o	Ljava/lang/Object; } public class_2960 avatar() { return this.avatar; } public String role() { return this.role; } public String description() { return this.description; } public String clickAction() { return this.clickAction; }
/*     */      }
/*     */   
/*     */   public static class_2960 getAvatar(String name) {
/*     */     try {
/* 127 */       class_1043 nIBT = getAvatarFromURL("https://cdn.discordapp.com/avatars/" + name + ".png?size=96");
/*     */       
/* 129 */       if (nIBT != null) {
/* 130 */         return class_310.method_1551().method_1531().method_4617("th-contributors-" + (int)MathUtility.random(0.0F, 1000000.0F), nIBT);
/*     */       }
/* 132 */       return null;
/*     */     }
/* 134 */     catch (Exception e) {
/* 135 */       e.printStackTrace();
/*     */       
/* 137 */       return null;
/*     */     } 
/*     */   }
/*     */   public static class_1043 getAvatarFromURL(String HeadStringURL) {
/*     */     try {
/* 142 */       return getAvatarFromStream((new URL(HeadStringURL)).openStream());
/* 143 */     } catch (IOException e) {
/* 144 */       e.printStackTrace();
/* 145 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class_1043 getAvatarFromStream(InputStream image) {
/* 150 */     class_1011 pic = null;
/*     */     try {
/* 152 */       pic = class_1011.method_4309(image);
/* 153 */     } catch (IOException e) {
/* 154 */       e.printStackTrace();
/*     */     } 
/* 156 */     if (pic != null) {
/* 157 */       return new class_1043(parseAvatar(pic));
/*     */     }
/* 159 */     return null;
/*     */   }
/*     */   
/*     */   public static class_1011 parseAvatar(class_1011 image) {
/* 163 */     class_1011 imgNew = new class_1011(96, 96, true);
/* 164 */     for (int x = 0; x < 96; x++) {
/* 165 */       for (int y = 0; y < 96; y++) {
/* 166 */         if (Math.hypot((x - 48), (y - 48)) > 45.0D)
/* 167 */         { imgNew.method_4305(x, y, Render2DEngine.injectAlpha(new Color(image.method_4315(x, y)), (int)((float)(48.0D - Math.hypot((x - 48), (y - 48))) / 3.0F * 255.0F)).getRGB()); }
/* 168 */         else { imgNew.method_4305(x, y, image.method_4315(x, y)); }
/*     */       
/*     */       } 
/* 171 */     }  image.close();
/* 172 */     return imgNew;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/* 177 */     scroll += (int)(verticalAmount * 5.0D);
/* 178 */     return super.method_25401(mouseX, mouseY, horizontalAmount, verticalAmount);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_25393() {
/* 184 */     scroll--;
/*     */     
/* 186 */     if (scroll <= -(this.contributors.size() * 150) + 100)
/* 187 */       scroll = 0; 
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\mainmenu\CreditsScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */