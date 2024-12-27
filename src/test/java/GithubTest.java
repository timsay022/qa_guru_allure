import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class GithubTest {

    private final String REPOSITORY = "allure-framework/allure2";
    private final String ISSUE_NAME = "Allure Report Shows Multiple Parent Suite Entries";

    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    public void testIssueSeachRaw() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/");

        $(".search-input").click();
        $("#query-builder-test").sendKeys(REPOSITORY);
        $("#query-builder-test").pressEnter();

        $(By.linkText("allure-framework/allure2")).click();
        $("#issues-tab").click();

        $("#issue_2834").shouldBe(visible);
        $("#issue_2834_link").shouldHave(text(ISSUE_NAME));
    }

    @Test
    public void testIssueSeachLambda() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Отрываем главную страницу", () -> open("https://github.com/"));

        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".search-input").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").pressEnter();
        });

        step("Открываем репозиторий " + REPOSITORY, () -> $(By.linkText(REPOSITORY)).click());

        step("Открываем таб Issues", () -> $("#issues-tab").click());

        step("Проверяем наличие Issue " + ISSUE_NAME + " в списке", () -> {
            $("#issue_2834").shouldBe(visible);
            $("#issue_2834_link").shouldHave(text(ISSUE_NAME));
        });

    }

    @Test
    public void testIssueSeachWithAnnotations() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssueTab();
        steps.shouldSeeTssueWithName(ISSUE_NAME);

    }
}
