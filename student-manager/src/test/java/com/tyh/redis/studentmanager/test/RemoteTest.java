package com.tyh.redis.studentmanager.test;

import redis.clients.jedis.Jedis;

public class RemoteTest {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("39.108.125.122",6379);
		jedis.auth("123");
		System.out.println(jedis.ping());
		
	}
}
