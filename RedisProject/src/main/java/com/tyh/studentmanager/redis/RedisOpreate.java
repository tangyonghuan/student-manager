package com.tyh.studentmanager.redis;

import com.tyh.studentmanager.entity.Student;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Redis操作类
 */
public  final  class RedisOpreate {
    private static JedisPool jedisPool = new JedisPool();


    /**
     * 将一个学生实体存储到Redis中
     */
    public static void storageStudent(Student student) {
        Jedis jedis = jedisPool.getResource();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        HashMap<String, String> studentMap = new HashMap<String, String>();
        studentMap.put("id", student.getId());
        studentMap.put("name",student.getName());
        String birthday = simpleDateFormat.format(student.getBirthday());
        studentMap.put("birthday", birthday);

        studentMap.put("description", student.getDescription());
        studentMap.put("avgscore", String.valueOf(student.getAvgScore()));

        jedis.hmset(student.getId(), studentMap);
        jedis.zadd("sort",student.getAvgScore(),student.getId());
        jedis.close();
    }

    /**
     *通过学生id获取一个学生实体
     */
    public static Student getStudentById(String id) throws ParseException {
        Jedis jedis = jedisPool.getResource();
        Map<String, String> studentMap = jedis.hgetAll(id);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");

        Date birthday = simpleDateFormat.parse(studentMap.get("birthday"));
        Student student = new Student(studentMap.get("id"),studentMap.get("name"),
                birthday,studentMap.get("description"),Integer.valueOf(studentMap.get("avgscore")));
        jedis.close();
        return student;
    }


    /**
     *获取某个分数区间的学生id的排序
     */
    public static Set<String> getIdSortByScore(String key,int start,int end){
        Jedis jedis = jedisPool.getResource();
        Set<String> ids = jedis.zrevrange(key, start, end);
        jedis.close();
        return ids;
    }

    /**
     *根据学生id删除信息
     */
    public static void deleteStudentById(String id){
        Jedis jedis = jedisPool.getResource();
        jedis.del(id);
        jedis.zrem("sort", id);
        jedis.close();
    }
    /**
     * 判断id是否存在
     */
    public static boolean idIsExist(String id){
        Jedis jedis = jedisPool.getResource();
        Boolean isExist = jedis.exists(id);
        jedis.close();
        return isExist;
    }
    /**
     * 获取某个分数区间学生实体数量
     */
    public static Long getStudentCount(int min,int max){
        Jedis jedis = jedisPool.getResource();
        Long count = jedis.zcount("sort",min,max);
        jedis.close();
        return count;
    }
}
