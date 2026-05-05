/*     */ package io.netty.handler.codec.socksx.v5;
/*     */ 
/*     */ import io.netty.util.internal.ObjectUtil;
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
/*     */ public class Socks5CommandStatus
/*     */   implements Comparable<Socks5CommandStatus>
/*     */ {
/*  26 */   public static final Socks5CommandStatus SUCCESS = new Socks5CommandStatus(0, "SUCCESS");
/*  27 */   public static final Socks5CommandStatus FAILURE = new Socks5CommandStatus(1, "FAILURE");
/*  28 */   public static final Socks5CommandStatus FORBIDDEN = new Socks5CommandStatus(2, "FORBIDDEN");
/*  29 */   public static final Socks5CommandStatus NETWORK_UNREACHABLE = new Socks5CommandStatus(3, "NETWORK_UNREACHABLE");
/*  30 */   public static final Socks5CommandStatus HOST_UNREACHABLE = new Socks5CommandStatus(4, "HOST_UNREACHABLE");
/*  31 */   public static final Socks5CommandStatus CONNECTION_REFUSED = new Socks5CommandStatus(5, "CONNECTION_REFUSED");
/*  32 */   public static final Socks5CommandStatus TTL_EXPIRED = new Socks5CommandStatus(6, "TTL_EXPIRED");
/*  33 */   public static final Socks5CommandStatus COMMAND_UNSUPPORTED = new Socks5CommandStatus(7, "COMMAND_UNSUPPORTED");
/*  34 */   public static final Socks5CommandStatus ADDRESS_UNSUPPORTED = new Socks5CommandStatus(8, "ADDRESS_UNSUPPORTED");
/*     */   
/*     */   public static Socks5CommandStatus valueOf(byte b) {
/*  37 */     switch (b) {
/*     */       case 0:
/*  39 */         return SUCCESS;
/*     */       case 1:
/*  41 */         return FAILURE;
/*     */       case 2:
/*  43 */         return FORBIDDEN;
/*     */       case 3:
/*  45 */         return NETWORK_UNREACHABLE;
/*     */       case 4:
/*  47 */         return HOST_UNREACHABLE;
/*     */       case 5:
/*  49 */         return CONNECTION_REFUSED;
/*     */       case 6:
/*  51 */         return TTL_EXPIRED;
/*     */       case 7:
/*  53 */         return COMMAND_UNSUPPORTED;
/*     */       case 8:
/*  55 */         return ADDRESS_UNSUPPORTED;
/*     */     } 
/*     */     
/*  58 */     return new Socks5CommandStatus(b);
/*     */   }
/*     */   
/*     */   private final byte byteValue;
/*     */   private final String name;
/*     */   private String text;
/*     */   
/*     */   public Socks5CommandStatus(int byteValue) {
/*  66 */     this(byteValue, "UNKNOWN");
/*     */   }
/*     */   
/*     */   public Socks5CommandStatus(int byteValue, String name) {
/*  70 */     this.name = (String)ObjectUtil.checkNotNull(name, "name");
/*  71 */     this.byteValue = (byte)byteValue;
/*     */   }
/*     */   
/*     */   public byte byteValue() {
/*  75 */     return this.byteValue;
/*     */   }
/*     */   
/*     */   public boolean isSuccess() {
/*  79 */     return (this.byteValue == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  84 */     return this.byteValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  89 */     if (!(obj instanceof Socks5CommandStatus)) {
/*  90 */       return false;
/*     */     }
/*     */     
/*  93 */     return (this.byteValue == ((Socks5CommandStatus)obj).byteValue);
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(Socks5CommandStatus o) {
/*  98 */     return this.byteValue - o.byteValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 103 */     String text = this.text;
/* 104 */     if (text == null) {
/* 105 */       this.text = text = this.name + '(' + (this.byteValue & 0xFF) + ')';
/*     */     }
/* 107 */     return text;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socksx\v5\Socks5CommandStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */