import java.io.*;
import java.util.*;

import static com.sun.org.apache.xerces.internal.util.XMLChar.trim;


public class dictionary {


  public static void printAnswer(Map<String, String> dictionary,
                                 List<String> words, int m) {
    String value;
    String name;
    int j = 0;
    while (j < m) {
      name = words.get(j);
      ++j;
      value = dictionary.get(name);
      System.out.println(Objects.requireNonNullElse(value, "Не найдено"));
    }
  }

  public static List<String> readRequest() throws IOException {
    int a = 0;
    List<String> words = new ArrayList<>();
    try {
      BufferedReader wordReader = new BufferedReader(new InputStreamReader(System.in));
      int m = Integer.parseInt(wordReader.readLine());

      if (m <= 0) {
        System.out.println("Количество проверяемых слов должно быть положительным");
      }

      while (a < m) {

        String word = wordReader.readLine().toLowerCase();
        do {
          System.out.println("Укажите проверяемое слово");
        } while (!word.isEmpty());

        words.add(word);
        a++;
      }
        //System.out.println(Arrays.toString(new List[]{words}));
      } catch(NumberFormatException e){
        System.err.println("Неверно указано количество слов в словаре " +
            "(неверный формат числа)"
            + e.getMessage());
      }
      return (words);
    }

    public static Map<String, String> readDictionary () throws IOException {
      Map<String, String> dictionary = new HashMap<>();
      String name;
      String value;
      try {
        BufferedReader dictionaryReader = new BufferedReader
            (new FileReader(".idea/res/dictionary.txt"));

        //читаем количество строк в словаре:
        int n = Integer.parseInt(dictionaryReader.readLine());

        if (n < 0) {
          System.out.println("количество слов в словаре должно быть положительным");
        }
        if (n == 0) {
          System.out.println("Словарь пуст?");
        }
        //System.out.println(n);
        //читаем словарь и разбираем на слова и определения:
        for (int i = 1; i < n; ++i) {// прочитать n раз
          String line = dictionaryReader.readLine().toLowerCase(); // читаем строку.
          if (line.isEmpty()) {
            System.out.println("Словарь пуст");
          }
          int spaceIndex = line.lastIndexOf(':'); // до первого двоеточия-слово
          // (выясняем индекс":").
          name = line.substring(0, spaceIndex); // отрезали "слово".
          value = trim(line.substring(spaceIndex + 1));
          //отрезали "определение и лишний пробел".
          dictionary.put(name, value);//записали в словарь полученные значения
        }
        dictionaryReader.close();

      } catch (FileNotFoundException e) {
        System.err.println("Файл не найден: " + e.getMessage());
      } catch (IndexOutOfBoundsException e) {
        System.err.println("Ошибка в файле: в строке нет двоеточия между словом и определением");
      } catch (NumberFormatException e) {
        System.err.println("Неверно указано количество слов в словаре (неверный формат числа)"
            + e.getMessage());
      }
      return (dictionary);
    }

    //Во всех задачах разбивайте решение на несколько коммитов:
    //
    //условие в комментарии и никакого кода
    //решение без учёта файлов - чтение с клавиатуры и вывод на экран
    //добавляем файлы, если они указаны в задаче
    //разбиваем задачу на методы
    //добавляем try..catch
    //Задача 1
    //Программисты, как вы уже знаете, постоянно учатся,
    // а в общении между собой используют весьма специфический язык.
    // Чтобы систематизировать ваш пополняющийся профессиональный лексикон,
    // мы придумали эту задачу.
    //
    //Напишите программу создания небольшого словаря сленговых программерских выражений,
    // чтобы она потом по запросу возвращала значения из этого словаря.
    //
    //Формат входных данных
    //Файл dict.txt
    //В первой строке задано одно целое число n — количество слов в словаре.
    //
    //В следующих n строках записаны слова и их определения,
    // разделенные двоеточием и символом пробела.
    //
    //Ввод с клавиатуры
    //В первой строке записано целое число m — количество поисковых слов,
    // чье определение нужно вывести.
    //
    //В следующих m строках записаны сами слова, по одному на строке.
    //
    //Формат выходных данных
    //Для каждого слова, независимо от регистра символов,
    // если оно присутствует в словаре, необходимо вывести на экран его определение.
    //
    //Если слова в словаре нет, программа должна вывести "Не найдено", без кавычек.
    //
    //Примечание 1
    //Мини-словарь для начинающих разработчиков можно посмотреть тут.
    //
    //Примечание 2
    //Гарантируется, что в определяемом слове или фразе отсутствует двоеточие (:),
    // следом за которым идёт пробел.
    //
    //Пример входных данных
    //5
    //Змея: язык программирования Python
    //Баг: от англ. bug — жучок, клоп, ошибка в программе
    //Конфа: конференция
    //Костыль: код, который нужен, чтобы исправить несовершенство ранее написанного кода
    //Бета: бета-версия, приложение на стадии публичного тестирования
    //3
    //Змея
    //Жаба
    //костыль
    //Пример выходных данных
    //язык программирования Python
    //Не найдено
    //код, который нужен, чтобы исправить несовершенство ранее написанного код
    public static void main (String[]args) throws IOException {

      readDictionary();
      readRequest();
      printAnswer(readDictionary(), readRequest(), m);
    }
  }






