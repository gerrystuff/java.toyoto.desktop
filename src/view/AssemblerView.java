package view;

import controller.AssemblerController;
import model.AssemblerModel;

public class AssemblerView {
    AssemblerModel model;
    AssemblerController controller;

    private void doInterface(){
        model = new AssemblerModel();
        controller = new AssemblerController(this,model);
    }
}
