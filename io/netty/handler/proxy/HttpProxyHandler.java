/*     */ package io.netty.handler.proxy;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import io.netty.channel.ChannelHandler;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import io.netty.channel.ChannelInboundHandler;
/*     */ import io.netty.channel.ChannelOutboundHandler;
/*     */ import io.netty.channel.ChannelPipeline;
/*     */ import io.netty.channel.ChannelPromise;
/*     */ import io.netty.handler.codec.base64.Base64;
/*     */ import io.netty.handler.codec.http.DefaultFullHttpRequest;
/*     */ import io.netty.handler.codec.http.HttpClientCodec;
/*     */ import io.netty.handler.codec.http.HttpHeaderNames;
/*     */ import io.netty.handler.codec.http.HttpHeaders;
/*     */ import io.netty.handler.codec.http.HttpMethod;
/*     */ import io.netty.handler.codec.http.HttpResponse;
/*     */ import io.netty.handler.codec.http.HttpResponseStatus;
/*     */ import io.netty.handler.codec.http.HttpUtil;
/*     */ import io.netty.handler.codec.http.HttpVersion;
/*     */ import io.netty.util.AsciiString;
/*     */ import io.netty.util.CharsetUtil;
/*     */ import io.netty.util.internal.ObjectUtil;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.SocketAddress;
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
/*     */ 
/*     */ 
/*     */ public final class HttpProxyHandler
/*     */   extends ProxyHandler
/*     */ {
/*     */   private static final String PROTOCOL = "http";
/*     */   private static final String AUTH_BASIC = "basic";
/*     */   private final HttpClientCodecWrapper codecWrapper;
/*     */   private final String username;
/*     */   private final String password;
/*     */   private final CharSequence authorization;
/*     */   private final HttpHeaders outboundHeaders;
/*     */   private final boolean ignoreDefaultPortsInConnectHostHeader;
/*     */   private HttpResponseStatus status;
/*     */   private HttpHeaders inboundHeaders;
/*     */   
/*     */   public HttpProxyHandler(SocketAddress proxyAddress) {
/*  66 */     this(proxyAddress, (HttpHeaders)null);
/*     */   }
/*     */   
/*     */   public HttpProxyHandler(SocketAddress proxyAddress, HttpHeaders headers) {
/*  70 */     this(proxyAddress, headers, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public HttpProxyHandler(SocketAddress proxyAddress, HttpHeaders headers, boolean ignoreDefaultPortsInConnectHostHeader) {
/*  76 */     super(proxyAddress); this.codecWrapper = new HttpClientCodecWrapper();
/*  77 */     this.username = null;
/*  78 */     this.password = null;
/*  79 */     this.authorization = null;
/*  80 */     this.outboundHeaders = headers;
/*  81 */     this.ignoreDefaultPortsInConnectHostHeader = ignoreDefaultPortsInConnectHostHeader;
/*     */   }
/*     */   
/*     */   public HttpProxyHandler(SocketAddress proxyAddress, String username, String password) {
/*  85 */     this(proxyAddress, username, password, (HttpHeaders)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpProxyHandler(SocketAddress proxyAddress, String username, String password, HttpHeaders headers) {
/*  90 */     this(proxyAddress, username, password, headers, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HttpProxyHandler(SocketAddress proxyAddress, String username, String password, HttpHeaders headers, boolean ignoreDefaultPortsInConnectHostHeader) {
/*  98 */     super(proxyAddress); ByteBuf authzBase64; this.codecWrapper = new HttpClientCodecWrapper();
/*  99 */     this.username = (String)ObjectUtil.checkNotNull(username, "username");
/* 100 */     this.password = (String)ObjectUtil.checkNotNull(password, "password");
/*     */     
/* 102 */     ByteBuf authz = Unpooled.copiedBuffer(username + ':' + password, CharsetUtil.UTF_8);
/*     */     
/*     */     try {
/* 105 */       authzBase64 = Base64.encode(authz, false);
/*     */     } finally {
/* 107 */       authz.release();
/*     */     } 
/*     */     try {
/* 110 */       this.authorization = (CharSequence)new AsciiString("Basic " + authzBase64.toString(CharsetUtil.US_ASCII));
/*     */     } finally {
/* 112 */       authzBase64.release();
/*     */     } 
/*     */     
/* 115 */     this.outboundHeaders = headers;
/* 116 */     this.ignoreDefaultPortsInConnectHostHeader = ignoreDefaultPortsInConnectHostHeader;
/*     */   }
/*     */ 
/*     */   
/*     */   public String protocol() {
/* 121 */     return "http";
/*     */   }
/*     */ 
/*     */   
/*     */   public String authScheme() {
/* 126 */     return (this.authorization != null) ? "basic" : "none";
/*     */   }
/*     */   
/*     */   public String username() {
/* 130 */     return this.username;
/*     */   }
/*     */   
/*     */   public String password() {
/* 134 */     return this.password;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addCodec(ChannelHandlerContext ctx) throws Exception {
/* 139 */     ChannelPipeline p = ctx.pipeline();
/* 140 */     String name = ctx.name();
/* 141 */     p.addBefore(name, null, (ChannelHandler)this.codecWrapper);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void removeEncoder(ChannelHandlerContext ctx) throws Exception {
/* 146 */     this.codecWrapper.codec.removeOutboundHandler();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void removeDecoder(ChannelHandlerContext ctx) throws Exception {
/* 151 */     this.codecWrapper.codec.removeInboundHandler();
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object newInitialMessage(ChannelHandlerContext ctx) throws Exception {
/* 156 */     InetSocketAddress raddr = destinationAddress();
/*     */     
/* 158 */     String hostString = HttpUtil.formatHostnameForHttp(raddr);
/* 159 */     int port = raddr.getPort();
/* 160 */     String url = hostString + ":" + port;
/* 161 */     String hostHeader = (this.ignoreDefaultPortsInConnectHostHeader && (port == 80 || port == 443)) ? hostString : url;
/*     */ 
/*     */ 
/*     */     
/* 165 */     DefaultFullHttpRequest defaultFullHttpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.CONNECT, url, Unpooled.EMPTY_BUFFER, false);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 170 */     defaultFullHttpRequest.headers().set((CharSequence)HttpHeaderNames.HOST, hostHeader);
/*     */     
/* 172 */     if (this.authorization != null) {
/* 173 */       defaultFullHttpRequest.headers().set((CharSequence)HttpHeaderNames.PROXY_AUTHORIZATION, this.authorization);
/*     */     }
/*     */     
/* 176 */     if (this.outboundHeaders != null) {
/* 177 */       defaultFullHttpRequest.headers().add(this.outboundHeaders);
/*     */     }
/*     */     
/* 180 */     return defaultFullHttpRequest;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean handleResponse(ChannelHandlerContext ctx, Object response) throws Exception {
/* 185 */     if (response instanceof HttpResponse) {
/* 186 */       if (this.status != null) {
/* 187 */         throw new HttpProxyConnectException(exceptionMessage("too many responses"), null);
/*     */       }
/* 189 */       HttpResponse res = (HttpResponse)response;
/* 190 */       this.status = res.status();
/* 191 */       this.inboundHeaders = res.headers();
/*     */     } 
/*     */     
/* 194 */     boolean finished = response instanceof io.netty.handler.codec.http.LastHttpContent;
/* 195 */     if (finished) {
/* 196 */       if (this.status == null) {
/* 197 */         throw new HttpProxyConnectException(exceptionMessage("missing response"), this.inboundHeaders);
/*     */       }
/* 199 */       if (this.status.code() != 200) {
/* 200 */         throw new HttpProxyConnectException(exceptionMessage("status: " + this.status), this.inboundHeaders);
/*     */       }
/*     */     } 
/*     */     
/* 204 */     return finished;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final class HttpProxyConnectException
/*     */     extends ProxyConnectException
/*     */   {
/*     */     private static final long serialVersionUID = -8824334609292146066L;
/*     */ 
/*     */     
/*     */     private final HttpHeaders headers;
/*     */ 
/*     */ 
/*     */     
/*     */     public HttpProxyConnectException(String message, HttpHeaders headers) {
/* 220 */       super(message);
/* 221 */       this.headers = headers;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public HttpHeaders headers() {
/* 228 */       return this.headers;
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class HttpClientCodecWrapper implements ChannelInboundHandler, ChannelOutboundHandler {
/* 233 */     final HttpClientCodec codec = new HttpClientCodec();
/*     */ 
/*     */     
/*     */     public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
/* 237 */       this.codec.handlerAdded(ctx);
/*     */     }
/*     */ 
/*     */     
/*     */     public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
/* 242 */       this.codec.handlerRemoved(ctx);
/*     */     }
/*     */ 
/*     */     
/*     */     public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
/* 247 */       this.codec.exceptionCaught(ctx, cause);
/*     */     }
/*     */ 
/*     */     
/*     */     public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
/* 252 */       this.codec.channelRegistered(ctx);
/*     */     }
/*     */ 
/*     */     
/*     */     public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
/* 257 */       this.codec.channelUnregistered(ctx);
/*     */     }
/*     */ 
/*     */     
/*     */     public void channelActive(ChannelHandlerContext ctx) throws Exception {
/* 262 */       this.codec.channelActive(ctx);
/*     */     }
/*     */ 
/*     */     
/*     */     public void channelInactive(ChannelHandlerContext ctx) throws Exception {
/* 267 */       this.codec.channelInactive(ctx);
/*     */     }
/*     */ 
/*     */     
/*     */     public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
/* 272 */       this.codec.channelRead(ctx, msg);
/*     */     }
/*     */ 
/*     */     
/*     */     public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
/* 277 */       this.codec.channelReadComplete(ctx);
/*     */     }
/*     */ 
/*     */     
/*     */     public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
/* 282 */       this.codec.userEventTriggered(ctx, evt);
/*     */     }
/*     */ 
/*     */     
/*     */     public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
/* 287 */       this.codec.channelWritabilityChanged(ctx);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
/* 293 */       this.codec.bind(ctx, localAddress, promise);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
/* 299 */       this.codec.connect(ctx, remoteAddress, localAddress, promise);
/*     */     }
/*     */ 
/*     */     
/*     */     public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
/* 304 */       this.codec.disconnect(ctx, promise);
/*     */     }
/*     */ 
/*     */     
/*     */     public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
/* 309 */       this.codec.close(ctx, promise);
/*     */     }
/*     */ 
/*     */     
/*     */     public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
/* 314 */       this.codec.deregister(ctx, promise);
/*     */     }
/*     */ 
/*     */     
/*     */     public void read(ChannelHandlerContext ctx) throws Exception {
/* 319 */       this.codec.read(ctx);
/*     */     }
/*     */ 
/*     */     
/*     */     public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
/* 324 */       this.codec.write(ctx, msg, promise);
/*     */     }
/*     */ 
/*     */     
/*     */     public void flush(ChannelHandlerContext ctx) throws Exception {
/* 329 */       this.codec.flush(ctx);
/*     */     }
/*     */     
/*     */     private HttpClientCodecWrapper() {}
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\proxy\HttpProxyHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */