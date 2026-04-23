import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.ConditionType as ConditionType

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.url)

WebUI.waitForPageLoad(10)

// Input data first name
if (Firstname == "" || Firstname == null) {
    assert false : "Firstname wajib diisi"
}
WebUI.setText(findTestObject('Page_demosite/input_Name_firstName'), Firstname)

//input lastname
if (Lastname == "" || Lastname == null) {
	assert false : "Lastname wajib diisi"
}
WebUI.setText(findTestObject('Page_demosite/input_Name_lastName'), Lastname)


//inputemail
if (Email == null || !Email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}\$")) {
    assert false : "Email wajib dalam format nama@gmail.com"
}
WebUI.setText(findTestObject('Page_demosite/input_Email_userEmail'), Email)





// Validasi Gender
if ((Gender == null) || (Gender.trim() == '')) {
    WebUI.comment('Gender kosong!')

    assert false
}

// Normalisasi (Male → male)
String genderValue = Gender.toLowerCase().trim()

// Tentukan object berdasarkan gender
TestObject genderOption

if (genderValue == 'male') {
    genderOption = findTestObject('Page_demosite/input_Gender_gender-radio-1')
} else if (genderValue == 'female') {
    genderOption = findTestObject('Page_demosite/input_Male_gender-radio-2')
} else if (genderValue == 'other') {
    genderOption = findTestObject('Page_demosite/input_Female_gender-radio-3')
} else {
    WebUI.comment('Gender tidak valid: ' + genderValue)

    assert false
}

// Klik gender
WebUI.waitForElementClickable(genderOption, 10)

WebUI.click(genderOption)

WebUI.click(findTestObject('Page_demosite/input_(10 Digits)_userNumber'), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementClickable(findTestObject('Page_demosite/input_(10 Digits)_userNumber'), 5)

//input mobile number
WebUI.setText(findTestObject('Page_demosite/input_(10 Digits)_userNumber'), PhoneNumber.toString())

// hari lahir
// Klik field DOB
WebUI.click(findTestObject('Page_demosite/input_Date of Birth_dateOfBirthInput'))

// Pilih tahun
WebUI.selectOptionByLabel(findTestObject('Page_demosite/select_tahun'), Tahun, false)

// Pilih bulan
WebUI.selectOptionByLabel(findTestObject('Page_demosite/select_bulan'), Bulan, false)

// Klik tanggal
TestObject dayObject = new TestObject()

dayObject.addProperty('xpath', ConditionType.EQUALS, ('//div[contains(@class,\'react-datepicker__day\') and not(contains(@class,\'outside-month\')) and text()=\'' + 
    Hari) + '\']')

WebUI.click(dayObject)

//input subjects
WebUI.click(findTestObject('Page_demosite/input_Subjects_subjectsInput'))

WebUI.setText(findTestObject('Page_demosite/input_Subjects_subjectsInput'), Subjects)

// HOBBY
String hobbyValue = Hobby.toLowerCase().trim()

// Tentukan Hobby
TestObject hobbyOption

if (hobbyValue == 'music') {
    hobbyOption = findTestObject('Page_demosite/hobbymusic')
} else if (hobbyValue == 'sports') {
    hobbyOption = findTestObject('Page_demosite/hobbysports')
} else if (hobbyValue == 'reading') {
    hobbyOption = findTestObject('Page_demosite/hobbyreadings')
} else {
    WebUI.comment('Hobby tidak valid: ' + hobbyValue)

    assert false
}

WebUI.waitForElementClickable(hobbyOption, 10)

WebUI.click(hobbyOption)

//uploadfile
String projectPath = System.getProperty('user.dir')

String filePath = (projectPath + '/Data Files/Upload/') + Picture

if (!(new File(filePath).exists())) {
    WebUI.comment('File tidak ditemukan: ' + filePath)

    assert false
}

WebUI.uploadFile(findTestObject('Page_demosite/uploadfile1'), filePath)

//currentaddress
WebUI.click(findTestObject('Page_demosite/textarea_Current Address_currentAddress'))

WebUI.setText(findTestObject('Page_demosite/textarea_Current Address_currentAddress'), Alamat)

//klick STATE
WebUI.click(findTestObject('Page_demosite/select_state'))

// Pilih State dari Excel
TestObject stateOption = new TestObject()

stateOption.addProperty('xpath', ConditionType.EQUALS, ('//div[text()=\'' + State) + '\']')

WebUI.click(stateOption)

// Klik City
WebUI.click(findTestObject('Page_demosite/select_city'))

// Pilih City (misal: Panipat)
TestObject cityOption = new TestObject()

cityOption.addProperty('xpath', ConditionType.EQUALS, ('//div[text()=\'' + City) + '\']')

WebUI.click(cityOption)

// Submit
WebUI.click(findTestObject('Page_demosite/button_Submit'))


WebUI.closeBrowser()

