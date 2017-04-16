package dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import dao.RoomTypeDao;
import dao.UserDao;

import model.Food;
import model.RoomType;
import model.User;

/**
 * 房间类型增删改查的实现
 */




public class RoomTypeDaoImpl extends BaseDao implements RoomTypeDao{
	private List<RoomType> roomTypeList;
	private static RoomTypeDaoImpl roomTypeDao;

	public RoomTypeDaoImpl instance() {
		if (roomTypeDao == null)
			roomTypeDao = new RoomTypeDaoImpl();
		return roomTypeDao;
	}

	public RoomTypeDaoImpl() {
		roomTypeList = new ArrayList<RoomType>();
		
	}

	public void addRoomType(String name,int price,int priceAdded) {
		RoomType roomType = new RoomType();
		
		roomType.setName(name);
		roomType.setPrice(price);
		roomType.setPriceAdded(priceAdded);
		getSession().save(roomType);
	}

	public void setRoomType(int id, String name, int price,int priceAdded) {
//		for (int i = 0; i < roomTypeList.size(); ++i) {
//			if (roomTypeList.get(i).getId() == id) {
//				roomTypeList.set(i, roomType);
//				return;
//			}
//		}
		RoomType roomType = new RoomType();
		roomType.setId(id);
		roomType.setName(name);
		roomType.setPrice(price);
		roomType.setPriceAdded(priceAdded);
//		getSession().update(roomType);
//		getSession().session.flush();
		Session session = getSession();
	    session.update(roomType);
		session.flush();
	
	}

	public List<RoomType> getRoomTypeList() {
		
		return getSession().createQuery("from RoomType").list();
	}

	public Object[] formatTakenData(RoomType roomType) {
		Object[] result = new Object[4];
		result[0] = roomType.getId();
		result[1] = roomType.getName();
		result[2] = roomType.getPrice();
		result[3] = roomType.getPriceAdded();
		return result;
	}

	public Object[][] getRoomTypesData() {
		
		roomTypeList = getRoomTypeList();
		Object[][] result = new Object[1][4];
		if (roomTypeList.size() > 0) {
			result = new Object[roomTypeList.size()][4];
			int i = 0;
			for (RoomType roomType : roomTypeList) {
				result[i] = formatTakenData(roomType);
				++i;
			}
		} else {
			result[0][0] = 0;
			result[0][1] = "没有数据";
			result[0][2] = 0;
			result[0][3] = 0;
		}
		return result;
	}

	public RoomType getRoomTypebyId(int id) {
//		for (RoomType roomType : roomTypeList) {
//			if (roomType.getId() == id)
//				return roomType;
//		}
//		return null;
		return (RoomType)getSession().createQuery("select rt from RoomType rt where rt.id = ?").setParameter(0, id).uniqueResult();
	}
	public RoomType getRoomTypebyName(String name) {
//		for (RoomType roomType : roomTypeList) {
//			if (roomType.getId() == id)
//				return roomType;
//		}
//		return null;
		return (RoomType)getSession().createQuery("select rt from RoomType rt where rt.name = ?").setParameter(0, name).uniqueResult();
	}

	public String getNamebyId(int id) {
		for (RoomType roomType : roomTypeList) {
			if (roomType.getId() == id)
				return roomType.getName();
		}
		return null;
	}

	public int getPricebyId(int id) {
		roomTypeList = getRoomTypeList();
		for (RoomType roomType : roomTypeList) {
			if (roomType.getId() == id)
				return roomType.getPrice();
		}
		return 0;
	}

	public int getPriceAddedbyId(int id) {
		roomTypeList = getRoomTypeList();
		for (RoomType roomType : roomTypeList) {
			if (roomType.getId() == id)
				return roomType.getPriceAdded();
		}
		return 0;
	}

	public void removeRoomType(int id) {
//		roomTypeList.remove(roomType);
		Session session = getSession();
	    RoomType roomType = (RoomType)session.load(RoomType.class, id);
	    roomTypeList.remove(roomType);
		session.delete(roomType);
		session.flush();
	}
}
