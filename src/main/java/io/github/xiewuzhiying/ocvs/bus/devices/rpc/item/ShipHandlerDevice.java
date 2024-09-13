package io.github.xiewuzhiying.ocvs.bus.devices.rpc.item;

import li.cil.oc2r.api.bus.device.object.Callback;
import li.cil.oc2r.common.bus.device.rpc.item.AbstractItemRPCDevice;
import li.cil.oc2r.common.util.BlockLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4dc;
import org.joml.Quaterniondc;
import org.joml.Vector3dc;
import org.joml.Vector4d;
import org.valkyrienskies.core.api.ships.ServerShip;
import org.valkyrienskies.mod.common.VSGameUtilsKt;

import java.util.*;
import java.util.function.Supplier;

import static java.lang.Math.asin;
import static java.lang.Math.atan2;

public final class ShipHandlerDevice extends AbstractItemRPCDevice {

    Supplier<Optional<BlockLocation>> location;
    ServerShip ship;

    public ShipHandlerDevice(final ItemStack identity, final ServerShip ship) {
        super(identity, "ship");
        this.ship = ship;
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
        return vectorToTable(ship.getOmega());
    }

    @Callback
    public Map<String, Double> getQuaternion() {
        return quatToTable(ship.getTransform().getShipToWorldRotation());
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
        return vectorToTable(ship.getTransform().getShipToWorldScaling());
    }

    @Callback
    public Map<String, Double> getShipyardPosition() {
        return vectorToTable(ship.getTransform().getPositionInShip());
    }

    @Callback
    public Map<String, Double> getVelocity() {
        return vectorToTable(ship.getVelocity());
    }

    @Callback
    public Map<String, Double> getWorldspacePosition() {
        return vectorToTable(ship.getTransform().getPositionInWorld());
    }

    @Callback
    public boolean isStatic() {
        return ship.isStatic();
    }

    @Callback
    public void setName(String name) {
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

    @Callback
    public static Map<String, Double> vectorToTable(Vector3dc vector) {
        Map<String, Double> table = new HashMap<>();
        table.put("x", vector.x());
        table.put("y", vector.y());
        table.put("z", vector.z());
        return table;
    }

    @Callback
    public static Map<String, Double> quatToTable(Quaterniondc quat) {
        Map<String, Double> table = new HashMap<>();
        table.put("x", quat.x());
        table.put("y", quat.y());
        table.put("z", quat.z());
        table.put("w", quat.w());
        return table;
    }
}
