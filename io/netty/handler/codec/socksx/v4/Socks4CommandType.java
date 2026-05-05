/*    */ package io.netty.handler.codec.socksx.v4;
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
/*    */ public class Socks4CommandType
/*    */   implements Comparable<Socks4CommandType>
/*    */ {
/* 25 */   public static final Socks4CommandType CONNECT = new Socks4CommandType(1, "CONNECT");
/* 26 */   public static final Socks4CommandType BIND = new Socks4CommandType(2, "BIND");
/*    */   
/*    */   public static Socks4CommandType valueOf(byte b) {
/* 29 */     switch (b) {
/*    */       case 1:
/* 31 */         return CONNECT;
/*    */       case 2:
/* 33 */         return BIND;
/*    */     } 
/*    */     
/* 36 */     return new Socks4CommandType(b);
/*    */   }
/*    */   
/*    */   private final byte byteValue;
/*    */   private final String name;
/*    */   private String text;
/*    */   
/*    */   public Socks4CommandType(int byteValue) {
/* 44 */     this(byteValue, "UNKNOWN");
/*    */   }
/*    */   
/*    */   public Socks4CommandType(int byteValue, String name) {
/* 48 */     this.name = (String)ObjectUtil.checkNotNull(name, "name");
/* 49 */     this.byteValue = (byte)byteValue;
/*    */   }
/*    */   
/*    */   public byte byteValue() {
/* 53 */     return this.byteValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 58 */     return this.byteValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 63 */     if (!(obj instanceof Socks4CommandType)) {
/* 64 */       return false;
/*    */     }
/*    */     
/* 67 */     return (this.byteValue == ((Socks4CommandType)obj).byteValue);
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(Socks4CommandType o) {
/* 72 */     return this.byteValue - o.byteValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 77 */     String text = this.text;
/* 78 */     if (text == null) {
/* 79 */       this.text = text = this.name + '(' + (this.byteValue & 0xFF) + ')';
/*    */     }
/* 81 */     return text;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socksx\v4\Socks4CommandType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */