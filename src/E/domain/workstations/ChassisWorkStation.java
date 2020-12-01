package E.domain.workstations;

import E.domain.CarProvider;
import E.domain.RobotType;
import E.views.WorkStationCanvas;

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
