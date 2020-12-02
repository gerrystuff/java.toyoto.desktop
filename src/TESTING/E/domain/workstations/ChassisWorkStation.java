package TESTING.E.domain.workstations;

import TESTING.E.domain.CarProvider;

public class ChassisWorkStation extends WorkStation{

    CarProvider carProvider;

    public ChassisWorkStation(int id, int numberStep,AssemblyLineListener assemblyLine) {
        super(id,numberStep,assemblyLine);
        carProvider = CarProvider.getInstance();
        setStatus(WorkStationStatus.WAITING);
    }

    public void run(){
        while(!exit){
            provideCar();
//
//            if(getStatus() == WorkStationStatus.FINISHED)
//                break;
            
//            robot = robotManager.getRobot(RobotType.CHASIS);
//            assemblyLine.getRobot(new WorkStationEvent(this,robot));
            
            work();
        }
    }

    private void work() {
    }

    private boolean getStatus() {
        return false;
    }

    private void provideCar() {

    }

    public void setStatus(WorkStationStatus event) {
    }
}
