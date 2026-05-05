/*     */ package thunder.hack.features.hud.impl;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Pattern;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_268;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_640;
/*     */ import thunder.hack.features.cmd.impl.StaffCommand;
/*     */ import thunder.hack.features.hud.HudElement;
/*     */ import thunder.hack.features.modules.client.HudEditor;
/*     */ import thunder.hack.gui.font.FontRenderers;
/*     */ import thunder.hack.setting.impl.ColorSetting;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ import thunder.hack.utility.render.animation.AnimationUtility;
/*     */ 
/*     */ public class StaffBoard extends HudElement {
/*  24 */   private static final Pattern validUserPattern = Pattern.compile("^\\w{3,16}$");
/*  25 */   private List<String> players = new ArrayList<>();
/*  26 */   private List<String> notSpec = new ArrayList<>();
/*  27 */   private Map<String, class_2960> skinMap = new HashMap<>();
/*     */   private float vAnimation;
/*     */   private float hAnimation;
/*     */   
/*     */   public StaffBoard() {
/*  32 */     super("StaffBoard", 50, 50);
/*     */   }
/*     */   
/*     */   public static List<String> getOnlinePlayer() {
/*  36 */     return (List<String>)mc.field_1724.field_3944.method_2880().stream()
/*  37 */       .map(class_640::method_2966)
/*  38 */       .map(GameProfile::getName)
/*  39 */       .filter(profileName -> validUserPattern.matcher(profileName).matches())
/*  40 */       .collect(Collectors.toList());
/*     */   }
/*     */   
/*     */   public static List<String> getOnlinePlayerD() {
/*  44 */     List<String> S = new ArrayList<>();
/*  45 */     for (class_640 player : mc.field_1724.field_3944.method_2880()) {
/*  46 */       if (mc.method_1542() || player.method_2955() == null)
/*  47 */         break;  String prefix = player.method_2955().method_1144().getString();
/*  48 */       if (check(class_124.method_539(prefix).toLowerCase()) || StaffCommand.staffNames
/*  49 */         .toString().toLowerCase().contains(player.method_2966().getName().toLowerCase()) || player
/*  50 */         .method_2966().getName().toLowerCase().contains("1danil_mansoru1") || player
/*  51 */         .method_2966().getName().toLowerCase().contains("barslan_") || player
/*  52 */         .method_2966().getName().toLowerCase().contains("timmings") || player
/*  53 */         .method_2966().getName().toLowerCase().contains("timings") || player
/*  54 */         .method_2966().getName().toLowerCase().contains("ruthless") || player
/*  55 */         .method_2955().method_1144().getString().contains("YT") || (player
/*  56 */         .method_2955().method_1144().getString().contains("Y") && player.method_2955().method_1144().getString().contains("T"))) {
/*  57 */         String name = Arrays.<Object>asList(player.method_2955().method_1204().toArray()).toString().replace("[", "").replace("]", "");
/*     */         
/*  59 */         if (player.method_2958() == class_1934.field_9219) {
/*  60 */           S.add(player.method_2955().method_1144().getString() + player.method_2955().method_1144().getString() + ":gm3");
/*     */           continue;
/*     */         } 
/*  63 */         S.add(player.method_2955().method_1144().getString() + player.method_2955().method_1144().getString() + ":active");
/*     */       } 
/*     */     } 
/*  66 */     return S;
/*     */   }
/*     */   
/*     */   public List<String> getVanish() {
/*  70 */     List<String> list = new ArrayList<>();
/*  71 */     for (class_268 s : mc.field_1687.method_8428().method_1159()) {
/*  72 */       if (s.method_1144().getString().isEmpty() || mc.method_1542())
/*  73 */         continue;  String name = Arrays.<Object>asList(s.method_1204().toArray()).toString().replace("[", "").replace("]", "");
/*     */       
/*  75 */       if (getOnlinePlayer().contains(name) || name.isEmpty())
/*     */         continue; 
/*  77 */       if ((StaffCommand.staffNames.toString().toLowerCase().contains(name.toLowerCase()) && 
/*  78 */         check(s.method_1144().getString().toLowerCase())) || 
/*  79 */         check(s.method_1144().getString().toLowerCase()) || name
/*  80 */         .toLowerCase().contains("1danil_mansoru1") || name
/*  81 */         .toLowerCase().contains("barslan_") || name
/*  82 */         .toLowerCase().contains("timmings") || name
/*  83 */         .toLowerCase().contains("timings") || name
/*  84 */         .toLowerCase().contains("ruthless") || s
/*  85 */         .method_1144().getString().contains("YT") || (s
/*  86 */         .method_1144().getString().contains("Y") && s.method_1144().getString().contains("T")))
/*     */       {
/*  88 */         list.add(s.method_1144().getString() + s.method_1144().getString() + ":vanish"); } 
/*     */     } 
/*  90 */     return list;
/*     */   }
/*     */   
/*     */   public static boolean check(String name) {
/*  94 */     if (mc.method_1558() != null && (mc.method_1558()).field_3761.contains("mcfunny")) {
/*  95 */       return (name.contains("helper") || name.contains("moder") || name.contains("модер") || name.contains("хелпер"));
/*     */     }
/*  97 */     return (name.contains("helper") || name.contains("moder") || name.contains("admin") || name.contains("owner") || name.contains("curator") || name.contains("куратор") || name.contains("модер") || name.contains("админ") || name.contains("хелпер") || name.contains("поддержка") || name.contains("сотрудник") || name.contains("зам") || name.contains("стажёр"));
/*     */   }
/*     */   
/*     */   public void onRender2D(class_332 context) {
/* 101 */     super.onRender2D(context);
/* 102 */     List<String> all = new ArrayList<>();
/* 103 */     all.addAll(this.players);
/* 104 */     all.addAll(this.notSpec);
/*     */     
/* 106 */     int y_offset1 = 0;
/* 107 */     float max_width = 50.0F;
/*     */     
/* 109 */     float pointerX = 0.0F;
/* 110 */     for (String player : all) {
/* 111 */       if (y_offset1 == 0) {
/* 112 */         y_offset1 += 4;
/*     */       }
/* 114 */       y_offset1 += 9;
/*     */       
/* 116 */       float nameWidth = FontRenderers.sf_bold_mini.getStringWidth(player.split(":")[0]);
/* 117 */       float timeWidth = FontRenderers.sf_bold_mini.getStringWidth(player.split(":")[1].equalsIgnoreCase("vanish") ? (String.valueOf(class_124.field_1061) + "V") : (player.split(":")[1].equalsIgnoreCase("gm3") ? (String.valueOf(class_124.field_1061) + "V " + String.valueOf(class_124.field_1061) + "(GM3)") : (String.valueOf(class_124.field_1060) + "Z")));
/*     */       
/* 119 */       float width = (nameWidth + timeWidth) * 1.4F;
/*     */       
/* 121 */       if (width > max_width) {
/* 122 */         max_width = width;
/*     */       }
/* 124 */       if (timeWidth > pointerX) {
/* 125 */         pointerX = timeWidth;
/*     */       }
/*     */     } 
/* 128 */     this.vAnimation = AnimationUtility.fast(this.vAnimation, (14 + y_offset1), 15.0F);
/* 129 */     this.hAnimation = AnimationUtility.fast(this.hAnimation, max_width, 15.0F);
/*     */     
/* 131 */     Render2DEngine.drawHudBase(context.method_51448(), getPosX(), getPosY(), this.hAnimation, this.vAnimation, ((Float)HudEditor.hudRound.getValue()).floatValue());
/*     */     
/* 133 */     if (HudEditor.hudStyle.is(HudEditor.HudStyle.Glowing)) {
/* 134 */       FontRenderers.sf_bold.drawCenteredString(context.method_51448(), "Staff", (getPosX() + this.hAnimation / 2.0F), (getPosY() + 4.0F), ((ColorSetting)HudEditor.textColor.getValue()).getColorObject());
/*     */     } else {
/* 136 */       FontRenderers.sf_bold.drawGradientCenteredString(context.method_51448(), "Staff", getPosX() + this.hAnimation / 2.0F, getPosY() + 4.0F, 10);
/*     */     } 
/*     */     
/* 139 */     if (y_offset1 > 0) {
/* 140 */       if (HudEditor.hudStyle.is(HudEditor.HudStyle.Blurry)) {
/* 141 */         Render2DEngine.drawRectDumbWay(context.method_51448(), getPosX() + 4.0F, getPosY() + 13.0F, getPosX() + getWidth() - 8.0F, getPosY() + 14.0F, new Color(1426063359, true));
/*     */       } else {
/* 143 */         Render2DEngine.horizontalGradient(context.method_51448(), getPosX() + 2.0F, getPosY() + 13.7F, getPosX() + 2.0F + this.hAnimation / 2.0F - 2.0F, getPosY() + 13.5F, Render2DEngine.injectAlpha(((ColorSetting)HudEditor.textColor.getValue()).getColorObject(), 0), ((ColorSetting)HudEditor.textColor.getValue()).getColorObject());
/* 144 */         Render2DEngine.horizontalGradient(context.method_51448(), getPosX() + 2.0F + this.hAnimation / 2.0F - 2.0F, getPosY() + 13.7F, getPosX() + 2.0F + this.hAnimation - 4.0F, getPosY() + 14.0F, ((ColorSetting)HudEditor.textColor.getValue()).getColorObject(), Render2DEngine.injectAlpha(((ColorSetting)HudEditor.textColor.getValue()).getColorObject(), 0));
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 149 */     Render2DEngine.addWindow(context.method_51448(), getPosX(), getPosY(), getPosX() + this.hAnimation, getPosY() + this.vAnimation, 1.0D);
/* 150 */     int y_offset = 0;
/*     */     
/* 152 */     for (String player : all) {
/* 153 */       float px = getPosX() + max_width - pointerX - 10.0F;
/*     */       
/* 155 */       class_2960 tex = getTexture(player);
/* 156 */       if (tex != null) {
/* 157 */         context.method_25293(tex, (int)(getPosX() + 3.0F), (int)(getPosY() + 16.0F + y_offset), 8, 8, 8.0F, 8.0F, 8, 8, 64, 64);
/* 158 */         context.method_25293(tex, (int)(getPosX() + 3.0F), (int)(getPosY() + 16.0F + y_offset), 8, 8, 40.0F, 8.0F, 8, 8, 64, 64);
/*     */       } 
/*     */       
/* 161 */       FontRenderers.sf_bold_mini.drawString(context.method_51448(), player.split(":")[0], (getPosX() + 13.0F), (getPosY() + 19.0F + y_offset), ((ColorSetting)HudEditor.textColor.getValue()).getColor());
/* 162 */       FontRenderers.sf_bold_mini.drawCenteredString(context.method_51448(), player.split(":")[1].equalsIgnoreCase("vanish") ? (String.valueOf(class_124.field_1061) + "O") : (player.split(":")[1].equalsIgnoreCase("gm3") ? (String.valueOf(class_124.field_1054) + "O") : (String.valueOf(class_124.field_1060) + "O")), (px + (
/* 163 */           getPosX() + max_width - px) / 2.0F), (getPosY() + 19.0F + y_offset), ((ColorSetting)HudEditor.textColor.getValue()).getColor());
/* 164 */       Render2DEngine.drawRect(context.method_51448(), px, getPosY() + 17.0F + y_offset, 0.5F, 8.0F, new Color(1157627903, true));
/* 165 */       y_offset += 9;
/*     */     } 
/* 167 */     Render2DEngine.popWindow();
/* 168 */     setBounds(getPosX(), getPosY(), this.hAnimation, this.vAnimation);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 173 */     if (mc.field_1724 != null && mc.field_1724.field_6012 % 10 == 0) {
/* 174 */       this.players = getVanish();
/* 175 */       this.notSpec = getOnlinePlayerD();
/* 176 */       this.players.sort(String::compareTo);
/* 177 */       this.notSpec.sort(String::compareTo);
/*     */     } 
/*     */   }
/*     */   
/*     */   private class_2960 getTexture(String n) {
/* 182 */     class_2960 id = null;
/* 183 */     if (this.skinMap.containsKey(n)) {
/* 184 */       id = this.skinMap.get(n);
/*     */     }
/* 186 */     for (class_640 ple : mc.method_1562().method_2880()) {
/* 187 */       if (n.contains(ple.method_2966().getName())) {
/* 188 */         id = ple.method_52810().comp_1626();
/* 189 */         if (!this.skinMap.containsKey(n))
/* 190 */           this.skinMap.put(n, id); 
/*     */         break;
/*     */       } 
/*     */     } 
/* 194 */     return id;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\hud\impl\StaffBoard.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */