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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import java.util.Arrays

// Open browser and login
WebUI.callTestCase(findTestCase('Login/TC_LOGIN_01'), [:], FailureHandling.STOP_ON_FAILURE)

// Use test data
def facility = findTestData('AppointmentData').getValue('Facility', 1)

def readmission = findTestData('AppointmentData').getValue('ApplyReadmission', 1)

def program = findTestData('AppointmentData').getValue('Program', 1)

def visitDate = findTestData('AppointmentData').getValue('VisitDate', 2)

def comment = findTestData('AppointmentData').getValue('Comment', 1)

// Fill form
WebUI.selectOptionByLabel(findTestObject('AppointmentPage/FacilityDropdown'), facility, false)

if (readmission == 'Yes') {
    WebUI.check(findTestObject('AppointmentPage/CheckboxReadmission'))
}

switch (program) {
    case 'Medicaid':
        WebUI.click(findTestObject('AppointmentPage/RadioMedicaid'))

        break
}

//WebUI.setText(findTestObject('AppointmentPage/CalendarInput'), visitDate)
WebUI.setText(findTestObject('AppointmentPage/CommentField'), comment)

WebUI.click(findTestObject('AppointmentPage/ApplyButton'))

WebUI.delay(2)

def dateField = findTestObject('AppointmentPage/CalendarInput')

String validationMessage = WebUI.executeJavaScript(
    'return arguments[0].validationMessage;',
    Arrays.asList(WebUI.findWebElement(dateField))
)

println("Validation Message: " + validationMessage)

// Verifikasi bahwa pesan yang ditampilkan sesuai
assert validationMessage == 'Please fill out this field.'

WebUI.takeScreenshot()

