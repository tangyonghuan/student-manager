package com.tyh.redis.studentmanager.redis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


import com.tyh.redis.studentmanager.entiy.Student;

import redis.clients.jedis.Jedis;

public class InfoOpreate {

	
	private static Jedis jedis = new Jedis("localhost");
	
	//存放一个学生信息到一个HASH中 用id 作为key 将id 存入一个ZSET中 用avgscore 作为score
	public static void storage(Student student) {
		// TODO Auto-generated method stub
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
		HashMap<String, String> studentMap = new HashMap<String, String>();
		studentMap.put("id", student.getId());
		studentMap.put("name",student.getName());
		String birthday = simpleDateFormat.format(student.getBirthday());
		studentMap.put("birthday", birthday);
			
		studentMap.put("description", student.getDescription());
		studentMap.put("avgscore", String.valueOf(student.getAvgscore()));
			
		jedis.hmset(student.getId(), studentMap);
		jedis.zadd("sort",student.getAvgscore(),student.getId());
	}
	//根据id取学生信息
	public static Student getStudent(String id) throws ParseException{
		Map<String, String>studentMap = jedis.hgetAll(id);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
	
		Date birthday = simpleDateFormat.parse(studentMap.get("birthday"));
		Student student = new Student(studentMap.get("id"),studentMap.get("name"),
				birthday,studentMap.get("description"),Integer.valueOf(studentMap.get("avgscore")));
		return student;
	}
	public static void Close(){
		jedis.close();
	}
	//获取排序
	public static Set<String>getSort(String key){
		
		return jedis.zrevrange(key, 0, 100);
	}
	//根据id删除信息
	public static void delete(String id){
		jedis.del(id);
		jedis.zrem("sort", id);
	}
}
