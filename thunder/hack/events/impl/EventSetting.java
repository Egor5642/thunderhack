/*    */ package thunder.hack.events.impl;
/*    */ 
/*    */ import thunder.hack.events.Event;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public class EventSetting extends Event {
/*    */   final Setting<?> setting;
/*    */   
/*    */   public EventSetting(Setting<?> setting) {
/* 10 */     this.setting = setting;
/*    */   }
/*    */   
/*    */   public Setting<?> getSetting() {
/* 14 */     return this.setting;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\events\impl\EventSetting.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */