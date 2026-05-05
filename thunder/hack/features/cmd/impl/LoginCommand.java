/*    */ package thunder.hack.features.cmd.impl;
/*    */ 
/*    */ import com.mojang.authlib.minecraft.UserApiService;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.StringArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.class_2172;
/*    */ import net.minecraft.class_320;
/*    */ import net.minecraft.class_4844;
/*    */ import net.minecraft.class_5520;
/*    */ import net.minecraft.class_7569;
/*    */ import net.minecraft.class_7574;
/*    */ import net.minecraft.class_7853;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ import thunder.hack.features.modules.client.ClientSettings;
/*    */ import thunder.hack.injection.accesors.IMinecraftClient;
/*    */ 
/*    */ public class LoginCommand extends Command {
/*    */   public LoginCommand() {
/* 24 */     super(new String[] { "login" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/* 29 */     builder.then(arg("name", (ArgumentType)StringArgumentType.word()).executes(context -> {
/*    */             login((String)context.getArgument("name", String.class));
/*    */             
/*    */             sendMessage((ClientSettings.isRu() ? "Аккаунт изменен на: " : "Switched account to: ") + (ClientSettings.isRu() ? "Аккаунт изменен на: " : "Switched account to: "));
/*    */             
/*    */             return 1;
/*    */           }));
/* 36 */     builder.executes(context -> {
/*    */           sendMessage(ClientSettings.isRu() ? "Использование: .login <nickname>" : "Usage: .login <nickname>");
/*    */           return 1;
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   public void login(String name) {
/*    */     try {
/* 45 */       setSession(new class_320(name, class_4844.method_43344(name), "", Optional.empty(), Optional.empty(), class_320.class_321.field_1988));
/* 46 */     } catch (Exception exception) {
/* 47 */       sendMessage((ClientSettings.isRu() ? "Неверное имя! " : "Incorrect username! ") + (ClientSettings.isRu() ? "Неверное имя! " : "Incorrect username! "));
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setSession(class_320 session) {
/* 52 */     IMinecraftClient mca = (IMinecraftClient)mc;
/* 53 */     mca.setSessionT(session);
/* 54 */     mc.method_53462().getProperties().clear();
/*    */     
/* 56 */     UserApiService apiService = UserApiService.OFFLINE;
/* 57 */     mca.setUserApiService(apiService);
/* 58 */     mca.setSocialInteractionsManagerT(new class_5520(mc, apiService));
/* 59 */     mca.setProfileKeys(class_7853.method_46532(apiService, session, mc.field_1697.toPath()));
/* 60 */     mca.setAbuseReportContextT(class_7574.method_44599(class_7569.method_44586(), apiService));
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\LoginCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */