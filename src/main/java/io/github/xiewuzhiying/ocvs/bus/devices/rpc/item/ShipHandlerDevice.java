package io.github.xiewuzhiying.ocvs.bus.devices.rpc.item;

import io.github.xiewuzhiying.ocvs.util.TableUtils;
import li.cil.oc2r.api.bus.device.object.Callback;
import li.cil.oc2r.api.bus.device.object.NamedDevice;
import li.cil.oc2r.api.bus.device.object.Parameter;
import li.cil.oc2r.common.bus.device.util.IdentityProxy;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4dc;
import org.joml.Vector4d;
import org.valkyrienskies.core.api.ships.ServerShip;

import java.util.*;

import static java.lang.Math.asin;
import static java.lang.Math.atan2;

public class ShipHandlerDevice extends IdentityProxy<ServerShip> implements NamedDevice {
    private final ServerShip ship;

    public ShipHandlerDevice(final ServerShip serverShip) {
        super(serverShip);
        this.ship = serverShip;
    }

    @Override
    public @NotNull Collection<String> getDeviceTypeNames() {
        return Collections.singleton("ship_handler");
    }

    @Callback
    public @NotNull String getName() {
        if (ship.getSlug() != null) {
            return ship.getSlug();
        } else {
            return "no-name";
        }
    }

    @Callback
    public long getId() {
        return ship.getId();
    }

    @Callback
    public double getMass() {
        return ship.getInertiaData().getMass();
    }

    @Callback
    public Map<String, Double> getOmega() {
        return TableUtils.vectorToTable(ship.getOmega());
    }

    @Callback
    public Map<String, Double> getQuaternion() {
        return TableUtils.quatToTable(ship.getTransform().getShipToWorldRotation());
    }

    @Callback
    public double getROll() {
        List<List<Double>> rotMatrix = this.getRotationMatrix();
        return atan2(-rotMatrix.get(1).get(0), rotMatrix.get(1).get(1));
    }

    @Callback
    public double getYaw() {
        List<List<Double>> rotMatrix = this.getRotationMatrix();
        return atan2(-rotMatrix.get(0).get(2), rotMatrix.get(2).get(2));
    }

    @Callback
    public double getPitch() {
        return -asin(this.getRotationMatrix().get(1).get(2));
    }

    @Callback
    public Map<String, Double> getScale() {
        return TableUtils.vectorToTable(ship.getTransform().getShipToWorldScaling());
    }

    @Callback
    public Map<String, Double> getShipyardPosition() {
        return TableUtils.vectorToTable(ship.getTransform().getPositionInShip());
    }

    @Callback
    public Map<String, Double> getVelocity() {
        return TableUtils.vectorToTable(ship.getVelocity());
    }

    @Callback
    public Map<String, Double> getWorldspacePosition() {
        return TableUtils.vectorToTable(ship.getTransform().getPositionInWorld());
    }

    @Callback
    public boolean isStatic() {
        return ship.isStatic();
    }

    @Callback
    public void setName(@Parameter("name") final String name) {
        ship.setSlug(name);
    }

    @Callback
    public List<List<Double>> getRotationMatrix() {
        Matrix4dc transform = ship.getTransform().getShipToWorld();
        List<List<Double>> matrix = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Vector4d row = transform.getRow(i, new Vector4d());
            matrix.add(i, List.of(row.x(), row.y(), row.z(), row.w()));
        }

        return matrix.stream().toList();
    }
}
