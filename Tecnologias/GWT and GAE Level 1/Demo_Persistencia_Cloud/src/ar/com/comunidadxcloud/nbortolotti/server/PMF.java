package ar.com.comunidadxcloud.nbortolotti.server;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public class PMF {
	  private static final PersistenceManagerFactory pmfInstance = JDOHelper.getPersistenceManagerFactory("transactions-optional");

	    private PMF() {}

	    public static PersistenceManagerFactory get() 
	    {
	        return pmfInstance;
	    }

}
