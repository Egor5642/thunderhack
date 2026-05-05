/*    */ package thunder.hack.gui.font;
/*    */ 
/*    */ import java.awt.Font;
/*    */ import java.awt.FontFormatException;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.Objects;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.ThunderHack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FontRenderers
/*    */ {
/*    */   public static FontRenderer settings;
/*    */   public static FontRenderer modules;
/*    */   public static FontRenderer categories;
/*    */   public static FontRenderer icons;
/*    */   public static FontRenderer mid_icons;
/*    */   public static FontRenderer big_icons;
/*    */   public static FontRenderer thglitch;
/*    */   public static FontRenderer thglitchBig;
/*    */   
/*    */   public static FontRenderer getModulesRenderer() {
/* 30 */     return modules;
/*    */   } public static FontRenderer monsterrat; public static FontRenderer sf_bold; public static FontRenderer sf_bold_mini; public static FontRenderer sf_bold_micro; public static FontRenderer sf_medium; public static FontRenderer sf_medium_mini; public static FontRenderer sf_medium_modules; public static FontRenderer minecraft; public static FontRenderer profont;
/*    */   @NotNull
/*    */   public static FontRenderer create(float size, String name) throws IOException, FontFormatException {
/* 34 */     return new FontRenderer(Font.createFont(0, Objects.<InputStream>requireNonNull(ThunderHack.class.getClassLoader().getResourceAsStream("assets/thunderhack/fonts/" + name + ".ttf"))).deriveFont(0, size / 2.0F), size / 2.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\font\FontRenderers.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */