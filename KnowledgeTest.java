package cmc.registration.pages;

import cmc.panelobjects.BasePage;
import cmc.registration.pages.enumeration.Countries;
import cmc.registration.pages.util.Translations;
import cmc.utils.SearchUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KnowledgeTest extends BasePage {

    @FindBy(xpath = "//div[@class='customer-prompt important']")
    private WebElement customerPromptKnowledgeTest;

    @FindBy(xpath = ".//cmc-account-details//*[@id='ArrForward']")
    private WebElement accountArrowForwrad;

    @FindBy(xpath = "//button[@class='cmc-button']")
    private WebElement startTestButton;

    @FindBy(xpath = "//button[@class='button']")
    private WebElement submitButton;

    @FindBy(xpath = "//p[@class='text']")
    private WebElement question;

    @FindBy(xpath = ".//button[@class='cmc-button']/div")
    private WebElement retryButton;

    @FindBy(xpath = ".//*[@id='ArrForward']")
    private WebElement knowledgeTest;

    @FindBy(xpath = ".//button[@class='next-gen-customer-hub__switch-button']")
    private WebElement customerHubButton;

    @FindBy(xpath = ".//h1[@class='next-gen-customer-hub__header-title']")
    private WebElement customerHubTitle;

    @FindBy(xpath = ".//div[contains(@class,'iframe-container')]/iframe")
    private WebElement iframeContainer;

    @FindBy(xpath = ".//iframe[@class='next-gen-customer-hub__page-content-window']")
    private WebElement customerHubFrame;

    @FindBy(xpath = ".//text[@class='percentage']")
    private WebElement testPercentage;

    @FindBy(xpath = ".//p[text()=\"You're now able to trade on a live account.\"]")
    private WebElement passMessage;

    @FindBy(xpath = "//div[@class='button-text']")
    private WebElement closeButton;

    @FindBy(xpath = ".//span[text()='A.']")
    private WebElement answerA;

    @FindBy(xpath = ".//p[@data-cy='cooling-period-text']//p[text()='1 days']")
    private WebElement coolingOff1Day;

    @FindBy(xpath = "(.//button[@class='risk-warning-button cmc-button-secondary'])[1]")
    private WebElement riskWarningAccept;

    @FindBy(xpath = ".//*[@class='circular-chart failed']")
    private WebElement knowledgeTestFailedChart;

    @FindBy(xpath = "//p[text()='Knowledge Test']")
    private WebElement knowledgeTestTab;

    @FindBy(xpath = "//div[@class='answer not-selected readonly correct']//span[@class='answer-text']")
    private WebElement fetchAnswer;
    @FindBy(xpath = "//div[@class='answer selected readonly correct']//span[@class='answer-text']")
    private WebElement fetchAnswerSelected;



    private Map<String, String> knowledgeTestQA;

    Map<String, String> questionAnswerEn = new HashMap<String, String>() {
        {
            put("Youholda1unitlong(buy)positioningold,andthemarketmid-priceiscurrently$1,300.Ifthemarginrequirementforthistradesizeis1%whatisthetotalmarginrequirementforthistrade?", "$13.00");
            put("WhentradingCFDsharesonmarginwhichofthefollowingstatementsiscorrectwhencomparingthiswithtradingphysicalshares?I-Thesameinvestment(monetaryvalue)willgiveyouamuchlargerexposuretotheunderlyingsharesII-YourprofitswillbemagnifiedIII-YourlosseswillbemagnifiedIV-Youcouldlosemorethanyourinvestment", "I, II, III & IV");
            put("Usingleverageallowsmeto:I-ChoosethesumIputatriskonatradeII-TakepartinshareholdervotesIII-InflateboththepotentiallossandpotentialprofitIcouldmakeonatradeIV-Balancetradestolimitpotentialprofitorloss", "III");
            put("Whichofthebelowstatementsis/arecorrect?I-Tradingonmargin,investingonlyaproportionofthenotionalvalueofthetrade,increasesrisktoyourcapitalII-PhysicalandleveragedtradingareequallyriskyIII-LeveragedtradingmagnifiesbothpotentialprofitsandlossesIV-Leveragedtradingincreasestheimpactofavolatileprice", "I, III & IV");
            put("Whatstatementbelowbestdescribesmargin?", "The ongoing amount I need in my account both to open and keep open a trade");
            put("YouplaceaCFDtradewithatotalnotionalpositionvalueof$3,000.Toopenthistradeyouonlyhadtodeposit10%ofthepositionsizewhichtotalled$300(3,000x10%).IfyourCFDaccountvalueiscurrently$2,000whatisyourmaximumriskforthistrade?", "3,000");
            put("TheUK100hasasellpriceof7,520andabuypriceof7,521.Youthinkthemarketisgoingtofallsoyougoshort(sell)10units.TheUK100Indexdoesindeedfallandisnowpricingatasellpriceof7,499andabuypriceof7,500.Ifyoudecidetocloseyourpositionnowwhatwouldbeyourprofit?", "200");
            put("Goldhasasellpriceof1,299.7andabuypriceof1300.0.Youthinkthemarketisgoingtorisesoyougolong(buy)5units.Uncertaintyhitsequitymarketsandthepriceofgoldrises.Itisnowtradingatasellpriceof1350.0andabuypriceof1350.3.Ifyoudecidetocloseyourpositionnowwhatwouldbeyourprofit?", "250.00");
            put("Ifyouinvested$1,000inphysicalsharesand$1,000inthesameproductusingCFDtradingwithamarginrequirementof10%(leverageof10:1),whatwoulda5%movelowerinpricehaveonyourrespectiveaccounts?", "Physical Shares: $50 loss" +
                    "CFD trading: $500 loss");
            put("Whichofthefollowingstatementsistrue?", "CFD trading can result in you making losses that exceed what you have deposited into the account");
            put("TheUK100Indexsellpriceis7,009andthebuypriceis7,010.Youbuy1unitatapriceof7,010.Themarketthenmovesup10pointssothesellpriceis7,019andthebuypriceis7,020,andyouclosethetrade.Howmuchprofithaveyoumade?", "9.00");
            put("BuyingaCFDonAppleallowsmeto:I-ParticipateinshareholdervotingonimportantissuesII-AttendshareholdermeetingsIII-OwnaportionofthecompanyIV-Beentitledtodividends", "None of the above");
            put("CFDtradingisaderivativeproductwhichhasthefollowingcharacteristicsI-Youdon’towntheunderlyingII-Youcantakealong(buy)orshort(sell)positionIII-Youonlydepositasmallpercentage(knownasmargin)ofthetotaltradenotionalvalue", "All of the above");
            put("Youholda1unitlongpositioninGermany30(DAX)withapurchasepriceof13,000andaguaranteedstopat12,900.Theguaranteedstoppremiumis1point.TheGermany30opensMondaymorningat12,750.Whatisthetotalloss(includinganypremium)youwillincurforthistrade?", "101");
            put("OnaFridayafternoonyouhaveanopenbuytradeintheGermany30.Thecurrentmarketpriceis12,300toselland12,301tobuy,andthereisaregularstopattachedtothetradeat12,250.ThemarketopensonMondaymorningat12,200toselland12,201tobuy.Whathappenedwiththetradeonopen?", "The trade closed at 12,200 as this was the first available price");
            put("TheUK100Indexhasasellpriceof7,520andabuypriceof7,521.Youthinkthemarketisgoingtofallsoyougoshort(sell)1unitandplaceastoplossorderat7,530.Ifthemarketrisesandyourstoplossisexecutedatyourdesiredlevel,whatisyourprofitorloss?", "10.00");
            put("IamtradingonCMC'splatform.IamusingaguaranteedstoplossonmyCFDbuytradeandthepriceoftheunderlyingisincreasinginmyfavour,whathappenstomymargin?", "It increases in line with the profit");
            put("WhyisthereachargeforusingaGSLO?", "A GSLO premium is to insure yourself against an undefined loss");
            put("WhichofthefollowingisafeatureofCMCStart(negativebalanceprotection)accounts?", "You can't lose more than the balance of your account");
            put("YouopenaUK100Indexpositionof10unitsonMondaymorningat9amandprotectyourtradewithaguaranteedstoplossorder.Ifyouweretoclosethispositionat4pmthatsamedaywhichofthefollowingisnotapplicabletoyourtrade?", "Holding costs");
            put("Inwhichofthesituationsbelowshouldyoufundmoneyintoyouraccount?I-IammakingaprofitonmytradeII-MytradehasgoneagainstmeandIamnearingautoclose-outbutIwanttokeepmytradeopenIII-MytradehasbeenclosedandhasleftmewithanegativebalanceIV-IwanttoopenanewtradebutIcan'twithmycurrentavailablebalance\"", "II, III & IV");
            put("Ifthemarketsmoveagainstyouandyouraccountvaluemovesbelowyourtotalmarginrequirementwhichofthesestatementsareoptionsopentoyou?I-DepositadditionalfundstobringyouraccountbackintoorderII-ClosepositionstobringyouraccountbackintoorderIII-Donothing.Ifyouraccountvaluecontinuestofallto50%ofyourmarginrequirementwewillcloseyourpositionsautomatically\"", "I, II & III");
            put("WhenyoutradeonCMC'splatform,thetradeis'over-the-counter',whichmeansthat", "Prices may differ from on-exchange prices, you can only close trades at CMC's price when we provide a price, which may not be on a continuous basis");


            put("OpeningaspreadbetonApplesharesallowsmeto:", "Speculate on the movement of the Apple share price.");
            put("WhichofthefollowingisnotacostassociatedwhentradingwithCMCMarkets?", "Stamp duty");
            put("Inwhichofthesituationsbelowshouldyoufundmoneyintoyouraccount?", "The market is going against me and I am nearing auto close-out but I want to keep my positions open.");
            put("Youholdalong(buy)positionwithastoplossorderattachedat12,250.Thepricegapsfrom12,260to12,240,whatpriceisthestoplossorderexecutedat?", "The stop loss is executed at 12,240 as this was the first available price after my stop loss level.");
            put("Youholdalong(buy)positionwithaStopLossOrderattachedat12,250.Thepricegapsfrom12,260to12,240,whatpriceistheStopLossOrderexecutedat?", "The Stop Loss is executed at 12,240 as this was the first available price after my stop loss level.");
            put("WhentradingCFDsharesonmarginwhichofthefollowingstatementsiscorrect?", "Your profits and losses will be magnified relative to the margin requirement.");
            put("PlacingaCFDtradeonApplesharesallowsmeto:", "Speculate on the movement of the Apple share price");
            put("Whenspreadbettingsharesonmarginwhichofthefollowingstatementsiscorrect?", "Your profits and losses will be magnified relative to the margin requirement.");


            put("Usingleverageallowsmeto:", "Inflate the size of the trade I can open and therefore magnify profits and losses");
            put("PlacingaspreadbetonApplesharesallowsmeto:", "Speculate on the movement of the Apple share price.");
            put("Ifthemarketsmoveagainstyouandyouraccountvaluedropsbelowyourtotalmarginrequirementwhichofthefollowingactionsshouldyoutake?", "Deposit additional funds to bring your account value back above your margin requirement.");
            put("WhichofthefollowingstatementsistrueabouttheCMCStartaccount?", "You can only lose the money you have in your trading account (Account Value).");
            put("WhenyoutradewithCMCyouaretradingoverthecounter(OTC)whichmeans:", "You are trading directly with CMC and not on a formal exchange.");
            put("WhichofthefollowingisnotacostassociatedwithaCMCMarketsaccount?", "Stamp duty");

            put("Whatisastoplossandhowcanithelpme?", "This is useful for risk management, CMC will seek to close out your CFD position on a best endeavours basis  at the desired price.");

            put("Youholdalong(buy)positionequivalentto$1perpoint,andthemarketpriceiscurrently1,300.Iftheinitialmarginrateis10%,whatistheinitialmarginrequirementforthistrade?", "$130.00");
            put("Youplacealong(buy)CFDpositionwithatotalnotionalpositionvalueof$3,000anda$150marginrequirement.Youhaveanaccountbalanceof$5,000.Whatisthemaximumamountthatcanbelostonthistrade?", "$3,000");
            put("Youopenalong(buy)positionequivalentto$5perpointat1,300.Thepriceofthemarketrisesandyoucloseyourpositionat1,350.Howmuchprofithaveyoumade?", "$250");
            put("Youopenalong(buy)positionequivalentto$1perpointatapriceof7,010.Themarketthenmovesupandyoucloseat7,020.Howmuchprofithaveyoumade?", "$10");
            put("Youholdalong(buy)positionwithastop-lossorderattachedat12,250.Thepricegapsfrom12,260to12,240,whatpriceisthestop-lossorderexecutedat??", "The stop-loss is executed at 12,240 as this was the first available price after my stop-loss level");
            put("Youthinkthemarketisgoingtofallsoyouopenashort(sell)positionequivalentto$1perpointat7,520andplacearegularstop-lossorderat7,530.Ifthemarketrisesandyourstop-lossisexecutedatyourdesiredlevel,whatisyourloss?", "$10");
            put("PlacingaCFDtradeonApplesharesallowsyouto:", "Speculate on the movement of the Apple share price");
            put("WhenyoutradewithCMCMarketsyoutrade\ufffdoverthecounter\ufffd(OTC),whichmeans:", "You’re trading directly with CMC and not on a formal exchange");
            put("Youopenashort(sell)CFDpositionequivalentto$10perpointat7,520.Themarketfallsandyoucloseyourpositionat7,500.Howmuchprofithaveyoumade?", "$200");
            put("Ifthemarketsmoveagainstyouandyouraccountvaluedropsbelowyourtotalmarginrequirement,whichofthefollowingactionsshouldyoutake?", "Deposit additional funds to bring your account value back above your margin requirement");

            put("Youholdalong(buy)Australian200indexpositionof1CFD,andthemarketpriceiscurrently6000.Iftheinitialmarginrateis1%,whatistheinitialmarginrequirementforthistrade?", "$60.00");
            put("Youholda1unitlong(buy)positioningold,andthemarketpriceiscurrently$1,300.Ifthemarginrequirementforthistradesizeis1%whatisthetotalmarginrequirementforthistrade?", "$13");
            put("Youholdalong(buy)positionof1Unit(equivalentto€1perpoint),andthemarketpriceiscurrently1300.Iftheinitialmarginrateis1%,whatistheinitialmarginrequirementforthistrade?", "€130.00");
            put("WhatisRiskManagement?", "Risk management is having a strategy in place to identify, assess and manage threats to my capital and earnings within an accepted exposure.");

            put("Youopenalong(buy)positionof5Units(equivalentto$5perpoint)at1300.Thepriceofthemarketrisesandyoucloseyourpositionat1350.Howmuchprofithaveyoumade?", "$250");
            put("HowdoyouensurethatyourAccountisadequatelyfunded?", "By monitoring the account consistently, taking into consideration margins, funds owed and the Account Revaluation amount.");


            put("\"CFDtradingisaderivativeproductwhichhasthefollowingcharacteristics:A-Youdon’towntheunderlyinginstrumentB-Youonlydepositasmallpercentage(knownasmargin)ofthetotaltradenotionalvalueC-Youcantakealong(buy)orshort(sell)position\"", "All of the above");
            put("WhentradingCFDsharesonmargin,whichofthefollowingstatementsiscorrect?", "Your profits and losses will be magnified relative to the margin requirement");
            put("Youholdalong(buy)Australia200Indexpositionof1CFD,andthemarketpriceiscurrently6000.Iftheinitialmarginrateis1%,whatistheinitialmarginrequirementforthistrade?", "$60");
            put("Youholdashort(sell)AUDUSDpositionof10,000andthemarketpriceiscurrently1.1800.Iftheleverageis500:1(0.20%margin),whatistheinitialmarginrequirementforthistrade?", "$20");
            put("Youplacealong(buy)CFDwithatotalnotionalpositionvalueof$3,000anda$150marginrequirement.Whatisthemaximumthatcanbelostonthistrade?", "$1,000");
            put("Youopenashort(sell)tradefor10CFDsonAustralia200Indexat6520.Themarketfallsandyoucloseyourpositionat6500.Howmuchprofithaveyoumade?", "$200");
            put("Youopenalong(buy)UK100Indexpositionof5CFDsandthemarketistradingat6000.Thepriceofthemarketrisesandyoucloseyourpositionat6050.Howmuchprofithaveyoumade?", "£250");
            put("Youopenalong(buy)Germany30Indexpositionof1CFDwiththemarkettradingat13010.Themarketthenmovesdownandyoucloseat12920.Howmuchhaveyoulostonthistrade?", "€ 90");
            put("Youopenalong(buy)GBPAUDtradeof1,000atapriceof1.7870.Themarketrisesandyouclosethetradeat1.7970.Howmuchprofithaveyoumade?", "$10");
            put("Youthinkthemarketisgoingtofallsoyouopenashort(sell)Australia200Indexpositionof1CFDwiththemarkettradingat6520andplacearegularstoplossorderat6530.Ifthemarketrisesandyourstoplossisexecutedatyourdesiredlevel,whatisyourloss?", "$10");
            put("IfthepriceofCrudeOilBrentis$42.50andyoutrade100units,whatisthenotionalpositionvalueofthetrade?", "$4,250");
            put("WhenyoutradewithCMCMarketsyoutrade‘overthecounter’(OTC),whichmeans:", "You are trading directly with CMC and not on a formal exchange");
            put("Usingleverageallowsyouto:", "Inflate the size of the trade I can open and therefore magnify profits and losses");
            put("Whatisgapping?", "Financial Products price can fluctuate rapidly, and  do not present an opportunity to place an order at the desired price level.");
            put("WhatisNegativebalanceprotection?", "Negative balance protection is where a Retail Client can only lose their invested capital.");
            put("CFDtradingisaderivativeproductwhichhasthefollowingcharacteristics:", "All of the above.");
            put("WhatarethemaincostsassociatedwithtradingCFDs?", "The costs associated with CFDs are:\n" +
                    "• Spread Costs\n" +
                    "• Commissions;\n" +
                    "• Holding Costs\n" +
                    "• Market Data Fees (if applicable)");


            //English
            put("OneofthecharacteristicsofCFDsis?", "No ownership rights on the underlying instrument.");
            put("CFDtradingisaderivativeproductwhichhasthefollowingcharacteristics:A-Youdon’towntheunderlyinginstrumentB-Youonlydepositapercentage(knownasmargin)ofthetotaltradenotionalvalueC-Youcantakealong(buy)orshort(sell)position", "All of the above.");
            put("WhentradingCFDsonmargin,whichofthefollowingstatementsiscorrect?", "Your profits and losses on CFDs will be magnified relative to the margin requirement");
            put("Whatisleverage?", "Trading with leverge makes it possible to take larger positions in financial markets with relatively small amounts of capital");
            put("Inwhichofthesituationsbelowshouldyoufundmoneyintoyouraccount?A-IammakingaprofitonmytradeB-MytradehasgoneagainstmeandIamnearingautoclose-outbutIwanttokeepmytradeopenC-IwanttoopenanewtradebutIcan'twithmycurrentavailablebalance", "B & C");
            put("Whatisastoploss?", "A stop loss is a type of contingent order to set pre-determined exit price.");
            put("Youplacealong(buy)CFDpositionwithatotalnotionalpositionvalueofEUR3,000andaEUR150marginrequirement.YouhaveanaccountbalanceofEUR5,000.Whatisthemaximumamountthatcanbelostonthistrade?", "EUR 3,000");
            put("Youplaceashort(sell)CFDpositionwithatotalnotionalpositionvalueofEUR3,000andaEUR150marginrequirement.YouhaveanaccountbalanceofEUR5,000.Whatisthemaximumamountthatcanbelostonthistrade?", "EUR 5,000");
            put("Youopenalong(buy)Germany40Indexpositionof1CFDwiththemarkettradingat15010.Themarketthenmovesdownandyoucloseat14920.Howmuchhaveyoulostonthistrade?", "EUR 90");
            put("Youopenalong(buy)SPX500Indexpositionof5CFDsandthemarketistradingat6000.Thepriceofthemarketrisesandyoucloseyourpositionat6050.Howmuchprofithaveyoumade?", "$250");
            put("WhatareMarketdatafees", "Thecostchargedbydatavendorswhichallowsproductswhichyouwanttoaccesstobepriced.");
            put("Youholdashort(sell)AUD/USDforeignexchange(FX)positionof10,000andtheleverageis30:1(3.33%margin),whatistheinitialmarginrequirementforthistrade?", "$333.33");
            put("Youholdalong(buy)SPX500Indexpositionof1CFD,andthemarketpriceiscurrently6000.Iftheinitialmarginrateis1%,whatistheinitialmarginrequirementforthistrade?", "$60");
            put("Youplacealong(buy)spreadbetwithatotalnotionalpositionvalueof$3,000anda$150marginrequirement.Youhaveanaccountbalanceof$5,000.Whatisthemaximumthatcanbelostonthisbet?", "$3,000");
            put("Youthinkthemarketisgoingtofallsoyouopenashort(sell)TeslaShareCFDpositionof5CFDwiththemarkettradingat1,000andplacearegularstoplossorderat1,200.Ifthemarketrisesandyourstoplossisexecutedatyourdesiredlevel,whatisyourloss?","$1,000");
            put("Youholda1unitlong(buy)positioningoldatapriceof$1,300.Themarginrequirementis5%.Whatisthetotalmarginrequirementforthistrade?","$65");
            put("Forexistradedincurrencypairs.IfyouBuytheCAD/USDcurrencypair,whatareyoudoing","Buy Canadian Dollars while simultaneously selling US dollars.");
            put("Whichofthefollowingprocessesareimportanttominimizingriskwhentradingforex?","Trading with a stop loss");
            put("Ifthemarketisvolatile,whichofthefollowingstatementistrue?","The market has big movements up and down.");
            put("Youthinkthemarketisgoingtofallsoyouopenashort(sell)SPX500Indexpositionof1CFDwiththemarkettradingat6520andplacearegularstoplossorderat6530.Ifthemarketrisesandyourstoplossisexecutedatyourdesiredlevel,whatisyourloss?","$10");
            put("Youopenalong(buy)positionof$5perpointat1,300.Thepriceofthemarketrisesandyoucloseyourpositionat1,350.Howmuchprofithaveyoumade?","$250");
            put("Youholdashort(sell)CAD/USDpositionof10,000.Ifthemarginrequiredis2%,whatisthemarginrequirementforthisposition?","$200");
            put("WhatareMarketdatafees?","The cost charged by data vendors which allows products which you want to access to be priced.");
            put("Youopenalong(buy)NASDAQ100Indexpositionof1CFDwiththemarkettradingat13,010.Themarketthenmovesdownandyoucloseat12,920.Howmuchhaveyoulostonthistrade?", "$90");
            put("PlacingaCFDtradeonApplesharesallowsmeto:","Speculate on the movement of the Apple share price.");
            put("GiventhatCMCMarketsspreadsare\"variable\",whichofthefollowingistrue?","The spread widens and narrows based on the changes in the underlying market.");
            put("YouopenaS&P/TSXIndexpositionof10units.Ifyouclosethispositionthesameday,whichofthefollowingisnotapplicabletoyourtrade?","Holding costs");
            put("Youopenalong(buy)CAD/USDtradeof1,000atapriceof1.7870.Themarketrisesandyouclosethetradeat1.7970.Howmuchprofithaveyoumade?","$100");
            put("Youopenashort(sell)tradefor100CFDsonBHPShareCFDsat$40.00.Themarketfallsandyoucloseyourpositionat$36.00.Howmuchprofitorlosshaveyoumade?","$400 Profit");
            put("Whencouldpositionsbeclosedoutwithoutmyexplicitinstructions?","Where a specified event occurs for example failure to pay an outstanding amount, insolvency or death.");
            put("Youopenalong(buy)Australian200Index(basedofftheASXFuturescontract)positionof10CFDwiththemarkettradingat6,700.Themarketthenmovesdownandyoucloseat6,680.Howmuchhaveyoulostonthistrade?","$200.00");
            put("Youholdashort(sell)AUDUSDpositionof10,000andthemarketpriceiscurrently1.1800.Iftheleverageis50:1(2%margin),whatistheinitialmarginrequirementforthistrade?","$200");

            //German
            put("BeimCFD-HandelhandeltmaneinderivativesFinanzinstrument,dasfolgendeMerkmaleaufweist:A-SieerwerbenkeineRechteandemzugrundeliegendeFinanzinstrumentB-SiehinterlegennureinenbestimmtenProzentsatz(diesogenannteMargin)desNominalwertesC-SiekönneneineLong-(Kauf)oderShort-Position(Verkauf)einnehmen", "Alle oben genannten Antworten");
            put("DurchdenEinsatzeinesHebelsk\u00F6nnenSie:", "Die Größe der Position erhöhen, die eröffnet werden kann, und so Gewinne und Verluste steigern");
            put("WelchederfolgendenAussagenzumHandelmitCFDsmiteinerHinterlegungvonMarginistrichtig?", "Ihre Gewinne und Verluste bei  CFDs erh\u00F6hen sich im Verh\u00E4ltnis der Marginforderungen.");
            put("WasisteinHebel/eineHebelwirkung?", "Der Handel mit Hebelwirkung erm\u00F6glicht, mit relativ geringem Kapital gr\u00F6\u00DFere Positionen an den Finanzm\u00E4rkten einzugehen");
            put("InwelcherderfolgendenSituationensolltenSieGeldaufIhrKontoeinzahlen?A-IchmacheeinenGewinnmitmeinemTradeB-MeinTradehatsichnegativentwickeltundichstehekurzvorderautomatischenSchlie\u00DFung,m\u00F6chteabermeinenTradeoffenhaltenC-Ichm\u00F6chteeinenneuenTradeer\u00F6ffnen,kanndiesabermitmeinemderzeitigenGuthabennichttun", "B & C");
            put("WasisteineStop-Loss-Order?", "Eine Stop-Loss-Order ist ein bedingter Auftrag, mit dem im Voraus festgelegte Ausstiegskurse gesetzt werden können.");
            put("SiehalteneineLong-Position(Kauf)miteinerStop-Loss-Orderbei12.250.DerKursbrichtvon12.260auf12.240ein.ZuwelchemKurswirdderStop-Loss-Auftragausgef\u00FChrt?", "Der Stop-Loss wird bei 12.240 ausgef\u00FChrt, da dies der erste verf\u00FCgbare Kurs nach meinem Stop-Loss-Niveau war.");
            put("SieplatziereneineCFD-Long-Position(Kauf)miteinemGesamtpositionswertvon3.000EURundeinerNachschusspflichtvon150EUR.SiehabeneinenKontostandvon5.000EUR.WiehochistdermaximaleBetrag,denSiebeidiesemHandelverlierenk\u00F6nnen?", "EUR 3,000");
            put("SieplatziereneineCFD-Short-Position(Verkauf)miteinemGesamtpositionswertvon3.000EURundeinerMargin-Anforderungvon150EUR.SiehabeneinenKontostandvon5.000EUR.WiehochistdermaximaleBetrag,denSiebeidiesemTradeverlierenk\u00F6nnen?", "EUR 5,000");
            put("Sieer\u00F6ffneneineLong-Position(Kauf)von1CFDaufdenGermany40Index,w\u00E4hrendderMarktbei15010notiert.DerMarktbewegtsichdannnachuntenundSieschlie\u00DFendiePosition(Verkauf)bei14920.WievielhabenSiebeidiesemTradeverloren?", "EUR 90");
            put("WasisteinMerkmalvonCFDs?", "Keine Eigentumsrechte an den zugrunde liegenden Finanzinstrumenten.");
            put("WennSiemitCMCtraden,handelnSieoverthecounter(OTC),wasbedeutet:", "Sie handeln direkt mit CMC und nicht an einer offiziellen B\u00F6rse.");


            //French
            put("L'unedescaract\u00E9ristiquesdesCFDsest?", "Aucun droit de propriété sur l'instrument sous-jacent.");
            put("L'utilisationdel'effetdeleviervouspermetde:", "Augmenter la taille de la transaction que je peux ouvrir et donc amplifier les profits et les pertes");
            put("Lorsdelan\u00E9gociationdeCFDssurmarge,laquelledesaffirmationssuivantesestcorrecte?", "Vos profits et vos pertes sur les CFDs seront amplifiés par rapport à l'exigence de marge.");
            put("Qu'est-cequel'effetdelevier?", "La négociation avec effet de levier permet de prendre des positions plus importantes sur les marchés financiers avec un capital relativement faible.");
            put("Qu'est-cequ'unStopLoss? ", "Un stop loss est un type d'ordre conditionnel permettant de fixer des prix d'entrée ou de sortie prédéterminés.");
            put("Vousd\u00E9tenezunepositionlongue(\u00E0l'achat)avecunOrdreStopLossy\u00E9tantli\u00E9\u00E0uncoursde12250.Lecourseffectueungapde12260\u00E012240,\u00E0quelcoursl'OrdreStopLossest-t-ilex\u00E9cut\u00E9?", "Le stop loss est exécuté à 12 240 car c'est le premier cours disponible au-delà du niveau de mon stop loss.");
            put("Vousprenezunepositionlongue(\u00E0l'achat)surunCFDpourunevaleurnominaletotalede3000\u20ACetunbesoinenmargede150\u20ACetavecuneValeurduComptede5000\u20AC.Quelestlemontantmaximumpouvant\u00EAtreperdusurcettetransaction?", "3 000 €");
            put("Vousplacezunepositionvendeuse(short)surCFDavecunevaleurnotionnelletotalede3000eurosetuneexigencedemargede150euros.Lesoldedevotrecompteestde5000euros.Quelestlemontantmaximumquipeut\u00EAtreperdusurcettetransaction?", "EUR 5,000");
            put("Vousouvrezunepositionlongue(achat)surl'indiceAllemagne40de1CFDalorsquelemarch\u00E9sen\u00E9gocie\u00E015010.Lemarch\u00E9baisseensuiteetvouscl\u00F4turez\u00E014920.Combienavez-vousperdusurcettetransaction?", "EUR 90");


            //Spanish
            put("¿CuálseríaunadelascaracterísticasdelosCFDs?", "No hay derechos de propiedad sobre el instrumento subyacente.");
            put("UnCFDesunproductoderivadoquetienelassiguientescaracteristicas:A-UstednoespropietariodelinstrumentosubyacenteB-Ustedsólodepositaunporcentaje(garantía)delvalortotaldelaoperaciónC-Puedetomarunaposiciónlarga(compra)ocorta(venta).", "Todas las anteriores");
            put("Elusodelapalancamientolepermite:", "Incrementar el tamaño de la operación que puedo abrir y, por tanto, magnificar los beneficios y las pérdidas.");
            put("AloperarconCFDscongarantía,¿cuáldelassiguientesafirmacionesescorrecta?", "Sus ganancias y pérdidas en CFDs se magnificarán en función de la garantía requerida.");
            put("¿Quéeselapalancamiento?", "Operar con apalancamiento permite tomar posiciones con más volumen en los mercados financieros con cantidades relativamente pequeñas de capital");
            put("¿Encuáldelassiguientessituacionesdeberíaingresardineroensucuenta?A-EstoyobteniendobeneficiosenmioperaciónB-Mioperaciónhaidoenmicontrayestaapuntodecerrarseautomáticamente,peroquieromantenerlaabierta.C-Quieroabrirunanuevaoperación,peronopuedoconmisaldoactualdisponible.", "B & C");
            put("¿Quéesunstoploss?", "Un stop loss es un tipo de orden contingente para pre-establecer el precio de salida");
            put("Ustedmantieneunaposiciónlarga(decompra)conunaordenStopLossadjuntaa12.250.Elpreciopasade12.260a12.240,¿aquéprecioseejecutalaordenStopLoss?", "El Stop Loss se ejecuta en 12.240, ya que era el primer precio disponible después de mi nivel de stop loss.");
            put("Ustedcolocaunaposiciónlarga(compra)deCFDconunvalornocionaltotaldelaposiciónde3.000eurosyunrequisitodemargende150euros.Elsaldodesucuentaesde5.000euros.¿Cuáleslacantidadmáximaquepuedeperderenestaoperación?", "EUR 3,000");
            put("Ustedabreunaposicióncorta(deventa)enCFDsconunvalornocionaltotaldelaposiciónde3.000eurosyunrequerimientodegarantíade150euros.Elsaldodesucuentaesde5.000euros.Cuáleslacantidadmáximaquepuedeperderenestaoperación?", "EUR 5,000");
            put("Ustedabreunaposiciónlarga(decompra)enelÍndiceAlemania40de1CFDconelmercadocotizandoa15010.Elmercadobajayustedcierraa14920.¿Cuántohaperdidoenestaoperación?", "EUR 90");
            put("CuandooperaconlosCFDsdeCMC,estáoperandoconderivadosOTC(overthecounter),esdecir,derivadosquenocotizanenunmercadoorganizado,loquesignificaque:", "Usted está operando directamente con CMC y no en ninguna Bolsa o mercado organizado.");


            //Italian
            put("UnadellecaratteristicheprincipalideiCFDè?", "Nessun diritto di proprietà sullo strumento sottostante.");
            put("QuandonegoziconCMCMarkets,effettuidellenegoziazionialdifuoridiunmercatoregolamentato(\"overthecounter\"o\"OTC\").Questosignificache:", "Negozi in contropartita diretta con CMC Markets e non su un mercato formale");
            put("CFDtradingèunprodottoderivatochepresentaleseguenticaratteristiche:A-nonsipossiedelostrumentosottostanteB-sidepositasolounapercentuale(notacomemargine)delvalorenozionaletotaledell'operazioneC-Sipuòassumereunaposizionelunga(acquisto)ocorta(vendita).", "Tutti i precedenti");
            put("L'utilizzodellalevafinanziariaconsentedi:", "Gonfiare la dimensione delle operazioni che posso aprire e quindi amplificare i profitti e le perdite.");
            put("QuandosinegozianoCFDsuazioniconmargine,qualedelleseguentiaffermazioniècorretta?", "I vostri profitti e le vostre perdite sulle azioni saranno amplificati rispetto al margine richiesto.");
            put("Checos'èlalevafinanziaria?", "Il trading con la leva finanziaria consente di assumere posizioni più ampie sui mercati finanziari con un capitale relativamente ridotto.");
            put("Inqualedelleseguentisituazionidovresteversaredenarosulvostroconto?A-StoottenendounprofittosullamiaoperazioneB-Ilmiotradeèandatocontrodimeesonovicinoallachiusuraautomatica,mavogliomantenereilmiotradeapertoC-Voglioaprireunanuovaoperazionemanonpossoconilmiosaldodisponibileattuale.", "B & C");
            put("Checos'èunostoploss?", "Lo stop loss è un tipo di ordine contingente per fissare un prezzo di uscita predeterminato.");
            put("Detieniunaposizionelong(inacquisto)conunOrdineStopLosscollegatoadunprezzodi12.250.Ilprezzosubisceungapda12.260a12.240,aqualeprezzovieneseguitol'OrdineStopLoss?", "Lo stop loss è eseguito a 12.240 perchè è il primo prezzo disponibile dopo il livello del mio stop loss.");
            put("SipiazzaunaposizioneCFDlunga(inacquisto)conunvalorenozionaletotaledi3.000euroeunmarginerichiestodi150euro.Ilsaldodelcontoèdi5.000euro.Qualèl'importomassimochesipuòperdereconquestaoperazione?", "EUR 3,000");
            put("SicollocaunaposizioneCFDshort(vendita)conunvalorenozionaletotaledi3.000euroeunmarginerichiestodi150euro.Ilsaldodelcontoèdi5.000euro.Qualèl'importomassimochesipuòperdereconquestaoperazione?", "EUR 5,000");
            put("Apriteunaposizionelunga(inacquisto)sull'indiceGermany40di1CFDconilmercatoa15010.Ilmercatosimuovepoialribassoevoichiudetea14920.Quantoavetepersoconquestaoperazione?", "EUR 90");


            //Norwegian
            put("EnavdeviktigsteegenskapenetilCFDerer:", "CFDer gir deg ingen eierrettigheter i de underliggende finansielle instrumentene");
            put("DinehandlermedCMCMarketsutførespåsåkaltOTC(overthecounter)basissombetyr:", "Du handler direkte med CMC Markets og ikke på en børs");
            put("EnCFDeretderivatproduktsomharfølgendeegenskaper:A-DueierikkedetunderliggendeinstrumentetB-Dutrengerkunåsetteinnenprosentandel(kjentsommarginkrav)avdentotalehandelsverdienC-Dukantalong(kjøps)ellershort(salgs)posisjoner", "Alle de ovennevnte");
            put("Brukavgiringgjørdetmuligå:", "Forstørre størrelsen på posisjonene man kan åpne, dette kan medføre økt potensiell gevinst eller tap");
            put("HvilketutsagnerkorrektnårduhandlerCFDerpåmargin?", "Gevinst og tap vil forstørres i forhold til marginkravet.");
            put("Hvaerhandelmedgiring?", "Handel med giring gjør det mulig å ta større posisjoner i  finansmarkedene med en relativt liten mengde innskutt egenkapital.");
            put("Ihvilkeavsituasjonenenedenforbørdusetteinnmermidlerpåkontoendin?A-NårdutjenerpengerpåhandelendinB-Nårhandelendinhargåttimotdegogdunærmerdegkontolikvidering,menvilholdehandelenåpenC-Nårduønskerååpneennyhandel,mendukanikkemednåværendeledigekapital", "B & C");
            put("Hvaerenstoploss-ordre?", "En stop loss-ordre er en ordretype for å stenge en posisjon til en kurs som kan være dårligere enn gjeldende markedskurs.");
            put("Duharenkjøpsposisjon(Long)medentilknyttetstoploss-ordrepåkurs12250.Kursenfallerfra12260til12240utenatdetgårnoenhandlerimellom,hvilkenkursutføresstoploss-ordrentil?", "Stop loss-ordren utføres på 12 240 da dette er første tilgjengelige kurs etter stop loss-nivået ditt.");
            put("Hvaerdetmaksimalepotensielletappåenlong(kjøps)posisjonmedenmarkedsverdipå3000krhvismarginkraveter150krogkontoverdiener5000kr?", "3000");
            put("Hvaerdetmaksimalepotensielletappåenshort(salgs)posisjonmedenmarkedsverdipå3000krhvismarginkraveter150krogkontoverdiener5000kr?", "EUR 5,000");
            put("Duåpnerenkjøpsposisjon(gårlong)påGermany40-indeksenpå1CFDnårmarkedethandlerpå15010.Markedetfaller,ogdustengerposisjonenpå14920.Hvormyehardutaptpådennehandelen?", "EUR 90");


            //Swedish
            put("EnavdeviktigastekännetecknenförCFD:erär?", "CFD:er ger dig inga rättigheter i det underliggande finansiella instrumentet.");
            put("MedCMCMarketshandlardu\"overthecounter\"(OTC),vadinnebärdetta?", "Du handlar direkt med CMC Markets och inte via börs");
            put("EnCFDärenderivatproduktmedföljandeegenskaper:A-DuägerintedetunderliggandeinstrumentetB-Dubehöverenbartsättainenprocentandel(käntsomsäkerhetskrav)avdettotalahandelsvärdetC-Dukantalång(köp-)ellerkort(sälj-)positioner", "Alla ovan alternativ");
            put("Attanvändahävstånggörattdukan:", "Förstora storleken på positionen du kan öppna, vilket medför potential till större vinster och förluster.");
            put("VilketavföljandepåståndärkorrektnärduhandlarCFD:ermedsäkerhetskrav?", "Dina vinster och förluster förstoras relativt till säkerhetskravet.");
            put("Vadärhävstång?", "Handel med hävstång gör det möjligt att ta större positioner på finansmarknaderna med ett relativt litet insatt egenkapital.");
            put("Ivilkaavnedansituationerbörduöverföramerpengartilldittkonto?A-NärdugörenvinstpådinhandelB-Handelnhargåttemotdigochdunärmardignivånförautomatisklikvidering,menvillhållahandelnöppenC-Närduönskaröppnaennyhandel,menintekandetmednuvarandeledigtkapital", "B & C");
            put("Vadärenstoploss-order?", "En stop loss-order är en ordertyp för att stänga en position till en kurs som kan vara sämre än gällande marknadskurs.");
            put("Duharenköpposition(lång)medentillhörandestoploss-orderpåkurs12250.Kursenfallerfrån12260till12240utanhandelemellan.Tillvilkenkursutförsstoploss-ordern?", "Stop loss-ordern utförs på 12 240 då detta är första tillgängliga kurs efter din stop loss-nivå.");
            put("Vilkenärdenmaximalaförlustenpåenlång(köp-)positionvarsmarknadsvärdeär3000Euroomsäkerhetskravetär150Euroochkontobalansenär5000Euro?", "EUR 3,000");
            put("Vilkenärdenmaximalaförlustenpåenshort(sälj-)positionvarsmarknadsvärdeär3000Euroomsäkerhetskravetär150Euroochkontobalansenär5000Euro?", "EUR 5,000");
            put("Duöppnarenköpposition(gårlång)påindexetGermany40med1CFDnärmarknadenhandlaspå15010.Marknadenfaller,ochdustängerpositionenpå14920.Hurstorärdinförlustpådennahandel?", "EUR 90");


            //Polish
            put("WskażcharakterystycznącechękontraktównaróżnicekursoweCFD", "Nie jesteś posiadaczem instrumentu bazowego.");
            put("KiedyprzeprowadzasztransakcjezCMCMarketsnarynkuOTC,tooznacza,że:", "Przeprowadzasz transakcje z CMC Markets, a nie na giełdzie.");
            put("TransakcjenakontraktachCFDcharakteryzująnastępującecechy:A-NiestajeszsięposiadaczeminstrumentubazowegoB-Podtransakcjeblokowanyjestjedyniepewienprocent(określanymianemdepozytuzabezpieczającego)pełnejwartościotwieranejtransakcjiC-Możeszotworzyćpozycjędługą(Kup)lubkrótką(Sprzedaj)", "Wszystkie powyższe");
            put("Wykorzystaniedźwignifinansowej(lewara)umożliwiaCi:", "Zwiększenie wielkości transakcyjnej i tym samym zysków oraz strat.");
            put("KtórezponiższychstwierdzeńjestprawdziwewprzypadkutransakcjizwykorzystaniemdepozytuzabezpieczającegonakontraktachCFD?", "Twoje zyski lub straty będą zwiększone odpowiednio w stosunku do wymaganego  depozytu zabezpieczającego.");
            put("Czymjestdźwigniafinansowa?", "Dźwignia finansowa umożliwia wykorzystanie względnie niewielkiego kapitału do otwarcia większych pozycji na rynku kapitałowym.");
            put("Wktórejzponiższychsytuacjinależyzasilićrachunektransakcyjny?A-MojatransakcjajestzyskownaB-RynekporuszasięwkierunkuprzeciwnymdomoichtransakcjiirachunekjestbliskiosiągnięciapoziomuWymuszonegoZamknięcia(Close-out),chcęnatomiastutrzymaćswojepozycjeC-Chcęotworzyćnowąpozycję,aleniemogęzpowoduobecnegosalda", "B i C");
            put("Czymjeststoploss?", "Stop loss jest rodzajem zlecenia, w ramach którego wskazuje się poziom cenowy zamknięcia pozycji.");
            put("Posiadaszpozycjędługą(kup)zeZleceniemStopLossdodanymnapoziomiecenowym12250.PojakiejcenierealizowanejestzlecenieStopLossprzylucecenowejwprzedzialeod12260do12240?", "Zlecenie Stop Loss zostanie realizowane po cenie 12 240, ponieważ jest to pierwsza dostępna cena.");
            put("PrzeprowadzasznastępującątransakcjęnakontrakcieCFD:otwieraszpozycjędługą(Kup)owartościnominalnej3000EUR,zwymaganymdepozytemzabezpieczającymnapoziomie150EUR.WartośćTwojegorachunkuwynosi5000EUR.Jakajestmaksymalnawartośćstraty,którąmożeszponieśćwramachtejtransakcji?", "EUR 3,000");
            put("PrzeprowadzasznastępującątransakcjęnakontrakcieCFD:otwieraszpozycjękrótką(Sprzedaj)owartościnominalnej3000EUR,zwymaganymdepozytemzabezpieczającymnapoziomie150EUR.WartośćTwojegorachunkuwynosi5000EUR.Jakajestmaksymalnawartośćstraty,którąmożeszponieśćwramachtejtransakcji?", "EUR 5,000");
            put("Otwieraszpozycjędługą(Kup)owartości1CFDnakontrakcieGermany40napoziomiecenowym15010.Rynekspadaizamykaszpozycjępocenie14920.JakdużajestTwojastrata?", "EUR 90");
        }
    };

    public KnowledgeTest(WebDriver driver) throws IOException {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickOnStartTest() {
        if (waitForVisibility(startTestButton, 10, "return", "") != null) {
            m_logger.info("Starting knowledge test");
            //moveToElement(startTestButton);
            startTestButton.click();
        }
    }

    public void openKnowledgeTest(String testCountryID) throws IOException {
        try {
            switchToFrame(customerHubFrame);
        }
        catch(Exception e) {}
        sleep(3000);


        if(waitForVisibility(accountArrowForwrad, 5, "return", "")!=null) {
            moveToElement(accountArrowForwrad);
            accountArrowForwrad.click();
            OnBoarding onBoardingPlatVerif = PageFactory.initElements(driver, OnBoarding.class);
            sleep(30000);
            onBoardingPlatVerif.verifyPlatformLogo();
            startTest(testCountryID);
//            if (waitForVisibility(startTestButton, 10, "return", "") != null) {
//                m_logger.info("Starting knowledge test");
//                moveToElement(startTestButton);
//                startTestButton.click();
//            } else {
//                m_logger.error("Start test button not visible");
//            }
        }
        else if (waitForVisibility(customerPromptKnowledgeTest, 3, "return", "") != null) {
            m_logger.info("Knowledge Test notification present");
            moveToElement(customerPromptKnowledgeTest);
            customerPromptKnowledgeTest.click();
            if (waitForVisibility(startTestButton, 10, "return", "") != null) {
                m_logger.info("Starting knowledge test");
                moveToElement(startTestButton);
                startTestButton.click();
            } else {
                m_logger.error("Start test button not visible");
            }
        } else if (waitForVisibility(knowledgeTest, 3, "return", "") != null) {
            moveToElement(knowledgeTest);
            knowledgeTest.click();
            OnBoarding onBoardingPlatVerif = PageFactory.initElements(driver, OnBoarding.class);
            onBoardingPlatVerif.verifyPlatformLogo();
            startTest(testCountryID);
        } else {
            try {
                startTest(testCountryID);
            } catch(Exception e) {
                m_logger.error("Knowledge test not displayed");
            }
        }
    }

    public void completeRiskWarningKnowledgeTest(String testCountryID) throws IOException {
        Countries country = Countries.getCountry(testCountryID);
        Translations translations = new Translations(country.language());
        String myAccounts = translations.getTranslation("myAccounts");
        driver.switchTo().defaultContent();
        WebElement myAccountTab;
        try {
            myAccountTab = driver.findElement(By.xpath(".//p[text()='" + myAccounts + "']"));
            moveToElement(myAccountTab);
            myAccountTab.click();
        } catch (Exception e1) {
            moveToElement(customerHubButton);
            customerHubButton.click();
            myAccountTab = driver.findElement(By.xpath(".//p[text()='" + myAccounts + "']"));
            moveToElement(myAccountTab);
            myAccountTab.click();
        }
        switchToFrame(customerHubFrame);
        completeRiskWarning(testCountryID);
        wait.until(ExpectedConditions.visibilityOf(startTestButton));
        startTestButton.click();
        completeKnowledgeTest(country);
    }

    private void completeRiskWarning(String testCountryID) throws IOException {
        try {
            Countries country = Countries.getCountry(testCountryID);
            Translations translations = new Translations(country.language());
            String riskWarningKnowledgeTest = translations.getTranslation("riskWarningKnowledgeTest");
            WebElement riskWarningKnowledgeTestlink = driver.findElement(By.xpath(".//div[text()='" + riskWarningKnowledgeTest + "']"));
            riskWarningKnowledgeTestlink.click();
            wait.until(ExpectedConditions.visibilityOf(riskWarningAccept));
            riskWarningAccept.click();
        }
        catch(Exception e) {
            m_logger.info("Error completing risk warning in hub");
            e.printStackTrace();
        }
    }

    private void startTest(String testCountryID) throws IOException {
        Countries country = Countries.getCountry(testCountryID);
        Translations translations = new Translations(country.language());
        String openKnowledgeTest = translations.getTranslation("openKnowledgeTest");
        driver.switchTo().defaultContent();
        WebElement openKnowledgeTestTab;
        try {
            openKnowledgeTestTab = driver.findElement(By.xpath(".//p[text()='" + openKnowledgeTest + "']"));
            moveToElement(openKnowledgeTestTab);
            openKnowledgeTestTab.click();
        } catch (Exception e1) {
            moveToElement(customerHubButton);
            customerHubButton.click();
            openKnowledgeTestTab = driver.findElement(By.xpath(".//p[text()='" + openKnowledgeTest + "']"));
            moveToElement(openKnowledgeTestTab);
            openKnowledgeTestTab.click();
        }
//        if (waitForVisibility(startTestButton, 20, "return", "") == null) {
//            moveToElement(openKnowledgeTestTab);
//            openKnowledgeTestTab.click();
//        }
        sleep(3000);
        switchToFrame(customerHubFrame);
        if (waitForVisibility(startTestButton, 10, "return", "") != null) {
            m_logger.info("Starting knowledge test");
            moveToElement(startTestButton);
            startTestButton.click();
        } else {
            m_logger.error("Start test button not visible");
        }
    }

    public void setQuestionAnswer() {
        m_logger.info("Selecting English knowledge test question and answers");
        knowledgeTestQA = questionAnswerEn;
    }

    public void clickOnTryagain() {
        waitForVisibility(retryButton, 60, "", "").click();
    }

    public void verifyCoolingOffdaysDisplayed(String days) {
        String xpath = ".//p[@data-cy='cooling-period-text']//p[text()='" + days + "']";
        m_logger.info(xpath);


        try {
          //  driver.switchTo().defaultContent();
            Thread.sleep(2000);
            switchToFrame(customerHubFrame);
            Thread.sleep(1000);
//            WebElement ele = driver.findElement(By.xpath(xpath));

            if (waitForVisibility(coolingOff1Day, 10, "return", "") != null)
                m_logger.info("Cooling off period text found as expected, days: " + days);
            else
                m_logger.error("Cooling off period text not found as expected, days expected: " + days + " found: " + coolingOff1Day.getText());


        } catch (Exception e) {
            e.printStackTrace();
            m_logger.error("Failed to verify cooling off period ");
            screenshot.takeScreenshot("coolingperiod");
        }
    }

    public void completeKnowledgeTestToFail(String testCountryID) {

        setQuestionAnswer();
        int tries=0;
        while(waitForVisibility(question, 10, "return", "")!=null && tries<15) {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WebElement answer;
            try {
              //  moveToElement(answerA);
                answerA.click();
            }
            catch(Exception e) {
                m_logger.info("This question failed");
                answerA.click();

            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
          //  moveToElement(submitButton);
            submitButton.click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
          //  moveToElement(submitButton);
            submitButton.click();
            tries++;
        }

        if(waitForVisibility(passMessage, 10, "return", "")!=null) {
            m_logger.warn("Knowledge test passed!!!");
          //  moveToElement(closeButton);
            closeButton.click();
        }
        else m_logger.info("Knowledge test not successful!!!");


    }
    public void completeKnowledgeTest(Countries country) throws IOException {
        sleep(3000);
        setQuestionAnswer();
        int tries=0;
        while(waitForVisibility(question, 10, "return", "")!=null && tries<15) {

//            m_logger.info("Question from hub:" + question.getText());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            if(questionAnswerEn.containsKey(question.getText().replaceAll("\\s", ""))){
            WebElement answer;
            try {
                m_logger.info("Question as displayed on form: " + question.getText().replaceAll("\\s", ""));
                answer = driver.findElement(By.xpath(".//span[contains(text(),\"" + questionAnswerEn.get(question.getText().replaceAll("\\s", "")) + "\")]"));
            }
            catch(Exception e) {
                m_logger.info("This question failed");
                //e.printStackTrace();
                moveToElement(answerA);
                answerA.click();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e.printStackTrace();
                }
                moveToElement(submitButton);
                submitButton.click();
                if(waitForVisibility(fetchAnswer,10,"return","")!=null){
                    String correctAnswer = fetchAnswer.getText();
                    m_logger.info("The correct answer is: " +correctAnswer);
                }else if(waitForVisibility(fetchAnswerSelected,10,"return", "")!=null){
                    String correctAnswer = fetchAnswerSelected.getText();
                    m_logger.info("The correct answer is: " +correctAnswer);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e.printStackTrace();
                }
                moveToElement(submitButton);
                submitButton.click();
                tries++;
                continue;
            }
                moveToElement(answer);
                answer.click();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                moveToElement(submitButton);
                submitButton.click();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                moveToElement(submitButton);
                submitButton.click();
//            }
//            else {
//                m_logger.error("Question not found");
//                SearchUtils.screenShot(driver,"noQuestionKnowledgeTest");
//            }
            tries++;
        }

        if(translations==null)
            translations = new Translations(country.language());
        String passText = translations.getTranslation("passMessage");
        try {
            if(waitForVisibility(driver.findElement(By.xpath(".//p[text()=\""+passText+"\"]")), 3, "return", "")!=null) {
                m_logger.info("Knowledge test passed");
                moveToElement(closeButton);
                closeButton.click();
            }
            else m_logger.warn("Knowledge test not successful!!!");
        }
        catch(Exception e) {
            try {
                if (waitForVisibility(knowledgeTestFailedChart, 5, "return", "") != null)
                    m_logger.error("Knowledge test Failed!!!");
            }
            catch(Exception e1) {
                m_logger.warn("Knowledge test incomplete!!!");
            }
        }
    }

    public void removeSpace() {

        ArrayList<String> noSpace = new ArrayList<>();

        for(String key : questionAnswerEn.keySet()) {
            key = key.replaceAll("\\s", "");
            noSpace.add(key);
        }

        for(String entry : noSpace) {
            m_logger.info(entry);
        }
    }

}
