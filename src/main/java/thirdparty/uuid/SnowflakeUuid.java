package thirdparty.uuid;

public class SnowflakeUuid {

    private static SnowflakeUuid ourInstance = new SnowflakeUuid();

    public static SnowflakeUuid getInstance() {
        return ourInstance;
    }

    private SnowflakeUuid() {};

    public void init(long centerId, long workerId) {
        idWorker = new SnowflakeIdWorker(workerId, centerId);
    }

    private SnowflakeIdWorker idWorker;

    public long getUUID() {
        return idWorker.nextId();
    }
}
