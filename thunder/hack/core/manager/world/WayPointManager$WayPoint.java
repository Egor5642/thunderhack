/*     */ package thunder.hack.core.manager.world;
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
/*     */ public class WayPoint
/*     */ {
/*     */   private int x;
/*     */   private int y;
/*     */   private int z;
/*     */   private String name;
/*     */   private String server;
/*     */   private String dimension;
/*     */   
/*     */   public WayPoint(int x, int y, int z, String name, String server, String dimension) {
/*  79 */     this.x = x;
/*  80 */     this.y = y;
/*  81 */     this.z = z;
/*  82 */     this.name = name;
/*  83 */     this.server = server;
/*  84 */     this.dimension = dimension;
/*     */   }
/*     */   
/*     */   public int getX() {
/*  88 */     return this.x;
/*     */   }
/*     */   
/*     */   public void setX(int x) {
/*  92 */     this.x = x;
/*     */   }
/*     */   
/*     */   public int getY() {
/*  96 */     return this.y;
/*     */   }
/*     */   
/*     */   public void setY(int y) {
/* 100 */     this.y = y;
/*     */   }
/*     */   
/*     */   public int getZ() {
/* 104 */     return this.z;
/*     */   }
/*     */   
/*     */   public void setZ(int z) {
/* 108 */     this.z = z;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 112 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 116 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getServer() {
/* 120 */     return this.server;
/*     */   }
/*     */   
/*     */   public void setServer(String server) {
/* 124 */     this.server = server;
/*     */   }
/*     */   
/*     */   public String getDimension() {
/* 128 */     return this.dimension;
/*     */   }
/*     */   
/*     */   public void setDimension(String dimension) {
/* 132 */     this.dimension = dimension;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\core\manager\world\WayPointManager$WayPoint.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */