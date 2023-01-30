#include <ESP8266WiFi.h>
#include <ESP8266WiFiAP.h>
#include <ESP8266WiFiGeneric.h>
#include <ESP8266WiFiGratuitous.h>
#include <ESP8266WiFiMulti.h>
#include <ESP8266WiFiScan.h>
#include <ESP8266WiFiSTA.h>
#include <ESP8266WiFiType.h>
#include <WiFiClient.h>
#include <WiFiClientSecure.h>
#include <WiFiClientSecureAxTLS.h>
#include <WiFiClientSecureBearSSL.h>
#include <WiFiServer.h>
#include <WiFiServerSecure.h>
#include <WiFiServerSecureAxTLS.h>
#include <WiFiServerSecureBearSSL.h>
#include <WiFiUdp.h>

#include <dummy.h>


#include <Adafruit_Fingerprint.h>

#define Finger_Rx 14 //D5
#define Finger_Tx 12 //D6
const char *ssid = "Magnussen";  //ENTER YOUR WIFI SETTINGS
const char *password = "badenrolland";
int x; //int var host of options
  



SoftwareSerial mySerial(Finger_Rx, Finger_Tx);


Adafruit_Fingerprint finger = Adafruit_Fingerprint(&mySerial);

int id;
IPAddress ip,rip;
unsigned int localPort = 8000;
unsigned int rPort ;

char packetBuffer[UDP_TX_PACKET_MAX_SIZE + 1];


WiFiUDP Udp;

void setup()
{
  id = 1;
  Serial.begin(9600);
  while (!Serial);  // For Yun/Leo/Micro/Zero/...
  delay(100);
  Serial.println("\n\nAdafruit Fingerprint sensor enrollment");

  // set the data rate for the sensor serial port
  finger.begin(57600);

  if (finger.verifyPassword()) {
    Serial.println("Found fingerprint sensor!");
  } else {
    Serial.println("Did not find fingerprint sensor :(");
    while (1) { delay(1); }
  }

  Serial.println(F("Reading sensor parameters"));
  finger.getParameters();
  Serial.print(F("Status: 0x")); Serial.println(finger.status_reg, HEX);
  Serial.print(F("Sys ID: 0x")); Serial.println(finger.system_id, HEX);
  Serial.print(F("Capacity: ")); Serial.println(finger.capacity);
  Serial.print(F("Security level: ")); Serial.println(finger.security_level);
  Serial.print(F("Device address: ")); Serial.println(finger.device_addr, HEX);
  Serial.print(F("Packet len: ")); Serial.println(finger.packet_len);
  Serial.print(F("Baud rate: ")); Serial.println(finger.baud_rate);connectToWiFi();Udp.begin(localPort);
}


void loop()                     // run over and over again
{   

    ip = WiFi.localIP();
    int p = Udp.parsePacket();
    if ( p != 0)
    {
    x = UDPrecieve(p);
    
    //Serial.println(" Choose 0 to add or 1 to check ");// choice of options
    //while (! Serial.available()); x = Serial.parseInt();
    while ((x == 1) || (x == 2))  {
      if (x == 1){ 
        Serial.println("Ready to add a fingerprint ! ");
        while (!addFingerprint());
        x=5;
        }else if (x == 2){
           Serial.println("Ready to check a fingerprint ! ");
           int sid = checkFingerprint();UDPsend(sid);//send id to java udp server
           x=5;
         }
  }}
}

uint8_t addFingerprint() {
  
  int p = -1;
  Serial.print("Waiting for valid finger to enroll as #"); Serial.println(id);delay(5000);
  while (p != FINGERPRINT_OK) {
    p = finger.getImage();
    switch (p) {
    case FINGERPRINT_OK:
      Serial.println("Image taken");
      break;
    }
  }

  // OK success!
 p = finger.image2Tz(1);
  switch (p) {
    case FINGERPRINT_OK:
      Serial.println("Image converted");
      break;
  }

  Serial.println("Remove finger");
  delay(2000);
  p = 0;
  while (p != FINGERPRINT_NOFINGER) {
    p = finger.getImage();
  }
  Serial.print("ID "); Serial.println(id);
  p = -1;
  Serial.println("Place same finger again");
  while (p != FINGERPRINT_OK) {
    p = finger.getImage();
    switch (p) {
    case FINGERPRINT_OK:
      Serial.println("Image taken");
      break;
    }
  }

  // OK success!

  p = finger.image2Tz(2);
  switch (p) {
    case FINGERPRINT_OK:
      Serial.println("Image converted");
      break;
  }

  // OK converted!
  Serial.print("Creating model for #");  Serial.println(id);

  p = finger.createModel();
  if (p == FINGERPRINT_OK) {
    Serial.println("Prints matched!");
  } else if (p == FINGERPRINT_PACKETRECIEVEERR) {
    Serial.println("Communication error");
    return p;
  } else if (p == FINGERPRINT_ENROLLMISMATCH) {
    Serial.println("Fingerprints did not match");
    return p;
  } else {
    Serial.println("Unknown error");
    return p;
  }

  Serial.print("ID "); Serial.println(id);
  p = finger.storeModel(id);
  if (p == FINGERPRINT_OK) {
    Serial.println("Stored!");
  
  } else if (p == FINGERPRINT_PACKETRECIEVEERR) {
    Serial.println("Communication error");
    return p;
  } else if (p == FINGERPRINT_BADLOCATION) {
    Serial.println("Could not store in that location");
    return p; 
  } else if (p == FINGERPRINT_FLASHERR) {
    Serial.println("Error writing to flash");
    return p;
  } else {
    Serial.println("Unknown error");
    return p;
  }
  UDPsend(id);//send id to java udp server 
  id++;
  return true;

  }
  
uint8_t checkFingerprint() {
  int p = -1;
  Serial.print("Waiting for valid finger to check # ");
while (p != FINGERPRINT_OK) {
  p = finger.getImage();
  switch (p) {
    case FINGERPRINT_OK:
      Serial.println("Image taken");
      break;
  }
} 

  // OK success!

  p = finger.image2Tz();
  switch (p) {
    case FINGERPRINT_OK:
      Serial.println("Image converted");
      break;
  }
  Serial.println("Remove finger");delay(2000);

  // OK converted!
  p = finger.fingerSearch();
  if (p == FINGERPRINT_OK) {// found a match! 
    Serial.println("Found a print match! ");
  } else if (p == FINGERPRINT_PACKETRECIEVEERR) {
    Serial.println("Communication error");
    return p;
  } else if (p == FINGERPRINT_NOTFOUND) {
    Serial.println("Did not find a match");
    return p;
  } else {
    Serial.println("Unknown error");
    return p;
  }
   Serial.print("Finger ID is "); Serial.print(finger.fingerID);
   Serial.print(" with confidence of "); Serial.println(finger.confidence);
   return finger.fingerID;
}
 void connectToWiFi(){
    WiFi.mode(WIFI_OFF);        //Prevents reconnection issue (taking too long to connect)
    delay(1000);
    WiFi.mode(WIFI_STA);
    Serial.print("Connecting to wifi :  ");
    Serial.println(ssid);
    WiFi.begin(ssid, password);
 
    while (WiFi.status() != WL_CONNECTED) {
      delay(500);
      Serial.print(".");
    }
    Serial.println("");
    Serial.println("Connected");
    
    
    Serial.print("IP address: ");
    Serial.println(WiFi.localIP());  //IP address assigned to your ESP
 
}
int UDPrecieve(int p){
  Serial.printf("UDP server on port %d\n", localPort);
    // if there's data available, read a packet
  //int packetSize = Udp.parsePacket();
  if (p != 0) {
    rip = Udp.remoteIP();rPort = Udp.remotePort();
    Serial.printf("Received packet of size %d from %s:%d\n    (to %s:%d, free heap = %d B)\n",
                  p,
                  rip.toString().c_str(), rPort,
                  Udp.destinationIP().toString().c_str(), Udp.localPort(),
                  ESP.getFreeHeap()); 

    // read the packet into packetBufffer
    int n = Udp.read(packetBuffer, UDP_TX_PACKET_MAX_SIZE);
    packetBuffer[n] = 0;
    if (packetBuffer[0] == '1' ){ //add case
    Serial.println("Data recieved is : ");Serial.println(packetBuffer);
    
    return 1;}else if (packetBuffer[0] == '2') { //check case
      Serial.println("Data recieved is : ");Serial.println(packetBuffer);
      return 2;
      }
    packetBuffer[0] = 0;
    return 5;   
  
  }}

 void UDPsend(int id){
  char RB[4];
  unsigned int remotePort = 8888; 
  IPAddress remoteIP(192,168,43,195);
  Udp.beginPacket(remoteIP,remotePort);
  delay(1000);
  char c[4];
  String sid = String(id);
  sid.toCharArray(c,4);
  for (int i=0;i<5;i++){
   RB[i] = c[i] ;delay(1000);}
  Udp.write(RB);delay(1000);
  Udp.endPacket();

  }
