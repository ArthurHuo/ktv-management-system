package dao.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import model.Booking;
import model.Room;
import dao.BookingDao;
/**
 * 预订房间的实现
 */


public class BookingDaoImpl extends BaseDao implements BookingDao{
	private List<Booking> bookingList;
	private static BookingDaoImpl bookingDao;

	public static BookingDaoImpl instance() {
		if (bookingDao == null)
			bookingDao = new BookingDaoImpl();
		return bookingDao;
	}

	public BookingDaoImpl() {
		bookingList = new ArrayList<Booking>();
	}

	public void addBooking(int roomid, String phone,int hours) {
		Booking booking = new Booking();
		RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
		
//		booking.setRoomId(roomid);
		booking.setRoomNumber(roomid);
		booking.setPhoneNumber(phone);
		booking.setHours(hours);
		booking.setBookTime(new Date());
		bookingList.add(booking);
		getSession().save(booking);
	}

	public Object[] formatData(Booking booking) {
		Object[] result = new Object[5];
		
		RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
		RoomTypeDaoImpl roomTypeDaoImpl = new RoomTypeDaoImpl();
//		System.out.println(roomTypeDaoImpl.getNamebyId(roomDaoImpl.getRoom(booking.getRoomNumber()).getType()));
		result[0] = roomDaoImpl.getRoom(booking.getRoomNumber()).getNumber();
		result[1] = roomTypeDaoImpl.getRoomTypebyId(roomDaoImpl.getRoom(booking.getRoomNumber()).getType()).getName();
		result[2] = booking.getPhoneNumber();
		result[3] = booking.getHours();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		result[4] = (booking.getBookTime() == null) ? "" : formatter
				.format(booking.getBookTime());
		return result;
	}

	public Object[][] getBookingsData() {
		bookingList = getBookingList();
		Object[][] result = new Object[BookingDaoImpl.instance().getBookingList()
				.size()][5];
		int i = 0;
		for (Booking booking : bookingList) {
			result[i] = formatData(booking);
			i++;
		}
		return result;
	}

	public List<Booking> getBookingList() {
		return getSession().createQuery("from Booking").list();
	}

	public Booking getBooking(String phoneNumber) {
		bookingList = getBookingList();
		for (Booking booking : bookingList) {
			if (booking.getPhoneNumber().equals(phoneNumber)) {
				return booking;
			}
		}
		return null;
	}

	public void removeBooking(Booking booking) {
		RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
		roomDaoImpl.getRoom(booking.getRoomNumber()).setBooked(false);
		bookingList.remove(booking);
		Session session = getSession();
//	    Booking booking = (Booking)getSession().createQuery("select r from Room r where r.number = ?").setParameter(0, number).uniqueResult();
//	    roomList.remove(room);
		session.delete(booking);
		session.flush();
	}
}
