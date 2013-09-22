package myAgents;

import java.util.Map;
import java.util.UUID;
import java.util.Vector;

import messages.StartRaceMessage;


import uk.ac.imperial.presage2.core.environment.UnavailableServiceException;
import uk.ac.imperial.presage2.core.messaging.Input;
import uk.ac.imperial.presage2.core.messaging.Performative;
import uk.ac.imperial.presage2.core.network.Message;
import uk.ac.imperial.presage2.util.location.Location;
import uk.ac.imperial.presage2.util.location.ParticipantLocationService;
import uk.ac.imperial.presage2.util.network.NetworkModule;
import uk.ac.imperial.presage2.util.participant.AbstractParticipant;

public class Referee extends AbstractParticipant {
	private ParticipantLocationService mLocationService;
	private Location mLocation;
	
	public Referee(UUID id, String name, Location myLocation)
	{
		super(id, name);
		this.mLocation = myLocation;
	}

	@Override
	protected void processInput(Input in) {
		// TODO Auto-generated method stub
		
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
		StartRaceMessage<Integer> startRaceMessage = new StartRaceMessage<Integer>(Performative.INFORM, network.getAddress(), getTime());
		network.sendMessage(startRaceMessage);
	}

}
