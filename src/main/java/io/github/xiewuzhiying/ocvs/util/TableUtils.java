package io.github.xiewuzhiying.ocvs.util;

import org.joml.Matrix4dc;
import org.joml.Quaterniondc;
import org.joml.Vector3dc;
import org.joml.Vector4d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TableUtils {
    public static Map<String, Double> vectorToTable(Vector3dc vector) {
        Map<String, Double> table = new HashMap<>();
        table.put("x", vector.x());
        table.put("y", vector.y());
        table.put("z", vector.z());
        return table;
    }

    public static Map<String, Double> quatToTable(Quaterniondc quat) {
        Map<String, Double> table = new HashMap<>();
        table.put("x", quat.x());
        table.put("y", quat.y());
        table.put("z", quat.z());
        table.put("w", quat.w());
        return table;
    }

    /*public static <K, V> Vector3dc getVectorFromTable(Map<K, V> input, String section) {
        val table = (input[section] ?: throwMalformedSectionError(section)) as Map<*, *>
        return Vector3d(
                (table["x"] ?: throwMalformedFieldError(section, "x")) as Double,
        (table["y"] ?: throwMalformedFieldError(section, "y")) as Double,
        (table["z"] ?: throwMalformedFieldError(section, "z")) as Double
        )
    }

    private fun getQuaternionFromTable(input: Map<*, *>): Quaterniondc {
        val table = (input["rot"] ?: throwMalformedSectionError("rot")) as Map<*, *>
        return Quaterniond(
                (table["x"] ?: throwMalformedFieldError("rot", "x")) as Double,
        (table["y"] ?: throwMalformedFieldError("rot", "y")) as Double,
        (table["z"] ?: throwMalformedFieldError("rot", "z")) as Double,
        (table["w"] ?: throwMalformedFieldError("rot", "w")) as Double
        )
    }*/
}
