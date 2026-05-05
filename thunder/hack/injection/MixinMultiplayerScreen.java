/*    */ package thunder.hack.injection;
/*    */ import net.minecraft.class_2561;
/*    */ import net.minecraft.class_4185;
/*    */ import net.minecraft.class_437;
/*    */ import net.minecraft.class_500;
/*    */ import thunder.hack.core.manager.IManager;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.gui.windows.WindowBase;
/*    */ import thunder.hack.gui.windows.WindowsScreen;
/*    */ import thunder.hack.gui.windows.impl.ConfigWindow;
/*    */ import thunder.hack.gui.windows.impl.FriendsWindow;
/*    */ import thunder.hack.gui.windows.impl.MacroWindow;
/*    */ import thunder.hack.gui.windows.impl.ProxyWindow;
/*    */ import thunder.hack.gui.windows.impl.WaypointWindow;
/*    */ import thunder.hack.setting.impl.PositionSetting;
/*    */ 
/*    */ @Mixin({class_500.class})
/*    */ public abstract class MixinMultiplayerScreen extends class_437 {
/*    */   public MixinMultiplayerScreen(class_2561 title) {
/* 20 */     super(title);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Inject(method = {"init"}, at = {@At("RETURN")})
/*    */   private void initHook(CallbackInfo ci) {
/* 32 */     class_4185.class_7840 builder = class_4185.method_46430((class_2561)class_2561.method_43470("⚡"), button -> IManager.mc.method_1507((class_437)new WindowsScreen(new WindowBase[] { (WindowBase)MacroWindow.get(((PositionSetting)ModuleManager.windows.macroPos.getValue()).getX() * IManager.mc.method_22683().method_4486(), ((PositionSetting)ModuleManager.windows.macroPos.getValue()).getY() * IManager.mc.method_22683().method_4502(), ModuleManager.windows.macroPos), (WindowBase)ConfigWindow.get(((PositionSetting)ModuleManager.windows.configPos.getValue()).getX() * IManager.mc.method_22683().method_4486(), ((PositionSetting)ModuleManager.windows.configPos.getValue()).getY() * IManager.mc.method_22683().method_4502(), ModuleManager.windows.configPos), (WindowBase)FriendsWindow.get(((PositionSetting)ModuleManager.windows.friendPos.getValue()).getX() * IManager.mc.method_22683().method_4486(), ((PositionSetting)ModuleManager.windows.friendPos.getValue()).getY() * IManager.mc.method_22683().method_4502(), ModuleManager.windows.friendPos), (WindowBase)WaypointWindow.get(((PositionSetting)ModuleManager.windows.waypointPos.getValue()).getX() * IManager.mc.method_22683().method_4486(), ((PositionSetting)ModuleManager.windows.waypointPos.getValue()).getY() * IManager.mc.method_22683().method_4502(), ModuleManager.windows.waypointPos), (WindowBase)ProxyWindow.get(((PositionSetting)ModuleManager.windows.proxyPos.getValue()).getX() * IManager.mc.method_22683().method_4486(), ((PositionSetting)ModuleManager.windows.proxyPos.getValue()).getY() * IManager.mc.method_22683().method_4502(), ModuleManager.windows.proxyPos) }))).method_46437(60, 20);
/* 33 */     if (!ModuleManager.unHook.isEnabled())
/* 34 */       method_37063((class_364)builder.method_46433(this.field_22789 - 65, this.field_22790 - 25).method_46431()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinMultiplayerScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */