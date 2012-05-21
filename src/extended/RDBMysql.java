package extended;

import core.GTerror;
import core.GTerrorJava;
import gtnative.GT;

public class RDBMysql extends RDB {

	public RDBMysql(String server,
					int port,
					String database,
					String username,
					String password) throws GTerrorJava {
		
		GTerror err = new GTerror();
		super.rdb_ptr = GT.INSTANCE.gt_rdb_mysql_new(server, port, database, username, password, err.to_ptr());
		if(err.is_set()){
			throw new GTerrorJava(err.get_err());
		}
	}

}
