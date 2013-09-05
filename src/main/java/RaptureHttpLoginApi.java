
import org.apache.jmeter.testbeans.TestBean;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;
import org.apache.jmeter.samplers.Sampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jorphan.util.JOrphanUtils;
import org.apache.jmeter.threads.JMeterContext;
import org.apache.jmeter.threads.JMeterVariables;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.config.ConfigElement;
import org.apache.jmeter.config.ConfigTestElement;
import org.apache.jmeter.engine.util.NoConfigMerge;
import org.apache.jmeter.testelement.TestStateListener;




//rapture related imports
import rapture.common.client.HttpLoginApi;
import rapture.common.client.SimpleCredentialsProvider;

public class RaptureHttpLoginApi extends ConfigTestElement
	implements TestBean, TestStateListener, NoConfigMerge    {

    private static final Logger log = LoggingManager.getLoggerForClass();
    private static final long serialVersionUID = 232L;
    private transient String username;
    private transient String password;
    private transient String url;
 
    @Override
	public void testStarted() {
    	//start the jmeter time for this action
        String raptureUrl = getUrl();
        String raptureUser = getUsername();
        String rapturePass = getPassword();
        
        SampleResult result = new SampleResult();
        result.sampleStart(); // start stopwatch
    	
    	try {
            
            //create the rapture credentials and login
            HttpLoginApi raptureLogin = new HttpLoginApi(raptureUrl, new SimpleCredentialsProvider(raptureUser, rapturePass));
            raptureLogin.login();
            
            //end the sampler timer
            result.sampleEnd(); // stop stopwatch
            result.setSuccessful( true );
            result.setResponseMessage("Successfully logged into rapture instance " + raptureUrl);
            result.setResponseCodeOK(); // 200 code
            
            //add  the connection  object into jmeter's context. Is this the correct approach?
            JMeterVariables vars = JMeterContextService.getContext().getVariables();
            vars.putObject( "raptureLoginObject", raptureLogin );
            JMeterContextService.getContext().setVariables( vars );
            
        } catch (Exception e) {
            result.sampleEnd(); // stop stopwatch
            result.setSuccessful( false );
            result.setResponseMessage( "Can't login to Rapture instance " + raptureUrl + "Exception: " + e );
 
            // get stack trace as a String to return as document data
            java.io.StringWriter stringWriter = new java.io.StringWriter();
            e.printStackTrace( new java.io.PrintWriter( stringWriter ) );
            result.setResponseData( stringWriter.toString() );
            result.setDataType( org.apache.jmeter.samplers.SampleResult.TEXT );
            result.setResponseCode( "500" );
            
        }
    	
    }
    
    @Override
	public void testEnded() {
    	//may want to put some logging in here...
    }
    
    @Override
	public void testEnded(String host) {
		this.testEnded();
	}
    
    @Override
	public void testStarted(String host) {
		this.testStarted();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		//build a string 
		return builder.toString();
	}
    
    //Sets up the plugins GUI elements.

    /**
     * @return Returns the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * @return Returns the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return Returns the url.
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            The url to set.
     */
    public void setUrl(String url) {
        this.url = url;
    }
    

}