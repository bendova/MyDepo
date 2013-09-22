package messages;

import uk.ac.imperial.presage2.core.Time;
import uk.ac.imperial.presage2.core.messaging.Performative;
import uk.ac.imperial.presage2.core.network.Message;
import uk.ac.imperial.presage2.core.network.NetworkAddress;

public class StartRaceMessage<T> extends Message<T> {

	public StartRaceMessage(Performative performative, NetworkAddress from,
			Time timestamp) {
		super(performative, from, timestamp);
	}
}
