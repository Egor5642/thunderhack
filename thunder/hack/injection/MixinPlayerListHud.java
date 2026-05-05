/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import java.util.List;
/*    */ import net.minecraft.class_1934;
/*    */ import net.minecraft.class_268;
/*    */ import net.minecraft.class_355;
/*    */ import net.minecraft.class_640;
/*    */ import net.minecraft.class_8144;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import thunder.hack.ThunderHack;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.features.modules.client.ClientSettings;
/*    */ 
/*    */ @Mixin({class_355.class})
/*    */ public class MixinPlayerListHud
/*    */ {
/*    */   private static final Comparator<Object> ENTRY_ORDERING;
/*    */   
/*    */   static {
/* 25 */     ENTRY_ORDERING = Comparator.comparingInt(entry -> (((class_640)entry).method_2958() == class_1934.field_9219) ? 1 : 0).thenComparing(entry -> (String)class_8144.method_49078(((class_640)entry).method_2955(), class_268::method_1197, "")).thenComparing(entry -> ((class_640)entry).method_2966().getName(), String::compareToIgnoreCase);
/*    */   }
/*    */   @Inject(method = {"collectPlayerEntries"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void collectPlayerEntriesHook(CallbackInfoReturnable<List<class_640>> cir) {
/* 29 */     if (((Boolean)ClientSettings.futureCompatibility.getValue()).booleanValue()) {
/*    */       return;
/*    */     }
/* 32 */     if (ThunderHack.isFuturePresent()) {
/*    */       return;
/*    */     }
/* 35 */     if (ModuleManager.extraTab.isEnabled()) {
/* 36 */       cir.setReturnValue(Module.mc.field_1724.field_3944.method_45732().stream().sorted(ENTRY_ORDERING).limit(1000L).toList());
/*    */     } else {
/* 38 */       cir.setReturnValue(Module.mc.field_1724.field_3944.method_45732().stream().sorted(ENTRY_ORDERING).limit(80L).toList());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinPlayerListHud.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */