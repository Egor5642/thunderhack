/*    */ package io.netty.handler.codec.socksx.v5;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.DecoderException;
/*    */ import io.netty.handler.codec.DecoderResult;
/*    */ import io.netty.handler.codec.ReplayingDecoder;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Socks5PasswordAuthResponseDecoder
/*    */   extends ReplayingDecoder<Socks5PasswordAuthResponseDecoder.State>
/*    */ {
/*    */   public enum State
/*    */   {
/* 39 */     INIT,
/* 40 */     SUCCESS,
/* 41 */     FAILURE;
/*    */   }
/*    */   
/*    */   public Socks5PasswordAuthResponseDecoder() {
/* 45 */     super(State.INIT);
/*    */   }
/*    */   protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
/*    */     try {
/*    */       byte version;
/*    */       int readableBytes;
/* 51 */       switch ((State)state()) {
/*    */         case INIT:
/* 53 */           version = in.readByte();
/* 54 */           if (version != 1) {
/* 55 */             throw new DecoderException("unsupported subnegotiation version: " + version + " (expected: 1)");
/*    */           }
/*    */           
/* 58 */           out.add(new DefaultSocks5PasswordAuthResponse(Socks5PasswordAuthStatus.valueOf(in.readByte())));
/* 59 */           checkpoint(State.SUCCESS);
/*    */         
/*    */         case SUCCESS:
/* 62 */           readableBytes = actualReadableBytes();
/* 63 */           if (readableBytes > 0) {
/* 64 */             out.add(in.readRetainedSlice(readableBytes));
/*    */           }
/*    */           break;
/*    */         
/*    */         case FAILURE:
/* 69 */           in.skipBytes(actualReadableBytes());
/*    */           break;
/*    */       } 
/*    */     
/* 73 */     } catch (Exception e) {
/* 74 */       fail(out, e);
/*    */     } 
/*    */   }
/*    */   private void fail(List<Object> out, Exception cause) {
/*    */     DecoderException decoderException;
/* 79 */     if (!(cause instanceof DecoderException)) {
/* 80 */       decoderException = new DecoderException(cause);
/*    */     }
/*    */     
/* 83 */     checkpoint(State.FAILURE);
/*    */     
/* 85 */     Socks5Message m = new DefaultSocks5PasswordAuthResponse(Socks5PasswordAuthStatus.FAILURE);
/* 86 */     m.setDecoderResult(DecoderResult.failure((Throwable)decoderException));
/* 87 */     out.add(m);
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socksx\v5\Socks5PasswordAuthResponseDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */