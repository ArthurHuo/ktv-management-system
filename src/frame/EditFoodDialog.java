package frame;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import listener.AddFoodListener;
import listener.EditFoodListener;
import model.Food;

import dao.impl.FoodDaoImpl;
/**
 * �������ʳ��Ĵ���
 */
public class EditFoodDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7928002009191943965L;
	private static EditFoodDialog addFoodDialog;
	private JTextField jtfName, jtfPrice, jtfNumRest,jtfTFoodId;
	private JTextField jcbType;
	private int id;

	public static EditFoodDialog instance() {
		if (addFoodDialog == null)
			addFoodDialog = new EditFoodDialog();
		return addFoodDialog;
	}

	public EditFoodDialog() {
		super(MainFrame.instance(), "���ʳƷ", true);
		setLayout(null);
		setSize(400, 350);
		setLocationRelativeTo(null);
		
		JLabel jlbName, jlbType, jlbPrice, jlbNumRest;
		JButton ensure, cancel;
		jlbName = new JLabel("ʳƷ���ƣ�");
		jlbType = new JLabel("ʳƷ���ࣺ");
		jlbPrice = new JLabel("ʳƷ���ۣ�");
		jlbNumRest = new JLabel("ʣ��������");
		jtfTFoodId = new JTextField();
		jtfName = new JTextField();
		jcbType = new JTextField();
		jtfPrice = new JTextField();
		jtfNumRest = new JTextField();
		ensure = new JButton("ȷ��");
		cancel = new JButton("ȡ��");

		jcbType.setEditable(true);

		jlbName.setBounds(60, 25, 60, 35);
		jlbType.setBounds(60, 80, 60, 35);
		jlbPrice.setBounds(60, 135, 60, 35);
		jlbNumRest.setBounds(60, 190, 90, 35);
		jtfName.setBounds(130, 25, 190, 35);
		jcbType.setBounds(130, 80, 190, 35);
		jtfPrice.setBounds(130, 135, 190, 35);
		jtfNumRest.setBounds(130, 190, 190, 35);
		ensure.setBounds(100, 250, 90, 40);
		cancel.setBounds(210, 250, 90, 40);

//		AddFoodListener addFoodListener = new AddFoodListener(jtfName, jcbType,
//				jtfPrice, jtfNumRest, cancel);
		EditFoodListener editFoodListener = new EditFoodListener(jtfTFoodId,jtfName, jcbType,
				jtfPrice, jtfNumRest, cancel);
		jtfName.addActionListener(editFoodListener);
		// jtfType.addActionListener(addFoodListener);
		jtfPrice.addActionListener(editFoodListener);
		jtfNumRest.addActionListener(editFoodListener);
		ensure.addActionListener(editFoodListener);
		cancel.addActionListener(editFoodListener);

		add(jlbName);
		add(jlbType);
		add(jlbPrice);
		add(jlbNumRest);
		add(jtfName);
		add(jcbType);
		add(jtfPrice);
		add(jtfNumRest);
		add(ensure);
		add(cancel);
	}

	public void open(Food food, int id) {
//		FoodDaoImpl foodDaoImpl = new FoodDaoImpl();
//		jtfName.setText("");
//		jcbType.removeAllItems();
//		jcbType.addItem("");
//		for (String item : foodDaoImpl.getTypeList()) {
//			jcbType.addItem(item);
//		}
//		jtfPrice.setText("");
//		jtfNumRest.setText("");
//		setVisible(true);
		jtfTFoodId.setText(String.valueOf(food.getId()));
		
		jtfName.setText(String.valueOf(food.getName()));
		jcbType.setText(food.getType());
		jtfPrice.setText(String.valueOf(food.getPrice()));
		jtfNumRest.setText(String.valueOf(food.getNumRest()));
		setVisible(true);
	}
}
