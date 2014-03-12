package unis.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import unis.modle.DependsOnExoticType;
import unis.services.connectionService.ConnectionService;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Controller
public class DefaultController {
	ComboPooledDataSource dataSources[];
	Map<String, ComboPooledDataSource> dataSourcesMap;
	List<String> keyList = new ArrayList<String>();
	Map<String, String> result = new HashMap<String, String>();
	ConnectionService connectionService;
	@RequestMapping({ "/", "/index" })
    public String route() {
        return "redirect:/login/user";
    }
	@RequestMapping("/login/{type}")
	public String login(@PathVariable String type,ModelMap map) throws Exception{
		/*s.clear();
		System.out.println(dataSourcesMap);
		for(ComboPooledDataSource dataSource : dataSources){
			Connection connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement("select sysdate from dual");
			ResultSet rs = ps.executeQuery();
			rs.next();
			s.add(rs.getString(1));
			rs.close();
			ps.close();
			connection.close();
		}
		map.addAttribute("lans", s);
		map.addAttribute("keyList", keyList);*/
		
		return "index";
	}

	@RequestMapping("/customEditor")
	@ResponseBody
	public Object assemble(DependsOnExoticType type){
		System.out.println(type.getType());
		return "success";
	}
	
	@RequestMapping("/getInfo")
	@ResponseBody
	public Object getInfo(){
		result.clear();
		Set<String> keys = dataSourcesMap.keySet();
		keyList.addAll(keys);
		System.out.println(keyList);
		connectionService.getInfo(result, keyList, dataSourcesMap);
		while(keyList.size() != 0){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	@Resource
	public void setDataSources(ComboPooledDataSource[] dataSources) throws Exception {
		this.dataSources = dataSources;
	}
	
	@Resource
	public void setDataSourcesMap(Map<String, ComboPooledDataSource> dataSourcesMap) {
		this.dataSourcesMap = dataSourcesMap;
	}
	
	@Resource
	public void setConnectionService(ConnectionService connectionService) {
		this.connectionService = connectionService;
	}
}
