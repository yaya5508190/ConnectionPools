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
	String sql;
	public ConnectionTask(Map<String, String> map,List<String> keyList,Map<String, ComboPooledDataSource> connectionMap,String sql) {
		super();
		// TODO Auto-generated constructor stub
		this.map = map;
		this.keyList = keyList;
		this.connectionMap = connectionMap;;
		this.sql = sql;
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
					ps = connection.prepareStatement(sql);
					rs = ps.executeQuery();
					rs.next();
					map.put(nowDb, rs.getString(1));
					
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					System.out.println(nowDb+"error===============================================================================");
					map.put(nowDb, "error");
				}finally{
					try {
						rs.close();
						ps.close();
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Thread ConnectionTask = new ConnectionTask(map, keyList, connectionMap, sql);
					ConnectionTask.start();
				}
			}else{
				return;
			}
		}
		
	}

}
