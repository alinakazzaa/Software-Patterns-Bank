package dialog;

import javax.swing.JOptionPane;

public class ConfirmDialog extends DialogFrame {

	private int reply;
	
	public ConfirmDialog(String title, String message) {
		super(title, message);
		showDialog();
	}
	
	@Override
	public void showDialog() {
		setReply(JOptionPane.showConfirmDialog(null, this.getMessage(), this.getTitle(),
										JOptionPane.YES_NO_OPTION));
	}
	
	public int getReply() {
		return reply;
	}

	public void setReply(int reply) {
		this.reply = reply;
	}
}
