/*    */ package io.netty.handler.codec.socksx.v4;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.DecoderException;
/*    */ import io.netty.handler.codec.DecoderResult;
/*    */ import io.netty.handler.codec.ReplayingDecoder;
/*    */ import io.netty.util.NetUtil;
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
/*    */ public class Socks4ClientDecoder
/*    */   extends ReplayingDecoder<Socks4ClientDecoder.State>
/*    */ {
/*    */   public enum State
/*    */   {
/* 39 */     START,
/* 40 */     SUCCESS,
/* 41 */     FAILURE;
/*    */   }
/*    */   
/*    */   public Socks4ClientDecoder() {
/* 45 */     super(State.START);
/* 46 */     setSingleDecode(true); } protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception { try {
/*    */       int version;
/*    */       int readableBytes;
/*    */       Socks4CommandStatus status;
/*    */       int dstPort;
/*    */       String dstAddr;
/* 52 */       switch ((State)state()) {
/*    */         case START:
/* 54 */           version = in.readUnsignedByte();
/* 55 */           if (version != 0) {
/* 56 */             throw new DecoderException("unsupported reply version: " + version + " (expected: 0)");
/*    */           }
/*    */           
/* 59 */           status = Socks4CommandStatus.valueOf(in.readByte());
/* 60 */           dstPort = in.readUnsignedShort();
/* 61 */           dstAddr = NetUtil.intToIpAddress(in.readInt());
/*    */           
/* 63 */           out.add(new DefaultSocks4CommandResponse(status, dstAddr, dstPort));
/* 64 */           checkpoint(State.SUCCESS);
/*    */         
/*    */         case SUCCESS:
/* 67 */           readableBytes = actualReadableBytes();
/* 68 */           if (readableBytes > 0) {
/* 69 */             out.add(in.readRetainedSlice(readableBytes));
/*    */           }
/*    */           break;
/*    */         
/*    */         case FAILURE:
/* 74 */           in.skipBytes(actualReadableBytes());
/*    */           break;
/*    */       } 
/*    */     
/* 78 */     } catch (Exception e) {
/* 79 */       fail(out, e);
/*    */     }  }
/*    */   
/*    */   private void fail(List<Object> out, Exception cause) {
/*    */     DecoderException decoderException;
/* 84 */     if (!(cause instanceof DecoderException)) {
/* 85 */       decoderException = new DecoderException(cause);
/*    */     }
/*    */     
/* 88 */     Socks4CommandResponse m = new DefaultSocks4CommandResponse(Socks4CommandStatus.REJECTED_OR_FAILED);
/* 89 */     m.setDecoderResult(DecoderResult.failure((Throwable)decoderException));
/* 90 */     out.add(m);
/*    */     
/* 92 */     checkpoint(State.FAILURE);
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socksx\v4\Socks4ClientDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */