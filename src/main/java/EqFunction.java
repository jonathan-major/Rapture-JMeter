import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
 
import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;
 
public class EqFunction extends AbstractFunction {
 
    private final static String NAME = "__eq";
    private final static ArrayList<String> DESC = new ArrayList<String>(2);
 
    private CompoundVariable a;
    private CompoundVariable b;
 
    static {
        DESC.add("a");
        DESC.add("b");
    }
 
    @Override
    public String getReferenceKey() {
        return NAME;
    }
 
    public List<String> getArgumentDesc() {
        return DESC;
    }
 
    @Override
    public String execute(SampleResult previousResult, Sampler currentSampler) throws InvalidVariableException {
 
        String a = this.a.execute();
        String b = this.b.execute();
 
        return a.equals(b) ? "true" : "false";
 
    }
 
    @Override
    public void setParameters(Collection<CompoundVariable> parameters) throws InvalidVariableException {
 
        if (parameters.size() > 2) throw new InvalidVariableException("Not enough parameters for " + NAME);
 
        Iterator<CompoundVariable> it = parameters.iterator();
        this.a = it.next();
        this.b = it.next();
 
    }
 
}