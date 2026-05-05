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
/*    */ public class Socks5PasswordAuthStatus
/*    */   implements Comparable<Socks5PasswordAuthStatus>
/*    */ {
/* 26 */   public static final Socks5PasswordAuthStatus SUCCESS = new Socks5PasswordAuthStatus(0, "SUCCESS");
/* 27 */   public static final Socks5PasswordAuthStatus FAILURE = new Socks5PasswordAuthStatus(255, "FAILURE");
/*    */   
/*    */   public static Socks5PasswordAuthStatus valueOf(byte b) {
/* 30 */     switch (b) {
/*    */       case 0:
/* 32 */         return SUCCESS;
/*    */       case -1:
/* 34 */         return FAILURE;
/*    */     } 
/*    */     
/* 37 */     return new Socks5PasswordAuthStatus(b);
/*    */   }
/*    */   
/*    */   private final byte byteValue;
/*    */   private final String name;
/*    */   private String text;
/*    */   
/*    */   public Socks5PasswordAuthStatus(int byteValue) {
/* 45 */     this(byteValue, "UNKNOWN");
/*    */   }
/*    */   
/*    */   public Socks5PasswordAuthStatus(int byteValue, String name) {
/* 49 */     this.name = (String)ObjectUtil.checkNotNull(name, "name");
/* 50 */     this.byteValue = (byte)byteValue;
/*    */   }
/*    */   
/*    */   public byte byteValue() {
/* 54 */     return this.byteValue;
/*    */   }
/*    */   
/*    */   public boolean isSuccess() {
/* 58 */     return (this.byteValue == 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 63 */     return this.byteValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 68 */     if (!(obj instanceof Socks5PasswordAuthStatus)) {
/* 69 */       return false;
/*    */     }
/*    */     
/* 72 */     return (this.byteValue == ((Socks5PasswordAuthStatus)obj).byteValue);
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(Socks5PasswordAuthStatus o) {
/* 77 */     return this.byteValue - o.byteValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 82 */     String text = this.text;
/* 83 */     if (text == null) {
/* 84 */       this.text = text = this.name + '(' + (this.byteValue & 0xFF) + ')';
/*    */     }
/* 86 */     return text;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socksx\v5\Socks5PasswordAuthStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */