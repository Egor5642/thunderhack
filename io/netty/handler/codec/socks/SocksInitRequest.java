/*    */ package io.netty.handler.codec.socks;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.util.internal.ObjectUtil;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
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
/*    */ public final class SocksInitRequest
/*    */   extends SocksRequest
/*    */ {
/*    */   private final List<SocksAuthScheme> authSchemes;
/*    */   
/*    */   public SocksInitRequest(List<SocksAuthScheme> authSchemes) {
/* 34 */     super(SocksRequestType.INIT);
/* 35 */     this.authSchemes = (List<SocksAuthScheme>)ObjectUtil.checkNotNull(authSchemes, "authSchemes");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<SocksAuthScheme> authSchemes() {
/* 44 */     return Collections.unmodifiableList(this.authSchemes);
/*    */   }
/*    */ 
/*    */   
/*    */   public void encodeAsByteBuf(ByteBuf byteBuf) {
/* 49 */     byteBuf.writeByte(protocolVersion().byteValue());
/* 50 */     byteBuf.writeByte(this.authSchemes.size());
/* 51 */     for (SocksAuthScheme authScheme : this.authSchemes)
/* 52 */       byteBuf.writeByte(authScheme.byteValue()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socks\SocksInitRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */