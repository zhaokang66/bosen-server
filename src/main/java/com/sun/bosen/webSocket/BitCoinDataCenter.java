package com.sun.bosen.webSocket;

import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
 
@WebServlet(name="BitCoinDataCenter",urlPatterns = "/BitCoinDataCenter",loadOnStartup=1) //标记为Servlet不是为了其被访问，而是为了便于伴随Tomcat一起启动
public class BitCoinDataCenter extends HttpServlet implements Runnable{
 
    public void init(ServletConfig config){
        startup();
    }
     
    public  void startup(){
        new Thread(this).start();
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
