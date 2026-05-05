/*     */ package thunder.hack.features.modules.render;
/*     */ import java.awt.Color;
/*     */ import java.util.List;
/*     */ import net.minecraft.class_238;
/*     */ import net.minecraft.class_2586;
/*     */ import net.minecraft.class_2818;
/*     */ import net.minecraft.class_4587;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.setting.impl.ColorSetting;
/*     */ import thunder.hack.utility.render.Render3DEngine;
/*     */ 
/*     */ public class StorageEsp extends Module {
/*     */   public final Setting<Boolean> outline;
/*     */   public final Setting<Boolean> fill;
/*     */   public final Setting<Boolean> chest;
/*     */   public final Setting<Boolean> trappedChest;
/*     */   public final Setting<Boolean> dispenser;
/*     */   public final Setting<Boolean> shulker;
/*     */   public final Setting<Boolean> echest;
/*     */   public final Setting<Boolean> furnace;
/*     */   public final Setting<Boolean> hopper;
/*     */   public final Setting<Boolean> barrels;
/*     */   public final Setting<Boolean> cart;
/*     */   
/*     */   public StorageEsp() {
/*  26 */     super("StorageEsp", Module.Category.RENDER);
/*     */ 
/*     */     
/*  29 */     this.outline = new Setting("Outline", Boolean.valueOf(true));
/*  30 */     this.fill = new Setting("Fill", Boolean.valueOf(true));
/*     */     
/*  32 */     this.chest = new Setting("Chest", Boolean.valueOf(true));
/*  33 */     this.trappedChest = new Setting("Trapped Chest", Boolean.valueOf(true));
/*  34 */     this.dispenser = new Setting("Dispenser", Boolean.valueOf(false));
/*  35 */     this.shulker = new Setting("Shulker", Boolean.valueOf(true));
/*  36 */     this.echest = new Setting("Ender Chest", Boolean.valueOf(true));
/*  37 */     this.furnace = new Setting("Furnace", Boolean.valueOf(false));
/*  38 */     this.hopper = new Setting("Hopper", Boolean.valueOf(false));
/*  39 */     this.barrels = new Setting("Barrel", Boolean.valueOf(false));
/*     */     
/*  41 */     this.cart = new Setting("Minecart", Boolean.valueOf(false));
/*  42 */     this.frame = new Setting("ItemFrame", Boolean.valueOf(false));
/*  43 */     this.chestColor = new Setting("ChestColor", new ColorSetting(-2013200640));
/*  44 */     this.trappedChestColor = new Setting("TrappedChestColor", new ColorSetting(-2013200640));
/*  45 */     this.shulkColor = new Setting("ShulkerColor", new ColorSetting(-2013200640));
/*  46 */     this.echestColor = new Setting("EChestColor", new ColorSetting(-2013200640));
/*  47 */     this.frameColor = new Setting("FrameColor", new ColorSetting(-2013200640));
/*  48 */     this.shulkerframeColor = new Setting("ShulkFrameColor", new ColorSetting(-2013200640));
/*  49 */     this.furnaceColor = new Setting("FurnaceColor", new ColorSetting(-2013200640));
/*  50 */     this.hopperColor = new Setting("HopperColor", new ColorSetting(-2013200640));
/*  51 */     this.dispenserColor = new Setting("DispenserColor", new ColorSetting(-2013200640));
/*  52 */     this.barrelColor = new Setting("BarrelColor", new ColorSetting(-2013200640));
/*  53 */     this.minecartColor = new Setting("MinecartColor", new ColorSetting(-2013200640));
/*     */   } public final Setting<Boolean> frame; private final Setting<ColorSetting> chestColor; private final Setting<ColorSetting> trappedChestColor; private final Setting<ColorSetting> shulkColor; private final Setting<ColorSetting> echestColor; private final Setting<ColorSetting> frameColor; private final Setting<ColorSetting> shulkerframeColor; private final Setting<ColorSetting> furnaceColor; private final Setting<ColorSetting> hopperColor; private final Setting<ColorSetting> dispenserColor; private final Setting<ColorSetting> barrelColor; private final Setting<ColorSetting> minecartColor;
/*     */   public void onRender3D(class_4587 stack) {
/*  56 */     if (mc.field_1690.field_1842)
/*  57 */       return;  for (class_2586 blockEntity : getBlockEntities()) {
/*  58 */       Color color = getColor(blockEntity);
/*     */       
/*  60 */       if (color == null) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  68 */       class_238 chestbox = new class_238(blockEntity.method_11016().method_10263() + 0.06D, blockEntity.method_11016().method_10264(), blockEntity.method_11016().method_10260() + 0.06D, blockEntity.method_11016().method_10263() + 0.94D, blockEntity.method_11016().method_10264() - 0.125D + 1.0D, blockEntity.method_11016().method_10260() + 0.94D);
/*     */ 
/*     */       
/*  71 */       if (((Boolean)this.fill.getValue()).booleanValue())
/*  72 */         if (blockEntity instanceof net.minecraft.class_2595)
/*  73 */         { Render3DEngine.drawFilledBox(stack, chestbox, color); }
/*  74 */         else if (blockEntity instanceof net.minecraft.class_2611)
/*  75 */         { Render3DEngine.drawFilledBox(stack, chestbox, color); }
/*  76 */         else { Render3DEngine.drawFilledBox(stack, new class_238(blockEntity.method_11016()), color); }
/*     */          
/*  78 */       if (((Boolean)this.outline.getValue()).booleanValue()) {
/*  79 */         if (blockEntity instanceof net.minecraft.class_2595) {
/*  80 */           Render3DEngine.drawBoxOutline(chestbox, Render2DEngine.injectAlpha(color, 255), 1.0F); continue;
/*  81 */         }  if (blockEntity instanceof net.minecraft.class_2611) {
/*  82 */           Render3DEngine.drawBoxOutline(chestbox, Render2DEngine.injectAlpha(color, 255), 1.0F); continue;
/*     */         } 
/*  84 */         Render3DEngine.drawBoxOutline(new class_238(blockEntity.method_11016()), Render2DEngine.injectAlpha(color, 255), 1.0F);
/*     */       } 
/*     */     } 
/*     */     
/*  88 */     for (class_1297 ent : Managers.ASYNC.getAsyncEntities()) {
/*  89 */       if (ent instanceof class_1533) { class_1533 iframe = (class_1533)ent; if (((Boolean)this.frame.getValue()).booleanValue()) {
/*  90 */           Color frameColor1 = ((ColorSetting)this.frameColor.getValue()).getColorObject();
/*  91 */           class_1792 class_1792 = iframe.method_6940().method_7909(); if (class_1792 instanceof class_1747) { class_1747 bitem = (class_1747)class_1792; if (bitem.method_7711() instanceof net.minecraft.class_2480)
/*  92 */               frameColor1 = ((ColorSetting)this.shulkerframeColor.getValue()).getColorObject();  }
/*     */           
/*  94 */           if (((Boolean)this.fill.getValue()).booleanValue()) {
/*  95 */             Render3DEngine.drawFilledBox(stack, iframe.method_5829(), frameColor1);
/*     */           }
/*  97 */           if (((Boolean)this.outline.getValue()).booleanValue())
/*  98 */             Render3DEngine.drawBoxOutline(iframe.method_5829(), Render2DEngine.injectAlpha(frameColor1, 255), 1.0F); 
/*     */         }  }
/*     */       
/* 101 */       if (ent instanceof class_1694) { class_1694 mcart = (class_1694)ent; if (((Boolean)this.cart.getValue()).booleanValue()) {
/* 102 */           if (((Boolean)this.fill.getValue()).booleanValue()) {
/* 103 */             Render3DEngine.drawFilledBox(stack, mcart.method_5829(), ((ColorSetting)this.minecartColor.getValue()).getColorObject());
/*     */           }
/* 105 */           if (((Boolean)this.outline.getValue()).booleanValue())
/* 106 */             Render3DEngine.drawBoxOutline(mcart.method_5829(), Render2DEngine.injectAlpha(((ColorSetting)this.minecartColor.getValue()).getColorObject(), 255), 1.0F); 
/*     */         }  }
/*     */     
/*     */     } 
/*     */   }
/*     */   @Nullable
/*     */   private Color getColor(class_2586 bEnt) {
/* 113 */     Color color = null;
/*     */     
/* 115 */     if (bEnt instanceof net.minecraft.class_2646 && ((Boolean)this.trappedChest.getValue()).booleanValue()) {
/* 116 */       color = ((ColorSetting)this.trappedChestColor.getValue()).getColorObject();
/* 117 */     } else if (bEnt instanceof net.minecraft.class_2595 && ((Boolean)this.chest.getValue()).booleanValue() && bEnt.method_11017() != class_2591.field_11891) {
/* 118 */       color = ((ColorSetting)this.chestColor.getValue()).getColorObject();
/* 119 */     } else if (bEnt instanceof net.minecraft.class_2611 && ((Boolean)this.echest.getValue()).booleanValue()) {
/* 120 */       color = ((ColorSetting)this.echestColor.getValue()).getColorObject();
/* 121 */     } else if (bEnt instanceof net.minecraft.class_3719 && ((Boolean)this.barrels.getValue()).booleanValue()) {
/* 122 */       color = ((ColorSetting)this.barrelColor.getValue()).getColorObject();
/* 123 */     } else if (bEnt instanceof net.minecraft.class_2627 && ((Boolean)this.shulker.getValue()).booleanValue()) {
/* 124 */       color = ((ColorSetting)this.shulkColor.getValue()).getColorObject();
/* 125 */     } else if (bEnt instanceof net.minecraft.class_2609 && ((Boolean)this.furnace.getValue()).booleanValue()) {
/* 126 */       color = ((ColorSetting)this.furnaceColor.getValue()).getColorObject();
/* 127 */     } else if (bEnt instanceof net.minecraft.class_2601 && ((Boolean)this.dispenser.getValue()).booleanValue()) {
/* 128 */       color = ((ColorSetting)this.dispenserColor.getValue()).getColorObject();
/* 129 */     } else if (bEnt instanceof net.minecraft.class_2614 && ((Boolean)this.hopper.getValue()).booleanValue()) {
/* 130 */       color = ((ColorSetting)this.hopperColor.getValue()).getColorObject();
/*     */     } 
/* 132 */     return color;
/*     */   }
/*     */   
/*     */   public static List<class_2586> getBlockEntities() {
/* 136 */     List<class_2586> list = new ArrayList<>();
/* 137 */     for (class_2818 chunk : getLoadedChunks()) {
/* 138 */       list.addAll(chunk.method_12214().values());
/*     */     }
/* 140 */     return list;
/*     */   }
/*     */   
/*     */   public static List<class_2818> getLoadedChunks() {
/* 144 */     List<class_2818> chunks = new ArrayList<>();
/* 145 */     int viewDist = ((Integer)mc.field_1690.method_42503().method_41753()).intValue();
/* 146 */     for (int x = -viewDist; x <= viewDist; x++) {
/* 147 */       for (int z = -viewDist; z <= viewDist; z++) {
/* 148 */         class_2818 chunk = mc.field_1687.method_2935().method_21730((int)mc.field_1724.method_23317() / 16 + x, (int)mc.field_1724.method_23321() / 16 + z);
/*     */         
/* 150 */         if (chunk != null) chunks.add(chunk); 
/*     */       } 
/*     */     } 
/* 153 */     return chunks;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\render\StorageEsp.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */