package com.yanggy.cloud.datasource;

/**
 * Created by yangguiyun on 2017/9/28.
 */
public class DynamicDataSourceHolder {

    /**
     * 主数据库标识
     */
    public static final String MASTER = "master";

    /**
     * 从数据库标识
     */
    public static final String SLAVE = "slave";

    private static final ThreadLocal<String> holder = new ThreadLocal<String>();

    private DynamicDataSourceHolder() {
        //
    }
    private static final ThreadLocal<String> CONTAINER = ThreadLocal.withInitial(
            () -> DynamicDataSourceHolder.SLAVE
    );
    public static void putDataSource(String key) {
        holder.set(key);
    }

    public static String getDataSource() {
        return holder.get();
    }

    public static void clearDataSource() {
        holder.remove();
    }

    public static boolean isMaster(){
        String master = holder.get();
        return holder.get().equals(MASTER);
    }

    public static void routeMaster() {
        CONTAINER.set(MASTER);
    }
    public static void routeSlave() {
        CONTAINER.set(SLAVE);
    }
    public static String get() {
        String key = CONTAINER.get();
        return CONTAINER.get();
    }
    public static void clear() {
        CONTAINER.remove();
    }
}
