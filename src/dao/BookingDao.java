package dao;

import java.util.List;

import model.Booking;
/**
 * �������ݿ⣬����Ԥ�����䣬�ӿ�
 */
public interface BookingDao {
	

	public void addBooking(int number, String phone,int hours);

	public Object[] formatData(Booking booking);

	public Object[][] getBookingsData();

	public List<Booking> getBookingList();

	public Booking getBooking(String phoneNumber);

	public void removeBooking(Booking booking);
}
