//package org.rapp.netty4.handler;
//
//import org.rapp.pojo.wifi.WifiSpy;
//import org.rapp.service.wifi.WifiSpyService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandler.Sharable;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.SimpleChannelInboundHandler;
//import io.netty.channel.socket.DatagramPacket;
//
//@Component
//@Qualifier("serverHandler")
//@Sharable
//public class ServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
//	
//	private static Logger logger = LoggerFactory.getLogger(ServerHandler.class);
//
//	@Autowired
//	WifiSpyService spyService;
//	
//	@Override
//	public void channelRead0(ChannelHandlerContext ctx, DatagramPacket datagramPacket)
//			throws Exception {
//		final ByteBuf bb = datagramPacket.content();
//	    byte[] buf = new byte[bb.readableBytes()];
//	    
//	    bb.readBytes(buf);
////		logger.info("info msg-->" + req);
//		
////		System.out.println("new msg-->" + new String(datagramPacket.content().array(), 0, datagramPacket.content().capacity()));
////		ctx.channel().writeAndFlush("server:"+msg);
//		
//		WifiSpy spy = new WifiSpy();
//		spy.parse(buf);
//		System.out.println("spy-->"+spy);
//		//保存日志
//		spyService.asyncSaveWifiSpy(spy);
//	}
//	
//	@Override
//	public void channelActive(ChannelHandlerContext ctx) throws Exception {
//		logger.info("Channel is active\n");
//		super.channelActive(ctx);
//	}
//
//	@Override
//	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//		logger.info("\nChannel is disconnected");
//		super.channelInactive(ctx);
//	}
//
//}
