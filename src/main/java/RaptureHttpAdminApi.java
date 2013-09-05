
import org.apache.jmeter.testbeans.TestBean;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterVariables;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;
import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;

import java.util.concurrent.atomic.AtomicInteger;

import rapture.common.client.HttpSeriesApi;
import rapture.common.client.HttpLoginApi;
import rapture.common.client.HttpAdminApi;

public class RaptureHttpAdminApi extends AbstractSampler {

	private static final Logger log = LoggingManager.getLoggerForClass();
    private static final long serialVersionUID = 240L;

    private static final String PARTITION_NAME = "RaptureHttpAdmin.partition_name"; //$NON-NLS-1$
    
        
	@Override
    public SampleResult sample(Entry ignored) {
	
	    JMeterVariables vars = JMeterContextService.getContext().getVariables();
        HttpLoginApi loginObj = (HttpLoginApi)vars.getObject("raptureLoginObject");
      
		SampleResult result = new SampleResult();
        result.sampleStart(); // start stopwatch
    	        
    	try {
    	    HttpSeriesApi api = new HttpSeriesApi(loginObj); 
    	    log.debug("Logged into rapture");
    	    new HttpAdminApi(loginObj).createPartition("dev"); //TO DO: need to pass in the partition name from GUI 
    	    log.debug("Created partition");
    	    
            result.sampleEnd(); // stop stopwatch
            result.setSuccessful( true );
            result.setResponseMessage( "Successfully performed action ");
            result.setResponseCodeOK(); // 200 code
        } catch (Exception e) {
            result.sampleEnd(); // stop stopwatch
            result.setSuccessful( false );
            result.setResponseMessage( "Exception: " + e );
 
            // get stack trace as a String to return as document data
            java.io.StringWriter stringWriter = new java.io.StringWriter();
            e.printStackTrace( new java.io.PrintWriter( stringWriter ) );
            result.setResponseData( stringWriter.toString() );
            result.setDataType( org.apache.jmeter.samplers.SampleResult.TEXT );
            result.setResponseCode( "500" );
        } 
 
        return result;
		
	}
	
	
	/**
     * Set the Partition Name.
     *
     * @param 
     */
	public void setPartitionName(String partName){
		setProperty(PARTITION_NAME, partName);
	}
	
	public String getPartitionName(){
		return getPropertyAsString(PARTITION_NAME);
	}
	
	
	
}
