1. Для создания пакета com.epam.jwd.app необходимо в папке проекта последовательно использовать команды:

mkdir com
mkdir com/epam
mkdir com/epam/jwd
mkdir com/epam/jwd/app
Для создания пакета com.epam.jwd.model в папке проекта после этих команд использовать:
mkdir com/epam/jwd/model


2.  Для создания папки lib использовать в папке проекта:

mkdir lib

Скачать необходимые jar с интернета и переместить их с папки загрузок в папку lib


3. Для компиляции против библиотек из папки lib необходимо переместиться в папку lib.
Для этого из папки проект использовать:

cd lib

Для компиляции против библиотек использовать:

javac -cp "../" ../com/epam/jwd/model/Dot.java (Для компиляции Dot.java)
javac -cp "../;./*" ../com/epam/jwd/app/MyAwesomeApplication.java (Для компиляции MyAwesomeApplication.java)


4. Выходим с папки lib в папку проекта:

cd ..

Создаем папку app:

mkdir app

Копируем в папку app папку com:

cp -r com app/


5. Создаем в папке проекта папку another_folder:

mkdir another_folder

Копируем туда lib и app:

cp -r app another_folder/
cp -r lib another_folder/



Переходим в папку another_folder:

cd another_folder

Копируем в папку app xml конфигурацию для логов:

cp ../log4j2.xml app

Запускаем MyAwesomeApplication.class:

java -cp "./app;lib/*" com.epam.jwd.app.MyAwesomeApplication











