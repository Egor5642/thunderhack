/*     */ package io.netty.handler.codec.socks;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.util.CharsetUtil;
/*     */ import io.netty.util.NetUtil;
/*     */ import io.netty.util.internal.ObjectUtil;
/*     */ import java.net.IDN;
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
/*     */ public final class SocksCmdRequest
/*     */   extends SocksRequest
/*     */ {
/*     */   private final SocksCmdType cmdType;
/*     */   private final SocksAddressType addressType;
/*     */   private final String host;
/*     */   private final int port;
/*     */   
/*     */   public SocksCmdRequest(SocksCmdType cmdType, SocksAddressType addressType, String host, int port) {
/*  38 */     super(SocksRequestType.CMD); String asciiHost;
/*  39 */     ObjectUtil.checkNotNull(cmdType, "cmdType");
/*  40 */     ObjectUtil.checkNotNull(addressType, "addressType");
/*  41 */     ObjectUtil.checkNotNull(host, "host");
/*     */     
/*  43 */     switch (addressType) {
/*     */       case IPv4:
/*  45 */         if (!NetUtil.isValidIpV4Address(host)) {
/*  46 */           throw new IllegalArgumentException(host + " is not a valid IPv4 address");
/*     */         }
/*     */         break;
/*     */       case DOMAIN:
/*  50 */         asciiHost = IDN.toASCII(host);
/*  51 */         if (asciiHost.length() > 255) {
/*  52 */           throw new IllegalArgumentException(host + " IDN: " + asciiHost + " exceeds 255 char limit");
/*     */         }
/*  54 */         host = asciiHost;
/*     */         break;
/*     */       case IPv6:
/*  57 */         if (!NetUtil.isValidIpV6Address(host)) {
/*  58 */           throw new IllegalArgumentException(host + " is not a valid IPv6 address");
/*     */         }
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/*  64 */     if (port <= 0 || port >= 65536) {
/*  65 */       throw new IllegalArgumentException(port + " is not in bounds 0 < x < 65536");
/*     */     }
/*  67 */     this.cmdType = cmdType;
/*  68 */     this.addressType = addressType;
/*  69 */     this.host = host;
/*  70 */     this.port = port;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SocksCmdType cmdType() {
/*  79 */     return this.cmdType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SocksAddressType addressType() {
/*  88 */     return this.addressType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String host() {
/*  97 */     return (this.addressType == SocksAddressType.DOMAIN) ? IDN.toUnicode(this.host) : this.host;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int port() {
/* 106 */     return this.port;
/*     */   }
/*     */ 
/*     */   
/*     */   public void encodeAsByteBuf(ByteBuf byteBuf) {
/* 111 */     byteBuf.writeByte(protocolVersion().byteValue());
/* 112 */     byteBuf.writeByte(this.cmdType.byteValue());
/* 113 */     byteBuf.writeByte(0);
/* 114 */     byteBuf.writeByte(this.addressType.byteValue());
/* 115 */     switch (this.addressType) {
/*     */       case IPv4:
/* 117 */         byteBuf.writeBytes(NetUtil.createByteArrayFromIpAddressString(this.host));
/* 118 */         byteBuf.writeShort(this.port);
/*     */         break;
/*     */ 
/*     */       
/*     */       case DOMAIN:
/* 123 */         byteBuf.writeByte(this.host.length());
/* 124 */         byteBuf.writeCharSequence(this.host, CharsetUtil.US_ASCII);
/* 125 */         byteBuf.writeShort(this.port);
/*     */         break;
/*     */ 
/*     */       
/*     */       case IPv6:
/* 130 */         byteBuf.writeBytes(NetUtil.createByteArrayFromIpAddressString(this.host));
/* 131 */         byteBuf.writeShort(this.port);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socks\SocksCmdRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */