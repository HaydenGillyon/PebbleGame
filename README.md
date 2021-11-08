# PebbleGame


## Introduction 

This game simulates players that pick up pebbles and discard pebbles with the goal to have 10 pebbles with weights that add up to exactly 100.


## Run Setup

* Unzip Pebble.zip
* Navigate to the directory where you extracted pebbles.zip
* Open Command prompt and type
~~~
java -jar pebbles.jar 
~~~


## Test Setop

* Unzip Pebble.zip
* Unzip pebblesTest.zip
* Navigate to the directory where you extracted pebblesTest.zip
* Open Command prompt and type
~~~
java -cp junit-4.13.2.jar ;hamcrest-core-1.3.jar;. org.junit.runner.JUnitCore  BagTest BlackBagTest PebbleGameTest PebbleTest WhiteBagTest
~~~



## Dependencies

Java 17.0.1

Junit 4 { If Testing }

