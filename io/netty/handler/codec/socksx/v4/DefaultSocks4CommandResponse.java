/*    */ package io.netty.handler.codec.socksx.v4;
/*    */ 
/*    */ import io.netty.handler.codec.DecoderResult;
/*    */ import io.netty.util.NetUtil;
/*    */ import io.netty.util.internal.ObjectUtil;
/*    */ import io.netty.util.internal.StringUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultSocks4CommandResponse
/*    */   extends AbstractSocks4Message
/*    */   implements Socks4CommandResponse
/*    */ {
/*    */   private final Socks4CommandStatus status;
/*    */   private final String dstAddr;
/*    */   private final int dstPort;
/*    */   
/*    */   public DefaultSocks4CommandResponse(Socks4CommandStatus status) {
/* 38 */     this(status, null, 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DefaultSocks4CommandResponse(Socks4CommandStatus status, String dstAddr, int dstPort) {
/* 49 */     if (dstAddr != null && 
/* 50 */       !NetUtil.isValidIpV4Address(dstAddr)) {
/* 51 */       throw new IllegalArgumentException("dstAddr: " + dstAddr + " (expected: a valid IPv4 address)");
/*    */     }
/*    */ 
/*    */     
/* 55 */     if (dstPort < 0 || dstPort > 65535) {
/* 56 */       throw new IllegalArgumentException("dstPort: " + dstPort + " (expected: 0~65535)");
/*    */     }
/*    */     
/* 59 */     this.status = (Socks4CommandStatus)ObjectUtil.checkNotNull(status, "cmdStatus");
/* 60 */     this.dstAddr = dstAddr;
/* 61 */     this.dstPort = dstPort;
/*    */   }
/*    */ 
/*    */   
/*    */   public Socks4CommandStatus status() {
/* 66 */     return this.status;
/*    */   }
/*    */ 
/*    */   
/*    */   public String dstAddr() {
/* 71 */     return this.dstAddr;
/*    */   }
/*    */ 
/*    */   
/*    */   public int dstPort() {
/* 76 */     return this.dstPort;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 81 */     StringBuilder buf = new StringBuilder(96);
/* 82 */     buf.append(StringUtil.simpleClassName(this));
/*    */     
/* 84 */     DecoderResult decoderResult = decoderResult();
/* 85 */     if (!decoderResult.isSuccess()) {
/* 86 */       buf.append("(decoderResult: ");
/* 87 */       buf.append(decoderResult);
/* 88 */       buf.append(", dstAddr: ");
/*    */     } else {
/* 90 */       buf.append("(dstAddr: ");
/*    */     } 
/* 92 */     buf.append(dstAddr());
/* 93 */     buf.append(", dstPort: ");
/* 94 */     buf.append(dstPort());
/* 95 */     buf.append(')');
/*    */     
/* 97 */     return buf.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socksx\v4\DefaultSocks4CommandResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */