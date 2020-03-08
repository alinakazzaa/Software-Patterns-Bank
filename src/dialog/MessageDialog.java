package dialog;

import javax.swing.JOptionPane;

public class MessageDialog extends DialogFrame {
	
	
	public MessageDialog(String title, String message) {
		super(title, message);
	}
	
	public void showDialog() {
		JOptionPane.showMessageDialog(null, this.getMessage(), this.getTitle(), JOptionPane.INFORMATION_MESSAGE);
	}

}
