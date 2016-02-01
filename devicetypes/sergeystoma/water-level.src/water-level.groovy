/**
 *  HC-SR04 Based Water Level Sensor
 *
 *  Copyright 2016 Sergey Stoma
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
 */
metadata {
	definition (name: "Water Level", namespace: "sergeystoma", author: "Sergey Stoma") {
		capability "Water Sensor"
        capability "Refresh"
        
        attribute "level", "number"
	}

	simulator {
		// TODO: define status and reply messages here
	}

	tiles {
		standardTile("water", "device.water", width: 2, height: 2) {
            state "dry", icon:"st.alarm.water.dry", backgroundColor:"#ffffff"
            state "wet", icon:"st.alarm.water.wet", backgroundColor:"#53a7c0"
        }
        //valueTile("level", "device.level", decoration: "flat") {
        //    state "level", label:'${currentValue}%', unit:"cm"
        //}

		main(["water"])
        details(["water"])
        //main(["water", "level"])
	}
    
    preferences {
		section {
			input(title: "Set up", description: "Configure how far is the sensor away from the floor, aka zero depth.", displayDuringSetup: true, type: "paragraph", element: "paragraph")
			input("floorLevel", "number", title: "Floor Level", description: "Floor distance in centimeters", range: "0..*", displayDuringSetup: true)
		}
		section {
			input("safeDepth", "number", title: "What is maximum safe water depth?", description: "Safe water depth in centimeters", range: "0..*", displayDuringSetup: true)
		}
	}
}

// parse events into attributes
def parse(String description) {
	log.debug "Parsing '${description}'"
	// TODO: handle 'water' attribute
    // createEvent(name: "water", value: eventValue, descriptionText: "$device.displayName is $eventValue")
}

def refresh() {
	log.debug("Refresh!")
	sendEvent(name: "water", value: "dry")
    sendEvent(name: "level", value: 34, unit: "cm")
}