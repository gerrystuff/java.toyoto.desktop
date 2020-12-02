package TESTING.E.domain.workstations;

public interface AssemblyLineListener {
   void receiveCar(WorkStationEvent event);
   void initWorkStation(WorkStationEvent event);
   void endWorkStation(WorkStationEvent event);
   void getRobot(WorkStationEvent event);
   void leaveRobot(WorkStationEvent event);
}
