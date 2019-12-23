package org.fral.examples;

//import org.junit.*;
//import org.redisson.Redisson;

import redis.clients.jedis.Jedis;

public class JedisIntegrationTest {
	
	private Jedis jedis;
	//private static Redisson redisServer;
	
	public JedisIntegrationTest() {
		jedis = new Jedis();
	}
	
	//@BeforeClass
	public static void setUp() {
		//redisServer = new RedisServer
	}
}
