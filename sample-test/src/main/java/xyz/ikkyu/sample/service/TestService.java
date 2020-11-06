package xyz.ikkyu.sample.service;

/**
 * @author xinming
 * @Date 2019/12/23 22:25
 */
public interface TestService {

    void testTransaction();


    String saveString(String name , String value);

    String saveHash(String hkey ,String key ,String value);

    String saveSet(String key ,Object[] values);

    String saveSortedSet(String key, String value, double sort);

    String incrementSortedSet(String key, String value, double sort);

    String incrementString(String name);

    String incrementHash(String hkey ,String key);

}
