package unis.services.connectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionTask extends Thread {

	Map<String, String> map;
	List<String> keyList;
	Map<String, ComboPooledDataSource> connectionMap;
	String nowDb;
	public ConnectionTask(Map<String, String> map,List<String> keyList,Map<String, ComboPooledDataSource> connectionMap) {
		super();
		// TODO Auto-generated constructor stub
		this.map = map;
		this.keyList = keyList;
		this.connectionMap = connectionMap;;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("New Thread Start");
		String flag = "0";
		synchronized (map) {
			System.out.println(map);
			for(String dbName : keyList){
				if(map.get(dbName)== null){
					this.nowDb = dbName;
					map.put(nowDb, "");
					flag = "1";
					break;
				}
			}
			if("1".equals(flag)){
				Connection connection = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				try{
					ComboPooledDataSource dataSource = connectionMap.get(nowDb);
					connection = dataSource.getConnection();
					ps = connection.prepareStatement("select sysdate from dual");
					rs = ps.executeQuery();
					rs.next();
					map.put(nowDb, rs.getString(1));
					Thread ConnectionTask = new ConnectionTask(map, keyList, connectionMap);
					ConnectionTask.start();
				}catch (Exception e) {
					// TODO: handle exception
					map.put(nowDb, "error");
				}finally{
					try {
						rs.close();
						ps.close();
						connection.close();
						keyList.remove(nowDb);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else{
				return;
			}
		}
		
	}

}
