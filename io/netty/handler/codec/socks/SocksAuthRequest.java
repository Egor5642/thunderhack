/*    */ package io.netty.handler.codec.socks;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.util.CharsetUtil;
/*    */ import io.netty.util.internal.ObjectUtil;
/*    */ import java.nio.charset.CharsetEncoder;
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
/*    */ public final class SocksAuthRequest
/*    */   extends SocksRequest
/*    */ {
/* 31 */   private static final SocksSubnegotiationVersion SUBNEGOTIATION_VERSION = SocksSubnegotiationVersion.AUTH_PASSWORD;
/*    */   private final String username;
/*    */   private final String password;
/*    */   
/*    */   public SocksAuthRequest(String username, String password) {
/* 36 */     super(SocksRequestType.AUTH);
/* 37 */     ObjectUtil.checkNotNull(username, "username");
/* 38 */     ObjectUtil.checkNotNull(password, "password");
/* 39 */     CharsetEncoder asciiEncoder = CharsetUtil.encoder(CharsetUtil.US_ASCII);
/* 40 */     if (!asciiEncoder.canEncode(username) || !asciiEncoder.canEncode(password)) {
/* 41 */       throw new IllegalArgumentException("username: " + username + " or password: **** values should be in pure ascii");
/*    */     }
/*    */     
/* 44 */     if (username.length() > 255) {
/* 45 */       throw new IllegalArgumentException("username: " + username + " exceeds 255 char limit");
/*    */     }
/* 47 */     if (password.length() > 255) {
/* 48 */       throw new IllegalArgumentException("password: **** exceeds 255 char limit");
/*    */     }
/* 50 */     this.username = username;
/* 51 */     this.password = password;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String username() {
/* 60 */     return this.username;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String password() {
/* 69 */     return this.password;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encodeAsByteBuf(ByteBuf byteBuf) {
/* 74 */     byteBuf.writeByte(SUBNEGOTIATION_VERSION.byteValue());
/* 75 */     byteBuf.writeByte(this.username.length());
/* 76 */     byteBuf.writeCharSequence(this.username, CharsetUtil.US_ASCII);
/* 77 */     byteBuf.writeByte(this.password.length());
/* 78 */     byteBuf.writeCharSequence(this.password, CharsetUtil.US_ASCII);
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socks\SocksAuthRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */