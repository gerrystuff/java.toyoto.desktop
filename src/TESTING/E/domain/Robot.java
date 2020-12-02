package TESTING.E.domain;

public class Robot {
    private final int numberWorkingStation;
    private final int workingTime;
    private RobotStatus status;
    private RobotType type;


    public Robot(
            int numberWorkingStation,
            int workingTime,
            RobotType type
    ){
        this.numberWorkingStation = numberWorkingStation;
        this.workingTime = workingTime;
        this.type = type;
        this.status = RobotStatus.AVALIABLE;
    }
    public int getNumberWorkingStation() {
        return numberWorkingStation;
    }

    public int getWorkingTime() {
        return workingTime;
    }

    public RobotStatus getStatus() {
        return status;
    }

    public void setStatus(RobotStatus status) {
        this.status = status;
    }

    public RobotType getType() {
        return type;
    }
}
