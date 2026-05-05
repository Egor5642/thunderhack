/*     */ package thunder.hack.gui.misc;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_437;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.misc.NoCommentExploit;
/*     */ import thunder.hack.gui.font.FontRenderers;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ 
/*     */ 
/*     */ public class GuiScanner
/*     */   extends class_437
/*     */ {
/*     */   public static boolean neartrack = false;
/*     */   public static boolean track = false;
/*     */   public static boolean busy = false;
/*  21 */   public ArrayList<NoCommentExploit.Cout> consoleout = new ArrayList<>(); int radarx; int radary; int radarx1; int radary1; int centerx;
/*     */   int centery;
/*     */   int consolex;
/*     */   
/*     */   public GuiScanner() {
/*  26 */     super(class_2561.method_30163("GuiScanner"));
/*     */   }
/*     */   int consoley; int consolex1; int consoley1; int hovery; int hoverx; int searchx; int searchy; int wheely;
/*     */   
/*     */   public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
/*  31 */     if (Module.mc.field_1724 == null)
/*     */       return; 
/*  33 */     this.radarx = Module.mc.method_22683().method_4486() / 8;
/*  34 */     this.radarx1 = Module.mc.method_22683().method_4486() * 5 / 8;
/*  35 */     this.radary = Module.mc.method_22683().method_4502() / 2 - (this.radarx1 - this.radarx) / 2;
/*  36 */     this.radary1 = Module.mc.method_22683().method_4502() / 2 + (this.radarx1 - this.radarx) / 2;
/*     */     
/*  38 */     this.centerx = (this.radarx + this.radarx1) / 2;
/*  39 */     this.centery = (this.radary + this.radary1) / 2;
/*     */     
/*  41 */     this.consolex = (int)(Module.mc.method_22683().method_4486() * 5.5F / 8.0F);
/*  42 */     this.consolex1 = Module.mc.method_22683().method_4486() - 50;
/*  43 */     this.consoley = this.radary;
/*  44 */     this.consoley1 = this.radary1 - 50;
/*     */ 
/*     */     
/*  47 */     Render2DEngine.drawRectDumbWay(context.method_51448(), this.consolex, this.consoley, this.consolex1, this.consoley1, new Color(-150205428, true));
/*     */     
/*  49 */     Render2DEngine.drawRectDumbWay(context.method_51448(), this.consolex, (this.consoley1 + 3), this.consolex1, (this.consoley1 + 17), new Color(-150205428, true));
/*  50 */     FontRenderers.monsterrat.drawString(context.method_51448(), "cursor pos: " + this.hoverx * 64 + "x  " + this.hovery * 64 + "z", (this.consolex + 4), (this.consoley1 + 6), -1);
/*     */     
/*  52 */     if (!track) {
/*  53 */       Render2DEngine.drawRectDumbWay(context.method_51448(), this.consolex, (this.consoley1 + 20), this.consolex1, (this.consoley1 + 35), new Color(-150205428, true));
/*  54 */       FontRenderers.monsterrat.drawString(context.method_51448(), "tracker off", (this.consolex + 4), (this.consoley1 + 26), -1);
/*     */     } else {
/*  56 */       Render2DEngine.drawRectDumbWay(context.method_51448(), this.consolex, (this.consoley1 + 20), this.consolex1, (this.consoley1 + 35), new Color(-144810402, true));
/*  57 */       FontRenderers.monsterrat.drawString(context.method_51448(), "tracker on", (this.consolex + 4), (this.consoley1 + 26), -1);
/*     */     } 
/*     */     
/*  60 */     Render2DEngine.drawRectDumbWay(context.method_51448(), this.consolex, (this.consoley1 + 38), this.consolex1, (this.consoley1 + 53), new Color(-150205428, true));
/*  61 */     FontRenderers.monsterrat.drawString(context.method_51448(), "clear console", (this.consolex + 4), (this.consoley1 + 42), -1);
/*     */     
/*  63 */     Render2DEngine.drawRectDumbWay(context.method_51448(), this.radarx, this.radary, this.radarx1, this.radary1, new Color(-535489259, true));
/*     */     
/*  65 */     for (NoCommentExploit.Dot point : new ArrayList(NoCommentExploit.dots)) {
/*  66 */       if (point.type() == NoCommentExploit.DotType.Searched) {
/*  67 */         Render2DEngine.drawRectDumbWay(context.method_51448(), point.posX() / 4.0F + this.centerx, point.posY() / 4.0F + this.centery, point.posX() / 4.0F + (this.radarx1 - this.radarx) / 300.0F + this.centerx, point.posY() / 4.0F + (this.radary1 - this.radary) / 300.0F + this.centery, new Color(-408377176, true)); continue;
/*     */       } 
/*  69 */       Render2DEngine.drawRectDumbWay(context.method_51448(), point.posX() / 4.0F + this.centerx, point.posY() / 4.0F + this.centery, point.posX() / 4.0F + (this.radarx1 - this.radarx) / 300.0F + this.centerx, point.posY() / 4.0F + (this.radary1 - this.radary) / 300.0F + this.centery, new Color(3991304));
/*     */     } 
/*     */ 
/*     */     
/*  73 */     Render2DEngine.drawRectDumbWay(context.method_51448(), this.centerx - 1.0F, this.centery - 1.0F, this.centerx + 1.0F, this.centery + 1.0F, new Color(16712451));
/*  74 */     Render2DEngine.drawRectDumbWay(context.method_51448(), (float)(Module.mc.field_1724.method_23317() / 16.0D / 4.0D + this.centerx), (float)(Module.mc.field_1724.method_23321() / 16.0D / 4.0D + this.centery), (float)(Module.mc.field_1724.method_23317() / 16.0D / 4.0D + ((this.radarx1 - this.radarx) / 300.0F) + this.centerx), (float)(Module.mc.field_1724.method_23321() / 16.0D / 4.0D + ((this.radary1 - this.radary) / 300.0F) + this.centery), new Color(4863));
/*     */     
/*  76 */     if (mouseX > this.radarx && mouseX < this.radarx1 && mouseY > this.radary && mouseY < this.radary1) {
/*  77 */       this.hoverx = mouseX - this.centerx;
/*  78 */       this.hovery = mouseY - this.centery;
/*     */     } 
/*     */     
/*  81 */     Render2DEngine.addWindow(context.method_51448(), this.consolex, this.consoley, this.consolex1, (this.consoley1 - 10), 1.0D);
/*     */     
/*  83 */     for (NoCommentExploit.Cout out : new ArrayList(this.consoleout)) {
/*  84 */       FontRenderers.monsterrat.drawString(context.method_51448(), out.out(), (this.consolex + 4), (this.consoley + 6 + out.posY() * 11 + this.wheely), -1);
/*     */     }
/*  86 */     Render2DEngine.popWindow();
/*     */     
/*  88 */     FontRenderers.monsterrat.drawString(context.method_51448(), "X+", (this.radarx1 + 5), this.centery, -1);
/*  89 */     FontRenderers.monsterrat.drawString(context.method_51448(), "X-", (this.radarx - 15), this.centery, -1);
/*  90 */     FontRenderers.monsterrat.drawString(context.method_51448(), "Y+", this.centerx, (this.radary1 + 5), -1);
/*  91 */     FontRenderers.monsterrat.drawString(context.method_51448(), "Y-", this.centerx, (this.radary - 8), -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mouseX, double mouseY, int button) {
/*  96 */     if (mouseX > this.radarx && mouseX < this.radarx1 && mouseY > this.radary && mouseY < this.radary1) {
/*  97 */       busy = true;
/*  98 */       this.searchx = (int)(mouseX - this.centerx);
/*  99 */       this.searchy = (int)(mouseY - this.centery);
/* 100 */       ModuleManager.noCommentExploit.rerun(this.searchx * 64, this.searchy * 64);
/* 101 */       this.consoleout.add(new NoCommentExploit.Cout(ModuleManager.noCommentExploit.couti, "Selected pos " + this.searchx * 65 + "x " + this.searchy * 64 + "z "));
/* 102 */       ModuleManager.noCommentExploit.couti++;
/*     */     } 
/* 104 */     if (mouseX > this.consolex && mouseX < this.consolex1 && mouseY > (this.consoley1 + 20) && mouseY < (this.consoley1 + 36)) {
/* 105 */       track = !track;
/*     */     }
/* 107 */     if (mouseX > this.consolex && mouseX < this.consolex1 && mouseY > (this.consoley1 + 38) && mouseY < (this.consoley1 + 53)) {
/* 108 */       ModuleManager.noCommentExploit.couti = 1;
/* 109 */       this.consoleout.clear();
/*     */     } 
/*     */     
/* 112 */     return super.method_25402(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/* 117 */     this.wheely += (int)(verticalAmount * 5.0D);
/* 118 */     return super.method_25401(mouseX, mouseY, horizontalAmount, verticalAmount);
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\misc\GuiScanner.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */