package pages.web.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class MainMenuComponent {
    private final SelenideElement projectTree = $("#projectTree");

    public void checkProjectExist(String projectName) {
        projectTree.shouldHave(text(projectName));
    }

    public void checkProjectNotExist(String projectName) {
        projectTree.shouldNotHave(text(projectName));
    }

}
