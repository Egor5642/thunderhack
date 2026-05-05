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
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class SocksCmdResponse
/*     */   extends SocksResponse
/*     */ {
/*     */   private final SocksCmdStatus cmdStatus;
/*     */   private final SocksAddressType addressType;
/*     */   private final String host;
/*     */   private final int port;
/*  39 */   private static final byte[] DOMAIN_ZEROED = new byte[] { 0 };
/*  40 */   private static final byte[] IPv4_HOSTNAME_ZEROED = new byte[] { 0, 0, 0, 0 };
/*  41 */   private static final byte[] IPv6_HOSTNAME_ZEROED = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SocksCmdResponse(SocksCmdStatus cmdStatus, SocksAddressType addressType) {
/*  47 */     this(cmdStatus, addressType, null, 0);
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
/*     */   public SocksCmdResponse(SocksCmdStatus cmdStatus, SocksAddressType addressType, String host, int port) {
/*  64 */     super(SocksResponseType.CMD);
/*  65 */     ObjectUtil.checkNotNull(cmdStatus, "cmdStatus");
/*  66 */     ObjectUtil.checkNotNull(addressType, "addressType");
/*  67 */     if (host != null) {
/*  68 */       String asciiHost; switch (addressType) {
/*     */         case IPv4:
/*  70 */           if (!NetUtil.isValidIpV4Address(host)) {
/*  71 */             throw new IllegalArgumentException(host + " is not a valid IPv4 address");
/*     */           }
/*     */           break;
/*     */         case DOMAIN:
/*  75 */           asciiHost = IDN.toASCII(host);
/*  76 */           if (asciiHost.length() > 255) {
/*  77 */             throw new IllegalArgumentException(host + " IDN: " + asciiHost + " exceeds 255 char limit");
/*     */           }
/*  79 */           host = asciiHost;
/*     */           break;
/*     */         case IPv6:
/*  82 */           if (!NetUtil.isValidIpV6Address(host)) {
/*  83 */             throw new IllegalArgumentException(host + " is not a valid IPv6 address");
/*     */           }
/*     */           break;
/*     */       } 
/*     */ 
/*     */     
/*     */     } 
/*  90 */     if (port < 0 || port > 65535) {
/*  91 */       throw new IllegalArgumentException(port + " is not in bounds 0 <= x <= 65535");
/*     */     }
/*  93 */     this.cmdStatus = cmdStatus;
/*  94 */     this.addressType = addressType;
/*  95 */     this.host = host;
/*  96 */     this.port = port;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SocksCmdStatus cmdStatus() {
/* 105 */     return this.cmdStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SocksAddressType addressType() {
/* 114 */     return this.addressType;
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
/*     */   public String host() {
/* 126 */     return (this.host != null && this.addressType == SocksAddressType.DOMAIN) ? IDN.toUnicode(this.host) : this.host;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int port() {
/* 136 */     return this.port;
/*     */   }
/*     */   
/*     */   public void encodeAsByteBuf(ByteBuf byteBuf) {
/*     */     byte[] hostContent;
/* 141 */     byteBuf.writeByte(protocolVersion().byteValue());
/* 142 */     byteBuf.writeByte(this.cmdStatus.byteValue());
/* 143 */     byteBuf.writeByte(0);
/* 144 */     byteBuf.writeByte(this.addressType.byteValue());
/* 145 */     switch (this.addressType) {
/*     */       
/*     */       case IPv4:
/* 148 */         hostContent = (this.host == null) ? IPv4_HOSTNAME_ZEROED : NetUtil.createByteArrayFromIpAddressString(this.host);
/* 149 */         byteBuf.writeBytes(hostContent);
/* 150 */         byteBuf.writeShort(this.port);
/*     */         break;
/*     */       
/*     */       case DOMAIN:
/* 154 */         if (this.host != null) {
/* 155 */           byteBuf.writeByte(this.host.length());
/* 156 */           byteBuf.writeCharSequence(this.host, CharsetUtil.US_ASCII);
/*     */         } else {
/* 158 */           byteBuf.writeByte(DOMAIN_ZEROED.length);
/* 159 */           byteBuf.writeBytes(DOMAIN_ZEROED);
/*     */         } 
/* 161 */         byteBuf.writeShort(this.port);
/*     */         break;
/*     */ 
/*     */       
/*     */       case IPv6:
/* 166 */         hostContent = (this.host == null) ? IPv6_HOSTNAME_ZEROED : NetUtil.createByteArrayFromIpAddressString(this.host);
/* 167 */         byteBuf.writeBytes(hostContent);
/* 168 */         byteBuf.writeShort(this.port);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socks\SocksCmdResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */