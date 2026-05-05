/*    */ package thunder.hack.features.modules.client;
/*    */ 
/*    */ import net.minecraft.class_124;
/*    */ import net.minecraft.class_243;
/*    */ import net.minecraft.class_332;
/*    */ import net.minecraft.class_3532;
/*    */ import org.joml.Vector4d;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.core.manager.world.WayPointManager;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.gui.font.FontRenderers;
/*    */ import thunder.hack.utility.render.Render3DEngine;
/*    */ import thunder.hack.utility.render.TextureStorage;
/*    */ 
/*    */ public final class WayPoints
/*    */   extends Module {
/*    */   public WayPoints() {
/* 18 */     super("WayPoints", Module.Category.CLIENT);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 23 */     sendMessage(Managers.COMMAND.getPrefix() + "waypoint add x y z name");
/*    */   }
/*    */   
/*    */   public void onRender2D(class_332 context) {
/* 27 */     if (!Managers.WAYPOINT.getWayPoints().isEmpty() && !fullNullCheck())
/* 28 */       for (WayPointManager.WayPoint wp : Managers.WAYPOINT.getWayPoints()) {
/* 29 */         if (wp.getName() == null || (
/* 30 */           mc.method_1542() && wp.getServer().equals("SinglePlayer")) || (mc
/* 31 */           .method_1562().method_45734() != null && !(mc.method_1562().method_45734()).field_3761.contains(wp.getServer())) || 
/* 32 */           !mc.field_1687.method_27983().method_29177().method_12832().equals(wp.getDimension()))
/* 33 */           continue;  double difX = wp.getX() - (mc.field_1724.method_19538()).field_1352;
/* 34 */         double difZ = wp.getZ() - (mc.field_1724.method_19538()).field_1350;
/* 35 */         float yaw = (float)class_3532.method_15338(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0D);
/* 36 */         double plYaw = class_3532.method_15393(mc.field_1724.method_36454());
/* 37 */         if (Math.abs(yaw - plYaw) > 90.0D)
/*    */           continue; 
/* 39 */         class_243 vector = new class_243(wp.getX(), wp.getY(), wp.getZ());
/* 40 */         Vector4d position = null;
/* 41 */         vector = Render3DEngine.worldSpaceToScreenSpace(new class_243(vector.field_1352, vector.field_1351, vector.field_1350));
/* 42 */         position = new Vector4d(vector.field_1352, vector.field_1351, vector.field_1350, 0.0D);
/* 43 */         position.x = Math.min(vector.field_1352, position.x);
/* 44 */         position.y = Math.min(vector.field_1351, position.y);
/* 45 */         position.z = Math.max(vector.field_1352, position.z);
/*    */         
/* 47 */         double posX = position.x;
/* 48 */         double posY = position.y;
/* 49 */         double endPosX = position.z;
/*    */         
/* 51 */         float diff = (float)(endPosX - posX) / 2.0F;
/* 52 */         float tagX = (float)((posX + diff - (FontRenderers.sf_bold_mini.getStringWidth(wp.getName()) / 2.0F)) * 1.0D);
/*    */         
/* 54 */         String coords = "" + wp.getX() + " " + wp.getX();
/* 55 */         float tagX2 = (float)((posX + diff - (FontRenderers.sf_bold_mini.getStringWidth(coords) / 2.0F)) * 1.0D);
/*    */         
/* 57 */         String distance = String.format("%.0f", new Object[] { Double.valueOf(Math.sqrt(mc.field_1724.method_5649(wp.getX(), wp.getY(), wp.getZ()))) }) + "m";
/* 58 */         float tagX3 = (float)((posX + diff - (FontRenderers.sf_bold_mini.getStringWidth(distance) / 2.0F)) * 1.0D);
/*    */         
/* 60 */         context.method_51448().method_22903();
/* 61 */         context.method_51448().method_22904(posX - 10.0D, posY - 35.0D, 0.0D);
/* 62 */         context.method_25293(TextureStorage.waypoint, 0, 0, 20, 20, 0.0F, 0.0F, 20, 20, 20, 20);
/* 63 */         context.method_51448().method_22909();
/*    */         
/* 65 */         FontRenderers.sf_bold_mini.drawString(context.method_51448(), wp.getName(), tagX, ((float)posY - 10.0F), -1);
/* 66 */         FontRenderers.sf_bold_mini.drawString(context.method_51448(), String.valueOf(class_124.field_1080) + String.valueOf(class_124.field_1080), tagX2, ((float)posY - 2.0F), -1);
/* 67 */         FontRenderers.sf_bold_mini.drawString(context.method_51448(), String.valueOf(class_124.field_1080) + String.valueOf(class_124.field_1080), tagX3, ((float)posY + 6.0F), -1);
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\client\WayPoints.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */