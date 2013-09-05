
import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testbeans.*;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.gui.JLabeledTextField;
import org.apache.jorphan.gui.layout.VerticalLayout;
import org.apache.jmeter.gui.util.*;
import org.apache.jorphan.gui.JLabeledChoice;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RaptureHttpAdminApiGUI extends AbstractSamplerGui implements  ChangeListener {
	
	private static final Logger log = LoggingManager.getLoggerForClass();
	
	private static final long serialVersionUID = 240L;

	private final JLabeledTextField partitionField = new JLabeledTextField(JMeterUtils.getResString("rapture_admin_api_partition_label")); 
	private JLabeledChoice methodChoice = null;
	private JPanel mainPanel = new JPanel();
	
	public RaptureHttpAdminApiGUI() {
        init();
    }
	
	@Override
    public TestElement createTestElement() {
		RaptureHttpAdminApi s = new RaptureHttpAdminApi();
		this.configureTestElement(s);
        this.modifyTestElement(s);
        return s;
    }
	
	
	@Override
    public void modifyTestElement(TestElement te) {
		RaptureHttpAdminApi sampler = (RaptureHttpAdminApi) te;

        this.configureTestElement(sampler);
        sampler.setPartitionName(partitionField.getText());
        
    }
	
	 /*
     * Helper method to set up the GUI screen
     */
    private void init() {
        // Standard setup
        setLayout(new BorderLayout(0, 5));
        setBorder(makeBorder());
        add(makeTitlePanel(), BorderLayout.NORTH); // Add the standard title

        // Specific setup
        mainPanel.setLayout(new BorderLayout(0, 5));
        mainPanel.add(createModePanel(), BorderLayout.NORTH);
        this.add(mainPanel);
    }
    
	@Override
    public String getLabelResource() {
        return "rapture_admin_api_title"; // $NON-NLS-1$
    }
		
	public void resetPanelToDefault(){
		
	}
	
	//JM 08/15/2013
	//haha wise words!
	//"Looking through the contents of a container probably indicates bad design. Tell the GUI what to do, don't interrogate its state."
	
	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == methodChoice){
			//get the chosen value from dropdown and decide which panel to display
			String value = methodChoice.getText();
			log.debug("Panel chosen: " + value);
			
			if(value == ""){
				Component[] comps = this.getComponents();
				
				int i = this.getComponentCount();
				Component comp = this.getComponent(i-1);
				
				
				for (i = 0; i < comps.length; i++) {
					log.debug("-->" + comps[i].getClass().toString());
				}
				
				
				
				log.debug("Component count is: " + i);
				//log.debug("Component name is : " + this.getComponent(i-1).getName());
				//this.remove(0);
				this.validate();
	
			} else {
				
				switch(value){
					case "createPartition": 
						mainPanel.add(createPartitionPanel());
						break;
					case "doesPartitionExist": 
						mainPanel.add(createPartitionExistPanel());
						break;
					case "getPartition": 
						mainPanel.add(createGetPartitionPanel());
					    break;
					default:
						break;
				}
				
				this.validate();
				log.debug("UI Refreshed");
			}
		}
	}
	
	private final JPanel createModePanel() {
		
		JPanel methodPanel = new JPanel();
		methodPanel.setBorder(BorderFactory.createTitledBorder("Choose Admin API Method"));
		methodPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		methodChoice = new JLabeledChoice();
		methodChoice.setLabel("API");
		methodChoice.addValue("");
		methodChoice.addValue("createPartition");
		methodChoice.addValue("doesPartitionExist");
		methodChoice.addValue("getPartition");
		methodChoice.addChangeListener(this);
		methodPanel.add(methodChoice);
		
		return methodPanel;
	}
	
	 /*
     * 
     *
     * @return the panel for entering the data
     */
	private final JPanel createPartitionPanel() {
		JPanel partPanel = new JPanel();
    	partPanel.setBorder(BorderFactory.createTitledBorder("Create New Partition"));
    	partPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        partPanel.add(partitionField);
        log.debug("Created new partition panel.");

        return partPanel;
    }

	private final JPanel createGetPartitionPanel(){
		JPanel getPanel = new JPanel();
		getPanel.setBorder(BorderFactory.createTitledBorder("Get Partition Data"));
		getPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    	
		
		log.debug("Created get partition panel.");
		return getPanel;
	}
	
	private final JPanel createPartitionExistPanel(){
		JPanel createPanel = new JPanel();
		createPanel.setBorder(BorderFactory.createTitledBorder("Partition Exists"));
		createPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		log.debug("Created partition exists panel.");
		return createPanel;
	}
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void clearGui() {
        super.clearGui();
        //methodChoice.setValues(new String[0]);
        partitionField.setText("");

    }

}
