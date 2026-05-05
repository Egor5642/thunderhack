/*    */ package io.netty.handler.codec.socksx.v5;
/*    */ 
/*    */ import io.netty.util.internal.ObjectUtil;
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
/*    */ public class Socks5AuthMethod
/*    */   implements Comparable<Socks5AuthMethod>
/*    */ {
/* 26 */   public static final Socks5AuthMethod NO_AUTH = new Socks5AuthMethod(0, "NO_AUTH");
/* 27 */   public static final Socks5AuthMethod GSSAPI = new Socks5AuthMethod(1, "GSSAPI");
/* 28 */   public static final Socks5AuthMethod PASSWORD = new Socks5AuthMethod(2, "PASSWORD");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   public static final Socks5AuthMethod UNACCEPTED = new Socks5AuthMethod(255, "UNACCEPTED");
/*    */   
/*    */   public static Socks5AuthMethod valueOf(byte b) {
/* 36 */     switch (b) {
/*    */       case 0:
/* 38 */         return NO_AUTH;
/*    */       case 1:
/* 40 */         return GSSAPI;
/*    */       case 2:
/* 42 */         return PASSWORD;
/*    */       case -1:
/* 44 */         return UNACCEPTED;
/*    */     } 
/*    */     
/* 47 */     return new Socks5AuthMethod(b);
/*    */   }
/*    */   
/*    */   private final byte byteValue;
/*    */   private final String name;
/*    */   private String text;
/*    */   
/*    */   public Socks5AuthMethod(int byteValue) {
/* 55 */     this(byteValue, "UNKNOWN");
/*    */   }
/*    */   
/*    */   public Socks5AuthMethod(int byteValue, String name) {
/* 59 */     this.name = (String)ObjectUtil.checkNotNull(name, "name");
/* 60 */     this.byteValue = (byte)byteValue;
/*    */   }
/*    */   
/*    */   public byte byteValue() {
/* 64 */     return this.byteValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 69 */     return this.byteValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 74 */     if (!(obj instanceof Socks5AuthMethod)) {
/* 75 */       return false;
/*    */     }
/*    */     
/* 78 */     return (this.byteValue == ((Socks5AuthMethod)obj).byteValue);
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(Socks5AuthMethod o) {
/* 83 */     return this.byteValue - o.byteValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 88 */     String text = this.text;
/* 89 */     if (text == null) {
/* 90 */       this.text = text = this.name + '(' + (this.byteValue & 0xFF) + ')';
/*    */     }
/* 92 */     return text;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socksx\v5\Socks5AuthMethod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */