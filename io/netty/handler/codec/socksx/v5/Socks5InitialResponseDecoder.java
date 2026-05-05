/*    */ package io.netty.handler.codec.socksx.v5;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.DecoderException;
/*    */ import io.netty.handler.codec.DecoderResult;
/*    */ import io.netty.handler.codec.ReplayingDecoder;
/*    */ import io.netty.handler.codec.socksx.SocksVersion;
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
/*    */ public class Socks5InitialResponseDecoder
/*    */   extends ReplayingDecoder<Socks5InitialResponseDecoder.State>
/*    */ {
/*    */   public enum State
/*    */   {
/* 40 */     INIT,
/* 41 */     SUCCESS,
/* 42 */     FAILURE;
/*    */   }
/*    */   
/*    */   public Socks5InitialResponseDecoder() {
/* 46 */     super(State.INIT);
/*    */   } protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
/*    */     try {
/*    */       byte version;
/*    */       int readableBytes;
/*    */       Socks5AuthMethod authMethod;
/* 52 */       switch ((State)state()) {
/*    */         case INIT:
/* 54 */           version = in.readByte();
/* 55 */           if (version != SocksVersion.SOCKS5.byteValue()) {
/* 56 */             throw new DecoderException("unsupported version: " + version + " (expected: " + SocksVersion.SOCKS5
/* 57 */                 .byteValue() + ')');
/*    */           }
/*    */           
/* 60 */           authMethod = Socks5AuthMethod.valueOf(in.readByte());
/* 61 */           out.add(new DefaultSocks5InitialResponse(authMethod));
/* 62 */           checkpoint(State.SUCCESS);
/*    */         
/*    */         case SUCCESS:
/* 65 */           readableBytes = actualReadableBytes();
/* 66 */           if (readableBytes > 0) {
/* 67 */             out.add(in.readRetainedSlice(readableBytes));
/*    */           }
/*    */           break;
/*    */         
/*    */         case FAILURE:
/* 72 */           in.skipBytes(actualReadableBytes());
/*    */           break;
/*    */       } 
/*    */     
/* 76 */     } catch (Exception e) {
/* 77 */       fail(out, e);
/*    */     } 
/*    */   }
/*    */   private void fail(List<Object> out, Exception cause) {
/*    */     DecoderException decoderException;
/* 82 */     if (!(cause instanceof DecoderException)) {
/* 83 */       decoderException = new DecoderException(cause);
/*    */     }
/*    */     
/* 86 */     checkpoint(State.FAILURE);
/*    */     
/* 88 */     Socks5Message m = new DefaultSocks5InitialResponse(Socks5AuthMethod.UNACCEPTED);
/* 89 */     m.setDecoderResult(DecoderResult.failure((Throwable)decoderException));
/* 90 */     out.add(m);
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socksx\v5\Socks5InitialResponseDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */