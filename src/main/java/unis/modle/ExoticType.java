package unis.modle;

public class ExoticType {
	private String name;
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+"---"+name;
	}
}
