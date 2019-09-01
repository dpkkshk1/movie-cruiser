import { AppPage } from './app.po';
import { browser, logging ,by , element} from 'protractor';
import { protractor } from 'protractor/built/ptor'

describe('frontend App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display Title', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('Frontend');
  });
  it('should be redirected to /login route on opening the application',()=>{
    expect(browser.getCurrentUrl()).toContain('/login')
  });
  it('should redirect to /register route',()=>{
    browser.element(by.css('.register-button')).click()
    expect(browser.getCurrentUrl()).toContain('/register')
  });
  it('should be able to register user',()=>{
    browser.element(by.id('userid')).sendKeys('user12');
    browser.element(by.id('firstName')).sendKeys('Suser');
    browser.element(by.id('lastName')).sendKeys('lastuser');
    browser.element(by.id('mobileNo')).sendKeys(1234);
    browser.element(by.id('password')).sendKeys('userpass');
    browser.element(by.css('.register-user')).click()
    expect(browser.getCurrentUrl()).toContain('/login')
  });
  it('should be able to login user and navigate to popular movies',()=>{
    browser.element(by.id('userid')).sendKeys('user12');
    browser.element(by.id('password')).sendKeys('userpass');
    browser.element(by.css('.login-user')).click()
    expect(browser.getCurrentUrl()).toContain('/movies/popular')
  });
  it('should be able to search for movies',()=>{
    browser.element(by.css('.search-user')).click()
    expect(browser.getCurrentUrl()).toContain('/movies/search')
    browser.element(by.id('search-button-input')).sendKeys('super')
    browser.element(by.id('search-button-input')).sendKeys(protractor.Key.ENTER)
    const searchItems = element.all(by.css('.movie-title'));
    expect(searchItems.count()).toBe(20);
    for(let i =0;i<1;i+=1){
      expect(searchItems.get(i).getText()).toContain('Super');
    }
  });
  it('should be able to add movie to watchlist',async()=>{
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const searchItems = element.all(by.css('.movie-thumbnail'));
    expect(searchItems.count()).toBe(20);
    searchItems.get(0).click();
    browser.element(by.css('.addButton')).click()
  });
  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    }));
  });
});
