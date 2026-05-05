/*     */ package thunder.hack.core.manager.client;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import thunder.hack.core.manager.IManager;
/*     */ 
/*     */ public class MacroManager implements IManager {
/*   9 */   private static CopyOnWriteArrayList<Macro> macros = new CopyOnWriteArrayList<>();
/*     */   
/*     */   public static void addMacro(Macro macro) {
/*  12 */     if (!macros.contains(macro)) {
/*  13 */       macros.add(macro);
/*     */     }
/*     */   }
/*     */   
/*     */   public void onLoad() {
/*  18 */     macros = new CopyOnWriteArrayList<>();
/*     */     try {
/*  20 */       File file = new File("ThunderHackRecode/misc/macro.txt");
/*     */       
/*  22 */       if (file.exists()) {
/*  23 */         BufferedReader reader = new BufferedReader(new FileReader(file)); 
/*  24 */         try { while (reader.ready()) {
/*  25 */             String[] nameKey = reader.readLine().split(":");
/*  26 */             String name = nameKey[0];
/*  27 */             String key = nameKey[1];
/*  28 */             String command = nameKey[2];
/*  29 */             addMacro(new Macro(name, command, Integer.parseInt(key)));
/*     */           } 
/*     */           
/*  32 */           reader.close(); } catch (Throwable throwable) { try { reader.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; } 
/*     */       } 
/*  34 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveMacro() {
/*  40 */     File file = new File("ThunderHackRecode/misc/macro.txt");
/*     */     try {
/*  42 */       if ((new File("ThunderHackRecode")).mkdirs()) {
/*  43 */         file.createNewFile();
/*     */       }
/*  45 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/*  48 */     try { BufferedWriter writer = new BufferedWriter(new FileWriter(file)); 
/*  49 */       try { for (Macro macro : macros) {
/*  50 */           writer.write(macro.name + ":" + macro.name + ":" + macro.bind + "\n");
/*     */         }
/*  52 */         writer.close(); } catch (Throwable throwable) { try { writer.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeMacro(Macro macro) {
/*  57 */     macros.remove(macro);
/*     */   }
/*     */   
/*     */   public CopyOnWriteArrayList<Macro> getMacros() {
/*  61 */     return macros;
/*     */   }
/*     */   
/*     */   public Macro getMacroByName(String n) {
/*  65 */     for (Macro m : getMacros()) {
/*  66 */       if (m.name.equalsIgnoreCase(n))
/*  67 */         return m; 
/*  68 */     }  return null;
/*     */   }
/*     */   
/*     */   public static class Macro { private String name;
/*     */     private String text;
/*     */     private int bind;
/*     */     
/*     */     public Macro(String name, String text, int bind) {
/*  76 */       this.name = name;
/*  77 */       this.text = text;
/*  78 */       this.bind = bind;
/*     */     }
/*     */     
/*     */     public String getName() {
/*  82 */       return this.name;
/*     */     }
/*     */     
/*     */     public void setName(String name) {
/*  86 */       this.name = name;
/*     */     }
/*     */     
/*     */     public String getText() {
/*  90 */       return this.text;
/*     */     }
/*     */     
/*     */     public void setText(String text) {
/*  94 */       this.text = text;
/*     */     }
/*     */     
/*     */     public int getBind() {
/*  98 */       return this.bind;
/*     */     }
/*     */     
/*     */     public void setBind(int bind) {
/* 102 */       this.bind = bind;
/*     */     }
/*     */     
/*     */     public void runMacro() {
/* 106 */       if (IManager.mc.field_1724 == null)
/* 107 */         return;  if (this.text.contains("/")) { IManager.mc.field_1724.field_3944.method_45730(this.text.replace("/", "")); }
/* 108 */       else { IManager.mc.field_1724.field_3944.method_45729(this.text); }
/*     */     
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\core\manager\client\MacroManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */