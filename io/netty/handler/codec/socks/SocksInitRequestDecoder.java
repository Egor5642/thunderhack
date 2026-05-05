/*    */ package io.netty.handler.codec.socks;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandler;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.ReplayingDecoder;
/*    */ import java.util.ArrayList;
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
/*    */ public class SocksInitRequestDecoder
/*    */   extends ReplayingDecoder<SocksInitRequestDecoder.State>
/*    */ {
/*    */   public SocksInitRequestDecoder() {
/* 35 */     super(State.CHECK_PROTOCOL_VERSION);
/*    */   }
/*    */   protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
/*    */     byte authSchemeNum;
/*    */     List<SocksAuthScheme> authSchemes;
/* 40 */     switch ((State)state()) {
/*    */       case CHECK_PROTOCOL_VERSION:
/* 42 */         if (byteBuf.readByte() != SocksProtocolVersion.SOCKS5.byteValue()) {
/* 43 */           out.add(SocksCommonUtils.UNKNOWN_SOCKS_REQUEST);
/*    */           break;
/*    */         } 
/* 46 */         checkpoint(State.READ_AUTH_SCHEMES);
/*    */       
/*    */       case READ_AUTH_SCHEMES:
/* 49 */         authSchemeNum = byteBuf.readByte();
/*    */         
/* 51 */         if (authSchemeNum > 0) {
/* 52 */           authSchemes = new ArrayList<SocksAuthScheme>(authSchemeNum);
/* 53 */           for (int i = 0; i < authSchemeNum; i++) {
/* 54 */             authSchemes.add(SocksAuthScheme.valueOf(byteBuf.readByte()));
/*    */           }
/*    */         } else {
/* 57 */           authSchemes = Collections.emptyList();
/*    */         } 
/* 59 */         out.add(new SocksInitRequest(authSchemes));
/*    */         break;
/*    */       
/*    */       default:
/* 63 */         throw new Error();
/*    */     } 
/*    */     
/* 66 */     ctx.pipeline().remove((ChannelHandler)this);
/*    */   }
/*    */   
/*    */   public enum State
/*    */   {
/* 71 */     CHECK_PROTOCOL_VERSION,
/* 72 */     READ_AUTH_SCHEMES;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socks\SocksInitRequestDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */