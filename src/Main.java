import java.util.ArrayList;
import java.util.List;

public class Main {
//    Задача №1
//    Написать метод flattenStrings, который получает список строк и возвращает
//    список всех букв во всех строках, например:
//    flattenStrings(List.of(“каждый”, “охотник”))
//    вернет: List(“к”,“а”,“ж”,“д”,“ы”,“й”,“о”,“х”,“о”,”т”,”н”,”и”,”к”)
//*****************************************************************************
//    Задача №2
//    Написать метод flattenInts, который получает список целых чисел и
//    возвращает список чисел по следующему закону:
//    Если число положительное, то оно превращается во все числа от одного
//    до этого числа
//    Если число отрицательное, то оно превращается во все числа от этого
//    числа до -1
//    Если ноль, то он пропускается.
//    Пример: flattenInts(List.of(3, 0, -4, 2))
//    вернет: List(1, 2, 3, -4, -3, -2, -1, 1, 2)
//*****************************************************************************
//    Задача №3
//    Сделать так, чтобы решения задач 1 и 2 использовали максимально
//    возможное количество общего кода, т.е. вычленить общий алгоритм и
//    написать его для обобщенных типов.
//*****************************************************************************

    public static void main(String[] args) {
        System.out.println(Utils.flattenStrings(List.of("каждый", "охотник")));
        System.out.println(Utils.flattenInts(List.of(3, 0, -4, 2)));
    }

//    public static List<Integer> flattenInts(List<Integer> list){
//        List<Integer> result = new ArrayList<>();
//        for (Integer el: list) {
//            if (el > 0) {
//                for (int i = 1; i <= el; i++) {
//                    result.add(i);
//                }
//            } else if (el < 0) {
//                for (int i = el; i <= -1; i++) {
//                    result.add(i);
//                }
//            }
//        }
//        return result;
//    }

//    public static List<String> flattenStrings(List<String> list) {
//        List<String> letters = new ArrayList<>();
//        for (String el: list) {
//            char[] charArray = el.toCharArray();
//            for (char c: charArray) {
//                letters.add(String.valueOf(c));
//            }
//        }
//        return letters;
//    }
}
