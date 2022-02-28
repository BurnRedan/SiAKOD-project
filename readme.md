## Инструкция по клонированию проекта в локальный репозиторий

#### Для дальнейших действий у вас в системе должен быть установлен Git. Сделать это можно через IDEA
#### Если у вас возникли проблемы, пишите в фидбек в Дискорде или стучите в личку лидам


В IDEA выбираете File->New->Project from version control...

![image](https://user-images.githubusercontent.com/68110536/156013139-2d833d1d-85f1-4804-9f17-1c460aee9546.png)

Так же можно перейти к следующему шагу из приветственного окна, нажав Get from VCS

![image](https://user-images.githubusercontent.com/68110536/156022558-a0b9591c-68ac-4268-ae06-fcdf1e9b5131.png)


В Version Control должен быть выбран Git. В поле URL копируйте адрес проекта https://github.com/BurnRedan/SiAKOD-project и указываете директорию, в которую хотите его сохранить, нажимаете Clone

![image](https://user-images.githubusercontent.com/68110536/156014233-9366f9b2-3052-430f-9f52-4391733992f6.png)

Далее проект окажется в указанной вами директории. 

Перед открытием проекта скорее всего появится такое окно

![image](https://user-images.githubusercontent.com/68110536/156014683-91f98ade-7e0d-4a70-81b5-83cf5bc8c2e9.png)

Нажимаете Trust Project, никаких майнеров никто не оставлял

Далее через какое-то время проект подтянет нужные зависимости и индексирует файлы

![image](https://user-images.githubusercontent.com/68110536/156015226-ac13b4fe-fc0e-429e-a575-f5384fbbd1fa.png)

В проекте используется сторонняя библиотека Lombok - для упрощения написания кода, и JavaFX - для графики и отображения.
Из-за JavaFX, остальные библиотеки приходится ставить вручную, а не через Maven. Собственно, когда вы склонируете проект, у вас в файле module-info.java будет такая ситуация:

![image](https://user-images.githubusercontent.com/68110536/156015781-70453531-f084-4b8c-8626-d83e872b72bb.png)

Для решения проблемы переходим во вкладку File->Project Structure...

![image](https://user-images.githubusercontent.com/68110536/156016161-02f67f1a-e983-4e44-954c-6251ef7a3b8f.png)

В новом окне переходите во вкладку Problems и нажимаете Fix->Add Dependencies...

![image](https://user-images.githubusercontent.com/68110536/156016419-7e8b615b-12f9-4b77-8354-d1ee645ed1b0.png)

![image](https://user-images.githubusercontent.com/68110536/156016602-6c9c86c1-5a44-4258-be77-fa092bacbfd1.png)

![image](https://user-images.githubusercontent.com/68110536/156016668-34c1cdc0-8fba-4c07-a3f9-d4d6f06b43ea.png)

Далее пробуйте запустить приложение с класса App

![image](https://user-images.githubusercontent.com/68110536/156016863-bfc9b49d-b222-48e2-b809-8219b3beaa07.png)

Может вылезти проблема с несовместимым SDK, нажимаете на Open Project Settings внизу

![image](https://user-images.githubusercontent.com/68110536/156017117-2f8dfe90-bedb-4788-adb4-512f3d36e0d8.png)

Переставляете версию SDK на 17 версию, если такой версии нет, то здесь же можете ее добавить или скачать через пункт Add SDK

![image](https://user-images.githubusercontent.com/68110536/156017882-780b5ca6-b6f6-4704-82ba-1021e51a27f5.png)

Пробуйте запустить проект снова и должно появиться такое окно.

#### Voilà.

#### Опять же, если у вас возникли проблемы, пишите в фидбек в Дискорде или стучите в личку лидам

![image](https://user-images.githubusercontent.com/68110536/156018305-8b593e30-269c-4f7b-9b10-3e53bc49218e.png)

