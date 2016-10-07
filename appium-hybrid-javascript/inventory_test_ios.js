var wd = require("wd"),
    chai = require("chai"),
    chaiAsPromised = require("chai-as-promised"),
    colors = require('colors'),
    path = require("path");

// setup chai - yawn
chai.use(chaiAsPromised);
chai.should();
chaiAsPromised.transferPromiseness = wd.transferPromiseness;

var appiumServer = {
  host: "localhost",
  port: 4723
};

// capacidades para o teste
var capabilities = {
  browserName: "iPhone 5s",
  autoWebview: true,
  platformName: "iOS",
  platformVersion: "10.0",
  deviceName: "iPhone Simulator",
  app: "/Users/eliasnogueira/Downloads/theOneManInventory-master/platforms/ios/build/emulator/The One Man Inventory.app"
};

// suite de teste
describe("E2E Inventory", function () {
  var browser;

  // timeout global
  this.timeout(10000);

  // pre condicao
  before(function(done) {
    browser = wd.promiseChainRemote(appiumServer);

    browser.on('status', function(info) {
      console.log(info.cyan);
    });
      
    browser
      .init(capabilities)
      .then(function() { done(); });
  });


  it("Login on Inventory", function (done) {
    browser
      .elementByName('email').sendKeys('elias.nogueira@gmail.com')
      .elementByName('password').sendKeys('elias')
      .elementByCssSelector('input[value=\'Login\']').click()
      .then(function() { done(); });
  });


  it("Deve haver uma categoria pre cadastrada", function (done) {
    browser
      .waitForElementByCssSelector('div[class=\'item item-divider center-text ng-binding\']', 10000)
      .elementByCssSelector('div[class=\'item item-divider center-text ng-binding\']') 
      .text().should.become('Categoria 1')
      .elementByCssSelector('div[class=\'item item-text-wrap ng-binding\']')
      .text().should.become('Teste de Categoria')
      .then(function() { done(); });
  });
 
    
  it("Deve haver um produto cadastrado", function (done) {
    browser
     .elementByCssSelector('div[class=\'item item-text-wrap ng-binding\']')
     .click()
     .waitForElementById('name', 10000)
     .elementById('name').text().should.become('Produto 1')
     .elementById('description').text().should.become('Descrição produto 1')
     .elementById('price').text().should.become('$200.00')
     .elementById('unit').text().should.become('1')
     .then(function() { done(); });
  });   
    
  // pos condicao
  after(function(done) {
    browser.quit()
    .then(function() { done(); });
  });
});