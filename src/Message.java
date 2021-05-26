import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class Message implements Serializable{
	private String text;
	private String from;
	private static final long serialVersionUID = 1L;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
	public Message() {}
	public Message(String text, String from) {
		this.from=from;
		this.text =text;
	}
	
	//ovo radi za bilo koji objekat
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
