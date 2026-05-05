/*     */ package io.netty.handler.codec.socksx.v4;
/*     */ 
/*     */ import io.netty.handler.codec.DecoderResult;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultSocks4CommandRequest
/*     */   extends AbstractSocks4Message
/*     */   implements Socks4CommandRequest
/*     */ {
/*     */   private final Socks4CommandType type;
/*     */   private final String dstAddr;
/*     */   private final int dstPort;
/*     */   private final String userId;
/*     */   
/*     */   public DefaultSocks4CommandRequest(Socks4CommandType type, String dstAddr, int dstPort) {
/*  42 */     this(type, dstAddr, dstPort, "");
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
/*     */   public DefaultSocks4CommandRequest(Socks4CommandType type, String dstAddr, int dstPort, String userId) {
/*  54 */     if (dstPort <= 0 || dstPort >= 65536) {
/*  55 */       throw new IllegalArgumentException("dstPort: " + dstPort + " (expected: 1~65535)");
/*     */     }
/*  57 */     this.type = (Socks4CommandType)ObjectUtil.checkNotNull(type, "type");
/*  58 */     this.dstAddr = IDN.toASCII(
/*  59 */         (String)ObjectUtil.checkNotNull(dstAddr, "dstAddr"));
/*  60 */     this.userId = (String)ObjectUtil.checkNotNull(userId, "userId");
/*  61 */     this.dstPort = dstPort;
/*     */   }
/*     */ 
/*     */   
/*     */   public Socks4CommandType type() {
/*  66 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   public String dstAddr() {
/*  71 */     return this.dstAddr;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dstPort() {
/*  76 */     return this.dstPort;
/*     */   }
/*     */ 
/*     */   
/*     */   public String userId() {
/*  81 */     return this.userId;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  86 */     StringBuilder buf = new StringBuilder(128);
/*  87 */     buf.append(StringUtil.simpleClassName(this));
/*     */     
/*  89 */     DecoderResult decoderResult = decoderResult();
/*  90 */     if (!decoderResult.isSuccess()) {
/*  91 */       buf.append("(decoderResult: ");
/*  92 */       buf.append(decoderResult);
/*  93 */       buf.append(", type: ");
/*     */     } else {
/*  95 */       buf.append("(type: ");
/*     */     } 
/*  97 */     buf.append(type());
/*  98 */     buf.append(", dstAddr: ");
/*  99 */     buf.append(dstAddr());
/* 100 */     buf.append(", dstPort: ");
/* 101 */     buf.append(dstPort());
/* 102 */     buf.append(", userId: ");
/* 103 */     buf.append(userId());
/* 104 */     buf.append(')');
/*     */     
/* 106 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socksx\v4\DefaultSocks4CommandRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */