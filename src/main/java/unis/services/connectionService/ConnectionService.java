package unis.services.connectionService;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Service("connectionService")
public class ConnectionService {
	ArrayList<String> list = new ArrayList<String>();
	
	public void getInfo(Map<String, String> map,List<String> keyList,Map<String, ComboPooledDataSource> connectionMap){
		
		Thread ConnectionTask1 = new ConnectionTask(map, keyList, connectionMap);
		Thread ConnectionTask2 = new ConnectionTask(map, keyList, connectionMap);
		Thread ConnectionTask3 = new ConnectionTask(map, keyList, connectionMap);
		Thread ConnectionTask4 = new ConnectionTask(map, keyList, connectionMap);
		
		ConnectionTask1.start();
		ConnectionTask2.start();
		ConnectionTask3.start();
		ConnectionTask4.start();
	}
}
