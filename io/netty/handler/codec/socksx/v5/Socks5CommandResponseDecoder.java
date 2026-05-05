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
/*     */ public class Socks5CommandResponseDecoder
/*     */   extends ReplayingDecoder<Socks5CommandResponseDecoder.State>
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
/*     */   public Socks5CommandResponseDecoder() {
/*  49 */     this(Socks5AddressDecoder.DEFAULT);
/*     */   }
/*     */   
/*     */   public Socks5CommandResponseDecoder(Socks5AddressDecoder addressDecoder) {
/*  53 */     super(State.INIT);
/*  54 */     this.addressDecoder = (Socks5AddressDecoder)ObjectUtil.checkNotNull(addressDecoder, "addressDecoder"); } protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception { try {
/*     */       byte version; int readableBytes;
/*     */       Socks5CommandStatus status;
/*     */       Socks5AddressType addrType;
/*     */       String addr;
/*     */       int port;
/*  60 */       switch ((State)state()) {
/*     */         case INIT:
/*  62 */           version = in.readByte();
/*  63 */           if (version != SocksVersion.SOCKS5.byteValue()) {
/*  64 */             throw new DecoderException("unsupported version: " + version + " (expected: " + SocksVersion.SOCKS5
/*  65 */                 .byteValue() + ')');
/*     */           }
/*  67 */           status = Socks5CommandStatus.valueOf(in.readByte());
/*  68 */           in.skipBytes(1);
/*  69 */           addrType = Socks5AddressType.valueOf(in.readByte());
/*  70 */           addr = this.addressDecoder.decodeAddress(addrType, in);
/*  71 */           port = in.readUnsignedShort();
/*     */           
/*  73 */           out.add(new DefaultSocks5CommandResponse(status, addrType, addr, port));
/*  74 */           checkpoint(State.SUCCESS);
/*     */         
/*     */         case SUCCESS:
/*  77 */           readableBytes = actualReadableBytes();
/*  78 */           if (readableBytes > 0) {
/*  79 */             out.add(in.readRetainedSlice(readableBytes));
/*     */           }
/*     */           break;
/*     */         
/*     */         case FAILURE:
/*  84 */           in.skipBytes(actualReadableBytes());
/*     */           break;
/*     */       } 
/*     */     
/*  88 */     } catch (Exception e) {
/*  89 */       fail(out, e);
/*     */     }  }
/*     */   
/*     */   private void fail(List<Object> out, Exception cause) {
/*     */     DecoderException decoderException;
/*  94 */     if (!(cause instanceof DecoderException)) {
/*  95 */       decoderException = new DecoderException(cause);
/*     */     }
/*     */     
/*  98 */     checkpoint(State.FAILURE);
/*     */     
/* 100 */     Socks5Message m = new DefaultSocks5CommandResponse(Socks5CommandStatus.FAILURE, Socks5AddressType.IPv4, null, 0);
/*     */     
/* 102 */     m.setDecoderResult(DecoderResult.failure((Throwable)decoderException));
/* 103 */     out.add(m);
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socksx\v5\Socks5CommandResponseDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */