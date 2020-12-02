package TESTING.E.domain.workstations;

import TESTING.E.domain.Car;
import TESTING.E.domain.Robot;
import TESTING.E.domain.RobotManager;

public class WorkStation implements Runnable {


    protected final int numberStep;
    protected final int numberAssemblyLine;
    protected Car car;
    protected RobotManager robotManager;
    protected Robot robot;
    protected boolean isAvailable;
    protected boolean exit;
    private WorkStationStatus status;

    protected AssemblyLineListener assemblyLine;

    public WorkStation(int numberAssemblyLine, int numberStep, AssemblyLineListener assemblyLine) {
        this.numberStep         = numberStep;
        this.numberAssemblyLine = numberAssemblyLine;
        this.assemblyLine       = assemblyLine;
        this.isAvailable        = true;
        this.status             = WorkStationStatus.WAITING;
        this.robotManager       = RobotManager.getInstance();
        this.exit = false;
    }


    @Override
    public void run() {
        while (!exit){
            assemblyLine.initWorkStation(new WorkStationEvent(this));

//            if(getStatus() == WorkStationStatus.FINISHED)
//                break;

//            robot = robotManager.getRobot(getRobotType(numberStep));
        }
    }

    private Object getRobotType(int numberStep) {
        return 0;
    }

    private boolean getStatus() {
        return false;
    }
}
