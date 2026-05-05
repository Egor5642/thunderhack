/*     */ package io.netty.handler.codec.socksx.v5;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import io.netty.handler.codec.DecoderException;
/*     */ import io.netty.handler.codec.DecoderResult;
/*     */ import io.netty.handler.codec.ReplayingDecoder;
/*     */ import io.netty.handler.codec.socksx.SocksVersion;
/*     */ import io.netty.util.internal.ObjectUtil;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Socks5CommandRequestDecoder
/*     */   extends ReplayingDecoder<Socks5CommandRequestDecoder.State>
/*     */ {
/*     */   private final Socks5AddressDecoder addressDecoder;
/*     */   
/*     */   public enum State
/*     */   {
/*  41 */     INIT,
/*  42 */     SUCCESS,
/*  43 */     FAILURE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Socks5CommandRequestDecoder() {
/*  49 */     this(Socks5AddressDecoder.DEFAULT);
/*     */   }
/*     */   
/*     */   public Socks5CommandRequestDecoder(Socks5AddressDecoder addressDecoder) {
/*  53 */     super(State.INIT);
/*  54 */     this.addressDecoder = (Socks5AddressDecoder)ObjectUtil.checkNotNull(addressDecoder, "addressDecoder"); } protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception { try {
/*     */       byte version; int readableBytes;
/*     */       Socks5CommandType type;
/*     */       Socks5AddressType dstAddrType;
/*     */       String dstAddr;
/*     */       int dstPort;
/*  60 */       switch ((State)state()) {
/*     */         case INIT:
/*  62 */           version = in.readByte();
/*  63 */           if (version != SocksVersion.SOCKS5.byteValue()) {
/*  64 */             throw new DecoderException("unsupported version: " + version + " (expected: " + SocksVersion.SOCKS5
/*  65 */                 .byteValue() + ')');
/*     */           }
/*     */           
/*  68 */           type = Socks5CommandType.valueOf(in.readByte());
/*  69 */           in.skipBytes(1);
/*  70 */           dstAddrType = Socks5AddressType.valueOf(in.readByte());
/*  71 */           dstAddr = this.addressDecoder.decodeAddress(dstAddrType, in);
/*  72 */           dstPort = in.readUnsignedShort();
/*     */           
/*  74 */           out.add(new DefaultSocks5CommandRequest(type, dstAddrType, dstAddr, dstPort));
/*  75 */           checkpoint(State.SUCCESS);
/*     */         
/*     */         case SUCCESS:
/*  78 */           readableBytes = actualReadableBytes();
/*  79 */           if (readableBytes > 0) {
/*  80 */             out.add(in.readRetainedSlice(readableBytes));
/*     */           }
/*     */           break;
/*     */         
/*     */         case FAILURE:
/*  85 */           in.skipBytes(actualReadableBytes());
/*     */           break;
/*     */       } 
/*     */     
/*  89 */     } catch (Exception e) {
/*  90 */       fail(out, e);
/*     */     }  }
/*     */   
/*     */   private void fail(List<Object> out, Exception cause) {
/*     */     DecoderException decoderException;
/*  95 */     if (!(cause instanceof DecoderException)) {
/*  96 */       decoderException = new DecoderException(cause);
/*     */     }
/*     */     
/*  99 */     checkpoint(State.FAILURE);
/*     */     
/* 101 */     Socks5Message m = new DefaultSocks5CommandRequest(Socks5CommandType.CONNECT, Socks5AddressType.IPv4, "0.0.0.0", 1);
/*     */     
/* 103 */     m.setDecoderResult(DecoderResult.failure((Throwable)decoderException));
/* 104 */     out.add(m);
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socksx\v5\Socks5CommandRequestDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */