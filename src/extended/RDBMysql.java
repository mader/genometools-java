/*
  Copyright (c) 2012 Malte Mader <mader@zbh.uni-hamburg.de>
  Copyright (c) 2012 Center for Bioinformatics, University of Hamburg

  Permission to use, copy, modify, and distribute this software for any
  purpose with or without fee is hereby granted, provided that the above
  copyright notice and this permission notice appear in all copies.

  THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
  WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
  MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
  ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
  WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
  ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
  OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
*/

package extended;

import core.GTerror;
import core.GTerrorJava;
import gtnative.GT;

public class RDBMysql extends RDB {

	/**
	 * Establish a new Database Connection. The connection is closed when the
	 * object is deleted. The deletion needs to be executed manually
	 * to ensure that the c side memory will be freed.  
	 * 
	 * @param server
	 * @param port
	 * @param database
	 * @param username
	 * @param password
	 * @throws GTerrorJava
	 */
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
