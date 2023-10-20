package org.example;
//Подключения необходимых библиотек
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//Класс окна, в котором размещено игровое поле
public class Okno extends JFrame {
    public Pole gameP;// Закрытая переменная класса - игровое поле
//    Обработчик событий нажатий на клавиши
    private class myKey implements KeyListener{
    //      Метод, который срабатывает при нажатии клавиши
    @Override
    public void keyPressed(KeyEvent e) {
//        Получение кода нажатой клавиши
        int key_ = e.getKeyCode();
//        Выход из программы, если нажата клавиша - Esc
        if (key_ == 27)System.exit(0);
        if(key_ == 37 || key_ == 65){// Если нажата стрелка в лево или клавиша A
//            Контроль перемещения в лево за пределы экрана
            if(gameP.x -3> -100)gameP.x-=15;
            else gameP.x = 752;
        }
        if(key_ == 39 || key_ == 68){// Если нажата стрелка в право или клавиша D
//            Контроль перемещения в право за пределы окна
            if(gameP.x +3<752)gameP.x+=15;
            else gameP.x = -100;
        }
    }
//    Пустые методы
    @Override
        public void keyReleased(KeyEvent e) {
        }
    @Override
        public void keyTyped(KeyEvent e) {
        }
    }
//    Конструктор класса
    public Okno(int slogn){
//        Подключение обработчика события для клавиатуры к окну
        addKeyListener(new myKey());
//        Установка активности окна
        setFocusable(true);
//        Задание размеров и расположения окна
        setBounds(0,0,800,600);
//        Задание заголовка окна
        setTitle("Игра: Новогодний дождь");
//        Создание объекта - игрового поля
        gameP = new Pole(slogn);
//        Прикрепление(вложение) панели - игрового поля в окно
        Container con = getContentPane();
        con.add(gameP);
//        Сделать окно видимым
        setVisible(true);
    }
}
