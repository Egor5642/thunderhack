/*     */ package thunder.hack.injection;
/*     */ 
/*     */ import io.netty.channel.ChannelHandler;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import io.netty.channel.ChannelPipeline;
/*     */ import io.netty.handler.proxy.Socks5ProxyHandler;
/*     */ import java.net.InetSocketAddress;
/*     */ import net.minecraft.class_2535;
/*     */ import net.minecraft.class_2547;
/*     */ import net.minecraft.class_2596;
/*     */ import net.minecraft.class_2598;
/*     */ import net.minecraft.class_8042;
/*     */ import net.minecraft.class_8762;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ import org.spongepowered.asm.mixin.injection.Inject;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*     */ import thunder.hack.ThunderHack;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.core.manager.client.ProxyManager;
/*     */ import thunder.hack.events.impl.PacketEvent;
/*     */ import thunder.hack.features.modules.Module;
/*     */ 
/*     */ 
/*     */ @Mixin({class_2535.class})
/*     */ public class MixinClientConnection
/*     */ {
/*     */   @Inject(method = {"exceptionCaught"}, at = {@At("HEAD")}, cancellable = true)
/*     */   private void exceptionCaughtHook(ChannelHandlerContext context, Throwable t, CallbackInfo ci) {
/*  31 */     if (ModuleManager.antiPacketException.isEnabled()) {
/*  32 */       ModuleManager.antiPacketException.sendChatMessage(t.getMessage());
/*  33 */       ci.cancel();
/*     */     } 
/*     */   }
/*     */   
/*     */   @Inject(method = {"handlePacket"}, at = {@At("HEAD")}, cancellable = true)
/*     */   private static <T extends class_2547> void onHandlePacket(class_2596<T> packet, class_2547 listener, CallbackInfo info) {
/*  39 */     if (Module.fullNullCheck())
/*  40 */       return;  if (packet instanceof class_8042) { class_8042 packs = (class_8042)packet;
/*  41 */       packs.method_48324().forEach(p -> {
/*     */             PacketEvent.Receive event = new PacketEvent.Receive(p);
/*     */             ThunderHack.EVENT_BUS.post(event);
/*     */             if (event.isCancelled()) {
/*     */               info.cancel();
/*     */             }
/*     */           }); }
/*     */     else
/*  49 */     { PacketEvent.Receive event = new PacketEvent.Receive(packet);
/*  50 */       ThunderHack.EVENT_BUS.post(event);
/*  51 */       if (event.isCancelled()) {
/*  52 */         info.cancel();
/*     */       } }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   @Inject(method = {"handlePacket"}, at = {@At("TAIL")}, cancellable = true)
/*     */   private static <T extends class_2547> void onHandlePacketPost(class_2596<T> packet, class_2547 listener, CallbackInfo info) {
/*  60 */     if (Module.fullNullCheck())
/*  61 */       return;  if (packet instanceof class_8042) { class_8042 packs = (class_8042)packet;
/*  62 */       packs.method_48324().forEach(p -> {
/*     */             PacketEvent.ReceivePost event = new PacketEvent.ReceivePost(p);
/*     */             ThunderHack.EVENT_BUS.post(event);
/*     */             if (event.isCancelled()) {
/*     */               info.cancel();
/*     */             }
/*     */           }); }
/*     */     else
/*  70 */     { PacketEvent.ReceivePost event = new PacketEvent.ReceivePost(packet);
/*  71 */       ThunderHack.EVENT_BUS.post(event);
/*  72 */       if (event.isCancelled()) {
/*  73 */         info.cancel();
/*     */       } }
/*     */   
/*     */   }
/*     */   
/*     */   @Inject(method = {"send(Lnet/minecraft/network/packet/Packet;)V"}, at = {@At("HEAD")}, cancellable = true)
/*     */   private void onSendPacketPre(class_2596<?> packet, CallbackInfo info) {
/*  80 */     if (Module.fullNullCheck())
/*  81 */       return;  if (ThunderHack.core.silentPackets.contains(packet)) {
/*  82 */       ThunderHack.core.silentPackets.remove(packet);
/*     */       
/*     */       return;
/*     */     } 
/*  86 */     PacketEvent.Send event = new PacketEvent.Send(packet);
/*  87 */     ThunderHack.EVENT_BUS.post(event);
/*  88 */     if (event.isCancelled()) info.cancel(); 
/*     */   }
/*     */   
/*     */   @Inject(method = {"send(Lnet/minecraft/network/packet/Packet;)V"}, at = {@At("RETURN")}, cancellable = true)
/*     */   private void onSendPacketPost(class_2596<?> packet, CallbackInfo info) {
/*  93 */     if (Module.fullNullCheck())
/*  94 */       return;  PacketEvent.SendPost event = new PacketEvent.SendPost(packet);
/*  95 */     ThunderHack.EVENT_BUS.post(event);
/*  96 */     if (event.isCancelled()) info.cancel();
/*     */   
/*     */   }
/*     */   
/*     */   @Inject(method = {"addHandlers"}, at = {@At("RETURN")})
/*     */   private static void addHandlersHook(ChannelPipeline pipeline, class_2598 side, boolean local, class_8762 packetSizeLogger, CallbackInfo ci) {
/* 102 */     ProxyManager.ThProxy proxy = Managers.PROXY.getActiveProxy();
/* 103 */     if (proxy != null && side == class_2598.field_11942 && !local)
/* 104 */       pipeline.addFirst(new ChannelHandler[] { (ChannelHandler)new Socks5ProxyHandler(new InetSocketAddress(proxy.getIp(), proxy.getPort()), proxy.getL(), proxy.getP()) }); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinClientConnection.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */