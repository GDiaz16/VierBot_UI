#include <Wire.h>
#include <Adafruit_PWMServoDriver.h>

class Articulacion {
  private:
    int angulo1 = 90;
    int angulo2 = 90;
    int servo;
  public:
    // Articulacion(int servo) ; //servo(0){}
    int* cola = new int[1];

    int getServo() {
      return servo;
    }
    int setServo(int n) {
      servo = n;
    }
  int getAngulo(){
    return angulo1;
  }
    void plusOne() {
      if (abs(angulo2 - angulo1) > 0 && angulo2 > angulo1) {
        angulo1++;
      } else if (abs(angulo2 - angulo1) > 0 && angulo2 < angulo1) {
        angulo1--;
      } else if (abs(angulo2 - angulo1) <= 0 && sizeof(cola) > 0) {
        angulo1 = angulo2;
        angulo2 = pop();
      }
    }

    void addAngulo(int element) {
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

Adafruit_PWMServoDriver servos = Adafruit_PWMServoDriver(0x40);

unsigned int pos0 = 170; // ancho de pulso en cuentas para pocicion 0°
unsigned int pos180 = 580; // ancho de pulso en cuentas para la pocicion 180°
boolean flag = true;
Articulacion arts[12] ;

void setup() {
  servos.begin();
  servos.setPWMFreq(60); //Frecuecia PWM de 60Hz o T=16,66ms
  Serial.begin(9600);

  for (int i = 0; i < 12; i++) {
    arts[i].setServo(i);
  }
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
      if (servo.toInt() >= 0 && servo.toInt() < 12 ) {
        arts[servo.toInt()].addAngulo(angulo.toInt());
      }
      Serial.println(s);

    }
  }

  for (int i = 0; i < 12; i++) {
    arts[i].plusOne();
    
  setServo(arts[i].getServo(), arts[i].getAngulo());
  }

  delay(10);

}
