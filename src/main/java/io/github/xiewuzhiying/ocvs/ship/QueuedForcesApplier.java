package io.github.xiewuzhiying.ocvs.ship;

import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3dc;
import org.valkyrienskies.core.api.ships.PhysShip;
import org.valkyrienskies.core.api.ships.ServerShip;
import org.valkyrienskies.core.api.ships.ShipForcesInducer;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

public class QueuedForcesApplier implements ShipForcesInducer {
    private final ConcurrentLinkedQueue<Vector3dc> invForces = new ConcurrentLinkedQueue<>();

    private final ConcurrentLinkedQueue<Pair<Vector3dc, Vector3dc>> invPosForces = new ConcurrentLinkedQueue<>();

    private final ConcurrentLinkedQueue<Vector3dc> invTorques = new ConcurrentLinkedQueue<>();

    private final ConcurrentLinkedQueue<Vector3dc> rotForces = new ConcurrentLinkedQueue<>();

    private final ConcurrentLinkedQueue<Pair<Vector3dc, Vector3dc>> rotPosForces = new ConcurrentLinkedQueue<>();

    private final ConcurrentLinkedQueue<Vector3dc> rotTorques = new ConcurrentLinkedQueue<>();

    private volatile boolean toBeStatic = false;

    private volatile boolean toBeStaticUpdated = false;

    @Override
    public void applyForces(@NotNull PhysShip physShip) {
        pollUntilEmpty(invForces, physShip::applyInvariantForce);
        pollUntilEmpty(invTorques, physShip::applyInvariantTorque);
        pollUntilEmpty(invPosForces, (pair) -> physShip.applyInvariantForceToPos(pair.getLeft(), pair.getRight()));
        pollUntilEmpty(rotForces, physShip::applyRotDependentForce);
        pollUntilEmpty(rotTorques, physShip::applyRotDependentTorque);
        pollUntilEmpty(rotPosForces, (pair) -> physShip.applyRotDependentForceToPos(pair.getLeft(), pair.getRight()));

        if (toBeStaticUpdated) {
            physShip.setStatic(toBeStatic);
            toBeStaticUpdated = false;
        }
    }

    public void applyInvariantForce(Vector3dc force) {
        invForces.add(force);
    }

    public void applyInvariantTorque(Vector3dc torque) {
        invTorques.add(torque);
    }

    public void applyInvariantForceToPos(Vector3dc force, Vector3dc pos) {
        invPosForces.add(Pair.of(force, pos));
    }

    public void applyRotDependentForce(Vector3dc force) {
        rotForces.add(force);
    }

    public void applyRotDependentTorque(Vector3dc torque) {
        rotTorques.add(torque);
    }

    public void  applyRotDependentForceToPos(Vector3dc force, Vector3dc pos) {
        rotPosForces.add(Pair.of(force, pos));
    }

    public void  setStatic(Boolean b) {
        toBeStatic = b;
        toBeStaticUpdated = true;
    }

    public static <T> void pollUntilEmpty(ConcurrentLinkedQueue<T> queue, Consumer<T> consumer) {
        T element;
        while ((element = queue.poll()) != null) {
            consumer.accept(element);
        }
    }

    public static QueuedForcesApplier getOrCreateControl(ServerShip ship) {
        var control = ship.getAttachment(QueuedForcesApplier.class);
        if (control == null) {
            control = new QueuedForcesApplier();
            ship.saveAttachment(QueuedForcesApplier.class, control);
        }
        return control;
    }
}
