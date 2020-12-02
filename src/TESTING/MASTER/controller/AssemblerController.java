package TESTING.MASTER.controller;

import TESTING.MASTER.model.AssemblerModel;
import TESTING.MASTER.view.AssemblerView;

public class AssemblerController {
    AssemblerModel model;
    AssemblerView view;

    public AssemblerController(AssemblerView view, AssemblerModel model){
        this.model = model;
        this.view = view;

        this.view.setData(this.model.getLines());

    }

}
