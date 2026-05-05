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
/*     */ 
/*     */ 
/*     */ public final class DefaultSocks5CommandRequest
/*     */   extends AbstractSocks5Message
/*     */   implements Socks5CommandRequest
/*     */ {
/*     */   private final Socks5CommandType type;
/*     */   private final Socks5AddressType dstAddrType;
/*     */   private final String dstAddr;
/*     */   private final int dstPort;
/*     */   
/*     */   public DefaultSocks5CommandRequest(Socks5CommandType type, Socks5AddressType dstAddrType, String dstAddr, int dstPort) {
/*  38 */     this.type = (Socks5CommandType)ObjectUtil.checkNotNull(type, "type");
/*  39 */     ObjectUtil.checkNotNull(dstAddrType, "dstAddrType");
/*  40 */     ObjectUtil.checkNotNull(dstAddr, "dstAddr");
/*     */     
/*  42 */     if (dstAddrType == Socks5AddressType.IPv4) {
/*  43 */       if (!NetUtil.isValidIpV4Address(dstAddr)) {
/*  44 */         throw new IllegalArgumentException("dstAddr: " + dstAddr + " (expected: a valid IPv4 address)");
/*     */       }
/*  46 */     } else if (dstAddrType == Socks5AddressType.DOMAIN) {
/*  47 */       dstAddr = IDN.toASCII(dstAddr);
/*  48 */       if (dstAddr.length() > 255) {
/*  49 */         throw new IllegalArgumentException("dstAddr: " + dstAddr + " (expected: less than 256 chars)");
/*     */       }
/*  51 */     } else if (dstAddrType == Socks5AddressType.IPv6 && 
/*  52 */       !NetUtil.isValidIpV6Address(dstAddr)) {
/*  53 */       throw new IllegalArgumentException("dstAddr: " + dstAddr + " (expected: a valid IPv6 address");
/*     */     } 
/*     */ 
/*     */     
/*  57 */     if (dstPort < 0 || dstPort > 65535) {
/*  58 */       throw new IllegalArgumentException("dstPort: " + dstPort + " (expected: 0~65535)");
/*     */     }
/*     */     
/*  61 */     this.dstAddrType = dstAddrType;
/*  62 */     this.dstAddr = dstAddr;
/*  63 */     this.dstPort = dstPort;
/*     */   }
/*     */ 
/*     */   
/*     */   public Socks5CommandType type() {
/*  68 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   public Socks5AddressType dstAddrType() {
/*  73 */     return this.dstAddrType;
/*     */   }
/*     */ 
/*     */   
/*     */   public String dstAddr() {
/*  78 */     return this.dstAddr;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dstPort() {
/*  83 */     return this.dstPort;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  88 */     StringBuilder buf = new StringBuilder(128);
/*  89 */     buf.append(StringUtil.simpleClassName(this));
/*     */     
/*  91 */     DecoderResult decoderResult = decoderResult();
/*  92 */     if (!decoderResult.isSuccess()) {
/*  93 */       buf.append("(decoderResult: ");
/*  94 */       buf.append(decoderResult);
/*  95 */       buf.append(", type: ");
/*     */     } else {
/*  97 */       buf.append("(type: ");
/*     */     } 
/*  99 */     buf.append(type());
/* 100 */     buf.append(", dstAddrType: ");
/* 101 */     buf.append(dstAddrType());
/* 102 */     buf.append(", dstAddr: ");
/* 103 */     buf.append(dstAddr());
/* 104 */     buf.append(", dstPort: ");
/* 105 */     buf.append(dstPort());
/* 106 */     buf.append(')');
/*     */     
/* 108 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socksx\v5\DefaultSocks5CommandRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */