package com.jountain.demo.utils;

import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
@UtilityClass
public class ListUtils {
    public static<T,R> List<R> mapList(List<T> list, Function<? super T,? extends R> mapper){
        Objects.requireNonNull(list, "list must not be null");
        Objects.requireNonNull(mapper, "mapper must not be null");
        return list.stream().map(mapper).collect(Collectors.toList());
    }
}
