package xyz.ikkyu.common.service;

import java.util.UUID;

/**
 * @author xinming
 * @Date 2020/7/5 17:34
 */
public interface IIdService {
    Long nextLongId();

    String nextStringId();

    default String nextUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
