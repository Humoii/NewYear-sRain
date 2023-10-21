package org.example;
//Подключение необходимых библиотек
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
//Класс панели, которая является игровым полем
public class Pole extends JPanel {
    private  Image shapka;// Закрытая переменная класса, в которую загружается шапка
    private Image fon;// Закрытая переменная класса, в которую загружается фон
    public int x = 400;// Открытая статическая переменная класса, нахождения шапки по Х
    public int slogn;// Переменная сложности игры
    private Podar[] gamePodar;// Массив подарков
    private Image end_game;// Изображение Окончания игры
    public Timer timerUpdate, timerDraw;// Два таймера
//    Конструктор класса
public Pole(int slogn) {
//    Создаем переменную сложности в конструкторе
    this.slogn = slogn;
//    Загрузка изображения шапки из файла
    try{
        shapka = ImageIO.read((new File("Image\\shapka.png")));
    }
//    Проверка загрузки изображения
    catch(IOException ex) {
        throw new RuntimeException("Шапка не загрузилась проверти путь");
    }
//    Загрузка изображения фона из файла
    try{
        fon = ImageIO.read((new File("Image\\fon.png")));
    }
//        Проверка загрузки изображения
    catch(IOException ex) {
        throw new RuntimeException("Фон не загрузился проверти путь");
    }
    try {
//        Загрузка изображения Окончания игры из файла
    end_game = ImageIO.read((new File("Image\\end_game.png")));
    }
//    Проверка загрузки изображения
    catch(IOException ex) {
        throw new RuntimeException("Конец игры не загружается");
    }
//    Создание пустого массива
    gamePodar = new Podar[7];
//    Загрузка семи изображений подарков
    for(int i=0; i<7; i++){
        try {
            gamePodar[i] = new Podar(ImageIO.read(new File("Image\\p"+i+".png")));
//            Проверка загрузки изображения
        } catch (IOException ex) {
            throw new RuntimeException("Подарок не загрузился");
        }
    }
//    Создание таймера, который будет раз в три секунды проверять подарки и добавлять
//    их на игровое поле
    timerUpdate = new Timer(2000, new ActionListener() {
//        Метод для проверки и добавления подарков на игровое поле
        @Override
        public void actionPerformed(ActionEvent e) {
            updateStart();
        }
    });
    timerUpdate.start();
//Создание таймера который будет перерисовывать игровое поле 10 раз в секунду
    timerDraw = new Timer(10, new ActionListener() {
//        Запуск метода перерисовки поля
        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    });
//    Запуск таймера для перерисовки
    timerDraw.start();
}
// Метод который отрисовывает графические объекты на панели
    public void paintComponent(Graphics gr){
//    Выполнить сначала отрисовку самого окна
    super.paintComponent(gr);
    gr.drawImage(fon, 0, 0, null);// Рисование фона
    gr.drawImage(shapka, x, 475, null);//Рисование шапки
//        Цикл, который отображает подарки на игровом поле и проверяет пропущенные подарки
    for(int i = 0; i < 7; i++){
        gamePodar[i].draw(gr);// Отображение подарка
//        Если подарок из массива подарков активен
        if(gamePodar[i].act == true){
//            Если подарок достиг нижней границы
            if((gamePodar[i].y+gamePodar[i].img.getHeight(null))>=520){
//                Если подарок пропущен
                if (Math.abs(gamePodar[i].x - x) > 75) {
//                    Вывод картинки Окончания игры
                    gr.drawImage(end_game, 300, 300, null);
                    timerDraw.stop();// Остановка таймера
                    timerUpdate.stop();// Остановка таймера
                    break;//Прерывание цикла
//                    Снятия подарка с игрового поля, если он пойман шапкой
                }else gamePodar[i].act=false;
            }
        }
    }
    }
//    Метод для проверки и добавления подарков на игровое поле
    public void updateStart(){
        int kol = 0;// Переменная для подсчета подарков на игровом поле
        for(int i = 0; i < 7; i++){// Цикл перебора всех подарков массива
            if(gamePodar[i].act == false){// Если подарок не на игравом поле
                if(kol<slogn){// Если текущее количество менее номера сложности (от 1 до 7)
//                    Активизация подарка на игровом поле, вывод его сверху игрового поля
                    gamePodar[i].start();
                    break;// Прерывание цикла
                }
            }else kol++;// Если подарок на игровом поле
        }
    }

}

