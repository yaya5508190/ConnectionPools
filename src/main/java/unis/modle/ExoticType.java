package unis.modle;

public class ExoticType {
	private String name;
	
	public ExoticType(String upperCase) {
		// TODO Auto-generated constructor stub
		this.name = upperCase;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+"---"+name;
	}
}
