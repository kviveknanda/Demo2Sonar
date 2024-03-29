package webapp.tier.healthcheck;

import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import webapp.tier.service.HazelcastService;

@Liveness
@ApplicationScoped
public class LivenessHealthCheck implements HealthCheck {

	private static final Logger LOG = Logger.getLogger(LivenessHealthCheck.class.getSimpleName());

	@Override
	public HealthCheckResponse call() {
		HazelcastService hassvc = new HazelcastService();
		if (hassvc.isActive()) {
			LOG.fine("Liveness: UP");
			return HealthCheckResponse.up("Cache Server connection health check");
		} else {
			LOG.warning("Liveness: DOWN");
			return HealthCheckResponse.down("Cache Server connection health check");
		}
	}

}
