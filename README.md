Generator za MBRS.

## Potrebno
-  MagicDraw 17.0.3.
- SciencenCenter (sc) projekat (skinuti ceo) - https://github.com/GrYloX/ScienceCenter
  - sc/sc je frontend
-  Angular CLI 7.2.3.
- Eclipse

## Podesavanje plugina
1. Skinuti i otvoriti projekat PluginDevelopment u Eclipse okruzenju.
2. Podesiti ga po upustvu iz vezbi 3 (java-magicdraw).
3. U build.properties se stavi putanja do MagicDraw-a.
4. Folder resources unutar PluginDevelopment-a, u app.properties se postavi putanja do sc projekta. 
   - Npr: app.scpath=D:\\\Downloads\\\ScienceCenter-master
5. Buildovati PluginDevelopment preko Ant-a u build.xml.
6. Otvoriti MagicDraw i ubaciti MBRS model.mdzip.

## Generisanje koda
1. Uvesti sc - backend u Eclipse kao maven projekat. 
2. Izgenerisati kod iz MagicDraw-a.

## Pokretanje i testiranje
1. Pokrenuti sc - backend kao Java applikaciju na ScienceCenterApplication.java.
2. Otici u folder \ScienceCenter-master\sc\sc i pokrenuti angular projekat sa ng serve.

Putanja je https://localhost:4200 i moze se objaviti novi rad, tj. proci kroz camunda proces objave i odobrenja rada.

