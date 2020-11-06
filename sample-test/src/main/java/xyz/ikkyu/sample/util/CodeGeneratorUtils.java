package xyz.ikkyu.sample.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xinming
 * @Date 2020/3/15 14:39
 */
@Slf4j
public class CodeGeneratorUtils {


    //静态变量存储最大值
    private static final AtomicInteger atomicNum = new AtomicInteger();
    //初始化分组编号
    private static final int INIT_GROUP_NUM = 0;

    /**
     * @Author  javaloveiphone
     * @Description :初始化设置分组编号最大值
     * @throws Exception
     * void
     */
//    @PostConstruct
//    public void initMaxNum(){
//        try{
//            int maxGroupNum = -1;
//            if(maxGroupNum<INIT_GROUP_NUM){
//                maxGroupNum = INIT_GROUP_NUM;
//            }
//            if(log.isDebugEnabled()){
//                log.debug("初始化分组编号最大值为："+maxGroupNum);
//            }
//            atomicNum.set(maxGroupNum);
//        }catch(Exception e){
//            log.error("初始化获取分组编号最大值异常",e);
//        }
//    }

    static {
        try{
            int maxGroupNum = -1;
            if(maxGroupNum<INIT_GROUP_NUM){
                maxGroupNum = INIT_GROUP_NUM;
            }
            if(log.isDebugEnabled()){
                log.debug("初始化分组编号最大值为："+maxGroupNum);
            }
            atomicNum.set(maxGroupNum);
        }catch(Exception e){
            log.error("初始化获取分组编号最大值异常",e);
        }
    }

    /**
     * @Author  javaloveiphone
     * @Description :获取最新分组编号
     * @return
     * int
     * 注：此方法并没有使用synchronized进行同步，因为共享的编号自增操作是原子操作，线程安全的
     */
    public static String getNewAutoNum(){
        //线程安全的原子操作，所以此方法无需同步
        int newNum = atomicNum.incrementAndGet();
        //数字长度为5位，长度不够数字前面补0
        String newStrNum = String.format("%05d", newNum);
        return newStrNum;
    }

}
