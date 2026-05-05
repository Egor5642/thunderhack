/*     */ package thunder.hack.core.manager.client;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ThProxy
/*     */ {
/*     */   private String name;
/*     */   private String ip;
/*     */   private String l;
/*     */   private String p;
/*     */   private int port;
/*     */   private int ping;
/*     */   
/*     */   public ThProxy(String name, String ip, int port, String l, String p) {
/* 153 */     this.ip = ip;
/* 154 */     this.l = l;
/* 155 */     this.p = p;
/* 156 */     this.name = name;
/* 157 */     this.port = port;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 161 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 165 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getIp() {
/* 169 */     return this.ip;
/*     */   }
/*     */   
/*     */   public void setIp(String ip) {
/* 173 */     this.ip = ip;
/*     */   }
/*     */   
/*     */   public String getL() {
/* 177 */     return this.l;
/*     */   }
/*     */   
/*     */   public void setL(String l) {
/* 181 */     this.l = l;
/*     */   }
/*     */   
/*     */   public String getP() {
/* 185 */     return this.p;
/*     */   }
/*     */   
/*     */   public void setP(String p) {
/* 189 */     this.p = p;
/*     */   }
/*     */   
/*     */   public void setPort(int port) {
/* 193 */     this.port = port;
/*     */   }
/*     */   
/*     */   public int getPort() {
/* 197 */     return this.port;
/*     */   }
/*     */   
/*     */   public void setPing(int ping) {
/* 201 */     this.ping = ping;
/*     */   }
/*     */   
/*     */   public int getPing() {
/* 205 */     return this.ping;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\core\manager\client\ProxyManager$ThProxy.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */