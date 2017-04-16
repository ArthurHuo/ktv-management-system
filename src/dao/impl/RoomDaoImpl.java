package dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Session;

import model.Food;
import model.Room;
import model.RoomType;
import dao.ConfigDao;
import dao.RoomDao;
/**
 * 房间增删改查的实现
 */
public class RoomDaoImpl extends BaseDao implements RoomDao{
	private List<Room> roomList;
	private static RoomDaoImpl roomDao;

	public RoomDaoImpl instance() {
		if (roomDao == null)
			roomDao = new RoomDaoImpl();
		return roomDao;
	}

	public RoomDaoImpl() {
		roomList = new ArrayList<Room>();
		
	}
	public void takeRoom(Room room,int hours,int typeid){
		room.setBooked(false);
		RoomTypeDaoImpl roomTypeDaoImpl = new RoomTypeDaoImpl();
		room.setMoney((int) ((roomTypeDaoImpl.getPricebyId(typeid)*hours)
				* ConfigDao.instance().getConfig().getDiscount() / 10));
		room.setStartTime(new Date());
		GregorianCalendar date = new GregorianCalendar();
		date.setTime(room.getStartTime());
		date.add(GregorianCalendar.HOUR, hours);
		
		room.setEndTime(date.getTime());
		Session session = getSession();
	    session.update(room);
		session.flush();
	}
	
	public void end(Room room){
		room.setMoney(0);
		room.setStartTime(null);
		room.setEndTime(null);
		Session session = getSession();
	    session.update(room);
		session.flush();
	}
	
	public void addHours(Room room){
		GregorianCalendar date = new GregorianCalendar();
		date.setTime(room.getEndTime());
		date.add(GregorianCalendar.HOUR, ConfigDao.instance().getConfig()
				.getHoursAdded());
		
		RoomTypeDaoImpl roomTypeDaoImpl = new RoomTypeDaoImpl();
		room.setMoney(room.getMoney() + (int)(roomTypeDaoImpl.getPriceAddedbyId(room.getType())
				* ConfigDao.instance().getConfig().getDiscount() / 10));
//		System.out.println(roomTypeDaoImpl.getPriceAddedbyId(room.getType()));
//		money += (RoomTypeDao1.instance().getPriceAddedbyId(room.getType())
//				* ConfigDao.instance().getConfig().getDiscount() / 10);
//		endTime = date.getTime();
		room.setEndTime(date.getTime());
		Session session = getSession();
	    session.update(room);
		session.flush();
	}
	
	public void bookRoom(Room room){
		room.setBooked(true);
		
		Session session = getSession();
	    session.update(room);
		session.flush();
	}
	
	public void buyFood(Room room,Food food, int nums){
		room.setMoney(room.getMoney()+(food.getPrice() * nums));
		Session session = getSession();
	    session.update(room);
		session.flush();
		food.setNumRest(food.getNumRest()-nums);
		session.update(food);
		session.flush();
		
	}

	public Room getRoom(int id) {
		return (Room)getSession().createQuery("select r from Room r where r.id = ?").setParameter(0, id).uniqueResult();
	}
	public Room getRoomByName(int id) {
		return (Room)getSession().createQuery("select r from Room r where r.number = ?").setParameter(0, id).uniqueResult();
	}

	public void addRoom(int number,int floor,int roomType) {
		Room room = new Room();
		room.setNumber(number);
		room.setFloor(floor);
		room.setType(roomType);
		getSession().save(room);
		roomList.add(room);
	}

	public void removeRoom(int number) {
//		roomList.remove(room);
		Session session = getSession();
	    Room room = (Room)getSession().createQuery("select r from Room r where r.number = ?").setParameter(0, number).uniqueResult();
	    roomList.remove(room);
		session.delete(room);
		session.flush();
	}

	public List<Room> getRoomList() {
		return getSession().createQuery("from Room").list();
	}

	public int getNumsTaken() {
		int nums = 0;
		for (Room room : roomList) {
			if (room.getStartTime() != null)
				++nums;
		}
		return nums;
	}

	public int getNumsExpired() {
		int nums = 0;
		for (Room room : roomList) {
			if (room.getEndTime() != null
					&& room.getEndTime().before(new Date()))
				++nums;
		}
		return nums;
	}

	public Object[][] getRoomsData() {
		roomList = getRoomList();
		Object[][] result = new Object[1][5];
		if (roomList.size() > 0) {
			result = new Object[roomList.size()][5];
			Room room;
			for (int i = 0; i < roomList.size(); i++) {
				room = roomList.get(i);
				result[i] = formatData(room);
			}
		} else {
			result[0][0] = 0;
			result[0][1] = "没有数据";
			result[0][2] = 0;
			result[0][3] = 0;
			result[0][4] = 0;
		}
		return result;
	}

	public Object[][] getRoomsTakenData() {
		roomList = getRoomList();
		Object[][] result = new Object[getNumsTaken()][5];
		int i = 0;
		for (Room room : roomList) {
			if (room.getStartTime() != null) {
				result[i] = formatTakenData(room);
				i++;
			}
		}
		return result;
	}

	public Object[][] getExpiredRoomsData() {
		roomList = getRoomList();
		Object[][] result = new Object[getNumsExpired()][5];
		int i = 0;
		for (Room room : roomList) {
			if (room.getEndTime() != null
					&& room.getEndTime().before(new Date())) {
				result[i] = formatTakenData(room);
				i++;
			}
		}
		return result;
	}

	public Object[] formatData(Room room) {
		RoomTypeDaoImpl roomTypeDaoImpl = new RoomTypeDaoImpl();
		Object[] result = new Object[5];
		result[0] = room.getNumber();
		result[1] = roomTypeDaoImpl.getRoomTypebyId(room.getType()).getName();
		result[2] = room.getFloor();
		result[3] = roomTypeDaoImpl.getRoomTypebyId(room.getType()).getPrice();
		result[4] = roomTypeDaoImpl.getRoomTypebyId(room.getType()).getPriceAdded();
		return result;
	}

	public Object[] formatTakenData(Room room) {
		RoomTypeDaoImpl roomTypeDaoImpl = new RoomTypeDaoImpl();
		Object[] result = new Object[5];
		result[0] = room.getNumber();
		result[1] = roomTypeDaoImpl.getRoomTypebyId(room.getType()).getName();
		result[2] = room.getMoney();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		result[3] = (room.getStartTime() == null) ? "" : formatter.format(room
				.getStartTime());
		result[4] = (room.getEndTime() == null) ? "" : formatter.format(room
				.getEndTime());
		return result;
	}

	


}
