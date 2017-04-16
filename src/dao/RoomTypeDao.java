package dao;

import java.util.List;

import dao.impl.RoomTypeDaoImpl;

import model.RoomType;

/**
 * �������ݿ⣬������������ɾ�Ĳ飬�ӿ�
 */

public interface RoomTypeDao {
	public RoomTypeDaoImpl instance();

	

	public void addRoomType(String name,int price,int priceAdded);

	public void setRoomType(int id, String name, int price,int priceAdded);

	public List<RoomType> getRoomTypeList();

	public Object[] formatTakenData(RoomType roomType);

	public Object[][] getRoomTypesData();

	public RoomType getRoomTypebyId(int id);

	public String getNamebyId(int id);

	public int getPricebyId(int id);

	public int getPriceAddedbyId(int id);

	public void removeRoomType(int id);
}
