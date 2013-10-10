package myAgents;

import java.util.UUID;

import org.apache.log4j.Logger;

import uk.ac.imperial.presage2.core.Action;
import uk.ac.imperial.presage2.core.environment.ActionHandler;
import uk.ac.imperial.presage2.core.environment.ActionHandlingException;
import uk.ac.imperial.presage2.core.messaging.Input;

public class InteractionHandler implements ActionHandler{
	protected final Logger logger = Logger.getLogger(InteractionHandler.class);
	
	@Override
	public boolean canHandle(Action action) {
	
		return false;
	}

	@Override
	public Input handle(Action action, UUID actor)
			throws ActionHandlingException {
		return null;
	}

}
