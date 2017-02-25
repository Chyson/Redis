package cn.ashin.redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisAskDataException;

public class TestRedis
{
	private Jedis jedis;

//	@Before
//	public void setup()
//	{
//		// 连接redis服务器
//		jedis = new Jedis("localhost", 6379);
//	}

	/**
	 * redis操作String
	 */
	@Test
	public void testString()
	{
		// 连接本地的 Redis 服务
		jedis = new Jedis("localhost");
		System.out.println("Connect to server");
		// 设置 redis 字符串数据
		jedis.set("username", "lichuanxin");
		// 获取存储的数据并输出
		System.out.println("Stored string in redis: " + jedis.get("username"));
	}

	/**
	 * redis删除
	 */
	@Test
	public void testDel()
	{
		// 连接本地的 Redis 服务
		jedis = new Jedis("localhost");
		System.out.println("Connect to server");
		// 删除
		jedis.del("username");
		System.out.println(jedis.get("username"));
	}

	/**
	 * redis操作List
	 */
	@Test
	public void testList()
	{
		// 连接本地的 Redis 服务
		jedis = new Jedis("localhost");
		System.out.println("Connect to server");
		// 存储数据到列表中
		jedis.lpush("database1", "Redis");
		jedis.lpush("database1", "Mongodb");
		jedis.lpush("database1", "Mysql");
		// 获取存储的数据并输出
		List<String> list = jedis.lrange("database1", 0, 5);
		for (int i = 0; i < list.size(); i++)
		{
			System.out.println("Stored string in redis: " + list.get(i));
		}
	}

	/**
	 * redis操作Map
	 */
	@Test
	public void testMap()
	{
		// 连接本地的 Redis 服务
		jedis = new Jedis("localhost");
		System.out.println("Connect to server");
		// 存储数据到map
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "lichuanxin");
		map.put("age", "22");
		map.put("qq", "656539278");
		jedis.hmset("user", map);
		List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
		System.out.println(rsmap);

		Iterator<String> iter = jedis.hkeys("user").iterator();
		while (iter.hasNext())
		{
			String key = iter.next();
			System.out.println(key + ":" + jedis.hmget("user", key));
		}
	}

	/**
	 * redis操作Set
	 */
	@Test
	public void testSet()
	{
		// 连接本地的 Redis 服务
		jedis = new Jedis("localhost");
		System.out.println("Connect to server");
		// 添加
		jedis.sadd("user1", "lizhenyu");
		jedis.sadd("user1", "ningguohui");
		jedis.sadd("user1", "lichuanxin");
		jedis.sadd("user1", "zhaohuihui");
		jedis.sadd("user1", "others");
		System.out.println(jedis.smembers("user1"));// 获取所有加入的value
		System.out.println(jedis.scard("user1"));// 返回集合的元素个数
	}

	/**
	 * redis操作Zset
	 */
	@Test
	public void testZset()
	{
		// 连接本地的 Redis 服务
		jedis = new Jedis("localhost");
		System.out.println("Connect to server");
		// 添加
		jedis.zadd("user2", 0, "lizhenyu");
		jedis.zadd("user2", 0, "ningguohui");
		jedis.zadd("user2", 0, "lichuanxin");
		jedis.zadd("user2", 0, "zhaohuihui");
		jedis.zadd("user2", 0, "others");
		System.out.println(jedis.zrangeByScore("user2", 0, 10));// 获取所有加入的value
		System.out.println(jedis.zcard("user2"));
	}
}
