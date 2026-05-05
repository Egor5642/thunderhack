/*    */ package thunder.hack.utility.math;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class FrameRateCounter {
/*  7 */   public static final FrameRateCounter INSTANCE = new FrameRateCounter();
/*  8 */   final List<Long> records = new ArrayList<>();
/*  9 */   int fps = 5;
/*    */   
/*    */   public void recordFrame() {
/* 12 */     long c = System.currentTimeMillis();
/* 13 */     this.records.add(Long.valueOf(c));
/* 14 */     this.records.removeIf(aLong -> (aLong.longValue() + 1000L < System.currentTimeMillis()));
/* 15 */     this.fps = Math.max(this.records.size(), 4);
/*    */   }
/*    */   
/*    */   public int getFps() {
/* 19 */     return this.fps;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\math\FrameRateCounter.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */