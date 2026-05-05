/*     */ package thunder.hack.features.modules.render;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import net.minecraft.class_156;
/*     */ import net.minecraft.class_2246;
/*     */ import net.minecraft.class_2248;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_238;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_2680;
/*     */ import net.minecraft.class_4587;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.ClientSettings;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.setting.impl.BooleanSettingGroup;
/*     */ import thunder.hack.setting.impl.ColorSetting;
/*     */ import thunder.hack.setting.impl.ItemSelectSetting;
/*     */ import thunder.hack.utility.Timer;
/*     */ import thunder.hack.utility.render.Render3DEngine;
/*     */ 
/*     */ public class BlockESP extends Module {
/*     */   public final Setting<ItemSelectSetting> selectedBlocks;
/*     */   
/*     */   public BlockESP() {
/*  29 */     super("BlockESP", Module.Category.RENDER);
/*     */ 
/*     */     
/*  32 */     this.selectedBlocks = new Setting("SelectedBlocks", new ItemSelectSetting(new ArrayList()));
/*     */     
/*  34 */     this.range = new Setting("Range", Integer.valueOf(100), Integer.valueOf(1), Integer.valueOf(128));
/*  35 */     this.limit = new Setting("Limit", new BooleanSettingGroup(true));
/*  36 */     this.limitCount = (new Setting("LimitCount", Integer.valueOf(50), Integer.valueOf(1), Integer.valueOf(2048))).addToGroup(this.limit);
/*  37 */     this.color = new Setting("Color", new ColorSetting(-16711681));
/*  38 */     this.illegals = new Setting("Illegals", Boolean.valueOf(true));
/*  39 */     this.tracers = new Setting("Tracers", Boolean.valueOf(false));
/*  40 */     this.fill = new Setting("Fill", Boolean.valueOf(true));
/*  41 */     this.outline = new Setting("Outline", Boolean.valueOf(true));
/*     */     
/*  43 */     this.searchThread = Executors.newSingleThreadExecutor();
/*  44 */     this.searchTimer = new Timer();
/*     */   }
/*     */   public static ArrayList<BlockVec> blocks = new ArrayList<>(); private final Setting<Integer> range; private final Setting<BooleanSettingGroup> limit; private final Setting<Integer> limitCount; private final Setting<ColorSetting> color; private final Setting<Boolean> illegals; private final Setting<Boolean> tracers; private final Setting<Boolean> fill; private final Setting<Boolean> outline; private final ExecutorService searchThread; private final Timer searchTimer; private long lastFrameTime;
/*     */   private boolean canContinue;
/*     */   
/*     */   public void onEnable() {
/*  50 */     blocks.clear();
/*  51 */     this.lastFrameTime = System.currentTimeMillis();
/*  52 */     this.canContinue = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  57 */     if (this.searchTimer.every(1000L) && this.canContinue) {
/*  58 */       CompletableFuture.supplyAsync(this::scan, this.searchThread).thenAcceptAsync(this::sync, class_156.method_18349());
/*  59 */       this.canContinue = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private ArrayList<BlockVec> scan() {
/*  64 */     ArrayList<BlockVec> blocks = new ArrayList<>();
/*  65 */     int startX = (int)Math.floor(mc.field_1724.method_23317() - ((Integer)this.range.getValue()).intValue());
/*  66 */     int endX = (int)Math.ceil(mc.field_1724.method_23317() + ((Integer)this.range.getValue()).intValue());
/*  67 */     int startY = mc.field_1687.method_31607() + 1;
/*  68 */     int endY = mc.field_1687.method_31600();
/*  69 */     int startZ = (int)Math.floor(mc.field_1724.method_23321() - ((Integer)this.range.getValue()).intValue());
/*  70 */     int endZ = (int)Math.ceil(mc.field_1724.method_23321() + ((Integer)this.range.getValue()).intValue());
/*     */     
/*  72 */     for (int x = startX; x <= endX; x++) {
/*  73 */       for (int y = startY; y <= endY; y++) {
/*  74 */         for (int z = startZ; z <= endZ; z++) {
/*  75 */           class_2338 pos = new class_2338(x, y, z);
/*  76 */           class_2680 bs = mc.field_1687.method_8320(pos);
/*  77 */           if (shouldAdd(bs.method_26204(), pos)) {
/*  78 */             blocks.add(new BlockVec(pos.method_10263(), pos.method_10264(), pos.method_10260()));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*  83 */     return blocks;
/*     */   }
/*     */   
/*     */   private void sync(ArrayList<BlockVec> b) {
/*  87 */     blocks = b;
/*  88 */     this.canContinue = true;
/*     */   }
/*     */   
/*     */   public void onRender3D(class_4587 stack) {
/*  92 */     if (fullNullCheck() || blocks.isEmpty())
/*  93 */       return;  int count = 0;
/*     */     
/*  95 */     if (mc.method_47599() < 8 && mc.field_1724.field_6012 > 100) {
/*  96 */       disable(ClientSettings.isRu() ? "Спасаем твой ПК :)" : "Saving ur pc :)");
/*     */       
/*     */       return;
/*     */     } 
/* 100 */     if (((Boolean)this.fill.getValue()).booleanValue() || ((Boolean)this.outline.getValue()).booleanValue()) {
/* 101 */       for (BlockVec vec : Lists.newArrayList(blocks)) {
/* 102 */         if (count > ((Integer)this.limitCount.getValue()).intValue() && ((BooleanSettingGroup)this.limit.getValue()).isEnabled()) {
/*     */           continue;
/*     */         }
/* 105 */         if (vec.getDistance(mc.field_1724.method_19538()) > this.range.getPow2Value()) {
/* 106 */           blocks.remove(vec);
/*     */           
/*     */           continue;
/*     */         } 
/* 110 */         class_238 b = new class_238(vec.x, vec.y, vec.z, vec.x + 1.0D, vec.y + 1.0D, vec.z + 1.0D);
/*     */         
/* 112 */         if (((Boolean)this.fill.getValue()).booleanValue()) {
/* 113 */           Render3DEngine.FILLED_QUEUE.add(new Render3DEngine.FillAction(b, ((ColorSetting)this.color.getValue()).getColorObject()));
/*     */         }
/* 115 */         if (((Boolean)this.outline.getValue()).booleanValue()) {
/* 116 */           Render3DEngine.OUTLINE_QUEUE.add(new Render3DEngine.OutlineAction(b, ((ColorSetting)this.color.getValue()).getColorObject(), 2.0F));
/*     */         }
/* 118 */         if (((Boolean)this.tracers.getValue()).booleanValue()) {
/*     */ 
/*     */ 
/*     */           
/* 122 */           class_243 vec2 = (new class_243(0.0D, 0.0D, 75.0D)).method_1037(-((float)Math.toRadians(mc.field_1773.method_19418().method_19329()))).method_1024(-((float)Math.toRadians(mc.field_1773.method_19418().method_19330()))).method_1019(mc.field_1719.method_33571());
/*     */           
/* 124 */           Render3DEngine.drawLineDebug(vec2, vec.getVector(), ((ColorSetting)this.color.getValue()).getColorObject());
/*     */         } 
/* 126 */         count++;
/*     */       } 
/*     */     }
/* 129 */     this.lastFrameTime = System.currentTimeMillis();
/*     */   }
/*     */   
/*     */   private boolean shouldAdd(class_2248 block, class_2338 pos) {
/* 133 */     if (block instanceof net.minecraft.class_2189) return false; 
/* 134 */     if (((ItemSelectSetting)this.selectedBlocks.getValue()).contains(block)) return true; 
/* 135 */     if (((Boolean)this.illegals.getValue()).booleanValue()) return isIllegal(block, pos); 
/* 136 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isIllegal(class_2248 block, class_2338 pos) {
/* 140 */     if (block instanceof net.minecraft.class_2288 || block instanceof net.minecraft.class_2213) return true;
/*     */     
/* 142 */     if (block == class_2246.field_9987) {
/* 143 */       if (!PlayerUtility.isInHell()) {
/* 144 */         return (pos.method_10264() > 4);
/*     */       }
/* 146 */       return (pos.method_10264() > 127 || (pos.method_10264() < 123 && pos.method_10264() > 4));
/*     */     } 
/* 148 */     return false;
/*     */   }
/*     */   public static final class BlockVec extends Record { private final double x; private final double y; private final double z;
/* 151 */     public BlockVec(double x, double y, double z) { this.x = x; this.y = y; this.z = z; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lthunder/hack/features/modules/render/BlockESP$BlockVec;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #151	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 151 */       //   0	7	0	this	Lthunder/hack/features/modules/render/BlockESP$BlockVec; } public double x() { return this.x; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lthunder/hack/features/modules/render/BlockESP$BlockVec;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #151	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lthunder/hack/features/modules/render/BlockESP$BlockVec; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lthunder/hack/features/modules/render/BlockESP$BlockVec;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #151	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lthunder/hack/features/modules/render/BlockESP$BlockVec;
/* 151 */       //   0	8	1	o	Ljava/lang/Object; } public double y() { return this.y; } public double z() { return this.z; }
/*     */      public double getDistance(@NotNull class_243 v) {
/* 153 */       double dx = this.x - v.field_1352;
/* 154 */       double dy = this.y - v.field_1351;
/* 155 */       double dz = this.z - v.field_1350;
/* 156 */       return dx * dx + dy * dy + dz * dz;
/*     */     }
/*     */     
/*     */     public class_243 getVector() {
/* 160 */       return new class_243(this.x + 0.5D, this.y + 0.5D, this.z + 0.5D);
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\render\BlockESP.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */