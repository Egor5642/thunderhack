/*    */ package io.netty.handler.codec.socksx.v5;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.DecoderException;
/*    */ import io.netty.handler.codec.DecoderResult;
/*    */ import io.netty.handler.codec.ReplayingDecoder;
/*    */ import io.netty.util.CharsetUtil;
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
/*    */ public class Socks5PasswordAuthRequestDecoder
/*    */   extends ReplayingDecoder<Socks5PasswordAuthRequestDecoder.State>
/*    */ {
/*    */   public enum State
/*    */   {
/* 40 */     INIT,
/* 41 */     SUCCESS,
/* 42 */     FAILURE;
/*    */   }
/*    */   
/*    */   public Socks5PasswordAuthRequestDecoder() {
/* 46 */     super(State.INIT); } protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception { try {
/*    */       int startOffset; int readableBytes;
/*    */       byte version;
/*    */       int usernameLength;
/*    */       int passwordLength;
/*    */       int totalLength;
/* 52 */       switch ((State)state()) {
/*    */         case INIT:
/* 54 */           startOffset = in.readerIndex();
/* 55 */           version = in.getByte(startOffset);
/* 56 */           if (version != 1) {
/* 57 */             throw new DecoderException("unsupported subnegotiation version: " + version + " (expected: 1)");
/*    */           }
/*    */           
/* 60 */           usernameLength = in.getUnsignedByte(startOffset + 1);
/* 61 */           passwordLength = in.getUnsignedByte(startOffset + 2 + usernameLength);
/* 62 */           totalLength = usernameLength + passwordLength + 3;
/*    */           
/* 64 */           in.skipBytes(totalLength);
/* 65 */           out.add(new DefaultSocks5PasswordAuthRequest(in
/* 66 */                 .toString(startOffset + 2, usernameLength, CharsetUtil.US_ASCII), in
/* 67 */                 .toString(startOffset + 3 + usernameLength, passwordLength, CharsetUtil.US_ASCII)));
/*    */           
/* 69 */           checkpoint(State.SUCCESS);
/*    */         
/*    */         case SUCCESS:
/* 72 */           readableBytes = actualReadableBytes();
/* 73 */           if (readableBytes > 0) {
/* 74 */             out.add(in.readRetainedSlice(readableBytes));
/*    */           }
/*    */           break;
/*    */         
/*    */         case FAILURE:
/* 79 */           in.skipBytes(actualReadableBytes());
/*    */           break;
/*    */       } 
/*    */     
/* 83 */     } catch (Exception e) {
/* 84 */       fail(out, e);
/*    */     }  }
/*    */   
/*    */   private void fail(List<Object> out, Exception cause) {
/*    */     DecoderException decoderException;
/* 89 */     if (!(cause instanceof DecoderException)) {
/* 90 */       decoderException = new DecoderException(cause);
/*    */     }
/*    */     
/* 93 */     checkpoint(State.FAILURE);
/*    */     
/* 95 */     Socks5Message m = new DefaultSocks5PasswordAuthRequest("", "");
/* 96 */     m.setDecoderResult(DecoderResult.failure((Throwable)decoderException));
/* 97 */     out.add(m);
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socksx\v5\Socks5PasswordAuthRequestDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */