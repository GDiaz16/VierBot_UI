#include <Wire.h>
#include <Adafruit_PWMServoDriver.h>


Adafruit_PWMServoDriver servos = Adafruit_PWMServoDriver(0x40);

unsigned int pos0 = 170; // ancho de pulso en cuentas para pocicion 0°
unsigned int pos180 = 580; // ancho de pulso en cuentas para la pocicion 180°
boolean flag = true;
class Articulacion {
  private:
    int angulo1 = 90;
    int angulo2 = 90;
    int servo;
  public:
  Articulacion():  servo(0){}
    int* cola = new int[1];
    void addAngulo(int angulo) {
      add(angulo);
    }
    int getServo(){
      return servo;
    }
    
    void plusOne() {
      if (abs(angulo2 - angulo1) > 0 && angulo2 > angulo1) {
        angulo1++;
      } else if (abs(angulo2 - angulo1) > 0 && angulo2 < angulo1) {
        angulo1--;
      }else if(abs(angulo2 - angulo1)<=0 && sizeof(cola)>0){
        angulo1 = angulo2;
        angulo2=pop();
      }
    }






    void add(int element) {
      int* aux = new int[sizeof(cola)];
      for (int i = 0 ; i < sizeof(cola); i++) {
        aux[i] = cola[i];
      }
      cola = new int[sizeof(aux) + 1];
      for (int i = 0 ; i < sizeof(aux); i++) {
        cola[i] = aux[i];
      }
      cola[sizeof(cola) - 1] = element;
    }
    int pop() {
      int popped;
      if (sizeof(cola) > 0) {
        int* aux = new int[sizeof(cola) - 1];
        for (int i = 0 ; i < sizeof(cola) - 1; i++) {
          aux[i] = cola[i];
        }
        popped = cola[sizeof(cola) - 1];
        cola = new int[sizeof(aux)];
        for (int i = 0 ; i < sizeof(aux); i++) {
          cola[i] = aux[i];
        }
      }
      return popped;
    }

};

void setup() {
  servos.begin();
  servos.setPWMFreq(60); //Frecuecia PWM de 60Hz o T=16,66ms
  Serial.begin(9600);

  Articulacion arts[12] ;
}

void setServo(uint8_t n_servo, int angulo) {
  int duty;
  duty = map(angulo, 0, 180, pos0, pos180);
  servos.setPWM(n_servo, 0, duty);

}
String data;

String servo = "12";
String angulo = "90";

String servo1;
String angulo1;

bool data1;
bool data2;

void loop() {
  Serial.println("%");
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
      String s = servo + "   " + angulo;
      Serial.println(s);

    }
  }

  for (int i = angulo.toInt(); i < 140; i++) {
    setServo(5, i);
    delay(10);
  }
  for (int i = 140; i > 30; i--) {
    setServo(5, i);
    delay(10);
  }


  ///hacer protocolo, transmision con flag de respuesta bidireccional

  //int coord[][2] = {{37, 87}, {33, 85}, {29, 81}, {27, 76}, {17, 82}, {9, 92}, {11, 98}, {7, 112}, {12, 115}, {18, 118}, {25, 119}, {34, 119}, {42, 118}, {51, 115}, {60, 112}, {69, 107}, {70, 98}, {78, 92}, {88, 75}, {100, 53}, {83, 70}, {75, 76}, {68, 81}, {61, 85}, {54, 87}, {48, 88}, {42, 88}, {37, 87}};
  //setServo(servo.toInt(), angulo.toInt());
  delay(10);

}
