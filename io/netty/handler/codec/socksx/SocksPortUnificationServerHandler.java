/*    */ package io.netty.handler.codec.socksx;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandler;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.channel.ChannelPipeline;
/*    */ import io.netty.handler.codec.ByteToMessageDecoder;
/*    */ import io.netty.handler.codec.socksx.v4.Socks4ServerDecoder;
/*    */ import io.netty.handler.codec.socksx.v4.Socks4ServerEncoder;
/*    */ import io.netty.handler.codec.socksx.v5.Socks5InitialRequestDecoder;
/*    */ import io.netty.handler.codec.socksx.v5.Socks5ServerEncoder;
/*    */ import io.netty.util.internal.ObjectUtil;
/*    */ import io.netty.util.internal.logging.InternalLogger;
/*    */ import io.netty.util.internal.logging.InternalLoggerFactory;
/*    */ import java.util.List;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SocksPortUnificationServerHandler
/*    */   extends ByteToMessageDecoder
/*    */ {
/* 41 */   private static final InternalLogger logger = InternalLoggerFactory.getInstance(SocksPortUnificationServerHandler.class);
/*    */ 
/*    */   
/*    */   private final Socks5ServerEncoder socks5encoder;
/*    */ 
/*    */ 
/*    */   
/*    */   public SocksPortUnificationServerHandler() {
/* 49 */     this(Socks5ServerEncoder.DEFAULT);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SocksPortUnificationServerHandler(Socks5ServerEncoder socks5encoder) {
/* 57 */     this.socks5encoder = (Socks5ServerEncoder)ObjectUtil.checkNotNull(socks5encoder, "socks5encoder");
/*    */   }
/*    */ 
/*    */   
/*    */   protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
/* 62 */     int readerIndex = in.readerIndex();
/* 63 */     if (in.writerIndex() == readerIndex) {
/*    */       return;
/*    */     }
/*    */     
/* 67 */     ChannelPipeline p = ctx.pipeline();
/* 68 */     byte versionVal = in.getByte(readerIndex);
/* 69 */     SocksVersion version = SocksVersion.valueOf(versionVal);
/*    */     
/* 71 */     switch (version) {
/*    */       case SOCKS4a:
/* 73 */         logKnownVersion(ctx, version);
/* 74 */         p.addAfter(ctx.name(), null, (ChannelHandler)Socks4ServerEncoder.INSTANCE);
/* 75 */         p.addAfter(ctx.name(), null, (ChannelHandler)new Socks4ServerDecoder());
/*    */         break;
/*    */       case SOCKS5:
/* 78 */         logKnownVersion(ctx, version);
/* 79 */         p.addAfter(ctx.name(), null, (ChannelHandler)this.socks5encoder);
/* 80 */         p.addAfter(ctx.name(), null, (ChannelHandler)new Socks5InitialRequestDecoder());
/*    */         break;
/*    */       default:
/* 83 */         logUnknownVersion(ctx, versionVal);
/* 84 */         in.skipBytes(in.readableBytes());
/* 85 */         ctx.close();
/*    */         return;
/*    */     } 
/*    */     
/* 89 */     p.remove((ChannelHandler)this);
/*    */   }
/*    */   
/*    */   private static void logKnownVersion(ChannelHandlerContext ctx, SocksVersion version) {
/* 93 */     logger.debug("{} Protocol version: {}({})", ctx.channel(), version);
/*    */   }
/*    */   
/*    */   private static void logUnknownVersion(ChannelHandlerContext ctx, byte versionVal) {
/* 97 */     if (logger.isDebugEnabled())
/* 98 */       logger.debug("{} Unknown protocol version: {}", ctx.channel(), Integer.valueOf(versionVal & 0xFF)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socksx\SocksPortUnificationServerHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */