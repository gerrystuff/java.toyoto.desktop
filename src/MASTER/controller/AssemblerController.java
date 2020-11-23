package MASTER.controller;

import MASTER.model.AssemblerModel;
import MASTER.view.AssemblerView;

public class AssemblerController {
    AssemblerModel model;
    AssemblerView view;

    public AssemblerController(AssemblerView view, AssemblerModel model){
        this.model = model;
        this.view = view;

        this.view.setData(this.model.getLines());

    }

}
