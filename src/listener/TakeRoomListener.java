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
				JOptionPane.showMessageDialog(null, "请输入电话号码！");
				return;
			}
//			Booking1 booking = BookingDao1.instance().getBooking(
//					jtfPhoneNumber.getText().trim());
			if(!isInteger(jtfPhoneNumber.getText())){
				JOptionPane.showMessageDialog(null, "请输入数字！");
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
						"<html>拿房成功！房间号： <b><font size=8>" + room.getNumber() + "</font></b>");
			} else {
				if(!isInteger(jtfPhoneNumber.getText())){
					JOptionPane.showMessageDialog(null, "请输入数字！");
					return;
				}
				JOptionPane.showMessageDialog(null, "该号码未订房，请确认！", "提示",
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
