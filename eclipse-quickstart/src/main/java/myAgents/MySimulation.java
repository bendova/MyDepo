package myAgents;

import java.util.Set;
import java.util.HashSet;

import com.google.inject.AbstractModule;

import uk.ac.imperial.presage2.core.simulator.InjectedSimulation;
import uk.ac.imperial.presage2.core.simulator.Parameter;
import uk.ac.imperial.presage2.core.simulator.Scenario;
import uk.ac.imperial.presage2.core.util.random.Random;
import uk.ac.imperial.presage2.util.environment.AbstractEnvironmentModule;
import uk.ac.imperial.presage2.util.location.area.Area;
import uk.ac.imperial.presage2.util.location.Location;
import uk.ac.imperial.presage2.util.location.MoveHandler;
import uk.ac.imperial.presage2.util.location.ParticipantLocationService;
import uk.ac.imperial.presage2.util.network.NetworkModule;

public class MySimulation extends InjectedSimulation {

	@Parameter(name="areaSize")
	public int areaSize;
	
	@Parameter(name="agentsCount")
	public int agentsCount;
	
	public MySimulation(Set<AbstractModule> modules)
	{
		super(modules);
	}
	
	@Override
	protected Set<AbstractModule> getModules() {
		logger.info("MySimulation::getModules()");
		
		Set<AbstractModule> modules = new HashSet<AbstractModule>();
		
		// so we add a 2D area
		modules.add(Area.Bind.area2D(areaSize, areaSize));
		
		// an Enviroment with and MoveHandler and ParticipantLocationService
		modules.add(new AbstractEnvironmentModule()
				.addActionHandler(MoveHandler.class)
				.addParticipantEnvironmentService(ParticipantLocationService.class));
		
		// and no Network
		modules.add(NetworkModule.fullyConnectedNetworkModule());
		
		return modules;
	}

	@Override
	protected void addToScenario(Scenario s) {
		int initialX;
		int initialY;
		Location initialLocation;
		initialX = 0;
		initialY = 0;
		initialLocation = new Location(initialX, initialY);
		for (int i = 0; i < agentsCount; i++) {
			s.addParticipant(new MyAgent(Random.randomUUID(), "myAgent"+i, initialLocation));
		}
		s.addParticipant(new Referee(Random.randomUUID(), "referee", initialLocation));
	}
}
