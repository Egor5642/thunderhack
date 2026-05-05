/*    */ package io.netty.handler.codec.socksx;
/*    */ 
/*    */ import io.netty.handler.codec.DecoderResult;
/*    */ import io.netty.util.internal.ObjectUtil;
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
/*    */ public abstract class AbstractSocksMessage
/*    */   implements SocksMessage
/*    */ {
/* 27 */   private DecoderResult decoderResult = DecoderResult.SUCCESS;
/*    */ 
/*    */   
/*    */   public DecoderResult decoderResult() {
/* 31 */     return this.decoderResult;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDecoderResult(DecoderResult decoderResult) {
/* 36 */     this.decoderResult = (DecoderResult)ObjectUtil.checkNotNull(decoderResult, "decoderResult");
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socksx\AbstractSocksMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */