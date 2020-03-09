package dialog;

import javax.swing.JOptionPane;

public class InputDialog extends DialogFrame {

	private Object input;
	
	public InputDialog(String title, String message) {
		super(title, message);
		showDialog();
	}
	
	public void showDialog() {
		setInput(JOptionPane.showInputDialog(null, this.getMessage()));
	}
	
    public Object getInput() {
		return input;
	}

	public void setInput(Object input) {
		this.input = input;
	}

}
