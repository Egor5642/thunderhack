/*     */ package thunder.hack.core.manager.client;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_4587;
/*     */ import org.lwjgl.glfw.GLFW;
/*     */ import thunder.hack.ThunderHack;
/*     */ import thunder.hack.features.hud.HudElement;
/*     */ import thunder.hack.features.hud.impl.PVPResources;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.AntiServerAdd;
/*     */ import thunder.hack.features.modules.client.UnHook;
/*     */ import thunder.hack.features.modules.combat.LegitHelper;
/*     */ import thunder.hack.features.modules.combat.Surround;
/*     */ import thunder.hack.features.modules.misc.AutoAuth;
/*     */ import thunder.hack.features.modules.misc.AutoSign;
/*     */ import thunder.hack.features.modules.misc.ChorusExploit;
/*     */ import thunder.hack.features.modules.misc.Spammer;
/*     */ import thunder.hack.features.modules.movement.HoleAnchor;
/*     */ import thunder.hack.features.modules.player.NoEntityTrace;
/*     */ import thunder.hack.features.modules.render.AspectRatio;
/*     */ import thunder.hack.features.modules.render.BlockESP;
/*     */ import thunder.hack.features.modules.render.KillEffect;
/*     */ import thunder.hack.features.modules.render.Particles;
/*     */ import thunder.hack.features.modules.render.Tooltips;
/*     */ 
/*     */ public class ModuleManager implements IManager {
/*  31 */   public ArrayList<Module> modules = new ArrayList<>();
/*  32 */   public List<Module> sortedModules = new ArrayList<>();
/*  33 */   public List<Integer> activeMouseKeys = new ArrayList<>();
/*     */   
/*  35 */   private int checkTicks = 0;
/*     */ 
/*     */   
/*  38 */   public static PenisESP penisESP = new PenisESP();
/*  39 */   public static AntiPacketException antiPacketException = new AntiPacketException();
/*  40 */   public static LevitationControl levitationControl = new LevitationControl();
/*  41 */   public static InventoryCleaner inventoryCleaner = new InventoryCleaner();
/*  42 */   public static NoCommentExploit noCommentExploit = new NoCommentExploit();
/*  43 */   public static NoWaterCollision noWaterCollision = new NoWaterCollision();
/*  44 */   public static BaritoneSettings baritoneSettings = new BaritoneSettings();
/*  45 */   public static PortalInventory portalInventory = new PortalInventory();
/*  46 */   public static TotemPopCounter totemPopCounter = new TotemPopCounter();
/*  47 */   public static HotbarReplenish hotbarReplenish = new HotbarReplenish();
/*  48 */   public static DurabilityAlert durabilityAlert = new DurabilityAlert();
/*  49 */   public static AutoCrystalBase autoCrystalBase = new AutoCrystalBase();
/*  50 */   public static CrosshairArrows crosshairArrows = new CrosshairArrows();
/*  51 */   public static PearlBlockThrow pearlBlockThrow = new PearlBlockThrow();
/*  52 */   public static AutoCrystalInfo autoCrystalInfo = new AutoCrystalInfo();
/*  53 */   public static ChatTranslator chatTranslator = new ChatTranslator();
/*  54 */   public static PacketCanceler packetCanceler = new PacketCanceler();
/*  55 */   public static ClientSettings clientSettings = new ClientSettings();
/*  56 */   public static TimerIndicator timerIndicator = new TimerIndicator();
/*  57 */   public static ThunderHackGui thunderHackGui = new ThunderHackGui();
/*  58 */   public static NoServerRotate noServerRotate = new NoServerRotate();
/*  59 */   public static BreakHighLight breakHighLight = new BreakHighLight();
/*  60 */   public static BlockHighLight blockHighLight = new BlockHighLight();
/*  61 */   public static AntiBadEffects antiBadEffects = new AntiBadEffects();
/*  62 */   public static MouseElytraFix mouseElytraFix = new MouseElytraFix();
/*  63 */   public static TotemAnimation totemAnimation = new TotemAnimation();
/*  64 */   public static PortalGodMode portalGodMode = new PortalGodMode();
/*  65 */   public static OptifineCapes optifineCapes = new OptifineCapes();
/*  66 */   public static Notifications notifications = new Notifications();
/*  67 */   public static NoEntityTrace noEntityTrace = new NoEntityTrace();
/*  68 */   public static MessageAppend messageAppend = new MessageAppend();
/*  69 */   public static EntityControl entityControl = new EntityControl();
/*  70 */   public static ElytraReplace elytraReplace = new ElytraReplace();
/*  71 */   public static ChorusExploit chorusExploit = new ChorusExploit();
/*  72 */   public static MoreKnockback moreKnockback = new MoreKnockback();
/*  73 */   public static AntiServerAdd antiServerAdd = new AntiServerAdd();
/*  74 */   public static AntiLegitMiss antiLegitMiss = new AntiLegitMiss();
/*  75 */   public static AntiBallPlace antiBallPlace = new AntiBallPlace();
/*  76 */   public static TridentBoost tridentBoost = new TridentBoost();
/*  77 */   public static Trajectories trajectories = new Trajectories();
/*  78 */   public static TargetStrafe targetStrafe = new TargetStrafe();
/*  79 */   public static RadarRewrite radarRewrite = new RadarRewrite();
/*  80 */   public static PVPResources pvpResources = new PVPResources();
/*  81 */   public static NoServerSlot noServerSlot = new NoServerSlot();
/*  82 */   public static NoCameraClip noCameraClip = new NoCameraClip();
/*  83 */   public static ItemScroller itemScroller = new ItemScroller();
/*  84 */   public static HitParticles hitParticles = new HitParticles();
/*  85 */   public static ElytraRecast elytraRecast = new ElytraRecast();
/*  86 */   public static EbatteSratte ebatteSratte = new EbatteSratte();
/*  87 */   public static ChestStealer chestStealer = new ChestStealer();
/*  88 */   public static AutoTpAccept autoTpAccept = new AutoTpAccept();
/*  89 */   public static AntiServerRP antiServerRP = new AntiServerRP();
/*  90 */   public static TotemCounter totemCounter = new TotemCounter();
/*  91 */   public static PerfectDelay perfectDelay = new PerfectDelay();
/*  92 */   public static ServerHelper serverHelper = new ServerHelper();
/*  93 */   public static ChestCounter chestCounter = new ChestCounter();
/*  94 */   public static StashLogger stashLogger = new StashLogger();
/*  95 */   public static FastLatency fastLatency = new FastLatency();
/*  96 */   public static PearlChaser pearlChaser = new PearlChaser();
/*  97 */   public static WorldTweaks worldTweaks = new WorldTweaks();
/*  98 */   public static VisualRange visualRange = new VisualRange();
/*  99 */   public static Speedometer speedometer = new Speedometer();
/* 100 */   public static ReverseStep reverseStep = new ReverseStep();
/* 101 */   public static NoJumpDelay noJumpDelay = new NoJumpDelay();
/* 102 */   public static NameProtect nameProtect = new NameProtect();
/* 103 */   public static MiddleClick middleClick = new MiddleClick();
/* 104 */   public static LogoutSpots logoutSpots = new LogoutSpots();
/* 105 */   public static LagNotifier lagNotifier = new LagNotifier();
/* 106 */   public static BreadCrumbs breadCrumbs = new BreadCrumbs();
/* 107 */   public static AutoRespawn autoRespawn = new AutoRespawn();
/* 108 */   public static AutoCrystal autoCrystal = new AutoCrystal();
/* 109 */   public static EntitySpeed entitySpeed = new EntitySpeed();
/* 110 */   public static AspectRatio aspectRatio = new AspectRatio();
/* 111 */   public static ClientSpoof clientSpoof = new ClientSpoof();
/* 112 */   public static LegitHelper legitHelper = new LegitHelper();
/* 113 */   public static AutoAnchor autoAnchor = new AutoAnchor();
/* 114 */   public static WaterSpeed waterSpeed = new WaterSpeed();
/* 115 */   public static TriggerBot triggerBot = new TriggerBot();
/* 116 */   public static TPSCounter tpsCounter = new TPSCounter();
/* 117 */   public static StorageEsp storageEsp = new StorageEsp();
/* 118 */   public static StaffBoard staffBoard = new StaffBoard();
/* 119 */   public static PistonPush pistonPush = new PistonPush();
/* 120 */   public static PistonAura pistonAura = new PistonAura();
/* 121 */   public static NoInteract noInteract = new NoInteract();
/* 122 */   public static ModuleList moduleList = new ModuleList();
/* 123 */   public static KillEffect killEffect = new KillEffect();
/* 124 */   public static JumpCircle jumpCircle = new JumpCircle();
/* 125 */   public static HoleAnchor holeAnchor = new HoleAnchor();
/* 126 */   public static Fullbright fullbright = new Fullbright();
/* 127 */   public static FpsCounter fpsCounter = new FpsCounter();
/* 128 */   public static FakePlayer fakePlayer = new FakePlayer();
/* 129 */   public static ElytraSwap elytraSwap = new ElytraSwap();
/* 130 */   public static ElytraPlus elytraPlus = new ElytraPlus();
/* 131 */   public static AutoSprint autoSprint = new AutoSprint();
/* 132 */   public static AutoGApple autoGApple = new AutoGApple();
/* 133 */   public static AntiHunger antiHunger = new AntiHunger();
/* 134 */   public static Animations animations = new Animations();
/* 135 */   public static DamageTint damageTint = new DamageTint();
/* 136 */   public static AntiAttack antiAttack = new AntiAttack();
/* 137 */   public static GapplesHud gapplesHud = new GapplesHud();
/* 138 */   public static HitBubbles hitBubbles = new HitBubbles();
/* 139 */   public static AutoTrader autoTrader = new AutoTrader();
/* 140 */   public static KillStats killStats = new KillStats();
/* 141 */   public static AutoAnvil autoAnvil = new AutoAnvil();
/* 142 */   public static CandleHud candleHud = new CandleHud();
/* 143 */   public static Particles particles = new Particles();
/* 144 */   public static ToolSaver toolSaver = new ToolSaver();
/* 145 */   public static WayPoints wayPoints = new WayPoints();
/* 146 */   public static WaterMark waterMark = new WaterMark();
/* 147 */   public static ViewModel viewModel = new ViewModel();
/* 148 */   public static TunnelEsp tunnelEsp = new TunnelEsp();
/* 149 */   public static TickShift tickShift = new TickShift();
/* 150 */   public static TargetHud targetHud = new TargetHud();
/* 151 */   public static SpeedMine speedMine = new SpeedMine();
/* 152 */   public static PotionHud potionHud = new PotionHud();
/* 153 */   public static PearlBait pearlBait = new PearlBait();
/* 154 */   public static PacketFly packetFly = new PacketFly();
/* 155 */   public static MultiTask multitask = new MultiTask();
/* 156 */   public static LegacyHud legacyHud = new LegacyHud();
/* 157 */   public static HudEditor hudEditor = new HudEditor();
/* 158 */   public static Crosshair crosshair = new Crosshair();
/* 159 */   public static Criticals criticals = new Criticals();
/* 160 */   public static ChatUtils chatUtils = new ChatUtils();
/* 161 */   public static AutoTotem autoTotem = new AutoTotem();
/* 162 */   public static AutoLeave autoLeave = new AutoLeave();
/* 163 */   public static AutoFlyme autoFlyme = new AutoFlyme();
/* 164 */   public static AutoArmor autoArmor = new AutoArmor();
/* 165 */   public static AutoPrisonMine autoPrisonMine = new AutoPrisonMine();
/* 166 */   public static Cooldowns cooldowns = new Cooldowns();
/* 167 */   public static TapeMouse tapeMouse = new TapeMouse();
/* 168 */   public static Rotations rotations = new Rotations();
/* 169 */   public static MemoryHud memoryHud = new MemoryHud();
/* 170 */   public static Companion companion = new Companion();
/* 171 */   public static AntiCrash antiCrash = new AntiCrash();
/* 172 */   public static AutoGear autoGear = new AutoGear();
/* 173 */   public static ViewLock viewLock = new ViewLock();
/* 174 */   public static Velocity velocity = new Velocity();
/* 175 */   public static Tooltips tooltips = new Tooltips();
/* 176 */   public static Surround surround = new Surround();
/* 177 */   public static AutoRG autoRG = new AutoRG();
/* 178 */   public static Scaffold scaffold = new Scaffold();
/* 179 */   public static PopChams popChams = new PopChams();
/* 180 */   public static NoRender noRender = new NoRender();
/* 181 */   public static NameTags nameTags = new NameTags();
/* 182 */   public static LongJump longJump = new LongJump();
/* 183 */   public static KeyBinds keyBinds = new KeyBinds();
/* 184 */   public static HoleSnap holeSnap = new HoleSnap();
/* 185 */   public static HoleFill holeFill = new HoleFill();
/* 186 */   public static ExtraTab extraTab = new ExtraTab();
/* 187 */   public static ClickGui clickGui = new ClickGui();
/* 188 */   public static AutoTrap autoTrap = new AutoTrap();
/* 189 */   public static AutoTool autoTool = new AutoTool();
/* 190 */   public static SoundESP soundESP = new SoundESP();
/* 191 */   public static AutoSoup autoSoup = new AutoSoup();
/* 192 */   public static AutoFish autoFish = new AutoFish();
/* 193 */   public static AutoBuff autoBuff = new AutoBuff();
/* 194 */   public static AutoAuth autoAuth = new AutoAuth();
/* 195 */   public static ArmorHud armorHud = new ArmorHud();
/* 196 */   public static AirPlace airPlace = new AirPlace();
/* 197 */   public static SelfTrap selfTrap = new SelfTrap();
/* 198 */   public static AntiVoid antiVoid = new AntiVoid();
/* 199 */   public static KillFeed killFeed = new KillFeed();
/* 200 */   public static AutoWalk autoWalk = new AutoWalk();
/* 201 */   public static AutoSign autoSign = new AutoSign();
/* 202 */   public static BlockESP blockESP = new BlockESP();
/* 203 */   public static SafeWalk safeWalk = new SafeWalk();
/* 204 */   public static Windows windows = new Windows();
/* 205 */   public static Breaker breaker = new Breaker();
/* 206 */   public static AutoEat autoEat = new AutoEat();
/* 207 */   public static AntiAFK antiAFK = new AntiAFK();
/* 208 */   public static SoundFX soundFX = new SoundFX();
/* 209 */   public static AutoBed autoBed = new AutoBed();
/* 210 */   public static TNTAura tntAura = new TNTAura();
/* 211 */   public static VoidESP voidESP = new VoidESP();
/* 212 */   public static Tracker tracker = new Tracker();
/* 213 */   public static TpsSync tpsSync = new TpsSync();
/* 214 */   public static Spammer spammer = new Spammer();
/* 215 */   public static Shaders shaders = new Shaders();
/* 216 */   public static PingHud pingHud = new PingHud();
/* 217 */   public static ItemESP itemESP = new ItemESP();
/* 218 */   public static HoleESP holeESP = new HoleESP();
/* 219 */   public static GuiMove guiMove = new GuiMove();
/* 220 */   public static FreeCam freeCam = new FreeCam();
/* 221 */   public static FastUse fastUse = new FastUse();
/* 222 */   public static BowSpam bowSpam = new BowSpam();
/* 223 */   public static BowPop bowPop = new BowPop();
/* 224 */   public static BoatFly boatFly = new BoatFly();
/* 225 */   public static Blocker blocker = new Blocker();
/* 226 */   public static AutoWeb autoWeb = new AutoWeb();
/* 227 */   public static AntiWeb antiWeb = new AntiWeb();
/* 228 */   public static AntiBot antiBot = new AntiBot();
/* 229 */   public static AntiAim antiAim = new AntiAim();
/* 230 */   public static AutoSex autoSex = new AutoSex();
/* 231 */   public static Tracers tracers = new Tracers();
/* 232 */   public static Parkour parkour = new Parkour();
/* 233 */   public static ClickTP clickTP = new ClickTP();
/* 234 */   public static XCarry xCarry = new XCarry();
/* 235 */   public static Trails trails = new Trails();
/* 236 */   public static Strafe strafe = new Strafe();
/* 237 */   public static Spider spider = new Spider();
/* 238 */   public static NoSlow noSlow = new NoSlow();
/* 239 */   public static NoFall noFall = new NoFall();
/* 240 */   public static Hotbar hotbar = new Hotbar();
/* 241 */   public static HitBox hitBox = new HitBox();
/* 242 */   public static Flight flight = new Flight();
/* 243 */   public static Coords coords = new Coords();
/* 244 */   public static AutoPilot autoPilot = new AutoPilot();
/* 245 */   public static Burrow burrow = new Burrow();
/* 246 */   public static AutoEZ autoEZ = new AutoEZ();
/* 247 */   public static AimBot aimBot = new AimBot();
/* 248 */   public static Quiver quiver = new Quiver();
/* 249 */   public static NoPush noPush = new NoPush();
/* 250 */   public static UnHook unHook = new UnHook();
/* 251 */   public static Avoid avoid = new Avoid();
/* 252 */   public static Timer timer = new Timer();
/* 253 */   public static Regen regen = new Regen();
/* 254 */   public static Speed speed = new Speed();
/* 255 */   public static Reach reach = new Reach();
/* 256 */   public static Radar radar = new Radar();
/* 257 */   public static Nuker nuker = new Nuker();
/* 258 */   public static Media media = new Media();
/* 259 */   public static Ghost ghost = new Ghost();
/* 260 */   public static Chams chams = new Chams();
/* 261 */   public static Blink blink = new Blink();
/* 262 */   public static Phase phase = new Phase();
/* 263 */   public static NoBob noBob = new NoBob();
/* 264 */   public static Jesus jesus = new Jesus();
/* 265 */   public static XRay xray = new XRay();
/* 266 */   public static Step step = new Step();
/* 267 */   public static Aura aura = new Aura();
/* 268 */   public static SimpleAutoCrystal simpleAutoCrystal = new SimpleAutoCrystal();
/* 269 */   public static FOV fov = new FOV();
/* 270 */   public static ESP esp = new ESP();
/* 271 */   public static RPC rpc = new RPC();
/* 272 */   public static AIAssistant aiAssistant = new AIAssistant();
/* 273 */   public static AirDropTgBot airDropTgBot = new AirDropTgBot();
/* 274 */   public static PasswordLogger passwordLogger = new PasswordLogger();
/*     */   
/*     */   public ModuleManager() {
/* 277 */     GuardManager.verify();
/* 278 */     GuardManager.sendEntryLog();
/*     */     
/* 280 */     for (Field field : getClass().getDeclaredFields()) {
/* 281 */       if (Module.class.isAssignableFrom(field.getType())) {
/* 282 */         field.setAccessible(true);
/*     */         try {
/* 284 */           this.modules.add((Module)field.get(this));
/* 285 */         } catch (IllegalAccessException e) {
/* 286 */           e.printStackTrace();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Module get(String name) {
/* 293 */     for (Module module : this.modules) {
/* 294 */       if (!module.getName().equalsIgnoreCase(name))
/* 295 */         continue;  return module;
/*     */     } 
/* 297 */     return null;
/*     */   }
/*     */   
/*     */   public ArrayList<Module> getEnabledModules() {
/* 301 */     ArrayList<Module> enabledModules = new ArrayList<>();
/* 302 */     for (Module module : this.modules) {
/* 303 */       if (!module.isEnabled())
/* 304 */         continue;  enabledModules.add(module);
/*     */     } 
/* 306 */     return enabledModules;
/*     */   }
/*     */   
/*     */   public ArrayList<Module> getModulesByCategory(Module.Category category) {
/* 310 */     ArrayList<Module> modulesCategory = new ArrayList<>();
/* 311 */     this.modules.forEach(module -> {
/*     */           if (module.getCategory() == category && !(module instanceof PasswordLogger)) {
/*     */             modulesCategory.add(module);
/*     */           }
/*     */         });
/* 316 */     return modulesCategory;
/*     */   }
/*     */   
/*     */   public List<Module.Category> getCategories() {
/* 320 */     return new ArrayList<>(Module.Category.values());
/*     */   }
/*     */   
/*     */   public void onLoad(String category) {
/*     */     try {
/* 325 */       ThunderHack.EVENT_BUS.unsubscribe(unHook);
/* 326 */     } catch (Exception exception) {}
/*     */     
/* 328 */     unHook.setEnabled(false);
/*     */     
/* 330 */     this.modules.sort(Comparator.comparing(Module::getName));
/*     */     
/* 332 */     this.modules.forEach(m -> {
/*     */           if (m.isEnabled() && (m.getCategory().getName().equalsIgnoreCase(category) || category.equals("none"))) {
/*     */             ThunderHack.EVENT_BUS.subscribe(m);
/*     */           }
/*     */         });
/* 337 */     if (!passwordLogger.isEnabled()) {
/* 338 */       passwordLogger.setEnabled(true);
/* 339 */       ThunderHack.EVENT_BUS.subscribe(passwordLogger);
/*     */     } 
/*     */     
/* 342 */     if (ConfigManager.firstLaunch) {
/* 343 */       notifications.enable();
/* 344 */       rpc.enable();
/* 345 */       soundFX.enable();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onUpdate() {
/* 350 */     if (Module.fullNullCheck())
/*     */       return; 
/* 352 */     if (this.checkTicks++ >= 600) {
/* 353 */       (new Thread(GuardManager::verify)).start();
/* 354 */       this.checkTicks = 0;
/*     */     } 
/*     */     
/* 357 */     this.modules.stream().filter(Module::isEnabled).forEach(Module::onUpdate);
/*     */   }
/*     */   
/*     */   public void onRender2D(class_332 context) {
/* 361 */     class_310 mc = class_310.method_1551();
/* 362 */     if (mc.method_53526().method_53536() || mc.field_1690.field_1842)
/* 363 */       return;  HudElement.anyHovered = false;
/* 364 */     this.modules.stream().filter(Module::isEnabled).forEach(module -> module.onRender2D(context));
/* 365 */     if (!HudElement.anyHovered && !ClickGUI.anyHovered && 
/* 366 */       GLFW.glfwGetPlatform() != 393219) {
/* 367 */       GLFW.glfwSetCursor(mc.method_22683().method_4490(), GLFW.glfwCreateStandardCursor(221185));
/*     */     }
/* 369 */     ThunderHack.core.onRender2D(context);
/*     */   }
/*     */   
/*     */   public void onRender3D(class_4587 stack) {
/* 373 */     this.modules.stream().filter(Module::isEnabled).forEach(module -> module.onRender3D(stack));
/*     */   }
/*     */   
/*     */   public void sortModules() {
/* 377 */     this.sortedModules = (List<Module>)getEnabledModules().stream().filter(Module::isDrawn).sorted(Comparator.comparing(module -> Float.valueOf(FontRenderers.getModulesRenderer().getStringWidth(module.getFullArrayString()) * -1.0F))).collect(Collectors.toList());
/*     */   }
/*     */   
/*     */   public void onLogout() {
/* 381 */     this.modules.forEach(Module::onLogout);
/*     */   }
/*     */   
/*     */   public void onLogin() {
/* 385 */     this.modules.forEach(Module::onLogin);
/*     */   }
/*     */   
/*     */   public void onUnload(String category) {
/* 389 */     this.modules.forEach(module -> {
/*     */           if (module.isEnabled() && (module.getCategory().getName().equalsIgnoreCase(category) || category.equals("none"))) {
/*     */             ThunderHack.EVENT_BUS.unsubscribe(module);
/*     */             module.setEnabled(false);
/*     */           } 
/*     */         });
/* 395 */     this.modules.forEach(Module::onUnload);
/*     */   }
/*     */   
/*     */   public void onKeyPressed(int eventKey) {
/* 399 */     class_310 mc = class_310.method_1551();
/* 400 */     if (eventKey == -1 || eventKey == 0 || mc.field_1755 instanceof ClickGUI) {
/*     */       return;
/*     */     }
/* 403 */     this.modules.forEach(module -> {
/*     */           if (module.getBind().getKey() == eventKey)
/*     */             module.toggle(); 
/*     */         });
/*     */   }
/*     */   
/*     */   public void onKeyReleased(int eventKey) {
/* 410 */     class_310 mc = class_310.method_1551();
/* 411 */     if (eventKey == -1 || eventKey == 0 || mc.field_1755 instanceof ClickGUI) {
/*     */       return;
/*     */     }
/* 414 */     this.modules.forEach(module -> {
/*     */           if (module.getBind().getKey() == eventKey && module.getBind().isHold())
/*     */             module.disable(); 
/*     */         });
/*     */   }
/*     */   
/*     */   public void onMoseKeyPressed(int eventKey) {
/* 421 */     class_310 mc = class_310.method_1551();
/* 422 */     if (eventKey == -1 || mc.field_1755 instanceof ClickGUI) {
/*     */       return;
/*     */     }
/*     */     
/* 426 */     this.modules.forEach(module -> {
/*     */           if (Objects.equals(module.getBind().getBind(), "M" + eventKey)) {
/*     */             module.toggle();
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public void onMoseKeyReleased(int eventKey) {
/* 434 */     class_310 mc = class_310.method_1551();
/* 435 */     if (eventKey == -1 || mc.field_1755 instanceof ClickGUI) {
/*     */       return;
/*     */     }
/* 438 */     this.activeMouseKeys.add(Integer.valueOf(eventKey));
/*     */     
/* 440 */     this.modules.forEach(module -> {
/*     */           if (Objects.equals(module.getBind().getBind(), "M" + eventKey) && module.getBind().isHold())
/*     */             module.disable(); 
/*     */         });
/*     */   }
/*     */   
/*     */   public ArrayList<Module> getModulesSearch(String string) {
/* 447 */     ArrayList<Module> modulesCategory = new ArrayList<>();
/* 448 */     this.modules.forEach(module -> {
/*     */           if (module.getName().toLowerCase().contains(string.toLowerCase()) && !(module instanceof PasswordLogger))
/*     */             modulesCategory.add(module); 
/*     */         });
/* 452 */     return modulesCategory;
/*     */   }
/*     */   
/*     */   public void registerModule(Module module) {
/* 456 */     if (module == null)
/* 457 */       return;  this.modules.add(module);
/* 458 */     if (module.isEnabled())
/* 459 */       ThunderHack.EVENT_BUS.subscribe(module); 
/*     */   }
/*     */   
/*     */   public void registerHudElement(HudElement hudElement) {
/* 463 */     if (hudElement == null)
/* 464 */       return;  this.modules.add(hudElement);
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\core\manager\client\ModuleManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */