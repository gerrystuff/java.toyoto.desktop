package E.domain;

import java.util.List;
import java.util.Optional;
import java.util.Vector;

public class RobotManager {
    private final List<Robot> robots;
    private static final RobotManager manager = new RobotManager();


    private RobotManager(){
        robots = new Vector<>();


        for(int i = 0; i < 5; i++){
            robots.add(new Robot(1,5,RobotType.CHASIS));
        }
        for(int i = 0; i < 4; i++){
            robots.add(new Robot(2,6,RobotType.MOTOR));

        }
        for(int i = 0; i < 2; i++){

            robots.add(new Robot(12,2,RobotType.TRANSMISSION));

        }
        for(int i = 0; i < 3; i++){
            robots.add(new Robot(3,5,RobotType.CAREBODY));

        }
        for(int i = 0; i < 3; i++){

            robots.add(new Robot(4,3,RobotType.INTERNAL));

        }

        for(int i = 0; i < 2; i++){
            robots.add(new Robot(5,3,RobotType.TIRE));

         }
        for(int i = 0; i < 5; i++){
            robots.add(new Robot(6,8,RobotType.TEST));

        }

    }

    public static RobotManager getInstance(){
        return manager;
    }

    public synchronized void leaveRobot(Robot robot){
        robot.setStatus(RobotStatus.AVALIABLE);
        notifyAll();
    }

    public synchronized Robot getRobot(RobotType type) {
        Robot robotAvailable;

        while (true) {

            Optional<Robot> robotcompatible = robots.stream().filter(robot ->
                    robot.getType() == type
                            && robot.getStatus() == RobotStatus.AVALIABLE
            ).findFirst();

            if (!robotcompatible.isPresent()) {
                try {
                    wait();
                } catch (InterruptedException ignored) {
                }
            }

            if (robotcompatible.isPresent()) {
                robotAvailable = robotcompatible.get();
                robotAvailable.setStatus(RobotStatus.BUSY);
                break;
            }

        }

        return robotAvailable;
    }
}
