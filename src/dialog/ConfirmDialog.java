package dialog;

import javax.swing.JOptionPane;

public class ConfirmDialog extends DialogFrame {

	public ConfirmDialog(String title, String message) {
		super(title, message);
	}
	
	@Override
	public void showDialog() {
		this.setReply(JOptionPane.showConfirmDialog(null, this.getMessage(), this.getTitle(),
										JOptionPane.YES_NO_OPTION));
	}
}
