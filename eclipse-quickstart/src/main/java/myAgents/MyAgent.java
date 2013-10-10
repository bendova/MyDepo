package myAgents;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.List;

import myAgents.TimeStamp;
import uk.ac.imperial.presage2.core.Time;
import uk.ac.imperial.presage2.core.messaging.Input;
import uk.ac.imperial.presage2.core.messaging.Performative;
import uk.ac.imperial.presage2.core.network.BroadcastMessage;
import uk.ac.imperial.presage2.core.network.Message;
import uk.ac.imperial.presage2.core.network.UnicastMessage;
import uk.ac.imperial.presage2.core.network.NetworkAddress;
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
	private boolean mHelloMessageSent;
	private boolean mReplyMessageSent;
	
	public MyAgent(UUID id, String name, Location myLocation)
	{
		super(id, name);
		this.mLocation = myLocation;
		mHelloMessageSent = false;
		mReplyMessageSent = false;
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
		if(mHelloMessageSent == false)
		{
			SendHelloMessage();
			mHelloMessageSent = true;
		}
		List<Message<?>> myMessages = network.getMessages();
		for (Iterator<Message<?>> iterator = myMessages.iterator(); iterator.hasNext();) {
			Message<?> message = iterator.next();
			logger.info("execute() I have a message " + message.getData());
			if(mReplyMessageSent == false)
			{
				SendReplyMessageTo(message.getFrom());
				mReplyMessageSent = true;
			}
		}
	}
	
	private void SendHelloMessage()
	{
		logger.info("SendHelloMessage()");
		
		BroadcastMessage<String> msg = new BroadcastMessage<String>(Performative.INFORM, "", new TimeStamp(), network.getAddress(), "Hello!");
		network.sendMessage(msg);
	}
	
	private void SendReplyMessageTo(NetworkAddress replyTo)
	{
		logger.info("SendReplyMessageTo()");
		
		UnicastMessage<String> replyMsg = new UnicastMessage<String>(Performative.INFORM, "", new TimeStamp(), network.getAddress(), replyTo, "Good to meet you!");
		network.sendMessage(replyMsg);
	}
	
}
