package cn.ashin.redis;

import redis.clients.jedis.Jedis;

public class Demo {
	public static void main(String[] args) {
		// 连接本地的 Redis服务
		Jedis jedis = new Jedis("localhost");
		// 连接虚拟机redis
		// Jedis jedis=new Jedis("192.168.202.128",6379);
		System.out.println("Connect to server");
		// 查看服务是否运行
		System.out.println("Server is running: " + jedis.ping());
	}
}
