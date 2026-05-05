/*     */ package io.netty.handler.codec.socks;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.channel.ChannelHandler;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import io.netty.handler.codec.ReplayingDecoder;
/*     */ import io.netty.util.NetUtil;
/*     */ import java.util.List;
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
/*     */ public class SocksCmdResponseDecoder
/*     */   extends ReplayingDecoder<SocksCmdResponseDecoder.State>
/*     */ {
/*     */   private SocksCmdStatus cmdStatus;
/*     */   private SocksAddressType addressType;
/*     */   
/*     */   public SocksCmdResponseDecoder() {
/*  37 */     super(State.CHECK_PROTOCOL_VERSION); } protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception { String host; int fieldLength;
/*     */     byte[] bytes;
/*     */     int port;
/*     */     String str1;
/*     */     int i;
/*  42 */     switch ((State)state()) {
/*     */       case CHECK_PROTOCOL_VERSION:
/*  44 */         if (byteBuf.readByte() != SocksProtocolVersion.SOCKS5.byteValue()) {
/*  45 */           out.add(SocksCommonUtils.UNKNOWN_SOCKS_RESPONSE);
/*     */           break;
/*     */         } 
/*  48 */         checkpoint(State.READ_CMD_HEADER);
/*     */       
/*     */       case READ_CMD_HEADER:
/*  51 */         this.cmdStatus = SocksCmdStatus.valueOf(byteBuf.readByte());
/*  52 */         byteBuf.skipBytes(1);
/*  53 */         this.addressType = SocksAddressType.valueOf(byteBuf.readByte());
/*  54 */         checkpoint(State.READ_CMD_ADDRESS);
/*     */       
/*     */       case READ_CMD_ADDRESS:
/*  57 */         switch (this.addressType) {
/*     */           case CHECK_PROTOCOL_VERSION:
/*  59 */             host = NetUtil.intToIpAddress(byteBuf.readInt());
/*  60 */             port = byteBuf.readUnsignedShort();
/*  61 */             out.add(new SocksCmdResponse(this.cmdStatus, this.addressType, host, port));
/*     */             break;
/*     */           
/*     */           case READ_CMD_HEADER:
/*  65 */             fieldLength = byteBuf.readByte();
/*  66 */             str1 = SocksCommonUtils.readUsAscii(byteBuf, fieldLength);
/*  67 */             i = byteBuf.readUnsignedShort();
/*  68 */             out.add(new SocksCmdResponse(this.cmdStatus, this.addressType, str1, i));
/*     */             break;
/*     */           
/*     */           case READ_CMD_ADDRESS:
/*  72 */             bytes = new byte[16];
/*  73 */             byteBuf.readBytes(bytes);
/*  74 */             str1 = SocksCommonUtils.ipv6toStr(bytes);
/*  75 */             i = byteBuf.readUnsignedShort();
/*  76 */             out.add(new SocksCmdResponse(this.cmdStatus, this.addressType, str1, i));
/*     */             break;
/*     */           
/*     */           case null:
/*  80 */             out.add(SocksCommonUtils.UNKNOWN_SOCKS_RESPONSE);
/*     */             break;
/*     */         } 
/*     */         
/*  84 */         throw new Error();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       default:
/*  90 */         throw new Error();
/*     */     } 
/*     */     
/*  93 */     ctx.pipeline().remove((ChannelHandler)this); }
/*     */ 
/*     */   
/*     */   public enum State
/*     */   {
/*  98 */     CHECK_PROTOCOL_VERSION,
/*  99 */     READ_CMD_HEADER,
/* 100 */     READ_CMD_ADDRESS;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socks\SocksCmdResponseDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */