package myAgents;

import myAgents.MyAgent;

import java.util.Collection;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.drools.runtime.ObjectFilter;
import org.drools.runtime.StatefulKnowledgeSession;

import com.google.inject.Inject;

import uk.ac.imperial.presage2.core.environment.EnvironmentService;
import uk.ac.imperial.presage2.core.environment.EnvironmentSharedStateAccess;
import uk.ac.imperial.presage2.core.event.EventBus;

public class RaceEnviromentService extends EnvironmentService{
	private final Logger logger = Logger.getLogger(this.getClass());
	private final StatefulKnowledgeSession session;
	private Map<UUID, MyAgent> mRacers;
	
	@Inject
	protected RaceEnviromentService(EnvironmentSharedStateAccess sharedState, StatefulKnowledgeSession session, EventBus eb) 
	{
		super(sharedState);
		this.session = session;
		eb.subscribe(this);
		
		mRacers = new HashMap<UUID, MyAgent>();
    }
	
	public Map<UUID, MyAgent> getRacers()
	{
		if(mRacers.isEmpty())
		{
			Collection<Object> rawAgents = session.getObjects(new ObjectFilter() {
						@Override
						public boolean accept(Object object) {
							return object instanceof MyAgent;
						}
					});
			for (Object pObj : rawAgents) {
				MyAgent p = (MyAgent) pObj;
				mRacers.put(p.getID(), p);
			}
		}
		return mRacers;
	}

}
