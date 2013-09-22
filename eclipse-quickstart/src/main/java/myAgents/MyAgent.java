package myAgents;

import java.util.Set;
import java.util.UUID;

import uk.ac.imperial.presage2.core.messaging.Input;
import uk.ac.imperial.presage2.core.util.random.Random;
import uk.ac.imperial.presage2.util.location.Location;
import uk.ac.imperial.presage2.util.location.Move;
import uk.ac.imperial.presage2.util.location.ParticipantLocationService;
import uk.ac.imperial.presage2.util.participant.AbstractParticipant;
import uk.ac.imperial.presage2.core.environment.ActionHandlingException;
import uk.ac.imperial.presage2.core.environment.ParticipantSharedState;
import uk.ac.imperial.presage2.core.environment.UnavailableServiceException;

public class MyAgent extends AbstractParticipant {
	
	private Location mLocation;
	private ParticipantLocationService mLocationService;
	
	public MyAgent(UUID id, String name, Location myLocation)
	{
		super(id, name);
		this.mLocation = myLocation;
	}
	
	@Override
	protected void processInput(Input arg0) {
		// TODO Auto-generated method stub

	}
	
	@Override
	protected Set<ParticipantSharedState> getSharedState() {
		Set<ParticipantSharedState> setShareStates = super.getSharedState();
		setShareStates.add(ParticipantLocationService.createSharedState(getID(), mLocation));
		return setShareStates;
	}
	
	@Override
	public void initialise()
	{
		super.initialise();
		try {
			mLocationService = getEnvironmentService(ParticipantLocationService.class);
		} catch (UnavailableServiceException e) {
			logger.warn(e);
		}
	}
	
	@Override
	public void execute()
	{
		mLocation = mLocationService.getAgentLocation(getID());
		
		logger.info("MyAgent::execute() mLocation = " + mLocation);
		
		// create a random move
		int dx = Random.randomInt(2) - 1;
		int dy = Random.randomInt(2) - 1;
		Move move = new Move(dx, dy);
		
		try {
			environment.act(move, getID(), authkey);
		}
		catch (ActionHandlingException e) {
			logger.warn("MyAgent::execute() Error while trying to move!"); 				
		}
	}

}
