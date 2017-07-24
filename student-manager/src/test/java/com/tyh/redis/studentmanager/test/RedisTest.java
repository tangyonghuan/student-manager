package com.tyh.redis.studentmanager.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.tyh.redis.studentmanager.entiy.Student;
import com.tyh.redis.studentmanager.redis.InfoOpreate;

public class RedisTest {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		for(int i=0;i<32;i++){
			char[] n = new char[3];
			
			Random random = new Random();
			Student student = new Student();
			student.setId("20131414624"+String.valueOf((10+random.nextInt(32))));
			for(int j=0;j<3;j++){
				if(j==0){
					n[j]=(char) (65+random.nextInt(26));				
				}else {
					n[j]=(char) (97+random.nextInt(26));	
				}
			}
			
			String name = String.valueOf(n);
			Date birthday = format.parse("1995-10-09");
			student.setName(name);
			student.setBirthday(birthday);
			student.setDescription("666");
			student.setAvgscore(60+random.nextInt(40));
			InfoOpreate.storage(student);
		}
	}
}
