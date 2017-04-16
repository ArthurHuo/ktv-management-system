package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.RoomType;

import dao.impl.RoomDaoImpl;
import dao.impl.RoomTypeDaoImpl;

import frame.AddRoomDialog;
import frame.MainPanel;

public class AddRoomListener implements ActionListener {
	private JComboBox jcbRoomType;
	private JTextField jtfRoomNumber, jtfEndNumber, jtbFloor;
	private JButton cancel;

	public AddRoomListener(JComboBox jcbRoomType, JTextField jtfRoomNumber,
			JTextField jtfEndNumber, JTextField jtbFloor, JButton cancel) {
		this.jcbRoomType = jcbRoomType;
		this.jtfRoomNumber = jtfRoomNumber;
		this.jtfEndNumber = jtfEndNumber;
		this.jtbFloor = jtbFloor;
		this.cancel = cancel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancel) {
			AddRoomDialog.instance().dispose();
		} else {
			if (jtfEndNumber.isVisible()) {//批量添加 没用了
//				if (jtfRoomNumber.getText().equals("")
//						|| jtfEndNumber.getText().equals("")
//						|| jtbFloor.getText().equals("")) {
//					JOptionPane.showMessageDialog(null, "请将信息填写完整！");
//					return;
//				}
//				int nums = 0;
//				int errNums = 0;
//				int roomId = Integer.parseInt(jtfRoomNumber.getText());
//				int endId = Integer.parseInt(jtfEndNumber.getText());
//				if (roomId > endId) {
//					JOptionPane.showMessageDialog(null, "开始房号应比结束房号小！");
//					return;
//				}
//				RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
//				while (roomId <= endId) {
//					if (roomDaoImpl.getRoom(roomId) == null) {
//						++nums;
//						int roomType = ((RoomType) jcbRoomType
//								.getSelectedItem()).getId();
//						RoomDao1.instance().addRoom(
//								new Room1(roomId, Integer.parseInt(jtbFloor
//										.getText()), roomType));
						
//					}else{
//						++errNums;
//					}
//					++roomId;
//				}
//				AddRoomDialog.instance().dispose();
//				MainPanel.instance().refresh();
//				JOptionPane
//						.showMessageDialog(null, "<html>成功添加 <b><font size=8>"
//								+ nums + "</font></b> 个房间！" + ((errNums > 0)?"其中 <b><font size=8>"
//								+ errNums + "</font></b> 个房间已存在。":""));
			} else {
				if (jtfRoomNumber.getText().equals("")
						|| jtbFloor.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请将信息填写完整！");
					return;
				}
				int roomId = Integer.parseInt(jtfRoomNumber.getText());
				RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
				if (roomDaoImpl.getRoom(roomId) != null) {
					JOptionPane.showMessageDialog(null,
							"<html>房号<b><font size=8>" + roomId
									+ "</font></b>已存在！", "提示",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				RoomTypeDaoImpl roomTypeDaoImpl = new RoomTypeDaoImpl();
				RoomType roomType = roomTypeDaoImpl.getRoomTypebyName(jcbRoomType.getSelectedItem().toString());
						
				roomDaoImpl.addRoom(roomId, Integer.parseInt(jtbFloor.getText()),
								roomType.getId());
				MainPanel.instance().refresh();
				AddRoomDialog.instance().dispose();
				JOptionPane.showMessageDialog(null, "添加成功！");
			}
		}
	}
}
