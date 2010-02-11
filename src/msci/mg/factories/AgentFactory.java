package msci.mg.factories;

import msci.mg.agents.AbstractAgent;
import org.apache.commons.collections15.Factory;

/**
 * The AgentFactory interface defines the method create() which should
 * build and return a derivative of AbstractAgent.
 * @author tobyclemson
 */
public abstract class AgentFactory implements Factory<AbstractAgent>{

    public abstract AbstractAgent create();

}