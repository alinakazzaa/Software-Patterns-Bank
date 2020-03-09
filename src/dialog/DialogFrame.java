package dialog;

public class DialogFrame implements Dialog {
	
	private String title, message;

	public DialogFrame(String title, String message) {
        setTitle(title);
        setMessage(message);
    }

	@Override
	public void showDialog() {
		// TODO Auto-generated method stub
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
