
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


6. Создал папку fat_jar и перешел в нее. В этой папке создал конфигурационный файл Maven - pom.xml, также сделал иерархию папок для проекта maven.
Для сборки в fat jar использовал maven shade plugin, интегрировал в pom.xml нужные зависимости.

Для сборки в jar использовал:

mvn package

Для запуска jar:

$ java -jar target/your-project-1.0-SNAPSHOT.jar














