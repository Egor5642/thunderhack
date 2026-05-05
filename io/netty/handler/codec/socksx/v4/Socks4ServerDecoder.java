/*     */ package io.netty.handler.codec.socksx.v4;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import io.netty.handler.codec.DecoderException;
/*     */ import io.netty.handler.codec.DecoderResult;
/*     */ import io.netty.handler.codec.ReplayingDecoder;
/*     */ import io.netty.handler.codec.socksx.SocksVersion;
/*     */ import io.netty.util.CharsetUtil;
/*     */ import io.netty.util.NetUtil;
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
/*     */ public class Socks4ServerDecoder
/*     */   extends ReplayingDecoder<Socks4ServerDecoder.State>
/*     */ {
/*     */   private static final int MAX_FIELD_LENGTH = 255;
/*     */   private Socks4CommandType type;
/*     */   private String dstAddr;
/*     */   private int dstPort;
/*     */   private String userId;
/*     */   
/*     */   public enum State
/*     */   {
/*  43 */     START,
/*  44 */     READ_USERID,
/*  45 */     READ_DOMAIN,
/*  46 */     SUCCESS,
/*  47 */     FAILURE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Socks4ServerDecoder() {
/*  56 */     super(State.START);
/*  57 */     setSingleDecode(true);
/*     */   }
/*     */   protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
/*     */     try {
/*     */       int version;
/*     */       int readableBytes;
/*  63 */       switch ((State)state()) {
/*     */         case START:
/*  65 */           version = in.readUnsignedByte();
/*  66 */           if (version != SocksVersion.SOCKS4a.byteValue()) {
/*  67 */             throw new DecoderException("unsupported protocol version: " + version);
/*     */           }
/*     */           
/*  70 */           this.type = Socks4CommandType.valueOf(in.readByte());
/*  71 */           this.dstPort = in.readUnsignedShort();
/*  72 */           this.dstAddr = NetUtil.intToIpAddress(in.readInt());
/*  73 */           checkpoint(State.READ_USERID);
/*     */         
/*     */         case READ_USERID:
/*  76 */           this.userId = readString("userid", in);
/*  77 */           checkpoint(State.READ_DOMAIN);
/*     */ 
/*     */         
/*     */         case READ_DOMAIN:
/*  81 */           if (!"0.0.0.0".equals(this.dstAddr) && this.dstAddr.startsWith("0.0.0.")) {
/*  82 */             this.dstAddr = readString("dstAddr", in);
/*     */           }
/*  84 */           out.add(new DefaultSocks4CommandRequest(this.type, this.dstAddr, this.dstPort, this.userId));
/*  85 */           checkpoint(State.SUCCESS);
/*     */         
/*     */         case SUCCESS:
/*  88 */           readableBytes = actualReadableBytes();
/*  89 */           if (readableBytes > 0) {
/*  90 */             out.add(in.readRetainedSlice(readableBytes));
/*     */           }
/*     */           break;
/*     */         
/*     */         case FAILURE:
/*  95 */           in.skipBytes(actualReadableBytes());
/*     */           break;
/*     */       } 
/*     */     
/*  99 */     } catch (Exception e) {
/* 100 */       fail(out, e);
/*     */     } 
/*     */   }
/*     */   private void fail(List<Object> out, Exception cause) {
/*     */     DecoderException decoderException;
/* 105 */     if (!(cause instanceof DecoderException)) {
/* 106 */       decoderException = new DecoderException(cause);
/*     */     }
/*     */     
/* 109 */     Socks4CommandRequest m = new DefaultSocks4CommandRequest((this.type != null) ? this.type : Socks4CommandType.CONNECT, (this.dstAddr != null) ? this.dstAddr : "", (this.dstPort != 0) ? this.dstPort : 65535, (this.userId != null) ? this.userId : "");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 115 */     m.setDecoderResult(DecoderResult.failure((Throwable)decoderException));
/* 116 */     out.add(m);
/*     */     
/* 118 */     checkpoint(State.FAILURE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String readString(String fieldName, ByteBuf in) {
/* 125 */     int length = in.bytesBefore(256, (byte)0);
/* 126 */     if (length < 0) {
/* 127 */       throw new DecoderException("field '" + fieldName + "' longer than " + 'ÿ' + " chars");
/*     */     }
/*     */     
/* 130 */     String value = in.readSlice(length).toString(CharsetUtil.US_ASCII);
/* 131 */     in.skipBytes(1);
/*     */     
/* 133 */     return value;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socksx\v4\Socks4ServerDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */