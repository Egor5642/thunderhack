/*     */ package thunder.hack.core.manager.world;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import thunder.hack.core.manager.IManager;
/*     */ 
/*     */ public class WayPointManager implements IManager {
/*  10 */   private static CopyOnWriteArrayList<WayPoint> wayPoints = new CopyOnWriteArrayList<>();
/*     */   
/*     */   public void addWayPoint(WayPoint wp) {
/*  13 */     if (!wayPoints.contains(wp))
/*  14 */       wayPoints.add(wp); 
/*     */   }
/*     */   
/*     */   public void onLoad() {
/*  18 */     wayPoints = new CopyOnWriteArrayList<>();
/*     */     try {
/*  20 */       File file = new File("ThunderHackRecode/misc/waypoints.txt");
/*     */       
/*  22 */       if (file.exists()) {
/*  23 */         BufferedReader reader = new BufferedReader(new FileReader(file)); 
/*  24 */         try { while (reader.ready()) {
/*  25 */             String[] line = reader.readLine().split(":");
/*  26 */             String x = line[0];
/*  27 */             String y = line[1];
/*  28 */             String z = line[2];
/*  29 */             String name = line[3];
/*  30 */             String server = line[4];
/*  31 */             String dimension = (line.length == 6) ? line[5] : "overworld";
/*     */             
/*  33 */             addWayPoint(new WayPoint(Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(z), name, server, dimension));
/*     */           } 
/*  35 */           reader.close(); } catch (Throwable throwable) { try { reader.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; } 
/*     */       } 
/*  37 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveWayPoints() {
/*  43 */     File file = new File("ThunderHackRecode/misc/waypoints.txt");
/*     */     try {
/*  45 */       (new File("ThunderHackRecode")).mkdirs();
/*  46 */       file.createNewFile();
/*  47 */     } catch (Exception exception) {}
/*     */     
/*  49 */     try { BufferedWriter writer = new BufferedWriter(new FileWriter(file)); 
/*  50 */       try { for (WayPoint wayPoint : wayPoints) {
/*  51 */           writer.write("" + wayPoint.x + ":" + wayPoint.x + ":" + wayPoint.y + ":" + wayPoint.z + ":" + wayPoint.name + ":" + wayPoint.server + "\n");
/*     */         }
/*  53 */         writer.close(); } catch (Throwable throwable) { try { writer.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeWayPoint(WayPoint macro) {
/*  58 */     wayPoints.remove(macro);
/*     */   }
/*     */   
/*     */   public CopyOnWriteArrayList<WayPoint> getWayPoints() {
/*  62 */     return wayPoints;
/*     */   }
/*     */   
/*     */   public WayPoint getWayPointByName(String name) {
/*  66 */     for (WayPoint wayPoint : getWayPoints()) {
/*  67 */       if (wayPoint.name.equalsIgnoreCase(name))
/*  68 */         return wayPoint; 
/*  69 */     }  return null;
/*     */   }
/*     */   public static class WayPoint { private int x;
/*     */     private int y;
/*     */     private int z;
/*     */     private String name;
/*     */     private String server;
/*     */     private String dimension;
/*     */     
/*     */     public WayPoint(int x, int y, int z, String name, String server, String dimension) {
/*  79 */       this.x = x;
/*  80 */       this.y = y;
/*  81 */       this.z = z;
/*  82 */       this.name = name;
/*  83 */       this.server = server;
/*  84 */       this.dimension = dimension;
/*     */     }
/*     */     
/*     */     public int getX() {
/*  88 */       return this.x;
/*     */     }
/*     */     
/*     */     public void setX(int x) {
/*  92 */       this.x = x;
/*     */     }
/*     */     
/*     */     public int getY() {
/*  96 */       return this.y;
/*     */     }
/*     */     
/*     */     public void setY(int y) {
/* 100 */       this.y = y;
/*     */     }
/*     */     
/*     */     public int getZ() {
/* 104 */       return this.z;
/*     */     }
/*     */     
/*     */     public void setZ(int z) {
/* 108 */       this.z = z;
/*     */     }
/*     */     
/*     */     public String getName() {
/* 112 */       return this.name;
/*     */     }
/*     */     
/*     */     public void setName(String name) {
/* 116 */       this.name = name;
/*     */     }
/*     */     
/*     */     public String getServer() {
/* 120 */       return this.server;
/*     */     }
/*     */     
/*     */     public void setServer(String server) {
/* 124 */       this.server = server;
/*     */     }
/*     */     
/*     */     public String getDimension() {
/* 128 */       return this.dimension;
/*     */     }
/*     */     
/*     */     public void setDimension(String dimension) {
/* 132 */       this.dimension = dimension;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\core\manager\world\WayPointManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */