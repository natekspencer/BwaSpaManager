/*
 *  BWA Spa
 *
 *  Copyright 2020 Nathan Spencer
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 *  CHANGE HISTORY
 *  VERSION     DATE            NOTES
 *  0.9.0       2020-01-30      Initial release with basic access and control of spas
 *  1.0.0       2020-01-31      Updated UI and icons as well as switch functionality that can be controlled with
 *                              Alexa. Added preference for a "Default Temperature When Turned On"
 *
 */

/*
 Button Codes
 4  - Pump1
 5  - Pump2
 6  - Pump3
 7  - Pump4
 8  - Pump5
 9  - Pump6
 12 - Blower
 14 - Mister
 17 - Light1
 18 - Light2
 22 - Aux1
 23 - Aux2
 80 - TempRange
 81 - HeatMode
 */

import groovy.time.TimeCategory

metadata {
    definition (name: "BWA Spa", namespace: "natekspencer", author: "Nathan Spencer") {
        capability "Actuator"
        capability "Temperature Measurement"
        capability "Thermostat Heating Setpoint"
        capability "Thermostat Mode"
        capability "Thermostat Operating State"
        capability "Refresh"
        capability "Sensor"
        capability "Switch"
        
        attribute "temperatureScale", "string"
        attribute "pump1", "string"
        attribute "pump2", "string"
        attribute "pump3", "string"
        attribute "pump4", "string"
        attribute "pump5", "string"
        attribute "pump6", "string"
        attribute "blower", "string"
        attribute "mister", "string"
        attribute "light1", "string"
        attribute "light2", "string"
        
        command "toggleSwitch"
        command "togglePump1"
        command "togglePump2"
        command "togglePump3"
        command "togglePump4"
        command "togglePump5"
        command "togglePump6"
        command "toggleBlower"
        command "toggleMister"
        command "toggleLight1"
        command "toggleLight2"
    }
    
    preferences {
        input "defaultOnTemperature", "number", title: "Default Temperature When Turned On", range: getTemperatureRange()
    }

    tiles(scale: 2) {
        multiAttributeTile(name: "temperature", type: "generic", width: 6, height: 4, canChangeIcon: true) {
            tileAttribute("device.temperature", key: "PRIMARY_CONTROL") {
                attributeState "temperature", label: '${currentValue}°', unit:"dF", action: "toggleSwitch", icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/hot-tub.png", canChangeIcon: true, defaultState: true, backgroundColors: [
                	[value: 80, color: "#153591"],
            		[value: 84, color: "#1e9cbb"],
            		[value: 88, color: "#90d2a7"],
            		[value: 92, color: "#44b621"],
            		[value: 96, color: "#f1d801"],
            		[value: 100, color: "#d04e00"],
            		[value: 104, color: "#bc2323"]
        		]
            }
            tileAttribute("device.spaStatus", key: "SECONDARY_CONTROL") {
                attributeState("spaStatus", label:'${currentValue}', defaultState: true)
            }
        }
        
        controlTile("heatSliderControl", "device.heatingSetpoint", "slider", width: 2, height: 1, inactiveLabel: false, range: getTemperatureRange()) {
			state "heatingSetpoint", action:"setHeatingSetpoint", backgroundColor: "#d04e00"
		}

        standardTile("pump1", "device.pump1", width: 2, height: 1, decoration: "flat") {
            state "off"            , label: 'pump 1: ${currentValue}', action: "togglePump1", nextState: "setting to low" , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-off.png" , defaultState: true
            state "setting to low" , label: 'pump 1: ${currentValue}', action: "togglePump1", nextState: "setting to low" , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-off.png"
            state "low"            , label: 'pump 1: ${currentValue}', action: "togglePump1", nextState: "setting to high", icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-low.png"
            state "setting to high", label: 'pump 1: ${currentValue}', action: "togglePump1", nextState: "setting to high", icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-low.png"
            state "high"           , label: 'pump 1: ${currentValue}', action: "togglePump1", nextState: "turning off"    , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-high.png"
            state "turning off"    , label: 'pump 1: ${currentValue}', action: "togglePump1", nextState: "turning off"    , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-high.png"
        }

        standardTile("pump2", "device.pump2", width: 2, height: 1, decoration: "flat") {
            state "off"            , label: 'pump 2: ${currentValue}', action: "togglePump2", nextState: "setting to low" , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-off.png" , defaultState: true
            state "setting to low" , label: 'pump 2: ${currentValue}', action: "togglePump2", nextState: "setting to low" , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-off.png"
            state "low"            , label: 'pump 2: ${currentValue}', action: "togglePump2", nextState: "setting to high", icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-low.png"
            state "setting to high", label: 'pump 2: ${currentValue}', action: "togglePump2", nextState: "setting to high", icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-low.png"
            state "high"           , label: 'pump 2: ${currentValue}', action: "togglePump2", nextState: "turning off"    , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-high.png"
            state "turning off"    , label: 'pump 2: ${currentValue}', action: "togglePump2", nextState: "turning off"    , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-high.png"
        }

        standardTile("pump3", "device.pump3", width: 2, height: 1, decoration: "flat") {
            state "off"            , label: 'pump 3: ${currentValue}', action: "togglePump3", nextState: "setting to low" , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-off.png" , defaultState: true
            state "setting to low" , label: 'pump 3: ${currentValue}', action: "togglePump3", nextState: "setting to low" , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-off.png"
            state "low"            , label: 'pump 3: ${currentValue}', action: "togglePump3", nextState: "setting to high", icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-low.png"
            state "setting to high", label: 'pump 3: ${currentValue}', action: "togglePump3", nextState: "setting to high", icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-low.png"
            state "high"           , label: 'pump 3: ${currentValue}', action: "togglePump3", nextState: "turning off"    , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-high.png"
            state "turning off"    , label: 'pump 3: ${currentValue}', action: "togglePump3", nextState: "turning off"    , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-high.png"
        }

        standardTile("pump4", "device.pump4", width: 2, height: 1, decoration: "flat") {
            state "off"            , label: 'pump 4: ${currentValue}', action: "togglePump4", nextState: "setting to low" , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-off.png" , defaultState: true
            state "setting to low" , label: 'pump 4: ${currentValue}', action: "togglePump4", nextState: "setting to low" , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-off.png"
            state "low"            , label: 'pump 4: ${currentValue}', action: "togglePump4", nextState: "setting to high", icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-low.png"
            state "setting to high", label: 'pump 4: ${currentValue}', action: "togglePump4", nextState: "setting to high", icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-low.png"
            state "high"           , label: 'pump 4: ${currentValue}', action: "togglePump4", nextState: "turning off"    , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-high.png"
            state "turning off"    , label: 'pump 4: ${currentValue}', action: "togglePump4", nextState: "turning off"    , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-high.png"
        }

        standardTile("pump5", "device.pump5", width: 2, height: 1, decoration: "flat") {
            state "off"            , label: 'pump 5: ${currentValue}', action: "togglePump5", nextState: "setting to low" , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-off.png" , defaultState: true
            state "setting to low" , label: 'pump 5: ${currentValue}', action: "togglePump5", nextState: "setting to low" , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-off.png"
            state "low"            , label: 'pump 5: ${currentValue}', action: "togglePump5", nextState: "setting to high", icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-low.png"
            state "setting to high", label: 'pump 5: ${currentValue}', action: "togglePump5", nextState: "setting to high", icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-low.png"
            state "high"           , label: 'pump 5: ${currentValue}', action: "togglePump5", nextState: "turning off"    , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-high.png"
            state "turning off"    , label: 'pump 5: ${currentValue}', action: "togglePump5", nextState: "turning off"    , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-high.png"
        }

        standardTile("pump6", "device.pump6", width: 2, height: 1, decoration: "flat") {
            state "off"            , label: 'pump 6: ${currentValue}', action: "togglePump6", nextState: "setting to low" , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-off.png" , defaultState: true
            state "setting to low" , label: 'pump 6: ${currentValue}', action: "togglePump6", nextState: "setting to low" , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-off.png"
            state "low"            , label: 'pump 6: ${currentValue}', action: "togglePump6", nextState: "setting to high", icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-low.png"
            state "setting to high", label: 'pump 6: ${currentValue}', action: "togglePump6", nextState: "setting to high", icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-low.png"
            state "high"           , label: 'pump 6: ${currentValue}', action: "togglePump6", nextState: "turning off"    , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-high.png"
            state "turning off"    , label: 'pump 6: ${currentValue}', action: "togglePump6", nextState: "turning off"    , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-high.png"
        }

        standardTile("blower", "device.blower", width: 2, height: 1, decoration: "flat") {
            state "off"              , label: 'blower: ${currentValue}', action: "toggleBlower", nextState: "setting to low"   , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-off.png" , defaultState: true
            state "setting to low"   , label: 'blower: ${currentValue}', action: "toggleBlower", nextState: "setting to low"   , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-off.png"
            state "low"              , label: 'blower: ${currentValue}', action: "toggleBlower", nextState: "setting to medium", icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-low.png"
            state "setting to medium", label: 'blower: ${currentValue}', action: "toggleBlower", nextState: "setting to medium", icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-low.png"
            state "medium"           , label: 'blower: ${currentValue}', action: "toggleBlower", nextState: "setting to high"  , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-med.png"
            state "setting to high"  , label: 'blower: ${currentValue}', action: "toggleBlower", nextState: "setting to high"  , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-med.png"
            state "high"             , label: 'blower: ${currentValue}', action: "toggleBlower", nextState: "turning off"      , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-high.png"
            state "turning off"      , label: 'blower: ${currentValue}', action: "toggleBlower", nextState: "turning off"      , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/fan-high.png"
        }
        
        standardTile("mister", "device.mister", width: 2, height: 1, decoration: "flat") {
            state "off"        , label: 'mister: ${currentValue}', action: "toggleMister", nextState: "turning on" , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/mist-off.png" , defaultState: true
            state "turning on" , label: 'mister: ${currentValue}', action: "toggleMister", nextState: "turning on" , icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/mist-off.png"
            state "on"         , label: 'mister: ${currentValue}', action: "toggleMister", nextState: "turning off", icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/mist-on.png"
            state "turning off", label: 'mister: ${currentValue}', action: "toggleMister", nextState: "turning off", icon: "https://raw.githubusercontent.com/natekspencer/BwaSpaManager/master/images/mist-on.png"
        }
        
        standardTile("light1", "device.light1", width: 2, height: 1, decoration: "flat") {
            state "default", label: 'light 1: ${currentValue}', action: "toggleLight1", icon: "st.switches.light.on" , defaultState: true
            state "off"    , label: 'light 1: ${currentValue}', action: "toggleLight1", icon: "st.switches.light.off"
        }
        
        standardTile("light2", "device.light2", width: 2, height: 1, decoration: "flat") {
            state "default", label: 'light 2: ${currentValue}', action: "toggleLight2", icon: "st.switches.light.on" , defaultState: true
            state "off"    , label: 'light 2: ${currentValue}', action: "toggleLight2", icon: "st.switches.light.off"
        }

        standardTile("refresh", "device.refresh", width: 2, height: 1, decoration: "flat") {
            state "refresh", label: 'refresh', action: "refresh", icon: "st.secondary.refresh", defaultState: true
        }

        main("temperature")
        details(["temperature"
            , "pump1", "heatSliderControl", "pump2"
            , "pump3", "blower", "pump4"
            , "pump5", "mister", "pump6"
            , "light1", "refresh", "light2"])
    }
}

def installed() {
}

def updated() {
}

def toggleSwitch() {
    if (device.currentValue("switch") == "on") {
        off()
    } else {
        on()
    }
}

def on() {
    if (defaultOnTemperature) {
        sendEvent(name: "previousHeatingSetpoint", value: device.currentValue("heatingSetpoint"), displayed: false)
        setHeatingSetpoint(defaultOnTemperature)
    }
    if (device.currentValue("pump1") == "off"
     && device.currentValue("pump2") == "off"
     && device.currentValue("pump3") == "off"
     && device.currentValue("pump4") == "off"
     && device.currentValue("pump5") == "off"
     && device.currentValue("pump6") == "off") {
        togglePump1()
    }
}

def off() {
    def checkAgain = false
    if (device.currentValue("pump1") != "off") {
        togglePump1()
        checkAgain = true
    }
    if (device.currentValue("pump2") != "off") {
        togglePump2()
        checkAgain = true
    }
    if (device.currentValue("pump3") != "off") {
        togglePump3()
        checkAgain = true
    }
    if (device.currentValue("pump4") != "off") {
        togglePump4()
        checkAgain = true
    }
    if (device.currentValue("pump5") != "off") {
        togglePump5()
        checkAgain = true
    }
    if (device.currentValue("pump6") != "off") {
        togglePump6()
        checkAgain = true
    }
    if (device.currentValue("heatingSetpoint") != device.currentValue("previousHeatingSetpoint")) {
        setHeatingSetpoint(device.currentValue("previousHeatingSetpoint"))
    }
    if (checkAgain) {
        runIn(10, off)
    }
}

def toggleButton(name, value, levels = 2) {
    def currentState = device.currentValue(name)
    def nextState
    if (currentState == "off") {
        nextState = levels > 2 ? "setting to low" : "turning on"
    } else if (currentState == "low") {
        nextState = "setting to ${ levels > 3 ? "medium" : "high"}"
    } else if (currentState == "medium") {
        nextState = "setting to high"
    } else if (currentState == "high" || currentState == "on") {
        nextState = "turning off"
    }
    if (nextState) {
        sendEvent(name: name, value: nextState)
    }
	sendCommand("Button", value)
}

def togglePump1() {
    toggleButton("pump1", 4, 3)
}

def togglePump2() {
    toggleButton("pump2", 5, 3)
}

def togglePump3() {
    toggleButton("pump3", 6, 3)
}

def togglePump4() {
    toggleButton("pump4", 7, 3)
}

def togglePump5() {
    toggleButton("pump5", 8, 3)
}

def togglePump6() {
    toggleButton("pump6", 9, 3)
}

def toggleBlower() {
    toggleButton("blower", 12, 4)
}

def toggleMister() {
    toggleButton("mister", 14)
}

def toggleLight1() {
	toggleButton("light1", 17)
}

def toggleLight2() {
	toggleButton("light2", 18)
}

def setHeatingSetpoint(setpoint) {
	sendCommand("SetTemp", device.currentValue("temperatureScale") == "C" ? setpoint * 2 : setpoint)
    sendEvent(name: "heatingSetpoint", value: setpoint)
}

def sendCommand(action, data) {
	parent.sendCommand(device.currentValue("device_id"), action, data)
    runIn(2, refresh)
}

def refresh() {
    parent.pollChildren()
}

def parseDeviceData(Map results) {
    results.each {name, value ->
        sendEvent(name: name, value: value, displayed: false)
    }
}

def parsePanelData(encodedData) {
    byte[] decoded = encodedData.decodeBase64()
    
    def messageLength = new BigInteger(1, decoded[0])
    def actualTemperature = new BigInteger(1, decoded[6])
    def currentTimeHour = new BigInteger(1, decoded[7])
    def currentTimeMinute = new BigInteger(1, decoded[8])
    def heatMode
    switch (new BigInteger(1, decoded[9])) {
    	case 0:
        	heatMode = "ready"
            break
        case 1:
        	heatMode = "rest"
            break
        case 2:
        	heatMode = "ready in rest"
            break
        default:
        	heatMode = "none"
            break
    }
    def flag1 = new BigInteger(1, decoded[13])
    def is24HourTime = (flag1 & 2) != 0
    def filterMode
    switch (flag1 & 12) {
        case 4:
        	filterMode = "1"
            break
        case 8:
        	filterMode = "2"
            break
        case 12:
        	filterMode = "1 & 2"
            break
    	case 0:
        default:
        	filterMode = "off"
            break
    }
    def accessibilityType
    switch (flag1 & 48) {
    	case 16:
        	accessibilityType = "Pump Light"
            break
        case 32:
        case 48:
        	accessibilityType = "None"
            break
        default:
        	accessibilityType = "All"
            break
    }
    def temperatureScale = (flag1 & 1) == 0 ? "F" : "C"
    def flag2 = new BigInteger(1, decoded[14])
    def temperateRange = (flag2 & 4) == 4 ? "High" : "Low"
    def isHeating = (flag2 & 48) != 0
    def flag3 = new BigInteger(1, decoded[15])
    def pump1State
    switch (flag3 & 3) {
        case 1:
        	pump1State = "low"
            break
        case 2:
        	pump1State = "high"
            break
        default:
        	pump1State = "off"
            break
    }
    def pump2State
    switch (flag3 & 12) {
        case 4:
        	pump2State = "low"
            break
        case 8:
        	pump2State = "high"
            break
        default:
        	pump2State = "off"
            break
    }
    def pump3State
    switch (flag3 & 48) {
        case 16:
        	pump3State = "low"
            break
        case 32:
        	pump3State = "high"
            break
        default:
        	pump3State = "off"
            break
    }
    def pump4State
    switch (flag3 & 192) {
        case 64:
        	pump4State = "low"
            break
        case 128:
        	pump4State = "high"
            break
        default:
        	pump4State = "off"
            break
    }
    def flag4 = new BigInteger(1, decoded[16])
    def pump5State
    switch (flag4 & 3) {
        case 1:
        	pump5State = "low"
            break
        case 2:
        	pump5State = "high"
            break
        default:
        	pump5State = "off"
            break
    }
    def pump6State
    switch (flag4 & 12) {
        case 4:
        	pump6State = "low"
            break
        case 8:
        	pump6State = "high"
            break
        default:
        	pump6State = "off"
            break
    }
    def byte17 = new BigInteger(1, decoded[17])
    def blowerState
    switch (byte17 & 12) {
        case 4:
        	blowerState = "low"
            break
        case 8:
        	blowerState = "medium"
            break
        case 12:
        	blowerState = "high"
            break
        default:
        	blowerState = "off"
            break
    }
    def flag6 = new BigInteger(1, decoded[18])
    def light1On = (flag6 & 3) != 0
    def light2On = (flag6 & 12) != 0
    def byte19 = new BigInteger(1, decoded[19])
    def misterOn = (byte19 & 1) != 0
    def aux1On = (byte19 & 8) != 0
    def aux2On = (byte19 & 16) != 0
    def targetTemperature = new BigInteger(1, decoded[24])
    def byte26 = new BigInteger(1, decoded[26])
    def wifiState
    switch (byte26 & 240) {
    	case 0:
        	wifiState = "OK"
            break
        case 16:
        	wifiState = "Spa Not Communicating"
            break
        case 32:
        	wifiState = "Startup"
            break
        case 48:
        	wifiState = "Prime"
            break
        case 64:
        	wifiState = "Hold"
            break
        case 80:
        	wifiState = "Panel"
            break
    }
    def pumpStateStatus
    if (flag3 < 1 && flag4 < 1 && (byte17 & 3) < 1) {
    	pumpStateStatus = "Off"
    } else {
    	pumpStateStatus = isHeating ? "Low Heat" : "Low"
    }
    
    if (actualTemperature == 255) {
    	actualTemperature = device.currentValue("temperature") * (temperatureScale == "C" ? 2.0F : 1)
    }
    
    if (temperatureScale == "C") {
    	actualTemperature /= 2.0F
    	targetTemperature /= 2.0F
    }
    
	/*log.info ("Message Length: ${messageLength}\n"
           + "Actual Temperature: ${actualTemperature}\n"
           + "Current Time Hour: ${currentTimeHour}\n"
           + "Current Time Minute: ${currentTimeMinute}\n"
           + "Is 24-Hour Time: ${is24HourTime}\n"
           + "Temperature Scale: ${temperatureScale}\n"
           + "Target Temperature: ${targetTemperature}\n"
           + "Filter Mode: ${filterMode}\n"
           + "Accessibility Type: ${accessibilityType}\n"
           + "Temperate Range: ${temperateRange}\n"
           + "Light-1 On: ${light1On}\n"
           + "Light-2 On: ${light2On}\n"
           + "Heat Mode: ${heatMode}\n"
           + "Is Heating: ${isHeating}\n"
           + "pump1State: ${pump1State}\n"
           + "pump2State: ${pump2State}\n"
           + "pump3State: ${pump3State}\n"
           + "pump4State: ${pump4State}\n"
           + "pump5State: ${pump5State}\n"
           + "pump6State: ${pump6State}\n"
           + "blowerState: ${blowerState}\n"
           + "misterOn: ${misterOn}\n"
           + "aux1On: ${aux1On}\n"
           + "aux2On: ${aux2On}\n"
           + "pumpStateStatus: ${pumpStateStatus}\n"
           + "wifiState: ${wifiState}\n"
    )*/
    
    sendEvent(name: "switch", value: (pump1State == "off" && pump2State == "off" && pump3State == "off" && pump4State == "off" && pump5State == "off" && pump6State == "off") ? "off" : "on")
    sendEvent(name: "temperatureScale", value: temperatureScale)
    sendEvent(name: "temperature", value: actualTemperature, unit: temperatureScale)
    sendEvent(name: "heatingSetpoint", value: targetTemperature, unit: temperatureScale)
    sendEvent(name: "thermostatMode", value: isHeating ? "heat" : "auto")
    sendEvent(name: "thermostatOperatingMode", value: isHeating ? "heating" : "idle")
    sendEvent(name: "pump1", value: pump1State)
    sendEvent(name: "pump2", value: pump2State)
    sendEvent(name: "pump3", value: pump3State)
    sendEvent(name: "pump4", value: pump4State)
    sendEvent(name: "pump5", value: pump5State)
    sendEvent(name: "pump6", value: pump6State)
    sendEvent(name: "blower", value: blowerState)
    sendEvent(name: "mister", value: misterOn ? "on" : "off")
    sendEvent(name: "light1", value: light1On ? "on" : "off")
    sendEvent(name: "light2", value: light2On ? "on" : "off")
    sendEvent(name: "spaStatus", value: "${heatMode}\n${isHeating ? "heating to ${targetTemperature}°" : "not heating"}")
}

def getTemperatureRange() {
	"(26.5..104)"
}

def getTimeZone() {
    location.timeZone?:TimeZone.getDefault()
}