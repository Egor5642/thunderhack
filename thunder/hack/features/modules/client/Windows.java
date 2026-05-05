/*    */ package thunder.hack.features.modules.client;
/*    */ import thunder.hack.gui.windows.WindowBase;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.setting.impl.PositionSetting;
/*    */ 
/*    */ public class Windows extends Module {
/*    */   public final Setting<PositionSetting> macroPos;
/*    */   public final Setting<PositionSetting> configPos;
/*    */   
/*    */   public Windows() {
/* 11 */     super("Windows", Module.Category.CLIENT);
/*    */ 
/*    */ 
/*    */     
/* 15 */     this.macroPos = new Setting("macroPos", new PositionSetting(0.3F, 0.3F), v -> false);
/* 16 */     this.configPos = new Setting("configPos", new PositionSetting(0.35F, 0.35F), v -> false);
/* 17 */     this.friendPos = new Setting("friendPos", new PositionSetting(0.4F, 0.4F), v -> false);
/* 18 */     this.waypointPos = new Setting("waypointPos", new PositionSetting(0.45F, 0.45F), v -> false);
/* 19 */     this.proxyPos = new Setting("proxyPos", new PositionSetting(0.5F, 0.5F), v -> false);
/*    */   }
/*    */   public final Setting<PositionSetting> friendPos; public final Setting<PositionSetting> waypointPos; public final Setting<PositionSetting> proxyPos;
/*    */   public void onEnable() {
/* 23 */     mc.method_1507((class_437)new WindowsScreen(new WindowBase[] {
/* 24 */             (WindowBase)MacroWindow.get(((PositionSetting)this.macroPos.getValue()).getX() * mc.method_22683().method_4486(), ((PositionSetting)this.macroPos.getValue()).getY() * mc.method_22683().method_4502(), this.macroPos), 
/* 25 */             (WindowBase)ConfigWindow.get(((PositionSetting)this.configPos.getValue()).getX() * mc.method_22683().method_4486(), ((PositionSetting)this.configPos.getValue()).getY() * mc.method_22683().method_4502(), this.configPos), 
/* 26 */             (WindowBase)FriendsWindow.get(((PositionSetting)this.friendPos.getValue()).getX() * mc.method_22683().method_4486(), ((PositionSetting)this.friendPos.getValue()).getY() * mc.method_22683().method_4502(), this.friendPos), 
/* 27 */             (WindowBase)WaypointWindow.get(((PositionSetting)this.waypointPos.getValue()).getX() * mc.method_22683().method_4486(), ((PositionSetting)this.waypointPos.getValue()).getY() * mc.method_22683().method_4502(), this.waypointPos), 
/* 28 */             (WindowBase)ProxyWindow.get(((PositionSetting)this.proxyPos.getValue()).getX() * mc.method_22683().method_4486(), ((PositionSetting)this.proxyPos.getValue()).getY() * mc.method_22683().method_4502(), this.proxyPos)
/*    */           }));
/* 30 */     disable();
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\client\Windows.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */