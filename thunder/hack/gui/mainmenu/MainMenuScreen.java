/*     */ package thunder.hack.gui.mainmenu;
/*     */ 
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.awt.Color;
/*     */ import java.net.URI;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.class_1074;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_156;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_429;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_442;
/*     */ import net.minecraft.class_500;
/*     */ import net.minecraft.class_526;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import thunder.hack.api.IAddon;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.gui.font.FontRenderers;
/*     */ import thunder.hack.utility.ThunderUtility;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ import thunder.hack.utility.render.TextureStorage;
/*     */ 
/*     */ public class MainMenuScreen
/*     */   extends class_437 {
/*  32 */   private final List<MainMenuButton> buttons = new ArrayList<>();
/*     */   public boolean confirm = false;
/*     */   public static int ticksActive;
/*     */   
/*     */   protected MainMenuScreen() {
/*  37 */     super(class_2561.method_30163("THMainMenuScreen"));
/*  38 */     INSTANCE = this;
/*     */     
/*  40 */     this.buttons.add(new MainMenuButton(-110.0F, -70.0F, class_1074.method_4662("menu.singleplayer", new Object[0]).toUpperCase(Locale.ROOT), () -> Module.mc.method_1507((class_437)new class_526(this))));
/*  41 */     this.buttons.add(new MainMenuButton(4.0F, -70.0F, class_1074.method_4662("menu.multiplayer", new Object[0]).toUpperCase(Locale.ROOT), () -> Module.mc.method_1507((class_437)new class_500(this))));
/*  42 */     this.buttons.add(new MainMenuButton(-110.0F, -29.0F, class_1074.method_4662("menu.options", new Object[0])
/*  43 */           .toUpperCase(Locale.ROOT)
/*  44 */           .replace(".", ""), () -> Module.mc.method_1507((class_437)new class_429(this, Module.mc.field_1690))));
/*  45 */     this.buttons.add(new MainMenuButton(4.0F, -29.0F, "CLICKGUI", () -> ModuleManager.clickGui.setGui()));
/*  46 */     Objects.requireNonNull(Module.mc); this.buttons.add(new MainMenuButton(-110.0F, 12.0F, class_1074.method_4662("menu.quit", new Object[0]).toUpperCase(Locale.ROOT), Module.mc::method_1592, true));
/*     */   }
/*     */   
/*  49 */   private static MainMenuScreen INSTANCE = new MainMenuScreen();
/*     */   
/*     */   public static MainMenuScreen getInstance() {
/*  52 */     ticksActive = 0;
/*     */     
/*  54 */     if (INSTANCE == null) {
/*  55 */       INSTANCE = new MainMenuScreen();
/*     */     }
/*  57 */     return INSTANCE;
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25393() {
/*  62 */     ticksActive++;
/*     */     
/*  64 */     if (ticksActive > 400) {
/*  65 */       ticksActive = 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25394(@NotNull class_332 context, int mouseX, int mouseY, float delta) {
/*  71 */     float halfOfWidth = Module.mc.method_22683().method_4486() / 2.0F;
/*  72 */     float halfOfHeight = Module.mc.method_22683().method_4502() / 2.0F;
/*     */     
/*  74 */     float mainX = halfOfWidth - 120.0F;
/*  75 */     float mainY = halfOfHeight - 80.0F;
/*  76 */     float mainWidth = 240.0F;
/*  77 */     float mainHeight = 140.0F;
/*     */ 
/*     */     
/*  80 */     method_25420(context, mouseX, mouseY, delta);
/*     */     
/*  82 */     Render2DEngine.drawHudBase(context.method_51448(), mainX, mainY, mainWidth, mainHeight, 20.0F);
/*     */     
/*  84 */     this.buttons.forEach(b -> b.onRender(context, mouseX, mouseY));
/*     */     
/*  86 */     boolean hoveredLogo = Render2DEngine.isHovered(mouseX, mouseY, (int)(halfOfWidth - 120.0F), (int)(halfOfHeight - 130.0F), 210.0D, 50.0D);
/*     */     
/*  88 */     FontRenderers.thglitchBig.drawCenteredString(context.method_51448(), "THUNDERHACK", (int)halfOfWidth, (int)(halfOfHeight - 120.0F), (new Color(255, 255, 255, hoveredLogo ? 230 : 180)).getRGB());
/*     */     
/*  90 */     boolean hovered = Render2DEngine.isHovered(mouseX, mouseY, (halfOfWidth - 50.0F), (halfOfHeight + 70.0F), 100.0D, 10.0D);
/*     */     
/*  92 */     FontRenderers.sf_medium.drawCenteredString(context.method_51448(), "<-- Back to default menu", halfOfWidth, (halfOfHeight + 70.0F), hovered ? -1 : Render2DEngine.applyOpacity(-1, 0.6F));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  97 */     String onlineUsers = String.format("online: %s%s", new Object[] { class_124.field_1077, Integer.valueOf(Managers.TELEMETRY.getOnlinePlayers().size()) });
/*     */     
/*  99 */     FontRenderers.sf_bold.drawCenteredString(context.method_51448(), onlineUsers, halfOfWidth, (halfOfHeight * 2.0F - 15.0F), Color.GREEN);
/*     */     
/* 101 */     context.method_51448().method_22903();
/* 102 */     context.method_51448().method_46416(halfOfWidth - 10.0F - FontRenderers.sf_medium.getStringWidth(onlineUsers) / 2.0F, halfOfHeight * 2.0F - 17.0F, 0.0F);
/* 103 */     Render2DEngine.drawBloom(context.method_51448(), Render2DEngine.applyOpacity(Color.GREEN, 0.6F), 9.0F);
/* 104 */     context.method_51448().method_22909();
/*     */     
/* 106 */     context.method_51448().method_22903();
/* 107 */     context.method_51448().method_46416(halfOfWidth - 10.0F - FontRenderers.sf_medium.getStringWidth(onlineUsers) / 2.0F, halfOfHeight * 2.0F - 17.0F, 0.0F);
/* 108 */     Render2DEngine.drawBloom(context.method_51448(), Render2DEngine.applyOpacity(Color.GREEN, (float)(0.5D + Math.sin(System.currentTimeMillis() / 500.0D) / 2.0D)), 9.0F);
/* 109 */     context.method_51448().method_22909();
/*     */ 
/*     */ 
/*     */     
/* 113 */     Render2DEngine.drawHudBase(context.method_51448(), (Module.mc.method_22683().method_4486() - 40), (Module.mc.method_22683().method_4502() - 40), 30.0F, 30.0F, 5.0F, Render2DEngine.isHovered(mouseX, mouseY, (Module.mc.method_22683().method_4486() - 40), (Module.mc.method_22683().method_4502() - 40), 30.0D, 30.0D) ? 0.7F : 1.0F);
/* 114 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, Render2DEngine.isHovered(mouseX, mouseY, (Module.mc.method_22683().method_4486() - 40), (Module.mc.method_22683().method_4502() - 40), 30.0D, 30.0D) ? 0.7F : 1.0F);
/* 115 */     context.method_25293(TextureStorage.thTeam, Module.mc.method_22683().method_4486() - 40, Module.mc.method_22683().method_4502() - 40, 30, 30, 0.0F, 0.0F, 30, 30, 30, 30);
/* 116 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 118 */     Render2DEngine.drawHudBase(context.method_51448(), (Module.mc.method_22683().method_4486() - 80), (Module.mc.method_22683().method_4502() - 40), 30.0F, 30.0F, 5.0F, Render2DEngine.isHovered(mouseX, mouseY, (Module.mc.method_22683().method_4486() - 80), (Module.mc.method_22683().method_4502() - 40), 30.0D, 30.0D) ? 0.7F : 1.0F);
/* 119 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, Render2DEngine.isHovered(mouseX, mouseY, (Module.mc.method_22683().method_4486() - 80), (Module.mc.method_22683().method_4502() - 40), 30.0D, 30.0D) ? 0.7F : 1.0F);
/* 120 */     context.method_25293(TextureStorage.donation, Module.mc.method_22683().method_4486() - 79, Module.mc.method_22683().method_4502() - 39, 28, 28, 0.0F, 0.0F, 30, 30, 30, 30);
/* 121 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 123 */     int offsetY = 10;
/* 124 */     for (String change : ThunderUtility.changeLog) {
/* 125 */       String prefix = getPrefix(change);
/* 126 */       FontRenderers.sf_medium.drawString(context.method_51448(), prefix, 10.0D, offsetY, Render2DEngine.applyOpacity(-1, 0.4F));
/* 127 */       offsetY += 10;
/*     */     } 
/*     */     
/* 130 */     int totalAddonsLoaded = Managers.ADDON.getTotalAddons();
/* 131 */     String addonsText = "Addons Loaded: " + totalAddonsLoaded;
/* 132 */     int screenWidth = Module.mc.method_22683().method_4486();
/* 133 */     int textWidth = (int)FontRenderers.sf_bold.getStringWidth(addonsText);
/* 134 */     int textX = screenWidth - textWidth - 5;
/* 135 */     FontRenderers.sf_bold.drawString(context.method_51448(), addonsText, textX, 5.0D, Color.WHITE.getRGB());
/*     */     
/* 137 */     int offset = 0;
/* 138 */     for (IAddon addon : Managers.ADDON.getAddons()) {
/*     */       
/* 140 */       textWidth = (int)FontRenderers.sf_bold.getStringWidth(addon.getName() + " |");
/* 141 */       textX = screenWidth - textWidth - 5;
/* 142 */       FontRenderers.sf_bold.drawString(context.method_51448(), addon.getName() + addon.getName() + " |", textX, (13 + offset), Color.GRAY.getRGB());
/* 143 */       offset += 9;
/*     */     } 
/*     */   }
/*     */   @NotNull
/*     */   private static String getPrefix(@NotNull String change) {
/* 148 */     String prefix = "";
/* 149 */     if (change.contains("[+]")) {
/* 150 */       change = change.replace("[+] ", "");
/* 151 */       prefix = String.valueOf(class_124.field_1060) + "[+] " + String.valueOf(class_124.field_1060);
/* 152 */     } else if (change.contains("[-]")) {
/* 153 */       change = change.replace("[-] ", "");
/* 154 */       prefix = String.valueOf(class_124.field_1061) + "[-] " + String.valueOf(class_124.field_1061);
/* 155 */     } else if (change.contains("[/]")) {
/* 156 */       change = change.replace("[/] ", "");
/* 157 */       prefix = String.valueOf(class_124.field_1076) + "[/] " + String.valueOf(class_124.field_1076);
/* 158 */     } else if (change.contains("[*]")) {
/* 159 */       change = change.replace("[*] ", "");
/* 160 */       prefix = String.valueOf(class_124.field_1065) + "[*] " + String.valueOf(class_124.field_1065);
/*     */     } 
/* 162 */     return prefix + prefix;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 167 */     float halfOfWidth = Module.mc.method_22683().method_4486() / 2.0F;
/* 168 */     float halfOfHeight = Module.mc.method_22683().method_4502() / 2.0F;
/* 169 */     this.buttons.forEach(b -> b.onClick((int)mouseX, (int)mouseY));
/*     */     
/* 171 */     if (Render2DEngine.isHovered(mouseX, mouseY, (halfOfWidth - 50.0F), (halfOfHeight + 70.0F), 100.0D, 10.0D)) {
/* 172 */       this.confirm = true;
/* 173 */       Module.mc.method_1507((class_437)new class_442());
/* 174 */       this.confirm = false;
/*     */     } 
/*     */     
/* 177 */     if (Render2DEngine.isHovered(mouseX, mouseY, (Module.mc.method_22683().method_4486() - 40), (Module.mc.method_22683().method_4502() - 40), 40.0D, 40.0D)) {
/* 178 */       Module.mc.method_1507(CreditsScreen.getInstance());
/*     */     }
/* 180 */     if (Render2DEngine.isHovered(mouseX, mouseY, (Module.mc.method_22683().method_4486() - 90), (Module.mc.method_22683().method_4502() - 40), 40.0D, 40.0D)) {
/* 181 */       class_156.method_668().method_673(URI.create("https://www.donationalerts.com/r/06ed/"));
/*     */     }
/* 183 */     if (Render2DEngine.isHovered(mouseX, mouseY, (int)(halfOfWidth - 157.0F), (int)(halfOfHeight - 140.0F), 300.0D, 70.0D)) {
/* 184 */       class_156.method_668().method_673(URI.create("https://thunderhack-site.vercel.app/"));
/*     */     }
/* 186 */     return super.method_25402(mouseX, mouseY, button);
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\mainmenu\MainMenuScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */