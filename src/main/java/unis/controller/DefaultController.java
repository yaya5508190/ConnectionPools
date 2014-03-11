package unis.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import unis.modle.DependsOnExoticType;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Controller
public class DefaultController {
	ComboPooledDataSource dataSources[];
	ArrayList<String> s = new ArrayList<String>();
	@RequestMapping({ "/", "/index" })
    public String route() {
        return "redirect:/login/user";
    }
	@RequestMapping("/login/{type}")
	public String login(@PathVariable String type) throws Exception{
		/*s.clear();
		for(ComboPooledDataSource dataSource : dataSources){
			Connection connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement("select userenv('language') from dual");
			ResultSet rs = ps.executeQuery();
			rs.next();
			s.add(rs.getString(1));
			rs.close();
			ps.close();
			connection.close();
		}
		map.addAttribute("lans", s);*/
		
		return "index";
	}

	@RequestMapping("/customEditor")
	@ResponseBody
	public Object assemble(DependsOnExoticType type){
		System.out.println(type.getType());
		return type;
	}
	@Resource
	public void setDataSources(ComboPooledDataSource[] dataSources) throws Exception {
		this.dataSources = dataSources;
	}
}
