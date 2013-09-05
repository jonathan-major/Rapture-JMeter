import java.beans.PropertyDescriptor;
import java.util.ResourceBundle;

import org.apache.jmeter.testbeans.BeanInfoSupport;

public class RaptureHttpLoginApiBeanInfo extends BeanInfoSupport {
	    
	    //These must agree with the resources
	
	    final String USERNAME = "username";  
	    final String PASSWORD = "password"; 
	    final String URL = "url"; 
	
	    public RaptureHttpLoginApiBeanInfo() {
	        super(RaptureHttpLoginApi.class);
	    
	    createPropertyGroup("httpdata", 
                new String[] { USERNAME,PASSWORD,URL });

	    //is this needed?
	    //ResourceBundle rb = (ResourceBundle) getBeanDescriptor().getValue(RESOURCE_BUNDLE);
	    
        PropertyDescriptor p = property(USERNAME);
        p.setValue(NOT_UNDEFINED, Boolean.TRUE);
        p.setValue(DEFAULT, "");        //$NON-NLS-1$
        p.setValue(NOT_EXPRESSION, Boolean.TRUE);
        
        p = property(PASSWORD);
        p.setValue(NOT_UNDEFINED, Boolean.TRUE);
        p.setValue(DEFAULT, "");        //$NON-NLS-1$
        p.setValue(NOT_EXPRESSION, Boolean.TRUE);
        
        p = property(URL);
        p.setValue(NOT_UNDEFINED, Boolean.TRUE);
        p.setValue(DEFAULT, "");        //$NON-NLS-1$
        p.setValue(NOT_EXPRESSION, Boolean.TRUE);
        
        
	    
	}
}