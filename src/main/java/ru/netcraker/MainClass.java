package ru.netcraker;


import ru.netcraker.my_linkedList.MylinkeList;

import java.util.Iterator;

public class MainClass {

    public static void main(String[] args){
        System.out.println("------initial-------");
        MylinkeList<Integer> mylinkeList = new MylinkeList<>();
        mylinkeList.add(20);
        mylinkeList.add(100);
        mylinkeList.add(1000);
        mylinkeList.add(90000);
        mylinkeList.add(200000);
        mylinkeList.add(5550000);

        System.out.println("mylinkeList = " + mylinkeList);
        //--------------------------------------------------
        System.out.println("------remove(index)-------");
        mylinkeList.remove(2);
        System.out.println("------remove(value)-------" + mylinkeList.remove(new Integer(5550000)));

        //--------------------------------------------------
        System.out.println("mylinkeList = " + mylinkeList);
        //--------------------------------------------------
        System.out.println("------indexOf(10)-------");
        System.out.println("index-->" + mylinkeList.indexOf(90000));
        System.out.println("------set(0,0)-------");
        mylinkeList.set(0,0);
        //--------------------------------------------------
        System.out.println("mylinkeList = " + mylinkeList);

        System.out.println("-------------old iterator--------------");
        Iterator<Integer> iterator = mylinkeList.iterator();
        while (iterator.hasNext()){
            System.out.println("iterator.next-->" + iterator.next());
        }

        System.out.println("-------------get--------------");
        System.out.println("value[0] = " + mylinkeList.get(0));
        
        System.out.println("-------------to Array----------");
        for (var i: mylinkeList.toArray()) {
            System.out.println("i = " + i);
        }

        System.out.println("-------------to Array(two)----------");
        Integer[] integers = new Integer[] { 0, 0, 0, 0 };
        integers = mylinkeList.toArray(integers);
        for (var i: integers) {
            System.out.println("i = " + i);
        }

        System.out.println("-----------contains(90000)-----------\n"
                + mylinkeList.contains(90000));

        System.out.println("-------------foreach--------------");
        for(Integer data : mylinkeList){
            System.out.println("data-->" + data);
        }
        System.out.println("-------------foreach2(lambda)--------------");
        mylinkeList.forEach(System.out::println);

        System.out.println("-------------clear--------------");
        mylinkeList.clear();
        System.out.println("mylinkeList = " + mylinkeList);

        System.out.println("-------------isEmpty--------------");
        System.out.println("isEmpty = " + mylinkeList.isEmpty());

    }

}
