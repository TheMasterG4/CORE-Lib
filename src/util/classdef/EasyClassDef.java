package util.classdef;

import util.containers.immutable.TriObject;

import java.lang.reflect.*;
import java.util.*;

public class EasyClassDef {

    public static TriObject<String,?,?>[] getVariables(Object obj) {
        List<TriObject<String,?,?>> variables = new ArrayList<>();
        if (obj == null) {
            return variables.toArray(new TriObject[0]);
        }
        for (Field field : obj.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                variables.add(new TriObject<>(field.getName(),field.get(obj).getClass(), field.get(obj)));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return variables.toArray(new TriObject[0]);
    }

}
