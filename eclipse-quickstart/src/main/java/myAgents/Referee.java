package myAgents;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import myAgents.RaceEnviromentService;
import uk.ac.imperial.presage2.core.environment.ActionHandlingException;
import uk.ac.imperial.presage2.core.environment.UnavailableServiceException;
import uk.ac.imperial.presage2.core.messaging.Input;
import uk.ac.imperial.presage2.core.participant.Participant;
import uk.ac.imperial.presage2.core.simulator.Scenario;
import uk.ac.imperial.presage2.util.location.Location;
import uk.ac.imperial.presage2.util.location.ParticipantLocationService;
import uk.ac.imperial.presage2.util.participant.AbstractParticipant;

public class Referee extends AbstractParticipant {
	private ParticipantLocationService mLocationService;
	private Location mLocation;
	private RaceEnviromentService mRaceService;
	private Scenario mScenario;
	private boolean mRaceStarted;
	private boolean mRaceFinished;
	
	private Map<UUID, MyAgent> mRacers;
	
	public Referee(UUID id, String name, Location myLocation, Scenario scenario)
	{
		super(id, name);
		this.mLocation = myLocation;
		mRaceStarted = false;
		mRaceFinished = false;
		mRacers = new HashMap<UUID, MyAgent>();
		mScenario = scenario;
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
			mRaceService = getEnvironmentService(RaceEnviromentService.class);
		} catch (UnavailableServiceException e) {
			logger.warn(e);
		}
	}
	
	@Override
	public void execute()
	{
	}
	
	private void SendRaceFinishedMessage()
	{
		
	}

}
