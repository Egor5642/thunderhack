/*     */ package thunder.hack.core.manager.client;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.awt.Image;
/*     */ import java.awt.SystemTray;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.TrayIcon;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_332;
/*     */ import org.apache.commons.lang3.SystemUtils;
/*     */ import thunder.hack.ThunderHack;
/*     */ import thunder.hack.core.manager.IManager;
/*     */ import thunder.hack.features.cmd.Command;
/*     */ import thunder.hack.features.modules.client.Notifications;
/*     */ import thunder.hack.gui.notification.Notification;
/*     */ 
/*     */ public class NotificationManager implements IManager {
/*  20 */   private final List<Notification> notifications = new ArrayList<>();
/*     */   private TrayIcon trayIcon;
/*     */   
/*     */   public void publicity(String title, String content, int second, Notification.Type type) {
/*  24 */     if (ModuleManager.notifications.mode.getValue() == Notifications.Mode.Text) {
/*  25 */       Command.sendMessage(String.valueOf(class_124.field_1080) + "[" + String.valueOf(class_124.field_1080) + String.valueOf(class_124.field_1064) + title + "] " + String.valueOf(class_124.field_1080) + String.valueOf(type.getColor()));
/*     */     }
/*  27 */     if (!mc.method_1569()) {
/*  28 */       nativeNotification(content, title);
/*     */     }
/*  30 */     this.notifications.add(new Notification(title, content, type, second * 1000));
/*     */   }
/*     */   
/*     */   public void onRender2D(class_332 context) {
/*  34 */     if (!ModuleManager.notifications.isEnabled())
/*     */       return; 
/*  36 */     float startY = isDefault() ? (mc.method_22683().method_4502() - 36.0F) : (mc.method_22683().method_4502() / 2.0F + 25.0F);
/*     */     
/*  38 */     if (this.notifications.size() > 8) {
/*  39 */       this.notifications.removeFirst();
/*     */     }
/*  41 */     this.notifications.removeIf(Notification::shouldDelete);
/*     */     
/*  43 */     for (Notification n : Lists.newArrayList(this.notifications)) {
/*  44 */       startY = (float)(startY - n.getHeight() - 3.0D);
/*  45 */       n.renderShaders(context.method_51448(), startY + (isDefault() ? false : (this.notifications.size() * 16)));
/*  46 */       n.render(context.method_51448(), startY + (isDefault() ? false : (this.notifications.size() * 16)));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onUpdate() {
/*  51 */     if (!ModuleManager.notifications.isEnabled())
/*  52 */       return;  this.notifications.forEach(Notification::onUpdate);
/*     */   }
/*     */   
/*     */   public static boolean isDefault() {
/*  56 */     return (ModuleManager.notifications.mode.getValue() == Notifications.Mode.Default);
/*     */   }
/*     */   
/*     */   private void nativeNotification(String message, String title) {
/*  60 */     if (SystemUtils.IS_OS_WINDOWS) {
/*  61 */       windows(message, title);
/*  62 */     } else if (SystemUtils.IS_OS_LINUX) {
/*  63 */       linux(message);
/*  64 */     } else if (SystemUtils.IS_OS_MAC) {
/*  65 */       mac(message);
/*     */     } else {
/*  67 */       ThunderHack.LOGGER.error("Unsupported OS: {}", SystemUtils.OS_NAME);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void windows(String message, String title) {
/*  72 */     if (SystemTray.isSupported()) {
/*     */       try {
/*  74 */         if (this.trayIcon == null) {
/*  75 */           SystemTray tray = SystemTray.getSystemTray();
/*  76 */           Image image = Toolkit.getDefaultToolkit().createImage("resources/icon.png");
/*     */           
/*  78 */           this.trayIcon = new TrayIcon(image, "ThunderHack");
/*  79 */           this.trayIcon.setImageAutoSize(true);
/*  80 */           this.trayIcon.setToolTip("ThunderHack");
/*  81 */           tray.add(this.trayIcon);
/*     */         } 
/*     */         
/*  84 */         this.trayIcon.displayMessage(title, message, TrayIcon.MessageType.INFO);
/*  85 */       } catch (Exception e) {
/*  86 */         ThunderHack.LOGGER.error(e.getMessage());
/*     */       } 
/*     */     } else {
/*  89 */       ThunderHack.LOGGER.error("SystemTray is not supported");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void mac(String message) {
/*  94 */     ProcessBuilder processBuilder = new ProcessBuilder(new String[0]);
/*  95 */     processBuilder.command(new String[] { "osascript", "-e", "display notification \"" + message + "\" with title \"ThunderHack\"" });
/*     */     try {
/*  97 */       processBuilder.start();
/*  98 */     } catch (IOException e) {
/*  99 */       ThunderHack.LOGGER.error(e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void linux(String message) {
/* 104 */     ProcessBuilder processBuilder = new ProcessBuilder(new String[0]);
/* 105 */     processBuilder.command(new String[] { "notify-send", "-a", "ThunderHack", message });
/*     */     
/*     */     try {
/* 108 */       processBuilder.start();
/* 109 */     } catch (IOException e) {
/* 110 */       ThunderHack.LOGGER.error(e.getMessage());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\core\manager\client\NotificationManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */