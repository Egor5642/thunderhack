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
/*    */ public class Socks4CommandStatus
/*    */   implements Comparable<Socks4CommandStatus>
/*    */ {
/* 25 */   public static final Socks4CommandStatus SUCCESS = new Socks4CommandStatus(90, "SUCCESS");
/* 26 */   public static final Socks4CommandStatus REJECTED_OR_FAILED = new Socks4CommandStatus(91, "REJECTED_OR_FAILED");
/* 27 */   public static final Socks4CommandStatus IDENTD_UNREACHABLE = new Socks4CommandStatus(92, "IDENTD_UNREACHABLE");
/* 28 */   public static final Socks4CommandStatus IDENTD_AUTH_FAILURE = new Socks4CommandStatus(93, "IDENTD_AUTH_FAILURE");
/*    */   
/*    */   public static Socks4CommandStatus valueOf(byte b) {
/* 31 */     switch (b) {
/*    */       case 90:
/* 33 */         return SUCCESS;
/*    */       case 91:
/* 35 */         return REJECTED_OR_FAILED;
/*    */       case 92:
/* 37 */         return IDENTD_UNREACHABLE;
/*    */       case 93:
/* 39 */         return IDENTD_AUTH_FAILURE;
/*    */     } 
/*    */     
/* 42 */     return new Socks4CommandStatus(b);
/*    */   }
/*    */   
/*    */   private final byte byteValue;
/*    */   private final String name;
/*    */   private String text;
/*    */   
/*    */   public Socks4CommandStatus(int byteValue) {
/* 50 */     this(byteValue, "UNKNOWN");
/*    */   }
/*    */   
/*    */   public Socks4CommandStatus(int byteValue, String name) {
/* 54 */     this.name = (String)ObjectUtil.checkNotNull(name, "name");
/* 55 */     this.byteValue = (byte)byteValue;
/*    */   }
/*    */   
/*    */   public byte byteValue() {
/* 59 */     return this.byteValue;
/*    */   }
/*    */   
/*    */   public boolean isSuccess() {
/* 63 */     return (this.byteValue == 90);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 68 */     return this.byteValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 73 */     if (!(obj instanceof Socks4CommandStatus)) {
/* 74 */       return false;
/*    */     }
/*    */     
/* 77 */     return (this.byteValue == ((Socks4CommandStatus)obj).byteValue);
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(Socks4CommandStatus o) {
/* 82 */     return this.byteValue - o.byteValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 87 */     String text = this.text;
/* 88 */     if (text == null) {
/* 89 */       this.text = text = this.name + '(' + (this.byteValue & 0xFF) + ')';
/*    */     }
/* 91 */     return text;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socksx\v4\Socks4CommandStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */