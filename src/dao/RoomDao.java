package dao;

import java.util.List;

import model.Room;

/**
 * �������ݿ⣬��������ɾ�Ĳ飬�ӿ�
 */
public interface RoomDao {
	

	public RoomDao instance();



	public Room getRoom(int id);

	public void addRoom(int number,int floor,int roomType);

	public void removeRoom(int id);

	public List<Room> getRoomList();

	public int getNumsTaken();

	public int getNumsExpired();

	public Object[][] getRoomsData();

	public Object[][] getRoomsTakenData();

	public Object[][] getExpiredRoomsData();

	public Object[] formatData(Room room);

	public Object[] formatTakenData(Room room);
}
