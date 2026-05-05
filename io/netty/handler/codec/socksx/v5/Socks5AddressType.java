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
/*    */ public class Socks5AddressType
/*    */   implements Comparable<Socks5AddressType>
/*    */ {
/* 26 */   public static final Socks5AddressType IPv4 = new Socks5AddressType(1, "IPv4");
/* 27 */   public static final Socks5AddressType DOMAIN = new Socks5AddressType(3, "DOMAIN");
/* 28 */   public static final Socks5AddressType IPv6 = new Socks5AddressType(4, "IPv6");
/*    */   
/*    */   public static Socks5AddressType valueOf(byte b) {
/* 31 */     switch (b) {
/*    */       case 1:
/* 33 */         return IPv4;
/*    */       case 3:
/* 35 */         return DOMAIN;
/*    */       case 4:
/* 37 */         return IPv6;
/*    */     } 
/*    */     
/* 40 */     return new Socks5AddressType(b);
/*    */   }
/*    */   
/*    */   private final byte byteValue;
/*    */   private final String name;
/*    */   private String text;
/*    */   
/*    */   public Socks5AddressType(int byteValue) {
/* 48 */     this(byteValue, "UNKNOWN");
/*    */   }
/*    */   
/*    */   public Socks5AddressType(int byteValue, String name) {
/* 52 */     this.name = (String)ObjectUtil.checkNotNull(name, "name");
/* 53 */     this.byteValue = (byte)byteValue;
/*    */   }
/*    */   
/*    */   public byte byteValue() {
/* 57 */     return this.byteValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 62 */     return this.byteValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 67 */     if (!(obj instanceof Socks5AddressType)) {
/* 68 */       return false;
/*    */     }
/*    */     
/* 71 */     return (this.byteValue == ((Socks5AddressType)obj).byteValue);
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(Socks5AddressType o) {
/* 76 */     return this.byteValue - o.byteValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 81 */     String text = this.text;
/* 82 */     if (text == null) {
/* 83 */       this.text = text = this.name + '(' + (this.byteValue & 0xFF) + ')';
/*    */     }
/* 85 */     return text;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socksx\v5\Socks5AddressType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */