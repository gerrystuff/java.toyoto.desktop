package V3.controller;

import V3.AssemblerView;
import V3.model.AssemblerModel;

public class AssemblerController {
    AssemblerModel model;
    AssemblerView view;

    public AssemblerController(AssemblerView view, AssemblerModel model){
        this.model = model;
        this.view = view;

        this.view.setData(this.model.getLines());

    }

}
