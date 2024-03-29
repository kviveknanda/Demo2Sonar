package webapp.tier.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface Database {

	public String insertMsg() throws SQLException, Exception;
	public List<String> selectMsg() throws SQLException, Exception;
	public String deleteMsg() throws SQLException, Exception;
}
