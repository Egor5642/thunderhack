/*     */ package io.netty.handler.codec.socksx.v5;
/*     */ 
/*     */ import io.netty.handler.codec.DecoderResult;
/*     */ import io.netty.util.NetUtil;
/*     */ import io.netty.util.internal.ObjectUtil;
/*     */ import io.netty.util.internal.StringUtil;
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
/*     */ public final class DefaultSocks5CommandResponse
/*     */   extends AbstractSocks5Message
/*     */   implements Socks5CommandResponse
/*     */ {
/*     */   private final Socks5CommandStatus status;
/*     */   private final Socks5AddressType bndAddrType;
/*     */   private final String bndAddr;
/*     */   private final int bndPort;
/*     */   
/*     */   public DefaultSocks5CommandResponse(Socks5CommandStatus status, Socks5AddressType bndAddrType) {
/*  36 */     this(status, bndAddrType, null, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultSocks5CommandResponse(Socks5CommandStatus status, Socks5AddressType bndAddrType, String bndAddr, int bndPort) {
/*  42 */     ObjectUtil.checkNotNull(status, "status");
/*  43 */     ObjectUtil.checkNotNull(bndAddrType, "bndAddrType");
/*     */     
/*  45 */     if (bndAddr != null) {
/*  46 */       if (bndAddrType == Socks5AddressType.IPv4) {
/*  47 */         if (!NetUtil.isValidIpV4Address(bndAddr)) {
/*  48 */           throw new IllegalArgumentException("bndAddr: " + bndAddr + " (expected: a valid IPv4 address)");
/*     */         }
/*  50 */       } else if (bndAddrType == Socks5AddressType.DOMAIN) {
/*  51 */         bndAddr = IDN.toASCII(bndAddr);
/*  52 */         if (bndAddr.length() > 255) {
/*  53 */           throw new IllegalArgumentException("bndAddr: " + bndAddr + " (expected: less than 256 chars)");
/*     */         }
/*  55 */       } else if (bndAddrType == Socks5AddressType.IPv6 && 
/*  56 */         !NetUtil.isValidIpV6Address(bndAddr)) {
/*  57 */         throw new IllegalArgumentException("bndAddr: " + bndAddr + " (expected: a valid IPv6 address)");
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*  62 */     if (bndPort < 0 || bndPort > 65535) {
/*  63 */       throw new IllegalArgumentException("bndPort: " + bndPort + " (expected: 0~65535)");
/*     */     }
/*  65 */     this.status = status;
/*  66 */     this.bndAddrType = bndAddrType;
/*  67 */     this.bndAddr = bndAddr;
/*  68 */     this.bndPort = bndPort;
/*     */   }
/*     */ 
/*     */   
/*     */   public Socks5CommandStatus status() {
/*  73 */     return this.status;
/*     */   }
/*     */ 
/*     */   
/*     */   public Socks5AddressType bndAddrType() {
/*  78 */     return this.bndAddrType;
/*     */   }
/*     */ 
/*     */   
/*     */   public String bndAddr() {
/*  83 */     return this.bndAddr;
/*     */   }
/*     */ 
/*     */   
/*     */   public int bndPort() {
/*  88 */     return this.bndPort;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  93 */     StringBuilder buf = new StringBuilder(128);
/*  94 */     buf.append(StringUtil.simpleClassName(this));
/*     */     
/*  96 */     DecoderResult decoderResult = decoderResult();
/*  97 */     if (!decoderResult.isSuccess()) {
/*  98 */       buf.append("(decoderResult: ");
/*  99 */       buf.append(decoderResult);
/* 100 */       buf.append(", status: ");
/*     */     } else {
/* 102 */       buf.append("(status: ");
/*     */     } 
/* 104 */     buf.append(status());
/* 105 */     buf.append(", bndAddrType: ");
/* 106 */     buf.append(bndAddrType());
/* 107 */     buf.append(", bndAddr: ");
/* 108 */     buf.append(bndAddr());
/* 109 */     buf.append(", bndPort: ");
/* 110 */     buf.append(bndPort());
/* 111 */     buf.append(')');
/*     */     
/* 113 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socksx\v5\DefaultSocks5CommandResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */