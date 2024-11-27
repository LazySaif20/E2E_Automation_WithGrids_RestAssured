package com.autogrid.steps;

import com.autogrid.utils.CommonActions;
import com.autogrid.utils.LaunchDriver;
import com.github.dockerjava.api.model.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustBookingMgmtListPage {
    private static final Logger logger = LoggerFactory.getLogger(CustBookingMgmtListPage.class);
    private final CommonActions commonActions;
    
    public CustBookingMgmtListPage(WebDriver driver){
        this.commonActions = new CommonActions(driver);
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//a[normalize-space()='Sales']")
    private WebElement SalesMenu;

    @FindBy(xpath = "//a[normalize-space()='Sales Operation']")
    private WebElement SalesOperation;
    
    @FindBy(xpath="//*[@id=\"gnb\"]/li[3]/div/ul/li[3]/ul/li[1]/a")
    private WebElement CustomerBookingMgmtLink;
    
    @FindBy(xpath="//span[contains(text(),'Customer Booking Mgt List')]")
    private WebElement CustomerBookingMgmtListTab ; 
    
    
    @FindBy(xpath="//*[@id=\"content\"]/section[1]/div[2]/dl[1]/dd[1]/span/span")
    private WebElement DateOf;
    
    @FindBy(xpath="//input[@id='sStartDt']")
    private WebElement StartDate;
    
    @FindBy(xpath="//input[@id='sEndDt']")
    private WebElement EndDate;

    @FindBy(xpath="//*[@id=\"content\"]/section[1]/div[2]/dl[1]/dd[3]/span/span/span[1]")
    private WebElement Model;

    
    @FindBy(xpath="//*[@id=\"content\"]/section[1]/div[2]/dl[1]/dd[4]/span/span/span[1]")
    private WebElement EnquiryStatus;
    
    
    @FindBy(xpath="//span[contains(text(),'Mobile No')]")
    private WebElement BasedOn;
    
    @FindBy(xpath="//input[@id='baseTxt']")
    private WebElement BaseText;
    
    
    @FindBy(xpath="//*[@id=\"content\"]/section[1]/div[2]/dl[2]/dd[3]/span/span/span[1]")
    private WebElement DeliveryStatus;
    
    
    @FindBy(xpath="//*[@id=\"content\"]/section[1]/div[2]/dl[2]/dd[4]")
    private WebElement TimePeriod;
    
    @FindBy(xpath="//*[@id=\"content\"]/section[1]/div[2]/dl[2]/dd[5]/span/span/span[1]")
    private WebElement BookingFIFOSkip;
    
     @FindBy(xpath="//button[@id='btnSave']")
    private WebElement SaveBtn;
     
    @FindBy(xpath="//button[@id='btnSearch']")
    private WebElement SearchBtn;
    
    
    public void clickOnSalesMenu() {
    	commonActions.clickElement(SalesMenu);;
    }
    
    
    public void clickOnSalesOperation() {
    	commonActions.clickElement(SalesOperation);
    	
    }
    
    public void CustomerBookingMgmtLink() {
    	commonActions.clickElement(CustomerBookingMgmtLink);
    }
   
   
    public void  CustomerBookingMgmtListTab() {
    	
    	commonActions.clickElement(CustomerBookingMgmtListTab);
    }
    
    public void DateOf() {
    	commonActions.clickElement(DateOf);
    	Select Cat=new Select(BasedOn);
    	Cat.selectByVisibleText("Enquiry");
    }
    public void StartDate()
    {
    	
    }
    public void EndDate() {
    	
    }
    
    
    
    public void Model() {
    	
    }
    
    public void EnquiryStatus () {
    	
    }
    
    public void BasedOn() {
    	Select Cat=new Select(BasedOn);
    	Cat.selectByVisibleText("Mobile No");
    }
    
    
    public void BaseText () 
    {
    	commonActions.clickElement(BaseText);
    	BaseText.clear();
    	BaseText.sendKeys("7489954647");
    	
    }
    
    public void DeliveryStatus () {
    	
    }
    
    public void TimePeriod () {
    	
    }
    
    public void BookingFIFOSkip () {
    	
    }
    
    public void SaveBtn () {
    	commonActions.clickElement(SaveBtn);
    	
    }
    
    public void SearchBtn () {
    	commonActions.clickElement(SearchBtn);
    	
    }
    
    
}