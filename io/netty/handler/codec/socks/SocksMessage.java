/*    */ package io.netty.handler.codec.socks;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class SocksMessage
/*    */ {
/*    */   private final SocksMessageType type;
/* 31 */   private final SocksProtocolVersion protocolVersion = SocksProtocolVersion.SOCKS5;
/*    */   
/*    */   protected SocksMessage(SocksMessageType type) {
/* 34 */     this.type = (SocksMessageType)ObjectUtil.checkNotNull(type, "type");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SocksMessageType type() {
/* 43 */     return this.type;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SocksProtocolVersion protocolVersion() {
/* 52 */     return this.protocolVersion;
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public abstract void encodeAsByteBuf(ByteBuf paramByteBuf);
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socks\SocksMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */