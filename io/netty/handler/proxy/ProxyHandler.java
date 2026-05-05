/*     */ package io.netty.handler.proxy;
/*     */ 
/*     */ import io.netty.channel.Channel;
/*     */ import io.netty.channel.ChannelDuplexHandler;
/*     */ import io.netty.channel.ChannelFuture;
/*     */ import io.netty.channel.ChannelFutureListener;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import io.netty.channel.ChannelPromise;
/*     */ import io.netty.channel.PendingWriteQueue;
/*     */ import io.netty.util.ReferenceCountUtil;
/*     */ import io.netty.util.concurrent.DefaultPromise;
/*     */ import io.netty.util.concurrent.EventExecutor;
/*     */ import io.netty.util.concurrent.Future;
/*     */ import io.netty.util.concurrent.GenericFutureListener;
/*     */ import io.netty.util.internal.ObjectUtil;
/*     */ import io.netty.util.internal.logging.InternalLogger;
/*     */ import io.netty.util.internal.logging.InternalLoggerFactory;
/*     */ import java.net.SocketAddress;
/*     */ import java.nio.channels.ConnectionPendingException;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ProxyHandler
/*     */   extends ChannelDuplexHandler
/*     */ {
/*  40 */   private static final InternalLogger logger = InternalLoggerFactory.getInstance(ProxyHandler.class);
/*     */ 
/*     */   
/*     */   private static final long DEFAULT_CONNECT_TIMEOUT_MILLIS = 10000L;
/*     */ 
/*     */   
/*     */   static final String AUTH_NONE = "none";
/*     */ 
/*     */   
/*     */   private final SocketAddress proxyAddress;
/*     */ 
/*     */   
/*     */   private volatile SocketAddress destinationAddress;
/*     */   
/*  54 */   private volatile long connectTimeoutMillis = 10000L;
/*     */   
/*     */   private volatile ChannelHandlerContext ctx;
/*     */   private PendingWriteQueue pendingWrites;
/*     */   private boolean finished;
/*     */   private boolean suppressChannelReadComplete;
/*     */   private boolean flushedPrematurely;
/*  61 */   private final LazyChannelPromise connectPromise = new LazyChannelPromise(); private Future<?> connectTimeoutFuture;
/*     */   
/*  63 */   private final ChannelFutureListener writeListener = new ChannelFutureListener()
/*     */     {
/*     */       public void operationComplete(ChannelFuture future) throws Exception {
/*  66 */         if (!future.isSuccess()) {
/*  67 */           ProxyHandler.this.setConnectFailure(future.cause());
/*     */         }
/*     */       }
/*     */     };
/*     */   
/*     */   protected ProxyHandler(SocketAddress proxyAddress) {
/*  73 */     this.proxyAddress = (SocketAddress)ObjectUtil.checkNotNull(proxyAddress, "proxyAddress");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final <T extends SocketAddress> T proxyAddress() {
/*  91 */     return (T)this.proxyAddress;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final <T extends SocketAddress> T destinationAddress() {
/*  99 */     return (T)this.destinationAddress;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean isConnected() {
/* 106 */     return this.connectPromise.isSuccess();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final Future<Channel> connectFuture() {
/* 114 */     return (Future<Channel>)this.connectPromise;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final long connectTimeoutMillis() {
/* 122 */     return this.connectTimeoutMillis;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setConnectTimeoutMillis(long connectTimeoutMillis) {
/* 130 */     if (connectTimeoutMillis <= 0L) {
/* 131 */       connectTimeoutMillis = 0L;
/*     */     }
/*     */     
/* 134 */     this.connectTimeoutMillis = connectTimeoutMillis;
/*     */   }
/*     */ 
/*     */   
/*     */   public final void handlerAdded(ChannelHandlerContext ctx) throws Exception {
/* 139 */     this.ctx = ctx;
/* 140 */     addCodec(ctx);
/*     */     
/* 142 */     if (ctx.channel().isActive())
/*     */     {
/*     */       
/* 145 */       sendInitialMessage(ctx);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
/* 172 */     if (this.destinationAddress != null) {
/* 173 */       promise.setFailure(new ConnectionPendingException());
/*     */       
/*     */       return;
/*     */     } 
/* 177 */     this.destinationAddress = remoteAddress;
/* 178 */     ctx.connect(this.proxyAddress, localAddress, promise);
/*     */   }
/*     */ 
/*     */   
/*     */   public final void channelActive(ChannelHandlerContext ctx) throws Exception {
/* 183 */     sendInitialMessage(ctx);
/* 184 */     ctx.fireChannelActive();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void sendInitialMessage(ChannelHandlerContext ctx) throws Exception {
/* 192 */     long connectTimeoutMillis = this.connectTimeoutMillis;
/* 193 */     if (connectTimeoutMillis > 0L) {
/* 194 */       this.connectTimeoutFuture = (Future<?>)ctx.executor().schedule(new Runnable()
/*     */           {
/*     */             public void run() {
/* 197 */               if (!ProxyHandler.this.connectPromise.isDone()) {
/* 198 */                 ProxyHandler.this.setConnectFailure(new ProxyConnectException(ProxyHandler.this.exceptionMessage("timeout")));
/*     */               }
/*     */             }
/*     */           },  connectTimeoutMillis, TimeUnit.MILLISECONDS);
/*     */     }
/*     */     
/* 204 */     Object initialMessage = newInitialMessage(ctx);
/* 205 */     if (initialMessage != null) {
/* 206 */       sendToProxyServer(initialMessage);
/*     */     }
/*     */     
/* 209 */     readIfNeeded(ctx);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void sendToProxyServer(Object msg) {
/* 224 */     this.ctx.writeAndFlush(msg).addListener((GenericFutureListener)this.writeListener);
/*     */   }
/*     */ 
/*     */   
/*     */   public final void channelInactive(ChannelHandlerContext ctx) throws Exception {
/* 229 */     if (this.finished) {
/* 230 */       ctx.fireChannelInactive();
/*     */     } else {
/*     */       
/* 233 */       setConnectFailure(new ProxyConnectException(exceptionMessage("disconnected")));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
/* 239 */     if (this.finished) {
/* 240 */       ctx.fireExceptionCaught(cause);
/*     */     } else {
/*     */       
/* 243 */       setConnectFailure(cause);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
/* 249 */     if (this.finished) {
/*     */       
/* 251 */       this.suppressChannelReadComplete = false;
/* 252 */       ctx.fireChannelRead(msg);
/*     */     } else {
/* 254 */       this.suppressChannelReadComplete = true;
/* 255 */       Throwable cause = null;
/*     */       try {
/* 257 */         boolean done = handleResponse(ctx, msg);
/* 258 */         if (done) {
/* 259 */           setConnectSuccess();
/*     */         }
/* 261 */       } catch (Throwable t) {
/* 262 */         cause = t;
/*     */       } finally {
/* 264 */         ReferenceCountUtil.release(msg);
/* 265 */         if (cause != null) {
/* 266 */           setConnectFailure(cause);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setConnectSuccess() {
/* 282 */     this.finished = true;
/* 283 */     cancelConnectTimeoutFuture();
/*     */     
/* 285 */     if (!this.connectPromise.isDone()) {
/* 286 */       boolean removedCodec = true;
/*     */       
/* 288 */       removedCodec &= safeRemoveEncoder();
/*     */       
/* 290 */       this.ctx.fireUserEventTriggered(new ProxyConnectionEvent(
/* 291 */             protocol(), authScheme(), this.proxyAddress, this.destinationAddress));
/*     */       
/* 293 */       removedCodec &= safeRemoveDecoder();
/*     */       
/* 295 */       if (removedCodec) {
/* 296 */         writePendingWrites();
/*     */         
/* 298 */         if (this.flushedPrematurely) {
/* 299 */           this.ctx.flush();
/*     */         }
/* 301 */         this.connectPromise.trySuccess(this.ctx.channel());
/*     */       } else {
/*     */         
/* 304 */         Exception cause = new ProxyConnectException("failed to remove all codec handlers added by the proxy handler; bug?");
/*     */         
/* 306 */         failPendingWritesAndClose(cause);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean safeRemoveDecoder() {
/*     */     try {
/* 313 */       removeDecoder(this.ctx);
/* 314 */       return true;
/* 315 */     } catch (Exception e) {
/* 316 */       logger.warn("Failed to remove proxy decoders:", e);
/*     */ 
/*     */       
/* 319 */       return false;
/*     */     } 
/*     */   }
/*     */   private boolean safeRemoveEncoder() {
/*     */     try {
/* 324 */       removeEncoder(this.ctx);
/* 325 */       return true;
/* 326 */     } catch (Exception e) {
/* 327 */       logger.warn("Failed to remove proxy encoders:", e);
/*     */ 
/*     */       
/* 330 */       return false;
/*     */     } 
/*     */   }
/*     */   private void setConnectFailure(Throwable cause) {
/* 334 */     this.finished = true;
/* 335 */     cancelConnectTimeoutFuture();
/*     */     
/* 337 */     if (!this.connectPromise.isDone()) {
/*     */       
/* 339 */       if (!(cause instanceof ProxyConnectException))
/*     */       {
/* 341 */         cause = new ProxyConnectException(exceptionMessage(cause.toString()), cause);
/*     */       }
/*     */       
/* 344 */       safeRemoveDecoder();
/* 345 */       safeRemoveEncoder();
/* 346 */       failPendingWritesAndClose(cause);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void failPendingWritesAndClose(Throwable cause) {
/* 351 */     failPendingWrites(cause);
/* 352 */     this.connectPromise.tryFailure(cause);
/* 353 */     this.ctx.fireExceptionCaught(cause);
/* 354 */     this.ctx.close();
/*     */   }
/*     */   
/*     */   private void cancelConnectTimeoutFuture() {
/* 358 */     if (this.connectTimeoutFuture != null) {
/* 359 */       this.connectTimeoutFuture.cancel(false);
/* 360 */       this.connectTimeoutFuture = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final String exceptionMessage(String msg) {
/* 369 */     if (msg == null) {
/* 370 */       msg = "";
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 380 */     StringBuilder buf = (new StringBuilder(128 + msg.length())).append(protocol()).append(", ").append(authScheme()).append(", ").append(this.proxyAddress).append(" => ").append(this.destinationAddress);
/* 381 */     if (!msg.isEmpty()) {
/* 382 */       buf.append(", ").append(msg);
/*     */     }
/*     */     
/* 385 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public final void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
/* 390 */     if (this.suppressChannelReadComplete) {
/* 391 */       this.suppressChannelReadComplete = false;
/*     */       
/* 393 */       readIfNeeded(ctx);
/*     */     } else {
/* 395 */       ctx.fireChannelReadComplete();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
/* 401 */     if (this.finished) {
/* 402 */       writePendingWrites();
/* 403 */       ctx.write(msg, promise);
/*     */     } else {
/* 405 */       addPendingWrite(ctx, msg, promise);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final void flush(ChannelHandlerContext ctx) throws Exception {
/* 411 */     if (this.finished) {
/* 412 */       writePendingWrites();
/* 413 */       ctx.flush();
/*     */     } else {
/* 415 */       this.flushedPrematurely = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void readIfNeeded(ChannelHandlerContext ctx) {
/* 420 */     if (!ctx.channel().config().isAutoRead()) {
/* 421 */       ctx.read();
/*     */     }
/*     */   }
/*     */   
/*     */   private void writePendingWrites() {
/* 426 */     if (this.pendingWrites != null) {
/* 427 */       this.pendingWrites.removeAndWriteAll();
/* 428 */       this.pendingWrites = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void failPendingWrites(Throwable cause) {
/* 433 */     if (this.pendingWrites != null) {
/* 434 */       this.pendingWrites.removeAndFailAll(cause);
/* 435 */       this.pendingWrites = null;
/*     */     } 
/*     */   } public abstract String protocol(); public abstract String authScheme(); protected abstract void addCodec(ChannelHandlerContext paramChannelHandlerContext) throws Exception;
/*     */   protected abstract void removeEncoder(ChannelHandlerContext paramChannelHandlerContext) throws Exception;
/*     */   private void addPendingWrite(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
/* 440 */     PendingWriteQueue pendingWrites = this.pendingWrites;
/* 441 */     if (pendingWrites == null) {
/* 442 */       this.pendingWrites = pendingWrites = new PendingWriteQueue(ctx);
/*     */     }
/* 444 */     pendingWrites.add(msg, promise);
/*     */   }
/*     */   protected abstract void removeDecoder(ChannelHandlerContext paramChannelHandlerContext) throws Exception;
/*     */   protected abstract Object newInitialMessage(ChannelHandlerContext paramChannelHandlerContext) throws Exception;
/*     */   protected abstract boolean handleResponse(ChannelHandlerContext paramChannelHandlerContext, Object paramObject) throws Exception;
/*     */   private final class LazyChannelPromise extends DefaultPromise<Channel> { protected EventExecutor executor() {
/* 450 */       if (ProxyHandler.this.ctx == null) {
/* 451 */         throw new IllegalStateException();
/*     */       }
/* 453 */       return ProxyHandler.this.ctx.executor();
/*     */     }
/*     */     
/*     */     private LazyChannelPromise() {} }
/*     */ 
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\proxy\ProxyHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */