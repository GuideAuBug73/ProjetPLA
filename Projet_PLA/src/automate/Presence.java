package automate;
import principal.Entity;
import principal.Spawn;

public class Presence extends _Condition {
	public Presence() {
	}

	@Override
	public boolean eval(Entity e) {
		assert (e instanceof Spawn);
		Spawn m_spawn = (Spawn) e;
		return m_spawn.presence;
	}
}