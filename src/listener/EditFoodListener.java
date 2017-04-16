package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.impl.FoodDaoImpl;
import dao.impl.RoomTypeDaoImpl;

import frame.AddRoomTypeDialog;
import frame.EditFoodDialog;
import frame.EditRoomTypeDialog;
import frame.MainPanel;

public class EditFoodListener implements ActionListener {
	private JTextField jtfName, jcbType, jtfPrice, jtfNumRest,jtfFoodId;
	private JButton cancel;
	private int id;

	public EditFoodListener(JTextField jtfFoodId,JTextField jtfName, JTextField jcbType,
			JTextField jtfPrice, JTextField jtfNumRest, JButton cancel) {
//		this.jtfTypeId = jtfTypeId;
//		this.jtfRoomType = jtfRoomType;
//		this.jtfRoomPrice = jtfRoomPrice;
//		this.jtfPriceAdded = jtfPriceAdded;
		this.jtfFoodId = jtfFoodId;
		this.jtfName = jtfName;
		this.jcbType = jcbType;
		this.jtfPrice = jtfPrice;
		this.jtfNumRest = jtfNumRest;
		this.cancel = cancel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancel) {
			EditFoodDialog.instance().dispose();
		} else {
			if (jcbType.getText().equals("")
					|| jtfPrice.getText().equals("")
					|| jtfNumRest.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "请将信息填写完整！");
				return;
			}
//			RoomTypeDaoImpl roomTypeDaoImpl = new RoomTypeDaoImpl();
//			roomTypeDaoImpl.setRoomType(
//					Integer.parseInt(jtfTypeId.getText()),
//					jtfRoomType.getText(), Integer
//							.parseInt(jtfRoomPrice.getText()), Integer
//							.parseInt(jtfPriceAdded.getText()));
			FoodDaoImpl foodDaoImpl = new FoodDaoImpl();
			foodDaoImpl.setFood(Integer.parseInt(jtfFoodId.getText()), jtfName.getText(), jcbType.getText(), Integer
					.parseInt(jtfPrice.getText()), Integer
					.parseInt(jtfNumRest.getText()));
			
			
			MainPanel.instance().refresh();
			EditFoodDialog.instance().dispose();
			JOptionPane.showMessageDialog(null, "修改成功！");
		}
	}
}
