//package org.rapp.netty4.server;
//
//import java.net.InetSocketAddress;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.channel.EventLoopGroup;
//
//@Component
//public class UDPServer {
//
//	@Autowired
//	@Qualifier("serverstrap")
//	private Bootstrap b;
//	
//	@Autowired
//	@Qualifier("tcpSocketAddress")
//	private InetSocketAddress tcpPort;
//	
//	@Autowired
//	@Qualifier("eventLoopGroup")
//	private EventLoopGroup eventLoopGroup;
//	
//
//	@PostConstruct
//	public void start() throws Exception {
//		System.out.println("Starting server at " + tcpPort);
//		b.bind(7080).sync().channel().closeFuture();//.await();
//	}
//
//	@PreDestroy
//	public void stop() throws Exception {
//		eventLoopGroup.shutdownGracefully();
//	}
//
//	public Bootstrap getB() {
//		return b;
//	}
//
//	public void setB(Bootstrap b) {
//		this.b = b;
//	}
//
//	public InetSocketAddress getTcpPort() {
//		return tcpPort;
//	}
//
//	public void setTcpPort(InetSocketAddress tcpPort) {
//		this.tcpPort = tcpPort;
//	}
//
//}
