/*   */ package thunder.hack.utility.render;
/*   */ 
/*   */ import net.fabricmc.fabric.api.event.Event;
/*   */ import net.fabricmc.fabric.api.event.EventFactory;
/*   */ import net.minecraft.class_1041;
/*   */ import net.minecraft.class_310;
/*   */ 
/*   */ public interface WindowResizeCallback {
/* 9 */   public static final Event<WindowResizeCallback> EVENT = EventFactory.createArrayBacked(WindowResizeCallback.class, callbacks -> ());
/*   */   
/*   */   void onResized(class_310 paramclass_310, class_1041 paramclass_1041);
/*   */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\WindowResizeCallback.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */