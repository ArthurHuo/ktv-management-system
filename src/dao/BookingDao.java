package dao;

import java.util.List;

import model.Booking;
/**
 * 操作数据库，管理预订房间，接口
 */
public interface BookingDao {
	

	public void addBooking(int number, String phone,int hours);

	public Object[] formatData(Booking booking);

	public Object[][] getBookingsData();

	public List<Booking> getBookingList();

	public Booking getBooking(String phoneNumber);

	public void removeBooking(Booking booking);
}
