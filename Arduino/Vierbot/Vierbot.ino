#include <Wire.h>
#include <Adafruit_PWMServoDriver.h>


Adafruit_PWMServoDriver servos = Adafruit_PWMServoDriver(0x40);

unsigned int pos0 = 170; // ancho de pulso en cuentas para pocicion 0°
unsigned int pos180 = 580; // ancho de pulso en cuentas para la pocicion 180°
boolean flag = true;

void setup() {
  servos.begin();
  servos.setPWMFreq(60); //Frecuecia PWM de 60Hz o T=16,66ms
  Serial.begin(9600);
}

void setServo(uint8_t n_servo, int angulo) {
  int duty;
  duty = map(angulo, 0, 180, pos0, pos180);
  servos.setPWM(n_servo, 0, duty);

}
String data;
String servo;
String angulo;
bool data1;
bool data2;

void loop() {
  if (Serial.available() > 0) {

    data = Serial.readStringUntil(';');


    if (data.equals("&")) {
      data1 = true;
    } else if (data1) {
      servo = data;
      data1 = false;
      data2 = true;
    } else if (data2) {
      angulo = data;
      data2 = false;
    } else if (data.equals("$")) {
      String s = servo + "   " +angulo;
      Serial.println(s);

    }
  }
 
  for (int i = 0; i < 6000; i++) {
    for (int j = 0; j < 6000; j++) {
      int x = 1605 * i * 1421 * j;
    }
  }
  ///hacer protocolo, transmision con flag de respuesta bidireccional

  //int coord[][2] = {{37, 87}, {33, 85}, {29, 81}, {27, 76}, {17, 82}, {9, 92}, {11, 98}, {7, 112}, {12, 115}, {18, 118}, {25, 119}, {34, 119}, {42, 118}, {51, 115}, {60, 112}, {69, 107}, {70, 98}, {78, 92}, {88, 75}, {100, 53}, {83, 70}, {75, 76}, {68, 81}, {61, 85}, {54, 87}, {48, 88}, {42, 88}, {37, 87}};
  //setServo(servo.toInt(), angulo.toInt());
  delay(10);

}
