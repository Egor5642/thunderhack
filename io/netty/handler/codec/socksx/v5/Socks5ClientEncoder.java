/*     */ package io.netty.handler.codec.socksx.v5;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.ByteBufUtil;
/*     */ import io.netty.channel.ChannelHandler.Sharable;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import io.netty.handler.codec.EncoderException;
/*     */ import io.netty.handler.codec.MessageToByteEncoder;
/*     */ import io.netty.util.internal.ObjectUtil;
/*     */ import io.netty.util.internal.StringUtil;
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
/*     */ @Sharable
/*     */ public class Socks5ClientEncoder
/*     */   extends MessageToByteEncoder<Socks5Message>
/*     */ {
/*  37 */   public static final Socks5ClientEncoder DEFAULT = new Socks5ClientEncoder();
/*     */ 
/*     */   
/*     */   private final Socks5AddressEncoder addressEncoder;
/*     */ 
/*     */ 
/*     */   
/*     */   protected Socks5ClientEncoder() {
/*  45 */     this(Socks5AddressEncoder.DEFAULT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Socks5ClientEncoder(Socks5AddressEncoder addressEncoder) {
/*  52 */     this.addressEncoder = (Socks5AddressEncoder)ObjectUtil.checkNotNull(addressEncoder, "addressEncoder");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final Socks5AddressEncoder addressEncoder() {
/*  59 */     return this.addressEncoder;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void encode(ChannelHandlerContext ctx, Socks5Message msg, ByteBuf out) throws Exception {
/*  64 */     if (msg instanceof Socks5InitialRequest) {
/*  65 */       encodeAuthMethodRequest((Socks5InitialRequest)msg, out);
/*  66 */     } else if (msg instanceof Socks5PasswordAuthRequest) {
/*  67 */       encodePasswordAuthRequest((Socks5PasswordAuthRequest)msg, out);
/*  68 */     } else if (msg instanceof Socks5CommandRequest) {
/*  69 */       encodeCommandRequest((Socks5CommandRequest)msg, out);
/*     */     } else {
/*  71 */       throw new EncoderException("unsupported message type: " + StringUtil.simpleClassName(msg));
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void encodeAuthMethodRequest(Socks5InitialRequest msg, ByteBuf out) {
/*  76 */     out.writeByte(msg.version().byteValue());
/*     */     
/*  78 */     List<Socks5AuthMethod> authMethods = msg.authMethods();
/*  79 */     int numAuthMethods = authMethods.size();
/*  80 */     out.writeByte(numAuthMethods);
/*     */     
/*  82 */     if (authMethods instanceof java.util.RandomAccess) {
/*  83 */       for (int i = 0; i < numAuthMethods; i++) {
/*  84 */         out.writeByte(((Socks5AuthMethod)authMethods.get(i)).byteValue());
/*     */       }
/*     */     } else {
/*  87 */       for (Socks5AuthMethod a : authMethods) {
/*  88 */         out.writeByte(a.byteValue());
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void encodePasswordAuthRequest(Socks5PasswordAuthRequest msg, ByteBuf out) {
/*  94 */     out.writeByte(1);
/*     */     
/*  96 */     String username = msg.username();
/*  97 */     out.writeByte(username.length());
/*  98 */     ByteBufUtil.writeAscii(out, username);
/*     */     
/* 100 */     String password = msg.password();
/* 101 */     out.writeByte(password.length());
/* 102 */     ByteBufUtil.writeAscii(out, password);
/*     */   }
/*     */   
/*     */   private void encodeCommandRequest(Socks5CommandRequest msg, ByteBuf out) throws Exception {
/* 106 */     out.writeByte(msg.version().byteValue());
/* 107 */     out.writeByte(msg.type().byteValue());
/* 108 */     out.writeByte(0);
/*     */     
/* 110 */     Socks5AddressType dstAddrType = msg.dstAddrType();
/* 111 */     out.writeByte(dstAddrType.byteValue());
/* 112 */     this.addressEncoder.encodeAddress(dstAddrType, msg.dstAddr(), out);
/* 113 */     out.writeShort(msg.dstPort());
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socksx\v5\Socks5ClientEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */