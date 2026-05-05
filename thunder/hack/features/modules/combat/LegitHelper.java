/*     */ package thunder.hack.features.modules.combat;
/*     */ import meteordevelopment.orbit.EventHandler;
/*     */ import net.minecraft.class_1268;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1511;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_1792;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_1802;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2350;
/*     */ import net.minecraft.class_238;
/*     */ import net.minecraft.class_239;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_2596;
/*     */ import net.minecraft.class_2824;
/*     */ import net.minecraft.class_2846;
/*     */ import net.minecraft.class_2868;
/*     */ import net.minecraft.class_2885;
/*     */ import net.minecraft.class_3532;
/*     */ import net.minecraft.class_3959;
/*     */ import net.minecraft.class_3965;
/*     */ import net.minecraft.class_3966;
/*     */ import net.minecraft.class_9278;
/*     */ import net.minecraft.class_9334;
/*     */ import thunder.hack.core.manager.client.AsyncManager;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.events.impl.EventEntitySpawn;
/*     */ import thunder.hack.events.impl.PacketEvent;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.injection.accesors.IMinecraftClient;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.setting.impl.Bind;
/*     */ import thunder.hack.setting.impl.BooleanSettingGroup;
/*     */ import thunder.hack.utility.Timer;
/*     */ import thunder.hack.utility.player.InteractionUtility;
/*     */ import thunder.hack.utility.player.InventoryUtility;
/*     */ import thunder.hack.utility.player.PlayerUtility;
/*     */ import thunder.hack.utility.player.SearchInvResult;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ import thunder.hack.utility.render.Render3DEngine;
/*     */ 
/*     */ public class LegitHelper extends Module {
/*     */   public LegitHelper() {
/*  45 */     super("LegitHelper", Module.Category.COMBAT);
/*     */ 
/*     */     
/*  48 */     this.minecarts = new Setting("Minecarts", new BooleanSettingGroup(true));
/*  49 */     this.maxDistance = (new Setting("MaxDistance", Float.valueOf(4.0F), Float.valueOf(2.0F), Float.valueOf(6.0F))).addToGroup(this.minecarts);
/*  50 */     this.refill = (new Setting("Refill", Boolean.valueOf(true))).addToGroup(this.minecarts);
/*  51 */     this.refillSlot = (new Setting("RefillSlot", Integer.valueOf(9), Integer.valueOf(1), Integer.valueOf(9), v -> ((Boolean)this.refill.getValue()).booleanValue())).addToGroup(this.minecarts);
/*     */     
/*  53 */     this.anchors = new Setting("Anchors", new BooleanSettingGroup(true));
/*  54 */     this.anchorDelay = (new Setting("AnchorDelay", Integer.valueOf(50), Integer.valueOf(5), Integer.valueOf(250))).addToGroup(this.anchors);
/*  55 */     this.anchorBind = (new Setting("AnchorBind", new Bind(89, false, false))).addToGroup(this.anchors);
/*     */     
/*  57 */     this.crystals = new Setting("Crystals", new BooleanSettingGroup(true));
/*  58 */     this.crystalDelay = (new Setting("CrystalDelay", Integer.valueOf(50), Integer.valueOf(5), Integer.valueOf(250))).addToGroup(this.crystals);
/*  59 */     this.crystalBind = (new Setting("CrystalBind", new Bind(85, false, false))).addToGroup(this.crystals);
/*  60 */     this.changePitch = (new Setting("ChangePitch", Boolean.valueOf(false))).addToGroup(this.crystals);
/*  61 */     this.crystalOptimizer = (new Setting("CrystalOptimizer", Boolean.valueOf(false))).addToGroup(this.crystals);
/*  62 */     this.switchBack = (new Setting("SwitchBack", Boolean.valueOf(false))).addToGroup(this.crystals);
/*     */     
/*  64 */     this.shieldBreaker = new Setting("ShieldBreaker", new BooleanSettingGroup(false));
/*  65 */     this.breakerDelay = (new Setting("BreakerDelay", Integer.valueOf(50), Integer.valueOf(5), Integer.valueOf(250))).addToGroup(this.shieldBreaker);
/*  66 */     this.swapBack = (new Setting("SwapBack", Boolean.valueOf(true))).addToGroup(this.shieldBreaker);
/*     */     
/*  68 */     this.windBoostJump = new Setting("WindBoostJump", new BooleanSettingGroup(true));
/*  69 */     this.windBoostBind = (new Setting("WindBoostBind", new Bind(73, false, false))).addToGroup(this.windBoostJump);
/*     */     
/*  71 */     this.crossBow = new Setting("CrossBow", new BooleanSettingGroup(true));
/*  72 */     this.crossBowBind = (new Setting("CrossBowBind", new Bind(79, false, false))).addToGroup(this.crossBow);
/*  73 */     this.cbswapBack = (new Setting("CBSwapBack", Boolean.valueOf(true))).addToGroup(this.crossBow);
/*     */     
/*  75 */     this.timer = new Timer();
/*  76 */     this.cbtimer = new Timer();
/*     */     
/*  78 */     this.lastCrystalVec = class_243.field_1353;
/*  79 */     this.rotationVec = class_243.field_1353;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Setting<BooleanSettingGroup> minecarts;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Setting<Float> maxDistance;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Setting<Boolean> refill;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Setting<Integer> refillSlot;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Setting<BooleanSettingGroup> anchors;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Setting<Integer> anchorDelay;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Setting<Bind> anchorBind;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Setting<BooleanSettingGroup> crystals;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Setting<Integer> crystalDelay;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Setting<Bind> crystalBind;
/*     */ 
/*     */ 
/*     */   
/*     */   private final Setting<Boolean> changePitch;
/*     */ 
/*     */ 
/*     */   
/*     */   private final Setting<Boolean> crystalOptimizer;
/*     */ 
/*     */ 
/*     */   
/*     */   private final Setting<Boolean> switchBack;
/*     */ 
/*     */ 
/*     */   
/*     */   private final Setting<BooleanSettingGroup> shieldBreaker;
/*     */ 
/*     */ 
/*     */   
/*     */   private final Setting<Integer> breakerDelay;
/*     */ 
/*     */ 
/*     */   
/*     */   private final Setting<Boolean> swapBack;
/*     */ 
/*     */ 
/*     */   
/*     */   private final Setting<BooleanSettingGroup> windBoostJump;
/*     */ 
/*     */ 
/*     */   
/*     */   private final Setting<Bind> windBoostBind;
/*     */ 
/*     */ 
/*     */   
/*     */   private final Setting<BooleanSettingGroup> crossBow;
/*     */ 
/*     */ 
/*     */   
/*     */   private final Setting<Bind> crossBowBind;
/*     */ 
/*     */ 
/*     */   
/*     */   private final Setting<Boolean> cbswapBack;
/*     */ 
/*     */ 
/*     */   
/*     */   private Timer timer;
/*     */ 
/*     */ 
/*     */   
/*     */   private Timer cbtimer;
/*     */ 
/*     */ 
/*     */   
/*     */   private class_243 lastCrystalVec;
/*     */ 
/*     */ 
/*     */   
/*     */   private class_243 rotationVec;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield anchors : Lthunder/hack/setting/Setting;
/*     */     //   4: invokevirtual getValue : ()Ljava/lang/Object;
/*     */     //   7: checkcast thunder/hack/setting/impl/BooleanSettingGroup
/*     */     //   10: invokevirtual isEnabled : ()Z
/*     */     //   13: ifeq -> 269
/*     */     //   16: aload_0
/*     */     //   17: aload_0
/*     */     //   18: getfield anchorBind : Lthunder/hack/setting/Setting;
/*     */     //   21: invokevirtual isKeyPressed : (Lthunder/hack/setting/Setting;)Z
/*     */     //   24: ifeq -> 269
/*     */     //   27: aload_0
/*     */     //   28: getfield timer : Lthunder/hack/utility/Timer;
/*     */     //   31: aload_0
/*     */     //   32: getfield anchorDelay : Lthunder/hack/setting/Setting;
/*     */     //   35: invokevirtual getValue : ()Ljava/lang/Object;
/*     */     //   38: checkcast java/lang/Integer
/*     */     //   41: invokevirtual intValue : ()I
/*     */     //   44: i2l
/*     */     //   45: ldc2_w 5
/*     */     //   48: lmul
/*     */     //   49: ldc2_w 100
/*     */     //   52: ladd
/*     */     //   53: invokevirtual every : (J)Z
/*     */     //   56: ifeq -> 269
/*     */     //   59: iconst_1
/*     */     //   60: anewarray net/minecraft/class_1792
/*     */     //   63: dup
/*     */     //   64: iconst_0
/*     */     //   65: getstatic net/minecraft/class_1802.field_8801 : Lnet/minecraft/class_1792;
/*     */     //   68: aastore
/*     */     //   69: invokestatic findItemInHotBar : ([Lnet/minecraft/class_1792;)Lthunder/hack/utility/player/SearchInvResult;
/*     */     //   72: invokevirtual slot : ()I
/*     */     //   75: istore_1
/*     */     //   76: iconst_1
/*     */     //   77: anewarray net/minecraft/class_1792
/*     */     //   80: dup
/*     */     //   81: iconst_0
/*     */     //   82: getstatic net/minecraft/class_1802.field_23141 : Lnet/minecraft/class_1792;
/*     */     //   85: aastore
/*     */     //   86: invokestatic findItemInHotBar : ([Lnet/minecraft/class_1792;)Lthunder/hack/utility/player/SearchInvResult;
/*     */     //   89: invokevirtual slot : ()I
/*     */     //   92: istore_2
/*     */     //   93: iload_1
/*     */     //   94: iconst_m1
/*     */     //   95: if_icmpeq -> 103
/*     */     //   98: iload_2
/*     */     //   99: iconst_m1
/*     */     //   100: if_icmpne -> 104
/*     */     //   103: return
/*     */     //   104: getstatic thunder/hack/features/modules/combat/LegitHelper.mc : Lnet/minecraft/class_310;
/*     */     //   107: getfield field_1724 : Lnet/minecraft/class_746;
/*     */     //   110: invokevirtual method_31548 : ()Lnet/minecraft/class_1661;
/*     */     //   113: getfield field_7545 : I
/*     */     //   116: istore_3
/*     */     //   117: getstatic thunder/hack/core/Managers.ASYNC : Lthunder/hack/core/manager/client/AsyncManager;
/*     */     //   120: iload_2
/*     */     //   121: <illegal opcode> run : (I)Ljava/lang/Runnable;
/*     */     //   126: invokevirtual run : (Ljava/lang/Runnable;)V
/*     */     //   129: getstatic thunder/hack/core/Managers.ASYNC : Lthunder/hack/core/manager/client/AsyncManager;
/*     */     //   132: <illegal opcode> run : ()Ljava/lang/Runnable;
/*     */     //   137: aload_0
/*     */     //   138: getfield anchorDelay : Lthunder/hack/setting/Setting;
/*     */     //   141: invokevirtual getValue : ()Ljava/lang/Object;
/*     */     //   144: checkcast java/lang/Integer
/*     */     //   147: invokevirtual intValue : ()I
/*     */     //   150: i2l
/*     */     //   151: invokevirtual run : (Ljava/lang/Runnable;J)V
/*     */     //   154: getstatic thunder/hack/core/Managers.ASYNC : Lthunder/hack/core/manager/client/AsyncManager;
/*     */     //   157: iload_1
/*     */     //   158: <illegal opcode> run : (I)Ljava/lang/Runnable;
/*     */     //   163: aload_0
/*     */     //   164: getfield anchorDelay : Lthunder/hack/setting/Setting;
/*     */     //   167: invokevirtual getValue : ()Ljava/lang/Object;
/*     */     //   170: checkcast java/lang/Integer
/*     */     //   173: invokevirtual intValue : ()I
/*     */     //   176: iconst_2
/*     */     //   177: imul
/*     */     //   178: i2l
/*     */     //   179: invokevirtual run : (Ljava/lang/Runnable;J)V
/*     */     //   182: getstatic thunder/hack/core/Managers.ASYNC : Lthunder/hack/core/manager/client/AsyncManager;
/*     */     //   185: <illegal opcode> run : ()Ljava/lang/Runnable;
/*     */     //   190: aload_0
/*     */     //   191: getfield anchorDelay : Lthunder/hack/setting/Setting;
/*     */     //   194: invokevirtual getValue : ()Ljava/lang/Object;
/*     */     //   197: checkcast java/lang/Integer
/*     */     //   200: invokevirtual intValue : ()I
/*     */     //   203: i2l
/*     */     //   204: ldc2_w 3
/*     */     //   207: lmul
/*     */     //   208: invokevirtual run : (Ljava/lang/Runnable;J)V
/*     */     //   211: getstatic thunder/hack/core/Managers.ASYNC : Lthunder/hack/core/manager/client/AsyncManager;
/*     */     //   214: iload_3
/*     */     //   215: <illegal opcode> run : (I)Ljava/lang/Runnable;
/*     */     //   220: aload_0
/*     */     //   221: getfield anchorDelay : Lthunder/hack/setting/Setting;
/*     */     //   224: invokevirtual getValue : ()Ljava/lang/Object;
/*     */     //   227: checkcast java/lang/Integer
/*     */     //   230: invokevirtual intValue : ()I
/*     */     //   233: iconst_4
/*     */     //   234: imul
/*     */     //   235: i2l
/*     */     //   236: invokevirtual run : (Ljava/lang/Runnable;J)V
/*     */     //   239: getstatic thunder/hack/core/Managers.ASYNC : Lthunder/hack/core/manager/client/AsyncManager;
/*     */     //   242: <illegal opcode> run : ()Ljava/lang/Runnable;
/*     */     //   247: aload_0
/*     */     //   248: getfield anchorDelay : Lthunder/hack/setting/Setting;
/*     */     //   251: invokevirtual getValue : ()Ljava/lang/Object;
/*     */     //   254: checkcast java/lang/Integer
/*     */     //   257: invokevirtual intValue : ()I
/*     */     //   260: i2l
/*     */     //   261: ldc2_w 5
/*     */     //   264: lmul
/*     */     //   265: invokevirtual run : (Ljava/lang/Runnable;J)V
/*     */     //   268: return
/*     */     //   269: getstatic thunder/hack/features/modules/combat/LegitHelper.mc : Lnet/minecraft/class_310;
/*     */     //   272: getfield field_1765 : Lnet/minecraft/class_239;
/*     */     //   275: astore_3
/*     */     //   276: aload_3
/*     */     //   277: instanceof net/minecraft/class_3966
/*     */     //   280: ifeq -> 302
/*     */     //   283: aload_3
/*     */     //   284: checkcast net/minecraft/class_3966
/*     */     //   287: astore_2
/*     */     //   288: aload_2
/*     */     //   289: invokevirtual method_17782 : ()Lnet/minecraft/class_1297;
/*     */     //   292: instanceof net/minecraft/class_1511
/*     */     //   295: ifeq -> 302
/*     */     //   298: iconst_1
/*     */     //   299: goto -> 303
/*     */     //   302: iconst_0
/*     */     //   303: istore_1
/*     */     //   304: getstatic thunder/hack/features/modules/combat/LegitHelper.mc : Lnet/minecraft/class_310;
/*     */     //   307: getfield field_1765 : Lnet/minecraft/class_239;
/*     */     //   310: astore #4
/*     */     //   312: aload #4
/*     */     //   314: instanceof net/minecraft/class_3965
/*     */     //   317: ifeq -> 352
/*     */     //   320: aload #4
/*     */     //   322: checkcast net/minecraft/class_3965
/*     */     //   325: astore_3
/*     */     //   326: getstatic thunder/hack/features/modules/combat/LegitHelper.mc : Lnet/minecraft/class_310;
/*     */     //   329: getfield field_1687 : Lnet/minecraft/class_638;
/*     */     //   332: aload_3
/*     */     //   333: invokevirtual method_17777 : ()Lnet/minecraft/class_2338;
/*     */     //   336: invokevirtual method_8320 : (Lnet/minecraft/class_2338;)Lnet/minecraft/class_2680;
/*     */     //   339: invokevirtual method_26204 : ()Lnet/minecraft/class_2248;
/*     */     //   342: getstatic net/minecraft/class_2246.field_10540 : Lnet/minecraft/class_2248;
/*     */     //   345: if_acmpne -> 352
/*     */     //   348: iconst_1
/*     */     //   349: goto -> 353
/*     */     //   352: iconst_0
/*     */     //   353: istore_2
/*     */     //   354: aload_0
/*     */     //   355: getfield crystals : Lthunder/hack/setting/Setting;
/*     */     //   358: invokevirtual getValue : ()Ljava/lang/Object;
/*     */     //   361: checkcast thunder/hack/setting/impl/BooleanSettingGroup
/*     */     //   364: invokevirtual isEnabled : ()Z
/*     */     //   367: ifeq -> 608
/*     */     //   370: aload_0
/*     */     //   371: aload_0
/*     */     //   372: getfield crystalBind : Lthunder/hack/setting/Setting;
/*     */     //   375: invokevirtual isKeyPressed : (Lthunder/hack/setting/Setting;)Z
/*     */     //   378: ifeq -> 608
/*     */     //   381: aload_0
/*     */     //   382: getfield timer : Lthunder/hack/utility/Timer;
/*     */     //   385: aload_0
/*     */     //   386: getfield crystalDelay : Lthunder/hack/setting/Setting;
/*     */     //   389: invokevirtual getValue : ()Ljava/lang/Object;
/*     */     //   392: checkcast java/lang/Integer
/*     */     //   395: invokevirtual intValue : ()I
/*     */     //   398: i2l
/*     */     //   399: iload_1
/*     */     //   400: ifeq -> 407
/*     */     //   403: lconst_1
/*     */     //   404: goto -> 420
/*     */     //   407: iload_2
/*     */     //   408: ifeq -> 417
/*     */     //   411: ldc2_w 2
/*     */     //   414: goto -> 420
/*     */     //   417: ldc2_w 4
/*     */     //   420: lmul
/*     */     //   421: invokevirtual every : (J)Z
/*     */     //   424: ifeq -> 608
/*     */     //   427: iconst_1
/*     */     //   428: anewarray net/minecraft/class_1792
/*     */     //   431: dup
/*     */     //   432: iconst_0
/*     */     //   433: getstatic net/minecraft/class_1802.field_8301 : Lnet/minecraft/class_1792;
/*     */     //   436: aastore
/*     */     //   437: invokestatic findItemInHotBar : ([Lnet/minecraft/class_1792;)Lthunder/hack/utility/player/SearchInvResult;
/*     */     //   440: invokevirtual slot : ()I
/*     */     //   443: istore_3
/*     */     //   444: iconst_1
/*     */     //   445: anewarray net/minecraft/class_2248
/*     */     //   448: dup
/*     */     //   449: iconst_0
/*     */     //   450: getstatic net/minecraft/class_2246.field_10540 : Lnet/minecraft/class_2248;
/*     */     //   453: aastore
/*     */     //   454: invokestatic findBlockInHotBar : ([Lnet/minecraft/class_2248;)Lthunder/hack/utility/player/SearchInvResult;
/*     */     //   457: invokevirtual slot : ()I
/*     */     //   460: istore #4
/*     */     //   462: iload #4
/*     */     //   464: iconst_m1
/*     */     //   465: if_icmpeq -> 486
/*     */     //   468: iload_3
/*     */     //   469: iconst_m1
/*     */     //   470: if_icmpeq -> 486
/*     */     //   473: iload_3
/*     */     //   474: bipush #9
/*     */     //   476: if_icmpge -> 486
/*     */     //   479: iload #4
/*     */     //   481: bipush #9
/*     */     //   483: if_icmplt -> 487
/*     */     //   486: return
/*     */     //   487: iload_1
/*     */     //   488: ifeq -> 531
/*     */     //   491: getstatic thunder/hack/features/modules/combat/LegitHelper.mc : Lnet/minecraft/class_310;
/*     */     //   494: getfield field_1761 : Lnet/minecraft/class_636;
/*     */     //   497: getstatic thunder/hack/features/modules/combat/LegitHelper.mc : Lnet/minecraft/class_310;
/*     */     //   500: getfield field_1724 : Lnet/minecraft/class_746;
/*     */     //   503: getstatic thunder/hack/features/modules/combat/LegitHelper.mc : Lnet/minecraft/class_310;
/*     */     //   506: getfield field_1765 : Lnet/minecraft/class_239;
/*     */     //   509: checkcast net/minecraft/class_3966
/*     */     //   512: invokevirtual method_17782 : ()Lnet/minecraft/class_1297;
/*     */     //   515: invokevirtual method_2918 : (Lnet/minecraft/class_1657;Lnet/minecraft/class_1297;)V
/*     */     //   518: getstatic thunder/hack/features/modules/combat/LegitHelper.mc : Lnet/minecraft/class_310;
/*     */     //   521: getfield field_1724 : Lnet/minecraft/class_746;
/*     */     //   524: getstatic net/minecraft/class_1268.field_5808 : Lnet/minecraft/class_1268;
/*     */     //   527: invokevirtual method_6104 : (Lnet/minecraft/class_1268;)V
/*     */     //   530: return
/*     */     //   531: getstatic thunder/hack/features/modules/combat/LegitHelper.mc : Lnet/minecraft/class_310;
/*     */     //   534: getfield field_1724 : Lnet/minecraft/class_746;
/*     */     //   537: invokevirtual method_31548 : ()Lnet/minecraft/class_1661;
/*     */     //   540: getfield field_7545 : I
/*     */     //   543: istore #5
/*     */     //   545: iload_2
/*     */     //   546: ifne -> 592
/*     */     //   549: getstatic thunder/hack/features/modules/combat/LegitHelper.mc : Lnet/minecraft/class_310;
/*     */     //   552: getfield field_1724 : Lnet/minecraft/class_746;
/*     */     //   555: invokevirtual method_31548 : ()Lnet/minecraft/class_1661;
/*     */     //   558: iload #4
/*     */     //   560: putfield field_7545 : I
/*     */     //   563: getstatic thunder/hack/features/modules/combat/LegitHelper.mc : Lnet/minecraft/class_310;
/*     */     //   566: invokevirtual method_1562 : ()Lnet/minecraft/class_634;
/*     */     //   569: new net/minecraft/class_2868
/*     */     //   572: dup
/*     */     //   573: iload #4
/*     */     //   575: invokespecial <init> : (I)V
/*     */     //   578: invokevirtual method_52787 : (Lnet/minecraft/class_2596;)V
/*     */     //   581: getstatic thunder/hack/features/modules/combat/LegitHelper.mc : Lnet/minecraft/class_310;
/*     */     //   584: checkcast thunder/hack/injection/accesors/IMinecraftClient
/*     */     //   587: invokeinterface idoItemUse : ()V
/*     */     //   592: getstatic thunder/hack/core/Managers.ASYNC : Lthunder/hack/core/manager/client/AsyncManager;
/*     */     //   595: aload_0
/*     */     //   596: iload_2
/*     */     //   597: iload_3
/*     */     //   598: iload #5
/*     */     //   600: <illegal opcode> run : (Lthunder/hack/features/modules/combat/LegitHelper;ZII)Ljava/lang/Runnable;
/*     */     //   605: invokevirtual run : (Ljava/lang/Runnable;)V
/*     */     //   608: aload_0
/*     */     //   609: getfield shieldBreaker : Lthunder/hack/setting/Setting;
/*     */     //   612: invokevirtual getValue : ()Ljava/lang/Object;
/*     */     //   615: checkcast thunder/hack/setting/impl/BooleanSettingGroup
/*     */     //   618: invokevirtual isEnabled : ()Z
/*     */     //   621: ifeq -> 778
/*     */     //   624: getstatic thunder/hack/features/modules/combat/LegitHelper.mc : Lnet/minecraft/class_310;
/*     */     //   627: getfield field_1765 : Lnet/minecraft/class_239;
/*     */     //   630: astore #5
/*     */     //   632: aload #5
/*     */     //   634: instanceof net/minecraft/class_3966
/*     */     //   637: ifeq -> 778
/*     */     //   640: aload #5
/*     */     //   642: checkcast net/minecraft/class_3966
/*     */     //   645: astore_3
/*     */     //   646: aload_3
/*     */     //   647: invokevirtual method_17782 : ()Lnet/minecraft/class_1297;
/*     */     //   650: astore #5
/*     */     //   652: aload #5
/*     */     //   654: instanceof net/minecraft/class_1657
/*     */     //   657: ifeq -> 778
/*     */     //   660: aload #5
/*     */     //   662: checkcast net/minecraft/class_1657
/*     */     //   665: astore #4
/*     */     //   667: getstatic thunder/hack/core/Managers.FRIEND : Lthunder/hack/core/manager/player/FriendManager;
/*     */     //   670: aload #4
/*     */     //   672: invokevirtual isFriend : (Lnet/minecraft/class_1657;)Z
/*     */     //   675: ifne -> 778
/*     */     //   678: aload #4
/*     */     //   680: invokevirtual method_6079 : ()Lnet/minecraft/class_1799;
/*     */     //   683: invokevirtual method_7909 : ()Lnet/minecraft/class_1792;
/*     */     //   686: getstatic net/minecraft/class_1802.field_8255 : Lnet/minecraft/class_1792;
/*     */     //   689: if_acmpeq -> 706
/*     */     //   692: aload #4
/*     */     //   694: invokevirtual method_6047 : ()Lnet/minecraft/class_1799;
/*     */     //   697: invokevirtual method_7909 : ()Lnet/minecraft/class_1792;
/*     */     //   700: getstatic net/minecraft/class_1802.field_8255 : Lnet/minecraft/class_1792;
/*     */     //   703: if_acmpne -> 778
/*     */     //   706: aload #4
/*     */     //   708: invokevirtual method_6030 : ()Lnet/minecraft/class_1799;
/*     */     //   711: invokevirtual method_7909 : ()Lnet/minecraft/class_1792;
/*     */     //   714: getstatic net/minecraft/class_1802.field_8255 : Lnet/minecraft/class_1792;
/*     */     //   717: if_acmpne -> 778
/*     */     //   720: aload_0
/*     */     //   721: getfield timer : Lthunder/hack/utility/Timer;
/*     */     //   724: ldc2_w 500
/*     */     //   727: invokevirtual every : (J)Z
/*     */     //   730: ifeq -> 778
/*     */     //   733: invokestatic getAxeHotBar : ()Lthunder/hack/utility/player/SearchInvResult;
/*     */     //   736: invokevirtual slot : ()I
/*     */     //   739: istore #5
/*     */     //   741: iload #5
/*     */     //   743: iconst_m1
/*     */     //   744: if_icmpne -> 748
/*     */     //   747: return
/*     */     //   748: getstatic thunder/hack/features/modules/combat/LegitHelper.mc : Lnet/minecraft/class_310;
/*     */     //   751: getfield field_1724 : Lnet/minecraft/class_746;
/*     */     //   754: invokevirtual method_31548 : ()Lnet/minecraft/class_1661;
/*     */     //   757: getfield field_7545 : I
/*     */     //   760: istore #6
/*     */     //   762: getstatic thunder/hack/core/Managers.ASYNC : Lthunder/hack/core/manager/client/AsyncManager;
/*     */     //   765: aload_0
/*     */     //   766: iload #5
/*     */     //   768: iload #6
/*     */     //   770: <illegal opcode> run : (Lthunder/hack/features/modules/combat/LegitHelper;II)Ljava/lang/Runnable;
/*     */     //   775: invokevirtual run : (Ljava/lang/Runnable;)V
/*     */     //   778: aload_0
/*     */     //   779: getfield minecarts : Lthunder/hack/setting/Setting;
/*     */     //   782: invokevirtual getValue : ()Ljava/lang/Object;
/*     */     //   785: checkcast thunder/hack/setting/impl/BooleanSettingGroup
/*     */     //   788: invokevirtual isEnabled : ()Z
/*     */     //   791: ifeq -> 892
/*     */     //   794: aload_0
/*     */     //   795: getfield refill : Lthunder/hack/setting/Setting;
/*     */     //   798: invokevirtual getValue : ()Ljava/lang/Object;
/*     */     //   801: checkcast java/lang/Boolean
/*     */     //   804: invokevirtual booleanValue : ()Z
/*     */     //   807: ifeq -> 892
/*     */     //   810: getstatic thunder/hack/features/modules/combat/LegitHelper.mc : Lnet/minecraft/class_310;
/*     */     //   813: getfield field_1724 : Lnet/minecraft/class_746;
/*     */     //   816: invokevirtual method_31548 : ()Lnet/minecraft/class_1661;
/*     */     //   819: aload_0
/*     */     //   820: getfield refillSlot : Lthunder/hack/setting/Setting;
/*     */     //   823: invokevirtual getValue : ()Ljava/lang/Object;
/*     */     //   826: checkcast java/lang/Integer
/*     */     //   829: invokevirtual intValue : ()I
/*     */     //   832: iconst_1
/*     */     //   833: isub
/*     */     //   834: invokevirtual method_5438 : (I)Lnet/minecraft/class_1799;
/*     */     //   837: invokevirtual method_7909 : ()Lnet/minecraft/class_1792;
/*     */     //   840: getstatic net/minecraft/class_1802.field_8069 : Lnet/minecraft/class_1792;
/*     */     //   843: if_acmpeq -> 892
/*     */     //   846: iconst_1
/*     */     //   847: anewarray net/minecraft/class_1792
/*     */     //   850: dup
/*     */     //   851: iconst_0
/*     */     //   852: getstatic net/minecraft/class_1802.field_8069 : Lnet/minecraft/class_1792;
/*     */     //   855: aastore
/*     */     //   856: invokestatic findItemInInventory : ([Lnet/minecraft/class_1792;)Lthunder/hack/utility/player/SearchInvResult;
/*     */     //   859: astore_3
/*     */     //   860: aload_3
/*     */     //   861: invokevirtual found : ()Z
/*     */     //   864: ifeq -> 892
/*     */     //   867: aload_3
/*     */     //   868: invokevirtual slot : ()I
/*     */     //   871: aload_0
/*     */     //   872: getfield refillSlot : Lthunder/hack/setting/Setting;
/*     */     //   875: invokevirtual getValue : ()Ljava/lang/Object;
/*     */     //   878: checkcast java/lang/Integer
/*     */     //   881: invokevirtual intValue : ()I
/*     */     //   884: iconst_1
/*     */     //   885: isub
/*     */     //   886: getstatic net/minecraft/class_1713.field_7791 : Lnet/minecraft/class_1713;
/*     */     //   889: invokestatic clickSlot : (IILnet/minecraft/class_1713;)V
/*     */     //   892: aload_0
/*     */     //   893: getfield crossBow : Lthunder/hack/setting/Setting;
/*     */     //   896: invokevirtual getValue : ()Ljava/lang/Object;
/*     */     //   899: checkcast thunder/hack/setting/impl/BooleanSettingGroup
/*     */     //   902: invokevirtual isEnabled : ()Z
/*     */     //   905: ifeq -> 982
/*     */     //   908: aload_0
/*     */     //   909: aload_0
/*     */     //   910: getfield crossBowBind : Lthunder/hack/setting/Setting;
/*     */     //   913: invokevirtual isKeyPressed : (Lthunder/hack/setting/Setting;)Z
/*     */     //   916: ifeq -> 982
/*     */     //   919: aload_0
/*     */     //   920: getfield cbtimer : Lthunder/hack/utility/Timer;
/*     */     //   923: ldc2_w 300
/*     */     //   926: invokevirtual every : (J)Z
/*     */     //   929: ifeq -> 982
/*     */     //   932: <illegal opcode> isValid : ()Lthunder/hack/utility/player/InventoryUtility$Searcher;
/*     */     //   937: invokestatic findInHotBar : (Lthunder/hack/utility/player/InventoryUtility$Searcher;)Lthunder/hack/utility/player/SearchInvResult;
/*     */     //   940: astore_3
/*     */     //   941: aload_3
/*     */     //   942: invokevirtual found : ()Z
/*     */     //   945: ifeq -> 982
/*     */     //   948: aload_3
/*     */     //   949: invokevirtual slot : ()I
/*     */     //   952: invokestatic saveAndSwitchTo : (I)V
/*     */     //   955: <illegal opcode> predict : ()Lnet/minecraft/class_7204;
/*     */     //   960: invokestatic sendSequencedPacket : (Lnet/minecraft/class_7204;)V
/*     */     //   963: aload_0
/*     */     //   964: getfield cbswapBack : Lthunder/hack/setting/Setting;
/*     */     //   967: invokevirtual getValue : ()Ljava/lang/Object;
/*     */     //   970: checkcast java/lang/Boolean
/*     */     //   973: invokevirtual booleanValue : ()Z
/*     */     //   976: ifeq -> 982
/*     */     //   979: invokestatic returnSlot : ()V
/*     */     //   982: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #83	-> 0
/*     */     //   #84	-> 59
/*     */     //   #85	-> 76
/*     */     //   #86	-> 93
/*     */     //   #88	-> 104
/*     */     //   #90	-> 117
/*     */     //   #95	-> 129
/*     */     //   #97	-> 154
/*     */     //   #100	-> 167
/*     */     //   #97	-> 179
/*     */     //   #102	-> 182
/*     */     //   #104	-> 211
/*     */     //   #107	-> 224
/*     */     //   #104	-> 236
/*     */     //   #109	-> 239
/*     */     //   #111	-> 268
/*     */     //   #114	-> 269
/*     */     //   #115	-> 304
/*     */     //   #117	-> 354
/*     */     //   #118	-> 427
/*     */     //   #119	-> 444
/*     */     //   #120	-> 462
/*     */     //   #122	-> 487
/*     */     //   #123	-> 491
/*     */     //   #124	-> 518
/*     */     //   #125	-> 530
/*     */     //   #128	-> 531
/*     */     //   #130	-> 545
/*     */     //   #131	-> 549
/*     */     //   #132	-> 563
/*     */     //   #133	-> 581
/*     */     //   #136	-> 592
/*     */     //   #154	-> 608
/*     */     //   #155	-> 624
/*     */     //   #156	-> 646
/*     */     //   #157	-> 672
/*     */     //   #158	-> 680
/*     */     //   #159	-> 708
/*     */     //   #161	-> 733
/*     */     //   #162	-> 741
/*     */     //   #163	-> 747
/*     */     //   #165	-> 748
/*     */     //   #167	-> 762
/*     */     //   #184	-> 778
/*     */     //   #185	-> 810
/*     */     //   #186	-> 846
/*     */     //   #187	-> 860
/*     */     //   #188	-> 867
/*     */     //   #191	-> 892
/*     */     //   #192	-> 932
/*     */     //   #193	-> 941
/*     */     //   #194	-> 948
/*     */     //   #195	-> 955
/*     */     //   #196	-> 963
/*     */     //   #197	-> 979
/*     */     //   #200	-> 982
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   76	193	1	glowSlot	I
/*     */     //   93	176	2	anchorSlot	I
/*     */     //   117	152	3	prevSlot	I
/*     */     //   288	14	2	ehr	Lnet/minecraft/class_3966;
/*     */     //   326	26	3	bhr	Lnet/minecraft/class_3965;
/*     */     //   444	164	3	crystalSlot	I
/*     */     //   462	146	4	obbySlot	I
/*     */     //   545	63	5	prevSlot	I
/*     */     //   741	37	5	axeSlot	I
/*     */     //   762	16	6	prevSlot	I
/*     */     //   646	132	3	ehr	Lnet/minecraft/class_3966;
/*     */     //   667	111	4	pl	Lnet/minecraft/class_1657;
/*     */     //   860	32	3	result	Lthunder/hack/utility/player/SearchInvResult;
/*     */     //   941	41	3	result	Lthunder/hack/utility/player/SearchInvResult;
/*     */     //   0	983	0	this	Lthunder/hack/features/modules/combat/LegitHelper;
/*     */     //   304	679	1	crystalAtCrosshair	Z
/*     */     //   354	629	2	obbyAtCrosshair	Z
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onEntitySpawn(EventEntitySpawn e) {
/* 205 */     class_1297 class_1297 = e.getEntity(); if (class_1297 instanceof class_1511) { class_1511 cr = (class_1511)class_1297; if (e.getEntity().method_5707(this.lastCrystalVec) < 4.0D) {
/* 206 */         this.lastCrystalVec = class_243.field_1353;
/* 207 */         if (((Boolean)this.changePitch.getValue()).booleanValue()) {
/* 208 */           float pitch = InteractionUtility.calculateAngle(cr.method_19538().method_1031(0.0D, 0.15D, 0.0D))[1];
/* 209 */           double gcdFix = Math.pow(((Double)mc.field_1690.method_42495().method_41753()).doubleValue() * 0.6D + 0.2D, 3.0D) * 1.2D;
/* 210 */           mc.field_1724.method_36457((float)(pitch - (pitch - mc.field_1724.method_36455()) % gcdFix));
/*     */         } 
/* 212 */         mc.field_1761.method_2918((class_1657)mc.field_1724, e.getEntity());
/* 213 */         mc.field_1724.method_6104(class_1268.field_5808);
/*     */       }  }
/*     */   
/*     */   }
/*     */   @EventHandler
/*     */   public void onPacketSend(PacketEvent.Send event) {
/* 219 */     if (((Boolean)this.crystalOptimizer.getValue()).booleanValue() && event.getPacket() instanceof class_2824 && 
/* 220 */       Criticals.getInteractType((class_2824)event.getPacket()) == Criticals.InteractType.ATTACK) { class_1297 class_1297 = Criticals.getEntity((class_2824)event.getPacket()); if (class_1297 instanceof class_1511) { class_1511 c = (class_1511)class_1297;
/* 221 */         if (!ModuleManager.autoCrystal.isEnabled()) {
/* 222 */           c.method_5768();
/* 223 */           c.method_31745(class_1297.class_5529.field_26998);
/* 224 */           c.method_36209();
/*     */         }  }
/*     */        }
/*     */   
/*     */   } @EventHandler
/*     */   public void onPacketSendPost(PacketEvent.SendPost event) {
/* 230 */     class_2596 class_2596 = event.getPacket(); if (class_2596 instanceof class_2846) { class_2846 action = (class_2846)class_2596; if (action.method_12363() == class_2846.class_2847.field_12974 && (
/* 231 */         (BooleanSettingGroup)this.minecarts.getValue()).isEnabled() && mc.field_1724.method_6047().method_7909() == class_1802.field_8102) {
/* 232 */         class_2338 bp = calcTrajectory(mc.field_1724.method_36454());
/* 233 */         if (bp != null && PlayerUtility.squaredDistanceFromEyes(bp.method_46558()) <= this.maxDistance.getPow2Value() && PlayerUtility.squaredDistanceFromEyes(bp.method_46558()) > 3.0F) {
/*     */           
/* 235 */           SearchInvResult baseResult = InventoryUtility.findItemInHotBar(new class_1792[] { class_1802.field_8129, class_1802.field_8655, class_1802.field_8211, class_1802.field_8848 });
/* 236 */           SearchInvResult cartResult = InventoryUtility.findItemInHotBar(new class_1792[] { class_1802.field_8069 });
/*     */           
/* 238 */           if (baseResult.found() && cartResult.found()) {
/* 239 */             InventoryUtility.saveSlot();
/* 240 */             baseResult.switchTo();
/* 241 */             sendSequencedPacket(s -> new class_2885(class_1268.field_5808, new class_3965(new class_243(bp.method_10263() + 0.5D, bp.method_10084().method_10264(), bp.method_10260() + 0.0D), class_2350.field_11036, bp, false), s));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 247 */             this.rotationVec = bp.method_10084().method_46558();
/* 248 */             cartResult.switchTo();
/* 249 */             sendSequencedPacket(s -> new class_2885(class_1268.field_5808, new class_3965(new class_243(bp.method_10263() + 0.5D, bp.method_10084().method_10264() + 0.125D, bp.method_10260() + 0.5D), class_2350.field_11036, bp.method_10084(), false), s));
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 254 */             InventoryUtility.returnSlot();
/*     */           } 
/*     */         } 
/*     */       }  }
/*     */   
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onSync(EventSync e) {
/* 263 */     if (this.rotationVec != null) {
/* 264 */       float[] angle = InteractionUtility.calculateAngle(this.rotationVec);
/* 265 */       mc.field_1724.method_36456(angle[0]);
/* 266 */       mc.field_1724.method_36457(angle[1]);
/* 267 */       this.rotationVec = null;
/*     */     } 
/*     */     
/* 270 */     if (isKeyPressed(this.windBoostBind) && mc.field_1724.method_24828()) {
/* 271 */       SearchInvResult result = InventoryUtility.findItemInHotBar(new class_1792[] { class_1802.field_49098 });
/* 272 */       if (result.found()) {
/* 273 */         mc.field_1724.method_36457(90.0F);
/* 274 */         mc.field_1724.method_6043();
/* 275 */         InventoryUtility.saveAndSwitchTo(result.slot());
/* 276 */         mc.field_1761.method_2919((class_1657)mc.field_1724, class_1268.field_5808);
/* 277 */         InventoryUtility.returnSlot();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private class_2338 calcTrajectory(float yaw) {
/* 283 */     double x = Render2DEngine.interpolate(mc.field_1724.field_6014, mc.field_1724.method_23317(), Render3DEngine.getTickDelta());
/* 284 */     double y = Render2DEngine.interpolate(mc.field_1724.field_6036, mc.field_1724.method_23318(), Render3DEngine.getTickDelta());
/* 285 */     double z = Render2DEngine.interpolate(mc.field_1724.field_5969, mc.field_1724.method_23321(), Render3DEngine.getTickDelta());
/*     */     
/* 287 */     y = y + mc.field_1724.method_18381(mc.field_1724.method_18376()) - 0.1000000014901161D;
/*     */     
/* 289 */     double motionX = (-class_3532.method_15374(yaw / 180.0F * 3.1415927F) * class_3532.method_15362(mc.field_1724.method_36455() / 180.0F * 3.1415927F));
/* 290 */     double motionY = -class_3532.method_15374(mc.field_1724.method_36455() / 180.0F * 3.141593F);
/* 291 */     double motionZ = (class_3532.method_15362(yaw / 180.0F * 3.1415927F) * class_3532.method_15362(mc.field_1724.method_36455() / 180.0F * 3.1415927F));
/* 292 */     float power = mc.field_1724.method_6048() / 20.0F;
/*     */     
/* 294 */     power = (power * power + power * 2.0F) / 3.0F;
/* 295 */     if (power > 1.0F) {
/* 296 */       power = 1.0F;
/*     */     }
/*     */     
/* 299 */     float distance = class_3532.method_15355((float)(motionX * motionX + motionY * motionY + motionZ * motionZ));
/* 300 */     motionX /= distance;
/* 301 */     motionY /= distance;
/* 302 */     motionZ /= distance;
/*     */     
/* 304 */     float pow = power * 3.0F;
/* 305 */     motionX *= pow;
/* 306 */     motionY *= pow;
/* 307 */     motionZ *= pow;
/*     */     
/* 309 */     if (!mc.field_1724.method_24828()) {
/* 310 */       motionY += mc.field_1724.method_18798().method_10214();
/*     */     }
/*     */ 
/*     */     
/* 314 */     for (int i = 0; i < 300; i++) {
/* 315 */       class_243 lastPos = new class_243(x, y, z);
/* 316 */       x += motionX;
/* 317 */       y += motionY;
/* 318 */       z += motionZ;
/*     */       
/* 320 */       motionX *= 0.99D;
/* 321 */       motionY *= 0.99D;
/* 322 */       motionZ *= 0.99D;
/*     */ 
/*     */       
/* 325 */       motionY -= 0.05000000074505806D;
/* 326 */       class_243 pos = new class_243(x, y, z);
/*     */       
/* 328 */       for (class_1297 ent : mc.field_1687.method_18112()) {
/* 329 */         if (!(ent instanceof net.minecraft.class_1667) && !ent.equals(mc.field_1724) && 
/* 330 */           ent.method_5829().method_994(new class_238(x - 0.3D, y - 0.3D, z - 0.3D, x + 0.3D, y + 0.3D, z + 0.3D))) {
/* 331 */           return null;
/*     */         }
/*     */       } 
/* 334 */       class_3965 bhr = mc.field_1687.method_17742(new class_3959(lastPos, pos, class_3959.class_3960.field_17559, class_3959.class_242.field_1348, (class_1297)mc.field_1724));
/* 335 */       if (bhr != null && bhr.method_17783() == class_239.class_240.field_1332) {
/* 336 */         return bhr.method_17777();
/*     */       }
/*     */       
/* 339 */       if (y <= -65.0D)
/*     */         break; 
/* 341 */     }  return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\combat\LegitHelper.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */