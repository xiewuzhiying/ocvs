package io.github.xiewuzhiying.ocvs.bus.devices.rpc.item;

import io.github.xiewuzhiying.ocvs.ship.QueuedForcesApplier;
import li.cil.oc2r.api.bus.device.object.Callback;
import li.cil.oc2r.api.bus.device.object.Parameter;
import net.minecraft.server.level.ServerLevel;
import org.joml.Vector3d;
import org.valkyrienskies.core.api.ships.ServerShip;
import org.valkyrienskies.mod.common.VSGameUtilsKt;

import static org.valkyrienskies.mod.common.ValkyrienSkiesMod.vsCore;

public class ExtendedShipHandlerDevice extends ShipHandlerDevice{
    private final ServerLevel level;
    private final ServerShip ship;

    public ExtendedShipHandlerDevice(ServerShip serverShip, ServerLevel serverLevel) {
        super(serverShip);
        this.level = serverLevel;
        this.ship = serverShip;
    }

    @Callback
    public void applyInvariantForce(@Parameter("xForce") Double xForce, @Parameter("yForce") Double yForce, @Parameter("zForce") Double zForce) {
        QueuedForcesApplier.getOrCreateControl(this.ship).applyInvariantForce(new Vector3d(xForce, yForce, zForce));
    }

    @Callback
    public void applyInvariantTorque(@Parameter("xTorque") Double xTorque, @Parameter("yTorque") Double yTorque, @Parameter("zTorque") Double zTorque) {
        QueuedForcesApplier.getOrCreateControl(this.ship).applyInvariantTorque(new Vector3d(xTorque, yTorque, zTorque));
    }

    @Callback
    public void applyInvariantForceToPos(@Parameter("xForce") Double xForce, @Parameter("yForce") Double yForce, @Parameter("zForce") Double zForce, @Parameter("xPos") Double xPos, @Parameter("yPos") Double yPos, @Parameter("zPos") Double zPos) {
        QueuedForcesApplier.getOrCreateControl(this.ship).applyInvariantForceToPos(new Vector3d(xForce, yForce, zForce), new Vector3d(xPos, yPos, zPos));
    }

    @Callback
    public void applyRotDependentForce(@Parameter("xForce") Double xForce, @Parameter("yForce") Double yForce, @Parameter("zForce") Double zForce) {
        QueuedForcesApplier.getOrCreateControl(this.ship).applyRotDependentForce(new Vector3d(xForce, yForce, zForce));
    }

    @Callback
    public void applyRotDependentTorque(@Parameter("xTorque") Double xTorque, @Parameter("yTorque") Double yTorque, @Parameter("zTorque") Double zTorque) {
        QueuedForcesApplier.getOrCreateControl(this.ship).applyRotDependentTorque(new Vector3d(xTorque, yTorque, zTorque));
    }

    @Callback
    public void applyRotDependentForceToPos(@Parameter("xForce") Double xForce, @Parameter("yForce") Double yForce, @Parameter("zForce") Double zForce, @Parameter("xPos") Double xPos, @Parameter("yPos") Double yPos, @Parameter("zPos") Double zPos) {
        QueuedForcesApplier.getOrCreateControl(this.ship).applyRotDependentForceToPos(new Vector3d(xForce, yForce, zForce), new Vector3d(xPos, yPos, zPos));
    }

    @Callback
    public void setStatic(Boolean b) {
        QueuedForcesApplier.getOrCreateControl(this.ship).setStatic(b);
    }

    @Callback
    public void setScale(Double scale) {
        vsCore.scaleShip(VSGameUtilsKt.getShipObjectWorld(level), ship, scale);
    }

    /*@Callback
    public <T> void teleport(@Parameter("teleportData") Map<String, T> args) {

        var pos = this.ship.getTransform().getPositionInWorld();
        if (args.containsKey("pos"))
            pos = TableUtils. getVectorFromTable(input, "pos")

        var rot = this.ship.transform.shipToWorldRotation
        if (input.containsKey("rot"))
            rot = getQuaternionFromTable(input).normalize(Quaterniond())

        var vel = this.ship.velocity
        if (input.containsKey("vel"))
            vel = getVectorFromTable(input, "vel")

        var omega = this.ship.omega
        if (input.containsKey("omega"))
            omega = getVectorFromTable(input, "omega")

        var dimension: String? = null
        if (input.containsKey("dimension"))
            dimension = (input["dimension"] ?: throwMalformedSectionError("dimension")) as String

        var scale = this.ship.transform.shipToWorldScaling.x()
        if (input.containsKey("scale"))
            scale = (input["scale"] ?: throwMalformedSectionError("scale")) as Double

        ShipTeleportData teleportData = new ShipTeleportDataImpl(pos, rot, vel, omega, dimension, scale);

        println("Rot: ${teleportData.newRot}\n")

        //vsCore.teleportShip(this.level.shipObjectWorld, this.ship, teleportData)
        this.level.shipObjectWorld.teleportShip(this.ship, teleportData)
    }*/
}
