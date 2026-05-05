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
/*    */ public class Socks5InitialRequestDecoder
/*    */   extends ReplayingDecoder<Socks5InitialRequestDecoder.State>
/*    */ {
/*    */   public enum State
/*    */   {
/* 40 */     INIT,
/* 41 */     SUCCESS,
/* 42 */     FAILURE;
/*    */   }
/*    */   
/*    */   public Socks5InitialRequestDecoder() {
/* 46 */     super(State.INIT); } protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception { try {
/*    */       byte version;
/*    */       int readableBytes;
/*    */       int authMethodCnt;
/*    */       Socks5AuthMethod[] authMethods;
/*    */       int i;
/* 52 */       switch ((State)state()) {
/*    */         case INIT:
/* 54 */           version = in.readByte();
/* 55 */           if (version != SocksVersion.SOCKS5.byteValue()) {
/* 56 */             throw new DecoderException("unsupported version: " + version + " (expected: " + SocksVersion.SOCKS5
/* 57 */                 .byteValue() + ')');
/*    */           }
/*    */           
/* 60 */           authMethodCnt = in.readUnsignedByte();
/*    */           
/* 62 */           authMethods = new Socks5AuthMethod[authMethodCnt];
/* 63 */           for (i = 0; i < authMethodCnt; i++) {
/* 64 */             authMethods[i] = Socks5AuthMethod.valueOf(in.readByte());
/*    */           }
/*    */           
/* 67 */           out.add(new DefaultSocks5InitialRequest(authMethods));
/* 68 */           checkpoint(State.SUCCESS);
/*    */         
/*    */         case SUCCESS:
/* 71 */           readableBytes = actualReadableBytes();
/* 72 */           if (readableBytes > 0) {
/* 73 */             out.add(in.readRetainedSlice(readableBytes));
/*    */           }
/*    */           break;
/*    */         
/*    */         case FAILURE:
/* 78 */           in.skipBytes(actualReadableBytes());
/*    */           break;
/*    */       } 
/*    */     
/* 82 */     } catch (Exception e) {
/* 83 */       fail(out, e);
/*    */     }  }
/*    */   
/*    */   private void fail(List<Object> out, Exception cause) {
/*    */     DecoderException decoderException;
/* 88 */     if (!(cause instanceof DecoderException)) {
/* 89 */       decoderException = new DecoderException(cause);
/*    */     }
/*    */     
/* 92 */     checkpoint(State.FAILURE);
/*    */     
/* 94 */     Socks5Message m = new DefaultSocks5InitialRequest(new Socks5AuthMethod[] { Socks5AuthMethod.NO_AUTH });
/* 95 */     m.setDecoderResult(DecoderResult.failure((Throwable)decoderException));
/* 96 */     out.add(m);
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socksx\v5\Socks5InitialRequestDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */