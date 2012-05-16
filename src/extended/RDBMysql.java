package extended;

import core.GTerror;
import gtnative.GT;

public class RDBMysql extends RDB {

	public RDBMysql(String server,
					int port,
					String database,
					String username,
					String password) {
		
		GTerror err = new GTerror();
		super.rdb_ptr = GT.INSTANCE.gt_rdb_mysql_new(server, port, database, username, password, err.to_ptr());
		if(err.is_set()){
			System.out.println(err.get_err());
		}
	}

}
