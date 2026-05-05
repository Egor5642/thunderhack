/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.InputStreamReader;
/*    */ import java.net.URL;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.class_156;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_640;
/*    */ import net.minecraft.class_8685;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.utility.OptifineCapes;
/*    */ import thunder.hack.utility.ThunderUtility;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_640.class})
/*    */ public class MixinPlayerListEntry
/*    */ {
/*    */   @Unique
/*    */   private boolean loadedCapeTexture;
/*    */   @Unique
/*    */   private class_2960 customCapeTexture;
/*    */   
/*    */   @Inject(method = {"<init>(Lcom/mojang/authlib/GameProfile;Z)V"}, at = {@At("TAIL")})
/*    */   private void initHook(GameProfile profile, boolean secureChatEnforced, CallbackInfo ci) {
/* 34 */     getTexture(profile);
/*    */   }
/*    */   
/*    */   @Inject(method = {"getSkinTextures"}, at = {@At("TAIL")}, cancellable = true)
/*    */   private void getCapeTexture(CallbackInfoReturnable<class_8685> cir) {
/* 39 */     if (this.customCapeTexture != null) {
/* 40 */       class_8685 prev = (class_8685)cir.getReturnValue();
/* 41 */       class_8685 newTextures = new class_8685(prev.comp_1626(), prev.comp_1911(), this.customCapeTexture, this.customCapeTexture, prev.comp_1629(), prev.comp_1630());
/* 42 */       cir.setReturnValue(newTextures);
/*    */     } 
/*    */   }
/*    */   
/*    */   @Unique
/*    */   private void getTexture(GameProfile profile) {
/* 48 */     if (this.loadedCapeTexture)
/* 49 */       return;  this.loadedCapeTexture = true;
/*    */     
/* 51 */     class_156.method_18349().execute(() -> {
/*    */           try {
/*    */             URL capesList = new URL("https://raw.githubusercontent.com/Pan4ur/THRecodeUtil/main/capes/capeBase.txt");
/*    */             BufferedReader in = new BufferedReader(new InputStreamReader(capesList.openStream()));
/*    */             String inputLine;
/*    */             while ((inputLine = in.readLine()) != null) {
/*    */               String colune = inputLine.trim();
/*    */               String name = colune.split(":")[0];
/*    */               String cape = colune.split(":")[1];
/*    */               if (Objects.equals(profile.getName(), name)) {
/*    */                 this.customCapeTexture = class_2960.method_60655("thunderhack", "textures/capes/" + cape + ".png");
/*    */                 return;
/*    */               } 
/*    */             } 
/* 65 */           } catch (Exception exception) {}
/*    */           for (String str : ThunderUtility.starGazer) {
/*    */             if (profile.getName().toLowerCase().equals(str.toLowerCase()))
/*    */               this.customCapeTexture = class_2960.method_60655("thunderhack", "textures/capes/starcape.png"); 
/*    */           } 
/*    */           if (ModuleManager.optifineCapes.isEnabled())
/*    */             OptifineCapes.loadPlayerCape(profile, ()); 
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinPlayerListEntry.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */