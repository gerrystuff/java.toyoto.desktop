package TESTING.V2.controller;


import TESTING.V2.Line;
import TESTING.V2.model.AssemblerModel;
import TESTING.V2.view.AssemblerView;

public class AssemblerController {
    Line lines[];
    AssemblerView view;
    AssemblerModel model;
    public AssemblerController(AssemblerView view, AssemblerModel model){
        lines = new Line[1];
        this.view = view;
        this.model = model;

        for(int i = 0; i < lines.length ; i++)
            lines[i] = new Line();

        towork();
    }

    public void towork(){
        for(int i = 0; i < lines.length ; i++)
            lines[i].start();

    }

}
