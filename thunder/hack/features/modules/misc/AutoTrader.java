/*     */ package thunder.hack.features.modules.misc;
/*     */ import java.util.HashMap;
/*     */ import net.minecraft.class_1268;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1713;
/*     */ import net.minecraft.class_1728;
/*     */ import net.minecraft.class_1914;
/*     */ import net.minecraft.class_1916;
/*     */ import net.minecraft.class_2863;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_492;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.ClientSettings;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.setting.impl.BooleanSettingGroup;
/*     */ import thunder.hack.setting.impl.SettingGroup;
/*     */ import thunder.hack.utility.player.InteractionUtility;
/*     */ import thunder.hack.utility.player.InventoryUtility;
/*     */ 
/*     */ public class AutoTrader extends Module {
/*     */   private final Setting<BooleanSettingGroup> buy;
/*     */   private final Setting<String> buyItem;
/*     */   private final Setting<BooleanSettingGroup> sell;
/*     */   private final Setting<String> sellItem;
/*     */   private final Setting<SettingGroup> disable;
/*     */   
/*     */   public AutoTrader() {
/*  28 */     super("AutoTrader", Module.Category.MISC);
/*     */ 
/*     */     
/*  31 */     this.buy = new Setting("Buy", new BooleanSettingGroup(true));
/*  32 */     this.buyItem = (new Setting("BuyItem", "apple")).addToGroup(this.buy);
/*  33 */     this.sell = new Setting("Sell", new BooleanSettingGroup(false));
/*  34 */     this.sellItem = (new Setting("SellItem", "bread")).addToGroup(this.sell);
/*  35 */     this.disable = new Setting("Disable", new SettingGroup(false, 0));
/*  36 */     this.noVillagers = (new Setting("NoVillagers", Boolean.valueOf(true))).addToGroup(this.disable);
/*  37 */     this.noItems = (new Setting("NoItems", Boolean.valueOf(false))).addToGroup(this.disable);
/*     */ 
/*     */ 
/*     */     
/*  41 */     this.villagers = new HashMap<>();
/*     */   }
/*     */   private final Setting<Boolean> noVillagers; private final Setting<Boolean> noItems; private int interactTicks; private int cooldown; private int lastVillager; private HashMap<Integer, Integer> villagers;
/*     */   public void onUpdate() {
/*  45 */     if (fullNullCheck()) {
/*     */       return;
/*     */     }
/*  48 */     if (this.interactTicks > 0) {
/*  49 */       this.interactTicks--;
/*     */     }
/*  51 */     if (this.cooldown > 0) {
/*  52 */       this.cooldown--;
/*     */       
/*     */       return;
/*     */     } 
/*  56 */     HashMap<Integer, Integer> cacheVillagers = new HashMap<>(this.villagers);
/*  57 */     cacheVillagers.forEach((id, time) -> {
/*     */           if (mc.field_1724.field_6012 - time.intValue() > 160) {
/*     */             this.villagers.remove(id);
/*     */           }
/*     */         });
/*  62 */     class_437 class_437 = mc.field_1755; if (class_437 instanceof class_492) { class_492 merch = (class_492)class_437;
/*  63 */       class_1728 msh = (class_1728)merch.method_17577();
/*  64 */       class_1916 offers = msh.method_17438();
/*     */       
/*  66 */       for (int i = 0; i < offers.size(); i++) {
/*  67 */         class_1914 offer = (class_1914)offers.get(i);
/*  68 */         if (goodDeal(offer)) {
/*  69 */           msh.method_20215(i);
/*  70 */           msh.method_7650(i);
/*  71 */           sendPacket((class_2596)new class_2863(i));
/*  72 */           clickSlot(2, class_1713.field_7794);
/*  73 */           this.cooldown = 3; return;
/*     */         } 
/*  75 */         if (!msh.method_7611(0).method_7677().method_7960()) {
/*  76 */           clickSlot(0, class_1713.field_7794);
/*  77 */           this.cooldown = 3; return;
/*     */         } 
/*  79 */         if (!msh.method_7611(1).method_7677().method_7960()) {
/*  80 */           clickSlot(1, class_1713.field_7794);
/*  81 */           this.cooldown = 3; return;
/*     */         } 
/*  83 */         if (offer.method_8255()) {
/*  84 */           this.villagers.put(Integer.valueOf(this.lastVillager), Integer.valueOf(mc.field_1724.field_6012));
/*     */         }
/*     */       } 
/*  87 */       mc.field_1724.method_7346(); }
/*  88 */     else if (this.interactTicks <= 0 && !(mc.field_1755 instanceof thunder.hack.gui.clickui.ClickGUI))
/*     */     
/*     */     { 
/*     */ 
/*     */       
/*  93 */       class_1297 ent = Lists.newArrayList(mc.field_1687.method_18112()).stream().filter(e -> e instanceof net.minecraft.class_1646).filter(e -> (mc.field_1724.method_5858(e) < 16.0D)).filter(e -> !this.villagers.containsKey(Integer.valueOf(e.method_5628()))).min(Comparator.comparing(e -> Float.valueOf(mc.field_1724.method_5739(e)))).orElse(null);
/*     */       
/*  95 */       if (ent != null) {
/*  96 */         float[] angles = InteractionUtility.calculateAngle(ent.method_33571().method_1031(Math.random() * 0.2D, 0.0D, Math.random() * 0.2D));
/*  97 */         mc.field_1724.method_36456(angles[0]);
/*  98 */         mc.field_1724.method_36457(angles[1]);
/*  99 */         mc.field_1761.method_2905((class_1657)mc.field_1724, ent, class_1268.field_5808);
/* 100 */         this.lastVillager = ent.method_5628();
/* 101 */         this.interactTicks = 12;
/* 102 */       } else if (((Boolean)this.noVillagers.getValue()).booleanValue()) {
/* 103 */         disable(ClientSettings.isRu() ? "Рядом нет жителей!" : "There are no villagers nearby!");
/*     */       }  }
/*     */   
/*     */   }
/*     */   
/*     */   private boolean goodDeal(class_1914 offer) {
/* 109 */     boolean selectedBuyItem = (offer.method_8250().method_7909().method_7876().equals("item.minecraft." + (String)this.buyItem.getValue()) || offer.method_8250().method_7909().method_7876().equals("block.minecraft." + (String)this.buyItem.getValue()));
/*     */ 
/*     */     
/* 112 */     boolean selectedSellItem = (offer.method_19272().method_7909().method_7876().equals("item.minecraft." + (String)this.sellItem.getValue()) || offer.method_19272().method_7909().method_7876().equals("block.minecraft." + (String)this.sellItem.getValue()));
/*     */     
/* 114 */     boolean haveItems = (offer.method_19272().method_7947() <= InventoryUtility.getItemCount(offer.method_19272().method_7909()));
/*     */     
/* 116 */     boolean canBuy = (selectedBuyItem && !offer.method_8255() && ((BooleanSettingGroup)this.buy.getValue()).isEnabled());
/*     */     
/* 118 */     boolean canSell = (selectedSellItem && !offer.method_8255() && ((BooleanSettingGroup)this.sell.getValue()).isEnabled());
/*     */     
/* 120 */     if ((canBuy || canSell) && !haveItems) {
/* 121 */       if (((Boolean)this.noItems.getValue()).booleanValue())
/* 122 */         disable(ClientSettings.isRu() ? "Кончились предметы!" : "Out of items!"); 
/* 123 */       return false;
/*     */     } 
/*     */     
/* 126 */     return (canBuy || canSell);
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\misc\AutoTrader.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */