package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;

import model.Booking;
import model.Food;
import model.Room;
import model.RoomType;

import dao.impl.BookingDaoImpl;
import dao.impl.FoodDaoImpl;
import dao.impl.RoomDaoImpl;
import dao.impl.RoomTypeDaoImpl;


import frame.AddFoodDialog;
import frame.AddRoomDialog;
import frame.AddRoomTypeDialog;
import frame.BuyFoodDialog;
import frame.DataTable;
import frame.EditFoodDialog;
import frame.EditRoomTypeDialog;
import frame.MainPanel;

public class TableListener extends MouseAdapter implements ActionListener {
	private DataTable table;
	private JPopupMenu menu;

	public TableListener(JPopupMenu menu) {
		this.table = MainPanel.instance().getTable();
		this.menu = menu;
	}

	public void mousePressed(MouseEvent e) {
		if (table.getSelectedRow() < 0) {
			int modifiers = e.getModifiers();
			modifiers -= MouseEvent.BUTTON3_MASK;
			modifiers |= MouseEvent.BUTTON1_MASK;
			MouseEvent ne = new MouseEvent(e.getComponent(), e.getID(),
					e.getWhen(), modifiers, e.getX(), e.getY(),
					e.getClickCount(), false);
			table.dispatchEvent(ne);
		}
	}

	public void mouseReleased(MouseEvent e) {
		if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0
				&& !e.isControlDown() && !e.isShiftDown()) {
			menu.show(table, e.getX(), e.getY());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (table.getSelectedRow() < 0)
			return;
		String strAction = ((JMenuItem) e.getSource()).getText().trim();
		if (!isSure("<html>您确定要进行<b><font size=6> " + strAction
				+ " </font></b>操作吗？"))
			return;
		if (strAction.equals("添加酒水")) {
			BuyFoodDialog.instance().open();
			return;
		} else if (strAction.equals("房间结算")) {
			String tmpStr = getSelectedValue(0);
			if (tmpStr == null)
				return;
			RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
			Room room = roomDaoImpl.getRoomByName(Integer.parseInt(tmpStr));
//			Room1 room = RoomDao1.instance().getRoom(Integer.parseInt(tmpStr));
			removeSelectedRow();
			JOptionPane.showMessageDialog(null,
					"<html>结算成功！总消费<b><font size=8>  " + room.getMoney()
							+ " </font></b>元。");
//			room.end();
			roomDaoImpl.end(room);
			return;
		} else if (strAction.equals("房间加钟")) {
			String tmpStr = getSelectedValue(0);
			if (tmpStr == null)
				return;
			DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
			
			RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
			Room room = roomDaoImpl.getRoomByName(Integer.parseInt(tmpStr));
			
//			room.addHours();
			roomDaoImpl.addHours(room);
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			tableModel.setValueAt(room.getMoney(), table.getSelectedRow(), 2);
			tableModel.setValueAt(formatter.format(room.getEndTime()),
					table.getSelectedRow(), 4);
		} else if (strAction.equals("开通房间")) {
			String tmpStr = getSelectedValue(2);
			if (tmpStr == null)
				return;
//			BookingDao1 bookingDao = BookingDao1.instance();
//			Booking1 booking = bookingDao.getBooking(tmpStr);
//			RoomDao1.instance().getRoom(booking.getRoomId())
//					.take(booking.getHours());
			RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
			BookingDaoImpl bookingDaoImpl = new BookingDaoImpl();
			List<Room> roomList = roomDaoImpl.getRoomList();
			Booking booking = bookingDaoImpl.getBooking(tmpStr);
			
			roomDaoImpl.takeRoom(roomDaoImpl.getRoom(booking.getRoomNumber()),booking.getHours(),roomDaoImpl.getRoom(booking.getRoomNumber()).getType());
			
			bookingDaoImpl.removeBooking(booking);
			removeSelectedRow();
			JOptionPane.showMessageDialog(
					null,
					"<html>开通房间成功！房间号：<b><font size=8>  "
							+ roomDaoImpl.getRoom(booking.getRoomNumber()).getNumber() + " </font></b>");
			return;
		} else if (strAction.equals("删除订单")) {
//			BookingDao1 bookingDao = BookingDao1.instance();
			BookingDaoImpl bookingDaoImpl = new BookingDaoImpl();
			for (int row : table.getSelectedRows()) {
				Booking booking = bookingDaoImpl.getBooking(getValue(row, 2));
				bookingDaoImpl.removeBooking(booking);
			}
			removeSelectedRows();
		} else if (strAction.equals("添加房间")) {
			AddRoomDialog.instance().open(false);
			return;
		} else if (strAction.equals("删除房间")) {
			RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
			for (int row : table.getSelectedRows()) {
				roomDaoImpl.removeRoom(Integer.parseInt(getValue(row,0)));
			}
			removeSelectedRows();
		} else if (strAction.equals("添加食品")) {
			AddFoodDialog.instance().open();
			return;
		} else if (strAction.equals("编辑食品")) {
			
			FoodDaoImpl foodDaoImpl = new FoodDaoImpl();
			Food food = foodDaoImpl.getFoodbyId(Integer
					.parseInt(getValue(table.getSelectedRow(), 0)));
			if(food == null){
				return;
			}
			EditFoodDialog.instance().open(food,Integer
					.parseInt(getValue(table.getSelectedRow(), 0)));
			return;
//			RoomTypeDaoImpl roomTypeDaoImpl = new RoomTypeDaoImpl();
//			RoomType roomType = roomTypeDaoImpl.getRoomTypebyId(Integer
//					.parseInt(getValue(table.getSelectedRow(), 0)));
//			if (roomType == null)
//				return;
//			EditRoomTypeDialog.instance().open(roomType);
//			return;
		} else if (strAction.equals("删除食品")) {
			FoodDaoImpl foodDaoImpl = new FoodDaoImpl();
			for (int row : table.getSelectedRows()) {
//				Food food = FoodDaoImpl.getFoodbyId(Integer.parseInt(getValue(row,
//						0)));
//				if (food == null)
//					return;
				foodDaoImpl.removeFood(Integer.parseInt(getValue(row,0)));
			}
			removeSelectedRows();
		} else if (strAction.equals("添加类型")) {
			AddRoomTypeDialog.instance().open();
			return;
		} else if (strAction.equals("编辑类型")) {
			RoomTypeDaoImpl roomTypeDaoImpl = new RoomTypeDaoImpl();
			RoomType roomType = roomTypeDaoImpl.getRoomTypebyId(Integer
					.parseInt(getValue(table.getSelectedRow(), 0)));
			if (roomType == null)
				return;
			EditRoomTypeDialog.instance().open(roomType);
			return;
		} else if (strAction.equals("删除类型")) {
			RoomTypeDaoImpl roomTypeDaoImpl = new RoomTypeDaoImpl();
			for (int row : table.getSelectedRows()) {
//				RoomType1 roomType = roomTypeDao.getRoomTypebyId(Integer
//						.parseInt(getValue(row, 0)));
//				if (roomType == null)
//					return;
				roomTypeDaoImpl.removeRoomType(Integer.parseInt(getValue(row,0)));
			}
			removeSelectedRows();
		}
		JOptionPane.showMessageDialog(null, strAction + "成功！");
	}

	private boolean isSure(String msg) {
		return (JOptionPane.showConfirmDialog(null, msg, "消息",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}

	private String getValue(int row, int column) {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		return tableModel.getValueAt(row, column).toString();
	}

	private String getSelectedValue(int column) {
		if (table.getSelectedRow() < 0)
			return null;
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		return tableModel.getValueAt(table.getSelectedRow(), column).toString();
	}

	private void removeSelectedRow() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.removeRow(table.getSelectedRow());
	}

	private void removeSelectedRows() {
		if (table.getSelectedRow() < 0)
			return;
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		boolean isEndSelect = false;
		if (table.getSelectedRows()[table.getSelectedRows().length - 1] == tableModel
				.getRowCount() - 1) {
			isEndSelect = true;
		}
		while (table.getSelectedRow() >= 0) {
			tableModel.removeRow(table.getSelectedRow());
		}
		if (isEndSelect && tableModel.getRowCount() > 0) {
			tableModel.removeRow(tableModel.getRowCount() - 1);
		}
		if (tableModel.getRowCount() <= 0) {
			MainPanel.instance().refresh();
		}
	}
}
