/*    */ package io.netty.handler.codec.socksx.v5;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandler.Sharable;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.EncoderException;
/*    */ import io.netty.handler.codec.MessageToByteEncoder;
/*    */ import io.netty.util.internal.ObjectUtil;
/*    */ import io.netty.util.internal.StringUtil;
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
/*    */ @Sharable
/*    */ public class Socks5ServerEncoder
/*    */   extends MessageToByteEncoder<Socks5Message>
/*    */ {
/* 33 */   public static final Socks5ServerEncoder DEFAULT = new Socks5ServerEncoder(Socks5AddressEncoder.DEFAULT);
/*    */ 
/*    */   
/*    */   private final Socks5AddressEncoder addressEncoder;
/*    */ 
/*    */ 
/*    */   
/*    */   protected Socks5ServerEncoder() {
/* 41 */     this(Socks5AddressEncoder.DEFAULT);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Socks5ServerEncoder(Socks5AddressEncoder addressEncoder) {
/* 48 */     this.addressEncoder = (Socks5AddressEncoder)ObjectUtil.checkNotNull(addressEncoder, "addressEncoder");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected final Socks5AddressEncoder addressEncoder() {
/* 55 */     return this.addressEncoder;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void encode(ChannelHandlerContext ctx, Socks5Message msg, ByteBuf out) throws Exception {
/* 60 */     if (msg instanceof Socks5InitialResponse) {
/* 61 */       encodeAuthMethodResponse((Socks5InitialResponse)msg, out);
/* 62 */     } else if (msg instanceof Socks5PasswordAuthResponse) {
/* 63 */       encodePasswordAuthResponse((Socks5PasswordAuthResponse)msg, out);
/* 64 */     } else if (msg instanceof Socks5CommandResponse) {
/* 65 */       encodeCommandResponse((Socks5CommandResponse)msg, out);
/*    */     } else {
/* 67 */       throw new EncoderException("unsupported message type: " + StringUtil.simpleClassName(msg));
/*    */     } 
/*    */   }
/*    */   
/*    */   private static void encodeAuthMethodResponse(Socks5InitialResponse msg, ByteBuf out) {
/* 72 */     out.writeByte(msg.version().byteValue());
/* 73 */     out.writeByte(msg.authMethod().byteValue());
/*    */   }
/*    */   
/*    */   private static void encodePasswordAuthResponse(Socks5PasswordAuthResponse msg, ByteBuf out) {
/* 77 */     out.writeByte(1);
/* 78 */     out.writeByte(msg.status().byteValue());
/*    */   }
/*    */   
/*    */   private void encodeCommandResponse(Socks5CommandResponse msg, ByteBuf out) throws Exception {
/* 82 */     out.writeByte(msg.version().byteValue());
/* 83 */     out.writeByte(msg.status().byteValue());
/* 84 */     out.writeByte(0);
/*    */     
/* 86 */     Socks5AddressType bndAddrType = msg.bndAddrType();
/* 87 */     out.writeByte(bndAddrType.byteValue());
/* 88 */     this.addressEncoder.encodeAddress(bndAddrType, msg.bndAddr(), out);
/*    */     
/* 90 */     out.writeShort(msg.bndPort());
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socksx\v5\Socks5ServerEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */