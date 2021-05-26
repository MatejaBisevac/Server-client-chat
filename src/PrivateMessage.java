import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;

public class PrivateMessage extends Message{
	private static final long serialVersionUID = 1L;
	
	private String toRecipient;

	public String getToRecipient() {
		return toRecipient;
	}

	public void setToRecipient(String toRecipient) {
		this.toRecipient = toRecipient;
	}
	public PrivateMessage() {
		super();
	}
	public PrivateMessage(String text, String from , String toRecipient) {
		super(text,from);
		this.toRecipient = toRecipient;
	}
	
	@Override
	public String toString() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XMLEncoder encoder = new XMLEncoder(baos);
		encoder.writeObject(this);
		encoder.close();
		String s = new String(baos.toByteArray());
		s = s.replace("\n"," ");
		return s;
	}
}
