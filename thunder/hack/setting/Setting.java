/*     */ package thunder.hack.setting;
/*     */ import java.util.function.Predicate;
/*     */ import thunder.hack.ThunderHack;
/*     */ import thunder.hack.events.impl.EventSetting;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.setting.impl.BooleanSettingGroup;
/*     */ import thunder.hack.setting.impl.EnumConverter;
/*     */ import thunder.hack.setting.impl.SettingGroup;
/*     */ 
/*     */ public class Setting<T> {
/*     */   private final String name;
/*     */   private final T defaultValue;
/*     */   private T value;
/*     */   private T plannedValue;
/*     */   private T min;
/*     */   private T max;
/*  17 */   public Setting<?> group = null;
/*     */   
/*     */   private boolean hasRestriction;
/*     */   private Predicate<T> visibility;
/*     */   private Module module;
/*     */   
/*     */   public Setting(String name, T defaultValue) {
/*  24 */     this.name = name;
/*  25 */     this.defaultValue = defaultValue;
/*  26 */     this.value = defaultValue;
/*  27 */     this.plannedValue = defaultValue;
/*     */   }
/*     */   
/*     */   public Setting(String name, T defaultValue, T min, T max) {
/*  31 */     this.name = name;
/*  32 */     this.defaultValue = defaultValue;
/*  33 */     this.value = defaultValue;
/*  34 */     this.min = min;
/*  35 */     this.max = max;
/*  36 */     this.plannedValue = defaultValue;
/*  37 */     this.hasRestriction = true;
/*     */   }
/*     */   
/*     */   public Setting(String name, T defaultValue, T min, T max, Predicate<T> visibility) {
/*  41 */     this.name = name;
/*  42 */     this.defaultValue = defaultValue;
/*  43 */     this.value = defaultValue;
/*  44 */     this.min = min;
/*  45 */     this.max = max;
/*  46 */     this.plannedValue = defaultValue;
/*  47 */     this.visibility = visibility;
/*  48 */     this.hasRestriction = true;
/*     */   }
/*     */   
/*     */   public Setting(String name, T defaultValue, Predicate<T> visibility) {
/*  52 */     this.name = name;
/*  53 */     this.defaultValue = defaultValue;
/*  54 */     this.value = defaultValue;
/*  55 */     this.visibility = visibility;
/*  56 */     this.plannedValue = defaultValue;
/*     */   }
/*     */   
/*     */   public static Enum get(Enum clazz) {
/*  60 */     int index = EnumConverter.currentEnum(clazz);
/*  61 */     for (int i = 0; i < ((Enum[])clazz.getClass().getEnumConstants()).length; ) {
/*  62 */       Enum e = ((Enum[])clazz.getClass().getEnumConstants())[i];
/*  63 */       if (i != index + 1) { i++; continue; }
/*  64 */        return e;
/*     */     } 
/*  66 */     return ((Enum[])clazz.getClass().getEnumConstants())[0];
/*     */   }
/*     */   
/*     */   public String getName() {
/*  70 */     return this.name;
/*     */   }
/*     */   
/*     */   public T getValue() {
/*  74 */     return this.value;
/*     */   }
/*     */   
/*     */   public void setValue(T value) {
/*  78 */     setValueSilent(value);
/*  79 */     ThunderHack.EVENT_BUS.post(new EventSetting(this));
/*     */   }
/*     */   
/*     */   public void setValueSilent(T value) {
/*  83 */     setPlannedValue(value);
/*  84 */     if (this.hasRestriction) {
/*  85 */       if (((Number)this.min).floatValue() > ((Number)value).floatValue()) {
/*  86 */         setPlannedValue(this.min);
/*     */       }
/*  88 */       if (((Number)this.max).floatValue() < ((Number)value).floatValue()) {
/*  89 */         setPlannedValue(this.max);
/*     */       }
/*     */     } 
/*  92 */     this.value = this.plannedValue;
/*     */   }
/*     */   
/*     */   public float getPow2Value() {
/*  96 */     if (this.value instanceof Float) {
/*  97 */       return ((Float)this.value).floatValue() * ((Float)this.value).floatValue();
/*     */     }
/*  99 */     if (this.value instanceof Integer) {
/* 100 */       return (((Integer)this.value).intValue() * ((Integer)this.value).intValue());
/*     */     }
/* 102 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public void setPlannedValue(T value) {
/* 106 */     this.plannedValue = value;
/*     */   }
/*     */   
/*     */   public T getMin() {
/* 110 */     return this.min;
/*     */   }
/*     */   
/*     */   public void setMin(T min) {
/* 114 */     this.min = min;
/*     */   }
/*     */   
/*     */   public T getMax() {
/* 118 */     return this.max;
/*     */   }
/*     */   
/*     */   public void setMax(T max) {
/* 122 */     this.max = max;
/*     */   }
/*     */   
/*     */   public Module getModule() {
/* 126 */     return this.module;
/*     */   }
/*     */   
/*     */   public void setModule(Module module) {
/* 130 */     this.module = module;
/*     */   }
/*     */   
/*     */   public String currentEnumName() {
/* 134 */     return EnumConverter.getProperName((Enum)this.value);
/*     */   }
/*     */   
/*     */   public String[] getModes() {
/* 138 */     return EnumConverter.getNames((Enum)this.value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnum(Enum mod) {
/* 143 */     this.plannedValue = (T)mod;
/*     */   }
/*     */ 
/*     */   
/*     */   public void increaseEnum() {
/* 148 */     this.plannedValue = (T)EnumConverter.increaseEnum((Enum)this.value);
/* 149 */     this.value = this.plannedValue;
/* 150 */     ThunderHack.EVENT_BUS.post(new EventSetting(this));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnumByNumber(int id) {
/* 155 */     this.plannedValue = (T)EnumConverter.setEnumInt((Enum)this.value, id);
/* 156 */     this.value = this.plannedValue;
/* 157 */     ThunderHack.EVENT_BUS.post(new EventSetting(this));
/*     */   }
/*     */   
/*     */   public boolean isNumberSetting() {
/* 161 */     return (this.value instanceof Double || this.value instanceof Integer || this.value instanceof Short || this.value instanceof Long || this.value instanceof Float);
/*     */   }
/*     */   
/*     */   public boolean isInteger() {
/* 165 */     return this.value instanceof Integer;
/*     */   }
/*     */   
/*     */   public boolean isFloat() {
/* 169 */     return this.value instanceof Float;
/*     */   }
/*     */   
/*     */   public boolean isEnumSetting() {
/* 173 */     return this.value.getClass().isEnum();
/*     */   }
/*     */   
/*     */   public boolean isBindSetting() {
/* 177 */     return this.value instanceof thunder.hack.setting.impl.Bind;
/*     */   }
/*     */   
/*     */   public boolean isStringSetting() {
/* 181 */     return this.value instanceof String;
/*     */   }
/*     */   
/*     */   public boolean isItemSelectSetting() {
/* 185 */     return this.value instanceof thunder.hack.setting.impl.ItemSelectSetting;
/*     */   }
/*     */   
/*     */   public boolean isPositionSetting() {
/* 189 */     return this.value instanceof thunder.hack.setting.impl.PositionSetting;
/*     */   }
/*     */   
/*     */   public T getDefaultValue() {
/* 193 */     return this.defaultValue;
/*     */   }
/*     */   
/*     */   public boolean hasRestriction() {
/* 197 */     return this.hasRestriction;
/*     */   }
/*     */   
/*     */   public Setting<T> addToGroup(Setting<?> group) {
/* 201 */     this.group = group;
/* 202 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isVisible() {
/* 206 */     if (this.group != null) {
/* 207 */       Object object = this.group.getValue(); if (object instanceof BooleanSettingGroup) { BooleanSettingGroup bp = (BooleanSettingGroup)object;
/* 208 */         if (!bp.isExtended())
/* 209 */           return false;  }
/*     */       
/* 211 */       object = this.group.getValue(); if (object instanceof SettingGroup) { SettingGroup p = (SettingGroup)object;
/* 212 */         if (!p.isExtended())
/* 213 */           return false;  }
/*     */     
/*     */     } 
/* 216 */     if (this.visibility == null) {
/* 217 */       return true;
/*     */     }
/* 219 */     return this.visibility.test(getValue());
/*     */   }
/*     */   
/*     */   public boolean is(T v) {
/* 223 */     return (this.value == v);
/*     */   }
/*     */   
/*     */   public boolean not(T v) {
/* 227 */     return (this.value != v);
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\setting\Setting.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */