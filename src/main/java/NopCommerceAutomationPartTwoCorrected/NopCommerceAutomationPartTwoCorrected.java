package NopCommerceAutomationPartTwoCorrected;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class NopCommerceAutomationPartTwoCorrected extends UtilsPage1{

    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod


    public void openBrowser() {

        System.setProperty("webdriver.chrome.driver","src\\main\\Resources\\BrowserDriver\\chromedriver.exe");

        //Open the Browser
        openChromeVersion76();
        //driver = new ChromeDriver();

        //Maximise the Browser window screen
        manageWindow();
        //driver.manage().window().fullscreen();

        //Set implicity wait for driver object
        manageTimeoutImplicity();
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //Open the website
        openNopCommerce();
        //driver.get("https://demo.nopcommerce.com/");
    }


    @AfterMethod

    public void browserClose(){
        //driver.quit();
    }
//Test 1
@Test

    public void userShouldBeAbleToClickTwoDifferentItemsToCompareAndSeeThoseItemsInComparePage() {


//Click On electronics
    clickElement(By.xpath("//li/a[@href=\"/electronics\"]"));
//Click on Cellphone
    clickElement(By.xpath("//img[@alt = \"Picture for category Cell phones\"]"));
//Click on First Product HTC One M8 Android L 5.0 Lollipop
    clickElement(By.xpath("//div/a[@href = \"/htc-one-m8-android-l-50-lollipop\"]"));
//Click add to Compare
    clickElement(By.xpath("//div[@class=\"compare-products\"]"));

    //To confirm if the First product is added to compare
     String actualResultTest1Module1 = extractText(By.xpath("//p/a[@href=\"/compareproducts\"]"));
     String expectedResultTest1Module1 = ("product comparison");
     softAssert.assertEquals(actualResultTest1Module1,expectedResultTest1Module1);

//Click on Second product HTC One Mini Blue
    clickElement(By.xpath("//a[@title=\"Show details for HTC One Mini Blue\"]"));
//click on add to compare
    clickElement(By.xpath("//div[@class=\"compare-products\"]"));

//To confirm if the second product is added to compare
    String actualResultTest1Module2 = extractText(By.xpath("//p/a[@href=\"/compareproducts\"]"));
    String expectedResultTest1Module2 = ("product comparison");
    softAssert.assertEquals(actualResultTest1Module2,expectedResultTest1Module2);

//Click on compare prodcuts

    clickElement(By.xpath("//p/a[@href=\"/compareproducts\"]"));

//To confirm the products in the compare list are the ones which we have added
    String actualResultTest1Module3 = extractText(By.xpath("//tr[@class=\"product-name\"]"));
    String expectedResultTest1Module3 = ("Name HTC One Mini Blue HTC One M8 Android L 5.0 Lollipop");
    softAssert.assertEquals(actualResultTest1Module3,expectedResultTest1Module3);

//Click on Clear button
    clickElement(By.xpath("//a[@class=\"clear-list\"]"));

//To confirm al is cleared and message is displayed
    String actualResultTest1Module4 = extractText(By.xpath("//div[@class=\"no-data\"]"));
    String expectedResultTest1Module4 = ("You have no items to compare.");
    softAssert.assertEquals(actualResultTest1Module4,expectedResultTest1Module4);

    softAssert.assertAll();
    }
//Test 2
@Test

    public void userShouldBeAbleToNavigateToNewsAndTypeDetailsAndItShouldBeDisplayedAtBottom(){


//Click on news
    clickElement(By.xpath("//li/a[@href=\"/news\"]"));
    //Click on Details
    clickElement(By.xpath("//a[@href=\"/new-online-store-is-open\" and @class=\"read-more\" ]"));

    //To confirm we can Leave our comments
    String actualResultTest2Module1 = extractText(By.id("comments"));
    String expectedResultTest2Module1 = ("Leave your comment\nTitle:\nComment:");
    softAssert.assertEquals(actualResultTest2Module1,expectedResultTest2Module1);

    //To enter Title
    enterText(By.id("AddNewComment_CommentTitle"),getProperty("FirstName")+" ");
    //To enter comment
    enterText(By.id("AddNewComment_CommentText"),getProperty("Comment"));
    //Click Add Comments
    clickElement(By.name("add-comment"));

    //To confirm the message has been added
    String actualResultTest2Module2 = extractText(By.xpath("//div[@class=\"result\"]"));
    String expectedResultTest2Module2 = ("News comment is successfully added.");
    softAssert.assertEquals(actualResultTest2Module2,expectedResultTest2Module2);

    //To Confirm the comment submitted is listed last in the comment section
    // to create list of all the comments
    List<WebElement> commentList = driver.findElements(By.xpath("//strong[@class=\"comment-text\"]"));
    //to ge t the last comment
    WebElement last_element = commentList.get(commentList.size()-1);
    //get text from the location

    System.out.println(last_element.getText());

    //Actual
    String actualResultTest2Module3 = last_element.getText();
    //Expected
    String expectedResultsTest2Module3 = getProperty("FirstName");
    //Assert
    softAssert.assertEquals(actualResultTest2Module3,expectedResultsTest2Module3);

    softAssert.assertAll();
    }

//Test 3
    @Test

    public void userShouldBeAbleToSearchByKeywordsUsingTheSearchBarAndAllTheResultsShouldHaveTheKeywordInThem(){

        //Enter KeyWord in Search Box : Card
        enterText(By.id("small-searchterms"),getProperty("KeyWord"));
        //Click Search Button
        clickElement(By.xpath("//input[@class=\"button-1 search-box-button\"]"));
        //To confirm all the result has the keyword in them
        //Result 1

        List<WebElement> al = driver.findElements(By.xpath("//div[@class=\"product-grid\"]"));

        System.out.println(al.size());

        int count = 0;

        for(WebElement e : al){
            if(e.getAttribute( "outerHTML").contains(getProperty("KeyWord"))){
                count++;

                System.out.println(e.getText());
                softAssert.assertTrue((e.getText()).contains(getProperty("KeyWord")));
            }else{

                System.out.println("No KeyWord"+ e.getText());
            }
            System.out.println(count);
            softAssert.assertEquals(al.size(),count);
        }

        //Enter random alphabets in search
        enterText(By.id("small-searchterms"),getProperty("RandomAlphabets"));
        //Click Search
        clickElement(By.xpath("//input[@class=\"button-1 search-box-button\"]"));


        //To Confirm it should show the error message
        String actualResultTest3Module4 = extractText(By.xpath("//div[@class=\"no-result\"]"));
        String expectedResultTest3Module4 = ("No products were found that matched your criteria.");
        softAssert.assertEquals(actualResultTest3Module4,expectedResultTest3Module4);

        //click on Homepage Image
        clickElement(By.xpath("//img[@alt=\"nopCommerce demo store\"]"));
        //Enter 3 Spaces in SearchBox
        enterText(By.id("small-searchterms"),"   ");
        //Click Search Button
        clickElement(By.xpath("//input[@class=\"button-1 search-box-button\"]"));

        //To confirm the Warning message should show
        String actualResultTest3Module5 = extractText(By.xpath("//div[@class=\"warning\"]"));
        String expectedResultTest3Module5 = ("Search term minimum length is 3 characters");
        softAssert.assertEquals(actualResultTest3Module5,expectedResultTest3Module5);

        softAssert.assertAll();
    }



}


