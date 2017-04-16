package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Room;
import model.RoomType;

import dao.impl.BookingDaoImpl;
import dao.impl.RoomDaoImpl;
import dao.impl.RoomTypeDaoImpl;

import frame.BookRoomDialog;
import frame.MainPanel;

public class BookRoomListener implements ActionListener {
	private JComboBox jtfType;
	private JTextField jtfPhoneNumber;
	private JTextField jlbDuration;
	private JCheckBox jcbTake;
	private JButton cancel;

	public BookRoomListener(JComboBox jtfType, JTextField jtfPhoneNumber,
			JTextField jlbDuration, JCheckBox jcbTake,
			JButton cancel) {
		this.jtfType = jtfType;
		this.jtfPhoneNumber = jtfPhoneNumber;
		this.jlbDuration = jlbDuration;
		this.jcbTake = jcbTake;
		this.cancel = cancel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancel) {
			BookRoomDialog.instance().dispose();
		} else {
			if (jtfType.getSelectedIndex() < 0) {
				JOptionPane.showMessageDialog(null, "请选择房间类型！");
				return;
			}
			
//			int roomType = ((RoomType1) jtfType.getSelectedItem()).getId();
			RoomTypeDaoImpl roomTypeDaoImpl = new RoomTypeDaoImpl();
			RoomType roomType = roomTypeDaoImpl.getRoomTypebyName(jtfType.getSelectedItem().toString());
			if (jcbTake.isSelected()) {
				RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
				List<Room> roomList = roomDaoImpl.getRoomList();
				for (int i = 0; i < roomList.size(); i++) {
					if (roomList.get(i).getType() == roomType.getId()
							&& !roomList.get(i).isBooked()
							&& roomList.get(i).getStartTime() == null
							) {
						BookRoomDialog.instance().dispose();
//						roomList.get(i).take(
//								Integer.parseInt(jlbDuration.getText()));
						roomDaoImpl.takeRoom(roomList.get(i),Integer.parseInt(jlbDuration.getText()),roomType.getId());
						MainPanel.instance().showRoomTakenData();
						JOptionPane.showMessageDialog(null,
								"<html>开房成功！房间号： <b><font size=8>"
										+ roomList.get(i).getNumber()
										+ "</font></b>");
						return;
					}
				}
				JOptionPane.showMessageDialog(null, "已没有该类型的空房！", "提示",
						JOptionPane.ERROR_MESSAGE);
			} else {
				
				if (jtfPhoneNumber.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入电话号码！");
					return;
				}
				if(!isInteger(jtfPhoneNumber.getText())){
					JOptionPane.showMessageDialog(null, "号码格式错误！");
					return;
				}
				RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
				List<Room> roomList = roomDaoImpl.getRoomList();
				for (int i = 0; i < roomList.size(); i++) {
					if (roomList.get(i).getType() == roomType.getId()
							&& !roomList.get(i).isBooked()
							&& roomList.get(i).getStartTime() == null
							) {
						BookingDaoImpl bookingDaoImpl = new BookingDaoImpl();
						if (bookingDaoImpl.getBooking(
								jtfPhoneNumber.getText()) != null) {
							JOptionPane.showMessageDialog(null, "该号码已预订过房间！",
									"提示", JOptionPane.ERROR_MESSAGE);
							return;
						}
						BookRoomDialog.instance().dispose();
//						roomList.get(i).book();
						roomDaoImpl.bookRoom(roomList.get(i));
//						Booking1 booking = new Booking1(roomList.get(i).getId(),
//								jtfPhoneNumber.getText(),
//								Integer.parseInt(jlbDuration.getText()));
						
//						BookingDaoImpl bookingDaoImpl = new BookingDaoImpl();
						bookingDaoImpl.addBooking(roomList.get(i).getId(),
								jtfPhoneNumber.getText(),
								Integer.parseInt(jlbDuration.getText()));
						MainPanel.instance().showRoomsBookedData();
						JOptionPane.showMessageDialog(null,
								"<html>订房成功！房间号： <b><font size=8>"
										+ roomList.get(i).getNumber()
										+ "</font></b>");
						return;
					}
				}
				JOptionPane.showMessageDialog(null, "已没有该类型的空房！", "提示",
						JOptionPane.ERROR_MESSAGE);
			}
		}
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
