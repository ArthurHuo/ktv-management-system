package test;

import java.text.DateFormat;
import java.util.Date;

import model.Room;

import org.hibernate.classic.Session;

public class test {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		UserDaoImpl userDaoImpl = new UserDaoImpl();
//		System.out.println(userDaoImpl.finduserByUsername("admin"));
//		userServiceImpl.login("admin","admin");
//		UserServiceImpl userServiceImpl = new UserServiceImpl();
//		System.out.println(userServiceImpl.login("admin","admin").toString());
//		User user;
//		UserDaoImpl userDaoImpl = new UserDaoImpl();
//		user = userDaoImpl.findUserById(1);
//		System.out.println(user);
//		Food food = new Food();
//		food.setId(16);
//		food.setName("aa");
//		food.setType("aaa");
//		food.setPrice(11);
//		food.setNumRest(1111);
//		System.out.println(food);
	
//		Session session = (Session) HibernateUtils.getSession();
//		Food food = (Food)session.load(Food.class, 16);
//		System.out.println(food.getName());
//		session.delete(food);
//		session.flush();
		Session session = (Session) HibernateUtils.getSession();
		Room room;
//		room.setId(1);
//		room.setNumber(1101);
//		room.setFloor(1);
//		room.setType(1);
//		room.setStartTime(new Date());
//		room.setEndTime(new Date());
//		room.setBooked(false);
//		session.save(room);
		
		
		
		
//		for(int i =0;i<6;i++){
//			room = new Room();
//			room.setId(i+1);
//			room.setNumber(101+i);
//			room.setFloor(1);
//			room.setType(1);
//			room.setStartTime(new Date());
//			room.setEndTime(new Date());
//			room.setBooked(false);
//			session.save(room);
//		}
//		for(int i =0;i<6;i++){
//			room = new Room();
//			room.setId(i+10);
//			room.setNumber(201+i);
//			room.setFloor(2);
//			room.setType(2);
//			room.setStartTime(new Date());
//			room.setEndTime(new Date());
//			room.setBooked(false);
//			session.save(room);
//		}
//		for(int i =0;i<6;i++){
//			room = new Room();
//			room.setId(i+20);
//			room.setNumber(301+i);
//			room.setFloor(3);
//			room.setType(3);
//			room.setStartTime(new Date());
//			room.setEndTime(new Date());
//			room.setBooked(false);
//			session.save(room);
//		}
//		for(int i =0;i<6;i++){
//			room = new Room();
//			room.setId(i+30);
//			room.setNumber(401+i);
//			room.setFloor(4);
//			room.setType(4);
//			room.setStartTime(new Date());
//			room.setEndTime(new Date());
//			room.setBooked(false);
//			session.save(room);
//		}
//		for(int i =0;i<6;i++){
//			room = new Room();
//			room.setId(i+40);
//			room.setNumber(501+i);
//			room.setFloor(5);
//			room.setType(5);
//			room.setStartTime(new Date());
//			room.setEndTime(new Date());
//			room.setBooked(false);
//			session.save(room);
//		}
//		
		
		for(int i =0;i<6;i++){
			room = new Room();
			room.setId(i+1);
			room.setNumber(101+i);
			room.setFloor(1);
			room.setType(1);
			room.setStartTime(null);
			room.setEndTime(null);
			room.setBooked(false);
			session.save(room);
		}
		for(int i =0;i<6;i++){
			room = new Room();
			room.setId(i+10);
			room.setNumber(201+i);
			room.setFloor(2);
			room.setType(2);
			room.setStartTime(null);
			room.setEndTime(null);
			room.setBooked(false);
			session.save(room);
		}
		for(int i =0;i<6;i++){
			room = new Room();
			room.setId(i+20);
			room.setNumber(301+i);
			room.setFloor(3);
			room.setType(3);
			room.setStartTime(null);
			room.setEndTime(null);
			room.setBooked(false);
			session.save(room);
		}
		for(int i =0;i<6;i++){
			room = new Room();
			room.setId(i+30);
			room.setNumber(401+i);
			room.setFloor(4);
			room.setType(4);
			room.setStartTime(null);
			room.setEndTime(null);
			room.setBooked(false);
			session.save(room);
		}
		for(int i =0;i<6;i++){
			room = new Room();
			room.setId(i+40);
			room.setNumber(501+i);
			room.setFloor(5);
			room.setType(5);
			room.setStartTime(null);
			room.setEndTime(null);
			room.setBooked(false);
			session.save(room);
		}
		
	}

}
