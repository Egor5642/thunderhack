/*    */ package io.netty.handler.proxy;
/*    */ 
/*    */ import io.netty.util.internal.ObjectUtil;
/*    */ import io.netty.util.internal.StringUtil;
/*    */ import java.net.SocketAddress;
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
/*    */ public final class ProxyConnectionEvent
/*    */ {
/*    */   private final String protocol;
/*    */   private final String authScheme;
/*    */   private final SocketAddress proxyAddress;
/*    */   private final SocketAddress destinationAddress;
/*    */   private String strVal;
/*    */   
/*    */   public ProxyConnectionEvent(String protocol, String authScheme, SocketAddress proxyAddress, SocketAddress destinationAddress) {
/* 37 */     this.protocol = (String)ObjectUtil.checkNotNull(protocol, "protocol");
/* 38 */     this.authScheme = (String)ObjectUtil.checkNotNull(authScheme, "authScheme");
/* 39 */     this.proxyAddress = (SocketAddress)ObjectUtil.checkNotNull(proxyAddress, "proxyAddress");
/* 40 */     this.destinationAddress = (SocketAddress)ObjectUtil.checkNotNull(destinationAddress, "destinationAddress");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String protocol() {
/* 47 */     return this.protocol;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String authScheme() {
/* 54 */     return this.authScheme;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public <T extends SocketAddress> T proxyAddress() {
/* 62 */     return (T)this.proxyAddress;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public <T extends SocketAddress> T destinationAddress() {
/* 70 */     return (T)this.destinationAddress;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 75 */     if (this.strVal != null) {
/* 76 */       return this.strVal;
/*    */     }
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
/* 89 */     StringBuilder buf = (new StringBuilder(128)).append(StringUtil.simpleClassName(this)).append('(').append(this.protocol).append(", ").append(this.authScheme).append(", ").append(this.proxyAddress).append(" => ").append(this.destinationAddress).append(')');
/*    */     
/* 91 */     return this.strVal = buf.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\proxy\ProxyConnectionEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */