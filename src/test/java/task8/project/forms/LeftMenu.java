package task8.project.forms;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class LeftMenu extends Form {

    public LeftMenu() {
        super(By.id("left_blocks"), "left menu");
    }

    public MyProfilePage getMyProfilePage(){
        return new MyProfilePage();
    }
}
