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
/*    */ public class Socks5CommandType
/*    */   implements Comparable<Socks5CommandType>
/*    */ {
/* 26 */   public static final Socks5CommandType CONNECT = new Socks5CommandType(1, "CONNECT");
/* 27 */   public static final Socks5CommandType BIND = new Socks5CommandType(2, "BIND");
/* 28 */   public static final Socks5CommandType UDP_ASSOCIATE = new Socks5CommandType(3, "UDP_ASSOCIATE");
/*    */   
/*    */   public static Socks5CommandType valueOf(byte b) {
/* 31 */     switch (b) {
/*    */       case 1:
/* 33 */         return CONNECT;
/*    */       case 2:
/* 35 */         return BIND;
/*    */       case 3:
/* 37 */         return UDP_ASSOCIATE;
/*    */     } 
/*    */     
/* 40 */     return new Socks5CommandType(b);
/*    */   }
/*    */   
/*    */   private final byte byteValue;
/*    */   private final String name;
/*    */   private String text;
/*    */   
/*    */   public Socks5CommandType(int byteValue) {
/* 48 */     this(byteValue, "UNKNOWN");
/*    */   }
/*    */   
/*    */   public Socks5CommandType(int byteValue, String name) {
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
/* 67 */     if (!(obj instanceof Socks5CommandType)) {
/* 68 */       return false;
/*    */     }
/*    */     
/* 71 */     return (this.byteValue == ((Socks5CommandType)obj).byteValue);
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(Socks5CommandType o) {
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


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socksx\v5\Socks5CommandType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */