package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Booking;
import model.Room;

import dao.impl.BookingDaoImpl;
import dao.impl.RoomDaoImpl;
import frame.MainPanel;

public class TakeRoomListener implements ActionListener {
	private JDialog takeRoomDialog;
	private JTextField jtfPhoneNumber;
	private JButton ensure;
	private JButton cancel;

	public TakeRoomListener(JDialog getRoomDialog, JTextField jtfPhoneNumber,
			JButton ensure, JButton cancel) {
		super();
		this.takeRoomDialog = getRoomDialog;
		this.jtfPhoneNumber = jtfPhoneNumber;
		this.ensure = ensure;
		this.cancel = cancel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ensure || e.getSource() == jtfPhoneNumber) {
			if (jtfPhoneNumber.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "������绰���룡");
				return;
			}
//			Booking1 booking = BookingDao1.instance().getBooking(
//					jtfPhoneNumber.getText().trim());
			if(!isInteger(jtfPhoneNumber.getText())){
				JOptionPane.showMessageDialog(null, "���������֣�");
				return;
			}
			BookingDaoImpl bookingDaoImpl = new BookingDaoImpl();
			Booking booking = bookingDaoImpl.getBooking(jtfPhoneNumber.getText().trim());
			
			if (booking != null) {
				RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
				Room room = roomDaoImpl.getRoom(booking.getRoomNumber());
				if(room != null){
//					room.take(booking.getHours());
					roomDaoImpl.takeRoom(room,booking.getHours(),room.getType());
				}
				takeRoomDialog.dispose();
//				BookingDao1.instance().removeBooking(booking);
				bookingDaoImpl.removeBooking(booking);
				MainPanel.instance().showRoomTakenData();
				JOptionPane.showMessageDialog(null,
						"<html>�÷��ɹ�������ţ� <b><font size=8>" + room.getNumber() + "</font></b>");
			} else {
				if(!isInteger(jtfPhoneNumber.getText())){
					JOptionPane.showMessageDialog(null, "���������֣�");
					return;
				}
				JOptionPane.showMessageDialog(null, "�ú���δ��������ȷ�ϣ�", "��ʾ",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == cancel)
			takeRoomDialog.dispose();
	}
	
	public boolean isInteger(String value){
		try{
		
		Integer.parseInt(value);
		return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
}
