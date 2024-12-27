import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebSteps {

    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("https://github.com/");
    }

    @Step("Ищем репозиторий {repo}")
    public void searchForRepository(String repo) {
        $(".search-input").click();
        $("#query-builder-test").sendKeys(repo);
        $("#query-builder-test").pressEnter();
    }

    @Step("Нажимаем по ссылке репозитория {repo}")
    public void clickOnRepositoryLink(String repo) {
        $(By.linkText(repo)).click();
    }

    @Step("Открываем таб Issue")
    public void openIssueTab() {
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие Issue {issueName} в списке")
    public void shouldSeeTssueWithName(String issueName) {
        $("#issue_2834").shouldBe(visible);
        $("#issue_2834_link").shouldHave(text(issueName));
    }
}
