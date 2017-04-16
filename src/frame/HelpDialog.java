package frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import listener.ConfigListener;
import dao.ConfigDao;
import entity.Config;
/**
 * ���������Ĵ���
 */
public class HelpDialog extends JDialog {
	private static final long serialVersionUID = 5016876596940564305L;
	private static HelpDialog configDialog;
	private JTextField jtfName, jtfDiscount, jtfHours, jtfHoursAdded, jtfBg;

	public static HelpDialog instance() {
		if (configDialog == null)
			configDialog = new HelpDialog();
		return configDialog;
	}

	public HelpDialog() {
		super(MainFrame.instance(), "����", true);
		setLayout(null);
		setSize(400, 420);
		setLocationRelativeTo(null);
		configDialog = this;
		JLabel jlbName = new JLabel("�����ʹ���ֲᣬлл��");
		
		jlbName.setBounds(60, 60, 120, 70);

		add(jlbName);

	}

	public void open() {
		Config config = ConfigDao.instance().getConfig();
	
		
		setVisible(true);
	}
}
